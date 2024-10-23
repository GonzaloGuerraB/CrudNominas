<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menú Principal</title>
    <link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>
    <h1>Gestión de Empleados</h1>
<div class="button-container">
    <nav>
        <button onclick="window.location.href='empleados'">Mostrar Empleados</button>
        <button onclick="window.location.href='salario'">Mostrar Salario por DNI</button>
        <button onclick="window.location.href='crear'">Crear Nuevo Empleado</button>
        <button onclick="window.location.href='buscarEmpleados'">Filtro Empleados</button> 
    </nav>
    <!-- Botón para volver atrás -->
	<button onclick="goBack()">Volver a la página anterior</button>
	</br>

	<!-- Código JavaScript -->
	<script>
		function goBack() {
			window.history.back(); // Regresa a la página anterior
		}
	</script>
</div>
</body>
</html>
