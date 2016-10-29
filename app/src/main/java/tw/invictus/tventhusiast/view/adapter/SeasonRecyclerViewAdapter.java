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
import butterknife.OnClick;
import tw.invictus.tventhusiast.R;
import tw.invictus.tventhusiast.model.pojo.Season;
import tw.invictus.tventhusiast.util.ColorUtil;
import tw.invictus.tventhusiast.view.SeasonListView;


/**
 * Created by ivan on 9/20/15.
 */
public class SeasonRecyclerViewAdapter extends RecyclerView.Adapter<SeasonRecyclerViewAdapter.ViewHolder> {

    private List<Season> seasons;
    private SeasonListView view;
    private int lightColor;
    private boolean isShowInDb;

    public SeasonRecyclerViewAdapter(List<Season> seasons, SeasonListView view, int lightColor, boolean isShowInDb) {
        this.seasons = seasons;
        this.view = view;
        this.lightColor = lightColor;
        this.isShowInDb = isShowInDb;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.season_list_item, parent, false);
        return new ViewHolder(root, view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Season season = seasons.get(position);
        String seasonNum = holder.itemName.getContext().getString(R.string.season, season.getSeasonNumber());
        holder.itemName.setText(seasonNum);

        boolean isClosedToWhite = ColorUtil.isColorCloserToWhite(lightColor);

        if (isClosedToWhite){
            holder.itemName.setTextColor(Color.BLACK);
        }else{
            holder.itemName.setTextColor(Color.WHITE);
        }

        if (isShowInDb){
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(season.isWatched());
        }else{
            holder.checkBox.setVisibility(View.GONE);
        }

        holder.cardView.setCardBackgroundColor(lightColor);
        holder.cardView.setRadius(holder.cardRadius);
    }

    @Override
    public int getItemCount() {
        return seasons.size();
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

        private SeasonListView seasonListView;

        public ViewHolder(View view, SeasonListView seasonListView) {
            super(view);
            this.seasonListView = seasonListView;
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.card_view)
        public void onItemClicked(View view){
            seasonListView.onSeasonClicked(getAdapterPosition());
        }

        @OnCheckedChanged(R.id.checkbox)
        public void onItemChecked(boolean checked){
            seasonListView.onSeasonCheckChanged(getAdapterPosition(), checked);
        }
    }
}
