
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
      <li><a href="index.html">ð  Menu Principal</a></li>
      <li><a href="gestao_usuarios.html" class="active">Gestão de UsuÃ¡rios</a></li>
    </ul>
  </aside>

  <main class="content">
    <h1>GestÃ£o de UsuÃ¡rios</h1>
    <form method="post" action="UsuarioController">
        <label>Nome:</label><input type="text" name="nome">
      <label>Email:</label><input type="email" name="email">
      <label>Cargo:</label>
      
      <select name="cargo">
        <option>Recepcionista</option>
        <option>Profissional</option>
        <option>Gerente</option>
      </select>
      <label>Senha:</label><input type="password" name="senha">
      <div class="buttons">
        <button>Cadastrar</button>
        <button type="submit" id="btnPesquisar">Pesquisar</button>
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