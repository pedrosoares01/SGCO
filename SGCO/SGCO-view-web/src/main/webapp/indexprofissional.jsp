<%@ page import="sgco.controller.LoginController" %>
<%@page contentType ="text/html" pageEncoding="UTF-8"%>

<% LoginController.validarSessao(request,response);%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>SGCO - Profissional</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
  <aside class="sidebar">
    <a href="${pageContext.request.contextPath}/indexprofissional.jsp"><h2>SGCO</h2></a>
    <ul>
      <li><a href="${pageContext.request.contextPath}/core/orcamento/pagina.jsp">Orçamento</a></li>
      <li><a href="estoque.jsp">Controle de Estoque</a></li>
      <li><a href="${pageContext.request.contextPath}/ProntuarioController">Prontuário</a></li>
      <li><a href="${pageContext.request.contextPath}/pacientes-agendados/pacientes_agendados.jsp">Pacientes Agendados</a></li>
      <li><a href="${pageContext.request.contextPath}/LogoutController" class="logout">Sair</a></li>
    </ul>
  </aside>

  <main class="content">
    <h1>Bem-vindo(a), Profissional</h1>
    <p>Selecione uma das opções ao lado para acessar suas funções no sistema.</p>

    <div class="cards">
      <div class="card">
        <h3>💰 Orçamento</h3>
        <p>Crie orçamentos com base nos procedimentos cadastrados.</p>
        <a href="${pageContext.request.contextPath}/core/orcamento/pagina.jsp">Acessar</a>
      </div>

      <div class="card">
        <h3>📦 Controle de Estoque</h3>
        <p>Verifique ou atualize os materiais disponíveis no consultório.</p>
        <a href="estoque.jsp">Acessar</a>
      </div>

      <div class="card">
        <h3>🩺 Prontuário</h3>
        <p>Atualize os prontuários dos pacientes com os procedimentos realizados.</p>
        <a href="prontuario.jsp">Acessar</a>
      </div>

      <div class="card">
        <h3>📅 Pacientes Agendados</h3>
        <p>Visualize a agenda de consultas e os pacientes marcados.</p>
        <a href="${pageContext.request.contextPath}/pacientes-agendados/pacientes_agendados.jsp">Acessar</a>
      </div>
    </div>
  </main>
</body>
</html>