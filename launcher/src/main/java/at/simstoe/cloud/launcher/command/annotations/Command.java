package at.simstoe.cloud.launcher.command.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 22.12.2024 - 23:41
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    String name();
    String description() default "";
    String[] aliases() default {};
}
