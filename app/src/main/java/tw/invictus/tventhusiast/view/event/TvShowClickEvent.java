package tw.invictus.tventhusiast.view.event;

import tw.invictus.tventhusiast.model.pojo.TvShow;

/**
 * Created by ivan on 1/9/16.
 */
public class TvShowClickEvent {
    private TvShow show;
    private boolean isTwoPane = false;

    public TvShowClickEvent(TvShow show, boolean isTwoPane) {
        this.show = show;
        this.isTwoPane = isTwoPane;
    }

    public boolean isTwoPane() {
        return isTwoPane;
    }

    public void setIsTwoPane(boolean isTwoPane) {
        this.isTwoPane = isTwoPane;
    }

    public TvShow getShow() {
        return show;
    }

    public void setShow(TvShow show) {
        this.show = show;
    }
}
