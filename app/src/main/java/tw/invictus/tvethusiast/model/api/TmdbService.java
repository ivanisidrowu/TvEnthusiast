package tw.invictus.tvethusiast.model.api;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import tw.invictus.tvethusiast.model.Configuration;
import tw.invictus.tvethusiast.model.DiscoverTvShowResponse;
import tw.invictus.tvethusiast.model.GetTvEpisodeResponse;
import tw.invictus.tvethusiast.model.GetTvSeasonResponse;
import tw.invictus.tvethusiast.model.GetTvShowResponse;

/**
 * Created by ivan on 9/21/15.
 */
public interface TmdbService {
    @GET("/3/configuration")
    Call<Configuration> getConfiguration(@Query("api_key") String apiKey);

    @GET("/3/discover/tv")
    Call<DiscoverTvShowResponse> discoverTvShows(@Query("api_key") String apiKey, @Query("sort_by") String sortParam);

    @GET("/3/tv/{id}")
    Call<GetTvShowResponse> getTvShow(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("/3/tv/{show_id}/season/{season_id}")
    Call<GetTvSeasonResponse> getTvSeason(@Path("show_id") int showId, @Path("season_id") int seasonId, @Query("api_key") String apiKey);

    @GET("/3/tv/{show_id}/season/{season_id}/episode/{episode_id}")
    Call<GetTvEpisodeResponse> getTvEpisode(@Path("show_id") int showId, @Path("season_id") int seasonId, @Path("episode_id") int episodeId, @Query("api_key") String apiKey);

}
