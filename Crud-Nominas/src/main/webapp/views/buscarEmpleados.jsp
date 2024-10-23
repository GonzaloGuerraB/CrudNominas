<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Búsqueda de Empleados</title>
<link rel="stylesheet" type="text/css" href="styles/style.css">

</head>
<body>
	<h1>Búsqueda de Empleados</h1>

	<form action="buscarEmpleados" method="get">
		<label for="nombre">Nombre:</label> <input type="text" id="nombre"
			name="nombre" value="${param.nombre}" /> <label for="dni">DNI:</label>
		<input type="text" id="dni" name="dni" value="${param.dni}" /> <label
			for="sexo">Sexo:</label> <select id="sexo" name="sexo">
			<option value="">Seleccionar</option>
			<option value="M" ${param.sexo == 'M' ? 'selected' : ''}>Masculino</option>
			<option value="F" ${param.sexo == 'F' ? 'selected' : ''}>Femenino</option>
		</select> <label for="categoria">Categoría:</label> <input type="number"
			id="categoria" name="categoria" value="${param.categoria}" /> <label
			for="anosTrabajados">Años trabajados:</label> <input type="number"
			id="anosTrabajados" name="anosTrabajados"
			value="${param.anosTrabajados}" />

		<button type="submit">Buscar</button>
	</form>

	</br>
	<button onclick="window.location.href='buscarEmpleados'">Resetear
		filtros</button>
	</br>
	</br>
	<div class="button-container">
		<button onclick="window.location.href='index.jsp'">Volver al
			menu principal</button>
		<!-- Botón para volver atrás -->
		<button onclick="goBack()">Volver a la página anterior</button>
	</div>
	<!-- Mostrar resultados -->
	<h2>Resultados de la Búsqueda</h2>
	<c:if test="${not empty empleados}">
		<table border="1">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>DNI</th>
					<th>Sexo</th>
					<th>Categoría</th>
					<th>Años Trabajados</th>
					<th>Modificar</th>
					<th>Eliminar</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="empleado" items="${empleados}">
					<tr>
						<td>${empleado.nombre}</td>
						<td>${empleado.dni}</td>
						<td>${empleado.sexo}</td>
						<td>${empleado.categoria}</td>
						<td>${empleado.anosTrabajados}</td>
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
	</c:if>

	<c:if test="${empty empleados}">
		<p>No se encontraron empleados.</p>
	</c:if>
	</br>

	<!-- Código JavaScript -->
	<script>
		function goBack() {
			window.history.back(); // Regresa a la página anterior
		}
	</script>
</body>
</html>
