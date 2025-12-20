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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
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

        boolean modoEdicao = request.getAttribute("modoEdicao") != null && (Boolean) request.getAttribute("modoEdicao");
        FornecedorMaterial fornecedorEdicao = (FornecedorMaterial) request.getAttribute("fornecedorEdicao");
        String nomeOriginal = (String) request.getAttribute("nomeOriginal");

        List<FornecedorMaterial> fornecedores = (List<FornecedorMaterial>) request.getAttribute("fornecedores");
    %>

    <% if (mensagem != null && !mensagem.isEmpty()) { %>
    <div class="alert alert-<%= tipoMensagem != null ? tipoMensagem : "info" %>">
        <i class="fas <%=
            "sucesso".equals(tipoMensagem) ? "fa-check-circle" :
            "erro".equals(tipoMensagem) ? "fa-exclamation-circle" :
            "alerta".equals(tipoMensagem) ? "fa-exclamation-triangle" :
            "fa-info-circle"
        %>"></i>
        <%= mensagem %>
    </div>
    <% } %>

    <div class="container">

        <section class="card">
            <h2><%= modoEdicao ? "Editar Fornecedor" : "Cadastro de Fornecedor" %></h2>
            <form id="fornecedorForm" action="${pageContext.request.contextPath}/FornecedorMaterialController" method="post">
                <input type="hidden" name="action" value="<%= modoEdicao ? "editar" : "cadastrar" %>">
                <% if (modoEdicao) { %>
                <input type="hidden" name="nomeOriginal" value="<%= nomeOriginal %>">
                <% } %>

                <div class="form-group">
                    <label for="nomeFornecedor">Nome do Fornecedor *</label>
                    <input type="text" id="nomeFornecedor" name="nomeFornecedor"
                           placeholder="Nome do fornecedor" required
                           value="<%= modoEdicao && fornecedorEdicao != null ? fornecedorEdicao.getNome() : "" %>">
                </div>

                <div class="form-group">
                    <label for="contatoFornecedor">Contato</label>
                    <input type="text" id="contatoFornecedor" name="contatoFornecedor"
                           placeholder="Telefone ou celular"
                           value="<%= modoEdicao && fornecedorEdicao != null && fornecedorEdicao.getContato() != null ? fornecedorEdicao.getContato() : "" %>">
                </div>

                <div class="form-group">
                    <label for="emailFornecedor">Email</label>
                    <input type="email" id="emailFornecedor" name="emailFornecedor"
                           placeholder="email@exemplo.com"
                           value="<%= modoEdicao && fornecedorEdicao != null && fornecedorEdicao.getEmail() != null ? fornecedorEdicao.getEmail() : "" %>">
                </div>

                <div class="buttons">
                    <button type="submit" class="btn-primary">
                        <i class="fas <%= modoEdicao ? "fa-save" : "fa-plus" %>"></i>
                        <%= modoEdicao ? "Atualizar" : "Cadastrar" %>
                    </button>
                    <button type="button" class="btn-secondary" onclick="limparFormulario()">
                        <i class="fas fa-eraser"></i> Limpar
                    </button>
                    <% if (modoEdicao) { %>
                    <button type="button" class="btn-cancel" onclick="cancelarEdicao()">
                        <i class="fas fa-times"></i> Cancelar
                    </button>
                    <% } %>
                </div>
            </form>
        </section>

        <section class="card">
            <h2>Pesquisar Fornecedor</h2>
            <form id="searchForm" action="${pageContext.request.contextPath}/FornecedorMaterialController" method="get">
                <input type="hidden" name="action" value="pesquisar">

                <div class="form-group">
                    <label for="searchFornecedor">Nome do Fornecedor</label>
                    <input type="text" id="searchFornecedor" name="searchFornecedor"
                           placeholder="Digite o nome para pesquisar..."
                           value="<%= searchFornecedor %>">
                </div>

                <button type="submit" class="btn-search">
                    <i class="fas fa-search"></i> Buscar
                </button>
                <button type="button" class="btn-list" onclick="listarTodos()">
                    <i class="fas fa-list"></i> Listar Todos
                </button>
            </form>

            <div class="search-results">
                <% if (fornecedores != null && !fornecedores.isEmpty()) { %>
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
                    <% for (FornecedorMaterial f : fornecedores) {
                        String nome = f.getNome();
                        String contato = f.getContato() != null ? f.getContato() : "";
                        String email = f.getEmail() != null ? f.getEmail() : "";
                    %>
                    <tr>
                        <td><%= nome %></td>
                        <td><%= contato %></td>
                        <td><%= email %></td>
                        <td class="acoes">
                            <div class="action-buttons">
                                <form action="${pageContext.request.contextPath}/FornecedorMaterialController" method="get" style="display: inline;">
                                    <input type="hidden" name="action" value="carregarEdicao">
                                    <input type="hidden" name="nome" value="<%= nome %>">
                                    <button type="submit" class="btn-edit" title="Editar">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                </form>
                                <form action="${pageContext.request.contextPath}/FornecedorMaterialController" method="post" style="display: inline;">
                                    <input type="hidden" name="action" value="excluir">
                                    <input type="hidden" name="nome" value="<%= nome %>">
                                    <button type="submit" class="btn-delete"
                                            onclick="return confirm('Tem certeza que deseja excluir <%= nome %>?')"
                                            title="Excluir">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </form>
                            </div>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
                <% } else if (fornecedores != null) { %>
                <p class="info-text">
                    <i class="fas fa-info-circle"></i>
                    Nenhum fornecedor encontrado.
                </p>
                <% } %>
            </div>
        </section>
    </div>
</main>

<script>
    function limparFormulario() {
        document.getElementById('fornecedorForm').reset();
    }

    function cancelarEdicao() {
        window.location.href = '${pageContext.request.contextPath}/FornecedorMaterialController';
    }

    function listarTodos() {
        window.location.href = '${pageContext.request.contextPath}/FornecedorMaterialController';
    }

    document.addEventListener('DOMContentLoaded', function() {
        const searchInput = document.getElementById('searchFornecedor');
        if (searchInput) {
            searchInput.focus();
        }

        if (<%= modoEdicao %>) {
            document.getElementById('nomeFornecedor').focus();
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