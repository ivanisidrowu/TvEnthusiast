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
import tw.invictus.tventhusiast.model.pojo.Review;
import tw.invictus.tventhusiast.view.DetailView;

/**
 * Created by ivan on 1/8/16.
 */
public class DetailReviewRecyclerViewAdapter extends RecyclerView.Adapter<DetailReviewRecyclerViewAdapter.ViewHolder> {

    private List<Review> reviews;
    private DetailView detailView;

    public DetailReviewRecyclerViewAdapter(List<Review> reviews, DetailView detailView) {
        this.reviews = reviews;
        this.detailView = detailView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_list_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.authorName.setText(reviews.get(position).getAuthor());
        holder.reviewText.setText(reviews.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.author)
        TextView authorName;

        @Bind(R.id.review_text)
        TextView reviewText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
