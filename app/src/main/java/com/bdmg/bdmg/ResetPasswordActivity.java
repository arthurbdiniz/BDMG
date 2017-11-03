package com.bdmg.bdmg;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputEmail;
    private Button btnReset, btnBack;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        auth = FirebaseAuth.getInstance(); /** Inits Firebase Instance **/

        initToolbar();
        initView();
        setClickListeners();

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

/*
* The following code example shows setting an AutocompleteFilter on a PlaceAutocompleteFragment to
* set a filter returning only results with a precise address.
*/
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .build();
        autocompleteFragment.setFilter(typeFilter);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("", "Place: " + place.getName());//get place details here
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("", "An error occurred: " + status);
            }
        });
    }

    public void initToolbar(){

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void initView(){
        inputEmail = (EditText) findViewById(R.id.email);
        btnReset = (Button) findViewById(R.id.btn_reset_password);
        //btnBack = (Button) findViewById(R.id.btn_back);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void setClickListeners(){
        btnBack.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }

    public void reset(){
        String email = inputEmail.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            inputEmail.setError(getString(R.string.registered_email));
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ResetPasswordActivity.this, getString(R.string.send_instructions), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ResetPasswordActivity.this, getString(R.string.send_failed), Toast.LENGTH_SHORT).show();
                        }

                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;

            case R.id.btn_reset_password:
                reset();
                break;

        }
    }
}
