package tw.invictus.tventhusiast.di;

import javax.inject.Singleton;

import dagger.Component;
import tw.invictus.tventhusiast.view.activity.MainActivity;

/**
 * Created by ivan on 2/26/16.
 */
@Singleton
@Component(modules = {ApiModule.class, MainActivityModule.class})
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
