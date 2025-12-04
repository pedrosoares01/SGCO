<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="sgco.sgco.domain.FornecedorMaterial" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fornecedores de Materiais - SGCO</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/sidebar.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/fornecedores_materiais.css">
</head>
<body>

<aside class="sidebar">
    <a href="index.jsp"><h2>SGCO</h2></a>
    <ul>
        <li><a href="gestao_pacientes.jsp">Gestão de Pacientes</a></li>
        <li><a href="gestao_usuarios.jsp">Gestão de Usuários</a></li>
        <li><a href="gestao_procedimentos.jsp">Gestão de Procedimentos</a></li>
        <li><a href="estoque.jsp">Controle de Estoque</a></li>
        <li><a href="fornecedores_materiais.jsp" class="active">Fornecedores de Materiais</a></li>
        <li><a href="fornecedores_servicos.jsp">Fornecedores de Serviços</a></li>
        <li><a href="receita.jsp">Gestão da Receita</a></li>
        <li><a href="pacientes_agendados.jsp">Pacientes Agendados</a></li>
        <li><a href="login.jsp" class="logout">Sair</a></li>
    </ul>
</aside>

<main class="content">
    <h1>Gestão de Fornecedores de Materiais</h1>


    <%
        String mensagem = (String) request.getAttribute("mensagem");
        String tipoMensagem = (String) request.getAttribute("tipoMensagem");
        String searchFornecedor = (String) request.getParameter("searchFornecedor");
        if (searchFornecedor == null) searchFornecedor = "";

        java.util.List fornecedores = null;
        if (request.getAttribute("fornecedores") instanceof java.util.List) {
            fornecedores = (java.util.List) request.getAttribute("fornecedores");
        }
    %>

    <% if (mensagem != null && !mensagem.isEmpty()) { %>
    <div class="alert alert-<%= tipoMensagem != null ? tipoMensagem : "info" %>"><%= mensagem %></div>
    <% } %>

    <div class="container">

        <section class="card">
            <h2>Cadastro de Fornecedor</h2>
            <form id="fornecedorForm" action="${pageContext.request.contextPath}/FornecedorMaterialController" method="post">
                <input type="hidden" name="action" value="cadastrar">

                <div class="form-group">
                    <label for="nomeFornecedor">Nome do Fornecedor</label>
                    <input type="text" id="nomeFornecedor" name="nomeFornecedor"
                           placeholder="Nome do fornecedor" required>
                </div>

                <div class="form-group">
                    <label for="contatoFornecedor">Contato</label>
                    <input type="text" id="contatoFornecedor" name="contatoFornecedor"
                           placeholder="Telefone ou celular">
                </div>

                <div class="form-group">
                    <label for="emailFornecedor">Email</label>
                    <input type="email" id="emailFornecedor" name="emailFornecedor"
                           placeholder="email@exemplo.com">
                </div>

                <div class="buttons">
                    <button type="submit" class="btn-primary">Cadastrar</button>
                    <button type="button" class="btn-secondary" onclick="limparFormulario()">Limpar</button>
                </div>
            </form>
        </section>


        <section class="card">
            <h2> Pesquisar Fornecedor</h2>
            <form id="searchForm" action="${pageContext.request.contextPath}/FornecedorMaterialController" method="get">
                <input type="hidden" name="action" value="pesquisar">

                <div class="form-group">
                    <label for="searchFornecedor">Nome do Fornecedor</label>
                    <input type="text" id="searchFornecedor" name="searchFornecedor"
                           placeholder="Digite o nome para pesquisar..."
                           value="<%= searchFornecedor %>">
                </div>

                <button type="submit" class="btn-search">Buscar</button>
            </form>

            <div class="search-results">
                <%
                    if (fornecedores != null && !fornecedores.isEmpty()) {
                %>
                <table class="results-table">
                    <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Contato</th>
                        <th>Email</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<FornecedorMaterial> fornecedor = (List<FornecedorMaterial>) request.getAttribute("fornecedores");
                        for (FornecedorMaterial f : fornecedor) {
                            String nome = f.getNome();
                            String contato = f.getContato();
                            String email = f.getEmail();
                            if (contato == null) contato = "";
                            if (email == null) email = "";
                    %>

                    <tr>
                        <td><%= nome %></td>
                        <td><%= contato %></td>
                        <td><%= email %></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/FornecedorMaterialController" method="post" style="display: inline;">
                                <input type="hidden" name="action" value="excluir">
                                <input type="hidden" name="nome" value="<%= nome %>">
                                <button type="submit" class="btn-delete"
                                        onclick="return confirm('Tem certeza que deseja excluir <%= nome %>?')">
                                    Excluir
                                </button>
                            </form>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    </tbody>
                </table>

            </div>
        </section>
    </div>
</main>

<script>
    function limparFormulario() {
        document.getElementById('fornecedorForm').reset();
    }

    document.addEventListener('DOMContentLoaded', function() {
        const searchInput = document.getElementById('searchFornecedor');
        if (searchInput) {
            searchInput.focus();
        }
    });

    setTimeout(function() {
        const alerts = document.querySelectorAll('.alert');
        alerts.forEach(alert => {
            alert.style.display = 'none';
        });
    }, 5000);
</script>
</body>
</html>