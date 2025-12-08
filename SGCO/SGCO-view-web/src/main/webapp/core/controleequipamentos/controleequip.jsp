<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <title>Controle de Equipamentos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/controleequip.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
</head>

<body>
    <jsp:include page="../sidebar.jsp" />

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

        <!-- Card – Listagem -->
        <div class="card big-card">
            <h2>Equipamentos Cadastrados</h2>

            <!-- Barra de Pesquisa -->
            <input type="text" id="buscar" placeholder="Pesquisar equipamento..." 
                   onkeyup="filtrar()">

        </div>

    </div>
			<a href="cadastrar.jsp">
                <button class="btn-primary">Novo Equipamento</button>
            </a>
</div>

<script>
function filtrar(){
    let termo = document.getElementById("buscar").value.toLowerCase();
    let linhas = document.querySelectorAll("#tabela-equip tbody tr");

    linhas.forEach(linha =>{
        let texto = linha.textContent.toLowerCase();
        linha.style.display = texto.includes(termo) ? "table-row" : "none";
    });
}
</script>

</body>
</html>
