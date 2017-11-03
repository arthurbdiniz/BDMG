package com.bdmg.bdmg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bdmg.bdmg.Model.Dimensionamento;

public class NewClientActivity extends AppCompatActivity {

    private Dimensionamento dimensionamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);

        dimensionamento = new Dimensionamento(1000, 60);


    }
}
