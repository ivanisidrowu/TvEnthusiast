package tw.invictus.tvethusiast.injector.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import tw.invictus.tvethusiast.injector.Activity;

/**
 * Created by ivan on 7/10/15.
 */
@Module
public class ActivityModule {

    private final Context mContext;

    public ActivityModule(Context mContext){
        this.mContext = mContext;
    }

    @Provides @Activity Context provideActivityContext(){
        return mContext;
    }
}
