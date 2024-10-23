package com.empresa.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empresa.dao.EmpleadoDAO;
import com.empresa.model.Empleado;

@WebServlet("/modificarEmpleado")
public class ModificarEmpleadoServlet extends HttpServlet {

    /**
     * Maneja las solicitudes GET para cargar el formulario de edición
     * de un empleado. Busca el empleado por su DNI y lo envía a la
     * vista JSP correspondiente.
     *
     * @param request  la solicitud HTTP.
     * @param response la respuesta HTTP.
     * @throws ServletException si ocurre un error durante el manejo de la solicitud.
     * @throws IOException      si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");

        // Obtén el empleado de la base de datos
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        Empleado empleado = empleadoDAO.obtenerEmpleadoPorDni(dni);

        // Verifica que el empleado no sea nulo
        if (empleado != null) {
            // Envía el empleado a la página JSP
            request.setAttribute("empleado", empleado);
            request.getRequestDispatcher("views/editarEmpleado.jsp").forward(request, response);
        } else {
            // Manejo de error si el empleado no se encuentra
            request.setAttribute("error", "Empleado no encontrado.");
            request.getRequestDispatcher("views/empleados.jsp").forward(request, response);
        }
    }

    /**
     * Maneja las solicitudes POST para actualizar los datos de un empleado.
     * Valida los parámetros recibidos, actualiza el empleado en la base de datos
     * y redirige a la lista de empleados con un mensaje de éxito o error.
     *
     * @param request  la solicitud HTTP.
     * @param response la respuesta HTTP.
     * @throws ServletException si ocurre un error durante el manejo de la solicitud.
     * @throws IOException      si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String dni = request.getParameter("dni");
        String sexo = request.getParameter("sexo");
        String categoriaStr = request.getParameter("categoria");
        String anosTrabajadosStr = request.getParameter("anosTrabajados");
        String sueldoStr = request.getParameter("sueldo");

        // Verifica si los parámetros necesarios están presentes y no son nulos
        if (categoriaStr == null || anosTrabajadosStr == null) {
            request.setAttribute("error", "Algunos campos necesarios están vacíos.");
            request.getRequestDispatcher("views/editarEmpleado.jsp").forward(request, response);
            return; // Termina la ejecución si hay un error
        }

        // Parsea los valores a tipos numéricos
        int categoria = Integer.parseInt(categoriaStr);
        int anosTrabajados = Integer.parseInt(anosTrabajadosStr);
        Empleado empleado = new Empleado(nombre, dni, sexo, categoria, anosTrabajados);
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        boolean actualizado = empleadoDAO.modificarEmpleadoConSueldo(empleado);
        
        if (actualizado) {
            request.setAttribute("success", "Empleado actualizado exitosamente.");
        } else {
            request.setAttribute("error", "Error al actualizar el empleado. Inténtalo de nuevo.");
        }

        // Cargar la lista de empleados
        List<Empleado> empleados = empleadoDAO.obtenerTodosLosEmpleados(); // Obtener todos los empleados
        Map<String, Double> sueldos = new HashMap<>(); // Mapa para almacenar sueldos

        for (Empleado emp : empleados) {
            // Obtener el sueldo por DNI
            double sueldoEmpleado = empleadoDAO.obtenerSueldoPorDni(emp.getDni());
            sueldos.put(emp.getDni(), sueldoEmpleado); // Asociar el sueldo con el DNI
        }

        // Establecer los atributos para la solicitud
        request.setAttribute("empleados", empleados);
        request.setAttribute("sueldos", sueldos); // Añadir el mapa de sueldos a la solicitud

        // Redirigir a la página de empleados
        request.getRequestDispatcher("views/empleados.jsp").forward(request, response);
    }
}
