<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Buscar Empleado</title>
    <link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>
    <h1>Buscar Empleado</h1>

    <form action="buscar" method="post">
        <label for="busqueda">Buscar por DNI o Nombre:</label>
        <input type="text" id="busqueda" name="busqueda" required>
        <button type="submit">Buscar</button>
    </form>

    <!-- Mostrar mensaje de error si existe -->
    <c:if test="${not empty mensaje}">
        <p style="color:red;">${mensaje}</p>
    </c:if>

    <!-- Mostrar los datos del empleado si fue encontrado -->
    <c:if test="${not empty empleado}">
        <table border="1">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>DNI</th>
                    <th>Sexo</th>
                    <th>Categoría</th>
                    <th>Años Trabajados</th>
                    <th>Sueldo</th> <!-- Columna para el sueldo -->
                    <th>Modificar</th>
					<th>Eliminar</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${empleado.nombre}</td>
                    <td>${empleado.dni}</td>
                    <td>${empleado.sexo}</td>
                    <td>${empleado.categoria}</td>
                    <td>${empleado.anosTrabajados}</td>
                    <td>${nomina != null ? nomina : 'No disponible'}</td> <!-- Muestra el sueldo desde el atributo 'nomina' -->
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
            </tbody>
        </table>
    </c:if>
	</br>
    <!-- Enlace para volver al menú principal -->
    <a href="index.jsp">Volver al Menú Principal</a>
</body>
</html>