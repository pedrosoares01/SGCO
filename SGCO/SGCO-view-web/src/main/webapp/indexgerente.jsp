<%@ page import="sgco.controller.LoginController" %>
<%@ page import="sgco.sgco.domain.Usuario" %>
<%@page contentType ="text/html" pageEncoding="UTF-8"%>

<% LoginController.validarSessao(request,response);%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>SGCO - Menu Principal</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
  <aside class="sidebar">
    <a href="${pageContext.request.contextPath}/indexgerente.jsp"><h2>SGCO</h2></a>
    <ul>
      <li><a href="${pageContext.request.contextPath}/core/paciente/pagina.jsp">Gestão de Pacientes</a></li>
      <li><a href="${pageContext.request.contextPath}/gestaoUsuarios/gestao_usuarios.jsp">Gestão de Usuários</a></li>
      <li><a href="${pageContext.request.contextPath}/core/procedimentos/pagina.jsp">Gestão de Procedimentos</a></li>
      <li><a href="${pageContext.request.contextPath}/estoque/estoque.jsp">Controle de Estoque</a></li>
      <li><a href="${pageContext.request.contextPath}/fornecedores_materiais/fornecedores_materiais.jsp">Fornecedores de Materiais</a></li>
      <li><a href="${pageContext.request.contextPath}/fornecedores_servicos/fornecedores_servicos.jsp">Fornecedores de Serviços</a></li>
      <li><a href="${pageContext.request.contextPath}/FinanceiroController">Gestão da Receita</a></li>
      <li><a href="${pageContext.request.contextPath}/saldo/saldo.jsp">Saldo</a></li>
      <li><a href="${pageContext.request.contextPath}/PacientesAgendadosController">Pacientes Agendados</a></li>
      <li><a href="${pageContext.request.contextPath}/LogoutController" class="logout">Sair</a></li>
    </ul>
  </aside>

  <main class="content">
    <h1>Bem-vindo(a), Gerente</h1>
    <p>Selecione uma das funcionalidades abaixo para gerenciar o consultório.</p>

    <div class="cards">
      <div class="card">
        <h3>👥 Gestão de Pacientes</h3>
        <p>Cadastre, atualize e consulte informações dos pacientes.</p>
        <a href="${pageContext.request.contextPath}/core/paciente/pagina.jsp">Acessar</a>
      </div>

      <div class="card">
        <h3>🧑‍💼 Gestão de Usuários</h3>
        <p>Gerencie recepcionistas, profissionais e permissões de acesso.</p>
        <a href="${pageContext.request.contextPath}/gestaoUsuarios/gestao_usuarios.jsp">Acessar</a>
      </div>

      <div class="card">
        <h3>🦷 Procedimentos</h3>
        <p>Cadastre e edite os procedimentos realizados no consultório.</p>
        <a href="${pageContext.request.contextPath}/core/procedimentos/pagina.jsp">Acessar</a>
      </div>

      <div class="card">
        <h3>📦 Estoque</h3>
        <p>Gerencie materiais e produtos disponíveis no consultório.</p>
        <a href="${pageContext.request.contextPath}/estoque/estoque.jsp">Acessar</a>
      </div>

      <div class="card">
        <h3>🚚 Fornecedores de Materiais</h3>
        <p>Cadastre e gerencie fornecedores de materiais .</p>
        <a href="${pageContext.request.contextPath}/fornecedores_materiais/fornecedores_materiais.jsp">Acessar</a>
      </div>

<div class="card">
        <h3>🚚 Fornecedores de Serviços</h3>
        <p>Cadastre e gerencie fornecedores de serviços.</p>
        <a href="${pageContext.request.contextPath}/fornecedores_servicos/fornecedores_servicos.jsp">Acessar</a>
      </div>

      <div class="card">
        <h3>📈 Receita</h3>
        <p>Visualize relatórios de despesas e receitas do consultório.</p>
        <a href="${pageContext.request.contextPath}/FinanceiroController">Acessar</a>
      </div>

        <div class="card">
            <h3>📈 Saldo</h3>
            <p>Visualize os pacientes devedores.</p>
            <a href="${pageContext.request.contextPath}/saldo/saldo.jsp">Acessar</a>
        </div>

      <div class="card">
        <h3>📋 Pacientes Agendados</h3>
        <p>Consulte a lista de pacientes com consultas marcadas.</p>
        <a href="${pageContext.request.contextPath}/pacientes-agendados/pacientes_agendados.jsp">Acessar</a>
      </div>

    </div>
  </main>
</body>
</html>