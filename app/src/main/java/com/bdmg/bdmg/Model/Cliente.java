package com.bdmg.bdmg.Model;


import android.os.Build;

import java.io.Serializable;

public class Cliente implements Serializable {

    public String nome;
    public String cpf;
    public String email;
    public String ddd;
    public String telephone;
    public String integratorId;
    public String clienteId;
    public String dateCreation;



    public Dimensionamento dimensionamento = null;
    public Building building = null;


    public Cliente(String nome, String cpf, String email, String ddd, String telephone,String clienteId, String integratorId,
                   String dateCreation,
                   Dimensionamento dimensionamento,
                   Building building) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.ddd = ddd;
        this.telephone = telephone;
        this.integratorId = integratorId;
        this.dateCreation = dateCreation;
        this.dimensionamento = dimensionamento;
        this.building = building;
        this.clienteId = clienteId;

    }

    public Cliente(String nome, String email, String ddd, String telephone, String clienteId, String integratorId, String dateCreation) {
        this.nome = nome;
        this.email = email;
        this.ddd = ddd;
        this.telephone = telephone;
        this.integratorId = integratorId;
        this.dateCreation = dateCreation;
        this.clienteId = clienteId;
    }



    public Cliente(String nome, String email, String ddd, String telephone, String clienteId, String integratorId, String dateCreation, Dimensionamento dimensionamento) {
        this.nome = nome;
        this.email = email;
        this.ddd = ddd;
        this.telephone = telephone;
        this.integratorId = integratorId;
        this.dateCreation = dateCreation;
        this.clienteId = clienteId;
        this.dimensionamento =dimensionamento;
    }


    public String getNome() {
        return nome;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getDdd() {
        return ddd;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getIntegratorId() {
        return integratorId;
    }
}
