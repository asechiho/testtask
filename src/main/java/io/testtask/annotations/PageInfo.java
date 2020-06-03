package io.testtask.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation using to describe page.
 * id/css/xpath - page locator
 * pageName - page name on UI
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface PageInfo {

    String id() default "";

    String xpath() default "";

    String css() default "";

    String pageName();
}
