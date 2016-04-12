package tw.invictus.tventhusiast.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscription;
import tw.invictus.tventhusiast.BuildConfig;
import tw.invictus.tventhusiast.R;
import tw.invictus.tventhusiast.model.api.RestfulApi;
import tw.invictus.tventhusiast.model.db.RealmService;
import tw.invictus.tventhusiast.model.db.RealmServiceImpl;
import tw.invictus.tventhusiast.model.pojo.Episode;
import tw.invictus.tventhusiast.model.pojo.TvShow;
import tw.invictus.tventhusiast.view.activity.MainActivity;

/**
 * Created by ivan on 3/7/16.
 */
public class DailyUpdateService extends IntentService {

    public static final String TAG = DailyUpdateService.class.getSimpleName();
    public static final String NOTIFICATION_DATE = "notification.date";

    private RestfulApi api;
    private RealmService service;
    private List<Subscription> subscriptions = new ArrayList<>();

    public DailyUpdateService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            initRestfulApi();
            initRealmService();

            if (!isNotificationSentToday()) {
                checkTodayAiring();
                checkTvShowUpdates();
            }

            unSubscribeAllTasks();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            onError(e);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unSubscribeAllTasks();
    }

    private void unSubscribeAllTasks() {
        for (Subscription subscription : subscriptions) {
            subscription.unsubscribe();
        }
    }

    private void checkTvShowUpdates() throws IOException {
        List<TvShow> result = service.getAllTvShows();
        updateTvShows(result);
    }

    private void onError(Throwable throwable) {
        Log.e(TAG, "onError: ", throwable);
    }

    private void updateTvShows(List<TvShow> shows) {
        for (TvShow show: shows){
            Subscription subscription = service
                    .createOrUpdateTvShow(show)
                    .subscribe();
            subscriptions.add(subscription);
        }
    }

    private void checkTodayAiring() {
        List<Episode> list = service.getAiringEpisode(getTodayDate());

        for (Episode episode: list){
            sendNotification(episode);
        }

        saveNotificationSended();
    }

    private String getTodayDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date current = new Date();
        return simpleDateFormat.format(current);
    }

    private void sendNotification(Episode episode) {
        String title = getResources().getString(R.string.new_episode);
        service.getTvShow(episode.getShowId())
                .subscribe(show -> {
                    String msg = this.getResources().getString(R.string.airing_msg, show.getTitle(), episode.getSeasonNumber(), episode.getEpisodeNumber(), episode.getTitle());

                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(this)
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle(title)
                                    .setContentText(msg);
                    Intent resultIntent = new Intent(this, MainActivity.class);
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                    stackBuilder.addParentStack(MainActivity.class);
                    stackBuilder.addNextIntent(resultIntent);
                    PendingIntent resultPendingIntent =
                            stackBuilder.getPendingIntent(
                                    0,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                            );
                    mBuilder.setContentIntent(resultPendingIntent);
                    NotificationManager mNotificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.notify((int) episode.getId(), mBuilder.build());

                }, throwable -> onError(throwable));

    }

    private void initRestfulApi() {
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(chain -> {
            Request request = chain.request();
            HttpUrl url = request.httpUrl().newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build();
            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        });

        api = new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(RestfulApi.class);
    }

    private void initRealmService() {
        service = new RealmServiceImpl(this);
        service.setApi(api);
    }

    private boolean isNotificationSentToday() {
        String today = getTodayDate();
        SharedPreferences preferences = this.getSharedPreferences(TAG, MODE_PRIVATE);
        String value = preferences.getString(NOTIFICATION_DATE, "");
        return (today.compareTo(value) == 0) ? true : false;
    }

    private void saveNotificationSended() {
        SharedPreferences.Editor editor = this.getSharedPreferences(TAG, MODE_PRIVATE).edit();
        editor.putString(NOTIFICATION_DATE, getTodayDate()).commit();
    }
}
