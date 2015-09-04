package tw.invictus.tvethusiast.model;

/**
 * Created by ivan on 7/10/15.
 */
public class Series {
    private String title;
    private String year;
    private String imdbId;

    public Series(String title, String year, String imdbId) {
        this.title = title;
        this.year = year;
        this.imdbId = imdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }
}
