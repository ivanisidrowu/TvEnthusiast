package tw.invictus.tventhusiast.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tw.invictus.tventhusiast.R;
import tw.invictus.tventhusiast.model.pojo.Video;
import tw.invictus.tventhusiast.view.DetailView;

/**
 * Created by ivan on 1/8/16.
 */
public class DetailVideoRecyclerViewAdapter extends RecyclerView.Adapter<DetailVideoRecyclerViewAdapter.ViewHolder> {

    private List<Video> videos;
    private DetailView detailView;

    public DetailVideoRecyclerViewAdapter(List<Video> videos, DetailView detailView) {
        this.videos = videos;
        this.detailView = detailView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_list_item, parent, false);
        return new ViewHolder(root, videos);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.trailerTitle.setText(videos.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.trailer_text)
        TextView trailerTitle;

        private List<Video> videos;

        public ViewHolder(View itemView, List<Video> videos) {
            super(itemView);
            this.videos = videos;
            ButterKnife.bind(this, itemView);
        }

    }
}
