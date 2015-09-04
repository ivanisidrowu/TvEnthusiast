package tw.invictus.tvethusiast.model.rest;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;
import tw.invictus.tvethusiast.model.Series;

/**
 * Created by ivan on 6/21/15.
 */
public interface TvdbApi {

    @GET("/")
    Observable<Series> getSeries(@Query("s") String searchString, @Query("type") String type);

//    @GET("/api/GetSeries.php")
//    void searchSeries(@Query("seriesname") String seriesName, @Query("language") String language, Callback<Data> callback);
//
//    @GET("/api/{apiKey}/series/{seriesId}/en.xml")
//    void getSpecificSeries(@Path("apiKey") String apiKey, @Path("seriesId") String seriesId, Callback<Data> callback);


}
