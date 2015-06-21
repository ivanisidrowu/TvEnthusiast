package tw.invictus.tvethusiast.service;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import tw.invictus.tvethusiast.model.Data;

/**
 * Created by ivan on 6/21/15.
 */
public interface TvdbApi {
    @GET("/api/GetSeries.php")
    void getSeries(@Query("seriesname") String seriesName, @Query("language") String language, Callback<Data> callback);
}
