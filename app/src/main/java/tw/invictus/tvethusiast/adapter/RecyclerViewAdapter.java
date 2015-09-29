package tw.invictus.tvethusiast.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import tw.invictus.tvethusiast.R;
import tw.invictus.tvethusiast.activity.DetailActivity;
import tw.invictus.tvethusiast.event.CardClickEvent;
import tw.invictus.tvethusiast.model.TvShow;
import tw.invictus.tvethusiast.util.PropertyConfig;

/**
 * Created by ivan on 9/20/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<TvShow> mValues;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public String mBoundString;

        public final View mView;
        @Bind(R.id.avatar) ImageView mImageView;
        @Bind(android.R.id.text1) TextView mTextView;
        @Bind(R.id.cv) CardView mCardView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = mCardView;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView.getText();
        }
    }

    public RecyclerViewAdapter(Context context, List<TvShow> items) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mBoundString = mValues.get(position).getName();
        holder.mTextView.setText(mValues.get(position).getName());
        String imgUrl = PropertyConfig.IMG_BASE + mValues.get(position).getPosterPath();
        Context context = holder.mImageView.getContext();

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                CardClickEvent event = new CardClickEvent(mValues.get(position));
                EventBus.getDefault().postSticky(event);
                context.startActivity(intent);
            }
        });

        Picasso.with(context).load(imgUrl).fit().centerCrop().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
