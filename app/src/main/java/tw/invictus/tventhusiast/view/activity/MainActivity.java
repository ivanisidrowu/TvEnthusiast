package tw.invictus.tventhusiast.view.activity;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import io.fabric.sdk.android.Fabric;
import tw.invictus.tventhusiast.R;
import tw.invictus.tventhusiast.TvShowApplication;
import tw.invictus.tventhusiast.model.api.RestfulApi;
import tw.invictus.tventhusiast.model.pojo.TvShow;
import tw.invictus.tventhusiast.presenter.MainActivityPresenter;
import tw.invictus.tventhusiast.presenter.MainFragmentPresenter;
import tw.invictus.tventhusiast.service.DailyUpdateServiceHelper;
import tw.invictus.tventhusiast.view.MainActivityView;
import tw.invictus.tventhusiast.view.adapter.SearchResultRecyclerViewAdapter;
import tw.invictus.tventhusiast.view.event.MenuItemClickEvent;
import tw.invictus.tventhusiast.view.event.TvShowShareEvent;
import tw.invictus.tventhusiast.view.event.TvShowClickEvent;
import tw.invictus.tventhusiast.view.fragment.DetailFragment;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, SearchView.OnQueryTextListener, MainActivityView, MenuItemCompat.OnActionExpandListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    RestfulApi api;
    @Inject
    MainActivityPresenter presenter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Nullable
    @Bind(R.id.search_recyclerview)
    RecyclerView searchRecyclerView;
    @Nullable
    @Bind(R.id.detail_container)
    FrameLayout detailContainer;

    private boolean mTwoPane;
    private Spinner spinner;
    private List<String> spinnerItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((TvShowApplication) getApplication()).getMainActivityComponent(this).inject(this);
        initPresenter();
        setSupportActionBar(toolbar);
        initSpinner();

        if (detailContainer != null) {
            mTwoPane = true;

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.detail_container, new DetailFragment(), DetailFragment.TAG)
                        .commit();
            }

        } else {
            mTwoPane = false;
        }

        DailyUpdateServiceHelper.setService(this);
    }

    private void initPresenter(){
        presenter.setApi(api);
        presenter.setView(this);
    }

    private void initSpinner(){
        View spinnerContainer = LayoutInflater.from(this).inflate(R.layout.toolbar_spinner,
                toolbar, false);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        toolbar.addView(spinnerContainer, layoutParams);

        SpinnerAdapter spinnerAdapter = new SpinnerAdapter();
        spinnerItems.add(getString(R.string.sort_by_poularity));
        spinnerItems.add(getString(R.string.sort_by_rate));
        spinnerItems.add(getString(R.string.favorites));
        spinnerAdapter.addItems(spinnerItems);

        spinner = (Spinner) spinnerContainer.findViewById(R.id.toolbar_spinner);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.search);
        MenuItemCompat.setOnActionExpandListener(searchMenuItem, this);
        SearchView searchView = (SearchView) MenuItemCompat
                .getActionView(searchMenuItem);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.share:
                EventBus.getDefault().post(new TvShowShareEvent());
                break;
            case R.id.about:
                showAboutDialog();
                break;
//            case R.id.settings:
//                startActivity(new Intent(this, SettingsActivity.class));
//                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean ismTwoPane() {
        return mTwoPane;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        closeSearchList();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.length() > 0 && newText.length() % 3 == 0){
            presenter.searchTvShow(newText);
        }else if(newText.length() == 0){
            closeSearchList();
        }
        return false;
    }

    @Override
    public void onTvShowSearched(List<TvShow> shows) {
        searchRecyclerView.setVisibility(View.VISIBLE);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchRecyclerView.setAdapter(new SearchResultRecyclerViewAdapter(shows, this));
    }

    @Override
    public void onSearchResultClicked(TvShow show) {
        closeSearchList();
        EventBus.getDefault().postSticky(new TvShowClickEvent(show, mTwoPane));
        if(mTwoPane){
            DetailFragment fragment = new DetailFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_container, fragment, DetailFragment.TAG)
                    .commit();
        }else{
            Intent intent = new Intent(this, DetailActivity.class);
            startActivity(intent);
        }
    }

    private void closeSearchList(){
        searchRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        closeSearchList();
        return true;
    }

    private void showAboutDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_about, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.create().show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case MainFragmentPresenter.SORT_BY_POPULARITY:
                EventBus.getDefault().post(new MenuItemClickEvent(MainFragmentPresenter.SORT_BY_POPULARITY));
                break;
            case MainFragmentPresenter.SORT_BY_TOPRATED:
                EventBus.getDefault().post(new MenuItemClickEvent(MainFragmentPresenter.SORT_BY_TOPRATED));
                break;
            case MainFragmentPresenter.LIST_MY_FAVORITES:
                EventBus.getDefault().post(new MenuItemClickEvent(MainFragmentPresenter.LIST_MY_FAVORITES));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class SpinnerAdapter extends BaseAdapter {
        private final String dropDownTag = "DROPDOWN";
        private final String nonDropDownTag = "NON_DROPDOWM";
        private List<String> mItems = new ArrayList<>();

        public void addItems(List<String> yourObjectList) {
            mItems.addAll(yourObjectList);
        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getDropDownView(int position, View view, ViewGroup parent) {
            if (view == null || !view.getTag().toString().equals(dropDownTag)) {
                view = getLayoutInflater().inflate(R.layout.toolbar_spinner_item_dropdown, parent, false);
                view.setTag(dropDownTag);
            }

            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(getTitle(position));

            return view;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null || !view.getTag().toString().equals(nonDropDownTag)) {
                view = getLayoutInflater().inflate(R.layout.
                        toolbar_spinner_item_actionbar, parent, false);
                view.setTag(nonDropDownTag);
            }
            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(getTitle(position));
            return view;
        }

        private String getTitle(int position) {
            return position >= 0 && position < mItems.size() ? mItems.get(position) : "";
        }
    }
}
