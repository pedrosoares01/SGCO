<%@ page import="sgco.sgco.domain.SaldoPaciente" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>


<%
    SaldoPaciente saldo = (SaldoPaciente) request.getAttribute("saldoPaciente");
    if (saldo == null) {
%>
    <p>Procedimento não encontrado.</p>
<%
        return;
    }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Editar Procedimento</title>
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
        <h2>Editar Informações </h2>

        <form action="${pageContext.request.contextPath}/SaldoController" method="post">
            <input type="hidden" name="acao" value="atualizar">

            <input type="hidden" name="pacienteId" value="<%= saldo.getPacienteId() %>">
            <input type="hidden" name="orcamentoId" value="<%= saldo.getOrcamentoId() %>">
            <input type="hidden" name="nomeDevedor" value="<%= saldo.getNomeDevedor() %>">

            <label>orcamento</label>
            <input type="text" id="nome" name="totalPagar" value="<%= saldo.getTotalPagar() %>" required>

            <label>Valor Pago</label>
            <input type="number" id="preco" name="valorPago" step="0.01" value="<%= saldo.getPago() %>" required>

            <div class="btn-group">
                <button type="submit" class="btn-save">Salvar</button>
            </div>
        </form>
    </div>


</body>
</html>
