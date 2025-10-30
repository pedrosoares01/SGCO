<%@ page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agenda - SGCO</title>
    <link rel="stylesheet" href="agenda.css">
    <link rel="stylesheet" href="../sidebar.css">
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
<%
String paciente, profissional, data, hora;
paciente = request.getParameter("paciente");
profissional = request.getParameter("profissional");
data = request.getParameter("data");
hora = request.getParameter("hora");
Connection connection;
PreparedStatement st;
Class.forName("com.mysql.cj.jdbc.Driver");
connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda","root","sgcopass");
st = connection.prepareStatement("INSERT INTO agenda VALUES(?,?,?,?)");
st.setString(1, paciente);
st.setString(2, profissional);
st.setString(3, data);
st.setString(4, hora);
st.executeUpdate();
%>
<main class="content">
    <h1>Agenda</h1>
    <div class="container">
        <section class="card">
            <h2>Agendar Consulta</h2>
            <form id="agendaForm" action ="agenda.jsp" method="POST">
                <label>Paciente:</label>
                <input type="text" name="paciente" placeholder="Nome do paciente" required>
                <label>Profissional:</label>
                <input type="text" name="profissional" placeholder="Nome do profissional" required>
                <label>Data:</label>
                <input type="date" name="data" required>
                <label>Hora:</label>
                <input type="time" name="hora" required>
                <div class="buttons">
                    <button type="submit" class="btn-primary">Agendar</button>
                </div>
            </form>
        </section>

        <!-- Pesquisar Agendamento -->
        <section class="card">
            <h2>🔍 Pesquisar Agendamento</h2>
            <form id="searchForm">
                <label>Paciente ou Profissional:</label>
                <input type="text" name="search" placeholder="Digite para pesquisar...">
                <div class="buttons">
                    <button type="submit" class="btn-primary">Buscar</button>
                </div>
            </form>

            <!-- Caixa de resultados dentro do card -->
            <div class="search-results">
                <p class="info-text">Resultados da pesquisa aparecerão aqui</p>
            </div>
        </section>
    </div>
</main>

</body>
</html>