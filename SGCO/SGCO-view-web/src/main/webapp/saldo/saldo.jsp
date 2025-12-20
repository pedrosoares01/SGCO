<%@ page import="sgco.sgco.domain.SaldoPaciente" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Controle de Saldo - SGCO</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/saldo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
</head>
<body>
<aside class="sidebar">
    <a href="../index.jsp"><h2>SGCO</h2></a>
    <ul>
        <li><a href="gestao_pacientes.jsp">Gestão de Pacientes</a></li>
        <li><a href="gestao_usuarios.jsp">Gestão de Usuários</a></li>
        <li><a href="gestao_procedimentos.jsp">Gestão de Procedimentos</a></li>
        <li><a href="estoque.jsp">Controle de Estoque</a></li>
        <li><a href="fornecedores_materiais.jsp">Fornecedores de Materiais</a></li>
        <li><a href="fornecedores_servicos.jsp">Fornecedores de Serviços</a></li>
        <li><a href="receita.jsp">Gestão da Receita</a></li>
        <li><a href="pacientes_agendados.jsp">Pacientes Agendados</a></li>
        <li><a href="login.jsp" class="logout">Sair</a></li>
    </ul>
</aside>

<main class="content">
    <h1>Controle de Saldo</h1>
    <section class="card">
        <div class="panel">
            <form  method="post" action="${pageContext.request.contextPath}/SaldoController">
                <label>Relatório de Devedores</label>
                <button type="submit">Gerar</button>
            </form>


            <%
                try{
                    List<SaldoPaciente> resultados = (List<SaldoPaciente>) request.getAttribute("saldos");
                    if (resultados != null && !resultados.isEmpty()) {
            %>
            <div class="search-results">
                <h3>Devedores:</h3>
                <table>
                    <tr>
                        <th>Paciente</th>
                        <th>Valor Pago</th>
                        <th>Total a Ser Pago</th>
                        <th>Valor Devido</th>
                    </tr>
                    <%
                        if (resultados.isEmpty()) {
                    %>
                    <tr><td colspan="4">Nenhum agendamento encontrado.</td></tr>
                    <%
                    } else {
                        for (SaldoPaciente a : resultados) {
                    %>
                    <tr>
                        <td><%= a.getNomeDevedor() %></td>
                        <td><%= a.getPago() %></td>
                        <td><%= a.getTotalPagar() %></td>
                        <td><%= a.getDevido() %></td>
                        <td>
                            <div class="result-actions" style="display:flex; gap:0.5rem;">

                                <form action="${pageContext.request.contextPath}/SaldoController" method="post">
                                    <input type="hidden" name="acao" value="atualizar">
                                    <input type="hidden" name="nomeDevedor" value="<%= a.getNomeDevedor() %>">
                                    <input type="hidden" name="ValorPago" value="<%= a.getPago() %>">
                                    <input type="hidden" name="TotalPagar" value="<%= a.getTotalPagar() %>">
                                    <input type="hidden" name="ValorDevido" value="<%= a.getDevido() %>">
                                    <button type="submit" class="btn-primary" style="background-color:#dc3545;">
                                        Atualizar
                                    </button>
                                </form>
                            </div>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </div>
            <%
                    }
                } catch (Exception e){}
            %>
        </div>
        <%
            String mensagem = (String) request.getAttribute("msg");
            String tipoMensagem = (String) request.getAttribute("tipoMensagem");
        %>

        <% if (mensagem != null) { %>
        <div id="popup" class="<%= tipoMensagem %>">
            <p><%= mensagem %></p>
            <button onclick="fecharPopup()">OK</button>
        </div>

        <script>
            function fecharPopup() {
                document.getElementById("popup").style.display = "none";
            }
        </script>
        <% } %>
    </section>

    <div class="panel">
        <form  method="post" action="${pageContext.request.contextPath}/SaldoController">
            <label for="usuario">Relatório de Devedores Atrasados</label>
            <input type="hidden"  name="action" id="action">
            <button type="submit" onclick="document.getElementById('action').value='DevedoresAtrasados'">Gerar</button>
        </form>
    </div>
</main>
</body>
</html>