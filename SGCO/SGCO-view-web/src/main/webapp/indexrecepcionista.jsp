<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>SGCO - Recepcionista</title>
  <link rel="stylesheet" href="index.css">
  <link rel="stylesheet" href="sidebar.css">
</head>
<body>
<aside class="sidebar">
  <a href="indexrecepcionista.html"><h2>SGCO</h2></a>
  <ul>
    <li><a href="gestao_pacientes.html">Gestão de Pacientes</a></li>
    <li><a href="agenda.html">Agenda</a></li>
    <li><a href="pagamento.html">Pagamentos</a></li>
    <li><a href="pacientes-agendados/pacientes_agendados.jsp">Pacientes Agendados</a></li>
    <li><a href="avaliacao.html">Avaliação de Profissionais</a></li>
    <li><a href="login.html" class="logout">Sair</a></li>
  </ul>
</aside>

<main class="content">
  <h1>Bem-vindo(a), Recepcionista</h1>
  <p>Selecione uma das opções ao lado para gerenciar o consultório.</p>

  <div class="cards">
    <div class="card">
      <h3>👥 Pacientes</h3>
      <p>Gerencie cadastros e informações dos pacientes.</p>
      <a href="gestao_pacientes.html">Acessar</a>
    </div>

    <div class="card">
      <h3>📅 Agenda</h3>
      <p>Agende e visualize consultas marcadas.</p>
      <a href="agenda/agenda.jsp">Acessar</a>
    </div>

    <div class="card">
      <h3>💳 Recebimento</h3>
      <p>Registre os pagamentos realizados pelos pacientes.</p>
      <a href="pagamento.html">Acessar</a>
    </div>

    <div class="card">
      <h3>📋 Pacientes Agendados</h3>
      <p>Veja a lista de pacientes com consultas marcadas.</p>
      <a href="pacientes-agendados/pacientes_agendados.jsp">Acessar</a>
    </div>

    <div class="card">
      <h3>⭐ Avaliações</h3>
      <p>Permita que os pacientes avaliem os profissionais.</p>
      <a href="avaliacao.html">Acessar</a>
    </div>
  </div>
</main>
</body>
</html>