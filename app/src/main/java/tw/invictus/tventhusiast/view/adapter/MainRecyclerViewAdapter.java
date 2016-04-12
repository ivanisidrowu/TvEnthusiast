package tw.invictus.tventhusiast.view.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tw.invictus.tventhusiast.BuildConfig;
import tw.invictus.tventhusiast.R;
import tw.invictus.tventhusiast.model.pojo.TvShow;
import tw.invictus.tventhusiast.view.MainFragmentView;

/**
 * Created by ivan on 12/27/15.
 */
public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = MainRecyclerViewAdapter.class.getSimpleName();
    private ArrayList<TvShow> shows;
    private MainFragmentView mainView;

    public MainRecyclerViewAdapter(ArrayList<TvShow> shows, MainFragmentView mainView) {
        this.shows = shows;
        this.mainView = mainView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_list_item, parent, false);
        return new ViewHolder(rootView, mainView, shows);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TvShow show = shows.get(position);
        String imageUrl = BuildConfig.IMG_BASE_URL + holder.imageSizeUrl + show.getPosterPath();
        String title = show.getTitle();
        if(holder.textView != null){
            holder.textView.setText(title);
        }
        Glide.with(holder.poster.getContext())
                .load(imageUrl)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.poster);

    }

    public ArrayList<TvShow> getShows() {
        return shows;
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.poster)
        ImageView poster;
        @Nullable
        @Bind(R.id.card_text)
        TextView textView;
        @Bind(R.id.card_view)
        CardView cardView;
        @BindString(R.string.image_size_url)
        String imageSizeUrl;

        View itemView;
        MainFragmentView mainView;
        List<TvShow> tvShows;

        public ViewHolder(View itemView, MainFragmentView mainView, List<TvShow> shows) {
            super(itemView);
            this.itemView = itemView;
            this.mainView = mainView;
            this.tvShows = shows;
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.card_view)
        public void onClick(View view){
            mainView.onTvShowClicked(tvShows.get(getAdapterPosition()));
        }
    }
}
