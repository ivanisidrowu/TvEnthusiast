package tw.invictus.tventhusiast.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ivan.wu on 12/31/2015.
 */
public final class NetworkUtil {

    public static int NOT_CONNECTED = 0;
    public static int CONNECTED = 1;


    public static final int getConnectivityStatus(Context context) {
        int result = NOT_CONNECTED;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            result = CONNECTED;
        }

        return result;
    }

    public static final boolean isOnline(Context context) {
        int status = NetworkUtil.getConnectivityStatus(context);
        return (status == NetworkUtil.CONNECTED) ? true : false;
    }

}
