package tw.invictus.tvethusiast.service;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import tw.invictus.tvethusiast.model.Data;

/**
 * Created by ivan on 6/21/15.
 */
public interface TvdbApi {
    @GET("/api/GetSeries.php")
    void searchSeries(@Query("seriesname") String seriesName, @Query("language") String language, Callback<Data> callback);

    @GET("/api/{apiKey}/series/{seriesId}/en.xml")
    void getSpecificSeries(@Path("apiKey") String apiKey, @Path("seriesId") String seriesId, Callback<Data> callback);
}
