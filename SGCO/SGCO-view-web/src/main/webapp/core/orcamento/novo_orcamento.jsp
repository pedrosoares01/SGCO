<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="sgco.model.domain.Paciente" %>
<%@ page import="sgco.model.domain.Procedimento" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Novo Orçamento</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
</head>
<body>
    <jsp:include page="../sidebar.jsp" />
    <main class="content">
        <div class="container">

            <h1>Novo Orçamento</h1>

            <%
                Paciente pacienteSel = (Paciente) request.getAttribute("pacienteSelecionado");
                List<Procedimento> procedimentosSel = (List<Procedimento>) request.getAttribute("procedimentosSelecionados");
                double valorTotal = 0;
                if (procedimentosSel != null) {
                    for (Procedimento p : procedimentosSel) {
                        valorTotal += p.getPreco();
                    }
                }
            %>

            <section class="card">
                <h3>Seleções Atuais</h3>
                <p>
                    <strong>Paciente:</strong>
                    <%= (pacienteSel != null ? pacienteSel.getNome() : "Nenhum selecionado") %><br>
                    <strong>Procedimentos:</strong><br>
                    <%
                        if (procedimentosSel != null && !procedimentosSel.isEmpty()) {
                            for (Procedimento p : procedimentosSel) { %>
                                <%= p.getNome() %> (R$ <%= String.format("%.2f", p.getPreco()) %>)<br>
                    <%      }
                        } else { %>
                            Nenhum selecionado
                    <% } %>
                    <br><strong>Valor Total:</strong> R$ <%= String.format("%.2f", valorTotal) %>
                </p>
            </section>

            <jsp:include page="pesquisarPaciente.jsp" />
            <jsp:include page="pesquisarProcedimento.jsp" />

            <%
                if (pacienteSel != null && procedimentosSel != null && !procedimentosSel.isEmpty()) {
            %>
                <section class="card">
                    <h2>Finalizar Orçamento</h2>
                    <form action="${pageContext.request.contextPath}/OrcamentoController" method="post">
                        <input type="hidden" name="acao" value="inserir">
                        <input type="hidden" name="id_paciente" value="<%= pacienteSel.getId() %>">

                        <div class="form-group">
                            <label for="descricao">Descrição</label>
                            <input type="text" id="descricao" name="descricao"
                                   value="<%
                                       StringBuilder sb = new StringBuilder();
                                       for (Procedimento p : procedimentosSel) {
                                           sb.append(p.getNome()).append(", ");
                                       }
                                       if (sb.length() > 2) sb.setLength(sb.length() - 2);
                                       out.print(sb.toString());
                                   %>"
                                   readonly>
                        </div>

                        <div class="form-group">
                            <label for="valor">Valor Total</label>
                            <input type="text" id="valor" name="valor"
                                   value="<%= String.format("%.2f", valorTotal) %>"
                                   readonly>
                        </div>

                        <button type="submit" class="btn-primary">Salvar Orçamento</button>
                    </form>
                </section>
            <%
                }
            %>

        </div>
    </main>
</body>
</html>
