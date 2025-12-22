<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="sgco.sgco.domain.FornecedorMaterial" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fornecedores de Materiais - SGCO</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fornecedores_materiais.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
</head>
<body>
<aside class="sidebar">
    <a href="${pageContext.request.contextPath}/indexgerente.jsp"><h2>SGCO</h2></a>
    <ul>
        <li><a href="${pageContext.request.contextPath}/core/paciente/pagina.jsp">Gestão de Pacientes</a></li>
        <li><a href="${pageContext.request.contextPath}/gestaoUsuarios/gestao_usuarios.jsp">Gestão de Usuários</a></li>
        <li><a href="${pageContext.request.contextPath}/core/procedimentos/pagina.jsp">Gestão de Procedimentos</a></li>
        <li><a href="${pageContext.request.contextPath}/estoque/estoque.jsp">Controle de Estoque</a></li>
        <li><a href="${pageContext.request.contextPath}/fornecedores_materiais/fornecedores_materiais.jsp">Fornecedores de Materiais</a></li>
        <li><a href="${pageContext.request.contextPath}/fornecedores_servicos/fornecedores_servicos.jsp">Fornecedores de Serviços</a></li>
        <li><a href="${pageContext.request.contextPath}/FinanceiroController">Gestão da Receita</a></li>
        <li><a href="${pageContext.request.contextPath}/saldo/saldo.jsp">Saldo</a></li>
        <li><a href="${pageContext.request.contextPath}/PacientesAgendadosController">Pacientes Agendados</a></li>
        <li><a href="${pageContext.request.contextPath}/LogoutController" class="logout">Sair</a></li>
    </ul>
</aside>
<main class="content">
    <h1>Gestão de Fornecedores de Materiais</h1>
    <div class="container">
        <section class="card">
            <h2>Cadastro de Fornecedor</h2>
            <form id="fornecedorForm" method="POST" action="${pageContext.request.contextPath}/FornecedorMaterialController">
                <input type="hidden" name="action" id="action">
                <label>Nome do Fornecedor:</label>
                <input type="text" name="nomeFornecedor" placeholder="Nome do fornecedor" required>
                <label>Contato:</label>
                <input type="text" name="contatoFornecedor" placeholder="Telefone ou celular">
                <label>Email:</label>
                <input type="email" name="emailFornecedor" placeholder="email@exemplo.com">

                <div class="buttons">
                    <button type="submit" class="btn-primary" onclick="document.getElementById('action').value='cadastrar'">Cadastrar</button>
                </div>
            </form>
        </section>

        <section class="card">
            <h2>Pesquisar Fornecedor</h2>
            <form method="POST" action="${pageContext.request.contextPath}/FornecedorMaterialController">
                <input type="hidden" name="action" id="actionPesquisar">
                <label>Nome do Fornecedor:</label>
                <input type="text" name="nome" placeholder="Digite para pesquisar..." required>
                <div class="buttons">
                    <button type="submit" class="btn-primary" onclick="document.getElementById('actionPesquisar').value='pesquisar'">Buscar</button>
                </div>
            </form>
            <%
                try{
                    List<FornecedorMaterial> fornecedores = (List<FornecedorMaterial>) request.getAttribute("fornecedores");
                    String nomeFornecedor = (String) request.getAttribute("nomeFornecedor");
                    if (fornecedores != null && !fornecedores.isEmpty()) {
            %>
            <div class="search-results">
                <h3>Resultados da pesquisa por "<%= nomeFornecedor != null ? nomeFornecedor : "" %>":</h3>
                <table>
                    <tr>
                        <th>Nome</th>
                        <th>Contato</th>
                        <th>Email</th>
                        <th>Ações</th>
                    </tr>
                    <%
                        if (fornecedores.isEmpty()) {
                    %>
                    <tr><td colspan="4">Nenhum fornecedor encontrado.</td></tr>
                    <%
                    } else {
                        for (FornecedorMaterial f : fornecedores) {
                    %>
                    <tr>
                        <td><%= f.getNome() %></td>
                        <td><%= f.getContato() != null ? f.getContato() : "" %></td>
                        <td><%= f.getEmail() != null ? f.getEmail() : "" %></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/FornecedorMaterialController" method="post" style="display: inline;">
                                <input type="hidden" name="action" value="excluir">
                                <input type="hidden" name="nome" value="<%= f.getNome() %>">
                                <button type="submit" class="btn-delete" onclick="return confirm('Tem certeza que deseja excluir <%= f.getNome() %>?')">Excluir</button>
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