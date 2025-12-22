<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Gestão da Receita</h1>

<section class="card">
    <h2>Relatório de Receitas</h2>

    <table style="width:100%; border-collapse: collapse;">
        <thead>
            <tr>
                <th>Descrição</th>
                <th>Valor (R$)</th>
                <th>Forma de Pagamento</th>
                <th>Data</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="r" items="${listaReceitas}">
                <tr>
                    <td>${r.descricao}</td>
                    <td>${r.valor}</td>
                    <td>${r.formaPagamento}</td>
                    <td>${r.data}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <c:if test="${empty listaReceitas}">
        <p class="info-text">Nenhuma receita encontrada </p>
    </c:if>
</section>
