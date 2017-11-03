package com.bdmg.bdmg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bdmg.bdmg.Model.Dimensionamento;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class SizingActivity extends AppCompatActivity {


    private CardView cardFinanciamento;
    private CardView cardGrafico;
    private Button continueButton;

    //Financiamento
    private TextView parcelaFinanciamento;
    private TextView entrada;
    private TextView valorFinanciado;
    private TextView custoTotal;
    private TextView taxaJuros;
    private TextView numeroParcelas;

    //Gráfico
    GraphView graph;


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

        parcelaFinanciamento = (TextView) findViewById(R.id.parcela_financiamento);
        entrada = (TextView) findViewById(R.id.entrada);
        valorFinanciado = (TextView) findViewById(R.id.valor_financiamento);
        custoTotal = (TextView) findViewById(R.id.custo_total);
        taxaJuros = (TextView) findViewById(R.id.taxa_juros);
        numeroParcelas = (TextView) findViewById(R.id.numero_parcelas);




        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dimensionamento dimensionamento = new Dimensionamento(124, 12);
                cardFinanciamento.setVisibility(View.VISIBLE);
                cardGrafico.setVisibility(View.VISIBLE);

                parcelaFinanciamento.setText(String.valueOf(dimensionamento.ParcelaFinanciamento));
                entrada.setText(String.valueOf(dimensionamento.Entrada));
                valorFinanciado.setText(String.valueOf(dimensionamento.ValorFinanciado));
                custoTotal.setText(String.valueOf(dimensionamento.CustoTotal));
                taxaJuros.setText(String.valueOf(dimensionamento.TaxaMes));
                numeroParcelas.setText(String.valueOf(dimensionamento.Prazo));


            }
        });

        graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {

                new DataPoint(0, 8),
                new DataPoint(1, 7),
                new DataPoint(2, 6),
                new DataPoint(3, 5),
                new DataPoint(4, 5),
                new DataPoint(5, 5),
                new DataPoint(6, 5),
                new DataPoint(7, 5),
                new DataPoint(8, 5)

        });
        graph.addSeries(series);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
