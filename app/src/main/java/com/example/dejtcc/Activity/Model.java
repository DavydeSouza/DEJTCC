package com.example.dejtcc.Activity;

public class Model {
    String comida;
    String tipo;
    String descricao;
    String preco;
    String estoque;

    public Model(){}

    public Model(String comida,String tipo,String descricao,String preco,String estoque){

        this.comida = comida;
        this.tipo = tipo;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getComida() {
        return comida;
    }

    public void setComida(String comida) {
        this.comida = comida;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getEstoque() {
        return estoque;
    }

    public void setEstoque(String estoque) {
        this.estoque = estoque;
    }
}
