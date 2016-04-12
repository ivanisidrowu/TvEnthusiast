package tw.invictus.tventhusiast.presenter;

import tw.invictus.tventhusiast.model.api.RestfulApi;
import tw.invictus.tventhusiast.model.db.RealmService;
import tw.invictus.tventhusiast.model.pojo.Season;
import tw.invictus.tventhusiast.model.pojo.TvShow;
import tw.invictus.tventhusiast.view.EpisodeListView;

/**
 * Created by ivan on 2/18/16.
 */
public interface EpisodeListPresenter extends BasePresenter {

    void setView(EpisodeListView view);

    void setApi(RestfulApi api);

    void setService(RealmService service);

    void loadSeason(TvShow show, Season season);

    void updateWatchedEpisode(long episodeId, boolean isWatched);
}
