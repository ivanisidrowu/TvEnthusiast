package tw.invictus.tventhusiast.model.builder;

import tw.invictus.tventhusiast.model.pojo.Episode;

/**
 * Created by ivan on 1/23/16.
 */
public class EpisodeBuilder {
    private long id;
    private String airDate;
    private String title;
    private String overview;
    private int episodeNumber;
    private int seasonNumber;
    private String stillPath;
    private boolean isWatched = false;
    private int showId;

    public EpisodeBuilder(long id) {
        this.id = id;
    }

    public EpisodeBuilder id(long id) {
        this.id = id;
        return this;
    }

    public EpisodeBuilder showId(int showId){
        this.showId = showId;
        return this;
    }

    public EpisodeBuilder airDate(String airDate) {
        this.airDate = airDate;
        return this;
    }

    public EpisodeBuilder title(String title) {
        this.title = title;
        return this;
    }

    public EpisodeBuilder overview(String overview) {
        this.overview = overview;
        return this;
    }

    public EpisodeBuilder episodeNum(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
        return this;
    }

    public EpisodeBuilder seasonNum(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
        return this;
    }

    public EpisodeBuilder stillPath(String stillPath) {
        this.stillPath = stillPath;
        return this;
    }

    public EpisodeBuilder isWatched(boolean isWatched) {
        this.isWatched = isWatched;
        return this;
    }

    public Episode build(){
        return new Episode(id, airDate, title, overview, episodeNumber, seasonNumber, stillPath, showId, isWatched);
    }
}
