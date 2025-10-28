<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section class="card">
        <h2>🔍 Pesquisar Procedimento</h2>
        <form id="searchForm">
          <div class="form-group">
            <label for="searchProcedimento">Nome do Procedimento</label>
            <input type="text" id="searchProcedimento" name="searchProcedimento" placeholder="Digite o nome para pesquisar...">
          </div>

          <button type="submit" class="btn-search">Buscar</button>
        </form>

        <div class="search-results">
          <p class="info-text">Resultados da pesquisa aparecerão aqui</p>
        </div>
      </section>
</body>
</html>