<?php
$hostname='localhost';
$database='proyecto_covid19';
$username='root';
$password='';

$conexion=new mysqli($hostname,$username,$password,$database);
echo 'Connected successfully';
if($conexion->connect_errno){
	
    echo "El sitio web está experimentado problemas";
}
?>