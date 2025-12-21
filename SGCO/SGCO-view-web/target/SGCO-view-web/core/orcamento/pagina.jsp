<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Orçamento</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
</head>
<body>
<aside class="sidebar">
    <a href="${pageContext.request.contextPath}/indexprofissional.jsp"><h2>SGCO</h2></a>
    <ul>
        <li><a href="${pageContext.request.contextPath}/core/orcamento/pagina.jsp">Orçamento</a></li>
        <li><a href="estoque.jsp">Controle de Estoque</a></li>
        <li><a href="prontuario.jsp">Prontuário</a></li>
        <li><a href="${pageContext.request.contextPath}/pacientes-agendados/pacientes_agendados.jsp">Pacientes Agendados</a></li>
        <li><a href="${pageContext.request.contextPath}/LogoutController" class="logout">Sair</a></li>
    </ul>
</aside>
    <main class="content">
        <div class="container">
            <jsp:include page="orcamento.jsp" />
        </div>
    </main>
</body>
</html>