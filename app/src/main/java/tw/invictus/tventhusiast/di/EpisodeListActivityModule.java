package tw.invictus.tventhusiast.di;

import dagger.Module;
import dagger.Provides;
import tw.invictus.tventhusiast.presenter.EpisodeListPresenter;
import tw.invictus.tventhusiast.presenter.EpisodeListPresenterImpl;

/**
 * Created by ivan on 2/18/16.
 */
@Module
public class EpisodeListActivityModule {

    public EpisodeListActivityModule() {
    }

    @Provides
    EpisodeListPresenter provideEpisodeListPresenter(){
        return new EpisodeListPresenterImpl();
    }
}