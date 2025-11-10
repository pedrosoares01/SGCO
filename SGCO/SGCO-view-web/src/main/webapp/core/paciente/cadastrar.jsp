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
    <h2>Cadastro de Paciente</h2>

    <form id="patientForm" action="${pageContext.request.contextPath}/PacienteController" method="post">
        <input type="hidden" name="acao" value="cadastrar">

        <div class="form-group">
            <label for="nome">Nome</label>
            <input type="text" id="nome" name="nome" placeholder="Nome completo do paciente" required>
        </div>

        <div class="form-group">
            <label for="cpf">CPF</label>
            <input type="text" id="cpf" name="cpf" placeholder="000.000.000-00" required>
        </div>

        <div class="form-group">
            <label for="endereco">Endereço</label>
            <input type="text" id="endereco" name="endereco" placeholder="Rua, número, bairro, cidade">
        </div>

        <div class="form-group">
            <label for="telefone">Telefone</label>
            <input type="tel" id="telefone" name="telefone" placeholder="(00) 00000-0000">
        </div>

        <div class="form-group">
            <label for="email">E-mail</label>
            <input type="email" id="email" name="email" placeholder="email@exemplo.com">
        </div>

        <div class="buttons">
            <button type="submit" class="btn-primary">Cadastrar</button>
            <button type="button" class="btn-secondary" onclick="limparFormulario()">Limpar</button>
        </div>
    </form>
</section>

<script>
    function limparFormulario() {
        document.getElementById("patientForm").reset();
    }
</script>