<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="sgco.sgco.domain.FornecedorServico" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fornecedores de Serviços - SGCO</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/sidebar.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/fornecedores_servicos.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

<aside class="sidebar">
    <a href="../index.jsp"><h2>SGCO</h2></a>
    <ul>
        <li><a href="gestao_pacientes.jsp">Gestão de Pacientes</a></li>
        <li><a href="gestao_usuarios.jsp">Gestão de Usuários</a></li>
        <li><a href="gestao_procedimentos.jsp">Gestão de Procedimentos</a></li>
        <li><a href="estoque.jsp">Controle de Estoque</a></li>
        <li><a href="fornecedores_materiais.jsp">Fornecedores de Materiais</a></li>
        <li><a href="fornecedores_servicos.jsp" class="active">Fornecedores de Serviços</a></li>
        <li><a href="receita.jsp">Gestão da Receita</a></li>
        <li><a href="pacientes_agendados.jsp">Pacientes Agendados</a></li>
        <li><a href="login.jsp" class="logout">Sair</a></li>
    </ul>
</aside>

<main class="content">
    <h1>Gestão de Fornecedores de Serviços</h1>

    <%
        String mensagem = (String) request.getAttribute("mensagem");
        String tipoMensagem = (String) request.getAttribute("tipoMensagem");
        String searchServico = (String) request.getParameter("searchServico");
        String searchFornecedor = (String) request.getParameter("searchFornecedor");
        if (searchServico == null) searchServico = "";
        if (searchFornecedor == null) searchFornecedor = "";

        boolean modoEdicao = request.getAttribute("modoEdicao") != null && (Boolean) request.getAttribute("modoEdicao");
        FornecedorServico fornecedorServicoEdicao = (FornecedorServico) request.getAttribute("fornecedorServicoEdicao");
        String nomeServicoOriginal = (String) request.getAttribute("nomeServicoOriginal");
        String fornecedorOriginal = (String) request.getAttribute("fornecedorOriginal");

        List<FornecedorServico> fornecedoresServicos = (List<FornecedorServico>) request.getAttribute("fornecedoresServicos");
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
            <h2><%= modoEdicao ? "Editar Serviço" : "Cadastro de Serviço" %></h2>
            <form id="servicoForm" action="${pageContext.request.contextPath}/FornecedorServicoController" method="post">
                <input type="hidden" name="action" value="<%= modoEdicao ? "editar" : "cadastrar" %>">
                <% if (modoEdicao) { %>
                <input type="hidden" name="nomeServicoOriginal" value="<%= nomeServicoOriginal %>">
                <input type="hidden" name="fornecedorOriginal" value="<%= fornecedorOriginal %>">
                <% } %>

                <div class="form-group">
                    <label for="nomeServico" class="required">Nome do Serviço</label>
                    <input type="text" id="nomeServico" name="nomeServico"
                           placeholder="Nome do serviço" required
                           value="<%= modoEdicao && fornecedorServicoEdicao != null ? fornecedorServicoEdicao.getNomeServico() : "" %>">
                </div>

                <div class="form-group">
                    <label for="fornecedor" class="required">Fornecedor</label>
                    <input type="text" id="fornecedor" name="fornecedor"
                           placeholder="Nome do fornecedor" required
                           value="<%= modoEdicao && fornecedorServicoEdicao != null ? fornecedorServicoEdicao.getFornecedor() : "" %>">
                </div>

                <div class="form-group">
                    <label for="contato">Contato</label>
                    <input type="text" id="contato" name="contato"
                           placeholder="Telefone ou email"
                           value="<%= modoEdicao && fornecedorServicoEdicao != null && fornecedorServicoEdicao.getContato() != null ? fornecedorServicoEdicao.getContato() : "" %>">
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
            <h2>Pesquisar Serviço</h2>
            <form id="searchForm" action="${pageContext.request.contextPath}/FornecedorServicoController" method="get">
                <input type="hidden" name="action" value="pesquisar">

                <div class="form-group">
                    <label for="searchServico">Nome do Serviço</label>
                    <input type="text" id="searchServico" name="searchServico"
                           placeholder="Digite o nome do serviço..."
                           value="<%= searchServico %>">
                </div>

                <div class="form-group">
                    <label for="searchFornecedor">Nome do Fornecedor</label>
                    <input type="text" id="searchFornecedor" name="searchFornecedor"
                           placeholder="Digite o nome do fornecedor..."
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
                <% if (fornecedoresServicos != null && !fornecedoresServicos.isEmpty()) { %>
                <table class="results-table">
                    <thead>
                    <tr>
                        <th>Serviço</th>
                        <th>Fornecedor</th>
                        <th>Contato</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (FornecedorServico fs : fornecedoresServicos) {
                        String nomeServico = fs.getNomeServico();
                        String fornecedor = fs.getFornecedor();
                        String contato = fs.getContato() != null ? fs.getContato() : "";
                    %>
                    <tr>
                        <td><%= nomeServico %></td>
                        <td><%= fornecedor %></td>
                        <td><%= contato %></td>
                        <td class="acoes">
                            <div class="action-buttons">
                                <form action="${pageContext.request.contextPath}/FornecedorServicoController" method="get" style="display: inline;">
                                    <input type="hidden" name="action" value="carregarEdicao">
                                    <input type="hidden" name="nomeServico" value="<%= nomeServico %>">
                                    <input type="hidden" name="fornecedor" value="<%= fornecedor %>">
                                    <button type="submit" class="btn-edit" title="Editar">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                </form>
                                <form action="${pageContext.request.contextPath}/FornecedorServicoController" method="post" style="display: inline;">
                                    <input type="hidden" name="action" value="excluir">
                                    <input type="hidden" name="nomeServico" value="<%= nomeServico %>">
                                    <input type="hidden" name="fornecedor" value="<%= fornecedor %>">
                                    <button type="submit" class="btn-delete"
                                            onclick="return confirm('Tem certeza que deseja excluir o serviço <%= nomeServico %> do fornecedor <%= fornecedor %>?')"
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
                <% } else if (fornecedoresServicos != null) { %>
                <p class="info-text">
                    <i class="fas fa-info-circle"></i>
                    Nenhum serviço encontrado.
                </p>
                <% } %>
            </div>
        </section>
    </div>
</main>

<script>
    function limparFormulario() {
        document.getElementById('servicoForm').reset();
    }

    function cancelarEdicao() {
        window.location.href = '${pageContext.request.contextPath}/FornecedorServicoController';
    }

    function listarTodos() {
        window.location.href = '${pageContext.request.contextPath}/FornecedorServicoController';
    }

    document.addEventListener('DOMContentLoaded', function() {
        const searchServico = document.getElementById('searchServico');
        if (searchServico) {
            searchServico.focus();
        }

        if (<%= modoEdicao %>) {
            document.getElementById('nomeServico').focus();
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