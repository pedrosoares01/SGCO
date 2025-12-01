<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="sgco.sgco.domain.Agenda"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalDateTime" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agenda - SGCO</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/agenda.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">

</head>
<body>
<aside class="sidebar">
    <a href="indexrecepcionista.html"><h2>SGCO</h2></a>
    <ul>
        <li><a href="${pageContext.request.contextPath}/core/pac/gestao_pacientes.html">Gestão de Pacientes</a></li>
        <li><a href="agenda.jsp">Agenda</a></li>
        <li><a href="pagamento.html">Pagamentos</a></li>
        <li><a href="../pacientes-agendados/pacientes_agendados.jsp">Pacientes Agendados</a></li>
        <li><a href="avaliacao.html">Avaliação de Profissionais</a></li>
        <li><a href="${pageContext.request.contextPath}/index.jsp" class="logout">Sair</a></li>
    </ul>
</aside>
<main class="content">
    <h1>Agenda</h1>
    <div class="container">
        <section class="card">
            <h2>Agendar Consulta</h2>
            <form id="agendaForm" method="POST" action ="${pageContext.request.contextPath}/AgendaController">
                <input type="hidden" name="action" id="action">
                <label>Paciente:</label>
                <input type="text" name="paciente" placeholder="Nome do paciente" required>
                <label>Profissional:</label>
                <input type="text" name="profissional" placeholder="Nome do profissional" required>
                <label>Data:</label>
                <input type="date" name="data" required>
                <label>Hora:</label>
                <input type="time" name="hora" required>
                <div class="buttons">
                    <button type="submit" class="btn-primary" onclick="document.getElementById('action').value='agendar'">Agendar</button>
                </div>
            </form>
        </section>
        <section class="card">
            <h2>🔍 Pesquisar Agendamento</h2>
            <form method="POST" action="${pageContext.request.contextPath}/AgendaController">
                <input type="hidden" name="action" id="actionPesquisar">
                <label>Paciente ou Profissional:</label>
                <input type="text" name="nome" placeholder="Digite para pesquisar..." required>
                <div class="buttons">
                    <button type="submit" class="btn-primary" onclick="document.getElementById('actionPesquisar').value='pesquisar'">Buscar</button>
                </div>
            </form>
            <%
                try{
                    List<Agenda> resultadosPesquisa = (List<Agenda>) request.getAttribute("resultadosPesquisa");
                    String nomePesquisa = (String) request.getAttribute("nomePesquisa");
                    if (resultadosPesquisa != null) {
            %>
            <div class="search-results">
                <h3>Resultados da pesquisa por "<%= nomePesquisa %>":</h3>
                <table>
                    <tr>
                        <th>Paciente</th>
                        <th>Profissional</th>
                        <th>Data</th>
                        <th>Hora</th>
                    </tr>
                    <%
                        if (resultadosPesquisa.isEmpty()) {
                    %>
                    <tr><td colspan="4">Nenhum agendamento encontrado.</td></tr>
                    <%
                    } else {
                        for (Agenda a : resultadosPesquisa) {
                    %>
                    <tr>
                        <td><%= a.getPaciente() %></td>
                        <td><%= a.getProfissional() %></td>
                        <td><%= a.getData() %></td>
                        <td><%= a.getHora() %></td>
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
        </section>
        <section class="card">
            <h2> Pacientes marcados para amanhã</h2>
            <form method="POST" action="${pageContext.request.contextPath}/AgendaController">
                <input type="hidden" name="action" id="actionListar">
                <label>nome do Profissional:</label>
                <input type="text" name="nome" placeholder="Digite para pesquisar..." required>
                <div class="buttons">
                    <button type="submit" class="btn-primary" onclick="document.getElementById('actionListar').value='listar'">listar pacientes</button>
                </div>
            </form>
            <%  try{
                List<Agenda> resultadosListar = (List<Agenda>) request.getAttribute("resultadosListar");
                int tamanho = 1;
                if (resultadosListar != null) {
                    tamanho = resultadosListar.size();
                }
                String nomeListar = (String) request.getAttribute("nomeListar");
                if (resultadosListar != null){
            %>
                <div class="search-results">
                    <h3>Pacientes de "<%= nomeListar %>":</h3>
                    <%
                        if (resultadosListar.isEmpty()) {
                    %>
                    <p>Nenhum paciente encontrado</p>
                    <%
                    } else{
                    %>
                    <form action="${pageContext.request.contextPath}/LembreteController" method="post">
                            <select name="pacientes" multiple size="<%= tamanho %>" class="mult-select">
                                <% for (Agenda a : resultadosListar) { %>
                                <option value="<%= a.getPaciente() %>">
                                    <%= a.getPaciente() %> - <%= a.getHora() %>
                                </option>
                                <% } %>
                            </select>
                            <button type="submit" class="btn-primary">Enviar lembretes</button>
                    </form>
                </div>
                <%} %>
            <% }
            } catch (Exception e){}
            %>


            <%
                String mensagem = (String) request.getAttribute("mensagem");
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
    </div>
</main>
</body>
</html>