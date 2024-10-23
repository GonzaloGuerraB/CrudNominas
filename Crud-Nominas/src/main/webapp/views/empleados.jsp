<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lista de Empleados</title>
<link rel="stylesheet" type="text/css" href="styles/style.css">
<!-- Incluye tu archivo CSS si es necesario -->
</head>
<body>
	<!-- Mostrar mensajes de error o éxito -->
	<c:if test="${not empty error}">
		<div style="color: red;">${error}</div>
	</c:if>

	<c:if test="${not empty success}">
		<div style="color: green;">${success}</div>
	</c:if>
	<h1>Lista de Empleados</h1>

	<!-- Mostrar mensaje si hay alguno -->
	<c:if test="${not empty mensaje}">
		<div>${mensaje}</div>
	</c:if>

	<table border="1">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>DNI</th>
				<th>Sexo</th>
				<th>Categoría</th>
				<th>Años Trabajados</th>
				<th>Sueldo</th>
				<th>Modificar</th>
				<th>Eliminar</th>
			</tr>
		</thead>
		<tbody>
			<!-- Iterar sobre la lista de empleados -->
			<c:forEach var="empleado" items="${empleados}">
				<tr>
					<td>${empleado.nombre}</td>
					<td>${empleado.dni}</td>
					<td>${empleado.sexo}</td>
					<td>${empleado.categoria}</td>
					<td>${empleado.anosTrabajados}</td>
					<td>${sueldos[empleado.dni] != null ? sueldos[empleado.dni] : 'No disponible'}
					</td>
					<td>
						<!-- Botón de "Editar" que redirige al servlet con el DNI del empleado -->
						<form action="modificarEmpleado" method="get">
							<input type="hidden" name="dni" value="${empleado.dni}">
							<input type="submit" value="Editar">
						</form>
					</td>
					<td>
						<!-- Botón de "Eliminar" que redirige al servlet con el DNI del empleado -->
						<form action="eliminarEmpleado" method="post">
							<input type="hidden" name="dni" value="${empleado.dni}">
							<input type="submit" value="Eliminar">
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- Formulario para agregar un nuevo empleado -->

	<h2>Agregar Empleado</h2>
	<form action="crear" method="post">
		<input type="hidden" name="redirectPage" value="crear.jsp" /> <label
			for="nombre">Nombre:</label> <input type="text" id="nombre"
			name="nombre" required> <label for="dni">DNI:</label> <input
			type="text" id="dni" name="dni" required> <label for="sexo">Sexo:</label>
		<select id="sexo" name="sexo" required>
			<option value="M">Masculino</option>
			<option value="F">Femenino</option>
		</select> <label for="categoria">Categoría:</label> <input type="text"
			id="categoria" name="categoria" required> <label
			for="anosTrabajados">Años Trabajados:</label> <input type="number"
			id="anosTrabajados" name="anosTrabajados" required>

		<button type="submit">Crear Empleado</button>
	</form>
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
