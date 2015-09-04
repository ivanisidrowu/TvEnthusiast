package tw.invictus.tvethusiast.injector.component;

import android.content.Context;

import java.util.List;

import dagger.Component;
import tw.invictus.tvethusiast.injector.Activity;
import tw.invictus.tvethusiast.injector.module.ActivityModule;
import tw.invictus.tvethusiast.injector.module.TvEthusiastModule;
import tw.invictus.tvethusiast.model.Series;
import tw.invictus.tvethusiast.view.activity.TvSeriesListActivity;

/**
 * Created by ivan on 7/10/15.
 */

@Activity
@Component(dependencies = AppComponent.class, modules = {TvEthusiastModule.class, ActivityModule.class})
public interface TvEthusiastComponent extends ActivityComponent{

    void inject(TvSeriesListActivity activity);

    Context activityContext();

    List<Series> series();

}
