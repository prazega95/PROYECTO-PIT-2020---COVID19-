<?php
$hostname_localhost = "localhost";
$database_localhost = "id13648985_proyectocovid19"; 
$username_localhost = "id13648985_covid19"; 
$password_localhost = "Prado123456789!"; 

$conexion=new mysqli($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
if($conexion->connect_errno){
    echo "El sitio web está experimentado problemas";
}
?>