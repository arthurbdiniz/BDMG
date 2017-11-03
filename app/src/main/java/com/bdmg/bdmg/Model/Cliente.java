package com.bdmg.bdmg.Model;


import java.io.Serializable;

public class Cliente implements Serializable {

    public String nome;
    public String cpf;
    public String email;
    public String ddd;
    public String telephone;
    public String integratorId;


    public Dimensionamento dimensionamento = null;


    public Cliente(String nome, String cpf, String email, String ddd, String telephone, String integratorId, Dimensionamento dimensionamento) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.ddd = ddd;
        this.telephone = telephone;
        this.integratorId = integratorId;
        this.dimensionamento = dimensionamento;

    }

    public Cliente(String nome, String email, String ddd, String telephone, String integratorId) {
        this.nome = nome;
        this.email = email;
        this.ddd = ddd;
        this.telephone = telephone;
        this.integratorId = integratorId;
    }

    public String getNome() {
        return nome;
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
