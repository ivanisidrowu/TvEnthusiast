package tw.invictus.tventhusiast.presenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import tw.invictus.tventhusiast.model.api.RestfulApi;
import tw.invictus.tventhusiast.model.db.RealmService;
import tw.invictus.tventhusiast.model.pojo.TvShow;
import tw.invictus.tventhusiast.util.PropUtil;
import tw.invictus.tventhusiast.view.SeasonListView;

/**
 * Created by ivan on 2/17/16.
 */
public class SeasonListPresenterImpl implements SeasonListPresenter {

    private RestfulApi api;
    private SeasonListView view;
    private RealmService service;
    private Subscription subscription;

    @Inject
    public SeasonListPresenterImpl() {
    }

    @Override
    public void setApi(RestfulApi api){
        this.api = api;
    }

    @Override
    public void setView(SeasonListView view){
        this.view = view;
    }

    @Override
    public void setDbService(RealmService service){
        this.service = service;
    }

    @Override
    public void getSeasons(TvShow show){
        if (show != null && service != null) {
            boolean isShowInDb = service.isTvShowAdded(show.getId());

            if (isShowInDb){
                view.onSeasonListLoaded(show, true);
            }else{
                subscription = api.getTvShow(show.getId(), PropUtil.LOCALE)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(show1 -> view.onSeasonListLoaded(show1, false));
            }
        }
    }

    @Override
    public void updatedWatchedSeason(long seasonId, boolean isWatched){
        subscription = service.updateWatchedSeason(seasonId, isWatched)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(realmSeason -> {}, throwable -> {});
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        subscription.unsubscribe();
    }

    @Override
    public void onStop() {

    }
}
