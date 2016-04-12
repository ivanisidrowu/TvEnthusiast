package tw.invictus.tventhusiast.model.db;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ivan on 10/23/15.
 */
public class RealmSeason extends RealmObject{

    @PrimaryKey
    private Long id;
    private String airDate;
    private String title;
    private String overview;
    private String posterPath;
    private Integer seasonNumber;
    private Boolean isWatched;
    private Integer showId;
    private RealmList<RealmEpisode> episodes;

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public Boolean isWatched() {
        return isWatched;
    }

    public void setIsWatched(Boolean isWatched) {
        this.isWatched = isWatched;
    }

    public RealmList<RealmEpisode> getEpisodes() {
        return this.episodes;
    }

    public void setEpisodes(RealmList<RealmEpisode> episodes) {
        this.episodes = episodes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }
}
