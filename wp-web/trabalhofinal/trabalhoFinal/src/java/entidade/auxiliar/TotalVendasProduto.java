/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade.auxiliar;

/**
 *
 * @author jhonatan
 */
public class TotalVendasProduto {

    private int idProduto;
    private String nomeProduto;
    private int totalQuantidadeVendida;
    private float totalValorVendido;

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getTotalQuantidadeVendida() {
        return totalQuantidadeVendida;
    }

    public void setTotalQuantidadeVendida(int totalQuantidadeVendida) {
        this.totalQuantidadeVendida = totalQuantidadeVendida;
    }

    public float getTotalValorVendido() {
        return totalValorVendido;
    }

    public void setTotalValorVendido(float totalValorVendido) {
        this.totalValorVendido = totalValorVendido;
    }
}
