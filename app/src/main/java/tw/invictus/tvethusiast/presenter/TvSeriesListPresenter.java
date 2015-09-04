package tw.invictus.tvethusiast.presenter;

import android.content.Context;
import android.content.Intent;

import java.util.List;

import javax.inject.Inject;

import tw.invictus.tvethusiast.model.Series;
import tw.invictus.tvethusiast.view.TvSeriesView;
import tw.invictus.tvethusiast.view.View;
import tw.invictus.tvethusiast.view.listener.RecyclerClickListener;

/**
 * Created by ivan on 7/11/15.
 */
public class TvSeriesListPresenter implements Presenter, RecyclerClickListener {

    private final List<Series> mTvSeriesList;
    private final Context mContext;
    private TvSeriesView mTvSeriesView;
    private Intent mIntent;

    @Inject public TvSeriesListPresenter(List<Series> seriesList, Context context){
        mTvSeriesList = seriesList;
        mContext = context;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void attachView(View v) {
        mTvSeriesView = (TvSeriesView) v;
        mTvSeriesView.showTvSeriesList(mTvSeriesList);
    }

    @Override
    public void attachIncomingIntent(Intent intent) {
        mIntent = intent;
    }

    @Override
    public void onElementClick(int position) {
        // TODO
    }
}
