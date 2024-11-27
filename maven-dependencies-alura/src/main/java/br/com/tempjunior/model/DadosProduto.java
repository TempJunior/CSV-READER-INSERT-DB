package br.com.tempjunior.model;

import java.math.BigDecimal;

public record DadosProduto(int id, String name, String description, double price, String category) {

    public DadosProduto(Product product){
        this(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory());
    }
}

