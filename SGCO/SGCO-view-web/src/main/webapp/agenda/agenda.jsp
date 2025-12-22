<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="sgco.sgco.domain.Agenda"%>
<%@ page import="sgco.sgco.domain.Usuario" %>

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
        <li><a href="${pageContext.request.contextPath}/PacientesAgendadosController">Pacientes Agendados</a></li>
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
                <select id="profissional" name="profissional" onchange="carregarHorarios()">
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
                <label>Data:</label>
                <input type="date" id="data" name="data" onchange="carregarHorarios()" required>
                <label>Hora:</label>
                <select name="hora" id="hora" required>
                    <option value="">Selecione um horário</option>
                </select>

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
                <label>Profissional:</label>
                <%
                    try{
                        List<Usuario> profissionais = (List<Usuario>) request.getAttribute("profissionais");
                %>
                <select id="profissional2" name="profissional2">
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
            <h2>Horários Disponíveis</h2>

            <div id="horarios-container" style="margin-top:20px; display:none;"></div>
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
    <script>
        function carregarHorarios() {
            const profissional = document.getElementById("profissional").value;
            const data = document.getElementById("data").value;
            if (!profissional || !data) {
                return;
            }
            fetch("AgendaController?action=horarios"
                + "&profissional=" + encodeURIComponent(profissional)
                + "&data=" + data
            )
                .then(response => response.text())
                .then(html => {
                    const container = document.getElementById("horarios-container");
                    container.innerHTML = html;
                    container.style.display = "block";
                    const selectHora = document.getElementById("hora");
                    selectHora.innerHTML = '<option value="">Selecione um horário</option>';
                    const livres = container.querySelectorAll(".status-livre");
                    livres.forEach(span => {
                        const hora = span.dataset.hora;

                        if (hora) {
                            const option = document.createElement("option");
                            option.value = hora;
                            option.textContent = hora;
                            selectHora.appendChild(option);
                        }
                    });
                    if (livres.length === 0) {
                        const option = document.createElement("option");
                        option.textContent = "Nenhum horário disponível";
                        option.disabled = true;
                        selectHora.appendChild(option);
                    }
                })
                .catch(error => {
                    console.error("Erro ao carregar horários:", error);
                });
        }
    </script>

</main>
</body>

</html>