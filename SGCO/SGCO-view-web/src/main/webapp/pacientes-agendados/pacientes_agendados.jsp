<%@ page import="sgco.sgco.domain.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page import="sgco.sgco.domain.Agenda" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Pacientes Agendados - SGCO</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pacientes_agendados.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
</head>
<body>
<aside class="sidebar">
  <a href="${pageContext.request.contextPath}/index.jsp"><h2>SGCO</h2></a>
  <ul>
    <li><a href="${pageContext.request.contextPath}/core/paciente/pagina.jsp">Gestão de Pacientes</a></li>
    <li><a href="${pageContext.request.contextPath}/gestaoUsuarios/gestao_usuarios.jsp">Gestão de Usuários</a></li>
    <li><a href="${pageContext.request.contextPath}/core/procedimentos/pagina.jsp">Gestão de Procedimentos</a></li>
    <li><a href="${pageContext.request.contextPath}/estoque.html">Controle de Estoque</a></li>
    <li><a href="${pageContext.request.contextPath}/fornecedores_materiais.html">Fornecedores de Materiais</a></li>
    <li><a href="${pageContext.request.contextPath}/fornecedores_servicos.html">Fornecedores de Serviços</a></li>
    <li><a href="${pageContext.request.contextPath}/receita.html">Gestão da Receita</a></li>
    <li><a href="${pageContext.request.contextPath}/pacientes-agendados/pacientes_agendados.jsp" class="active">Pacientes Agendados</a></li>
    <li><a href="${pageContext.request.contextPath}/LogoutController" class="logout">Sair</a></li>
  </ul>
</aside>

<main class="content">
  <h1>Pacientes Agendados</h1>

  <div class="container">
    <section class="card">
      <h2>Relatórios de Agendamentos</h2>
      <p>Visualize e gere relatórios dos pacientes com consultas agendadas.</p>
      <form method="POST" action="${pageContext.request.contextPath}/PacientesAgendadosController">
      <label>Profissional:</label>
      <%
        try{
          List<Usuario> profissionais = (List<Usuario>) request.getAttribute("profissionais");
      %>
      <select id="profissional" name="profissional">
        <%
          if (profissionais == null || profissionais.isEmpty()) {
        %>
        <option value="">Nenhum profissional encontrado</option>
        <%
        } else {
        %>
        <option value="">Selecione um profissional:</option>
        <%
          for (Usuario p : profissionais) {
        %>
        <option value="<%= p.getNome() %>"><%= p.getNome() %></option>
        <%
            }
          }
        %>
      </select>
      <%
        } catch (Exception e){}
      %>
      <div class="buttons">
        <button type="submit" class="btn-primary" onclick="document.getElementById('action').value='gerar-relatorio'">Gerar Relatorio</button>
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
