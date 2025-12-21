<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="sgco.sgco.domain.Agenda" %>

<div class="horarios-container">
  <table class="horarios-table">
    <tr>
      <th>Horário</th>
      <th>Status</th>
    </tr>

    <%
      List<Agenda> ocupados = (List<Agenda>) request.getAttribute("ocupados");
      if (ocupados == null) ocupados = new ArrayList<>();

      List<String> horarios = new ArrayList<>();

      java.time.LocalTime h = java.time.LocalTime.of(8,0);
      while (!h.isAfter(java.time.LocalTime.of(11,30))) {
        horarios.add(h.toString().substring(0,5));
        h = h.plusMinutes(30);
      }

      h = java.time.LocalTime.of(14,0);
      while (!h.isAfter(java.time.LocalTime.of(17,30))) {
        horarios.add(h.toString().substring(0,5));
        h = h.plusMinutes(30);
      }

      for (String hora : horarios) {
        boolean ocupado = ocupados.stream()
                .anyMatch(a -> a.getHora().substring(0,5).equals(hora));
    %>

    <tr>
      <td><%= hora %></td>
      <td>
        <% if (ocupado) { %>
        <span class="status-ocupado">OCUPADO</span>
        <% } else { %>
        <span class="status-livre" data-hora="<%= hora %>">LIVRE</span>
        <% } %>
      </td>
    </tr>

    <% } %>

  </table>
</div>
