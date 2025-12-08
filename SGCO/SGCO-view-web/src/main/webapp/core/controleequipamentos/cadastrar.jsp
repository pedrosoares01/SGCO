<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <title>Cadastrar Equipamento</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/controleequip.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
</head>

<body>

<div class="content">

    <h1>Cadastrar Equipamento</h1>

    <div class="container">

        <div class="card">
           <form action="${pageContext.request.contextPath}/CadastrarEquipamento" method="post">
    <div>
        <label for="nome">Nome do Equipamento</label>
        <input type="text" id="nome" name="nome" placeholder="Ex: Autoclave">
    </div>

    <div>
        <label for="codigo">Código / Identificação</label>
        <input type="text" id="codigo" name="codigo" placeholder="Ex: EQP-0021">
    </div>

    <div>
        <label for="local">Local / Sala</label>
        <input type="text" id="local" name="local" placeholder="Ex: Sala 01">
    </div>

    <div>
        <label for="ultima">Última Manutenção</label>
        <input type="date" id="ultima" name="ultima">
    </div>

    <div>
        <label for="freq">Frequência da Manutenção (meses)</label>
        <input type="number" id="freq" name="freq" placeholder="Ex: 6">
    </div>

    <div>
        <label for="status">Status</label>
        <select id="status" name="status">
            <option value="Funcionando">Funcionando</option>
            <option value="Em manutenção">Em manutenção</option>
            <option value="Aguardando manutenção">Aguardando manutenção</option>
        </select>
    </div>

    <!-- BOTÃO SALVAR (não pode ficar dentro de <a> !!!) -->
    <button type="submit" class="btn-primary">Salvar Equipamento</button>

    <!-- BOTÃO VOLTAR -->
    <a href="controleequip.jsp" class="btn-secondary">Voltar</a>
</form>

        </div>

    </div>

</div>

</body>
</html>
