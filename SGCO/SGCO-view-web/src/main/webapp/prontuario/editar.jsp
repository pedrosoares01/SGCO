<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="sgco.sgco.domain.Prontuario" %>
<%@ page import="sgco.sgco.domain.Usuario" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/prontuario.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
<!-- CSS novo do editar -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/editar.css">

<%
  Prontuario p = (Prontuario) request.getAttribute("prontuario");
  List<Usuario> profissionais = (List<Usuario>) request.getAttribute("profissionais");
%>

<div class="content">
  <div class="edit-container">
    <div class="edit-card">

      <h1 class="edit-title">Editar prontuário</h1>
      <p class="edit-subtitle">
        Atualize os dados do paciente e salve as alterações
      </p>

      <form action="${pageContext.request.contextPath}/ProntuarioController"
            method="post"
            class="edit-form">

        <input type="hidden" name="action" value="salvarEdicao">
        <input type="hidden" name="id" value="<%= p.getId() %>">

        <div class="form-group">
          <label>Nome</label>
          <input type="text" name="nome" value="<%= p.getNome() %>" required>
        </div>

        <div class="form-group">
          <label>Último exame</label>
          <input type="date" name="ultimoExame" value="<%= p.getUltimo_exame() %>">
        </div>

        <div class="form-group">
          <label>Profissional responsável</label>
          <select name="profissional" required>
            <option value="">Selecione</option>
            <%
              if (profissionais != null) {
                for (Usuario u : profissionais) {
            %>
            <option value="<%= u.getNome() %>"
                    <%= u.getNome().equals(p.getProfissional()) ? "selected" : "" %>>
              <%= u.getNome() %>
            </option>
            <%
                }
              }
            %>
          </select>
        </div>

        <div class="form-group">
          <label>Endereço</label>
          <input type="text" name="endereco" value="<%= p.getEndereco() %>">
        </div>

        <div class="form-group">
          <label>Telefone</label>
          <input type="text" name="telefone" value="<%= p.getTelefone() %>">
        </div>

        <div class="form-group">
          <label>Naturalidade</label>
          <input type="text" name="naturalidade" value="<%= p.getNaturalidade() %>">
        </div>

        <div class="form-group">
          <label>Profissão</label>
          <input type="text" name="profissao" value="<%= p.getProfissao() %>">
        </div>

        <div class="form-group">
          <label>Data de nascimento</label>
          <input type="date" name="dataNascimento" value="<%= p.getNascimento() %>">
        </div>

        <div class="form-group">
          <label>Estado civil</label>
          <input type="text" name="estadoCivil" value="<%= p.getEstado_civil() %>">
        </div>

        <div class="form-group">
          <label>Indicação</label>
          <input type="text" name="indicacao" value="<%= p.getIndicacao() %>">
        </div>

        <div class="form-group">
          <label>Convênio</label>
          <input type="text" name="convenio" value="<%= p.getConvenio() %>">
        </div>

        <div class="form-group">
          <label>Sexo</label>
          <div class="radio-group">
            <label>
              <input type="radio" name="sexo" value="M"
                <%= p.getSexo() == 'M' ? "checked" : "" %>>
              Masculino
            </label>
            <label>
              <input type="radio" name="sexo" value="F"
                <%= p.getSexo() == 'F' ? "checked" : "" %>>
              Feminino
            </label>
          </div>
        </div>

        <div class="form-group full">
          <label>Queixa principal (Q.P.)</label>
          <textarea name="qp"><%= p.getQp() %></textarea>
        </div>

        <div class="form-group full">
          <label>História da doença atual (H.D.A.)</label>
          <textarea name="hda"><%= p.getHda() %></textarea>
        </div>

        <div class="edit-buttons">
          <a href="${pageContext.request.contextPath}/ProntuarioController"
             class="btn-cancel">
            Cancelar
          </a>

          <button type="submit" class="btn-save">
            Salvar alterações
          </button>
        </div>

      </form>

    </div>
  </div>
</div>

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