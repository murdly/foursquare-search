package com.akarbowy.foursquaresearch.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.akarbowy.foursquaresearch.App;
import com.akarbowy.foursquaresearch.R;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {

    @Inject
    SearchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        DaggerSearchActivityComponent.builder()
                .appComponent(((App) getApplication()).getComponent())
                .searchActivityModule(new SearchActivityModule(this))
                .build();
    }
}
