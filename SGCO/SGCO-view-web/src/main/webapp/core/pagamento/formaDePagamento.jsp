<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div class = "select-list">
		<c:if test="${not empty orcamento}">
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
