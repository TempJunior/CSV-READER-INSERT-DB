package br.com.tempjunior;

import br.com.tempjunior.services.ProdutoService;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ProdutoService main = new ProdutoService();
        main.insertDadosCsv();
    }
}