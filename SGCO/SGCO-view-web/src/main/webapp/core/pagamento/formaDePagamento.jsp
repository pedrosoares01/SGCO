<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
    String mensagem = (String) request.getAttribute("mensagem");
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

<div class="select-list">

    <c:if test="${not empty orcamento}">

        <fmt:formatNumber value="${orcamento.valor}" pattern="##0.00" var="valorFormatado" />

        <div class="info-box" style="margin-bottom: 1rem; padding: 1rem; background: #f5f5f5; border-radius: 8px;">
            <strong>Orçamento selecionado:</strong><br>
            <span>Descrição: ${orcamento.descricao}</span><br>
            <span>Valor total: R$ ${fn:replace(valorFormatado, ".", ",")}</span>
        </div>

        <form action="${pageContext.request.contextPath}/PagamentoController" method="post">
            <input type="hidden" name="acao" value="salvar" />
            <input type="hidden" name="orcamentoId" value="${orcamento.id}" />

            <label>Selecione a forma de pagamento:</label>
            <select name="formaPagamento" required>
                <option value="Dinheiro">Dinheiro</option>
                <option value="Crédito">Cartão de Crédito</option>
                <option value="Débito">Cartão de Débito</option>
                <option value="Pix">Pix</option>
            </select>

            <br><br>

            <button type="submit" class="btn-primary">Confirmar Pagamento</button>
        </form>

    </c:if>

</div>