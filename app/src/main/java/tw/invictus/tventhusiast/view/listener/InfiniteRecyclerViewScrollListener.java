package tw.invictus.tventhusiast.view.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by ivan.wu on 12/31/2015.
 */
public abstract class InfiniteRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private int visibleThreshold = 5;
    private int currentPage = 0;
    private int previousTotalItemCount = 0;
    private boolean loading = true;
    private int startingPageIndex = 0;

    private LinearLayoutManager mLinearLayoutManager;

    public InfiniteRecyclerViewScrollListener(LinearLayoutManager layoutManager) {
        this.mLinearLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        int firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int visibleItemCount = view.getChildCount();
        int totalItemCount = mLinearLayoutManager.getItemCount();

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }
        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            currentPage++;
            onLoadMore(currentPage, totalItemCount);
            loading = true;
        }
    }

    public abstract void onLoadMore(int page, int totalItemsCount);

}