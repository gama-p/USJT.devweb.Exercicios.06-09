<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="br.ustj.model.Pais"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Buscar Paises</title>

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

	<!-- Modal -->
	<div class="modal fade" id="delete-modal" tabindex="-1" role="dialog"
		aria-labelledby="modalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Fechar">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="modalLabel">Excluir Pais</h4>
				</div>
				<div class="modal-body">Deseja realmente excluir este pais?</div>
				<div class="modal-footer">
					<form action="ManterPais.do" method="post">
						<input type="hidden" name="id" id="id_excluir" />
						<button type="submit" class="btn btn-primary" name="acao"
							value="Excluir">Sim</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">N&atilde;o</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- /.modal -->

	<!-- Barra superior com os menus de navegação -->
	<c:import url="Menu.jsp" />

	<!-- Container Principal -->
	<div id="main" class="container">
		<h3 class="page-header">Paises</h3>

		<form action="listar_paises.do" method="post">
			<div id="top" class="row">

				<div class="col-md-6">
					<div class="input-group h2">
						<input name="data[search]" class="form-control" id="search"
							type="text"
							placeholder="Pesquisar Paises (deixe vazio para trazer todos)">
						<span class="input-group-btn">
							<button class="btn btn-primary" type="submit" name="acao"
								value="buscar">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</span>
					</div>
				</div>

				<div class="col-md-3">
					<a href="CriarPais.jsp" class="btn btn-primary pull-right h2">Novo
						Pais</a>
				</div>
			</div>
			<!-- /#top -->
		</form>
		<hr />
		<c:if test="${not empty lista}">
			<div id="list" class="row">

				<div class="table-responsive col-md-12">
					<table class="table table-striped" cellspacing="0" cellpadding="0">
						<thead>
							<tr>
								<th>ID</th>
								<th>Nome</th>
								<th>População</th>
								<th>Área</th>
								<th class="actions">Ações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="pais" items="${lista }">
								<tr>
									<td>${pais.id }</td>
									<td>${pais.nome }</td>
									<td>${pais.populacao }</td>
									<td>${pais.area }</td>
									<td class="actions"><a class="btn btn-success btn-xs"
										href="ManterPais.do?acao=Visualizar&id=${pais.id }">Visualizar</a>
										<a class="btn btn-warning btn-xs"
										href="ManterPais.do?acao=Editar&id=${pais.id }">Editar</a>
										<button id="btn${pais.id }%>" type="button"
											class="btn btn-danger btn-xs" data-toggle="modal"
											data-target="#delete-modal" data-pais="${pais.id }">Excluir</button>
									</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>

				</div>
			</div>
			<!-- /#list -->

			<div id="bottom" class="row">
				<div class="col-md-12">
					<!-- paginação ainda não foi implementada -->
					<ul class="pagination">
						<li class="disabled"><a>&lt; Anterior</a></li>
						<li class="disabled"><a>1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li class="next"><a href="#" rel="next">Próximo &gt;</a></li>
					</ul>
					<!-- /.pagination -->
				</div>
			</div>
		</c:if>
		<!-- /#bottom -->
	</div>
	<!-- /#main -->



	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"
		integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ"
		crossorigin="anonymous"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
		integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd"
		crossorigin="anonymous"></script>

	<script type="text/javascript">
		$("#delete-modal").on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget); //botao que disparou a modal
			var recipient = button.data('pais');
			$("#id_excluir").val(recipient);
		});
	</script>

</body>

</html>
