<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="sgco.model.domain.Procedimento" %>

<section class="card">
    <h2>Pesquisar Procedimento</h2>
    <form id="searchForm" action="${pageContext.request.contextPath}/PesquisarProcedimento" method="post">
        <div class="form-group">
            <label for="searchProcedimento">Nome do Procedimento</label>
            <input type="text" id="searchProcedimento" name="searchProcedimento" placeholder="Digite o nome para pesquisar..." required>
        </div>

        <button type="submit" class="btn-search">Buscar</button>
    </form>

   <div class="search-results">
	    <%
	        List<Procedimento> resultados = (List<Procedimento>) request.getAttribute("resultados");
	
	        if (resultados != null) {
	            if (!resultados.isEmpty()) {
	    %>
	                <h3 class="result-title">Resultados encontrados:</h3>
	                <div class="result-list">
	                    <% for (Procedimento p : resultados) { %>
	                        <div class="result-item">
	                            <strong><%= p.getNome() %></strong> R$ <%= String.format("%.2f", p.getPreco()) %>
	                        </div>
	                    <% } %>
	                </div>
	    <%
	            } else {
	    %>
	                <p class="no-results">Nenhum procedimento encontrado.</p>
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