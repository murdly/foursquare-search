package com.akarbowy.foursquaresearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.akarbowy.foursquaresearch.ui.SearchActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.start_button)
    public void showTestScreen() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}