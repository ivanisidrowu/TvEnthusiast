package tw.invictus.tventhusiast.di;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import tw.invictus.tventhusiast.BuildConfig;
import tw.invictus.tventhusiast.model.api.RestfulApi;

/**
 * Created by ivan on 12/26/15.
 */
@Module
public class ApiModule {

    private OkHttpClient client;

    public ApiModule(){
        client = new OkHttpClient();
        client.interceptors().add(chain -> {
            Request request = chain.request();
            HttpUrl url = request.httpUrl().newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build();
            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        });

    }

    @Singleton
    @Provides
    RestfulApi provideRestfulApi(){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(RestfulApi.class);
    }
}
