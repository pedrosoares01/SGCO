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
	<aside class="sidebar">
		<a href="${pageContext.request.contextPath}/indexgerente.jsp"><h2>SGCO</h2></a>
		<ul>
			<li><a href="${pageContext.request.contextPath}/core/paciente/pagina.jsp">Gestão de Pacientes</a></li>
			<li><a href="${pageContext.request.contextPath}/gestaoUsuarios/gestao_usuarios.jsp">Gestão de Usuários</a></li>
			<li><a href="${pageContext.request.contextPath}/core/procedimentos/pagina.jsp">Gestão de Procedimentos</a></li>
			<li><a href="${pageContext.request.contextPath}/estoque/estoque.jsp">Controle de Estoque</a></li>
			<li><a href="${pageContext.request.contextPath}/fornecedores_materiais/fornecedores_materiais.jsp">Fornecedores de Materiais</a></li>
			<li><a href="${pageContext.request.contextPath}/fornecedores_servicos/fornecedores_servicos.jsp">Fornecedores de Serviços</a></li>
			<li><a href="${pageContext.request.contextPath}/FinanceiroController">Gestão da Receita</a></li>
            <li><a href="${pageContext.request.contextPath}/saldo/saldo.jsp">Saldo</a></li>
			<li><a href="${pageContext.request.contextPath}/PacientesAgendadosController">Pacientes Agendados</a></li>
			<li><a href="${pageContext.request.contextPath}/LogoutController" class="logout">Sair</a></li>
		</ul>
	</aside>
		<main class="content">
			<div class="container">
				<jsp:include page="cadastrar.jsp" />
				<jsp:include page="pesquisar.jsp" />
			</div>
		</main>
	</body>
</html>