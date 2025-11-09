<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<aside class="sidebar">
  <a href=""><h2>SGCO</h2></a>
  <ul>
    <li><a href="/SGCO-view-web/core/procedimentos/pagina.jsp" class="active">Gestão de Procedimentos</a></li>

    <li class="menu-item">
      <div class="menu-link">
        <a href="/SGCO-view-web/core/orcamento/pagina.jsp" class="active">Orçamento</a>
        <span class="arrow" role="button" aria-expanded="false" onclick="toggleSubmenu(this)">&#9654;</span>
      </div>

      <ul class="submenu" aria-hidden="true">
        <li><a href="/SGCO-view-web/core/orcamento/orcamentoForm.jsp">Consultar Orçamentos</a></li>
      </ul>
    </li>

    <li><a href="" class="logout">Sair</a></li>
  </ul>
</aside>

<script>
/* Toggle do submenu (clicar só na setinha) */
function toggleSubmenu(arrow) {
  const menuItem = arrow.closest('.menu-item');
  if (!menuItem) return;

  const submenu = menuItem.querySelector('.submenu');
  const expanded = arrow.getAttribute('aria-expanded') === 'true';

  if (!submenu) return;

  if (expanded) {
    // fechar
    submenu.classList.remove('open');
    submenu.setAttribute('aria-hidden', 'true');
    arrow.setAttribute('aria-expanded', 'false');
    arrow.classList.remove('rotated');
  } else {
    // abrir
    submenu.classList.add('open');
    submenu.setAttribute('aria-hidden', 'false');
    arrow.setAttribute('aria-expanded', 'true');
    arrow.classList.add('rotated');
  }
}
</script>
