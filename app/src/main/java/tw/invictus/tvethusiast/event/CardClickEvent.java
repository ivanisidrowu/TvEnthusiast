package tw.invictus.tvethusiast.event;

import tw.invictus.tvethusiast.model.TvShow;

/**
 * Created by ivan on 9/29/15.
 */
public class CardClickEvent {

    private TvShow tvShow;

    public CardClickEvent(TvShow show){
        this.tvShow = show;
    }

    public TvShow getTvShow() {
        return tvShow;
    }

    public void setTvShow(TvShow tvShow) {
        this.tvShow = tvShow;
    }
}
