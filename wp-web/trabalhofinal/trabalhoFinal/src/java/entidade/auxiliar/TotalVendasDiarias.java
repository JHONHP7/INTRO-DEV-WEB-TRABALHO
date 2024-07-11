/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade.auxiliar;

import java.util.Date;

/**
 *
 * @author jhonatan
 */
public class TotalVendasDiarias {

    private Date dataVenda;
    private double totalQuantidadeVendida;
    private double totalValorVendido;

    // Getters e setters
    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getTotalQuantidadeVendida() {
        return totalQuantidadeVendida;
    }

    public void setTotalQuantidadeVendida(double totalQuantidadeVendida) {
        this.totalQuantidadeVendida = totalQuantidadeVendida;
    }

    public double getTotalValorVendido() {
        return totalValorVendido;
    }

    public void setTotalValorVendido(double totalValorVendido) {
        this.totalValorVendido = totalValorVendido;
    }
}
