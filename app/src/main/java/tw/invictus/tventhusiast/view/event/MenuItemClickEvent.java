package tw.invictus.tventhusiast.view.event;

/**
 * Created by ivan on 1/10/16.
 */
public class MenuItemClickEvent {

    private int sortType;

    public MenuItemClickEvent(int sortType) {
        this.sortType = sortType;
    }

    public int getSortType() {
        return sortType;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }
}
