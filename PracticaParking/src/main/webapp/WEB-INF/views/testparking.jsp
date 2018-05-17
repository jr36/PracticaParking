<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js">
	</script>
</head>
<body>

<form action="#" id="enviar">
   <h2> Entrada al Parking: </h2>
   Parking Id: <input type="text" name="parkingId" id="parkingId"> 
   Matricula: <input type="text" name="matricula" id="matricula">
   <br><br>
   <input type="submit" value="Envia Datos"> 
</form>
<script type="text/javascript">
	$('#enviar').submit(function(evento){
		var urlrest = 'registroMatricula';
		$.ajax({
			url: urlrest,
			type: 'POST',
			contentType: "application/json",
		    data: JSON.stringify({parkingId:$('#parkingId').val(),matricula:$('#matricula').val()}),
		    success: function(){alert('Los datos se han recibido correctamente');},
		    error: function(){alert('Se ha producido un error en el envio de datos');}
		});
		evento.preventDefault(); //Evita que se ejecute la petición GET del form
	})
</script>

</body>
</html>
