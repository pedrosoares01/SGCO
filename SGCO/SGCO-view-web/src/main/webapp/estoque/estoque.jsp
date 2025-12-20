<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="sgco.sgco.domain.Material" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Controle de Estoque - SGCO</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estoque.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
</head>
<body>
<aside class="sidebar">
    <a href="${pageContext.request.contextPath}/indexgerente.jsp"><h2>SGCO</h2></a>
    <ul>
        <li><a href="${pageContext.request.contextPath}/gestao_pacientes.jsp">Gestão de Pacientes</a></li>
        <li><a href="${pageContext.request.contextPath}/gestao_usuarios.jsp">Gestão de Usuários</a></li>
        <li><a href="${pageContext.request.contextPath}/gestao_procedimentos.jsp">Gestão de Procedimentos</a></li>
        <li><a href="${pageContext.request.contextPath}/MaterialController">Controle de Estoque</a></li>
        <li><a href="${pageContext.request.contextPath}/fornecedores_materiais.jsp">Fornecedores de Materiais</a></li>
        <li><a href="${pageContext.request.contextPath}/fornecedores_servicos.jsp">Fornecedores de Serviços</a></li>
        <li><a href="${pageContext.request.contextPath}/receita.jsp">Gestão da Receita</a></li>
        <li><a href="${pageContext.request.contextPath}/pacientes_agendados.jsp">Pacientes Agendados</a></li>
        <li><a href="${pageContext.request.contextPath}/relatorios.jsp">Relatórios Gerenciais</a></li>
        <li><a href="${pageContext.request.contextPath}/login.jsp" class="logout">Sair</a></li>
    </ul>
</aside>
<main class="content">
    <h1>Controle de Estoque</h1>
    <div class="container">
        <section class="card">
            <h2>Cadastro de Material</h2>
            <form id="materialForm" method="POST" action="${pageContext.request.contextPath}/MaterialController">
                <input type="hidden" name="action" id="action">
                <label>Nome do Material:</label>
                <input type="text" name="nome" placeholder="Nome do material" required>
                <label>Quantidade:</label>
                <input type="number" name="quantidade" placeholder="Quantidade em estoque" required min="0">

                <div class="buttons">
                    <button type="submit" class="btn-primary" onclick="document.getElementById('action').value='cadastrar'">Cadastrar</button>
                </div>
            </form>
        </section>

        <section class="card">
            <h2>Pesquisar Material</h2>
            <form method="POST" action="${pageContext.request.contextPath}/MaterialController">
                <input type="hidden" name="action" id="actionPesquisar">
                <label>Nome do Material:</label>
                <input type="text" name="nome" placeholder="Digite para pesquisar..." required>
                <div class="buttons">
                    <button type="submit" class="btn-primary" onclick="document.getElementById('actionPesquisar').value='pesquisar'">Buscar</button>
                </div>
            </form>
            <%
                try{
                    List<Material> materiais = (List<Material>) request.getAttribute("materiais");
                    String nomeMaterial = (String) request.getAttribute("nomeMaterial");
                    if (materiais != null && !materiais.isEmpty()) {
            %>
            <div class="search-results">
                <h3>Resultados da pesquisa por "<%= nomeMaterial != null ? nomeMaterial : "" %>":</h3>
                <table>
                    <tr>
                        <th>Nome</th>
                        <th>Quantidade</th>
                        <th>Ações</th>
                    </tr>
                    <%
                        if (materiais.isEmpty()) {
                    %>
                    <tr><td colspan="3">Nenhum material encontrado.</td></tr>
                    <%
                    } else {
                        for (Material m : materiais) {
                    %>
                    <tr>
                        <td><%= m.getNome() %></td>
                        <td><%= m.getQuantidade() %></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/MaterialController" method="post" style="display: inline;">
                                <input type="hidden" name="action" value="excluir">
                                <input type="hidden" name="nome" value="<%= m.getNome() %>">
                                <button type="submit" class="btn-delete" onclick="return confirm('Tem certeza que deseja excluir <%= m.getNome() %>?')">Excluir</button>
                            </form>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </div>
            <%
                    }
                } catch (Exception e){}
            %>
        </section>
    </div>

    <%
        String msg = (String) request.getAttribute("msg");
        String tipoMsg = (String) request.getAttribute("tipoMensagem");
    %>

    <% if (msg != null) { %>
    <div id="popup" class="<%= tipoMsg %>">
        <p><%= msg %></p>
        <button onclick="fecharPopup()">OK</button>
    </div>

    <script>
        function fecharPopup() {
            document.getElementById("popup").style.display = "none";
        }
    </script>
    <% } %>
</main>
</body>
</html>