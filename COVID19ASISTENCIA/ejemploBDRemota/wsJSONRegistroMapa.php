<?PHP
$hostname_localhost = "localhost";
$database_localhost = "mapas_covid19"; 
$username_localhost = "root"; 
$password_localhost = ""; 

$json = array(); 

 if (isset ($_GET["latitud_mapa"]) && 
	 isset ($_GET["longitud_mapa"]) &&
     isset ($_GET["direccion_mapa"])) { 
        
    $latitud = $_GET['latitud_mapa'];   
    $longitud = $_GET['longitud_mapa'];   
    $direccion = $_GET['direccion_mapa'];  
 

    $conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);   

    $insert="INSERT INTO tb_mapa(latitud_mapa, longitud_mapa, direccion_mapa) VALUES('{$latitud}','{$longitud}','{$direccion}')";
    $resultado_insert=mysqli_query($conexion,$insert);

    if($resultado_insert){
        $consulta="";
        $resultado=mysqli_query($conexion,$consulta);

        if($registro=mysqli_fetch_array($resultado)){
            $json['tb_mapa'][]=$registro;
           }
           mysqli_close($conexion);
           echo json_encode($json);
        }
        else{
         $resulta["latitud_mapa"]="NO registra";
         $resulta["longitud_mapa"]="NO registra";
         $resulta["direccion_mapa"]="NO registra";

         $json['tb_mapa'][]=$resulta;
           echo json_encode($json);
        }     
    }
        else{
         $resulta["latitud_mapa"]="WS NO retorna";
         $resulta["longitud_mapa"]="WS NO retorna";
         $resulta["direccion_mapa"]="WS NO retorna";
		 
         $json['tb_mapa'][]=$resulta;
         echo json_encode($json);
        }

?>