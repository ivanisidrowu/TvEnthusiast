package tw.invictus.tventhusiast.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import tw.invictus.tventhusiast.R;
import tw.invictus.tventhusiast.TvShowApplication;
import tw.invictus.tventhusiast.model.api.RestfulApi;
import tw.invictus.tventhusiast.model.db.RealmService;
import tw.invictus.tventhusiast.model.pojo.TvShow;
import tw.invictus.tventhusiast.presenter.SeasonListPresenter;
import tw.invictus.tventhusiast.view.SeasonListView;
import tw.invictus.tventhusiast.view.activity.EpisodeListActivity;
import tw.invictus.tventhusiast.view.adapter.SeasonRecyclerViewAdapter;
import tw.invictus.tventhusiast.view.event.FavoriteTvShowChangeEvent;
import tw.invictus.tventhusiast.view.event.GetPaletteEvent;
import tw.invictus.tventhusiast.view.event.RefreshMyTvShowEvent;
import tw.invictus.tventhusiast.view.event.SeasonItemClickEvent;

/**
 * Created by ivan on 10/23/15.
 */
public class SeasonListFragment extends Fragment implements SeasonListView {

    @Inject
    SeasonListPresenter presenter;
    @Inject
    RestfulApi api;
    @Inject
    RealmService realmService;

    private TvShow show;
    private RecyclerView recyclerView;
    private int lightColor = Color.GRAY;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.fragment_season_list, container, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ((TvShowApplication) getActivity().getApplication()).getSeasonListComponent(this).inject(this);
        EventBus.getDefault().registerSticky(this);

        presenter.setApi(api);
        presenter.setView(this);
        presenter.setDbService(realmService);
        presenter.getSeasons(show);

        return recyclerView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
    }

    @SuppressWarnings("unused")
    public void onEvent(RefreshMyTvShowEvent event) {
        this.show = event.getShow();
    }
    @SuppressWarnings("unused")
    public void onEvent(FavoriteTvShowChangeEvent event){
        presenter.getSeasons(show);
    }

    @SuppressWarnings("unused")
    public void onEvent(GetPaletteEvent event) {
        this.lightColor = event.getLightColor();
        presenter.getSeasons(show);
    }

    @Override
    public void onSeasonListLoaded(TvShow show, boolean isShowInDb) {
        this.show = show;
        recyclerView.setAdapter(new SeasonRecyclerViewAdapter(show.getSeasons(), this, lightColor, isShowInDb));
    }

    @Override
    public void onSeasonClicked(int position) {
        EventBus.getDefault().postSticky(new SeasonItemClickEvent(show, show.getSeasons().get(position)));
        Intent intent = new Intent();
        intent.setClass(getActivity(), EpisodeListActivity.class);
        getActivity().startActivity(intent);
    }

    @Override
    public void onSeasonCheckChanged(int position, boolean isChecked) {
        long seasonId = show.getSeasons().get(position).getId();
        presenter.updatedWatchedSeason(seasonId, isChecked);
    }
}
