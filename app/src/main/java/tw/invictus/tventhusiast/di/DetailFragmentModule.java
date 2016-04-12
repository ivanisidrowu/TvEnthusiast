package tw.invictus.tventhusiast.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import tw.invictus.tventhusiast.presenter.DetailPresenter;
import tw.invictus.tventhusiast.presenter.DetailPresenterImpl;

/**
 * Created by ivan on 12/27/15.
 */
@Module
public class DetailFragmentModule {

    private Context context;

    public DetailFragmentModule(Context context) {
        this.context = context;
    }

    @Provides
    DetailPresenter provideDetailPresenter(){
        return new DetailPresenterImpl();
    }
}
