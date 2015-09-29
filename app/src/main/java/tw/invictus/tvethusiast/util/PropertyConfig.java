package tw.invictus.tvethusiast.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import tw.invictus.tvethusiast.R;

/**
 * Created by ivan on 9/20/15.
 */
public class PropertyConfig {
    public static final String IMG_BASE = "http://image.tmdb.org/t/p/w500";
    private final Properties properties;

    public PropertyConfig(Context context) throws IOException {
        InputStream rawResource = context.getResources().openRawResource(R.raw.config);
        properties = new Properties();
        properties.load(rawResource);
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }

}
