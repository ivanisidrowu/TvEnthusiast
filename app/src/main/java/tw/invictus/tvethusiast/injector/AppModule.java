package tw.invictus.tvethusiast.injector;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tw.invictus.tvethusiast.TvEthusiastApplication;
import tw.invictus.tvethusiast.model.Repository;
import tw.invictus.tvethusiast.model.rest.RestRepository;

/**
 * Created by ivan on 7/10/15.
 */

@Module
public class AppModule {

    private final TvEthusiastApplication mTvEthusicatsApplication;

    public AppModule(TvEthusiastApplication tvEthusiastApplication){
        this.mTvEthusicatsApplication = tvEthusiastApplication;
    }

    @Provides @Singleton TvEthusiastApplication providesTvEthusiastApplicationContext(){
        return mTvEthusicatsApplication;
    }

    @Provides @Singleton Repository provideDataRepository(RestRepository restRepository){
        return restRepository;
    }
}
