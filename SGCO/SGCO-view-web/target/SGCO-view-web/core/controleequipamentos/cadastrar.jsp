<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <title>Cadastrar Equipamento</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/controleequip.css">
</head>

<body>

	    <a href="pagina.jsp">
    <button>Voltar</button>
    	</a>
    	
<div class="content">

    <h1>Cadastrar Equipamento</h1>

    <div class="container">

        <div class="card">
			<form action="${pageContext.request.contextPath}/CadastrarEquipamento" method="post">

    <div>
        <label for="nome">Nome do Equipamento</label>
        	<select id="nome" name="nome">
        	<option value="Cadeira odontológica">Cadeira odontológica</option>
			<option value="Equipo odontológico">Equipo odontológico</option>
			<option value="Refletor odontológico">Refletor odontológico</option>
			<option value="Compressor de ar">Compressor de ar</option>
			<option value="Bomba a vácuo">Bomba a vácuo</option>
			<option value="Sugador cirúrgico">Sugador cirúrgico</option>
			<option value="Autoclave">Autoclave</option>
			<option value="Estufa de esterilização">Estufa de esterilização</option>
			<option value="Seladora térmica">Seladora térmica</option>
			<option value="Caneta de alta rotação">Caneta de alta rotação</option>
			<option value="Caneta de baixa rotação">Caneta de baixa rotação</option>
			<option value="Micromotor">Micromotor</option>
			<option value="Contra-ângulo">Contra-ângulo</option>
			<option value="Ultrassom odontológico">Ultrassom odontológico</option>
			<option value="Jato de bicarbonato">Jato de bicarbonato</option>
			<option value="Raio-X odontológico">Raio-X odontológico</option>
			<option value="Aparelho de raio-X portátil">Aparelho de raio-X portátil</option>
			<option value="Cuba ultrassônica">Cuba ultrassônica</option>
			<option value="Laser terapêutico">Laser terapêutico</option>
			<option value="Motor de implante">Motor de implante</option>
        	
        </select>
    </div>

    <div>
        <label for="codigo">Identificação com código</label>
        <input type="text" id="codigo" name="codigo" value="EQP-" placeholder="Ex: EQP-0021">
    </div>

    <div>
        <label for="local">Local / Sala</label>
        <input type="text" id="local" name="local" value="Sala " placeholder="Ex: Sala 01">
    </div>

    <div>
        <label for="ultima">Última Manutenção</label>
        <input type="date" max="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>" id="ultima" name="ultima">
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

    </div>

</div>
</body>
</html>
