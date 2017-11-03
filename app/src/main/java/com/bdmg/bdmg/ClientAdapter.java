package com.bdmg.bdmg;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bdmg.bdmg.Model.Cliente;
import com.google.firebase.storage.FirebaseStorage;
import java.util.ArrayList;


public class ClientAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private FirebaseStorage storage = FirebaseStorage.getInstance();

    private ArrayList<Cliente> tickets;
    private ArrayList<Cliente> tempTicketsText;
    private ArrayList<Cliente> tempTicketsCategory;
    private ArrayList<Cliente> tempTicketsLocation;

    private ArrayList<Cliente> filteredTickets;
    private ArrayList<Cliente> tempList;

    private RecyclerView recyclerView;
    private Context context;
    private ClientViewHolder ticketViewHolder;
    private boolean imageTask = false;

    public ClientAdapter(ArrayList<Cliente> tickets, Context context, RecyclerView recyclerView) {
        this.tickets = tickets;
        this.filteredTickets = tickets;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.client_item, parent, false);

        ClientViewHolder ticketViewHolder = new ClientViewHolder(view);
        view.setOnClickListener(this);

        return ticketViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ticketViewHolder = (ClientViewHolder) holder;

        final Cliente client  = filteredTickets.get(position) ;

        ticketViewHolder.name.setText(client.getNome());
        ticketViewHolder.email.setText(client.getEmail());
        ticketViewHolder.ddd.setText(client.getDdd());
        ticketViewHolder.telephone.setText(client.getTelephone());
        ticketViewHolder.dateCreation.setText(client.getDateCreation());

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return filteredTickets.size();
    }

    @Override
    public void onClick(View v) {
        int itemPosition = recyclerView.getChildLayoutPosition(v);
        Cliente cliente = filteredTickets.get(itemPosition);

        Intent goTicket = new Intent(context, ClientViewActivity.class);
        goTicket.putExtra("Client", cliente);
        goTicket.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(goTicket);


    }



}

