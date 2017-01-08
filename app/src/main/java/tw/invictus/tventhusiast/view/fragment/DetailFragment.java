package tw.invictus.tventhusiast.view.fragment;


import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.florent37.glidepalette.BitmapPalette;
import com.github.florent37.glidepalette.GlidePalette;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import tw.invictus.tventhusiast.BuildConfig;
import tw.invictus.tventhusiast.R;
import tw.invictus.tventhusiast.TvShowApplication;
import tw.invictus.tventhusiast.model.api.RestfulApi;
import tw.invictus.tventhusiast.model.db.RealmService;
import tw.invictus.tventhusiast.model.pojo.TvShow;
import tw.invictus.tventhusiast.model.pojo.Video;
import tw.invictus.tventhusiast.presenter.DetailPresenter;
import tw.invictus.tventhusiast.util.ColorUtil;
import tw.invictus.tventhusiast.util.IntentUtil;
import tw.invictus.tventhusiast.util.NetworkUtil;
import tw.invictus.tventhusiast.view.DetailView;
import tw.invictus.tventhusiast.view.activity.DetailActivity;
import tw.invictus.tventhusiast.view.event.FavoriteTvShowChangeEvent;
import tw.invictus.tventhusiast.view.event.GetPaletteEvent;
import tw.invictus.tventhusiast.view.event.RefreshMyTvShowEvent;
import tw.invictus.tventhusiast.view.event.TvShowClickEvent;
import tw.invictus.tventhusiast.view.event.TvShowShareEvent;

/**
 * Created by ivan on 1/10/16.
 */
public class DetailFragment extends Fragment implements DetailView, BitmapPalette.CallBack {

    public static final String TAG = DetailFragment.class.getSimpleName();

    @Inject
    DetailPresenter presenter;
    @Inject
    RestfulApi restfulApi;
    @Inject
    RealmService realmService;

