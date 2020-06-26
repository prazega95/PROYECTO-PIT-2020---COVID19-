<?PHP
$hostname_localhost = "localhost";
$database_localhost = "proyecto_covid19"; 
$username_localhost = "root"; 
$password_localhost = ""; 

$json = array(); 

 if (isset ($_GET["Departamento"]) && 
     isset ($_GET["Provincia"]) && 
	 isset ($_GET["Distrito"]) &&
	 isset ($_GET["Direccion"]) &&
	 isset ($_GET["Latitud"]) &&
	 isset ($_GET["Longitud"]) &&
	 isset ($_GET["NumeroFamiliar"]) &&
	 isset ($_GET["Profesion"]) &&
	 isset ($_GET["PrimerSintoma"]) &&
	 isset ($_GET["SegundoSintoma"]) &&
     isset ($_GET["TercerSintoma"]) &&
	 isset ($_GET["CuartoSintoma"]) &&
	 isset ($_GET["QuintoSintoma"]) &&
	 isset ($_GET["SextoSintoma"]) &&
	 isset ($_GET["Ninguna"]) &&	
     isset ($_GET["Email"]) &&
	 isset ($_GET["Fecha"]) &&
     isset ($_GET["Condicion"]) &&			 
     isset ($_GET["cod_usuario"])) { 
        
    $sintomas1 = $_GET['Departamento'];   
    $sintomas2 = $_GET['Provincia'];   
    $sintomas3 = $_GET['Distrito'];  
	$sintomas4 = $_GET['Direccion']; 
    $sintomas5 = $_GET['Latitud']; 
	$sintomas6 = $_GET['Longitud']; 
    $sintomas7 = $_GET['NumeroFamiliar'];
	$sintomas8 = $_GET['Profesion']; 
	$sintomas9 = $_GET['PrimerSintoma']; 
	$sintomas10 = $_GET['SegundoSintoma']; 
	$sintomas11 = $_GET['TercerSintoma']; 
	$sintomas12 = $_GET['CuartoSintoma']; 
	$sintomas13 = $_GET['QuintoSintoma']; 
	$sintomas14 = $_GET['SextoSintoma'];
    $sintomas15 = $_GET['Ninguna']; 
    $sintomas16 = $_GET['Email']; 	
	$sintomas17 = $_GET['Fecha']; 	
	$sintomas18 = $_GET['Condicion']; 
	$sintomas19 = $_GET['cod_usuario'];
 

    $conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);   

    $insert="INSERT INTO tb_sintomas(Departamento, 
	                                 Provincia, 
									 Distrito, 
									 Direccion,
									 Latitud,
									 Longitud,
									 NumeroFamiliar, 
									 Profesion,
									 PrimerSintoma,
									 SegundoSintoma,
									 TercerSintoma,
									 CuartoSintoma,
									 QuintoSintoma,
									 SextoSintoma,
									 Ninguna,
									 Email,
									 Fecha,
									 Condicion,
									 cod_usuario) VALUES('{$sintomas1}',
									                        '{$sintomas2}',
														    '{$sintomas3}',
														    '{$sintomas4}',
															'{$sintomas5}',
															'{$sintomas6}',
															'{$sintomas7}',
															'{$sintomas8}',
															'{$sintomas9}',
															'{$sintomas10}',
															'{$sintomas11}',
															'{$sintomas12}',
															'{$sintomas13}',
															'{$sintomas14}',
															'{$sintomas15}',
															'{$sintomas16}',
															'{$sintomas17}',
															'{$sintomas18}',
														    '{$sintomas19}')";
    $resultado_insert=mysqli_query($conexion,$insert);

    if($resultado_insert){
       
        $resultado=mysqli_query($conexion,$consulta);

        if($registro=mysqli_fetch_array($resultado)){
            $json['tb_sintomas'][]=$registro;
           }
           mysqli_close($conexion);
           echo json_encode($json);
        }
        else{
         $resulta["sintomas1"]="NO registra";
         $resulta["sintomas2"]="NO registra";
         $resulta["sintomas3"]="NO registra";
         $resulta["sintomas4"]="NO registra";
		 $resulta["sintomas5"]="NO registra";
		 $resulta["sintomas6"]="NO registra";
		 $resulta["sintomas7"]="NO registra";
		 $resulta["sintomas8"]="NO registra";
		 $resulta["sintomas9"]="NO registra";
		 $resulta["sintomas10"]="NO registra";
		 $resulta["sintomas11"]="NO registra";
		 $resulta["sintomas12"]="NO registra";
		 $resulta["sintomas13"]="NO registra";
		 $resulta["sintomas14"]="NO registra";
		 $resulta["sintomas15"]="NO registra";
		 $resulta["sintomas16"]="NO registra";
		 $resulta["sintomas17"]="NO registra";
	     $resulta["sintomas18"]="NO registra";
		 $resulta["sintomas19"]="NO registra";
		   
         $json['tb_sintomas'][]=$resulta;
           echo json_encode($json);
        }     
    }
        else{
         $resulta["sintomas1"]="WS NO retorna";
         $resulta["sintomas2"]="WS NO retorna";
         $resulta["sintomas3"]="WS NO retorna";
         $resulta["sintomas4"]="WS NO retorna";
		 $resulta["sintomas5"]="WS NO retorna";
		 $resulta["sintomas6"]="WS NO retorna";
		 $resulta["sintomas7"]="WS NO retorna";
		 $resulta["sintomas8"]="WS NO retorna";
		 $resulta["sintomas9"]="WS NO retorna";
		 $resulta["sintomas10"]="WS NO retorna";
		 $resulta["sintomas11"]="WS NO retorna";
		 $resulta["sintomas12"]="WS NO retorna";
		 $resulta["sintomas13"]="WS NO retorna";
		 $resulta["sintomas14"]="WS NO retorna";
		 $resulta["sintomas15"]="WS NO retorna";
		 $resulta["sintomas16"]="WS NO retorna";
		 $resulta["sintomas17"]="WS NO retorna";
         $resulta["sintomas18"]="WS NO retorna";
		 $resulta["sintomas19"]="WS NO retorna";
			 
         $json['tb_sintomas'][]=$resulta;
         echo json_encode($json);
        }
		
		
		mysqli_close($conexion);
		
		
		

?>