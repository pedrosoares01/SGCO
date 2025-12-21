<%@ page import="java.util.List" %>
<%@ page import="sgco.sgco.domain.Avaliacao" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Avaliação de Profissionais - SGCO</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/agenda.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/avaliacao.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
</head>
<body>
<aside class="sidebar">
  <a href="${pageContext.request.contextPath}/indexrecepcionista.jsp"><h2>SGCO</h2></a>
  <ul>
    <li><a href="${pageContext.request.contextPath}/core/paciente/pagina.jsp">Gestão de Pacientes</a></li>
    <li><a href="${pageContext.request.contextPath}/AgendaController">Agenda</a></li>
    <li><a href="${pageContext.request.contextPath}/core/pagamento/pagina.jsp">Pagamentos</a></li>
    <li><a href="${pageContext.request.contextPath}/pacientes-agendados/pacientes_agendados.jsp">Pacientes Agendados</a></li>
    <li><a href="${pageContext.request.contextPath}/AvaliacaoController">Avaliação de Profissionais</a></li>
    <li><a href="${pageContext.request.contextPath}/LogoutController" class="logout">Sair</a></li>
  </ul>
</aside>
  <main class="content">
    <h1>Avaliação de Profissionais</h1>

    <div class="container">
      <section class="card">
        <form method="POST" action ="${pageContext.request.contextPath}/AvaliacaoController">
          <label>Profissional:</label>
          <input type="text" placeholder="Digite o nome do profissional" name="profissional">

          <label>Nota:</label>
          <select name="nota">
            <option value="1">1 - Péssimo</option>
            <option value="2">2 - Ruim</option>
            <option value="3">3 - Regular</option>
            <option value="4">4 - Bom</option>
            <option value="5">5 - Excelente</option>
          </select>

          <div class="buttons">
            <button type="submit">Avaliar</button>
          </div>
        </form>
      </section>
    </div>
    <%
      try {
        List<Avaliacao> avaliacoes = (List<Avaliacao>) request.getAttribute("avaliacoes");
        if (avaliacoes != null && !avaliacoes.isEmpty()) {
    %>
    <div class="search-results">
      <h3>Avaliações</h3>
      <table>
        <tr>
          <th>Profissonal</th>
          <th>Nota</th>
        </tr>
        <%
          if (avaliacoes.isEmpty()) {
        %>
        <tr><td colspan="4">Nenhuma avaliação encontrada.</td></tr>
        <%
        } else {
            for (Avaliacao av : avaliacoes){
        %>
        <tr>
          <td><%= av.getProfissional() %></td>
          <td><%= av.getNota() %></td>
        </tr>
        <%
            }
          }
        %>
      </table>
      <div class="buttons">
        <button type="button" id="btnMedia">Calcular Médias</button>
    </div>
    <%
        }
      } catch (Exception e){}
    %>
  </main>
  <script src="${pageContext.request.contextPath}/avaliacao/avaliacao.js"></script>
</body>
</html>