    @Bind(R.id.main_content)
    CoordinatorLayout mainBackground;
    @Bind(R.id.backdrop)
    ImageView backdrop;
    @Bind(R.id.fab)
    FloatingActionButton actionButton;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.mask)
    FrameLayout mask;
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    @BindString(R.string.image_size_url)
    String imageSize;
    @BindString(R.string.backdrop_size_url)
    String backdropSize;
    @BindString(R.string.share)
    String shareMessage;
    @BindString(R.string.overview)
    String overviewText;
    @BindString(R.string.seasons)
    String seasonsText;

    private TvShow show;
    private Video video;
    private boolean isShowAdded = false;
    private boolean isTwoPane = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);
        actionButton.setEnabled(false);
        ((TvShowApplication) getActivity().getApplication()).getDetailFragmentComponent(this).inject(this);

        final Animation mAnimation = AnimationUtils.loadAnimation(this.getContext(), R.anim.fab);
        actionButton.setVisibility(View.VISIBLE); //It has to be invisible before here
        actionButton.startAnimation(mAnimation);

        initPresenter();
        EventBus.getDefault().registerSticky(this);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null){
            show = savedInstanceState.getParcelable(TAG);
            initUI();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if(!isTwoPane){
            inflater.inflate(R.menu.menu_detail, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected");
        int id = item.getItemId();
        switch (id){
            case R.id.share:
                if(video != null)
                    IntentUtil.shareYoutubeVideo(video.getKey(), getContext(), shareMessage);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(show != null){
            outState.putParcelable(TAG, show);
        }
    }

    private void initPresenter() {
        presenter.setDetailView(this);
        presenter.setRealmService(realmService);
        presenter.setApi(restfulApi);
    }

    private void initUI(){
        if (show != null) {
            mainBackground.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            String backdropImgUrl = BuildConfig.IMG_BASE_URL + backdropSize + show.getBackdropPath();

            initToolbar();
            initFab();
            initViewPager(viewpager, show);
            loadVideosAndReviews(show);

            Glide.with(this)
                    .load(backdropImgUrl)
                    .asBitmap()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(GlidePalette.with(backdropImgUrl).intoCallBack(this))
                    .into(backdrop);
        } else {
            Log.e(TAG, "initUI: movie is null");
        }
    }

    private void initToolbar(){
        if(!isTwoPane){
            ((DetailActivity) getActivity()).setSupportActionBar(toolbar);
            ((DetailActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbarLayout.setTitle(show.getTitle());
        }
        toolbarLayout.setTitle(show.getTitle());
    }

    private void initFab(){
        isShowAdded = presenter.isTvShowAdded(show.getId());
        if(isShowAdded){
            Glide.with(this).load(R.drawable.ic_favorite_pressed).into(actionButton);
        }
    }

    private void loadVideosAndReviews(TvShow show){
        boolean isOnline = NetworkUtil.isOnline(this.getContext());
        if(isOnline){
            presenter.loadVideos(show.getId());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @SuppressWarnings("unused")
    public void onEvent(TvShowClickEvent event){
        this.isTwoPane = event.isTwoPane();
        presenter.loadTvShow(event.getShow().getId());
    }

    @SuppressWarnings("unused")
    public void onEvent(TvShowShareEvent event){
        if (video != null)
            IntentUtil.shareYoutubeVideo(video.getKey(), getContext(), shareMessage);
    }

    @Override
    public void onVideosLoaded(List<Video> videos) {
        video = (videos.size() > 0) ? videos.get(0) : null;
    }

    @Override
    public void onTvShowAdded() {
        Log.d(TAG, "onTvShowAdded");
        isShowAdded = true;
        Glide.with(this).load(R.drawable.ic_favorite_pressed).into(actionButton);
        YoYo.with(Techniques.RubberBand).duration(500).playOn(actionButton);
        setActionButton(true);
        Snackbar.make(this.getView(), R.string.tv_show_added, Snackbar.LENGTH_SHORT).show();
        EventBus.getDefault().post(new FavoriteTvShowChangeEvent());
    }

    @Override
    public void onTvShowDeleted() {
        Log.d(TAG, "onTvShowDeleted");
        isShowAdded = false;
        Glide.with(this).load(R.drawable.ic_favorite).into(actionButton);
        YoYo.with(Techniques.RubberBand).duration(500).playOn(actionButton);
        setActionButton(true);
        Snackbar.make(this.getView(), R.string.tv_show_deleted, Snackbar.LENGTH_SHORT).show();
        EventBus.getDefault().post(new FavoriteTvShowChangeEvent());
    }

    @Override
    public void onTvShowLoaded(TvShow show) {
        this.show = show;
        initUI();
        mask.setVisibility(View.GONE);
        setActionButton(true);
    }

    @OnClick(R.id.fab)
    public void onFabClicked(View view){
        Log.d(TAG, "onFabClicked");
        setActionButton(false);
        if(isShowAdded){
            presenter.deleteTvShow(show);
        }else{
            presenter.addTvShow(show);
        }
    }

    @OnClick(R.id.toolbar_play)
    public void onToolbarPlayClicked(View view){
        if(video != null){
            IntentUtil.viewYoutubeVideo(video.getKey(), this.getContext());
        }
    }
    
    private void setActionButton(boolean clickable){
        if (clickable){
            actionButton.setAlpha((float) 1);
        }else{
            actionButton.setAlpha((float) 0.5);
        }
        actionButton.setEnabled(clickable);
        actionButton.setClickable(clickable);
    }

    private void initViewPager(ViewPager viewPager, TvShow show) {
        Adapter adapter = new Adapter(getChildFragmentManager());
        OverviewFragment overviewFragment = new OverviewFragment();
        SeasonListFragment seasonListFragment = new SeasonListFragment();
        EventBus.getDefault().postSticky(new RefreshMyTvShowEvent(show));
        adapter.addFragment(overviewFragment, overviewText);
        adapter.addFragment(seasonListFragment, seasonsText);
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void onPaletteLoaded(@Nullable Palette palette) {

        if (palette != null) {
            int darkColor = ColorUtil.getDarkColorFromPalette(palette);
            int lightColor = ColorUtil.getLightColorFromPalette(palette);
            int backgroundColor = ColorUtil.getBackgroundColorFromPalette(palette);

            EventBus.getDefault().postSticky(new GetPaletteEvent(darkColor, lightColor, backgroundColor));

            tabs.setBackgroundColor(darkColor);
            actionButton.setBackgroundTintList(ColorStateList.valueOf(lightColor));
            mainBackground.setBackgroundColor(backgroundColor);
        }

    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
