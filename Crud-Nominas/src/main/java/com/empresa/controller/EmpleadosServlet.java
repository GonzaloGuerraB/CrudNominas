package com.empresa.controller;

import com.empresa.dao.EmpleadoDAO;
import com.empresa.model.Empleado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/empleados")
public class EmpleadosServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Maneja las solicitudes GET para obtener la lista de empleados.
     * Llama al DAO para recuperar todos los empleados y sus sueldos,
     * y luego redirige a la vista JSP correspondiente.
     *
     * @param request  la solicitud HTTP que se procesa.
     * @param response la respuesta HTTP que se enviará al cliente.
     * @throws ServletException si ocurre un error durante el manejo de la solicitud.
     * @throws IOException      si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        List<Empleado> empleados = empleadoDAO.obtenerTodosLosEmpleados();

        // Crear un mapa para asociar el sueldo a cada empleado
        Map<String, Double> sueldos = new HashMap<>();
        for (Empleado empleado : empleados) {
            // Aquí puedes hacer otra consulta para obtener el sueldo según el DNI
            double sueldo = empleadoDAO.obtenerSueldoPorDni(empleado.getDni());
            sueldos.put(empleado.getDni(), sueldo);
        }

        request.setAttribute("empleados", empleados);
        request.setAttribute("sueldos", sueldos);
        request.getRequestDispatcher("views/empleados.jsp").forward(request, response);
    }
}
