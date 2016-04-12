package tw.invictus.tventhusiast.view;

import java.util.ArrayList;

import tw.invictus.tventhusiast.model.pojo.TvShow;

/**
 * Created by ivan on 12/26/15.
 */
public interface MainFragmentView {
    void onTvShowClicked(TvShow show);
    void onMoreTvShowsLoaded(ArrayList<TvShow> shows);
    void onTvShowsLoaded(ArrayList<TvShow> shows);
    void onLoadError(Throwable throwable);
}
