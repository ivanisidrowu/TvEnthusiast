package tw.invictus.tvethusiast.view;

import java.util.List;

import tw.invictus.tvethusiast.model.Series;

/**
 * Created by ivan on 7/10/15.
 */
public interface TvSeriesView extends View{

    void showTvSeriesList(List<Series> seriesList);
}
