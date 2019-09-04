package vn.locdt.jats.core;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.jline.reader.LineReader;
import org.springframework.context.annotation.Configuration;
import vn.locdt.jats.bundle.question.JQuestion;
import vn.locdt.jats.module.shell.command.QuestionCommand;
import vn.locdt.jats.module.shell.question.annotation.Confirmation;
import vn.locdt.jats.module.shell.question.annotation.Question;
import vn.locdt.jats.module.shell.question.annotation.Selection;
import vn.locdt.jats.util.common.LogUtils;
import vn.locdt.jats.util.common.StringUtils;

@Aspect
@Configuration
public class QuestionAJ {
	@Around("execution(* runCommand(..)) && target(q)")
	public Object resolveShellOptionValues(ProceedingJoinPoint pjp, QuestionCommand q) throws Throwable {

		//get original args
		Object[] args = pjp.getArgs();

		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method runCommandMethod = signature.getMethod();

		Parameter[] parameters = runCommandMethod.getParameters();
		if (parameters.length == 0)
			return pjp.proceed(args);

		for (int i = 0; i < parameters.length; i++) {
			Parameter p = parameters[i];
			// param has value
			if (args[i] != null && StringUtils.isNotEmpty(args[i].toString())) {
				continue;
			}

			Question questionAnno = p.getAnnotation(Question.class);
			if (questionAnno == null) {
				LogUtils.printDebugLog("[" + p.getName() + "]" + " didn't map with any question. Skip");
				continue;
			}

			LineReader lr = q.getLineReader();

			Selection selectionAnno = p.getAnnotation(Selection.class);
			Confirmation confirmAnno = p.getAnnotation(Confirmation.class);

			if (selectionAnno != null) {
				args[i] = JQuestion.select(lr, questionAnno.title(), selectionAnno.list()).prompt();
			} else if (confirmAnno != null) {
				args[i] = JQuestion.confirm(lr, questionAnno.title()).prompt();
			} else {
				args[i] = JQuestion.input(lr, questionAnno.title()).prompt();
			}
		}

		//execute original method with new args
		return pjp.proceed(args);
	}
}
