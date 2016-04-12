package tw.invictus.tventhusiast.presenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import tw.invictus.tventhusiast.model.api.RestfulApi;
import tw.invictus.tventhusiast.view.MainActivityView;

/**
 * Created by ivan on 2/27/16.
 */
public class MainActivityPresenterImpl implements MainActivityPresenter {

    private RestfulApi api;
    private MainActivityView view;
    private Subscription subscription;

    @Inject
    public MainActivityPresenterImpl() {

    }

    @Override
    public MainActivityPresenter setApi(RestfulApi api) {
        this.api = api;
        return this;
    }

    @Override
    public MainActivityPresenter setView(MainActivityView view) {
        this.view = view;
        return this;
    }

    @Override
    public void searchTvShow(String query) {
        subscription = api.searchTvShows(query)
                .debounce(200, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvShowsResponse -> view.onTvShowSearched(tvShowsResponse.getResult()));
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        if (subscription != null)
            subscription.unsubscribe();
    }

    @Override
    public void onStop() {

    }
}
