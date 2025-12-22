<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Gestão de Equipamentos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/controleequip.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
    
    
    
</head>
<body>
    <jsp:include page="../sidebar.jsp" />

    <main class="content">
        <div class="container">
            <jsp:include page="controleequip.jsp" />
            
            
        </div>
    </main>
</body>
</html>