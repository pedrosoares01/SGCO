<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Controle de Saldo - SGCO</title>
    <link rel="stylesheet" href="css/saldo.css">
    <link rel="stylesheet" href="css/sidebar.css">
</head>
<body>
<aside class="sidebar">
    <a href="index.jsp"><h2>SGCO</h2></a>
    <ul>
        <li><a href="gestao_pacientes.jsp">Gestão de Pacientes</a></li>
        <li><a href="gestao_usuarios.jsp">Gestão de Usuários</a></li>
        <li><a href="gestao_procedimentos.jsp">Gestão de Procedimentos</a></li>
        <li><a href="estoque.jsp">Controle de Estoque</a></li>
        <li><a href="fornecedores_materiais.jsp">Fornecedores de Materiais</a></li>
        <li><a href="fornecedores_servicos.jsp">Fornecedores de Serviços</a></li>
        <li><a href="receita.jsp">Gestão da Receita</a></li>
        <li><a href="pacientes_agendados.jsp">Pacientes Agendados</a></li>
        <li><a href="login.jsp" class="logout">Sair</a></li>
    </ul>
</aside>

<main class="content">
    <h1>Controle de Saldo</h1>
    <div class="panel">
        <form  method="post" action="${pageContext.request.contextPath}/SaldoController">
            <label for="usuario">Relatório de Devedores</label>
            <input type="hidden"  name="action" id="action">
            <button type="submit" onclick="document.getElementById('action').value='Devedores'">Gerar</button>
        </form>
    </div>


    <div class="panel">
        <form  method="post" action="${pageContext.request.contextPath}/SaldoController">
            <label for="usuario">Relatório de Devedores Atrasados</label>
            <input type="hidden"  name="action" id="action">
            <button type="submit" onclick="document.getElementById('action').value='DevedoresAtrasados'">Gerar</button>
        </form>
    </div>
</main>
</body>
</html>