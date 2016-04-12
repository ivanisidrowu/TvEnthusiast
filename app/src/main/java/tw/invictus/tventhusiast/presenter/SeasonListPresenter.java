package tw.invictus.tventhusiast.presenter;

import tw.invictus.tventhusiast.model.api.RestfulApi;
import tw.invictus.tventhusiast.model.db.RealmService;
import tw.invictus.tventhusiast.model.pojo.TvShow;
import tw.invictus.tventhusiast.view.SeasonListView;

/**
 * Created by ivan on 2/17/16.
 */
public interface SeasonListPresenter extends BasePresenter {
    void setApi(RestfulApi api);

    void setView(SeasonListView view);

    void setDbService(RealmService service);

    void getSeasons(TvShow show);

    void updatedWatchedSeason(long seasonId, boolean isWatched);
}
