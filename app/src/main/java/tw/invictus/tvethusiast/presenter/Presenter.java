package tw.invictus.tvethusiast.presenter;

import android.content.Intent;

import tw.invictus.tvethusiast.view.View;

/**
 * Created by ivan on 7/10/15.
 */
public interface Presenter {
    void onStart ();

    void onStop ();

    void attachView (View v);

    void attachIncomingIntent (Intent intent);

}
