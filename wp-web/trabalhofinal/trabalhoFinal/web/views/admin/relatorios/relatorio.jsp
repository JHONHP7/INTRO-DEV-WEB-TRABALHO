<%-- 
    Document   : relatorio
    Created on : 26 de jun. de 2024, 01:15:13
    Author     : jhonatan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.aux.TotalVendasProduto"%>
<%@page import="entidade.aux.TotalVendasDiarias"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Mostrar Relatório</title>
        <link href="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5 justify-content-center">
                <div class="col-lg-10 col-md-10 col-sm-10 col-12 mx-auto">
                    <h1 class="text-center">Relatório</h1>

                    <h2 class="pt-3">Total de Vendas por Produto</h2>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>ID Produto</th>
                                    <th>Nome</th>
                                    <th>Total Quantidade Vendida</th>
                                    <th>Total Valor Vendido</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% ArrayList<TotalVendasProduto> listaTotalVendasProduto = (ArrayList<TotalVendasProduto>) request.getAttribute("listaTotalVendasProduto");
                                if (listaTotalVendasProduto != null) {
                                    for (TotalVendasProduto vendaProduto : listaTotalVendasProduto) {%>
                                <tr>
                                    <td><%= vendaProduto.getIdProduto()%></td>
                                    <td class="text-nowrap"><%= vendaProduto.getNomeProduto()%></td>
                                    <td class="text-nowrap"><%= vendaProduto.getTotalQuantidadeVendida()%></td>
                                    <td class="text-nowrap"><%= String.format("%.2f", vendaProduto.getTotalValorVendido())%></td>
                                </tr>
                                <%     }
                            } else { %>
                                <tr>
                                    <td colspan="4" class="text-center">Nenhum dado disponível</td>
                                </tr>
                                <% }
                                %>
                            </tbody>
                        </table>
                    </div>

                    <h2 class="pt-3">Total Vendido Diariamente</h2>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Data Venda</th>
                                    <th>Total Quantidade Vendida</th>
                                    <th>Total Valor Vendido</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% ArrayList<TotalVendasDiarias> listaTotalVendasDiarias = (ArrayList<TotalVendasDiarias>) request.getAttribute("listaTotalVendasDiarias");
                                if (listaTotalVendasDiarias != null) {
                                    for (TotalVendasDiarias vendaDiaria : listaTotalVendasDiarias) {%>
                                <tr>
                                    <td><%= vendaDiaria.getDataVenda()%></td>
                                    <td class="text-nowrap"><%= vendaDiaria.getTotalQuantidadeVendida()%></td>
                                    <td class="text-nowrap"><%= String.format("%.2f", vendaDiaria.getTotalValorVendido())%></td>
                                </tr>
                                <%     }
                            } else { %>
                                <tr>
                                    <td colspan="3" class="text-center">Nenhum dado disponível</td>
                                </tr>
                                <% }
                                %>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>
        <script src="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

