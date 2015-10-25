package tw.invictus.tvethusiast.view.event;

/**
 * Created by ivan on 10/25/15.
 */
public class SearchTvShowEvent {

    private String query;

    public SearchTvShowEvent(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
