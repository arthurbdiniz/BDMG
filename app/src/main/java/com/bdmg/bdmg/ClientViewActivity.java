package com.bdmg.bdmg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bdmg.bdmg.Model.Cliente;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ClientViewActivity extends AppCompatActivity {

    private Cliente client;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private DatabaseReference clientObj;

    //Cliente
    private TextView clientName;
    private TextView clientCpf;
    private TextView clientEmail;
    private TextView clientDDD;
    private TextView clientTelephone;

    //Financiamento
    private TextView parcelaFinanciamento;
    private TextView entrada;
    private TextView valorFinanciado;
    private TextView custoTotal;
    private TextView taxaJuros;
    private TextView numeroParcelas;

    //Gráfico
    GraphView graph;
    private int numeroParcel = 0;


    private Button buttonDeleteClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        client = (Cliente) intent.getSerializableExtra("Client");
        initViews();

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("clients");
        clientObj = FirebaseDatabase.getInstance().getReference().child("clients").child(client.clienteId);

        buttonDeleteClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                //deleteTicket();

            }
        });

        clientObj.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.d("TAG", snapshot.getValue().toString());

                //Colocar informações mais interessantes na tela
                clientName.setText(snapshot.child("nome").getValue().toString());
                clientEmail.setText(snapshot.child("email").getValue().toString());
                clientCpf.setText(snapshot.child("cpf").getValue().toString());
                clientDDD.setText(snapshot.child("ddd").getValue().toString());
                clientTelephone.setText(snapshot.child("telephone").getValue().toString());

                parcelaFinanciamento.setText(snapshot.child("dimensionamento").child("ParcelaFinanciamento").getValue().toString());
                entrada.setText(snapshot.child("dimensionamento").child("Entrada").getValue().toString());
                valorFinanciado.setText(snapshot.child("dimensionamento").child("ValorFinanciado").getValue().toString());
                custoTotal.setText(snapshot.child("dimensionamento").child("CustoTotal").getValue().toString());
                taxaJuros.setText(snapshot.child("dimensionamento").child("TaxaMes").getValue().toString());
                numeroParcelas.setText(snapshot.child("dimensionamento").child("Prazo").getValue().toString());
                numeroParcel = Integer.parseInt(snapshot.child("dimensionamento").child("Prazo").getValue().toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
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

        setTitle(client.getNome());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void initViews() {

        clientName = (TextView) findViewById(R.id.client_name);
        clientEmail = (TextView) findViewById(R.id.client_email);
        clientCpf = (TextView) findViewById(R.id.client_cpf);
        clientDDD = (TextView) findViewById(R.id.client_telephone_ddd);
        clientTelephone = (TextView) findViewById(R.id.client_telephone_number);

        parcelaFinanciamento = (TextView) findViewById(R.id.parcela_financiamento);
        entrada = (TextView) findViewById(R.id.entrada);
        valorFinanciado = (TextView) findViewById(R.id.valor_financiamento);
        custoTotal = (TextView) findViewById(R.id.custo_total);
        taxaJuros = (TextView) findViewById(R.id.taxa_juros);
        numeroParcelas = (TextView) findViewById(R.id.numero_parcelas);

        buttonDeleteClient = (Button) findViewById(R.id.delete_client_btn);

    }

    public void deleteTicket(){
        clientObj.removeValue();
    }
}
