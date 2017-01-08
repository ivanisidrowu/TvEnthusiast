package tw.invictus.tventhusiast.model.db;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import rx.Observable;
import tw.invictus.tventhusiast.model.api.RestfulApi;
import tw.invictus.tventhusiast.model.builder.EpisodeBuilder;
import tw.invictus.tventhusiast.model.builder.SeasonBuilder;
import tw.invictus.tventhusiast.model.builder.TvShowBuilder;
import tw.invictus.tventhusiast.model.pojo.Episode;
import tw.invictus.tventhusiast.model.pojo.Genre;
import tw.invictus.tventhusiast.model.pojo.Season;
import tw.invictus.tventhusiast.model.pojo.TvShow;

/**
 * Created by ivan on 1/9/16.
 */
public class RealmServiceImpl implements RealmService {

    private static final String TAG = RealmServiceImpl.class.getSimpleName();

    private final Context context;
    private RestfulApi api;

    @Inject
    public RealmServiceImpl(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public void setApi(RestfulApi api) {
        this.api = api;
    }

    @Override
    public Observable<RealmEpisode> updateWatchedEpisode(long episodeId, boolean isWatched) {
        return Observable.create(subscriber -> {
            Log.d(TAG, "updateWatchedEpisode: " + Boolean.toString(isWatched));
            Realm realm = Realm.getInstance(context);
            realm.beginTransaction();
            RealmEpisode realmEpisode = realm
                    .where(RealmEpisode.class)
                    .equalTo("id", episodeId)
                    .findFirst();

            realmEpisode.setIsWatched(isWatched);
            realm.copyToRealmOrUpdate(realmEpisode);
            realm.commitTransaction();

            subscriber.onNext(realmEpisode);
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<RealmSeason> updateWatchedSeason(long seasonId, boolean isWatched) {
        return Observable.create(subscriber -> {
            Realm realm = Realm.getInstance(context);
            realm.beginTransaction();
            RealmSeason realmSeason = realm
                    .where(RealmSeason.class)
                    .equalTo("id", seasonId)
                    .findFirst();
            for (RealmEpisode realmEpisode : realmSeason.getEpisodes()) {
                realmEpisode.setIsWatched(isWatched);
            }
            realmSeason.setIsWatched(isWatched);
            realm.copyToRealmOrUpdate(realmSeason);
            realm.commitTransaction();

            Log.d(TAG, "updateWatchedSeason: finished");
            subscriber.onNext(realmSeason);
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<RealmTvShow> createOrUpdateTvShow(TvShow tvShow) {

        return Observable.from(tvShow.getSeasons())
                .flatMap(season -> api.getSeasonAsObservable(tvShow.getId(), season.getSeasonNumber()))
                .flatMap(season1 -> createRealmSeason(season1, tvShow.getId()))
                .toList()
                .flatMap(realmSeasons -> createRealmTvShow(tvShow, realmSeasons));
    }

    private Observable<RealmTvShow> createRealmTvShow(TvShow tvShow, List<RealmSeason> realmSeasons) {
        return Observable.create(subscriber -> {
            Realm realm = Realm.getInstance(context);

            RealmTvShow realmTvShow = new RealmTvShow();
            realmTvShow.setPosterPath(tvShow.getPosterPath());
            realmTvShow.setBackdropPath(tvShow.getBackdropPath());
            realmTvShow.setFirstAirDate(tvShow.getFirstAirDate());
            realmTvShow.setId(tvShow.getId());
            realmTvShow.setLastAirDate(tvShow.getLastAirDate());
            realmTvShow.setNumberOfEpisodes(tvShow.getNumberOfEpisodes());
            realmTvShow.setNumberOfSeasons(tvShow.getNumberOfSeasons());
            realmTvShow.setOverview(tvShow.getOverview());
            realmTvShow.setStatus(tvShow.getStatus());
            realmTvShow.setTitle(tvShow.getTitle());
            realmTvShow.setIsWatched(false);

            RealmList<RealmGenre> realmGenreRealmList = new RealmList<>();
            for (Genre genre : tvShow.getGenres()) {
                realmGenreRealmList.add(createRealmGenre(genre));
            }
            realmTvShow.setGenres(realmGenreRealmList);

            RealmList<RealmSeason> realmSeasonsList = new RealmList<>();
            realmSeasonsList.addAll(realmSeasons);
            realmTvShow.setSeasons(realmSeasonsList);

            realm.beginTransaction();
            realm.copyToRealmOrUpdate(realmTvShow);
            realm.commitTransaction();

            subscriber.onNext(realmTvShow);
            subscriber.onCompleted();
        });
    }

    private RealmGenre createRealmGenre(Genre genre) {
        Realm realm = Realm.getInstance(context);
        RealmGenre realmGenre = new RealmGenre();
        realmGenre.setId(genre.getId());
        realmGenre.setName(genre.getName());
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(realmGenre);
        realm.commitTransaction();
        return realmGenre;
    }

    private Observable<RealmSeason> createRealmSeason(Season season, int showId) {
        return Observable.create(subscriber -> {
            Realm realm = Realm.getInstance(context);

            RealmList<RealmEpisode> list = new RealmList<>();
            for (Episode episode : season.getEpisodes()) {
                list.add(createRealmEpisode(episode, showId));
            }

            RealmSeason realmSeason = new RealmSeason();
            realmSeason.setSeasonNumber(season.getSeasonNumber());
            realmSeason.setId(season.getId());
            realmSeason.setAirDate(season.getAirDate());
            realmSeason.setOverview(season.getOverview());
            realmSeason.setPosterPath(season.getPosterPath());
            realmSeason.setTitle(season.getTitle());
            realmSeason.setEpisodes(list);
            realmSeason.setIsWatched(false);
            realmSeason.setShowId(showId);

            realm.beginTransaction();
            realm.copyToRealmOrUpdate(realmSeason);
            realm.commitTransaction();

            subscriber.onNext(realmSeason);
            subscriber.onCompleted();
        });
    }

    private RealmEpisode createRealmEpisode(Episode episode, int showId) {
        Realm realm = Realm.getInstance(context);

        RealmEpisode realmEpisode = new RealmEpisode();
        realmEpisode.setTitle(episode.getTitle());
        realmEpisode.setOverview(episode.getOverview());
        realmEpisode.setAirDate(episode.getAirDate());
        realmEpisode.setEpisodeNumber(episode.getEpisodeNumber());
        realmEpisode.setId(episode.getId());
        realmEpisode.setSeasonNumber(episode.getSeasonNumber());
        realmEpisode.setStillPath(episode.getStillPath());
        realmEpisode.setIsWatched(false);
        realmEpisode.setShowId(showId);

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(realmEpisode);
        realm.commitTransaction();

        return realmEpisode;
    }

    @Override
    public List<Episode> getAiringEpisode(String date){
        Realm realm = Realm.getInstance(context);

        RealmResults<RealmEpisode> list = realm
                .where(RealmEpisode.class)
                .equalTo("airDate", date)
                .findAll();

        List<Episode> episodes = new ArrayList<>();

        for (RealmEpisode realmEpisode: list){
            Episode episode = createEpisodeUiObject(realmEpisode);
            episodes.add(episode);
        }

        return episodes;

    }

    @Override
    public Observable<TvShow> deleteTvShow(TvShow show) {
        return Observable.create(subscriber -> {
            Realm realm = Realm.getInstance(context);

            RealmTvShow realmTvShow = realm.where(RealmTvShow.class).equalTo("id", show.getId()).findFirst();
            RealmResults<RealmSeason> seasonRealmResults = realm.where(RealmSeason.class).equalTo("id", show.getId()).findAll();
            RealmResults<RealmEpisode> episodeRealmResults = realm.where(RealmEpisode.class).equalTo("id", show.getId()).findAll();

            realm.beginTransaction();
            realmTvShow.removeFromRealm();

            for (RealmSeason realmSeason : seasonRealmResults) {
                realmSeason.removeFromRealm();
            }

            for (RealmEpisode realmEpisode : episodeRealmResults) {
                realmEpisode.removeFromRealm();
            }

            realm.commitTransaction();
            subscriber.onNext(show);
            subscriber.onCompleted();
        });
    }

    @Override
    public boolean isTvShowAdded(int id) {
        RealmTvShow show = Realm.getInstance(context).where(RealmTvShow.class).equalTo("id", id).findFirst();
        boolean result = (show != null) ? true : false;
        return result;
    }

    @Override
    public Observable<TvShow> getTvShow(int id){

        return Observable.create(subscriber -> {
            RealmTvShow show = Realm.getInstance(context).where(RealmTvShow.class).equalTo("id", id).findFirst();
            subscriber.onNext(createTvShowUiObject(show));
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<Season> getSeason(long id){
        return Observable.create(subscriber -> {
            RealmSeason realmSeason = Realm.getInstance(context).where(RealmSeason.class).equalTo("id", id).findFirst();
            subscriber.onNext(createSeasonUiObject(realmSeason));
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<ArrayList<TvShow>> getAllTvShowsAsync() {
        return Realm.getInstance(context)
                .where(RealmTvShow.class)
                .findAllAsync()
                .asObservable()
                .map(realmTvShows -> {
                    ArrayList<TvShow> list = new ArrayList<TvShow>(realmTvShows.size());
                    for (RealmTvShow show : realmTvShows) {
                        list.add(createTvShowUiObject(show));
                    }
                    return list;
                });
    }

    @Override
    public List<TvShow> getAllTvShows(){
        RealmResults<RealmTvShow> realmTvShows = Realm.getInstance(context).where(RealmTvShow.class).findAll();
        List<TvShow> list = new ArrayList<>();
        for (RealmTvShow realmTvShow: realmTvShows){
            list.add(createTvShowUiObject(realmTvShow));
        }

        return list;
    }

    private TvShow createTvShowUiObject(RealmTvShow realmTvShow) {
        List<Season> seasons = createSeasonUiObjectList(realmTvShow);
        List<Genre> genres = createGenreUiObjectList(realmTvShow);
        return new TvShowBuilder(realmTvShow.getId(), realmTvShow.getBackdropPath(), realmTvShow.getOverview(), realmTvShow.getTitle())
                .posterPath(realmTvShow.getPosterPath())
                .firstAirDate(realmTvShow.getFirstAirDate())
                .lastAirDate(realmTvShow.getLastAirDate())
                .numberOfEpisodes(realmTvShow.getNumberOfEpisodes())
                .numberOfSeasons(realmTvShow.getNumberOfSeasons())
                .status(realmTvShow.getStatus())
                .seasons(seasons)
                .genres(genres)
                .build();

    }

    private List<Genre> createGenreUiObjectList(RealmTvShow realmTvShow) {
        List<Genre> genres = new ArrayList<>();
        for (RealmGenre realmGenre : realmTvShow.getGenres()) {
            genres.add(createGenreUiObject(realmGenre));
        }
        return genres;
    }

    private Genre createGenreUiObject(RealmGenre realmGenre) {
        Genre genre = new Genre();
        genre.setName(realmGenre.getName());
        genre.setId(realmGenre.getId());
        return genre;
    }

    private List<Season> createSeasonUiObjectList(RealmTvShow realmTvShow) {
        List<Season> seasons = new ArrayList<>();
        for (RealmSeason realmSeason : realmTvShow.getSeasons()) {
            seasons.add(createSeasonUiObject(realmSeason));
        }
        return seasons;
    }

    private Season createSeasonUiObject(RealmSeason realmSeason) {
        List<Episode> episodes = createEpisodeUiObjectList(realmSeason);
        return new SeasonBuilder(realmSeason.getId(), realmSeason.getTitle())
                .seasonNumber(realmSeason.getSeasonNumber())
                .airDate(realmSeason.getAirDate())
                .isWatched(realmSeason.isWatched())
                .overview(realmSeason.getOverview())
                .posterPath(realmSeason.getPosterPath())
                .episodes(episodes)
                .build();
    }

    private List<Episode> createEpisodeUiObjectList(RealmSeason realmSeason) {
        List<Episode> episodes = new ArrayList<>();
        for (RealmEpisode realmEpisode : realmSeason.getEpisodes()) {
            episodes.add(createEpisodeUiObject(realmEpisode));
        }
        return episodes;
    }

    private Episode createEpisodeUiObject(RealmEpisode realmEpisode) {
        return new EpisodeBuilder(realmEpisode.getId())
                .episodeNum(realmEpisode.getEpisodeNumber())
                .seasonNum(realmEpisode.getSeasonNumber())
                .airDate(realmEpisode.getAirDate())
                .isWatched(realmEpisode.isWatched())
                .showId(realmEpisode.getShowId())
                .overview(realmEpisode.getOverview())
                .stillPath(realmEpisode.getStillPath())
                .title(realmEpisode.getTitle())
                .build();
    }
}
