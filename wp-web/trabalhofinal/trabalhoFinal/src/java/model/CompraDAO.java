package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Compras;
import java.sql.Connection;
import java.sql.Date;

public class CompraDAO implements Dao<Compras> {

    private Connection conexao;

    public CompraDAO() {
        this.conexao = new Conexao().getConexao();
    }

    @Override
    public Compras get(int id) {
        try {
            Compras compra = new Compras();
            PreparedStatement sql = conexao.prepareStatement("SELECT * FROM trabalhofinal.compras WHERE id = ?");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                compra.setId(resultado.getInt("id"));
                compra.setQuantidadeCompra(resultado.getInt("quantidade_compra"));
                compra.setDataCompra(resultado.getDate("data_compra"));
                compra.setValorCompra(resultado.getFloat("valor_compra"));
                compra.setIdFornecedor(resultado.getInt("id_fornecedor"));
                compra.setIdProduto(resultado.getInt("id_produto"));
                compra.setIdFuncionario(resultado.getInt("id_funcionario"));
            }
            return compra;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar compra por ID: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Compras> getAll() {
        ArrayList<Compras> listaCompras = new ArrayList<>();
        try {
            String selectSQL = "SELECT * FROM trabalhofinal.compras ORDER BY data_compra DESC";
            PreparedStatement preparedStatement = conexao.prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                Compras compra = new Compras();
                compra.setId(resultado.getInt("id"));
                compra.setQuantidadeCompra(resultado.getInt("quantidade_compra"));
                compra.setDataCompra(resultado.getDate("data_compra"));
                compra.setValorCompra(resultado.getFloat("valor_compra"));
                compra.setIdFornecedor(resultado.getInt("id_fornecedor"));
                compra.setIdProduto(resultado.getInt("id_produto"));
                compra.setIdFuncionario(resultado.getInt("id_funcionario"));
                listaCompras.add(compra);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar compras: " + e.getMessage());
        }
        return listaCompras;
    }

    @Override
    public void insert(Compras compra) {
        try {
            PreparedStatement sql = conexao.prepareStatement("INSERT INTO trabalhofinal.compras (quantidade_compra, data_compra, valor_compra, id_fornecedor, id_produto, id_funcionario) VALUES (?, ?, ?, ?, ?, ?)");
            sql.setInt(1, compra.getQuantidadeCompra());
            sql.setDate(2, new java.sql.Date(compra.getDataCompra().getTime())); // Converte java.util.Date para java.sql.Date
            sql.setFloat(3, compra.getValorCompra());
            sql.setInt(4, compra.getIdFornecedor());
            sql.setInt(5, compra.getIdProduto());
            sql.setInt(6, compra.getIdFuncionario());
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir compra: " + e.getMessage());
        }
    }

    @Override
    public void update(Compras compra) {
        try {
            PreparedStatement sql = conexao.prepareStatement("UPDATE trabalhofinal.compras SET quantidade_compra = ?, data_compra = ?, valor_compra = ?, id_fornecedor = ?, id_produto = ?, id_funcionario = ? WHERE id = ?");
            sql.setInt(1, compra.getQuantidadeCompra());
            sql.setDate(2, new java.sql.Date(compra.getDataCompra().getTime())); // Converte java.util.Date para java.sql.Date
            sql.setFloat(3, compra.getValorCompra());
            sql.setInt(4, compra.getIdFornecedor());
            sql.setInt(5, compra.getIdProduto());
            sql.setInt(6, compra.getIdFuncionario());
            sql.setInt(7, compra.getId());
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar compra: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement sql = conexao.prepareStatement("DELETE FROM trabalhofinal.compras WHERE id = ?");
            sql.setInt(1, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir compra: " + e.getMessage());
        }
    }

    //Metodos novos 
    public ArrayList<Compras> getAllByFuncionario(int idFuncionario) {
        ArrayList<Compras> listaCompras = new ArrayList<>();
        try {
            String selectSQL = "SELECT * FROM trabalhofinal.compras WHERE id_funcionario = ? ORDER BY data_compra DESC";
            PreparedStatement preparedStatement = conexao.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idFuncionario);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                Compras compra = new Compras();
                compra.setId(resultado.getInt("id"));
                compra.setQuantidadeCompra(resultado.getInt("quantidade_compra"));
                compra.setDataCompra(resultado.getDate("data_compra"));
                compra.setValorCompra(resultado.getFloat("valor_compra"));
                compra.setIdFornecedor(resultado.getInt("id_fornecedor"));
                compra.setIdProduto(resultado.getInt("id_produto"));
                compra.setIdFuncionario(resultado.getInt("id_funcionario"));
                listaCompras.add(compra);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar compras por funcionário: " + e.getMessage());
        }
        return listaCompras;
    }

}
