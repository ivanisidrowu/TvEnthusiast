package tw.invictus.tventhusiast.model.api;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;
import tw.invictus.tventhusiast.model.pojo.Episode;
import tw.invictus.tventhusiast.model.pojo.Season;
import tw.invictus.tventhusiast.model.pojo.TvShow;
import tw.invictus.tventhusiast.model.pojo.TvShowsResponse;
import tw.invictus.tventhusiast.model.pojo.VideoResponse;

/**
 * Created by ivan on 12/26/15.
 */
public interface RestfulApi {

    @GET("tv/{id}")
    Observable<TvShow> getTvShow(@Path("id") int id, @Query("language") String language);

    @GET("tv/{show_id}/season/{season_number}")
    Observable<Season> getSeasonAsObservable(@Path("show_id") int showId, @Path("season_number") int seasonNumber);

    @GET("tv/{show_id}/season/{season_number}")
    Call<Season> getSeason(@Path("show_id") int showId, @Path("season_number") int seasonNumber);

    @GET("tv/{show_id}/season/{season_number}/episode/{episode_number}")
    Observable<Episode> getEpisode(@Path("show_id") int showId, @Path("season_number") int seasonNumber, @Path("episode_number") int episodeNumber);

    @GET("tv/{show_id}/videos")
    Observable<VideoResponse> getVideos(@Path("show_id") int showId);

    @GET("tv/popular")
    Observable<TvShowsResponse> getPopularShows(@Query("language") String language, @Query("page") int page);

    @GET("tv/top_rated")
    Observable<TvShowsResponse> getTopRatedShows(@Query("language") String language, @Query("page") int page);

    @GET("search/tv")
    Observable<TvShowsResponse> searchTvShows(@Query("query") String query);

}
