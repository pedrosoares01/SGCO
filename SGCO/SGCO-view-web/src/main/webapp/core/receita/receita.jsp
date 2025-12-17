<section class="card">
    <h2>Registro de Despesas</h2>

    <form method="post" action="${pageContext.request.contextPath}/DespesaController">
        <input type="hidden" name="acao" value="cadastrar">

        <div class="form-group">
            <label for="expense">Despesa</label>
            <input type="text" id="expense" name="expense" required>
        </div>

        <div class="form-group">
            <label for="amount">Valor (R$)</label>
            <input type="number" id="amount" name="amount" step="0.01" required>
        </div>

        <div class="form-group">
            <label for="payment">Forma de Pagamento</label>
            <select id="payment" name="payment">
                <option>Dinheiro</option>
                <option>PIX</option>
                <option>Cartão</option>
            </select>
        </div>

        <div class="buttons">
            <button type="submit" class="btn-primary">Registrar</button>

            <a href="${pageContext.request.contextPath}/DespesaController?acao=editar&id=1">
    			Editar
			</a>


        </div>
    </form>
</section>
