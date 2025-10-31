<section class="card">
    <h2>Pesquisar <%= (request.getAttribute("tituloPesquisa") != null ? request.getAttribute("tituloPesquisa") : "Item") %></h2>
    <form id="searchForm">
        <div class="form-group">
            <label for="searchProcedimento">Nome do <%= (request.getAttribute("tituloPesquisa") != null ? request.getAttribute("tituloPesquisa") : "Item") %></label>
            <input type="text" id="searchProcedimento" name="searchProcedimento" placeholder="Digite o nome para pesquisar...">
        </div>
        <button type="submit" class="btn-search">Buscar</button>
    </form>

    <div class="search-results">
        <p class="info-text">Resultados da pesquisa aparecerão aqui</p>
    </div>
</section>