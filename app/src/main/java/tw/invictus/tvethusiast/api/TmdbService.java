package tw.invictus.tvethusiast.api;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import tw.invictus.tvethusiast.model.ConfigurationResponse;
import tw.invictus.tvethusiast.model.DiscoverTvShowResponse;

/**
 * Created by ivan on 9/21/15.
 */
public interface TmdbService {
    @GET("/3/configuration")
    Call<ConfigurationResponse> getConfiguration(@Query("api_key") String apiKey);

    @GET("/3/discover/tv")
    Call<DiscoverTvShowResponse> discoverTvShows(@Query("api_key") String apiKey, @Query("sort_by") String sortParam);
}
