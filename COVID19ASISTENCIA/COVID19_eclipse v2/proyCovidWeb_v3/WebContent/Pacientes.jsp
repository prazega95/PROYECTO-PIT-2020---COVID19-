<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
		<title login-heading> PROYECTO COVID19</title>
	</head>
	<body>
	

	<h1 class="text-center">Lista de Ciudadanos</h1>
    <br>


		<div class="d-flex">
	
		<div class="row align-items-start"> 
		
			<div class="card col-sm-4">
		
				<div class="card-body">
					<form action="ControladorPacientes?menu=Paciente" method="POST">
					
						<div class="form-group">
							<label>NOMBRES</label>
							<input type="text" value="${paciente.getNomPac() }" name="txtnombres" class="form-control">
						</div>
						
							<div class="form-group">
							<label>APELLIDO</label>
							<input type="text" value="${paciente.getApePac() }" name="txtapellido" class="form-control">
						</div>
						
							<div class="form-group">
							<label>DNI</label>
							<input type="text" value="${paciente.getDniPac() }" name="txtdocumento" class="form-control">
						</div>
						
						<div class="form-group">
							<label>TELEFONO</label>
							<input type="text" value="${paciente.getFonoPac() }" name="txtfono" class="form-control">
						</div>
						<div class="form-group">
							<label>USUARIO</label>
							<input type="text" value="${paciente.getUserPac() }" name="txtuser" class="form-control">
						</div>
						<div class="form-group">
							<label>CONTRASEÑA</label>
							<input type="text" value="${paciente.getPassPac() }" name="txtpass" class="form-control">
						</div>
						<input type="submit" name="accion" value="Agregar" class="btn btn-info">
						<input type="submit" name="accion" value="Actualizar" class="btn btn-success">
					</form>
				</div>
			</div>
		
		
		
		
		
			<div class="col-sm-8">
				<table class="table table-hover">
					<thead>	
						<tr>
							<th>ID</th>
							<th>NOMBRES</th>
						    <th>APELLIDO</th>
						    <th>DNI</th>
							<th>TELEFONO</th>
							<th>USUARIO</th>
							<th>CONTRASEÑA</th>
							<th>ACCION</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="paci" items="${pacientes}">
						<tr> 
						<td>${paci.getIdPac() }</td>
						<td>${paci.getNomPac() }</td>
						<td>${paci.getApePac() }</td>
						<td>${paci.getDniPac() }</td>
						<td>${paci.getFonoPac() }</td>
						<td>${paci.getUserPac() }</td>
						<td>${paci.getPassPac() }</td>
							<td>
								<a class="btn btn-warning" href="ControladorPacientes?menu=Paciente&accion=Editar&id=${paci.getIdPac() }">Editar</a>
								<a class="btn btn-danger" href="ControladorPacientes?menu=Paciente&accion=Eliminar&id=${paci.getIdPac() }">Eliminar</a>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
		
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	</body>
</html>