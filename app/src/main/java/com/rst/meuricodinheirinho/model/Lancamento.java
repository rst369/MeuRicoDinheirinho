package com.rst.meuricodinheirinho.model;

import java.io.Serializable;

public class Lancamento implements Serializable {

    private static final long serialVersionUID= 9200511240715093289L;

    private double valor;
    private String data;
    private String categoria;
    private String desc;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
