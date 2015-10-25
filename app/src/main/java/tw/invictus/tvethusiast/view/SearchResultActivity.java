package tw.invictus.tvethusiast.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.greenrobot.event.EventBus;
import tw.invictus.tvethusiast.R;
import tw.invictus.tvethusiast.databinding.ActivitySearchResultBinding;
import tw.invictus.tvethusiast.model.TvShow;
import tw.invictus.tvethusiast.view.event.CardClickEvent;
import tw.invictus.tvethusiast.view.event.SearchTvShowEvent;
import tw.invictus.tvethusiast.viewmodel.DetailViewModel;
import tw.invictus.tvethusiast.viewmodel.SearchResultViewModel;

/**
 * Created by ivan on 10/25/15.
 */
public class SearchResultActivity extends AppCompatActivity{

    private ActivitySearchResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_result);
        EventBus.getDefault().registerSticky(this);
        String title = getResources().getString(R.string.result);
        binding.toolbar.setTitle(title);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @SuppressWarnings("unused")
    public void onEvent(SearchTvShowEvent event){
        SearchResultViewModel viewModel = new SearchResultViewModel(this);
        viewModel.setRecyclerView(binding.recyclerview);
        binding.setViewModel(viewModel);
        viewModel.viewSearchResults(event.getQuery());
    }
}
