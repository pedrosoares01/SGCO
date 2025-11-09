<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Gestão de Procedimentos</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
	</head>
	<body>
		<jsp:include page="../sidebar.jsp" />
		<main class="content">
			<div class="container">
				<jsp:include page="/core/orcamento/pesquisarPaciente.jsp" />
				<jsp:include page="/core/orcamento/pesquisarProcedimento.jsp" />
			</div>
		</main>
	</body>
</html>