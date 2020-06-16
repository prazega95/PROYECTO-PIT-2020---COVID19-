<?php
$hostname_localhost ="localhost";
$database_localhost ="id13648985_proyectocovid19";
$username_localhost ="id13648985_covid19";
$password_localhost ="Prado123456789!";


$json=array();

		if(isset($_GET["Departamento"])){
		$departamento=$_GET["Departamento"];

		
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		
		$consultaConfirmadas="SELECT COUNT(*) cantidad FROM tb_sintomas where Resultado='Confirmado' AND Departamento='{$departamento}'";
		$resultadoConfirmadas=mysqli_query($conexion,$consultaConfirmadas);

		$consultaHospitalizado="SELECT COUNT(*) cantidad FROM tb_sintomas where Resultado='Hospitalizado' AND Departamento='{$departamento}'";
		$resultadoHospitalizado=mysqli_query($conexion,$consultaHospitalizado);	
		
		$consultaMuertes="SELECT COUNT(*) cantidad FROM tb_sintomas where Resultado='Fallecido' AND Departamento='{$departamento}'";
		$resultadoMuertes=mysqli_query($conexion,$consultaMuertes);	
			
		$consultaRecuperado="SELECT COUNT(*) cantidad FROM tb_sintomas where Resultado='Recuperado' AND Departamento='{$departamento}'";
		$resultadoRecuperado=mysqli_query($conexion,$consultaRecuperado);		

			
		if($registro=mysqli_fetch_array($resultadoConfirmadas)){
			$json['TotalConfirmadas'][]=$registro;
			
		}if($registro=mysqli_fetch_array($resultadoHospitalizado)){
			$json['TotalHospitalizado'][]=$registro;
			
        }if($registro=mysqli_fetch_array($resultadoMuertes)){
			$json['TotalMuertes'][]=$registro;

        }if($registro=mysqli_fetch_array($resultadoRecuperado)){
		    $json['TotalRecuperado'][]=$registro;			
			
	    }else{
			$resultar["cantidad"]=0;
			$json['tb_sintomas'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['tb_sintomas'][]=$resultar;
		echo json_encode($json);
	}

?>