package com.bdmg.bdmg;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.bdmg.bdmg.Model.Cliente;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    final ArrayList<Cliente> clients = new ArrayList<Cliente>();
    final   ArrayList<Cliente> userClients = new ArrayList<Cliente>();

    private FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private ClientAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private FloatingActionButton floatingButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initRecyclerView();
        initFloatingButton();

        setTitle("Clientes");


        mAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("clients");
//      DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("tickets");

        progressBar.setVisibility(View.VISIBLE);
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);
                clients.clear();
                userClients.clear();
                for (DataSnapshot clientData : dataSnapshot.getChildren()) {
                    //player.child("title").getValue();
                    //Log.i("player", player.getKey());
                    //friends.add(mDatabase.getKey());

                    Cliente client = new Cliente(   clientData.child("nome").getValue().toString(),
                                                    clientData.child("email").getValue().toString(),
                                                    clientData.child("ddd").getValue().toString(),
                                                    clientData.child("telephone").getValue().toString(),
                                                    clientData.child("integratorId").getValue().toString());



                    //ticket.setTicketId(player.getKey());
                    Log.d("1", "1");
                    clients.add(client);
                    final FirebaseUser integrator = FirebaseAuth.getInstance().getCurrentUser();
                    if(client.integratorId.equals(integrator.getUid())){
                        userClients.add(client);
                    }

                }

                adapter = new ClientAdapter(clients ,getApplicationContext(), recyclerView);

                recyclerView.setAdapter(adapter);
                RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext(),
                        LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layout);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_new_client) {
            startActivity(new  Intent(getApplicationContext(), NewClientActivity.class));
        } else if (id == R.id.nav_clients) {

        } else if (id == R.id.nav_financing) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_exit) {
            mAuth.signOut();
            startActivity(new  Intent(getApplicationContext(), LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void initFloatingButton(){
        floatingButton = (FloatingActionButton) findViewById(R.id.fab);

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goNavigation = new  Intent(getApplicationContext(), NewClientActivity.class);
                startActivity(goNavigation);

            }
        });
    }

    private void initRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
//        recyclerView.setOnScrollListener(new HidingScrollListener() {
//            @Override
//            public void onHide() {
//                hideViews();
//            }
//            @Override
//            public void onShow() {
//                showViews();
//            }
//        });
    }

//    private void hideViews() {
//        appBarLayout.animate().translationY(-appBarLayout.getHeight()).setInterpolator(new AccelerateInterpolator(2));
//        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) createTicketFloatingButton.getLayoutParams();
//        int fabBottomMargin = lp.bottomMargin;
//        createTicketFloatingButton.animate().translationY(createTicketFloatingButton.getHeight()+fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
//    }
//
//    private void showViews() {
//        appBarLayout.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
//        createTicketFloatingButton.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
//    }
}
