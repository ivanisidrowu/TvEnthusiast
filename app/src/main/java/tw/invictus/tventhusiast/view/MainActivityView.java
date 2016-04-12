package tw.invictus.tventhusiast.view;

import java.util.List;

import tw.invictus.tventhusiast.model.pojo.TvShow;

/**
 * Created by ivan on 2/27/16.
 */
public interface MainActivityView {
    void onTvShowSearched(List<TvShow> shows);
    void onSearchResultClicked(TvShow show);
}
