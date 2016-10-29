package tw.invictus.tventhusiast.view.adapter;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.BindInt;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import tw.invictus.tventhusiast.R;
import tw.invictus.tventhusiast.model.pojo.Episode;
import tw.invictus.tventhusiast.model.pojo.Season;
import tw.invictus.tventhusiast.util.ColorUtil;
import tw.invictus.tventhusiast.view.EpisodeListView;


/**
 * Created by ivan on 9/20/15.
 */
public class EpisodeRecyclerViewAdapter extends RecyclerView.Adapter<EpisodeRecyclerViewAdapter.ViewHolder> {

    private List<Episode> mValues;
    private int lightColor;
    private EpisodeListView view;
    private boolean isShowInDb;

    public EpisodeRecyclerViewAdapter(EpisodeListView view, Season season, int lightColor, boolean isShowInDb) {
        this.view = view;
        this.mValues = season.getEpisodes();
        this.lightColor = lightColor;
        this.isShowInDb = isShowInDb;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.episode_list_item, parent, false);
        return new ViewHolder(root, view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Episode episode = mValues.get(position);
        String episodeNum = holder.itemName.getContext().getString(R.string.episode, episode.getEpisodeNumber(), episode.getTitle());
        holder.itemName.setText(episodeNum);

        boolean isClosedToWhite = ColorUtil.isColorCloserToWhite(lightColor);

        if (isClosedToWhite){
            holder.itemName.setTextColor(Color.BLACK);
        }else{
            holder.itemName.setTextColor(Color.WHITE);
        }

        if (isShowInDb){
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(episode.isWatched());
        }else{
            holder.checkBox.setVisibility(View.GONE);
        }

        holder.cardView.setCardBackgroundColor(lightColor);
        holder.cardView.setRadius(holder.cardRadius);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.card_view)
        CardView cardView;
        @Bind(R.id.item_name)
        TextView itemName;
        @Bind(R.id.checkbox)
        CheckBox checkBox;
        @BindInt(R.integer.fragment_list_card_radius)
        int cardRadius;

        private EpisodeListView listView;

        public ViewHolder(View view, EpisodeListView listView) {
            super(view);
            this.listView = listView;
            ButterKnife.bind(this, view);
        }

        @OnCheckedChanged(R.id.checkbox)
        public void onItemCheckChanged(boolean checked){
            listView.onEpisodeCheckChanged(getAdapterPosition(), checked);
        }
    }
}
