<?PHP
$hostname_localhost ="localhost";
$database_localhost ="id13648985_proyectocovid19";
$username_localhost ="id13648985_covid19";
$password_localhost ="Prado123456789!";

$json=array();

	if(isset($_GET["doc_usuario"])){
		$documento=$_GET["doc_usuario"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select cod_usuario,nom_usuario from tb_usuario where doc_usuario= '{$documento}'";
		$resultado=mysqli_query($conexion,$consulta);
			
		if($registro=mysqli_fetch_array($resultado)){
			$json['usuario'][]=$registro;
		
		}else{
			$resultar["cod_usuario"]=0;
			$resultar["nom_usuario"]='no registra';
			$json['tb_usuario'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['tb_usuario'][]=$resultar;
		echo json_encode($json);
	}
	
	
?>