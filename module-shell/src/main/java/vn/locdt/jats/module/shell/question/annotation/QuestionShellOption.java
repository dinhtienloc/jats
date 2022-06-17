package vn.locdt.jats.module.shell.question.annotation;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.shell.standard.ShellOption;

import java.lang.annotation.*;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface QuestionShellOption {
    Class value();
    String[] activedValue() default {ShellOption.NULL, ShellOption.NONE};
}
