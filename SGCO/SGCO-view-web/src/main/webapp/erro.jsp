
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Erro</title>
  <link rel="stylesheet" href="css/index.css">
</head>
<body>
  <div>
    <h1>Ocorreu um erro</h1>
    <p><%=request.getParameter("msg")%></p>

    <div class="buttons">
      <button onclick="history.back()">Voltar</button>
    </div>
  </div>
</body>
</html>
