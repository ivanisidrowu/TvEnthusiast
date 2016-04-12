package tw.invictus.tventhusiast.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import tw.invictus.tventhusiast.presenter.MainActivityPresenter;
import tw.invictus.tventhusiast.presenter.MainActivityPresenterImpl;

/**
 * Created by ivan on 2/27/16.
 */
@Module
public class MainActivityModule {
    private final Context context;

    public MainActivityModule(Context context){
        this.context = context;
    }

    @Provides
    public MainActivityPresenter provideMainActivityPresenter(){
        return new MainActivityPresenterImpl();
    }
}
