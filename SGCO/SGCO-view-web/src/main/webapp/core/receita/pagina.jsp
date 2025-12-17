<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Gestão da Receita</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/receita.css">
</head>
<body>

    <jsp:include page="../sidebar.jsp" />

    <main class="content">
        <h1>Gestão da Receita</h1>

        <div class="container">
            <jsp:include page="receita.jsp" />
        </div>
    </main>

</body>
</html>
