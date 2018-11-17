package vn.locdt.jats.module.shell.question.annotation;

import org.springframework.shell.standard.ShellOption;

import java.lang.annotation.*;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface QuestionShellOption {
    Class value();
}
