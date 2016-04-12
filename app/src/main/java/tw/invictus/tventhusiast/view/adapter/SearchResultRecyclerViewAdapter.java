package tw.invictus.tventhusiast.view.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tw.invictus.tventhusiast.R;
import tw.invictus.tventhusiast.model.pojo.TvShow;
import tw.invictus.tventhusiast.view.MainActivityView;

/**
 * Created by ivan on 2/27/16.
 */
public class SearchResultRecyclerViewAdapter extends RecyclerView.Adapter<SearchResultRecyclerViewAdapter.ViewHolder>{

    private List<TvShow> shows;
    private MainActivityView view;

    public SearchResultRecyclerViewAdapter(List<TvShow> shows, MainActivityView view) {
        this.shows = shows;
        this.view = view;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_item, parent, false);
        return new ViewHolder(root, view, shows);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(shows.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.trailer_text)
        TextView text;
        @Bind(R.id.card_view)
        CardView cardView;
        @BindColor(R.color.colorPrimaryDark)
        int darkColor;

        private MainActivityView mainActivityView;
        private List<TvShow> shows;

        public ViewHolder(View itemView, MainActivityView mainActivityView, List<TvShow> shows) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cardView.setBackgroundColor(darkColor);
            this.mainActivityView = mainActivityView;
            this.shows = shows;
        }

        @OnClick(R.id.card_view)
        public void onClicked(View view){
            mainActivityView.onSearchResultClicked(shows.get(getAdapterPosition()));
        }
    }
}
