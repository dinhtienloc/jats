package vn.locdt.jats.module.shell.question.annotation;

import org.springframework.shell.core.annotation.CliOption;

import java.lang.annotation.*;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface QuestionCliOption {
    Class value();
}
