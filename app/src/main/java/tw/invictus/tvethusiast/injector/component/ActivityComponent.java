package tw.invictus.tvethusiast.injector.component;

import android.content.Context;

import dagger.Component;
import tw.invictus.tvethusiast.injector.Activity;
import tw.invictus.tvethusiast.injector.module.ActivityModule;

/**
 * Created by ivan on 7/10/15.
 */
@Activity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Context context();
}
