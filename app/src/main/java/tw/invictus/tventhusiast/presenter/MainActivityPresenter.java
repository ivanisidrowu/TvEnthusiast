package tw.invictus.tventhusiast.presenter;

import tw.invictus.tventhusiast.model.api.RestfulApi;
import tw.invictus.tventhusiast.view.MainActivityView;

/**
 * Created by ivan on 2/27/16.
 */
public interface MainActivityPresenter extends BasePresenter {
    MainActivityPresenter setApi(RestfulApi api);

    MainActivityPresenter setView(MainActivityView view);

    void searchTvShow(String query);
}
