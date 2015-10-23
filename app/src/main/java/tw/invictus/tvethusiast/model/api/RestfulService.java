package tw.invictus.tvethusiast.model.api;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import tw.invictus.tvethusiast.model.Episode;
import tw.invictus.tvethusiast.model.Season;
import tw.invictus.tvethusiast.model.TvShow;
import tw.invictus.tvethusiast.model.pojo.SeriesResponse;

/**
 * Created by ivan on 9/21/15.
 */
public interface RestfulService {

    @GET("/3/tv/{id}")
    Call<TvShow> getTvShow(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("/3/tv/{show_id}/season/{season_id}")
    Call<Season> getSeason(@Path("show_id") int showId, @Path("season_id") int seasonId, @Query("api_key") String apiKey);

    @GET("/3/tv/{show_id}/season/{season_id}/episode/{episode_id}")
    Call<Episode> getEpisode(@Path("show_id") int showId, @Path("season_id") int seasonId, @Path("episode_id") int episodeId, @Query("api_key") String apiKey);

    @GET("/3/tv/popular")
    Call<SeriesResponse> getPopularShows(@Query("api_key") String apiKey);

    @GET("/3/search/tv")
    Call<SeriesResponse> searchTvShows(@Query("api_key") String apiKey, @Query("query") String query);
}
