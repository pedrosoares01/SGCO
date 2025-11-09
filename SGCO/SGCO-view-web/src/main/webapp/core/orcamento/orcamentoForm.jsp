<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="sgco.model.domain.Procedimento" %>
<%@ page import="sgco.model.domain.Paciente" %>

<%
    Paciente pacienteSelecionado = (Paciente) session.getAttribute("pacienteSelecionado");
    List<Procedimento> procedimentosSelecionados =
        (List<Procedimento>) session.getAttribute("procedimentosSelecionados");

    if (procedimentosSelecionados == null) {
        procedimentosSelecionados = new java.util.ArrayList<>();
    }

    String mensagem = (String) request.getAttribute("mensagem");
    String mensagemErro = (String) request.getAttribute("mensagemErro");
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Orçamento</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="content">
    <h1>Orçamento</h1>

    <% if (mensagem != null) { %>
        <div class="card" style="border-left: 4px solid #00b894; background: #ecfdf5;">
            <p><strong>✔️ <%= mensagem %></strong></p>
        </div>
    <% } %>

    <% if (mensagemErro != null) { %>
        <div class="card" style="border-left: 4px solid #e63946; background: #fdecea;">
            <p><strong>⚠️ <%= mensagemErro %></strong></p>
        </div>
    <% } %>

    <div class="container">

        <!-- Paciente -->
        <div class="card">
            <h2>Paciente</h2>

            <% if (pacienteSelecionado != null) { %>
                <p><strong>Nome:</strong> <%= pacienteSelecionado.getNome() %></p>
                <p><strong>ID:</strong> <%= pacienteSelecionado.getId() %></p>
            <% } else { %>
                <form action="<%= request.getContextPath() %>/OrcamentoController" method="get">
                    <input type="hidden" name="acao" value="selecionarPaciente">
                    <div class="form-group">
                        <label for="idPaciente">ID do Paciente</label>
                        <input type="number" id="idPaciente" name="id" placeholder="Digite o ID do paciente" required>
                    </div>
                    <div class="buttons">
                        <button type="submit" class="btn-search">Selecionar</button>
                    </div>
                </form>
            <% } %>
        </div>

        <!-- Procedimentos -->
        <div class="card">
            <h2>Procedimentos</h2>

            <form action="<%= request.getContextPath() %>/OrcamentoController" method="get">
                <input type="hidden" name="acao" value="selecionarProcedimento">
                <div class="form-group">
                    <label for="idProcedimento">ID do Procedimento</label>
                    <input type="number" id="idProcedimento" name="id" placeholder="Digite o ID do procedimento" required>
                </div>
                <div class="buttons">
                    <button type="submit" class="btn-primary">Adicionar</button>
                </div>
            </form>

            <div class="search-results">
                <span class="result-title">Procedimentos Selecionados</span>

                <% if (!procedimentosSelecionados.isEmpty()) { %>
                    <div class="result-list">
                        <% for (Procedimento p : procedimentosSelecionados) { %>
                            <div class="result-item">
                                <div>
                                    <strong><%= p.getNome() %></strong> — R$ <%= String.format("%.2f", p.getPreco()) %>
                                </div>
                                <span class="id">#<%= p.getId() %></span>
                            </div>
                        <% } %>
                    </div>
                <% } else { %>
                    <p class="info-text">Nenhum procedimento selecionado ainda.</p>
                <% } %>
            </div>
        </div>

    </div>
</div>

</body>
</html>
