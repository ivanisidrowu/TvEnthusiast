package tw.invictus.tvethusiast.injector;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by ivan on 7/10/15.
 */

@Scope @Retention(RUNTIME)
public @interface Activity {
}
