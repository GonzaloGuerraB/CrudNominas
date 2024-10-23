<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Salario de Empleado</title>
    <link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>
<!-- Mostrar mensajes de error o éxito -->
	<c:if test="${not empty error}">
		<div style="color: red;">${error}</div>
	</c:if>

	<c:if test="${not empty success}">
		<div style="color: green;">${success}</div>
	</c:if>
    <h1>Consultar Salario de Empleado</h1>

    <form action="salario" method="post">
        <label for="dni">DNI del Empleado:</label>
        <input type="text" id="dni" name="dni" required>
        <button type="submit">Consultar</button>
    </form>

    <c:if test="${not empty sueldo}">
        <h2>Salario del empleado con DNI ${dni}: ${sueldo} Euros.</h2>
    </c:if>

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
	<button onclick="window.location.href='index.jsp'">Volver al menu principal</button>
</body>
</html>