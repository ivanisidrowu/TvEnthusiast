package tw.invictus.tventhusiast.di;

import javax.inject.Singleton;

import dagger.Component;
import tw.invictus.tventhusiast.view.fragment.SeasonListFragment;

/**
 * Created by ivan on 2/17/16.
 */
@Singleton
@Component(modules = {SeasonListFragmentModule.class, ApiModule.class, DbModule.class})
public interface SeasonListFragmentComponent {
    void inject(SeasonListFragment fragment);
}
