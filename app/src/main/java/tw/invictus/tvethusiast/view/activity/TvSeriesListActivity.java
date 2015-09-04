package tw.invictus.tvethusiast.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import tw.invictus.tvethusiast.R;
import tw.invictus.tvethusiast.TvEthusiastApplication;
import tw.invictus.tvethusiast.model.Series;
import tw.invictus.tvethusiast.presenter.TvSeriesListPresenter;
import tw.invictus.tvethusiast.view.TvSeriesView;

/**
 * Created by ivan on 7/10/15.
 */
public class TvSeriesListActivity extends AppCompatActivity implements TvSeriesView{

    @InjectView(R.id.activity_tvseries_recycler)
    RecyclerView mRecyclerView;
    @InjectView(R.id.activity_tvseries_toolbar)
    Toolbar mToolbar;
    @Inject
    TvSeriesListPresenter mTvSeriesListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvseries_list);
        ButterKnife.inject(this);

        initToolbar();
        initRecyclerrView();
        initDependencyInjector();
        initPresenter();
    }

    @Override
    public void showTvSeriesList(List<Series> seriesList) {

    }

    private void initToolbar(){
        this.setSupportActionBar(mToolbar);
    }

    private void initRecyclerrView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initDependencyInjector(){
        TvEthusiastApplication tvEthusiastApplication = (TvEthusiastApplication) getApplication();

    }

    private void initPresenter(){
        mTvSeriesListPresenter.attachView(this);
    }
}
