package tw.invictus.tventhusiast.util;

import android.content.Context;
import android.content.Intent;

/**
 * Created by ivan on 1/16/16.
 */
public final class IntentUtil {

    public static final String YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v=";

    public static final void shareYoutubeVideo(String videoKey, Context context, String shareMessage){
        String url = YOUTUBE_BASE_URL + videoKey;
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url);

        context.startActivity(Intent.createChooser(intent, shareMessage));
    }
}
