package tw.invictus.tvethusiast.injector.component;

import javax.inject.Singleton;

import dagger.Component;
import tw.invictus.tvethusiast.TvEthusiastApplication;
import tw.invictus.tvethusiast.injector.AppModule;
import tw.invictus.tvethusiast.model.Repository;

/**
 * Created by ivan on 7/10/15.
 */
@Singleton @Component(modules = AppModule.class)
public interface AppComponent {

    TvEthusiastApplication app();
    Repository dataRepository();
}
