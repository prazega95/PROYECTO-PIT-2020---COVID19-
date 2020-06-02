<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
		<title>PROYECTO COVID19</title>

	</head>
	
		<body>
	
		<div class="container">
			<h1 class="text-center">Situacion Medica de Pacientes</h1>
			
			<br><br>
			
			<table class="table" id="table">
				<thead class="thead-light text-center">
					<tr>
						<th>NOMBRES</th>
						<th>APELLIDOS</th>
						<th>DEPARTAMENTO</th>
						<th>PROVINCIA</th>
						<th>DISTRITO</th>
						<th>DIRECCION</th>
						<th>CANTIDAD FAMILIAR</th>
						<th>PROFESION</th>
						<th>EMAIL</th>
						<th>SINTOMAS</th>
					</tr>
				</thead>
				<tbody class="text-center">
					<c:forEach var="sint" items="${sintomas }">
						<tr>
						
							<td>${sint.getPaciente().getNomPac() }</td>
							<td>${sint.getPaciente().getApePac() }</td>
							<td>${sint.getDepartamento() }</td>
							<td>${sint.getProvincia() }</td>
							<td>${sint.getDistrito() }</td>
							<td>${sint.getDireccion() }</td>
							<td>${sint.getNumFamiliar() }</td>
							<td>${sint.getProfesion() }</td>
							<td>${sint.getEmail() }</td>														
							<td>
							
								<button  class="btn btn-primary" onclick="editar('${sint.getPaciente().getNomPac()}','${sint.getPriSintoma()}','${sint.getSegSintoma() }','${sint.getTerSintoma() }','${sint.getCuartSintoma() }','${sint.getQuintSintoma() }','${sint.getSextSintoma() }')" data-toggle="modal" data-target="#exampleModal">
		 							 Visualizar
								</button>
								
								
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>	

		
		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		      
		        <h5 class="modal-title text-center" id="exampleModalLabel">SUS SINTOMAS SON : </h5>
		        
                 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		      
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		      
		        	<h6>CIUDADANO :   <input type="text" id="id_nombre" style="border:none;"></h6>
					<h6>--------------------------------------------------------------------</h6>
		      	<form>
		      	   
		        	<div>
						<h6>Primer Sintoma:   <input type="text" id="id_primer" style="border:none;"></h6>
					</div>
					<div>
						<h6>Segundo Sintoma:   <input type="text" id="id_segundo" style="border:none;"></h6>
					</div>
					<div>
						<h6>Tercer Sintoma:   <input type="text" id="id_tercer" style="border:none;"></h6>
					</div>
					<div>
						<h6>Cuarto Sintoma:   <input type="text" id="id_cuarto" style="border:none;"></h6>
					</div>
					<div>
						<h6>Quinto Sintoma:   <input type="text" id="id_quinto" style="border:none;"></h6>
					</div>
					<div>
						<h6>Sexto Sintoma:   <input type="text" id="id_sexto" style="border:none;"></h6>
					</div>
					
				</form>	
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		
		<script type="text/javascript">
			function editar(Nombre,PrimerSintoma,SegundoSintoma,TercerSintoma,CuartoSintoma,QuintoSintoma,SextoSintoma){
				$('input[id=id_nombre]').val(Nombre);
				$('input[id=id_primer]').val(PrimerSintoma);
				$('input[id=id_segundo]').val(SegundoSintoma);
				$('input[id=id_tercer]').val(TercerSintoma);
				$('input[id=id_cuarto]').val(CuartoSintoma);
				$('input[id=id_quinto]').val(QuintoSintoma);
				$('input[id=id_sexto]').val(SextoSintoma);
			}

		</script>


		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	</body>
</html>