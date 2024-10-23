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

@WebServlet("/eliminarEmpleado")
public class EliminarEmpleadoServlet extends HttpServlet {

    /**
     * Maneja las solicitudes POST para eliminar un empleado.
     * Recupera el DNI del empleado a eliminar y llama al DAO para
     * realizar la operación de eliminación. Luego, actualiza la
     * lista de empleados y redirige a la vista correspondiente.
     *
     * @param request  la solicitud HTTP que se procesa.
     * @param response la respuesta HTTP que se enviará al cliente.
     * @throws ServletException si ocurre un error durante el manejo de la solicitud.
     * @throws IOException      si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el parámetro DNI del formulario
        String dni = request.getParameter("dni");

        // Verifica si el DNI está presente y no es nulo
        if (dni == null || dni.isEmpty()) {
            request.setAttribute("error", "DNI no proporcionado.");
            request.getRequestDispatcher("eliminarEmpleado.jsp").forward(request, response);
            return; // Termina la ejecución si hay un error
        }

        // Llama al DAO para eliminar el empleado
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        boolean eliminado = empleadoDAO.eliminarEmpleado(dni);

        // Verifica si se eliminó el empleado correctamente
        if (eliminado) {
            request.setAttribute("success", "Empleado eliminado exitosamente.");
        } else {
            request.setAttribute("error", "Error al eliminar el empleado. Inténtalo de nuevo.");
        }

        // Cargar la lista actualizada de empleados después de la eliminación
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

        // Redirigir a la página de empleados actualizada
        request.getRequestDispatcher("views/empleados.jsp").forward(request, response);
    }
}
