document.addEventListener("DOMContentLoaded", function() {
    const botao = document.getElementById("btnMedia");
    botao.addEventListener("click", function() {
        const rows = document.querySelectorAll(".search-results table tr:not(:first-child)");
        const dados = {};
        rows.forEach(row => {
            const cols = row.querySelectorAll("td");
            if (cols.length === 2) {
                const nome = cols[0].textContent.trim();
                const nota = parseFloat(cols[1].textContent.trim());
                if (!isNaN(nota)) {
                    if (!dados[nome]) {
                        dados[nome] = { soma: 0, qtd: 0 };
                    }
                    dados[nome].soma += nota;
                    dados[nome].qtd++;
                }
            }
        });
        if (Object.keys(dados).length === 0) {
            alert("Nenhuma avaliação encontrada para calcular a média.");
            return;
        }
        const tabela = document.querySelector(".search-results table");
        tabela.innerHTML = `
      <tr>
        <th>Profissional</th>
        <th>Média das Notas</th>
      </tr>
    `;
        for (const [profissional, info] of Object.entries(dados)) {
            const media = (info.soma / info.qtd).toFixed(2);
            const linha = document.createElement("tr");
            linha.innerHTML = `
        <td>${profissional}</td>
        <td>${media}</td>
      `;
            tabela.appendChild(linha);
        }
    });
});
