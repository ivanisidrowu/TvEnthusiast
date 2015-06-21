package tw.invictus.tvethusiast.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ivan on 6/21/15.
 */
@Root(name = "Series")
public class Series{

    @Element(name = "id")
    private int id;

    @Element(name = "seriesid")
    private int sereiesId;

    @Element(name = "language")
    private String language;

    @Element(name = "SeriesName")
    private String seriesName;

    @Element(name = "banner")
    private String banner;

    @Element(name = "Overview")
    private String overview;

    @Element(name = "FirstAired")
    private String firstAired;

    @Element(name = "Network")
    private String network;

    @Element(name = "IMDB_ID")
    private String imdbId;

    @Element(name = "zap2it_id")
    private String zap2itId;

    public int getSereiesId() {
        return sereiesId;
    }

    public void setSereiesId(int sereiesId) {
        this.sereiesId = sereiesId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirstAired() {
        return firstAired;
    }

    public void setFirstAired(String firstAired) {
        this.firstAired = firstAired;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getZap2itId() {
        return zap2itId;
    }

    public void setZap2itId(String zap2itId) {
        this.zap2itId = zap2itId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
