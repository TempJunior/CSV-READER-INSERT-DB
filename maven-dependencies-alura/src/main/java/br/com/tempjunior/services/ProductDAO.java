package br.com.tempjunior.services;

import br.com.tempjunior.model.DadosProduto;
import br.com.tempjunior.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDAO {
    private Connection conn;
    private Product produto = new Product();

    ProductDAO(Connection connection){
        this.conn = connection;
    }

    public void insertDadosNoBanco(List<Product> products) {
        String checkSql = "SELECT COUNT(*) FROM produtos WHERE id = ?";
        String insertSql = "INSERT INTO produtos (id, name, description, price, category)" +
                " VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

            for (Product produto : products) {
                // Verificar se o produto já existe
                checkStmt.setInt(1, produto.getId());
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                int count = rs.getInt(1);

                if (count == 0) {

                    insertStmt.setInt(1, produto.getId());
                    insertStmt.setString(2, produto.getName());
                    insertStmt.setString(3, produto.getDescription());
                    insertStmt.setDouble(4, produto.getPrice());
                    insertStmt.setString(5, produto.getCategory());

                    insertStmt.addBatch();
                }
            }

            insertStmt.executeBatch();
            System.out.println("Novos dados inseridos com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
