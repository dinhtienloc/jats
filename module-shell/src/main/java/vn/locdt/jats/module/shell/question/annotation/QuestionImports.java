package vn.locdt.jats.module.shell.question.annotation;

import java.lang.annotation.*;

/**
 * Created by locdt on 1/27/2018.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface QuestionImports {
    Class<?>[] value();
}
