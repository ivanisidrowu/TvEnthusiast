package tw.invictus.tventhusiast.presenter;

import tw.invictus.tventhusiast.model.api.RestfulApi;
import tw.invictus.tventhusiast.model.db.RealmService;
import tw.invictus.tventhusiast.view.MainFragmentView;

/**
 * Created by ivan on 2/14/16.
 */
public interface MainFragmentPresenter extends BasePresenter {
    int START_PAGE_INDEX = 0;
    int SORT_BY_POPULARITY = 0;
    int SORT_BY_TOPRATED = 1;
    int LIST_MY_FAVORITES = 2;

    void setApi(RestfulApi api);

    void setMainView(MainFragmentView mainView);

    void setRealmService(RealmService realmService);

    void loadTvShows(int page);

    void getPopularTvShows(int page);

    void getTopRatedTvShows(int page);

    void getTvShowsFromDb();

    @MainFragmentPresenterImpl.ListType
    int getListType();

    @MainFragmentPresenterImpl.ListType
    int getListType(int type);

    void setListType(@MainFragmentPresenterImpl.ListType int listType);
}
