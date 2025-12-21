<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <title>Controle de Equipamentos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/controleequip.css">
</head>

<body>

<div class="content">

    <h1>Controle de Equipamentos</h1>

    <div class="container">

        <!-- Card 1 – Cadastro -->
        <div class="card">
            <h2>Cadastrar Equipamento</h2>

            <form action="CadastrarEquipamento" method="post">
                <div>
                    <label for="nome">Nome do Equipamento</label>
                    <input type="text" id="nome" name="nome" placeholder="Ex: Autoclave">
                </div>

                <div>
                    <label for="codigo">Código / Identificação</label>
                    <input type="text" id="codigo" name="codigo" placeholder="Ex: EQP-0021">
                </div>

                <div>
                    <label for="local">Local / Sala</label>
                    <input type="text" id="local" name="local" placeholder="Ex: Sala 01">
                </div>

                <div>
                    <label for="ultima">Última Manutenção</label>
                    <input type="date" id="ultima" name="ultima">
                </div>

                <div>
                    <label for="freq">Frequência da Manutenção (meses)</label>
                    <input type="number" id="freq" name="freq" placeholder="Ex: 6">
                </div>

                <div>
                    <label for="status">Status</label>
                    <select id="status" name="status">
                        <option value="Funcionando">Funcionando</option>
                        <option value="Em manutenção">Em manutenção</option>
                        <option value="Aguardando manutenção">Aguardando manutenção</option>
                    </select>
                </div>

                <button type="submit" class="btn-primary">Salvar Equipamento</button>
            </form>
        </div>

        <!-- Card 2 – Listagem -->
        <div class="card big-card">
            <h2>Equipamentos Cadastrados</h2>

            <!-- Barra de Pesquisa -->
            <input type="text" id="buscar" placeholder="Pesquisar equipamento..." 
                   onkeyup="filtrar()">

            <div class="table-wrapper">
                <table id="tabela-equip">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Código</th>
                            <th>Local</th>
                            <th>Status</th>
                            <th>Próxima Manutenção</th>
                            <th>Ações</th>
                        </tr>
                    </thead>

                    <tbody>

                    <%-- Exemplo estático — pode substituir por lista vinda do backend --%>
                    <tr>
                        <td>Autoclave</td>
                        <td>EQP-001</td>
                        <td>Esterilização</td>
                        <td>Funcionando</td>
                        <td>15/03/2025</td>
                        <td>
                            <button class="btn-secondary">Editar</button>
                            <button class="btn-search">Manutenção</button>
                        </td>
                    </tr>

                    <tr class="linha-atrasado">
                        <td>Compressor</td>
                        <td>EQP-014</td>
                        <td>Sala Técnica</td>
                        <td>Atrasado</td>
                        <td class="texto-vermelho">Vencida</td>
                        <td>
                            <button class="btn-secondary">Editar</button>
                            <button class="btn-search">Manutenção</button>
                        </td>
                    </tr>

                    <tr class="linha-alerta">
                        <td>Raio-X</td>
                        <td>EQP-099</td>
                        <td>Sala 02</td>
                        <td>Funcionando</td>
                        <td class="texto-destaque">Faltam 10 dias</td>
                        <td>
                            <button class="btn-secondary">Editar</button>
                            <button class="btn-search">Manutenção</button>
                        </td>
                    </tr>

                    </tbody>
                </table>
            </div>
        </div>

    </div>

</div>

<script>
function filtrar(){
    let termo = document.getElementById("buscar").value.toLowerCase();
    let linhas = document.querySelectorAll("#tabela-equip tbody tr");

    linhas.forEach(linha =>{
        let texto = linha.textContent.toLowerCase();
        linha.style.display = texto.includes(termo) ? "table-row" : "none";
    });
}
</script>

</body>
</html>
