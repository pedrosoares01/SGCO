<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Gestão de Receita</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/receita.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
</head>
<body>

<jsp:include page="../sidebar.jsp"/>

<main class="content">

    <h1>Gestão de Receita</h1>

    <div class="container">

        <!-- ================= REGISTRO FINANCEIRO ================= -->
        <section class="card">
            <h2>Registro Financeiro</h2>

            <!-- ================= RECEITAS ================= -->
            <h3>Receitas</h3>

            <c:if test="${empty listaReceitas}">
                <p style="color: gray; font-style: italic;">
                    Nenhuma receita encontrada.
                </p>
            </c:if>

            <c:if test="${not empty listaReceitas}">
                <table>
                    <tr>
                        <th>Descrição</th>
                        <th>Valor</th>
                        <th>Forma</th>
                        <th>Data</th>
                    </tr>

                    <c:forEach items="${listaReceitas}" var="r">
                        <tr>
                            <td>${r.descricao}</td>
                            <td>R$ ${r.valor}</td>
                            <td>${r.formaPagamento}</td>
                            <td>${r.data}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>

            <br>

            <!-- ================= DESPESAS ================= -->
            <h3>Despesas</h3>

            <c:if test="${empty listaDespesas}">
                <p style="color: gray; font-style: italic;">
                    Nenhuma despesa encontrada.
                </p>
            </c:if>

            <c:if test="${not empty listaDespesas}">
                <table>
                    <tr>
                        <th>Descrição</th>
                        <th>Valor</th>
                        <th>Forma</th>
                        <th>Data</th>
                    </tr>

                    <c:forEach items="${listaDespesas}" var="d">
                        <tr>
                            <td>${d.descricao}</td>
                            <td>R$ ${d.valor}</td>
                            <td>${d.formaPagamento}</td>
                            <td>${d.data}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>

        </section>

        <!-- ================= RELATÓRIO ================= -->
        <section class="card">
            <h2>Relatório Financeiro</h2>

            <p>
                <strong>Total de Receitas:</strong>
                R$ ${relatorio.totalReceitas}
            </p>

            <p>
                <strong>Total de Despesas:</strong>
                R$ ${relatorio.totalDespesas}
            </p>

            <h3>
                Saldo Final:
                <span style="color:${relatorio.saldo >= 0 ? 'green' : 'red'}">
                    R$ ${relatorio.saldo}
                </span>
            </h3>

            <br>

            <button class="btn-primary" onclick="window.print()">
                Emitir Relatório
            </button>
        </section>

    </div>

</main>
</body>
</html>
