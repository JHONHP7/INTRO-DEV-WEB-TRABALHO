package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Categorias;

public class CategoriaDAO implements Dao<Categorias> {

    @Override
    public Categorias get(int id) {
        Conexao conexao = new Conexao();
        try {
            Categorias categoria = new Categorias();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM trabalhofinal.categorias WHERE id = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                categoria.setId(resultado.getInt("id"));
                categoria.setNomeCategoria(resultado.getString("nome_categoria"));
            }
            return categoria;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar categoria por ID: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Categorias> getAll() {
        ArrayList<Categorias> listaCategorias = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM trabalhofinal.categorias ORDER BY nome_categoria";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                Categorias categoria = new Categorias();
                categoria.setId(resultado.getInt("id"));
                categoria.setNomeCategoria(resultado.getString("nome_categoria"));
                listaCategorias.add(categoria);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar categorias: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return listaCategorias;
    }

    @Override
    public void insert(Categorias categoria) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO trabalhofinal.categorias (nome_categoria)"
                    + " VALUES (?)");
            sql.setString(1, categoria.getNomeCategoria());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir categoria: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Categorias categoria) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE trabalhofinal.categorias SET nome_categoria = ? WHERE id = ? ");
            sql.setString(1, categoria.getNomeCategoria());
            sql.setInt(2, categoria.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar categoria: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM trabalhofinal.categorias WHERE id = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir categoria: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }
}
