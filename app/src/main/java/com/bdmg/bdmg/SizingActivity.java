package com.bdmg.bdmg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.bdmg.bdmg.Model.Dimensionamento;

public class SizingActivity extends AppCompatActivity {


    private CardView cardFinanciamento;
    private CardView cardGrafico;
    private Button continueButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sizing);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Dimensionamento");

        cardFinanciamento = (CardView) findViewById(R.id.card_financiamento);
        cardGrafico = (CardView) findViewById(R.id.card_grafico);
        continueButton = (Button) findViewById(R.id.continue_btn);


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dimensionamento dimensionamento = new Dimensionamento(124, 12);
                cardFinanciamento.setVisibility(View.VISIBLE);
                cardGrafico.setVisibility(View.VISIBLE);

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
