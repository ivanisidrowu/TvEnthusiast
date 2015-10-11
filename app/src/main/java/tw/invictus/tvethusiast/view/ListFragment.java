/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tw.invictus.tvethusiast.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import tw.invictus.tvethusiast.R;
import tw.invictus.tvethusiast.view.adapter.RecyclerViewAdapter;
import tw.invictus.tvethusiast.model.api.TmdbService;
import tw.invictus.tvethusiast.model.DiscoverTvShowResponse;
import tw.invictus.tvethusiast.model.TvShow;
import tw.invictus.tvethusiast.view.util.PropertyConfig;

public class ListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.fragment_list, container, false);
        setupRecyclerView(rv);
        return rv;
    }

    private void setupRecyclerView(final RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
        getTvSeries(recyclerView);
    }

    private void getTvSeries(final RecyclerView recyclerView){
        try {
            PropertyConfig config = new PropertyConfig(getActivity());
            String url = config.getProperty("api.endpoint");
            String apiKey = config.getProperty("api.key");
            Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
            TmdbService service = retrofit.create(TmdbService.class);
            service.discoverTvShows(apiKey, "popularity.desc").enqueue(new Callback<DiscoverTvShowResponse>() {
                @Override
                public void onResponse(Response<DiscoverTvShowResponse> response) {
                    List<TvShow> tvShows = response.body().getTvShows();
                    recyclerView.setAdapter(new RecyclerViewAdapter(getActivity(), tvShows));
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
