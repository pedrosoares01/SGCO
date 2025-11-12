
<%@page contentType ="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>SGCO - Login</title>
  <link rel="stylesheet" href="css/login.css">
</head>
<body>
  <div class="login-container">
    <h1>SGCO</h1>
    <p>Sistema de Gestão de Consultórios Odontológicos</p>

    <form  method="post" action="${pageContext.request.contextPath}/LoginController">
      <label for="usuario">Usuário</label>
      <input type="text" id="usuario"  name="nome" placeholder="Digite seu usuário">

      <label for="senha">Senha</label>
      <input type="password" id="senha"  name="senha" placeholder="Digite sua senha">

      <button type="submit">Entrar</button>
    </form>
  </div>
</body>
</html>