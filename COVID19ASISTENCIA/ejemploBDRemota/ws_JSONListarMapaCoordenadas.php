<?php 
$hostname_localhost ="localhost";
$database_localhost ="proyecto_covid19";
$username_localhost ="root";
$password_localhost ="";
	
	$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
	if (mysqli_connect_errno()) {
		echo "Failed to connect MySQL: " . mysqli_connect_error();
	}

	$query = mysqli_query($conexion, "SELECT cod_sintomas,Resultado,Latitud,Longitud FROM tb_sintomas");
	
	$json = '{"wisata": [';

	
	// create looping dech array in fetch
	while ($row = mysqli_fetch_array($query)){

	// quotation marks (") are not allowed by the json string, we will replace it with the` character
	// strip_tag serves to remove html tags on strings
		$char ='"';

		$json .= 
		'{
			"Id":"'.str_replace($char,'`',strip_tags($row['cod_sintomas'])).'",
			"Resultado":"'.str_replace($char,'`',strip_tags($row['Resultado'])).'",
			"Latitud":"'.str_replace($char,'`',strip_tags($row['Latitud'])).'",
			"Longitud":"'.str_replace($char,'`',strip_tags($row['Longitud'])).'"
		},';
	}

	// omitted commas at the end of the array
	$json = substr($json,0,strlen($json)-1);

	$json .= ']}';

	// print json
	echo $json;
	
	mysqli_close($conexion);
	
?>