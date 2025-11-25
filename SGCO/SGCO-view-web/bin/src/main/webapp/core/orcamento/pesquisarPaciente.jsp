<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="sgco.model.domain.Paciente"%>

<section class="card">
    <h2>Pesquisar Paciente</h2>
    
    <form id="searchForm" action="${pageContext.request.contextPath}/PacienteController" method="post">
    <input type="hidden" name="acao" value="pesquisar">
    <input type="hidden" name="origem" value="orcamento"> 

    <div class="form-group">
        <label for="search">Nome do Paciente</label>
        <input type="text" id="search" name="search" placeholder="Digite o nome para pesquisar..." required>
    </div>

    <button type="submit" class="btn-search">Buscar</button>
</form>

    <div class="search-results">
        <%
            List<Paciente> resultados = (List<Paciente>) request.getAttribute("resultadosPacientes");

            if (resultados != null) {
                if (!resultados.isEmpty()) {
        %>
                    <h3 class="result-title">Pacientes encontrados:</h3>
                    <div class="result-list">
                        <% for (Paciente p : resultados) { %>
                            <div class="result-item">
                                <div class="result-info">
                                    <strong><%= p.getNome() %></strong><br>
                                    <span>CPF: <%= p.getCpf() %></span>
                                </div>

                                <form action="${pageContext.request.contextPath}/OrcamentoController" method="post" class="select-form">
                                    <input type="hidden" name="acao" value="selecionarPaciente">
                                    <input type="hidden" name="id_paciente" value="<%= p.getId() %>">
                                    <button type="submit" class="btn-select">Selecionar</button>
                                </form>
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
