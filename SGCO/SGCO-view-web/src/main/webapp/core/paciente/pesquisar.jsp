<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="sgco.model.domain.Paciente" %>

<section class="card">
    <h2>Pesquisar Paciente</h2>

    <form id="searchForm" action="${pageContext.request.contextPath}/PacienteController" method="post">
        <input type="hidden" name="acao" value="pesquisar">

        <div class="form-group">
            <label for="searchPaciente">Nome do Paciente</label>
            <input type="text" id="searchPaciente" name="searchPaciente"
                   placeholder="Digite o nome para pesquisar..." required>
        </div>

        <button type="submit" class="btn-primary">Buscar</button>
    </form>

    <div class="search-results">
        <%
            List<Paciente> resultados = (List<Paciente>) request.getAttribute("resultados");
            if (resultados != null) {
                if (!resultados.isEmpty()) {
        %>
                    <h3 class="result-title">Resultados encontrados:</h3>
                    <div class="result-list">
                        <% for (Paciente p : resultados) { %>
                            <div class="result-item">
                                <div class="result-info">
                                    <strong><%= p.getNome() %></strong>
                                    <span>CPF: <%= p.getCpf() %></span>
                                </div>

                                <div class="result-actions">
                                    <form action="${pageContext.request.contextPath}/PacienteController" method="get">
                                        <input type="hidden" name="acao" value="editar">
                                        <input type="hidden" name="id" value="<%= p.getId() %>">
                                        <button type="submit" class="btn-secondary">Editar</button>
                                    </form>
                                </div>
                            </div>
                        <% } %>
                    </div>
        <%
                } else {
        %>
                    <p class="no-results">Nenhum paciente encontrado.</p>
        <%
                }
            } else {
        %>
                <p class="info-text">Digite o nome e clique em <strong>Buscar</strong> para ver os resultados.</p>
        <%
            }
        %>
    </div>
</section>