<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Controle de Equipamentos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/controleequip.css">
</head>
<body>

<%
String popup = (String) session.getAttribute("popup");
if (popup != null) {
%>
<script>
    alert("<%= popup %>");
</script>
<%
    session.removeAttribute("popup");
}
%>

<div class="content">
    <h1>Controle de Equipamentos</h1>

    <div class="container">
        <div class="card big-card">
            <h2>Equipamentos Cadastrados</h2>

<form action="${pageContext.request.contextPath}/EquipamentoServlet" method="get">
    <p class="info-text">
        Digite o código do equipamento e clique em <strong>Buscar</strong> para ver os resultados.
    </p>
    <input type="text" name="codigo" placeholder="Digite o código">
    <button type="submit" class="btn-primary">Buscar</button>
</form>

<%@ page import="sgco.model.domain.Equipamentos" %>
<%
    Equipamentos eq = (Equipamentos) request.getAttribute("equipamento");
    if (eq != null) {
%>

    <h3>Resultado da Busca</h3>
    <table border="1" id="tabela-equip">
        <tbody>
            <tr><th>ID</th><td><%= eq.getId() %></td></tr>
            <tr><th>Nome</th><td><%= eq.getNome() %></td></tr>
            <tr><th>Código</th><td><%= eq.getCodigo() %></td></tr>
            <tr><th>Local</th><td><%= eq.getLocal() %></td></tr>
            <tr><th>Última</th><td><%= eq.getUltima() %></td></tr>
            <tr><th>Frequência</th><td><%= eq.getFreq() %></td></tr>
            <tr><th>Status</th><td><%= eq.getStatus() %></td></tr>
        </tbody>
    </table>
<%
    } else if (request.getParameter("codigo") != null) {
%>
    <p>Nenhum equipamento encontrado com esse código.</p>
<%
    }
%>
        </div>
    </div>

    <a href="cadastrar.jsp">
        <button class="btn-primary">Novo Equipamento</button>
    </a>
</div>

</body>
</html>