/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tw.invictus.tventhusiast.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import tw.invictus.tventhusiast.BuildConfig;
import tw.invictus.tventhusiast.R;
import tw.invictus.tventhusiast.TvShowApplication;
import tw.invictus.tventhusiast.model.api.RestfulApi;
import tw.invictus.tventhusiast.model.db.RealmService;
import tw.invictus.tventhusiast.model.pojo.Season;
import tw.invictus.tventhusiast.model.pojo.TvShow;
import tw.invictus.tventhusiast.presenter.EpisodeListPresenter;
import tw.invictus.tventhusiast.view.EpisodeListView;
import tw.invictus.tventhusiast.view.adapter.EpisodeRecyclerViewAdapter;
import tw.invictus.tventhusiast.view.event.GetPaletteEvent;
import tw.invictus.tventhusiast.view.event.SeasonItemClickEvent;

public class EpisodeListActivity extends AppCompatActivity implements EpisodeListView {

    private static final String TAG = "EpisodeListActivity";

    @Inject
    EpisodeListPresenter presenter;

    @Inject
    RestfulApi api;

    @Inject
    RealmService realmService;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.backdrop)
    ImageView backdrop;

    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;

    @Bind(R.id.main_content)
    CoordinatorLayout mainBackground;

    @BindString(R.string.backdrop_size_url)
    String backdropSize;

    private int backgroundColor = Color.DKGRAY;
    private int lightColor = Color.GRAY;
    private Season season;
    private boolean isShowInDb = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_list);
        ((TvShowApplication) getApplication()).getEpisodeListComponent(this).inject(this);
        presenter.setView(this);
        presenter.setApi(api);
        presenter.setService(realmService);

        ButterKnife.bind(this);
        EventBus.getDefault().registerSticky(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @SuppressWarnings("unused")
    public void onEvent(SeasonItemClickEvent event){
        TvShow show = event.getShow();
        isShowInDb = realmService.isTvShowAdded(show.getId());
        season = event.getSeason();
        String backdropImgUrl = BuildConfig.IMG_BASE_URL + backdropSize + show.getBackdropPath();
        String title = getString(R.string.season, season.getSeasonNumber());
        toolbar.setTitle(title);
        Glide.with(this).
                load(backdropImgUrl)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(backdrop);
        presenter.loadSeason(show, season);
    }

    @SuppressWarnings("unused")
    public void onEvent(GetPaletteEvent event){
        this.backgroundColor = event.getBackgroundColor();
        this.lightColor = event.getLightColor();

        mainBackground.setBackgroundColor(backgroundColor);
    }

    @Override
    public void onSeasonLoaded(Season season) {
        this.season = season;
        recyclerView.setAdapter(new EpisodeRecyclerViewAdapter(this, season, lightColor, isShowInDb));
    }

    @Override
    public void onEpisodeCheckChanged(int position, boolean checked) {
        try {
            presenter.updateWatchedEpisode(season.getEpisodes().get(position).getId(), checked);
        } catch (Exception e) {
            Log.e(TAG, "onEpisodeCheckChanged: ", e);
        }
    }
}
