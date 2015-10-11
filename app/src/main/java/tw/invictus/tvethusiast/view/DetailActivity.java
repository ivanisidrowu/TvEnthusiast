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

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.greenrobot.event.EventBus;
import tw.invictus.tvethusiast.R;
import tw.invictus.tvethusiast.databinding.ActivityDetailBinding;
import tw.invictus.tvethusiast.model.TvShow;
import tw.invictus.tvethusiast.view.event.CardClickEvent;
import tw.invictus.tvethusiast.viewmodel.DetailViewModel;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        EventBus.getDefault().registerSticky(this);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @SuppressWarnings("unused")
    public void onEvent(CardClickEvent event){
        TvShow show = event.getTvShow();
        DetailViewModel viewModel = new DetailViewModel(this, show);
        binding.setViewModel(viewModel);
        binding.collapsingToolbar.setTitle(show.getName());
    }

}
