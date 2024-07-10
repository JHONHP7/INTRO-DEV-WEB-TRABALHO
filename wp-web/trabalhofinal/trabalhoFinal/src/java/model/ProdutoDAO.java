/**
 *
 * @author jhonatan-silva
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Produtos;

public class ProdutoDAO implements Dao<Produtos> {

    @Override
    public Produtos get(int id) {
        Conexao conexao = new Conexao();
        try {
            Produtos produto = new Produtos();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM trabalhofinal.produtos WHERE id = ?");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                produto.setId(resultado.getInt("id"));
                produto.setNomeProduto(resultado.getString("nome_produto"));
                produto.setDescricao(resultado.getString("descricao"));
                produto.setPrecoCompra(resultado.getDouble("preco_compra"));
                produto.setPrecoVenda(resultado.getDouble("preco_venda"));
                produto.setQuantidadeDisponivel(resultado.getInt("quantidade_disponível"));
                produto.setLiberadoVenda(resultado.getString("liberado_venda").charAt(0));
                produto.setIdCategoria(resultado.getInt("id_categoria"));
            }
            return produto;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto por ID: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Produtos> getAll() {
        ArrayList<Produtos> listaProdutos = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM trabalhofinal.produtos ORDER BY nome_produto";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                Produtos produto = new Produtos();
                produto.setId(resultado.getInt("id"));
                produto.setNomeProduto(resultado.getString("nome_produto"));
                produto.setDescricao(resultado.getString("descricao"));
                produto.setPrecoCompra(resultado.getDouble("preco_compra"));
                produto.setPrecoVenda(resultado.getDouble("preco_venda"));
                produto.setQuantidadeDisponivel(resultado.getInt("quantidade_disponível"));
                produto.setLiberadoVenda(resultado.getString("liberado_venda").charAt(0));
                produto.setIdCategoria(resultado.getInt("id_categoria"));
                listaProdutos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return listaProdutos;
    }

    @Override
    public void insert(Produtos produto) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO trabalhofinal.produtos (nome_produto, descricao, preco_compra, preco_venda, quantidade_disponível, liberado_venda, id_categoria)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)");
            sql.setString(1, produto.getNomeProduto());
            sql.setString(2, produto.getDescricao());
            sql.setDouble(3, produto.getPrecoCompra());
            sql.setDouble(4, produto.getPrecoVenda());
            sql.setInt(5, produto.getQuantidadeDisponivel());
            sql.setString(6, String.valueOf(produto.getLiberadoVenda()));
            sql.setInt(7, produto.getIdCategoria());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Produtos produto) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "UPDATE trabalhofinal.produtos SET nome_produto = ?, descricao = ?, preco_compra = ?, preco_venda = ?, quantidade_disponível = ?, liberado_venda = ?, id_categoria = ? WHERE id = ?");
            sql.setString(1, produto.getNomeProduto());
            sql.setString(2, produto.getDescricao());
            sql.setDouble(3, produto.getPrecoCompra());
            sql.setDouble(4, produto.getPrecoVenda());
            sql.setInt(5, produto.getQuantidadeDisponivel());
            sql.setString(6, String.valueOf(produto.getLiberadoVenda()));
            sql.setInt(7, produto.getIdCategoria());
            sql.setInt(8, produto.getId());
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar produto: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM trabalhofinal.produtos WHERE id = ?");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir produto: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }
}
