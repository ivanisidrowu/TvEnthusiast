package tw.invictus.tventhusiast.model.builder;

import java.util.List;

import tw.invictus.tventhusiast.model.pojo.Episode;
import tw.invictus.tventhusiast.model.pojo.Season;

/**
 * Created by ivan on 1/23/16.
 */
public class SeasonBuilder {
    private long id;
    private String airDate;
    private String title;
    private String overview;
    private String posterPath;
    private int seasonNumber;
    private boolean isWatched = false;
    private List<Episode> episodes;

    public SeasonBuilder(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public SeasonBuilder id(long id) {
        this.id = id;
        return this;
    }

    public SeasonBuilder airDate(String airDate) {
        this.airDate = airDate;
        return this;
    }

    public SeasonBuilder title(String title) {
        this.title = title;
        return this;
    }

    public SeasonBuilder overview(String overview) {
        this.overview = overview;
        return this;
    }

    public SeasonBuilder posterPath(String posterPath) {
        this.posterPath = posterPath;
        return this;
    }

    public SeasonBuilder seasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
        return this;
    }

    public SeasonBuilder isWatched(boolean isWatched) {
        this.isWatched = isWatched;
        return this;
    }

    public SeasonBuilder episodes(List<Episode> episodes) {
        this.episodes = episodes;
        return this;
    }

    public Season build(){
        return new Season(id, airDate, title, overview, posterPath, seasonNumber, isWatched, episodes);
    }
}
