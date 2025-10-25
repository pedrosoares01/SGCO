<%@page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Pacientes Agendados - SGCO</title>
  <link rel="stylesheet" href="pacientes_agendados.css">
  <link rel="stylesheet" href="../sidebar.css">
</head>
<body>
<aside class="sidebar">
  <a href="index.html"><h2>SGCO</h2></a>
  <ul>
    <li><a href="gestao_pacientes.html">Gestão de Pacientes</a></li>
    <li><a href="gestao_usuarios.html">Gestão de Usuários</a></li>
    <li><a href="gestao_procedimentos.html">Gestão de Procedimentos</a></li>
    <li><a href="estoque.html">Controle de Estoque</a></li>
    <li><a href="fornecedores_materiais.html">Fornecedores de Materiais</a></li>
    <li><a href="fornecedores_servicos.html">Fornecedores de Serviços</a></li>
    <li><a href="receita.html">Gestão da Receita</a></li>
    <li><a href="../pacientes-agendados/pacientes_agendados.jsp" class="active">Pacientes Agendados</a></li>
    <li><a href="login.html" class="logout">Sair</a></li>
  </ul>
</aside>

<main class="content">
  <h1>Pacientes Agendados</h1>

  <div class="container">
    <section class="card">
      <h2>Relatórios de Agendamentos</h2>
      <p>Visualize e gere relatórios dos pacientes com consultas agendadas.</p>
      <button class="btn-primary">Gerar Relatório</button>
    </section>
  </div>
</main>
</body>
</html>
