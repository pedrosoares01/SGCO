<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="sgco.sgco.domain.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page import="sgco.sgco.domain.Prontuario" %>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Prontuário - SGCO</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/prontuario.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
  <script src="https://unpkg.com/imask"></script>
</head>

<body>
<aside class="sidebar">
  <a href="${pageContext.request.contextPath}/indexprofissional.jsp">
    <h2>SGCO</h2>
  </a>
  <ul>
      <li><a href="${pageContext.request.contextPath}/core/orcamento/pagina.jsp">Orçamento</a></li>
      <li><a href="${pageContext.request.contextPath}/estoque/estoque.jsp">Controle de Estoque</a></li>
      <li><a href="${pageContext.request.contextPath}/ProntuarioController">Prontuário</a></li>
      <li><a href="${pageContext.request.contextPath}/PacientesAgendadosController">Pacientes Agendados</a></li>
      <li><a href="${pageContext.request.contextPath}/LogoutController" class="logout">Sair</a></li>
  </ul>
</aside>

<main class="content">
  <h1>Prontuário</h1>
  <div class="container">
    <section class="card">
      <h2>Cadastrar Prontuário</h2>
      <form id="prontuarioForm" method="POST" action="${pageContext.request.contextPath}/ProntuarioController">
        <input type="hidden" name="action" id="action">
        <label>Paciente:</label>
        <input type="text" name="nome" placeholder="Nome do paciente" required>
        <label>Profissional:</label>
        <% try{ List<Usuario> profissionais = (List<Usuario>) request.getAttribute("profissionais");
        %>
        <select id="profissional" name="profissional">
          <% if (profissionais==null || profissionais.isEmpty()) { %>
          <option value="">Nenhum profissional encontrado</option>
          <% } else { %>
          <option value="">Selecione um profissional:</option>
          <% for (Usuario p : profissionais) { %>
          <option value="<%= p.getNome() %>">
            <%= p.getNome() %>
          </option>
          <% } } %>
        </select>
        <% } catch (Exception e){} %>
        <label>Data do Último Exame:</label>
        <input type="date" id="ultimoExame" name="ultimoExame" required>
        <label>Endereço:</label>
        <input type="text" id="endereco" name="endereco" required>
        <label>Telefone:</label>
        <input type="tel" id="telefone" name="telefone" pattern="\([0-9]{2}\) [0-9]{4,5}-[0-9]{4}" placeholder="(99) 91234-5678" required>
        <label>Naturalidade:</label>
        <input type="text" id="naturalidade" name="naturalidade" required>
        <label>Profissão:</label>
        <input type="text" id="profissao" name="profissao" required>
        <label>Data de Nascimento:</label>
        <input type="date" id="dataNascimento" name="dataNascimento" required>
        <div class="form-group">
          <label>Sexo</label>
          <div class="radio-group">
            <label class="radio-item">
              <input type="radio" name="sexo" value="M">
              Masculino
            </label>
            <label class="radio-item">
              <input type="radio" name="sexo" value="F">
              Feminino
            </label>
          </div>
        </div>
        <label>Estado Civil:</label>
        <input type="text" id="estadoCivil" name="estadoCivil" required>
        <label>Indicação:</label>
        <input type="text" id="indicacao" name="indicacao" required>
        <label>Convênio</label>
        <input type="text" id="convenio" name="convenio" required>
        <label>Q.P.</label>
        <textarea id="qp" name="qp" cols="40" rows="5"></textarea>
        <label>H.D.A</label>
        <textarea id="hda" name="hda" cols="40" rows="5"></textarea>
        <div class="buttons">
          <button type="submit" class="btn-primary" onclick="document.getElementById('action').value='cadastrar'">Cadastrar</button>
        </div>
      </form>
    </section>
    <section class="card">
      <h2>🔍 Pesquisar Prontuário</h2>
      <form method="POST" action="${pageContext.request.contextPath}/ProntuarioController">
        <label>Paciente ou Profissional:</label>
        <input type="text" name="nome" placeholder="Digite para pesquisar..." required>
        <div class="buttons">
          <button type="submit" class="btn-primary" onclick="document.getElementById('action').value='pesquisar'">Buscar</button>
        </div>
      </form>
        <%
            try {
                List<Prontuario> resultados = (List<Prontuario>) request.getAttribute("resultados");
                String nome = (String) request.getAttribute("nome");

                if (resultados != null && !resultados.isEmpty()) {
        %>

        <div class="search-results">
            <h3>Resultados da pesquisa por "<%= nome %>"</h3>

            <% for (Prontuario p : resultados) { %>
            <table class="prontuario-table">

                <tr class="section-title">
                    <th colspan="2">Dados do Paciente</th>
                </tr>
                <tr>
                    <th>Nome</th>
                    <td><%= p.getNome() %></td>
                </tr>
                <tr>
                    <th>Sexo</th>
                    <td><%= p.getSexo() %></td>
                </tr>
                <tr>
                    <th>Data de Nascimento</th>
                    <td><%= p.getNascimento() %></td>
                </tr>
                <tr>
                    <th>Estado Civil</th>
                    <td><%= p.getEstado_civil() %></td>
                </tr>

                <tr class="section-title">
                    <th colspan="2">Contato</th>
                </tr>
                <tr>
                    <th>Telefone</th>
                    <td><%= p.getTelefone() %></td>
                </tr>
                <tr>
                    <th>Endereço</th>
                    <td><%= p.getEndereco() %></td>
                </tr>
                <tr>
                    <th>Naturalidade</th>
                    <td><%= p.getNaturalidade() %></td>
                </tr>
                <tr>
                    <th>Profissão</th>
                    <td><%= p.getProfissao() %></td>
                </tr>

                <tr class="section-title">
                    <th colspan="2">Informações Médicas</th>
                </tr>
                <tr>
                    <th>Último Exame</th>
                    <td><%= p.getUltimo_exame() %></td>
                </tr>
                <tr>
                    <th>Profissional Responsável</th>
                    <td><%= p.getProfissional() %></td>
                </tr>
                <tr>
                    <th>Convênio</th>
                    <td><%= p.getConvenio() %></td>
                </tr>
                <tr>
                    <th>Indicação</th>
                    <td><%= p.getIndicacao() %></td>
                </tr>

                <tr class="section-title">
                    <th colspan="2">Informações Adicionais</th>
                </tr>
                <tr>
                    <th>Q.P.</th>
                    <td><%= p.getQp() %></td>
                </tr>
                <tr>
                    <th>H.D.A.</th>
                    <td><%= p.getHda() %></td>
                </tr>
                <tr class="section-title">
                    <th colspan="2">Ações</th>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <form action="ProntuarioController" method="get" style="display:inline;">
                            <input type="hidden" name="action" value="editar">
                            <input type="hidden" name="id" value="<%= p.getId() %>">
                            <button type="submit" class="btn-primary">Editar</button>
                        </form>
                        <form action="ProntuarioController" method="post" style="display:inline;" onsubmit="return confirm('Deseja realmente excluir este prontuário?');">
                            <input type="hidden" name="action" value="excluir">
                            <input type="hidden" name="id" value="<%= p.getId() %>">
                            <button type="submit" class="btn-danger">Excluir</button>
                        </form>
                    </td>
                </tr>
            </table>
            <br/>
            <% } %>
        </div>

        <%
                }
            } catch (Exception e) {}
        %>

    </section>
    <%
      String mensagem = (String) request.getAttribute("msg");
      String tipoMensagem = (String) request.getAttribute("tipoMensagem");
    %>

    <% if (mensagem != null) { %>
    <div id="popup" class="<%= tipoMensagem %>">
      <p><%= mensagem %></p>
      <button onclick="fecharPopup()">OK</button>
    </div>

    <script>
      function fecharPopup() {
        document.getElementById("popup").style.display = "none";
      }
    </script>
    <% } %>
  </div>
</main>

<script>
  const element = document.getElementById('telefone');
  const maskOptions = {
    mask: [
      { mask: '(00) 0000-0000' },
      { mask: '(00) 00000-0000' }
    ]
  };
  IMask(element, maskOptions);
</script>
</body>

</html>