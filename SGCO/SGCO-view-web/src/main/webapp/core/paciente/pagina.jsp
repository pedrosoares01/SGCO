<%@ page import="sgco.sgco.domain.Usuario" %>
<%@ page import="sgco.controller.LoginController" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% LoginController.validarSessao(request,response);%>
<%
    Usuario u;
    u = (Usuario) session.getAttribute("usuarioLogado");
    String cargo = u.getCargo();
%>
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
<aside class="sidebar">
    <%if (cargo.equals("Gerente")){%>
    <a href="${pageContext.request.contextPath}/indexgerente.jsp"><h2>SGCO</h2></a>
    <%} else if (cargo.equals("Recepcionista")){%>
    <a href="${pageContext.request.contextPath}/indexrecepcionista.jsp"><h2>SGCO</h2></a>
    <%} else {%>
    <a href="${pageContext.request.contextPath}/index.jsp"></a>
    <%}%>
    <ul>
        <%if (cargo.equals("Gerente")){%>
        <li><a href="${pageContext.request.contextPath}/core/paciente/pagina.jsp">Gestão de Pacientes</a></li>
        <li><a href="${pageContext.request.contextPath}/gestaoUsuarios/gestao_usuarios.jsp">Gestão de Usuários</a></li>
        <li><a href="${pageContext.request.contextPath}/core/procedimentos/pagina.jsp">Gestão de Procedimentos</a></li>
        <li><a href="${pageContext.request.contextPath}/estoque/estoque.jsp">Controle de Estoque</a></li>
        <li><a href="${pageContext.request.contextPath}/fornecedores_materiais/fornecedores_materiais.jsp">Fornecedores de Materiais</a></li>
        <li><a href="${pageContext.request.contextPath}/fornecedores_servicos/fornecedores_servicos.jsp">Fornecedores de Serviços</a></li>
        <li><a href="receita.jsp">Gestão da Receita</a></li>
        <li><a href="${pageContext.request.contextPath}/PacientesAgendadosController">Pacientes Agendados</a></li>
        <li><a href="${pageContext.request.contextPath}/LogoutController" class="logout">Sair</a></li>
        <%} else if (cargo.equals("Recepcionista")){%>
        <li><a href="${pageContext.request.contextPath}/core/paciente/pagina.jsp">Gestão de Pacientes</a></li>
        <li><a href="${pageContext.request.contextPath}/AgendaController">Agenda</a></li>
        <li><a href="${pageContext.request.contextPath}/core/pagamento/pagina.jsp">Pagamentos</a></li>
        <li><a href="${pageContext.request.contextPath}/pacientes-agendados/pacientes_agendados.jsp">Pacientes Agendados</a></li>
        <li><a href="${pageContext.request.contextPath}/AvaliacaoController">Avaliação de Profissionais</a></li>
        <li><a href="${pageContext.request.contextPath}/LogoutController" class="logout">Sair</a></li>
        <%} else {%>
        <li><a href="${pageContext.request.contextPath}/LogoutController" class="logout">Sair</a></li>
        <%}%>
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