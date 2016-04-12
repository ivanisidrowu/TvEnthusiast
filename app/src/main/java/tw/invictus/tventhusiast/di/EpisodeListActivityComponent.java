package tw.invictus.tventhusiast.di;

import javax.inject.Singleton;

import dagger.Component;
import tw.invictus.tventhusiast.view.activity.EpisodeListActivity;

/**
 * Created by ivan on 2/18/16.
 */
@Singleton
@Component(modules = {EpisodeListActivityModule.class, ApiModule.class, DbModule.class})
public interface EpisodeListActivityComponent {
    void inject(EpisodeListActivity activity);
}