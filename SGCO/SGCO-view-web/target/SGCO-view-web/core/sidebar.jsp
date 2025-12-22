<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<aside class="sidebar">
  <a href=""><h2>SGCO</h2></a>
  <ul>
    <li><a href="${pageContext.request.contextPath}/core/procedimentos/pagina.jsp" class="active">Gestão de Procedimentos</a></li>
    
    <li><a href="${pageContext.request.contextPath}/core/paciente/pagina.jsp" class="active">Gestão de Pacientes</a></li>
    
    <li><a href="${pageContext.request.contextPath}/core/controleequipamentos/pagina.jsp" class="active">Gestão de Equipamentos</a></li>

    <li><a href="${pageContext.request.contextPath}/core/receita/pagina.jsp" class="active">Gestão de Receita</a></li>

    <li><a href="${pageContext.request.contextPath}/core/orcamento/pagina.jsp" class="active">Orçamento</a></li>
        
    <li><a href="${pageContext.request.contextPath}/core/pagamento/pagina.jsp" class="active">Pagamentos</a></li>

    <li><a href="" class="logout">Sair</a></li>
  </ul>
</aside>

