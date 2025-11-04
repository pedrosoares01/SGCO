
<%@page contentType ="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gestão de Usuários - SGCO</title>
        <link rel="stylesheet" href="gestao_usuarios.css">
        <link rel="stylesheet" href="busca.css">
    </head>
    <body>
        <aside class="sidebar">
            <h2>SGCO</h2>
            <ul>
                <li><a href="index.jsp">Menu Principal</a></li>
                <li><a href="gestao_usuarios.jsp" class="active">Gestão de Usuários</a></li>
            </ul>
        </aside>

        <main class="content">
            <h1>Gestão de Usuários</h1>
            <form method="post" action="UsuarioController">
                
                <input type="hidden" name="acao" value="cadastrar" id="acao">
                
                <label>Nome:</label><input type="text" name="nome" required>
                
                <label>Email:</label><input type="email" name="email" required>
                
                <label>Id:</label><input type="email" name="id">
                
                <label>Cargo:</label>
                <select name="cargo" required>
                    <option>Recepcionista</option>
                    <option>Profissional</option>
                    <option>Gerente</option>
                </select>
                
                <label>Senha:</label><input type="password" name="senha" required>
                
                <div class="buttons">
                    <button type="submit" onclick="document.getElementById('acao').value='cadastrar'">Cadastrar</button>
                    <button type="submit" onclick="document.getElementById('acao').value='atualizar'">Atualizar</button>
                    <button type="submit" onclick="document.getElementById('acao').value='excluir'">Excluir</button>
                    <button  id="btnPesquisar">Pesquisar</button>
                </div>
                
            </form>
        </main>

        <main class="content" id="search">
            <h1>Pesquisar</h1>
            <div id="info-search">
                <label>Nome:</label><input type="text">
            </div>
        </main>

        <script src="busca.js"></script>
    </body>
</html>