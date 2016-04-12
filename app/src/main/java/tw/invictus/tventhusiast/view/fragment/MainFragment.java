package tw.invictus.tventhusiast.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import tw.invictus.tventhusiast.TvShowApplication;
import tw.invictus.tventhusiast.R;
import tw.invictus.tventhusiast.model.api.RestfulApi;
import tw.invictus.tventhusiast.model.db.RealmService;
import tw.invictus.tventhusiast.model.pojo.TvShow;
import tw.invictus.tventhusiast.presenter.MainFragmentPresenter;
import tw.invictus.tventhusiast.util.DialogUtil;
import tw.invictus.tventhusiast.util.NetworkUtil;
import tw.invictus.tventhusiast.view.MainFragmentView;
import tw.invictus.tventhusiast.view.activity.DetailActivity;
import tw.invictus.tventhusiast.view.activity.MainActivity;
import tw.invictus.tventhusiast.view.adapter.MainRecyclerViewAdapter;
import tw.invictus.tventhusiast.view.event.FavoriteTvShowChangeEvent;
import tw.invictus.tventhusiast.view.event.MenuItemClickEvent;
import tw.invictus.tventhusiast.view.event.TvShowClickEvent;
import tw.invictus.tventhusiast.view.event.NetworkChangeEvent;
import tw.invictus.tventhusiast.view.listener.InfiniteRecyclerViewScrollListener;

/**
 * Created by ivan on 1/10/16.
 */
public class MainFragment extends Fragment implements MainFragmentView {

    public static final String TAG = MainFragment.class.getSimpleName();
    public static final String SAVED_POSITION = "saved.position";
    public static final String SAVED_SORT_PARAM = "saved.sort.param";
    public static final String SAVED_SHOWS = "saved.shows";

    @Inject
    MainFragmentPresenter presenter;
    @Inject
    RestfulApi restfulApi;
    @Inject
    RealmService realmService;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindString(R.string.no_network_connection)
    String noNetworkMsg;

    private int lastScrollPosition = 0;

    public MainFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ((TvShowApplication) getActivity().getApplication()).getMainFragmentComponent(this).inject(this);
        ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        initRecyclerView();
        initPresenter();

        if (savedInstanceState == null){
            presenter.loadTvShows(MainFragmentPresenter.START_PAGE_INDEX);
        }

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int currentScrollPosition = ((GridLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        if(recyclerView.getAdapter() != null){
            ArrayList<TvShow> shows = ((MainRecyclerViewAdapter) recyclerView.getAdapter()).getShows();
            outState.putInt(SAVED_POSITION, currentScrollPosition);
            outState.putParcelableArrayList(SAVED_SHOWS, shows);
            outState.putInt(SAVED_SORT_PARAM, presenter.getListType());

        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null){
            ArrayList<TvShow> shows = savedInstanceState.getParcelableArrayList(SAVED_SHOWS);
            lastScrollPosition = savedInstanceState.getInt(SAVED_POSITION);
            recyclerView.setAdapter(new MainRecyclerViewAdapter(shows, this));
            recyclerView.scrollToPosition(lastScrollPosition);
            int param = savedInstanceState.getInt(SAVED_SORT_PARAM);
            presenter.setListType(presenter.getListType(param));
        }
    }

    private void initPresenter(){
        presenter.setMainView(this);
        presenter.setApi(restfulApi);
        presenter.setRealmService(realmService);
    }

    private void initRecyclerView(){
        int movieColumns = getResources().getInteger(R.integer.activity_movie_column);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), movieColumns);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new InfiniteRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                loadMoreShows(page);
            }
        });
    }

    private void loadMoreShows(int page){
        int currentSortParam = presenter.getListType();
        if(currentSortParam != MainFragmentPresenter.LIST_MY_FAVORITES){
            if (NetworkUtil.isOnline(this.getContext())) {
                presenter.loadTvShows(page);
            }else{
                DialogUtil.getAlertDialog(this.getContext(), "", noNetworkMsg).show();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        ButterKnife.unbind(this);
    }

    @SuppressWarnings("unused")
    public void onEvent(NetworkChangeEvent event){
        int status = event.getNetworkStatus();
        if(status == NetworkUtil.CONNECTED){
            presenter.loadTvShows(MainFragmentPresenter.START_PAGE_INDEX);
        }else{
            String message = getResources().getString(R.string.no_network_connection);
            DialogUtil.getAlertDialog(getActivity(), "", message).show();
        }
    }

    @SuppressWarnings("unused")
    public void onEvent(MenuItemClickEvent event){
        presenter.setListType(presenter.getListType(event.getSortType()));
        presenter.loadTvShows(MainFragmentPresenter.START_PAGE_INDEX);
    }

    @SuppressWarnings("unused")
    public void onEvent(FavoriteTvShowChangeEvent event){
        int type = presenter.getListType();
        if(type == MainFragmentPresenter.LIST_MY_FAVORITES){
            presenter.getTvShowsFromDb();
        }
    }

    @Override
    public void onTvShowClicked(TvShow show) {
        boolean isTwoPane = ((MainActivity) getActivity()).ismTwoPane();
        EventBus.getDefault().postSticky(new TvShowClickEvent(show, isTwoPane));
        if(isTwoPane){
            DetailFragment fragment = new DetailFragment();
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_container, fragment, DetailFragment.TAG)
                    .commit();
        }else{
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onMoreTvShowsLoaded(ArrayList<TvShow> shows) {
//        scrollToLastScrollPosition();
        lastScrollPosition = 0;
        int currentSize = recyclerView.getAdapter().getItemCount();
        List<TvShow> currentShows = ((MainRecyclerViewAdapter) recyclerView.getAdapter()).getShows();
        currentShows.addAll(shows);
        recyclerView.getAdapter().notifyItemRangeInserted(currentSize, currentShows.size() - 1);
    }

    @Override
    public void onTvShowsLoaded(ArrayList<TvShow> shows) {
        Log.i(TAG, "onTvShowsLoaded:");
        if (recyclerView != null){
            recyclerView.setAdapter(new MainRecyclerViewAdapter(shows, this));
        }
//        scrollToLastScrollPosition();
    }

    @Override
    public void onLoadError(Throwable throwable) {
        Log.e(TAG, "onLoadError: ", throwable);
    }

    private void scrollToLastScrollPosition(){
        if(lastScrollPosition != 0){
            recyclerView.scrollToPosition(lastScrollPosition);
        }
    }
}
