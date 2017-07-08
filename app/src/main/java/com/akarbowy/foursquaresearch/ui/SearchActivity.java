package com.akarbowy.foursquaresearch.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.akarbowy.foursquaresearch.App;
import com.akarbowy.foursquaresearch.R;
import com.akarbowy.foursquaresearch.network.model.VenueItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {

    @BindView(R.id.list)
    RecyclerView list;

    @Inject
    SearchPresenter presenter;

    VenuesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        adapter = new VenuesAdapter();
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        DaggerSearchActivityComponent.builder()
                .appComponent(((App) getApplication()).getComponent())
                .searchActivityModule(new SearchActivityModule(this))
                .build()
                .inject(this);

        presenter.search("the");
    }

    @Override public void setVenues(List<VenueItem> venues) {
        adapter.addItems(venues);
    }
}
