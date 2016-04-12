package tw.invictus.tventhusiast.view.event;

import tw.invictus.tventhusiast.model.pojo.Season;
import tw.invictus.tventhusiast.model.pojo.TvShow;

/**
 * Created by ivan on 2/20/16.
 */
public class SeasonItemClickEvent {
    private TvShow show;
    private Season season;

    public SeasonItemClickEvent(TvShow show, Season season) {
        this.show = show;
        this.season = season;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public TvShow getShow() {
        return show;
    }

    public void setShow(TvShow show) {
        this.show = show;
    }
}
