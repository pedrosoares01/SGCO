<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Gestão de Pacientes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
</head>
<body>
    <jsp:include page="../sidebar.jsp" />

    <main class="content">
        <div class="container">
            <jsp:include page="cadastrar.jsp" />
            <jsp:include page="pesquisar.jsp" />
        </div>
    </main>
</body>
</html>