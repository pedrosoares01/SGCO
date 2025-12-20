<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="sgco.sgco.domain.Material" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Controle de Estoque - SGCO</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estoque.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

<aside class="sidebar">
    <a href="indexgerente.jsp"><h2>SGCO</h2></a>
    <ul>
        <li><a href="gestao_pacientes.jsp">Gestão de Pacientes</a></li>
        <li><a href="gestao_usuarios.jsp">Gestão de Usuários</a></li>
        <li><a href="gestao_procedimentos.jsp">Gestão de Procedimentos</a></li>
        <li><a href="${pageContext.request.contextPath}/MaterialController" style="background: #0077b6;">Controle de Estoque</a></li>
        <li><a href="fornecedores_materiais.jsp">Fornecedores de Materiais</a></li>
        <li><a href="fornecedores_servicos.jsp">Fornecedores de Serviços</a></li>
        <li><a href="receita.jsp">Gestão da Receita</a></li>
        <li><a href="pacientes_agendados.jsp">Pacientes Agendados</a></li>
        <li><a href="relatorios.jsp">Relatórios Gerenciais</a></li>
        <li><a href="login.jsp" class="logout">Sair</a></li>
    </ul>
</aside>

<main class="content">
    <h1>Controle de Estoque</h1>

    <%
        String mensagem = (String) request.getAttribute("mensagem");
        String tipoMensagem = (String) request.getAttribute("tipoMensagem");
        String searchMaterial = (String) request.getParameter("searchMaterial");
        if (searchMaterial == null) searchMaterial = "";

        boolean modoEdicao = request.getAttribute("modoEdicao") != null && (Boolean) request.getAttribute("modoEdicao");
        Material materialEdicao = (Material) request.getAttribute("materialEdicao");
        String nomeOriginal = (String) request.getAttribute("nomeOriginal");

        List<Material> materiais = (List<Material>) request.getAttribute("materiais");
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
            <h2><%= modoEdicao ? "Editar Material" : "Cadastro de Material" %></h2>
            <form id="materialForm" action="${pageContext.request.contextPath}/MaterialController" method="post">
                <input type="hidden" name="action" value="<%= modoEdicao ? "editar" : "cadastrar" %>">
                <% if (modoEdicao) { %>
                <input type="hidden" name="nomeOriginal" value="<%= nomeOriginal %>">
                <% } %>

                <div class="form-group">
                    <label for="nome" class="required">Nome do Material</label>
                    <input type="text" id="nome" name="nome"
                           placeholder="Nome do material" required
                           value="<%= modoEdicao && materialEdicao != null ? materialEdicao.getNome() : "" %>">
                </div>

                <div class="form-group">
                    <label for="quantidade" class="required">Quantidade</label>
                    <input type="number" id="quantidade" name="quantidade"
                           placeholder="Quantidade em estoque" required min="0"
                           value="<%= modoEdicao && materialEdicao != null ? materialEdicao.getQuantidade() : "" %>">
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
            <h2>Pesquisar Material</h2>
            <form id="searchForm" action="${pageContext.request.contextPath}/MaterialController" method="get">
                <input type="hidden" name="action" value="pesquisar">

                <div class="form-group">
                    <label for="searchMaterial">Nome do Material</label>
                    <input type="text" id="searchMaterial" name="nome"
                           placeholder="Digite o nome para pesquisar..."
                           value="<%= searchMaterial %>">
                </div>

                <button type="submit" class="btn-search">
                    <i class="fas fa-search"></i> Buscar
                </button>
                <button type="button" class="btn-list" onclick="listarTodos()">
                    <i class="fas fa-list"></i> Listar Todos
                </button>
            </form>

            <div class="search-results">
                <% if (materiais != null && !materiais.isEmpty()) { %>
                <table class="results-table">
                    <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Quantidade</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (Material m : materiais) {
                        String nome = m.getNome();
                        int quantidade = m.getQuantidade();
                    %>
                    <tr>
                        <td><%= nome %></td>
                        <td><%= quantidade %></td>
                        <td class="acoes">
                            <div class="action-buttons">
                                <form action="${pageContext.request.contextPath}/MaterialController" method="get" style="display: inline;">
                                    <input type="hidden" name="action" value="carregarEdicao">
                                    <input type="hidden" name="nome" value="<%= nome %>">
                                    <button type="submit" class="btn-edit" title="Editar">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                </form>
                                <form action="${pageContext.request.contextPath}/MaterialController" method="post" style="display: inline;">
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
                <% } else if (materiais != null) { %>
                <p class="info-text">
                    <i class="fas fa-info-circle"></i>
                    Nenhum material cadastrado no estoque.
                </p>
                <% } %>
            </div>
        </section>
    </div>
</main>

<script>
    function limparFormulario() {
        document.getElementById('materialForm').reset();
    }

    function cancelarEdicao() {
        window.location.href = '${pageContext.request.contextPath}/MaterialController';
    }

    function listarTodos() {
        window.location.href = '${pageContext.request.contextPath}/MaterialController';
    }

    document.addEventListener('DOMContentLoaded', function() {
        const searchInput = document.getElementById('searchMaterial');
        if (searchInput) {
            searchInput.focus();
        }

        // Se estiver em modo de edição, focar no primeiro campo
        if (<%= modoEdicao %>) {
            document.getElementById('nome').focus();
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