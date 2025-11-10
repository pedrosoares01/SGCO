<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="sgco.sgco.domain.Agenda"%>
<%@ page import="java.sql.ResultSet" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agenda - SGCO</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/agenda.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
</head>
<body>
<aside class="sidebar">
    <a href="indexrecepcionista.html"><h2>SGCO</h2></a>
    <ul>
        <li><a href="gestao_pacientes.html">Gestão de Pacientes</a></li>
        <li><a href="agenda.jsp">Agenda</a></li>
        <li><a href="pagamento.html">Pagamentos</a></li>
        <li><a href="../pacientes-agendados/pacientes_agendados.jsp">Pacientes Agendados</a></li>
        <li><a href="avaliacao.html">Avaliação de Profissionais</a></li>
        <li><a href="login.html" class="logout">Sair</a></li>
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
                <input type="hidden" name="action" id="actionp">
                <label>Paciente ou Profissional:</label>
                <input type="text" name="nome" placeholder="Digite para pesquisar..." required>
                <div class="buttons">
                    <button type="submit" class="btn-primary" onclick="document.getElementById('action').value='pesquisar'">Buscar</button>
                </div>
            </form>
            <%
                try{
                    List<Agenda> resultados = (List<Agenda>) request.getAttribute("resultados");
                    String nome = (String) request.getAttribute("nome");
                    if (resultados != null && !resultados.isEmpty()) {
            %>
            <div class="search-results">
                <h3>Resultados da pesquisa por "<%= nome %>":</h3>
                <table>
                    <tr>
                        <th>Paciente</th>
                        <th>Profissional</th>
                        <th>Data</th>
                        <th>Hora</th>
                    </tr>
                    <%
                        if (resultados.isEmpty()) {
                    %>
                    <tr><td colspan="4">Nenhum agendamento encontrado.</td></tr>
                    <%
                    } else {
                        for (Agenda a : resultados) {
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
    </div>
</main>
</body>
</html>