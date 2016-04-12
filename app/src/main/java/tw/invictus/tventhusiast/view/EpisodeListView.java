package tw.invictus.tventhusiast.view;

import tw.invictus.tventhusiast.model.pojo.Season;

/**
 * Created by ivan on 2/20/16.
 */
public interface EpisodeListView {
    void onSeasonLoaded(Season season);
    void onEpisodeCheckChanged(int position, boolean checked);
}
