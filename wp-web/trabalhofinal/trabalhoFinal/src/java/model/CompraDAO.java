/**
 *
 * @author jhonatan-silva
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import entidade.Compras;

public class CompraDAO implements Dao<Compras> {

    @Override
    public Compras get(int id) {
        Conexao conexao = new Conexao();
        try {
            Compras compra = new Compras();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM trabalhofinal.compras WHERE id = ?");
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
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Compras> getAll() {
        ArrayList<Compras> listaCompras = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM trabalhofinal.compras ORDER BY data_compra";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
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
        } finally {
            conexao.closeConexao();
        }
        return listaCompras;
    }

    @Override
    public void insert(Compras compra) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO trabalhofinal.compras (quantidade_compra, data_compra, valor_compra, id_fornecedor, id_produto, id_funcionario)"
                    + " VALUES (?, ?, ?, ?, ?, ?)");
            sql.setInt(1, compra.getQuantidadeCompra());
            sql.setDate(2, new Date(compra.getDataCompra().getTime()));
            sql.setDouble(3, compra.getValorCompra());
            sql.setInt(4, compra.getIdFornecedor());
            sql.setInt(5, compra.getIdProduto());
            sql.setInt(6, compra.getIdFuncionario());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir compra: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Compras compra) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE trabalhofinal.compras SET quantidade_compra = ?, data_compra = ?, valor_compra = ?, id_fornecedor = ?, id_produto = ?, id_funcionario = ? WHERE id = ?");
            sql.setInt(1, compra.getQuantidadeCompra());
            sql.setDate(2, new Date(compra.getDataCompra().getTime()));
            sql.setDouble(3, compra.getValorCompra());
            sql.setInt(4, compra.getIdFornecedor());
            sql.setInt(5, compra.getIdProduto());
            sql.setInt(6, compra.getIdFuncionario());
            sql.setInt(7, compra.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar compra: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM trabalhofinal.compras WHERE id = ?");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir compra: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }
}
