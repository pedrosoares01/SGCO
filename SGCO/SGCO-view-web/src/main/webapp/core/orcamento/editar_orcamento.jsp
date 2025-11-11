<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="sgco.model.domain.Orcamento" %>

<%
    Orcamento o = (Orcamento) request.getAttribute("orcamento");
    if (o == null) {
%>
    <p>Orçamento não encontrado.</p>
<%
        return;
    }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Editar Orçamento</title>
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
        <h2>Editar Orçamento</h2>

        <form action="${pageContext.request.contextPath}/OrcamentoController" method="post">
		    <input type="hidden" name="acao" value="atualizar">
		    <input type="hidden" name="id" value="${orcamento.id}">
		    
		    <label>Descrição:</label>
		    <input type="text" name="descricao" value="${orcamento.descricao}" required>
		
		    <label>Valor:</label>
		    <input type="text" name="valor" value="${orcamento.valor}" required>
		
		    <label>ID Profissional:</label>
		    <input type="number" name="id_profissional" value="${orcamento.idProfissional}" required>
		
		    <label>ID Paciente:</label>
		    <input type="number" name="id_paciente" value="${orcamento.idPaciente}" required>
		
		    <button type="submit" class="btn-primary">Salvar Alterações</button>
		</form>
    </div>

    <script>
        function confirmarExclusao(id) {
            if (confirm("Tem certeza que deseja excluir este orçamento?")) {
                window.location.href = "${pageContext.request.contextPath}/OrcamentoController?acao=excluir&id=" + id;
            }
        }
    </script>

</body>
</html>
