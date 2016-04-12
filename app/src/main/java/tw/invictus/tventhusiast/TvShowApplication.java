package tw.invictus.tventhusiast;

import android.app.Application;

import tw.invictus.tventhusiast.di.ApiModule;
import tw.invictus.tventhusiast.di.DaggerDetailFragmentComponent;
import tw.invictus.tventhusiast.di.DaggerEpisodeListActivityComponent;
import tw.invictus.tventhusiast.di.DaggerMainActivityComponent;
import tw.invictus.tventhusiast.di.DaggerMainFragmentComponent;
import tw.invictus.tventhusiast.di.DaggerSeasonListFragmentComponent;
import tw.invictus.tventhusiast.di.DbModule;
import tw.invictus.tventhusiast.di.DetailFragmentComponent;
import tw.invictus.tventhusiast.di.DetailFragmentModule;
import tw.invictus.tventhusiast.di.EpisodeListActivityComponent;
import tw.invictus.tventhusiast.di.EpisodeListActivityModule;
import tw.invictus.tventhusiast.di.MainActivityComponent;
import tw.invictus.tventhusiast.di.MainActivityModule;
import tw.invictus.tventhusiast.di.MainFragmentComponent;
import tw.invictus.tventhusiast.di.MainFragmentModule;
import tw.invictus.tventhusiast.di.SeasonListFragmentComponent;
import tw.invictus.tventhusiast.di.SeasonListFragmentModule;
import tw.invictus.tventhusiast.view.activity.EpisodeListActivity;
import tw.invictus.tventhusiast.view.activity.MainActivity;
import tw.invictus.tventhusiast.view.fragment.DetailFragment;
import tw.invictus.tventhusiast.view.fragment.MainFragment;
import tw.invictus.tventhusiast.view.fragment.SeasonListFragment;

/**
 * Created by ivan on 12/26/15.
 */
public class TvShowApplication extends Application {

    public MainFragmentComponent getMainFragmentComponent(MainFragment mainFragment) {
        return DaggerMainFragmentComponent.builder()
                .apiModule(new ApiModule())
                .dbModule(new DbModule(mainFragment.getContext()))
                .mainFragmentModule(new MainFragmentModule(mainFragment.getContext())).build();
    }

    public DetailFragmentComponent getDetailFragmentComponent(DetailFragment detailFragment){
        return DaggerDetailFragmentComponent.builder()
                .apiModule(new ApiModule())
                .dbModule(new DbModule(detailFragment.getContext()))
                .detailFragmentModule(new DetailFragmentModule(detailFragment.getContext())).build();
    }

    public SeasonListFragmentComponent getSeasonListComponent(SeasonListFragment seasonListFragment){
        return DaggerSeasonListFragmentComponent.builder()
                .apiModule(new ApiModule())
                .dbModule(new DbModule(seasonListFragment.getContext()))
                .seasonListFragmentModule(new SeasonListFragmentModule()).build();
    }

    public EpisodeListActivityComponent getEpisodeListComponent(EpisodeListActivity episodeListActivity){
        return DaggerEpisodeListActivityComponent.builder()
                .apiModule(new ApiModule())
                .dbModule(new DbModule(episodeListActivity))
                .episodeListActivityModule(new EpisodeListActivityModule()).build();
    }

    public MainActivityComponent getMainActivityComponent(MainActivity activity){
        return DaggerMainActivityComponent.builder()
                .apiModule(new ApiModule())
                .mainActivityModule(new MainActivityModule(activity)).build();
    }
}
