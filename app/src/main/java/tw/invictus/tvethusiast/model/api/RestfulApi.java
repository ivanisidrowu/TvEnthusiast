package tw.invictus.tvethusiast.model.api;

import android.content.Context;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import tw.invictus.tvethusiast.view.util.PropertyConfig;

/**
 * Created by ivan on 10/23/15.
 */
public class RestfulApi {
    public static RestfulService getApi(Context context) throws IOException {
        PropertyConfig config = new PropertyConfig(context);
        String url = config.getProperty("api.endpoint");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        return  retrofit.create(RestfulService.class);
    }
}
