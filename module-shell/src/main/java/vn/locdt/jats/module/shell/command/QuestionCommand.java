package vn.locdt.jats.module.shell.command;

import org.springframework.shell.core.annotation.CliOption;
import vn.locdt.jats.module.shell.exception.CliOptionQuestionNotMapping;
import vn.locdt.jats.module.shell.exception.QuestionDeclareNotConsistent;
import vn.locdt.jats.module.shell.question.annotation.QuestionImports;
import vn.locdt.jats.module.shell.question.QuestionStatus;
import vn.locdt.jats.module.shell.question.QuestionCLI;
import vn.locdt.jats.module.shell.question.annotation.QuestionCliOption;
import vn.locdt.jats.module.shell.setting.SettingData;
import vn.locdt.jats.util.LogUtils;

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
    private Map<QuestionCLI, CliOptionValue> questionOptsMap;

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
                CliOptionValue optionValue = questionOptsMap.get(q);
                if (optionValue != null && optionValue.getValue() != null) {
                    q.start();
                    LogUtils.printDebugLog("Class: " + q.getClass().getCanonicalName() + " | Status: " + status.name());
                    if (QuestionStatus.STOP.equals(status))
                        break;
                }
                else {
                    LogUtils.printDebugLog("Skipping " + q.getClass().getCanonicalName());
                }
            }
        } catch (Exception e) {
            LogUtils.printErrorLog("Error occurs when run questions", e);
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
            LogUtils.printDebugLog(classes.length);
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

    private Class<?>[] getImportedQuestionClasses() throws Exception{
        for (Annotation a: getClass().getAnnotations()) {
            if (a.getClass().equals(QuestionImports.class))
                return (Class<?>[]) a.getClass().getMethod("value").invoke(a);
        }
        return new Class<?>[]{};
    }

    protected void resolveOptionValues(String... values) throws CliOptionQuestionNotMapping {
        try {
            Method[] methods = getClass().getMethods();
            Method runCommandMethod = Arrays.stream(methods)
                    .filter(m -> SHELL_COMMAND_JAVA_METHOD.equals(m.getName()))
                    .findFirst().orElse(null);

            if (runCommandMethod == null)
                return;

            Parameter[] parameters = runCommandMethod.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                Parameter p = parameters[i];
                Class clazz = getQuestionOfParameter(p);
                if (clazz == null)
                    throw new CliOptionQuestionNotMapping("Could not found any question mapped to " + p.getName());

                QuestionCLI question = (QuestionCLI) clazz.getConstructor().newInstance();
                String[] options = getCliOptionOfParameter(p);

                CliOptionValue optionValue = new CliOptionValue(options, values[i]);
                questionOptsMap.put(question, optionValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    }
}
