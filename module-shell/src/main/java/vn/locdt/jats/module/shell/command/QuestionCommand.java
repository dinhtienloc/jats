package vn.locdt.jats.module.shell.command;

import org.jline.reader.LineReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;
import vn.locdt.jats.module.shell.exception.CliOptionQuestionNotMapping;
import vn.locdt.jats.module.shell.exception.QuestionDeclareNotConsistent;
import vn.locdt.jats.module.shell.exception.RunCommandMethodNotFound;
import vn.locdt.jats.module.shell.question.ChainingQuestionCLI;
import vn.locdt.jats.module.shell.question.QuestionCLI;
import vn.locdt.jats.module.shell.question.QuestionStatus;
import vn.locdt.jats.module.shell.question.annotation.QuestionImports;
import vn.locdt.jats.module.shell.question.annotation.QuestionShellOption;
import vn.locdt.jats.util.common.LogUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * Created by locdt on 1/22/2018.
 */
@Component
public abstract class QuestionCommand {

	@Autowired
	@Lazy
	private LineReader lineReader;

	protected final static String SHELL_COMMAND_JAVA_METHOD = "runCommand";
	private Map<String, CliOptionValue> questionOptsMap;

	public QuestionCommand() {
		this.questionOptsMap = new HashMap<>();
	}

	@SuppressWarnings("unchecked")
	protected QuestionStatus startQuestions(Parameters parameter) {
		QuestionStatus status = QuestionStatus.FINISHED;
		try {
			List<QuestionCLI> questions = this.getImportedQuestionList();

			if (this.questionOptsMap.size() > questions.size())
				throw new QuestionDeclareNotConsistent("Found " + this.questionOptsMap.size() + " mapped but " + questions.size() + " imported.");

			for (QuestionCLI q : questions) {
				CliOptionValue optionValue = this.questionOptsMap.get(q.getClass().getSimpleName());
				LogUtils.printDebugLog("Getting mapped attributes by key '" + q.getClass().getSimpleName() + "': " + optionValue);

				if (optionValue != null && optionValue.getValue() != null) {
					LogUtils.printDebugLog("Skipping " + q.getClass().getCanonicalName());
				} else {
					if (parameter != null) {
						Object questionParam = parameter.get(q.getClass());
						if (questionParam != null && q instanceof ChainingQuestionCLI) {
							((ChainingQuestionCLI) q).setParameter(questionParam);
						}
					}

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

		return status;
	}

	@SuppressWarnings("unchecked")
	private List<QuestionCLI> getImportedQuestionList() {
		List<QuestionCLI> qs = new ArrayList<>();
		try {
			Class[] classes = this.getImportedQuestionClasses();
			LogUtils.printDebugLog("Imported Question Length: " + classes.length);
			for (Class c : classes) {
				Constructor constructor = c.getConstructor();
				QuestionCLI q = (QuestionCLI) constructor.newInstance();
				q.setLineReader(this.lineReader);
				qs.add(q);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		return qs;
	}

	private Class<?>[] getImportedQuestionClasses() {
		QuestionImports questionImports = this.getClass().getAnnotation(QuestionImports.class);
		if (questionImports != null)
			return questionImports.value();

		return new Class<?>[]{};
	}

	protected void resolveOptionValues(String... values) throws CliOptionQuestionNotMapping {
		try {
			Method runCommandMethod = this.getRunCommandMethod();
			if (runCommandMethod == null)
				throw new RunCommandMethodNotFound("Could not found any method named '" +
						SHELL_COMMAND_JAVA_METHOD + "' in class '" +
						this.getClass().getSimpleName() + "'");

			Parameter[] parameters = runCommandMethod.getParameters();
			if (parameters.length == 0)
				return;

			for (int i = 0; i < parameters.length; i++) {
				Parameter p = parameters[i];
				CliQuestionOption cliQuestionOption = this.getQuestionOfParameter(p);
				if (cliQuestionOption == null) continue;

				Class clazz = cliQuestionOption.getQuestionClass();
				if (clazz == null) {
					LogUtils.printDebugLog(p.getName() + " does not map to any value. Skip");
					continue;
				}

				if (!cliQuestionOption.getActivedValue().contains(values[i])) {
					LogUtils.printDebugLog(p.getName() + " has valid value that is not active the question. Skip");
					continue;
				}

				String key = clazz.getSimpleName();
				String[] options = this.getCliOptionOfParameter(p);

				CliOptionValue optionValue = new CliOptionValue(options, values[i]);
				this.questionOptsMap.put(key, optionValue);
				LogUtils.printDebugLog("Saved Mapping: " + key + " -> " + optionValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LogUtils.printDebugLog("Question - Option Mapper: " + this.questionOptsMap);
	}

	private Method getRunCommandMethod() {
		for (Method m : this.getClass().getMethods()) {
			if (SHELL_COMMAND_JAVA_METHOD.equals(m.getName())) {
				return m;
			}
		}
		return null;
	}

	private String[] getCliOptionOfParameter(Parameter p) {
		ShellOption option = p.getAnnotation(ShellOption.class);
		if (option != null) {
			return option.value();
		}
		return null;
	}

	private CliQuestionOption getQuestionOfParameter(Parameter p) {
		QuestionShellOption option = p.getAnnotation(QuestionShellOption.class);
		if (option != null) {
			return new CliQuestionOption(option.value(), option.activedValue());
		}
		return null;
	}

	protected LineReader getLineReader() {
		return this.lineReader;
	}

//	/**
//	 * Given an array of objects and count number of not null object.
//	 * @param objects Object's array
//	 * @return 	0  - All objects are null
//	 * 			-1 - Null objects are smaller than the length of array
//	 * 			1  - None of objects are null
//	 */
//	protected int countNotNullValues(Object... objects) {
//    	int count = 0;
//    	for (Object o : objects) {
//    		if (o != null) count++;
//		}
//
//		if (count == 0) return 0;
//    	if (count < objects.length) return -1;
//    	else return 1;
//	}

	private class CliOptionValue {
		private String[] keys;
		private String value;

		CliOptionValue(String[] keys, String value) {
			this.keys = keys;
			this.value = value;
		}

//        public String[] getKeys() {
//            return this.keys;
//        }
//
//        public void setKeys(String[] keys) {
//            this.keys = keys;
//        }

		public String getValue() {
			return this.value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "CliOptionValue{" +
					"keys=" + Arrays.toString(this.keys) +
					", value='" + this.value + '\'' +
					'}';
		}
	}

	private class CliQuestionOption {
		private Class questionClass;
		private List<String> activedValue;

		CliQuestionOption(Class questionClass, String[] activedValue) {
			this.questionClass = questionClass;
			this.activedValue = Arrays.asList(activedValue);
		}

		Class getQuestionClass() {
			return this.questionClass;
		}

		List<String> getActivedValue() {
			return this.activedValue;
		}
	}

	public class Parameters {
		private Map<String, Object> parameters;

		public Parameters() {
			this.parameters = new HashMap<>();
		}

		public Parameters put(Class clazz, Object obj) {
			this.parameters.put(clazz.getSimpleName(), obj);
			return this;
		}

		public Object get(Class clazz) {
			return this.parameters.get(clazz.getSimpleName());
		}
	}
}
