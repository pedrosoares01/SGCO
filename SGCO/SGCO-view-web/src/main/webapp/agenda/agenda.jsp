<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="sgco.sgco.domain.Agenda"%>
<%@ page import="sgco.sgco.domain.Usuario" %>
<%@ page import="java.sql.ResultSet" %>

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
    <h1>Agenda</h1>
    <div class="container">
        <section class="card">
            <h2>Agendar Consulta</h2>
            <form id="agendaForm" method="POST" action ="${pageContext.request.contextPath}/AgendaController">
                <input type="hidden" name="action" id="action">
                <label>Paciente:</label>
                <input type="text" name="paciente" placeholder="Nome do paciente" required>
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
                <input type="text" name="profissional" placeholder="Nome do profissional" required>
                <label>Data:</label>
                <input type="date" id="data" name="data">
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
                String mensagemListar = (String) request.getAttribute("mensagem");
                String tipoMensagemListar = (String) request.getAttribute("tipoMensagem");
            %>

            <% if (mensagemListar != null) { %>
            <div id="popup" class="<%= tipoMensagemListar %>">
                <p><%= mensagemListar %></p>
                <button onclick="fecharPopup()">OK</button>
            </div>

            <script>
                function fecharPopup() {
                    document.getElementById("popup").style.display = "none";
                }
            </script>
            <% } %>


        </section>

        <section class="card">
            <h3>Horarios Disponiveis</h3>

            <form method="GET" action="${pageContext.request.contextPath}/AgendaController">
                <input type="hidden" name="action" value="horarios">
                <label>Profissional:</label>
                <%
                    List<Usuario> profissionais2 = (List<Usuario>) request.getAttribute("profissionais");
                    String selProf = request.getParameter("profissional");
                %>
                <select name="profissional" onchange="this.form.submit()">
                    <option value="">Selecione um profissional</option>
                    <%
                        if (profissionais2 != null) {
                            for (Usuario p : profissionais2) {
                    %>
                    <option value="<%= p.getNome() %>" <%= (selProf != null && selProf.equals(p.getNome())) ? "selected" : "" %>><%= p.getNome() %></option>
                    <%
                            }
                        }
                    %>
                </select>

                <label>Data:</label>
                <input type="date" name="data" onchange="this.form.submit()" value="<%= request.getParameter("data") != null ? request.getParameter("data") : "" %>">
            </form>

            <div id="horarios-container">
                <table class="horarios-table">
                    <tr><th>Horario</th><th>Status</th></tr>
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
                            boolean ocupado = ocupados.stream().anyMatch(a -> {
                                if (a.getHora() == null) return false;
                                String hSql = a.getHora().toString().substring(0,5);
                                return hSql.equals(hora);
                            });
                    %>
                    <tr>
                        <td><%= hora %></td>
                        <td>
                            <% if (ocupado) { %>
                            <span class="status-ocupado">OCUPADO</span>
                            <% } else { %>
                            <span class="status-livre">LIVRE</span>
                            <% } %>
                        </td>
                    </tr>
                    <% } %>
                </table>
            </div>
        </section>

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
    </div>
</main>
</body>

</html>