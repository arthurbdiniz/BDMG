package com.bdmg.bdmg.Model;


import android.util.Log;

import java.io.Serializable;

public class Dimensionamento implements Serializable {

    public String TAG = "Dimensionamento";

    public double Tarifa = 0.73;
    public int ProdutividadeBruta = 1450;
    public int TaxaDisponibilidade = 100;
    public int ProdutividadeLiquidaAno = 1407;
    public int ProdutividadeLiquidaMes = 117;
    public int Perdas = 3;
    public int Custo = 0; //Reais por KW
    public double ContaFixa = 73.0;
    public int DiferencaPorcentagem = 18;
    public int TaxaAno = 21;

    public double ConsumoEstimado = 0; //ok
    public double TotalCompensado = 0; //ok
    public double CapacidadeSistema = 0; //ok
    public double InvestimentoTotal = 0; //ok
    public double CustoTotal = 0; //ok
    public double Entrada = 0; //ok
    public double ValorFinanciado = 0; //ok
    public double ParcelaFinanciamento = 0;
    public double DesembolsoMensal = 0;
    public double Diferenca = 0; //ok

    //Input
    public int Prazo = 0;
    public double ContaEnergia = 0;
    public double TaxaMes = 1.6;


    public Dimensionamento(double contaEnergia, int Prazo) {
        this.ContaEnergia = contaEnergia;
        this.Prazo = Prazo;


        calculaConsumoEstimado();
        calculaTotalCompensado();
        calculaCapacidadeSistema();

        calculaCusto();

        calculaInvestimentoTotal();
        calculaCustoTotal();
        calculaEntrada();
        calculaValorFinanciado();
        calculaDesembolsoMensal();
        calculaDiferenca();
        calculaParcelaFinanciamento();

        mostraDados();

    }

    private void calculaCusto() {
        if(CapacidadeSistema >= 0 && CapacidadeSistema < 2 ){
            Custo = 7280;
        }else if(CapacidadeSistema >= 2 && CapacidadeSistema < 4) {
            Custo = 5370;
        }else if(CapacidadeSistema >= 4 && CapacidadeSistema < 6) {
            Custo = 4780;
        }else if(CapacidadeSistema >= 6 && CapacidadeSistema < 8) {
            Custo = 4670;
        }else if(CapacidadeSistema >= 8 && CapacidadeSistema <10) {
            Custo = 4440;
        }else if(CapacidadeSistema > 10){
            Custo = 4350;
        }
    }

    private void calculaConsumoEstimado() {
        ConsumoEstimado = ContaEnergia / Tarifa;
    }

    private void calculaTotalCompensado() {
        TotalCompensado = ConsumoEstimado - TaxaDisponibilidade;
    }


    private void calculaCapacidadeSistema() {
        CapacidadeSistema = TotalCompensado/ProdutividadeLiquidaMes;
    }

    private void calculaInvestimentoTotal() {
        InvestimentoTotal = CapacidadeSistema * Custo;
    }

    private void calculaCustoTotal() {
        CustoTotal = InvestimentoTotal;
    }

    private void calculaEntrada() {
        Entrada =  0.1 * CustoTotal;
    }

    private void calculaValorFinanciado() {
        ValorFinanciado = CustoTotal - Entrada;

    }

    private void calculaParcelaFinanciamento() {
        ParcelaFinanciamento = (ValorFinanciado * TaxaMes)/1-(1/Math.pow((1+TaxaMes),Prazo));
        ParcelaFinanciamento /= 60;
    }

    //Depende do ParcelaFinanciamento
    private void calculaDesembolsoMensal() {
        DesembolsoMensal = ContaFixa + ParcelaFinanciamento;
    }

    private void calculaDiferenca() {
        Diferenca = ContaEnergia - DesembolsoMensal;
    }

    private void mostraDados() {

        Log.d(TAG, String.valueOf(ConsumoEstimado));
        Log.d(TAG, String.valueOf(TotalCompensado));
        Log.d(TAG, String.valueOf(CapacidadeSistema));
        Log.d(TAG, String.valueOf(InvestimentoTotal));
        Log.d(TAG, String.valueOf(CustoTotal));
        Log.d(TAG, String.valueOf(Entrada));
        Log.d(TAG, String.valueOf(ValorFinanciado));
        Log.d(TAG, String.valueOf(DesembolsoMensal));
        Log.d(TAG, String.valueOf(Diferenca));
        Log.d(TAG, String.valueOf(ParcelaFinanciamento));

    }

}

