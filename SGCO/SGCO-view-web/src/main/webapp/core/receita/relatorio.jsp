<%@ page import="java.util.List, model.domain.Despesa" %>

<h1>Relatório de Despesas</h1>

<c:forEach var="d" items="${lista}">
    <div class="result-item">
        <span>${d.descricao} - R$ ${d.valor}</span>

        <a href="editar.jsp?id=${d.id}">Editar</a>
        <form method="post" action="DespesaController">
            <input type="hidden" name="acao" value="excluir">
            <input type="hidden" name="id" value="${d.id}">
            <button class="btn-secondary">Excluir</button>
        </form>
    </div>
</c:forEach>
