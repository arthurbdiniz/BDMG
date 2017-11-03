package com.bdmg.bdmg.Model;

import android.util.Log;

public class Dimensionamento {

    private String TAG = "Dimensionamento";

    private double Tarifa = 0.73;
    private int ProdutividadeBruta = 1450;
    private int TaxaDisponibilidade = 100;
    private int ProdutividadeLiquidaAno = 1407;
    private int ProdutividadeLiquidaMes = 117;
    private int Perdas = 3;
    private int Custo = 0; //Reais por KW
    private double ContaFixa = 73.0;
    private int DiferencaPorcentagem = 18;
    private int TaxaAno = 21;

    private double ConsumoEstimado = 0; //ok
    private double TotalCompensado = 0; //ok
    private double CapacidadeSistema = 0; //ok
    private double InvestimentoTotal = 0; //ok
    private double CustoTotal = 0; //ok
    private double Entrada = 0; //ok
    private double ValorFinaciado = 0; //ok
    private double ParcelaFinanciamento = 0; //TODO
    private double DesembolsoMensal = 0; //ok
    private double Diferenca = 0; //ok

    //Input
    private int Prazo = 0;
    private double ContaEnergia = 0;
    private double TaxaMes = 1.6;


    public Dimensionamento(float contaEnergia, int Prazo) {
        this.ContaEnergia = contaEnergia;
        this.Prazo = Prazo;
        this.TaxaMes = TaxaMes;

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
        ValorFinaciado = CustoTotal - Entrada;

    }

    private void calculaParcelaFinanciamento() {
        ParcelaFinanciamento = (ValorFinaciado * TaxaMes)/1-(1/Math.pow((1+TaxaMes),Prazo));
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
        Log.d(TAG, String.valueOf(ValorFinaciado));
        Log.d(TAG, String.valueOf(DesembolsoMensal));
        Log.d(TAG, String.valueOf(Diferenca));
        Log.d(TAG, String.valueOf(ParcelaFinanciamento));

    }

}

