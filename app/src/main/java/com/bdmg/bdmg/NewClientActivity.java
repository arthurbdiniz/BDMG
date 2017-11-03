package com.bdmg.bdmg;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.bdmg.bdmg.Model.Cliente;
import com.bdmg.bdmg.Model.Dimensionamento;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewClientActivity extends AppCompatActivity {

    private EditText clientName;
    private EditText clientCpf;
    private EditText clientEmail;
    private EditText clientDDD;
    private EditText clientTelephone;

    private EditText clientBill;
    private EditText clientDeadline;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String ticketId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);

        setTitle("Novo cliente");

        clientName = (EditText) findViewById(R.id.client_name);
        clientCpf = (EditText) findViewById(R.id.client_cpf);
        clientEmail = (EditText) findViewById(R.id.client_email);
        clientDDD = (EditText) findViewById(R.id.client_telephone_ddd);
        clientTelephone = (EditText) findViewById(R.id.client_telephone_number);

        clientBill = (EditText) findViewById(R.id.client_bill);
        clientDeadline = (EditText) findViewById(R.id.client_deadline);

        clientName.setText("arthur");
        clientCpf.setText("123123");
        clientEmail.setText("arthur@gmail.com");
        clientDDD.setText("132");
        clientTelephone.setText("123132");
        clientBill.setText("1233");
        clientDeadline.setText("12");

        initFirebase();




    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_new_client, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done:

                //verifys and send to firebase
                //startActivity(new Intent(this, About.class));

                Dimensionamento dimensionamento = new Dimensionamento(Double.parseDouble(clientBill.getText().toString()) , Integer.parseInt(clientDeadline.getText().toString()));
                final FirebaseUser integrator = FirebaseAuth.getInstance().getCurrentUser();

                Cliente client = new Cliente(clientName.getText().toString(),
                                                clientCpf.getText().toString(),
                                                clientEmail.getText().toString(),
                                                clientDDD.getText().toString(),
                                                clientTelephone.getText().toString(),
                                                integrator.getUid(),
                                                dimensionamento
                                                );

                if (TextUtils.isEmpty(ticketId)) {
                    ticketId = mFirebaseDatabase.push().getKey();
                }
                mFirebaseDatabase.child(ticketId).setValue(client);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void initFirebase(){


        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("clients");
        //mFirebaseInstance.getReference("bdmg-cacef");
        // app_title change listener
//        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                //Log.e(TAG, "App title updated");
//
//                //String appTitle = dataSnapshot.getValue(String.class);
//
//                // update toolbar title
//                //getSupportActionBar().setTitle(appTitle);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                //  Log.e(TAG, "Failed to read app title value.", error.toException());
//            }
//        });
    }
}
