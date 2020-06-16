<?php
$hostname_localhost ="localhost";
$database_localhost ="id13648985_proyectocovid19";
$username_localhost ="id13648985_covid19";
$password_localhost ="Prado123456789!";


$json=array();
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
        $consulta="SELECT * FROM tb_noticias";	
		$resultado=mysqli_query($conexion,$consulta);
		
		while($registro=mysqli_fetch_array($resultado)){
			$json['noticias'][]=$registro;
			
		}
        mysqli_close($conexion);
		echo json_encode($json);
		

?>