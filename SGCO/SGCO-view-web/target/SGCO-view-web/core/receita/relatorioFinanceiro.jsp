<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="card">

    <!-- Cabeçalho -->
    <h1>Relatório Financeiro</h1>
    <p><strong>Consultório Odontológico SGCO</strong></p>
    <p>CNPJ: 00.000.000/0001-00</p>
    <p>Emitido em: ${pageContext.request.time}</p>

    <hr><br>

    <!-- Totais -->
    <h2>Resumo Financeiro</h2>

    <p><strong>Total de Receitas:</strong> R$ ${relatorio.totalReceitas}</p>
    <p><strong>Total de Despesas:</strong> R$ ${relatorio.totalDespesas}</p>

    <h3>
        Saldo Final:
        <span style="color:${relatorio.saldo >= 0 ? 'green' : 'red'}">
            R$ ${relatorio.saldo}
        </span>
    </h3>

    <br>

    <button class="btn-primary" onclick="window.print()">
        Emitir Relatório / Nota Fiscal
    </button>
</section>
