/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jhonatan-silva
 */
public class Conexao {

    /* Banco de dados: `dbjava` */
    private Connection conexao;

    public Conexao() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //load driver  
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/trabalhofinal", "root", "1996");
        } catch (SQLException e) {
            throw new RuntimeException("Nao foi possivel efetuar uma conexao com o BD!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Nao foi possivel encontrar a classe referente! Verifique o driver!");
        }
    }

    public Connection getConexao() {
        return this.conexao;
    }

    public void closeConexao() {
        try {
            this.conexao.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
