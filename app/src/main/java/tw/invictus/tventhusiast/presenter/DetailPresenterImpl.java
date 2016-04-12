package tw.invictus.tventhusiast.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import tw.invictus.tventhusiast.model.api.RestfulApi;
import tw.invictus.tventhusiast.model.db.RealmService;
import tw.invictus.tventhusiast.model.pojo.TvShow;
import tw.invictus.tventhusiast.util.PropUtil;
import tw.invictus.tventhusiast.view.DetailView;

/**
 * Created by ivan on 12/27/15.
 */
public class DetailPresenterImpl implements DetailPresenter {

    private static final String TAG = DetailPresenterImpl.class.getSimpleName();

    private DetailView detailView;
    private RestfulApi api;
    private RealmService realmService;
    private List<Subscription> subscriptions = new ArrayList<>();

    @Inject
    public DetailPresenterImpl() {
    }

    @Override
    public void setDetailView(DetailView detailView) {
        this.detailView = detailView;
    }

    @Override
    public void setApi(RestfulApi api) {
        this.api = api;
        realmService.setApi(api);
    }

    @Override
    public void setRealmService(RealmService service) {
        this.realmService = service;
    }

    @Override
    public void loadVideos(int id) {
        Subscription subscription = api.getVideos(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(videoResponse -> detailView.onVideosLoaded(videoResponse.getResults()));
        subscriptions.add(subscription);
    }

    @Override
    public void loadTvShow(int id) {
        boolean isTvShowAdded = realmService.isTvShowAdded(id);
        if (isTvShowAdded){
            Subscription subscription = realmService.getTvShow(id)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(tvShow -> detailView.onTvShowLoaded(tvShow));
            subscriptions.add(subscription);
        }else{
            Subscription subscription = api.getTvShow(id, PropUtil.LOCALE)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(show -> detailView.onTvShowLoaded(show));
            subscriptions.add(subscription);
        }
    }

    @Override
    public void addTvShow(TvShow show) {
        Subscription subscription = realmService
                .createOrUpdateTvShow(show)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(realmTvShow -> {
                    detailView.onTvShowAdded();
                });
        subscriptions.add(subscription);
    }

    @Override
    public void deleteTvShow(TvShow show) {
        Subscription subscription = realmService
                .deleteTvShow(show)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvShow -> {
                    detailView.onTvShowDeleted();
                });
        subscriptions.add(subscription);
    }

    @Override
    public boolean isTvShowAdded(int id) {
        return realmService.isTvShowAdded(id);
    }

    private void cleanSubscriptions() {
        for (int i = 0; i < subscriptions.size(); i++) {
            Subscription subscription = subscriptions.get(i);
            if (subscription != null) {
                subscription.unsubscribe();
            }
        }
        subscriptions.clear();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        cleanSubscriptions();
    }

    @Override
    public void onStop() {

    }
}
