<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="br.ustj.model.Cliente"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>cerveja.biz - Alterar Cliente</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css"
	integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ"
	crossorigin="anonymous">

<link href="css/style.css" rel="stylesheet">

</head>

<body>
	<!-- Barra superior com os menus de navegação -->
	<c:import url="Menu.jsp" />

	<!-- Container Principal -->
	<div id="main" class="container">
		<h3 class="page-header">Alterar Cliente #${cliente.id }</h3>
		
		<!-- Formulario para alteração de clientes -->
		<form action="controller.do" method="post">
			<!-- area de campos do form -->
			<input type="hidden" name="id" value="${cliente.id }" />
			<div class="row">
				<div class="form-group col-md-12">
					<label for="nome">Nome</label> <input type="text"
						class="form-control" name="nome" id="nome" required
						maxlength="100" placeholder="nome completo"
						value="${cliente.nome }">
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-6">
					<label for="fone">Celular</label> <input type="tel"
						class="form-control" name="fone" id="fone" maxlength="15"
						pattern="(?:\(\d{2}\)|\d{2})[- ]?\d{5}[- ]?\d{4}"
						placeholder="opcional; celular com ddd no formato (99) 99999-9999"
						value="${cliente.fone }">
				</div>

				<div class="form-group col-md-6">
					<label for="email">E-Mail</label> <input type="email"
						class="form-control" name="email" id="email" required
						maxlength="60" placeholder="email obrigatório"
						value="${cliente.email }">
				</div>
			</div>
			<hr />
			<div id="actions" class="row">
				<div class="col-md-12">
					<button type="submit" class="btn btn-primary" name="command"
						value="AlterarCliente">Salvar</button>
					<a href="ListarClientes.jsp" class="btn btn-default">Cancelar</a>
				</div>
			</div>
		</form>
	</div>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"
		integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ"
		crossorigin="anonymous"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
		integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd"
		crossorigin="anonymous"></script>

</body>

</html>
