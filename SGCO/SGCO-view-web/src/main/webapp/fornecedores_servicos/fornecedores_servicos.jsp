<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="sgco.sgco.domain.FornecedorServico" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fornecedores de Serviços - SGCO</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fornecedores_servicos.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
</head>
<body>
<aside class="sidebar">
    <a href="${pageContext.request.contextPath}/index.jsp"><h2>SGCO</h2></a>
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
    <h1>Gestão de Fornecedores de Serviços</h1>
    <div class="container">
        <section class="card">
            <h2>Cadastro de Serviço</h2>
            <form id="servicoForm" method="POST" action="${pageContext.request.contextPath}/FornecedorServicoController">
                <input type="hidden" name="action" id="action">
                <label>Nome do Serviço:</label>
                <input type="text" name="nomeServico" placeholder="Nome do serviço" required>
                <label>Fornecedor:</label>
                <input type="text" name="fornecedor" placeholder="Nome do fornecedor" required>
                <label>Contato:</label>
                <input type="text" name="contato" placeholder="Telefone ou email">

                <div class="buttons">
                    <button type="submit" class="btn-primary" onclick="document.getElementById('action').value='cadastrar'">Cadastrar</button>
                </div>
            </form>
        </section>

        <section class="card">
            <h2>Pesquisar Serviço</h2>
            <form method="POST" action="${pageContext.request.contextPath}/FornecedorServicoController">
                <input type="hidden" name="action" id="actionPesquisar">
                <label>Nome do Serviço ou Fornecedor:</label>
                <input type="text" name="nome" placeholder="Digite para pesquisar..." required>
                <div class="buttons">
                    <button type="submit" class="btn-primary" onclick="document.getElementById('actionPesquisar').value='pesquisar'">Buscar</button>
                </div>
            </form>
            <%
                try{
                    List<FornecedorServico> fornecedoresServicos = (List<FornecedorServico>) request.getAttribute("fornecedoresServicos");
                    String nomeServico = (String) request.getAttribute("nomeServico");
                    if (fornecedoresServicos != null && !fornecedoresServicos.isEmpty()) {
            %>
            <div class="search-results">
                <h3>Resultados da pesquisa por "<%= nomeServico != null ? nomeServico : "" %>":</h3>
                <table>
                    <tr>
                        <th>Serviço</th>
                        <th>Fornecedor</th>
                        <th>Contato</th>
                        <th>Ações</th>
                    </tr>
                    <%
                        if (fornecedoresServicos.isEmpty()) {
                    %>
                    <tr><td colspan="4">Nenhum serviço encontrado.</td></tr>
                    <%
                    } else {
                        for (FornecedorServico fs : fornecedoresServicos) {
                    %>
                    <tr>
                        <td><%= fs.getNomeServico() %></td>
                        <td><%= fs.getFornecedor() %></td>
                        <td><%= fs.getContato() != null ? fs.getContato() : "" %></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/FornecedorServicoController" method="post" style="display: inline;">
                                <input type="hidden" name="action" value="excluir">
                                <input type="hidden" name="nomeServico" value="<%= fs.getNomeServico() %>">
                                <input type="hidden" name="fornecedor" value="<%= fs.getFornecedor() %>">
                                <button type="submit" class="btn-delete" onclick="return confirm('Tem certeza que deseja excluir o serviço <%= fs.getNomeServico() %> do fornecedor <%= fs.getFornecedor() %>?')">Excluir</button>
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