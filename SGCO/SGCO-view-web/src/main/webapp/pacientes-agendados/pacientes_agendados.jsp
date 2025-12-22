<%@ page import="sgco.sgco.domain.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page import="sgco.sgco.domain.Agenda" %>
<%@ page import="sgco.controller.LoginController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% LoginController.validarSessao(request,response);%>
<%
  Usuario u;
  u = (Usuario) session.getAttribute("usuarioLogado");
  String cargo = u.getCargo();
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Pacientes Agendados - SGCO</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pacientes_agendados.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
</head>
<body>
<aside class="sidebar">
  <%if (cargo.equals("Gerente")){%>
  <a href="${pageContext.request.contextPath}/indexgerente.jsp"><h2>SGCO</h2></a>
  <%} else if (cargo.equals("Recepcionista")){%>
  <a href="${pageContext.request.contextPath}/indexrecepcionista.jsp"><h2>SGCO</h2></a>
  <%} else if (cargo.equals("Profissional")){%>
  <a href="${pageContext.request.contextPath}/indexprofissional.jsp"><h2>SGCO</h2></a>
  <%} else {%>
  <a href="${pageContext.request.contextPath}/index.jsp"></a>
  <%}%>
  <ul>
    <%if (cargo.equals("Gerente")){%>
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
    <%} else if (cargo.equals("Profissional")){%>
    <li><a href="${pageContext.request.contextPath}/core/orcamento/pagina.jsp">Orçamento</a></li>
    <li><a href="${pageContext.request.contextPath}/estoque/estoque.jsp">Controle de Estoque</a></li>
    <li><a href="${pageContext.request.contextPath}/ProntuarioController">Prontuário</a></li>
    <li><a href="${pageContext.request.contextPath}/PacientesAgendadosController">Pacientes Agendados</a></li>
    <li><a href="${pageContext.request.contextPath}/LogoutController" class="logout">Sair</a></li>
    <%} else if (cargo.equals("Recepcionista")){%>
    <li><a href="${pageContext.request.contextPath}/core/paciente/pagina.jsp">Gestão de Pacientes</a></li>
    <li><a href="${pageContext.request.contextPath}/AgendaController">Agenda</a></li>
    <li><a href="${pageContext.request.contextPath}/core/pagamento/pagina.jsp">Pagamentos</a></li>
    <li><a href="${pageContext.request.contextPath}/PacientesAgendadosController">Pacientes Agendados</a></li>
    <li><a href="${pageContext.request.contextPath}/AvaliacaoController">Avaliação de Profissionais</a></li>
    <li><a href="${pageContext.request.contextPath}/LogoutController" class="logout">Sair</a></li>
    <%} else {%>
    <li><a href="${pageContext.request.contextPath}/LogoutController" class="logout">Sair</a></li>
    <%}%>
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
            <th>Ação</th>
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
            <td>
              <form method="post" action="${pageContext.request.contextPath}/PacientesAgendadosController" onsubmit="return confirm('Deseja realmente desmarcar este agendamento?');" style="display:inline;">
                <input type="hidden" name="action" value="desmarcar">
                <input type="hidden" name="id" value="<%= a.getId() %>">
                <button type="submit" class="btn-danger">Desmarcar</button>
              </form>
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
    </section>
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
</main>
</body>
</html>