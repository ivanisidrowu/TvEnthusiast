package tw.invictus.tvethusiast.viewmodel;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import tw.invictus.tvethusiast.model.TvShow;
import tw.invictus.tvethusiast.model.api.RestfulApi;
import tw.invictus.tvethusiast.model.pojo.SeriesResponse;
import tw.invictus.tvethusiast.view.adapter.RecyclerViewAdapter;
import tw.invictus.tvethusiast.view.util.PropertyConfig;

/**
 * Created by ivan on 10/23/15.
 */
public class PopularShowsViewModel {

    private Context context;
    private RecyclerView recyclerView;

    public PopularShowsViewModel(Context context){
        this.context = context;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
        getTvSeries();
    }

    private void getTvSeries(){
        try {
            PropertyConfig config = new PropertyConfig(context);
            String apiKey = config.getProperty("api.key");
            RestfulApi.getApi(context).getPopularShows(apiKey).enqueue(new Callback<SeriesResponse>() {
                @Override
                public void onResponse(Response<SeriesResponse> response, Retrofit retrofit) {
                    List<TvShow> tvShows = response.body().getResult();
                    recyclerView.setAdapter(new RecyclerViewAdapter(context, tvShows));
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.e("ivan", "fail");
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
