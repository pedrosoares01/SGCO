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
    <jsp:include page="../sidebar.jsp" />
    <main class="content">
        <div class="container">
            <jsp:include page="orcamento.jsp" />
        </div>
    </main>
</body>
</html>