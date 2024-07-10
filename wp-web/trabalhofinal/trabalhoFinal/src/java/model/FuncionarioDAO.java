/**
 * @author jhonatan-silva
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Funcionarios;

public class FuncionarioDAO implements Dao<Funcionarios> {

    @Override
    public Funcionarios get(int id) {
        Conexao conexao = new Conexao();
        try {
            Funcionarios funcionario = new Funcionarios();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM funcionarios WHERE id = ?");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                funcionario.setId(resultado.getInt("id"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setCpf(resultado.getString("cpf"));
                funcionario.setSenha(resultado.getString("senha"));
                funcionario.setPapel(resultado.getString("papel"));
            }
            return funcionario;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar funcionário por ID: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Funcionarios> getAll() {
        ArrayList<Funcionarios> listaFuncionarios = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM trabalhofinal.funcionarios ORDER BY nome";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                Funcionarios funcionario = new Funcionarios();
                funcionario.setId(resultado.getInt("id"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setCpf(resultado.getString("cpf"));
                funcionario.setSenha(resultado.getString("senha"));
                funcionario.setPapel(resultado.getString("papel"));
                listaFuncionarios.add(funcionario);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar funcionários: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return listaFuncionarios;
    }

    public ArrayList<Funcionarios> getAllVendedores() {
        ArrayList<Funcionarios> listaFuncionarios = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM trabalhofinal.funcionarios where papel like '1' ORDER BY nome";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                Funcionarios funcionario = new Funcionarios();
                funcionario.setId(resultado.getInt("id"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setCpf(resultado.getString("cpf"));
                funcionario.setSenha(resultado.getString("senha"));
                funcionario.setPapel(resultado.getString("papel"));
                listaFuncionarios.add(funcionario);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar funcionários: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return listaFuncionarios;
    }

    public ArrayList<Funcionarios> getAllCompradores() {
        ArrayList<Funcionarios> listaFuncionarios = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM trabalhofinal.funcionarios where papel like '2' ORDER BY nome";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                Funcionarios funcionario = new Funcionarios();
                funcionario.setId(resultado.getInt("id"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setCpf(resultado.getString("cpf"));
                funcionario.setSenha(resultado.getString("senha"));
                funcionario.setPapel(resultado.getString("papel"));
                listaFuncionarios.add(funcionario);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar funcionários: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return listaFuncionarios;
    }

    public ArrayList<Funcionarios> getAllAdms() {
        ArrayList<Funcionarios> listaFuncionarios = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM trabalhofinal.funcionarios where papel like '0' ORDER BY nome";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                Funcionarios funcionario = new Funcionarios();
                funcionario.setId(resultado.getInt("id"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setCpf(resultado.getString("cpf"));
                funcionario.setSenha(resultado.getString("senha"));
                funcionario.setPapel(resultado.getString("papel"));
                listaFuncionarios.add(funcionario);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar funcionários: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return listaFuncionarios;
    }

    @Override
    public void insert(Funcionarios funcionario) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO funcionarios (nome, cpf, senha, papel)"
                    + " VALUES (?, ?, ?, ?)");
            sql.setString(1, funcionario.getNome());
            sql.setString(2, funcionario.getCpf());
            sql.setString(3, funcionario.getSenha());
            sql.setString(4, funcionario.getPapel());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir funcionário: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Funcionarios funcionario) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE funcionarios SET nome = ?, cpf = ?, senha = ?, papel = ? WHERE id = ?");
            sql.setString(1, funcionario.getNome());
            sql.setString(2, funcionario.getCpf());
            sql.setString(3, funcionario.getSenha());
            sql.setString(4, funcionario.getPapel());
            sql.setInt(5, funcionario.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar funcionário: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM funcionarios WHERE id = ?");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir funcionário: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    public Funcionarios Logar(Funcionarios funcionario) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM funcionarios WHERE cpf = ? AND senha = ? LIMIT 1");
            sql.setString(1, funcionario.getCpf());
            sql.setString(2, funcionario.getSenha());
            ResultSet resultado = sql.executeQuery();
            Funcionarios funcionarioObtido = new Funcionarios();
            if (resultado.next()) {
                funcionarioObtido.setId(resultado.getInt("id"));
                funcionarioObtido.setNome(resultado.getString("nome"));
                funcionarioObtido.setCpf(resultado.getString("cpf"));
                funcionarioObtido.setSenha(resultado.getString("senha"));
                funcionarioObtido.setPapel(resultado.getString("papel"));
            } else {
                throw new RuntimeException("Funcionário não encontrado.");
            }
            return funcionarioObtido;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao logar funcionário: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }
}
