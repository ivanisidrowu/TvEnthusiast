package tw.invictus.tventhusiast.presenter;

import tw.invictus.tventhusiast.model.api.RestfulApi;
import tw.invictus.tventhusiast.model.db.RealmService;
import tw.invictus.tventhusiast.model.pojo.TvShow;
import tw.invictus.tventhusiast.view.DetailView;

/**
 * Created by ivan on 2/14/16.
 */
public interface DetailPresenter extends BasePresenter {

    void setDetailView(DetailView detailView);

    void setApi(RestfulApi api);

    void setRealmService(RealmService service);

    void loadVideos(int id);

    void loadTvShow(int id);

    void addTvShow(TvShow show);

    void deleteTvShow(TvShow show);

    boolean isTvShowAdded(int id);

}
