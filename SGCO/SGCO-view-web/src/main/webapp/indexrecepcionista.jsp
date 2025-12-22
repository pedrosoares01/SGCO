<%@ page import="sgco.controller.LoginController" %>
<%@page contentType ="text/html" pageEncoding="UTF-8"%>

<% LoginController.validarSessao(request,response);%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>SGCO - Recepcionista</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
  <aside class="sidebar">
    <a href="${pageContext.request.contextPath}/indexrecepcionista.jsp"><h2>SGCO</h2></a>
    <ul>
      <li><a href="${pageContext.request.contextPath}/core/paciente/pagina.jsp">Gestão de Pacientes</a></li>
      <li><a href="${pageContext.request.contextPath}/AgendaController">Agenda</a></li>
      <li><a href="${pageContext.request.contextPath}/core/pagamento/pagina.jsp">Pagamentos</a></li>
      <li><a href="${pageContext.request.contextPath}/PacientesAgendadosController">Pacientes Agendados</a></li>
      <li><a href="${pageContext.request.contextPath}/AvaliacaoController">Avaliação de Profissionais</a></li>
      <li><a href="${pageContext.request.contextPath}/LogoutController" class="logout">Sair</a></li>
    </ul>
  </aside>

  <main class="content">
    <h1>Bem-vindo(a), Recepcionista</h1>
    <p>Selecione uma das opções ao lado para gerenciar o consultório.</p>

    <div class="cards">
      <div class="card">
        <h3>👥 Pacientes</h3>
        <p>Gerencie cadastros e informações dos pacientes.</p>
        <a href="${pageContext.request.contextPath}/core/paciente/pagina.jsp">Acessar</a>
      </div>

      <div class="card">
        <h3>📅 Agenda</h3>
        <p>Agende e visualize consultas marcadas.</p>
        <a href="${pageContext.request.contextPath}/AgendaController">Acessar</a>
      </div>

      <div class="card">
        <h3>💳 Recebimento</h3>
        <p>Registre os pagamentos realizados pelos pacientes.</p>
        <a href="${pageContext.request.contextPath}/core/pagamento/pagina.jsp">Acessar</a>
      </div>

      <div class="card">
        <h3>📋 Pacientes Agendados</h3>
        <p>Veja a lista de pacientes com consultas marcadas.</p>
        <a href="${pageContext.request.contextPath}/PacientesAgendadosController">Acessar</a>
      </div>

      <div class="card">
        <h3>⭐ Avaliações</h3>
        <p>Permita que os pacientes avaliem os profissionais.</p>
        <a href="${pageContext.request.contextPath}/AvaliacaoController">Acessar</a>
      </div>
    </div>
  </main>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
</body>
</html>