<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Editar Empleado</title>
<link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>
	<h1>Editar Empleado</h1>

	<c:if test="${not empty error}">
		<div style="color: red;">${error}</div>
	</c:if>

	<c:if test="${not empty success}">
		<div style="color: green;">${success}</div>
	</c:if>

	<form action="modificarEmpleado" method="post">
		<label for="nombre">Nombre:</label> <input type="text" name="nombre"
			value="${empleado.nombre}" required> <label for="dni">DNI:</label>
		<input type="text" name="dni" value="${empleado.dni}" readonly
			required> <label for="sexo">Sexo:</label> <select name="sexo"
			required>
			<option value="M" ${empleado.sexo == 'M' ? 'selected' : ''}>Masculino</option>
			<option value="F" ${empleado.sexo == 'F' ? 'selected' : ''}>Femenino</option>
		</select> <label for="categoria">Categoría:</label> <input type="number"
			name="categoria" value="${empleado.categoria}" required> <label
			for="anosTrabajados">Años Trabajados:</label> <input type="number"
			name="anosTrabajados" value="${empleado.anosTrabajados}" required>

		<button type="submit">Guardar Cambios</button>
	</form>
	</br>
	</br>
	<!-- Botón para volver atrás -->
	<button onclick="goBack()">Volver a la página anterior</button>
	</br>
	<!-- Código JavaScript -->
	<script>
		function goBack() {
			window.history.back(); // Regresa a la página anterior
		}
	</script>
	</br>
	<button onclick="window.location.href='index.jsp'">Volver al
		menu principal</button>
</body>
</html>
