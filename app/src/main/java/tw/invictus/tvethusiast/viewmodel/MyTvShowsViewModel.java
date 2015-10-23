package tw.invictus.tvethusiast.viewmodel;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.orm.query.Select;

import java.util.List;

import tw.invictus.tvethusiast.model.TvShow;
import tw.invictus.tvethusiast.view.adapter.RecyclerViewAdapter;

/**
 * Created by ivan on 10/23/15.
 */
public class MyTvShowsViewModel {

    private Context context;
    private RecyclerView recyclerView;

    public MyTvShowsViewModel(Context context){
        this.context = context;
    }

    public void setRecyclerView(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
        getMySeries();
    }

    public void getMySeries(){
        List<TvShow> mySeries = TvShow.listAll(TvShow.class);
        recyclerView.setAdapter(new RecyclerViewAdapter(context, mySeries));
    }
}
