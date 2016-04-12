package tw.invictus.tventhusiast.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import tw.invictus.tventhusiast.presenter.MainFragmentPresenter;
import tw.invictus.tventhusiast.presenter.MainFragmentPresenterImpl;

/**
 * Created by ivan on 12/26/15.
 */
@Module
public class MainFragmentModule {

    private final Context context;

    public MainFragmentModule(Context context){
        this.context = context;
    }

    @Provides
    MainFragmentPresenter provideMainPresenter(){
        return new MainFragmentPresenterImpl(context);
    }
}
