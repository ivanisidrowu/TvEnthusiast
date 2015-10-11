package tw.invictus.tvethusiast.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import tw.invictus.tvethusiast.R;
import tw.invictus.tvethusiast.databinding.ListItemBinding;
import tw.invictus.tvethusiast.model.TvShow;
import tw.invictus.tvethusiast.viewmodel.ListItemViewModel;

/**
 * Created by ivan on 9/20/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final TypedValue mTypedValue = new TypedValue();
    private List<TvShow> mValues;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemBinding binding;

        public ViewHolder(ListItemBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }

        public void bindTvShow(TvShow show) {
            binding.setViewModel(new ListItemViewModel(binding.getRoot().getContext(), show));
        }
    }

    public RecyclerViewAdapter(Context context, List<TvShow> items) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item,
                parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bindTvShow(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
