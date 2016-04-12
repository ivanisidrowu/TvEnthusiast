package tw.invictus.tventhusiast.di;

/**
 * Created by ivan on 2/17/16.
 */

import dagger.Module;
import dagger.Provides;
import tw.invictus.tventhusiast.presenter.SeasonListPresenter;
import tw.invictus.tventhusiast.presenter.SeasonListPresenterImpl;

@Module
public class SeasonListFragmentModule {

    public SeasonListFragmentModule() {
    }

    @Provides
    SeasonListPresenter provideSeasonListPresenter(){
        return new SeasonListPresenterImpl();
    }
}
