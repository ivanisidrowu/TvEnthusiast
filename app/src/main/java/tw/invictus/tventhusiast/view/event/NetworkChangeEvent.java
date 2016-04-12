package tw.invictus.tventhusiast.view.event;

/**
 * Created by ivan.wu on 12/31/2015.
 */
public class NetworkChangeEvent {

    private final int networkStatus;

    public NetworkChangeEvent(int networkStatus) {
        this.networkStatus = networkStatus;
    }

    public int getNetworkStatus() {
        return networkStatus;
    }
}
