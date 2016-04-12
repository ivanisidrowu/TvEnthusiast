package tw.invictus.tventhusiast.view.event;


import tw.invictus.tventhusiast.model.pojo.TvShow;

/**
 * Created by ivan on 11/1/15.
 */
public class RefreshMyTvShowEvent {


    private int textResourceId;
    private TvShow show;

    public RefreshMyTvShowEvent(int textResourceId, TvShow show){
        this.textResourceId = textResourceId;
        this.show = show;
    }

    public RefreshMyTvShowEvent(TvShow show) {
        this.show = show;
    }

    public int getTextResourceId() {
        return textResourceId;
    }

    public void setTextResourceId(int textResourceId) {
        this.textResourceId = textResourceId;
    }

    public TvShow getShow() {
        return show;
    }

    public void setShow(TvShow show) {
        this.show = show;
    }
}
