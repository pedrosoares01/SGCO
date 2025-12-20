<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="sgco.model.domain.Orcamento"%>

<section class="card">
    <h2>Selecionar Orçamento</h2>

    <form id="searchForm" action="${pageContext.request.contextPath}/OrcamentoController" method="post">
        <input type="hidden" name="acao" value="pesquisar">
        <input type="hidden" name="origem" value="pagamento">

        <div class="form-group">
            <label for="searchId">ID do Orçamento</label>
            <input type="number" name="searchId" id="searchId" placeholder="Digite o ID do orçamento..." required>
        </div>

        <button type="submit" class="btn-search">Buscar</button>
    </form>

    <div class="search-results" style="margin-top: 1.5rem;">
        <%
            List<Orcamento> resultados = (List<Orcamento>) request.getAttribute("resultados");

            if (resultados != null) {
                if (!resultados.isEmpty()) {
        %>

        <h3 class="result-title">Resultados encontrados:</h3>

        <div class="result-list">
            <% for (Orcamento o : resultados) { %>
            <div class="result-item">
                <div class="result-info">
                    <strong><%= o.getDescricao() %></strong><br>
                    <span>Valor: R$ <%= String.format("%.2f", o.getValor()) %></span><br>
                    <small>
                        Profissional: <%= (o.getNomeProfissional() != null ? o.getNomeProfissional() : "N/A") %> |
                        Paciente: <%= (o.getNomePaciente() != null ? o.getNomePaciente() : "N/A") %>
                    </small>
                </div>

                <div class="result-actions" style="display:flex; gap:0.5rem;">

                    <form action="${pageContext.request.contextPath}/PagamentoController" method="post">
                        <input type="hidden" name="acao" value="selecionarOrcamento">
                        <input type="hidden" name="orcamentoId" value="<%= o.getId() %>">

                        <button type="submit" class="btn-primary">
                            Selecionar
                        </button>
                    </form>

                </div>
            </div>
            <% } %>
        </div>

        <%  } else { %>
        <p class="no-results">Nenhum orçamento encontrado.</p>
        <% } %>

        <% } else { %>
        <p class="info-text">Pesquise um orçamento para ver os resultados.</p>
        <% } %>
    </div>
    <jsp:include page="formaDePagamento.jsp"/>
</section>