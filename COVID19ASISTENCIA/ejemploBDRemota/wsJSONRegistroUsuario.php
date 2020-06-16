<?PHP
$hostname_localhost = "localhost";
$database_localhost = "id13648985_proyectocovid19"; 
$username_localhost = "id13648985_covid19"; 
$password_localhost = "Prado123456789!"; 

$json = array(); 

 if (isset ($_GET["nom_usuario"]) && 
     isset ($_GET["ape_usuario"]) && 
	 isset ($_GET["TipoDoc_usuario"]) && 
     isset ($_GET["doc_usuario"]) && 
     isset ($_GET["tel_usuario"]) && 
	 isset ($_GET["login_usuario"]) &&
     isset ($_GET["pass_usuario"])) { 
        
    $nombre = $_GET['nom_usuario'];   
    $apellido = $_GET['ape_usuario']; 
    $tipoDocumento = $_GET['TipoDoc_usuario']; 	
    $documento = $_GET['doc_usuario'];  
    $telefono = $_GET['tel_usuario'];  
    $usuario = $_GET['login_usuario'];  
    $clave = $_GET['pass_usuario'];  

    $conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);   

    $insert="INSERT INTO tb_usuario(nom_usuario, ape_usuario, TipoDoc_usuario, doc_usuario, tel_usuario, login_usuario, pass_usuario) VALUES('{$nombre}','{$apellido}','{$tipoDocumento}','{$documento}','{$telefono}','{$usuario}','{$clave}')";
    $resultado_insert=mysqli_query($conexion,$insert);

    if($resultado_insert){
     $consulta="SELECT * FROM tb_usuario";
     $resultado=mysqli_query($conexion,$consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['tb_usuario'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
        }
		
			
		
		
        else{
         $resulta["nombre"]="NO registra";
         $resulta["apellido"]="NO registra";
		 $resulta["tipoDocumento"]="NO registra";
         $resulta["documento"]="NO registra";
         $resulta["telefono"]="NO registra";
         $resulta["usuario"]="NO registra";
         $resulta["clave"]="NO registra";
		 
         $json['tb_usuario'][]=$resulta;
           echo json_encode($json);
        }     
    }
        else{
         $resulta["nombre"]="WS NO retorna";
         $resulta["apellido"]="WS NO retorna";
		 $resulta["tipoDocumento"]="WS NO retorna";
         $resulta["documento"]="WS NO retorna";
         $resulta["telefono"]="WS NO retorna";
         $resulta["usuario"]="WS NO retorna";
         $resulta["clave"]="WS NO retorna";
		 
         $json['tb_usuario'][]=$resulta;
         echo json_encode($json);
        }

?>