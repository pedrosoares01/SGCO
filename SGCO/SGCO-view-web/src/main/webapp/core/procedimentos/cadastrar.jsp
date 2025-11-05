<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

<section class="card">
	<h2>Cadastro de Procedimento</h2>
	<form id="procedimentoForm" action="/SGCO-view-web/CadastrarProcedimento" method="post">
		<div class="form-group">
			<label for="procedimento">Procedimento</label>
			<input type="text" id="procedimento" name="procedimento" placeholder="Nome do procedimento" required>
		</div>

		<div class="form-group">
			<label for="preco">Preço</label>
			<input type="number" id="preco" name="preco" step="0.01" placeholder="Valor do procedimento" required>
		</div>

		<div class="buttons">
			<button type="submit" class="btn-primary">Cadastrar</button>
			<button type="button" class="btn-secondary" onclick="limparFormulario()">Limpar</button>
		</div>
	</form>
</section>

<script>
    function limparFormulario() {
        document.getElementById("procedimentoForm").reset();
    }
</script>