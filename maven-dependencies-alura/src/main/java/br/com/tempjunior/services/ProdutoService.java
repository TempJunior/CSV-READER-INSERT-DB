package br.com.tempjunior.services;

import br.com.tempjunior.dao.ConnectionFactory;
import br.com.tempjunior.model.Product;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.*;
import java.sql.Connection;
import java.util.List;

public class ProdutoService {
    private ConnectionFactory connection;
    private final Path csvPath = Paths.get("C:\\Users\\jbarreto\\OneDrive - Amigos do Bem\\Área de Trabalho\\maven-dependencies-alura\\maven-dependencies-alura\\src\\main\\resources\\products.csv");

    public ProdutoService(){
        this.connection = new ConnectionFactory();
    }

    private List<Product> listaDeProduto() throws FileNotFoundException {
        List<Product> products = new CsvToBeanBuilder(new FileReader("C:\\Users\\jbarreto\\OneDrive - Amigos do Bem\\Área de Trabalho\\maven-dependencies-alura\\maven-dependencies-alura\\src\\main\\resources\\products.csv"))
                .withType(Product.class).build().parse();

        //TradutorService tradutorService = new TradutorService();

        for(Product product : products){
            //tradutorService.traduz(product);
            System.out.println(product);
        }

        return products;
    }

    public void insertDadosCsv(){
        Connection conn = connection.recuperaConection();

        ProductDAO conexao = new ProductDAO(conn);
        try {
            conexao.insertDadosNoBanco(listaDeProduto());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
