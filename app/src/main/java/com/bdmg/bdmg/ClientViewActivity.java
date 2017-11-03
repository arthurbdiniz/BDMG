package com.bdmg.bdmg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bdmg.bdmg.Model.Cliente;

public class ClientViewActivity extends AppCompatActivity {

    private Cliente client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        client = (Cliente) intent.getSerializableExtra("Client");

        setTitle(client.getNome());
    }
}
