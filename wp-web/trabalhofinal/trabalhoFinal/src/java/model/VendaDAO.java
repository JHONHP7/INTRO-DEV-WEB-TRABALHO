package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import entidade.Vendas;
import entidade.auxiliar.TotalVendasDiarias;
import entidade.auxiliar.TotalVendasProduto;

public class VendaDAO implements Dao<Vendas> {

    @Override
    public Vendas get(int id) {
        Conexao conexao = new Conexao();
        try {
            Vendas venda = new Vendas();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM trabalhofinal.vendas WHERE id = ?");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado.next()) {
                venda.setId(resultado.getInt("id"));
                venda.setQuantidadeVenda(resultado.getInt("quantidade_venda"));
                venda.setDataVenda(resultado.getDate("data_venda"));
                venda.setValorVenda(resultado.getFloat("valor_venda"));
                venda.setIdCliente(resultado.getInt("id_cliente"));
                venda.setIdProduto(resultado.getInt("id_produto"));
                venda.setIdFuncionario(resultado.getInt("id_funcionario"));
                
            }
            return venda;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar venda por ID: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Vendas> getAll() {
        ArrayList<Vendas> listaVendas = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM trabalhofinal.vendas ORDER BY data_venda";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                Vendas venda = new Vendas();
                venda.setId(resultado.getInt("id"));
                venda.setQuantidadeVenda(resultado.getInt("quantidade_venda"));
                venda.setDataVenda(resultado.getDate("data_venda"));
                venda.setValorVenda(resultado.getFloat("valor_venda"));
                venda.setIdCliente(resultado.getInt("id_cliente"));
                venda.setIdProduto(resultado.getInt("id_produto"));
                venda.setIdFuncionario(resultado.getInt("id_funcionario"));
                listaVendas.add(venda);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vendas: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return listaVendas;
    }

    @Override
    public void insert(Vendas venda) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO trabalhofinal.vendas (quantidade_venda, data_venda, valor_venda, id_cliente, id_produto, id_funcionario)"
                    + " VALUES (?, ?, ?, ?, ?, ?)");
            sql.setInt(1, venda.getQuantidadeVenda());
            sql.setDate(2, (Date) venda.getDataVenda()); // Uso direto de java.sql.Date
            sql.setDouble(3, venda.getValorVenda());
            sql.setInt(4, venda.getIdCliente());
            sql.setInt(5, venda.getIdProduto());
            sql.setInt(6, venda.getIdFuncionario());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir venda: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Vendas venda) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE trabalhofinal.vendas SET quantidade_venda = ?, data_venda = ?, valor_venda = ?, id_cliente = ?, id_produto = ?, id_funcionario = ? WHERE id = ?");
            sql.setInt(1, venda.getQuantidadeVenda());
            sql.setDate(2, (Date) venda.getDataVenda()); // Uso direto de java.sql.Date
            sql.setDouble(3, venda.getValorVenda());
            sql.setInt(4, venda.getIdCliente());
            sql.setInt(5, venda.getIdProduto());
            sql.setInt(6, venda.getIdFuncionario());
            sql.setInt(7, venda.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar venda: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM trabalhofinal.vendas WHERE id = ?");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir venda: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    /**
     * Metodos novos
     */
    public ArrayList<TotalVendasProduto> getTotalVendasPorProduto() {
        ArrayList<TotalVendasProduto> listaTotalVendasProduto = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT p.id, p.nome_produto, SUM(v.quantidade_venda) AS total_quantidade_vendida, "
                    + "SUM(v.valor_venda) AS total_valor_vendido "
                    + "FROM vendas v "
                    + "JOIN produtos p ON v.id_produto = p.id "
                    + "GROUP BY p.id, p.nome_produto";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                TotalVendasProduto totalVendasProduto = new TotalVendasProduto();
                totalVendasProduto.setIdProduto(resultado.getInt("id"));
                totalVendasProduto.setNomeProduto(resultado.getString("nome_produto"));
                totalVendasProduto.setTotalQuantidadeVendida(resultado.getInt("total_quantidade_vendida"));
                totalVendasProduto.setTotalValorVendido(resultado.getFloat("total_valor_vendido"));
                listaTotalVendasProduto.add(totalVendasProduto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar total de vendas por produto: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return listaTotalVendasProduto;
    }

    public ArrayList<TotalVendasDiarias> getTotalVendasDiarias() {
        ArrayList<TotalVendasDiarias> listaTotalVendasDiarias = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT data_venda, SUM(quantidade_venda) AS total_quantidade_vendida, ROUND(SUM(valor_venda), 2) AS total_valor_vendido FROM trabalhofinal.vendas GROUP BY data_venda ORDER BY data_venda DESC";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                TotalVendasDiarias totalVendasDiarias = new TotalVendasDiarias();
                totalVendasDiarias.setDataVenda(resultado.getDate("data_venda"));
                totalVendasDiarias.setTotalQuantidadeVendida(resultado.getInt("total_quantidade_vendida"));
                totalVendasDiarias.setTotalValorVendido(resultado.getDouble("total_valor_vendido"));
                listaTotalVendasDiarias.add(totalVendasDiarias);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao calcular total de vendas diárias: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return listaTotalVendasDiarias;
    }

    public ArrayList<Vendas> getByFuncionario(int idFuncionario) {
        ArrayList<Vendas> listaVendas = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM trabalhofinal.vendas WHERE id_funcionario = ? ORDER BY data_venda";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            preparedStatement.setInt(1, idFuncionario);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                Vendas venda = new Vendas();
                venda.setId(resultado.getInt("id"));
                venda.setQuantidadeVenda(resultado.getInt("quantidade_venda"));
                venda.setDataVenda(resultado.getDate("data_venda"));
                venda.setValorVenda(resultado.getFloat("valor_venda"));
                venda.setIdCliente(resultado.getInt("id_cliente"));
                venda.setIdProduto(resultado.getInt("id_produto"));
                venda.setIdFuncionario(resultado.getInt("id_funcionario"));
                listaVendas.add(venda);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vendas por funcionário: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return listaVendas;
    }
}
