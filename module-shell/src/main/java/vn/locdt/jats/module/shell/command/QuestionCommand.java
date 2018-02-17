package vn.locdt.jats.module.shell.command;

import org.springframework.shell.core.annotation.CliOption;
import vn.locdt.jats.module.shell.exception.CliOptionQuestionNotMapping;
import vn.locdt.jats.module.shell.exception.QuestionDeclareNotConsistent;
import vn.locdt.jats.module.shell.exception.RunCommandMethodNotFound;
import vn.locdt.jats.module.shell.question.annotation.QuestionImports;
import vn.locdt.jats.module.shell.question.QuestionStatus;
import vn.locdt.jats.module.shell.question.QuestionCLI;
import vn.locdt.jats.module.shell.question.annotation.QuestionCliOption;
import vn.locdt.jats.module.shell.setting.SettingData;
import vn.locdt.jats.util.LogUtils;

import javax.rmi.CORBA.Util;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * Created by locdt on 1/22/2018.
 */
public abstract class QuestionCommand {
    protected final static String SHELL_COMMAND_JAVA_METHOD = "runCommand";
    private Map<String, CliOptionValue> questionOptsMap;

    public QuestionCommand() {
        this.questionOptsMap = new HashMap<>();
    }

    public QuestionStatus startQuestions() {
        QuestionStatus status = QuestionStatus.FINISHED;
        try {
            List<QuestionCLI> questions = getImportedQuestionList();

            if (questionOptsMap.size() > questions.size())
                throw new QuestionDeclareNotConsistent("Found " + questionOptsMap.size() + " mapped but " + questions.size() + " imported.");

            for (QuestionCLI q : questions) {

                CliOptionValue optionValue = questionOptsMap.get(q.getClass().getSimpleName());
                LogUtils.printDebugLog("Getting mapped attributes by key '" + q.getClass().getSimpleName() + "': " + optionValue);
                if (optionValue != null && optionValue.getValue() != null) {
                    LogUtils.printDebugLog("Skipping " + q.getClass().getCanonicalName());
                }
                else {
                    status = q.start();
                    LogUtils.printDebugLog("Class: " + q.getClass().getCanonicalName() + " | Status: " + status.name());
                    if (QuestionStatus.STOP.equals(status))
                        break;
                }
            }
        } catch (Exception e) {
            LogUtils.printErrorLog("Error occurs while creating questions:");
            e.printStackTrace();
            status = QuestionStatus.STOP;
        }

        if (QuestionStatus.FINISHED.equals(status))
            SettingData.save();

        return status;
    }

    private List<QuestionCLI> getImportedQuestionList() {
        List<QuestionCLI> qs = new ArrayList<>();
        try {
            Class[] classes = getImportedQuestionClasses();
            LogUtils.printDebugLog("Imported Question Length: " + classes.length);
            for (Class c : classes) {
                Constructor constructor = c.getConstructor();
                qs.add((QuestionCLI) constructor.newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return qs;
    }

    private Class<?>[] getImportedQuestionClasses() {
        QuestionImports questionImports = getClass().getAnnotation(QuestionImports.class);
        if (questionImports != null)
            return questionImports.value();

        return new Class<?>[]{};
    }

    protected void resolveOptionValues(String... values) throws CliOptionQuestionNotMapping {
        try {
            Method runCommandMethod = getRunCommandMethod();
            if (runCommandMethod == null)
                throw new RunCommandMethodNotFound("Could not found any method named '" +
                        SHELL_COMMAND_JAVA_METHOD + "' in class '" +
                        getClass().getSimpleName() + "'");

            Parameter[] parameters = runCommandMethod.getParameters();
            if (parameters.length == 0)
                return;

            for (int i = 0; i < parameters.length; i++) {
                Parameter p = parameters[i];
                Class clazz = getQuestionOfParameter(p);
                if (clazz == null)
                    throw new CliOptionQuestionNotMapping("Could not found any question mapped to " + p.getName());

                String key = clazz.getSimpleName();
                String[] options = getCliOptionOfParameter(p);

                CliOptionValue optionValue = new CliOptionValue(options, values[i]);
                questionOptsMap.put(key, optionValue);
                LogUtils.printDebugLog("Saved Mapping: " + key + " -> " + optionValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtils.createDebugLog("Question - Option Mapper: " + questionOptsMap);
    }

    private Method getRunCommandMethod() {
        for (Method m : getClass().getMethods()) {
            if (SHELL_COMMAND_JAVA_METHOD.equals(m.getName())) {
                return m;
            }
        }
        return null;
    }

    private String[] getCliOptionOfParameter(Parameter p) {
        CliOption option = p.getAnnotation(CliOption.class);
        if (option != null) {
            return option.key();
        }
        return null;
    }

    private Class getQuestionOfParameter(Parameter p) {
        QuestionCliOption option = p.getAnnotation(QuestionCliOption.class);
        if (option != null) {
            return option.value();
        }
        return null;
    }

    public class CliOptionValue {
        private String[] keys;
        private String value;

        public CliOptionValue(String[] keys, String value) {
            this.keys = keys;
            this.value = value;
        }

        public String[] getKeys() {
            return keys;
        }

        public void setKeys(String[] keys) {
            this.keys = keys;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "CliOptionValue{" +
                    "keys=" + Arrays.toString(keys) +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}
