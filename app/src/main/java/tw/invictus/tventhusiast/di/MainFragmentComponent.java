package tw.invictus.tventhusiast.di;

import javax.inject.Singleton;

import dagger.Component;
import tw.invictus.tventhusiast.view.fragment.MainFragment;

/**
 * Created by ivan on 12/26/15.
 */
@Singleton
@Component(modules = {MainFragmentModule.class, ApiModule.class, DbModule.class})
public interface MainFragmentComponent {
    void inject(MainFragment mainFragment);
}
