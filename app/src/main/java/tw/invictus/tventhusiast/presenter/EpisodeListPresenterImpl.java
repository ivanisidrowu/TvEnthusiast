package tw.invictus.tventhusiast.presenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import tw.invictus.tventhusiast.model.api.RestfulApi;
import tw.invictus.tventhusiast.model.db.RealmService;
import tw.invictus.tventhusiast.model.pojo.Season;
import tw.invictus.tventhusiast.model.pojo.TvShow;
import tw.invictus.tventhusiast.view.EpisodeListView;

/**
 * Created by ivan on 2/18/16.
 */
public class EpisodeListPresenterImpl implements EpisodeListPresenter {

    private EpisodeListView view;
    private RestfulApi api;
    private RealmService service;
    private Subscription subscription;

    @Inject
    public EpisodeListPresenterImpl() {

    }

    @Override
    public void setView(EpisodeListView view) {
        this.view = view;
    }

    @Override
    public void setApi(RestfulApi api) {
        this.api = api;
    }

    @Override
    public void setService(RealmService service){
        this.service = service;
    }

    @Override
    public void loadSeason(TvShow show, Season season){
        boolean isShowInDb = service.isTvShowAdded(show.getId());
        if(isShowInDb){
            subscription = service.getSeason(season.getId())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(season1 -> view.onSeasonLoaded(season1));
        }else{
            subscription = api.getSeasonAsObservable(show.getId(), season.getSeasonNumber())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(season1 -> view.onSeasonLoaded(season1));
        }
    }

    @Override
    public void updateWatchedEpisode(long episodeId, boolean isWatched){
        service.updateWatchedEpisode(episodeId, isWatched)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable1 -> {})
                .subscribe(realmEpisode -> {}, throwable -> {});
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
