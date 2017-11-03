package com.bdmg.bdmg;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ClientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener    {

    final TextView name;
    final TextView email;
    final TextView ddd;
    final TextView telephone;
    final TextView dateCreation;


    public ClientViewHolder(View view) {
        super(view);
        view.setOnClickListener(this);
        name = (TextView) view.findViewById(R.id.client_name);
        email = (TextView) view.findViewById(R.id.client_email);
        ddd = (TextView) view.findViewById(R.id.client_ddd);
        telephone = (TextView) view.findViewById(R.id.client_telephone);
        dateCreation = (TextView) view.findViewById(R.id.client_date_creation);
    }
    @Override
    public void onClick(View view) {

    }


}
