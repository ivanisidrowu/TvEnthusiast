package tw.invictus.tvethusiast.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import tw.invictus.tvethusiast.R;

/**
 * Created by ivan on 9/20/15.
 */
public class PropertyCofig {
    private final Properties properties;

    public PropertyCofig(Context context) throws IOException {
        InputStream rawResource = context.getResources().openRawResource(R.raw.config);
        properties = new Properties();
        properties.load(rawResource);
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
