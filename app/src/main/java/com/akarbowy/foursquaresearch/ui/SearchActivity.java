package com.akarbowy.foursquaresearch.ui;

import android.os.Bundle;
import android.os.Parcel;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akarbowy.foursquaresearch.App;
import com.akarbowy.foursquaresearch.R;
import com.akarbowy.foursquaresearch.data.model.VenueItem;
import com.akarbowy.foursquaresearch.utils.ConnectionUtil;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {

    @BindView(R.id.search_view)
    FloatingSearchView searchView;
    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.empty_state_view)
    View view;

    @Inject
    SearchPresenter presenter;

    private VenuesAdapter adapter;
    private boolean isViewActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        DaggerSearchActivityComponent.builder()
                .appComponent(((App) getApplication()).getComponent())
                .searchActivityModule(new SearchActivityModule(this))
                .build()
                .inject(this);

        setupList();
        setupSearchBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isViewActive = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isViewActive = false;

        searchView.clearSearchFocus();
    }

    private void setupList() {
        adapter = new VenuesAdapter();
        list.addItemDecoration(new DividerItemDecoration(list.getContext(),
                DividerItemDecoration.VERTICAL));
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
    }

    private void setupSearchBar() {
        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                if (newQuery.isEmpty()) {
                    searchView.clearSuggestions();
                    return;
                }

                SearchSuggestion suggestion = new SearchSuggestion() {
                    @Override public String getBody() {
                        return newQuery;
                    }

                    @Override public int describeContents() {
                        return 0;
                    }

                    @Override public void writeToParcel(Parcel dest, int flags) {

                    }
                };

                searchView.swapSuggestions(Collections.singletonList(suggestion));
            }
        });

        searchView.setOnBindSuggestionCallback(new SearchSuggestionsAdapter.OnBindSuggestionCallback() {
            @Override public void onBindSuggestion(View suggestionView, ImageView leftIcon, TextView textView, SearchSuggestion item, int itemPosition) {
                String text = String.format("%s %s", getString(R.string.search_suggestion), item.getBody());
                textView.setText(text);
            }
        });

        searchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                onSearchQuery(searchSuggestion.getBody());
            }

            @Override public void onSearchAction(final String currentQuery) {
                if(!currentQuery.isEmpty()){
                    onSearchQuery(currentQuery);
                }
            }
        });
    }

    void onSearchQuery(String query) {
        searchView.clearSearchFocus();
        searchView.clearSuggestions();

        if(ConnectionUtil.isNetworkAvailable(this)){
            presenter.search(query);
        } else {
            Toast.makeText(this, getString(R.string.error_no_internet_connection), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean isActive() {
        return isViewActive;
    }

    @Override
    public void setVenues(List<VenueItem> venues) {
        searchView.hideProgress();

        adapter.addItems(venues);
    }

    @Override
    public void setLoading(boolean isLoading) {
        if (isLoading) {
            searchView.showProgress();
        } else {
            searchView.hideProgress();
        }
    }

    @Override public void setEmptyState(boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override public void showError() {
        Snackbar.make(list, getString(R.string.error_general), Snackbar.LENGTH_SHORT).show();
    }
}
