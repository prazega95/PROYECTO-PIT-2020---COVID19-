<?php
include 'conexionCovid.php';
$login_usuario=$_POST['usuario'];
$pass_usuario=$_POST['clave'];

//$login_usuario="erick";
//$pass_usuario="123456";

$sentencia=$conexion->prepare("SELECT * FROM tb_usuario WHERE login_usuario=? AND pass_usuario=?");
$sentencia->bind_param('ss',$login_usuario,$pass_usuario);
$sentencia->execute();

$resultado = $sentencia->get_result();
if ($fila = $resultado->fetch_assoc()) {
         echo json_encode($fila,JSON_UNESCAPED_UNICODE);     
}
$sentencia->close();
$conexion->close();
?>