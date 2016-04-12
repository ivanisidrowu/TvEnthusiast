package tw.invictus.tventhusiast.model.db;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import tw.invictus.tventhusiast.model.api.RestfulApi;
import tw.invictus.tventhusiast.model.pojo.Episode;
import tw.invictus.tventhusiast.model.pojo.Season;
import tw.invictus.tventhusiast.model.pojo.TvShow;

/**
 * Created by ivan on 1/9/16.
 */
public interface RealmService {

    void setApi(RestfulApi api);

    Observable<RealmEpisode> updateWatchedEpisode(long episodeId, boolean isWatched);

    Observable<RealmSeason> updateWatchedSeason(long seasonId, boolean isWatched);

    Observable<RealmTvShow> createOrUpdateTvShow(TvShow tvShow);

    List<Episode> getAiringEpisode(String date);

    Observable<TvShow> deleteTvShow(TvShow show);

    boolean isTvShowAdded(int id);

    Observable<TvShow> getTvShow(int id);

    Observable<Season> getSeason(long id);

    Observable<ArrayList<TvShow>> getAllTvShowsAsync();

    List<TvShow> getAllTvShows();
}
