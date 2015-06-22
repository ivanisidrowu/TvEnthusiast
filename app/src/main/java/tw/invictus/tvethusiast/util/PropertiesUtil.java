package tw.invictus.tvethusiast.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by ivan on 6/21/15.
 */
public class PropertiesUtil {

    private static final String KEY_API = "tvdb.api.key";
    private static final String KEY_ENDPOINT = "tvdb.api.endpoint";
    private static final String PROPS_PATH = "app.properties";
    private static PropertiesUtil instance = null;
    private Properties properties = null;

    private PropertiesUtil(){

    }

    public static PropertiesUtil getInstance(){
        if(instance == null){
            synchronized (PropertiesUtil.class){
                if(instance == null){
                    instance = new PropertiesUtil();
                }
            }
        }
        return instance;
    }

    private Properties getProperties(){
        if(properties == null){
            properties = new Properties();
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(PROPS_PATH);
            try {
                properties.load(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return properties;
    }

    public static String getApiKey(){
        return getInstance().getProperties().getProperty(KEY_API);
    }

    public static String getKeyEndpoint(){
        return getInstance().getProperties().getProperty(KEY_ENDPOINT);
    }
}
