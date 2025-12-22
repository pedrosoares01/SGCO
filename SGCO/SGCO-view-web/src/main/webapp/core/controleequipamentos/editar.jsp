<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="sgco.model.domain.Equipamentos" %>

<%
    Equipamentos p = (Equipamentos) request.getAttribute("equipamento");
    if (p == null) {
%>
    <p>Equipamento não encontrado.</p>
<%
        return;
    }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Editar Equipamento</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, sans-serif;
            background-color: #f4f8fb;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .card {
            background: white;
            padding: 2rem 3rem;
            border-radius: 1rem;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            width: 420px;
        }

        h2 {
            color: #0077b6;
            text-align: center;
            margin-bottom: 1.5rem;
        }

        label {
            display: block;
            font-weight: 600;
            margin-top: 1rem;
            color: #333;
        }

        input {
            width: 100%;
            padding: 0.7rem;
            border-radius: 0.5rem;
            border: 1px solid #ccc;
            margin-top: 0.4rem;
        }

        .btn-group {
            display: flex;
            justify-content: space-between;
            margin-top: 1.5rem;
        }

        button {
            border: none;
            padding: 0.8rem 1.5rem;
            border-radius: 0.5rem;
            cursor: pointer;
            font-weight: bold;
            transition: 0.3s;
        }

        .btn-save {
            background-color: #0077b6;
            color: white;
        }

        .btn-save:hover {
            background-color: #005f8d;
        }

        .btn-delete {
            background-color: #dc3545;
            color: white;
        }

        .btn-delete:hover {
            background-color: #a71d2a;
        }
    </style>
</head>
<body>

    <div class="card">
        <h2>Editar Equipamento</h2>

        <form action="${pageContext.request.contextPath}/EquipamentosController" method="post">
            <input type="hidden" name="acao" value="atualizar">
            <input type="hidden" name="id" value="<%= p.getId() %>">

            <label for="nome">Nome</label>
            <input type="text" id="nome" name="nome" value="<%= p.getNome() %>" required>

            <label for="codigo">Código</label>
            <input type="text" id="codigo" name="codigo" value="<%= p.getCodigo() %>" required>

            <label for="local">Local</label>
            <input type="text" id="local" name="local" value="<%= p.getLocal() %>" required>

            <label for="ultima">Última Manutenção</label>
            <input type="text" id="ultima" name="ultima" value="<%= p.getUltima() %>">

            <label for="freq">Frequência (dias)</label>
            <input type="number" id="freq" name="freq" value="<%= p.getFreq() %>" required>

            <label for="status">Status</label>
            <input type="text" id="status" name="status" value="<%= p.getStatus() %>" required>

            <div class="btn-group">
                <button type="submit" class="btn-save">Salvar</button>
                <button type="button" class="btn-delete" onclick="confirmarExclusao(<%= p.getId() %>)">Excluir</button>
            </div>
        </form>
    </div>

    <script>
        function confirmarExclusao(id) {
            if (confirm("Tem certeza que deseja excluir este equipamento?")) {
                window.location.href = "${pageContext.request.contextPath}/EquipamentosController?acao=excluir&id=" + id;
            }
        }
    </script>

</body>
</html>
