package tw.invictus.tventhusiast.model.builder;

import java.util.List;

import tw.invictus.tventhusiast.model.pojo.Genre;
import tw.invictus.tventhusiast.model.pojo.Season;
import tw.invictus.tventhusiast.model.pojo.TvShow;

/**
 * Created by ivan on 1/23/16.
 */
public class TvShowBuilder {
    private int id;
    private String backdropPath;
    private String firstAirDate;
    private String overview;
    private String posterPath;
    private String title;
    private String lastAirDate;
    private int numberOfEpisodes;
    private int numberOfSeasons;
    private String status;
    private List<Season> seasons;
    private List<Genre> genres;

    public TvShowBuilder(int id, String backdropPath, String overview, String title) {
        this.id = id;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.title = title;
    }

    public TvShowBuilder numberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
        return this;
    }

    public TvShowBuilder id(int id) {
        this.id = id;
        return this;
    }

    public TvShowBuilder backdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
        return this;
    }

    public TvShowBuilder firstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
        return this;
    }

    public TvShowBuilder overview(String overview) {
        this.overview = overview;
        return this;
    }

    public TvShowBuilder posterPath(String posterPath) {
        this.posterPath = posterPath;
        return this;
    }

    public TvShowBuilder title(String title) {
        this.title = title;
        return this;
    }

    public TvShowBuilder lastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
        return this;
    }

    public TvShowBuilder numberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
        return this;
    }

    public TvShowBuilder status(String status) {
        this.status = status;
        return this;
    }

    public TvShowBuilder seasons(List<Season> seasons) {
        this.seasons = seasons;
        return this;
    }

    public TvShowBuilder genres(List<Genre> genres) {
        this.genres = genres;
        return this;
    }

    public TvShow build(){
        return new TvShow(id, backdropPath, firstAirDate, overview, posterPath, title, lastAirDate, numberOfEpisodes, numberOfSeasons, genres, status, seasons);
    }
}
