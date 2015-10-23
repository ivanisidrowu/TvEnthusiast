package tw.invictus.tvethusiast.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import de.greenrobot.event.EventBus;
import tw.invictus.tvethusiast.view.event.CardClickEvent;
import tw.invictus.tvethusiast.model.TvShow;
import tw.invictus.tvethusiast.view.util.PropertyConfig;
import tw.invictus.tvethusiast.view.DetailActivity;

/**
 * Created by ivan on 10/11/15.
 */
public class ListItemViewModel extends BaseObservable{

    private TvShow tvShow;
    private Context context;

    public ListItemViewModel(Context context, TvShow show){
        this.context = context;
        this.tvShow = show;
    }

    public String getName(){
        return tvShow.getTitle();
    }

    public String getPosterUrl(){
        return PropertyConfig.IMG_BASE + tvShow.getPosterPath();
    }

    public void setTvShow(TvShow show){
        this.tvShow = show;
    }

    public void onItemClick(View view){
        Intent intent = new Intent(context, DetailActivity.class);
        CardClickEvent event = new CardClickEvent(tvShow);
        EventBus.getDefault().postSticky(event);
        context.startActivity(intent);
    }

    @BindingAdapter({"bind:posterUrl"})
    public static void loadImage(ImageView view, String posterUrl){
        Picasso.with(view.getContext()).load(posterUrl).fit().centerCrop().into(view);
    }
}
