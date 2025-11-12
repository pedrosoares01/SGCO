
<%@page contentType ="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gestão de Usuários - SGCO</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gestao_usuarios.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/busca.css">
    </head>
    <body>
        <aside class="sidebar">
            <h2>SGCO</h2>
            <ul>
                <li><a href="${pageContext.request.contextPath}/indexgerente.jsp">Menu Principal</a></li>
                <li><a href="gestao_usuarios.jsp" class="active">Gestão de Usuários</a></li>
            </ul>
        </aside>

        <main class="content">
            <h1>Gestão de Usuários</h1>
            <form method="post" action="${pageContext.request.contextPath}/UsuarioController">
                
                <input type="hidden" name="acao" value="" id="acao">
                
                <label>Nome:</label><input type="text" placeholder="nome" name="nome" value="<%= request.getAttribute("nome") == null ? "" : request.getAttribute("nome") %>" required>
                
                <label>Email:</label><input type="email" placeholder="email@gmail.com" name="email" value="<%= request.getAttribute("email") == null ? "" : request.getAttribute("email")  %>" required >
                
                <label>Id:</label><input type="text" placeholder="id" name="id" value="<%= request.getAttribute("id") == null ? "" : request.getAttribute("id") %>">
                
                <label>Cargo:</label>
                <select name="cargo" required>
                    <option value="Recepcionista" <%= "Recepcionista".equals(request.getAttribute("cargo")) ? "selected" : "" %>>Recepcionista</option>
                    <option value="Profissional" <%= "Profissional".equals(request.getAttribute("cargo")) ? "selected" : "" %>>Profissional</option>
                    <option value="Gerente" <%= "Gerente".equals(request.getAttribute("cargo")) ? "selected" : "" %>>Gerente</option>
                </select>
                
                <label>Senha:</label><input type="password" placeholder="digite a senha" name="senha" value="<%= request.getAttribute("senha") == null ? "" : request.getAttribute("senha") %>" required>
                
                <div class="buttons">
                    <button type="submit" onclick="document.getElementById('acao').value='cadastrar'">Cadastrar</button>
                    <button type="submit" onclick="document.getElementById('acao').value='atualizar'">Atualizar</button>
                    <button type="submit" onclick="document.getElementById('acao').value='excluir'">Excluir</button>
                </div>
                
            </form>
            <% if (request.getAttribute("msg") != null) { %>
            <div style="color: green; font-weight: bold;">
                <%= request.getAttribute("msg") %>
            </div>
            <% } %>
        </main>

        <main class="content" id="search">
            <h1>Pesquisar</h1>
            <div id="info-search">
                <form method="post" action="${pageContext.request.contextPath}/UsuarioController">
                    <label>Nome:</label><input type="text" name="nome" required>
                    <button  id="btnPesquisar" type="submit" onclick="document.getElementById('acao').value='pesquisar'" >Pesquisar</button>
                </form>

            </div>
        </main>
    </body>
</html>