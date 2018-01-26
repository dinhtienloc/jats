package vn.locdt.jats.question;

import org.springframework.context.annotation.Import;

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
