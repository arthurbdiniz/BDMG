package com.bdmg.bdmg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SizingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sizing);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Dimensionamento");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
