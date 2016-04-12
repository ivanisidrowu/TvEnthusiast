package tw.invictus.tventhusiast.view;

import tw.invictus.tventhusiast.model.pojo.TvShow;

/**
 * Created by ivan on 2/17/16.
 */
public interface SeasonListView {
    void onSeasonListLoaded(TvShow show, boolean isShowInDb);
    void onSeasonClicked(int position);
    void onSeasonCheckChanged(int position, boolean isChecked);
}
