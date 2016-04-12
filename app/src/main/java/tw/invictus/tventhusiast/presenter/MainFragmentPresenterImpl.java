package tw.invictus.tventhusiast.presenter;

import android.content.Context;
import android.support.annotation.IntDef;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import tw.invictus.tventhusiast.model.api.RestfulApi;
import tw.invictus.tventhusiast.model.db.RealmService;
import tw.invictus.tventhusiast.model.pojo.TvShow;
import tw.invictus.tventhusiast.model.pojo.TvShowsResponse;
import tw.invictus.tventhusiast.util.PropUtil;
import tw.invictus.tventhusiast.view.MainFragmentView;

/**
 * Created by ivan on 12/26/15.
 */
public class MainFragmentPresenterImpl implements MainFragmentPresenter {
    private static final String TAG = MainFragmentPresenterImpl.class.getSimpleName();

    @IntDef({SORT_BY_POPULARITY, SORT_BY_TOPRATED, LIST_MY_FAVORITES})
    public @interface ListType{

    }

    private int listType;
    private Context context;
    private MainFragmentView mainView;
    private Subscription subscription;
    private RestfulApi api;
    private RealmService realmService;

    @Inject
    public MainFragmentPresenterImpl(Context context) {
        this.context = context;
        this.listType = SORT_BY_POPULARITY;
    }

    @Override
    public void setApi(RestfulApi api) {
        this.api = api;
    }

    @Override
    public void setMainView(MainFragmentView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void setRealmService(RealmService realmService) {
        this.realmService = realmService;
    }

    @Override
    public void loadTvShows(int page){
        switch (listType){
            case SORT_BY_POPULARITY:
                getPopularTvShows(page);
                break;
            case SORT_BY_TOPRATED:
                getTopRatedTvShows(page);
                break;
            case LIST_MY_FAVORITES:
                getTvShowsFromDb();
                break;
        }

    }

    @Override
    public void getPopularTvShows(int page){
        subscription = api.getPopularShows(PropUtil.LOCALE, page + 1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> processTvShowResponse(response, page), throwable -> mainView.onLoadError(throwable));
    }

    @Override
    public void getTopRatedTvShows(int page){
        subscription = api.getTopRatedShows(PropUtil.LOCALE, page + 1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> processTvShowResponse(response, page), throwable -> mainView.onLoadError(throwable));
    }

    @Override
    public void getTvShowsFromDb(){
        subscription = realmService
                .getAllTvShowsAsync()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(shows -> {
                    mainView.onTvShowsLoaded(shows);
                });
    }

    private void processTvShowResponse(TvShowsResponse response, int page){
        ArrayList<TvShow> shows = response.getResult();
        if (page == START_PAGE_INDEX){
            mainView.onTvShowsLoaded(shows);
        }else{
            mainView.onMoreTvShowsLoaded(shows);
        }

    }

    @Override
    @ListType
    public int getListType() {
        return listType;
    }

    @Override
    @ListType
    public int getListType(int type){
        switch (type){
            case SORT_BY_POPULARITY:
                return SORT_BY_POPULARITY;
            case SORT_BY_TOPRATED:
                return SORT_BY_TOPRATED;
            case LIST_MY_FAVORITES:
                return LIST_MY_FAVORITES;
            default:
                return SORT_BY_POPULARITY;
        }
    }

    @Override
    public void setListType(@ListType int listType) {
        this.listType = listType;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        if(subscription != null){
            subscription.unsubscribe();
        }
    }

    @Override
    public void onStop() {

    }

}
