package tw.invictus.tvethusiast.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tw.invictus.tvethusiast.R;
import tw.invictus.tvethusiast.viewmodel.MyTvShowsViewModel;

/**
 * Created by ivan on 10/23/15.
 */
public class MyTvShowsFragment extends Fragment {

    private MyTvShowsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.fragment_list, container, false);
        viewModel = new MyTvShowsViewModel(getActivity());
        viewModel.setRecyclerView(recyclerView);
        return recyclerView;
    }
}
