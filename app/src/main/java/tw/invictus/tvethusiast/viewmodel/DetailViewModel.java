package tw.invictus.tvethusiast.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import tw.invictus.tvethusiast.model.TvShow;
import tw.invictus.tvethusiast.view.util.PropertyConfig;

/**
 * Created by ivan on 10/11/15.
 */
public class DetailViewModel {

    private TvShow show;
    private Context context;

    public DetailViewModel(Context context, TvShow show){
        this.context = context;
        this.show = show;
    }

    public String getName(){
        return show.getName();
    }

    public String getOverview(){
        return show.getOverview();
    }

    public String getBackdropUrl(){
        return PropertyConfig.IMG_BASE + show.getBackdropPath();
    }

    public void setTvShow(TvShow show){
        this.show = show;
    }

    @BindingAdapter({"bind:backdropUrl"})
    public static void loadImage(ImageView view, String backdropUrl){
        Picasso.with(view.getContext()).load(backdropUrl).fit().centerCrop().into(view);
    }
}
