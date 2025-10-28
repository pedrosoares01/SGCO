<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestão de Procedimentos</title>
</head>
<body>
	<section class="card">
        <h2>Cadastro de Procedimento</h2>
        <form id="procedimentoForm">
          <div class="form-group">
            <label for="procedimento">Procedimento</label>
            <input type="text" id="procedimento" name="procedimento" placeholder="Nome do procedimento" required>
          </div>

          <div class="form-group">
            <label for="preco">Preço</label>
            <input type="number" id="preco" name="preco" step="0.01" placeholder="Valor do procedimento" required>
          </div>

          <div class="buttons">
            <button type="submit" class="btn-primary">Cadastrar</button>
            <button type="button" class="btn-secondary">Limpar</button>
          </div>
        </form>
      </section>
</body>
</html>