package tw.invictus.tvethusiast.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.SearchView;

import de.greenrobot.event.EventBus;
import tw.invictus.tvethusiast.view.SearchResultActivity;
import tw.invictus.tvethusiast.view.event.SearchTvShowEvent;

/**
 * Created by ivan on 10/11/15.
 */
public class MainViewModel implements SearchView.OnQueryTextListener{

    private Context context;

    public MainViewModel(Context context){
        this.context = context;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Intent intent = new Intent(context, SearchResultActivity.class);
        SearchTvShowEvent event = new SearchTvShowEvent(query);
        EventBus.getDefault().postSticky(event);
        context.startActivity(intent);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
