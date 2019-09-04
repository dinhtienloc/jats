//package vn.locdt.jats.aj;
//
//import java.lang.reflect.Method;
//import java.lang.reflect.Parameter;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.jline.reader.LineReader;
//import vn.locdt.jats.bundle.question.JQuestion;
//import vn.locdt.jats.module.shell.command.QuestionCommand;
//import vn.locdt.jats.module.shell.question.annotation.Question;
//import vn.locdt.jats.module.shell.question.annotation.Selection;
//import vn.locdt.jats.util.common.LogUtils;
//import vn.locdt.jats.util.common.StringUtils;
//
//@Aspect
//public class QuestionAJ {
//	@Around("execution(* vn.locdt.jats.synergix.addon.command.CreateFormCommand.runCommand(..))")
//	public Object resolveShellOptionValues(ProceedingJoinPoint pjp) throws Throwable {
//
//		System.out.println("Inside runCommand method");
//		//get original args
//		Object[] args = pjp.getArgs();
//
//		//get all annotations for arguments
//		MethodSignature signature = (MethodSignature) pjp.getSignature();
//		Method runCommandMethod = signature.getMethod();
//
//		Parameter[] parameters = runCommandMethod.getParameters();
//		if (parameters.length == 0)
//			return pjp.proceed(args);
//
//		for (int i = 0; i < parameters.length; i++) {
//			Parameter p = parameters[i];
//			// param has value
//			if (StringUtils.isNotEmpty(args[i].toString())) {
//				continue;
//			}
//
//			Question qAnno = p.getAnnotation(Question.class);
//			if (qAnno == null) {
//				LogUtils.printDebugLog("["+p.getName()+"]" + " didn't map with any question. Skip");
//				continue;
//			}
//
////			Selection selectionAnno = p.getAnnotation(Selection.class);
////			LineReader lr = question.getLineReader();
////			if (selectionAnno == null) {
////				args[i] = JQuestion.input(lr, qAnno.title()).getValue();
////			}
////			else {
////				args[i] = JQuestion.select(lr, qAnno.title(), selectionAnno.list()).getValue();
////			}
//		}
//
//		//execute original method with new args
//		return pjp.proceed(args);
//	}
//}
