package tw.invictus.tventhusiast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import tw.invictus.tventhusiast.service.DailyUpdateServiceHelper;

/**
 * Created by ivan on 12/6/15.
 */
public class DailyUpdateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        DailyUpdateServiceHelper.setService(context);
    }
}
