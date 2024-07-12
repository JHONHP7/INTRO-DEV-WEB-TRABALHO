/**
 *
 * @author jhonatan-silva
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Fornecedores;

public class FornecedorDAO implements Dao<Fornecedores> {

    @Override
    public Fornecedores get(int id) {
        Conexao conexao = new Conexao();
        try {
            Fornecedores fornecedor = new Fornecedores();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM trabalhofinal.fornecedores WHERE id = ?");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                fornecedor.setId(resultado.getInt("id"));
                fornecedor.setRazaoSocial(resultado.getString("razao_social"));
                fornecedor.setCnpj(resultado.getString("cnpj"));
                fornecedor.setEndereco(resultado.getString("endereco"));
                fornecedor.setBairro(resultado.getString("bairro"));
                fornecedor.setCidade(resultado.getString("cidade"));
                fornecedor.setUf(resultado.getString("uf"));
                fornecedor.setCep(resultado.getString("cep"));
                fornecedor.setTelefone(resultado.getString("telefone"));
                fornecedor.setEmail(resultado.getString("email"));
            }
            return fornecedor;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar fornecedor por ID: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Fornecedores> getAll() {
        ArrayList<Fornecedores> listaFornecedores = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM trabalhofinal.fornecedores ORDER BY razao_social";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                Fornecedores fornecedor = new Fornecedores();
                fornecedor.setId(resultado.getInt("id"));
                fornecedor.setRazaoSocial(resultado.getString("razao_social"));
                fornecedor.setCnpj(resultado.getString("cnpj"));
                fornecedor.setEndereco(resultado.getString("endereco"));
                fornecedor.setBairro(resultado.getString("bairro"));
                fornecedor.setCidade(resultado.getString("cidade"));
                fornecedor.setUf(resultado.getString("uf"));
                fornecedor.setCep(resultado.getString("cep"));
                fornecedor.setTelefone(resultado.getString("telefone"));
                fornecedor.setEmail(resultado.getString("email"));
                listaFornecedores.add(fornecedor);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar fornecedores: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return listaFornecedores;
    }

    @Override
    public void insert(Fornecedores fornecedor) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO trabalhofinal.fornecedores (razao_social, cnpj, endereco, bairro, cidade, uf, cep, telefone, email)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            sql.setString(1, fornecedor.getRazaoSocial());
            sql.setString(2, fornecedor.getCnpj());
            sql.setString(3, fornecedor.getEndereco());
            sql.setString(4, fornecedor.getBairro());
            sql.setString(5, fornecedor.getCidade());
            sql.setString(6, fornecedor.getUf());
            sql.setString(7, fornecedor.getCep());
            sql.setString(8, fornecedor.getTelefone());
            sql.setString(9, fornecedor.getEmail());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir fornecedor: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Fornecedores fornecedor) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE trabalhofinal.fornecedores SET razao_social = ?, cnpj = ?, endereco = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, telefone = ?, email = ? WHERE id = ?");
            sql.setString(1, fornecedor.getRazaoSocial());
            sql.setString(2, fornecedor.getCnpj());
            sql.setString(3, fornecedor.getEndereco());
            sql.setString(4, fornecedor.getBairro());
            sql.setString(5, fornecedor.getCidade());
            sql.setString(6, fornecedor.getUf());
            sql.setString(7, fornecedor.getCep());
            sql.setString(8, fornecedor.getTelefone());
            sql.setString(9, fornecedor.getEmail());
            sql.setInt(10, fornecedor.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar fornecedor: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM trabalhofinal.fornecedores WHERE id = ?");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir fornecedor: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    //Metodos novos
    public boolean existsByCnpj(String cnpj) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT COUNT(*) FROM trabalhofinal.fornecedores WHERE cnpj = ?");
            sql.setString(1, cnpj);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                return resultado.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência de fornecedor por CNPJ: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    public boolean existsByRazaoSocial(String razaoSocial) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT COUNT(*) FROM trabalhofinal.fornecedores WHERE razao_social = ?");
            sql.setString(1, razaoSocial);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                return resultado.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência de fornecedor por Razão Social: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }
}
