package tw.invictus.tvethusiast.model;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

/**
 * Created by ivan on 10/23/15.
 */
public class Episode extends SugarRecord<Episode>{
    @SerializedName("id")
    private Integer serverId;

    @SerializedName("air_date")
    private String airDate;

    @SerializedName("name")
    private String title;

    private String overview;

    @SerializedName("episode_number")
    private Integer episodeNumber;

    @SerializedName("season_number")
    private Integer seasonNumber;

    @SerializedName("still_path")
    private String stillPath;

    private Season season;

    public Episode(){

    }

    public Episode(String title) {
        this.title = title;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

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

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public String getStillPath() {
        return stillPath;
    }

    public void setStillPath(String stillPath) {
        this.stillPath = stillPath;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }
}
