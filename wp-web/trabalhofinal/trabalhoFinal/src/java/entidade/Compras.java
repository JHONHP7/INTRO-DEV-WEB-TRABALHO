package entidade;

import java.util.Date;

public class Compras {

    private int id;
    private int quantidadeCompra;
    private Date dataCompra;
    private float valorCompra;
    private int idFornecedor;
    private int idProduto;
    private int idFuncionario;

    public Compras() {
    }

    public Compras(int id, int quantidadeCompra, Date dataCompra, float valorCompra, int idFornecedor, int idProduto, int idFuncionario) {
        this.id = id;
        this.quantidadeCompra = quantidadeCompra;
        this.dataCompra = dataCompra;
        this.valorCompra = valorCompra;
        this.idFornecedor = idFornecedor;
        this.idProduto = idProduto;
        this.idFuncionario = idFuncionario;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidadeCompra() {
        return quantidadeCompra;
    }

    public void setQuantidadeCompra(int quantidadeCompra) {
        this.quantidadeCompra = quantidadeCompra;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public float getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(float valorCompra) {
        this.valorCompra = valorCompra;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
}
