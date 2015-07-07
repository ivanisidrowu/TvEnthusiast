package tw.invictus.tvethusiast.util;

import android.content.Context;

import java.io.InputStream;
import java.util.Properties;

import tw.invictus.tvethusiast.R;

/**
 * Created by ivan on 6/21/15.
 */
public class PropertiesUtil {

    private static final String KEY_API = "tvdb.api.key";
    private static final String KEY_ENDPOINT = "tvdb.api.endpoint";
    private static final String PROPS_PATH = "app.properties";
    private static PropertiesUtil instance = null;
    private Properties properties = null;
    private Context context = null;

    private PropertiesUtil(Context context){
        this.context = context;
    }

    public static PropertiesUtil getInstance(Context context){
        if(instance == null){
            synchronized (PropertiesUtil.class){
                if(instance == null){
                    instance = new PropertiesUtil(context);
                }
            }
        }
        return instance;
    }

    private Properties getProperties(){
        if(properties == null){
            properties = new Properties();
            try {
                InputStream inputStream = context.getResources().openRawResource(R.raw.app);
                properties.load(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return properties;
    }
    public String getApiKey(){
        return getProperties().getProperty(KEY_API);
    }

    public String getKeyEndpoint(){
        return instance.getProperties().getProperty(KEY_ENDPOINT);
    }
}
