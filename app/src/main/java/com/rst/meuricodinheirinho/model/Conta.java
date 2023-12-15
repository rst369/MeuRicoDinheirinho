package com.rst.meuricodinheirinho.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Conta implements Serializable {

    private static final long serialVersionUID = -3597525783389144908L;
    private double saldo;
    List<Lancamento> lancamentos;
    private String nome;

    private String saldoStr;
    public Conta(String nome) {
        this.nome = nome;
        this.saldoStr = String.valueOf(this.saldo);
        lancamentos = new ArrayList<>();
    }

    public Conta(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }

    public void adicionaLancamento(Lancamento lancamento){
        lancamentos.add(lancamento);
        saldo += lancamento.getValor();
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSaldoStr() {
        return "saldo: "+saldoStr;
    }

    public void setSaldoStr(String saldoStr) {
        this.saldoStr = saldoStr;
    }


}
