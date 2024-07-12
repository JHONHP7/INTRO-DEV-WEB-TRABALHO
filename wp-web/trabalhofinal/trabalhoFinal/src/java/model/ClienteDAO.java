/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author jhonatan-silva
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Clientes;

public class ClienteDAO implements Dao<Clientes> {

    @Override
    public Clientes get(int id) {
        Conexao conexao = new Conexao();
        try {
            Clientes cliente = new Clientes();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM trabalhofinal.clientes WHERE id = ?");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                cliente.setId(resultado.getInt("id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setEndereco(resultado.getString("endereco"));
                cliente.setBairro(resultado.getString("bairro"));
                cliente.setCidade(resultado.getString("cidade"));
                cliente.setUf(resultado.getString("uf"));
                cliente.setCep(resultado.getString("cep"));
                cliente.setTelefone(resultado.getString("telefone"));
                cliente.setEmail(resultado.getString("email"));
            }
            return cliente;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por ID: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Clientes> getAll() {
        ArrayList<Clientes> listaClientes = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM trabalhofinal.clientes ORDER BY nome";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                Clientes cliente = new Clientes();
                cliente.setId(resultado.getInt("id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setEndereco(resultado.getString("endereco"));
                cliente.setBairro(resultado.getString("bairro"));
                cliente.setCidade(resultado.getString("cidade"));
                cliente.setUf(resultado.getString("uf"));
                cliente.setCep(resultado.getString("cep"));
                cliente.setTelefone(resultado.getString("telefone"));
                cliente.setEmail(resultado.getString("email"));
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return listaClientes;
    }

    @Override
    public void insert(Clientes cliente) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO trabalhofinal.clientes (nome, cpf, endereco, bairro, cidade, uf, cep, telefone, email)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            sql.setString(1, cliente.getNome());
            sql.setString(2, cliente.getCpf());
            sql.setString(3, cliente.getEndereco());
            sql.setString(4, cliente.getBairro());
            sql.setString(5, cliente.getCidade());
            sql.setString(6, cliente.getUf());
            sql.setString(7, cliente.getCep());
            sql.setString(8, cliente.getTelefone());
            sql.setString(9, cliente.getEmail());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cliente: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Clientes cliente) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE trabalhofinal.clientes SET nome = ?, cpf = ?, endereco = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, telefone = ?, email = ? WHERE id = ?");
            sql.setString(1, cliente.getNome());
            sql.setString(2, cliente.getCpf());
            sql.setString(3, cliente.getEndereco());
            sql.setString(4, cliente.getBairro());
            sql.setString(5, cliente.getCidade());
            sql.setString(6, cliente.getUf());
            sql.setString(7, cliente.getCep());
            sql.setString(8, cliente.getTelefone());
            sql.setString(9, cliente.getEmail());
            sql.setInt(10, cliente.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar cliente: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM trabalhofinal.clientes WHERE id = ?");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir cliente: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    public boolean cpfExists(String cpf) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT 1 FROM trabalhofinal.clientes WHERE cpf = ?");
            sql.setString(1, cpf);
            ResultSet resultado = sql.executeQuery();
            return resultado.next();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar CPF: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

}
