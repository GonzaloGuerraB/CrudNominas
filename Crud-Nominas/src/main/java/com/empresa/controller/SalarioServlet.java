package com.empresa.controller;

import com.empresa.dao.EmpleadoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/salario")
public class SalarioServlet extends HttpServlet {
    private EmpleadoDAO empleadoDAO;

    /**
     * Inicializa el servlet, creando una instancia de {@link EmpleadoDAO}.
     */
    @Override
    public void init() {
        empleadoDAO = new EmpleadoDAO();
    }

    /**
     * Maneja las solicitudes GET para el servlet de salario.
     * Redirige a la vista JSP que muestra el formulario de salario.
     *
     * @param request  la solicitud HTTP.
     * @param response la respuesta HTTP.
     * @throws ServletException si ocurre un error durante el manejo de la solicitud.
     * @throws IOException      si ocurre un error de entrada/salida.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirigir a la vista JSP para mostrar el formulario de salario
        request.getRequestDispatcher("views/salario.jsp").forward(request, response);
    }

    /**
     * Maneja las solicitudes POST para el servlet de salario.
     * Busca el salario de un empleado basado en su DNI proporcionado y redirige a la vista JSP.
     *
     * @param request  la solicitud HTTP.
     * @param response la respuesta HTTP.
     * @throws ServletException si ocurre un error durante el manejo de la solicitud.
     * @throws IOException      si ocurre un error de entrada/salida.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dni = request.getParameter("dni");

        // Buscar el salario del empleado
        Double sueldo = empleadoDAO.obtenerSueldoPorDni(dni);

        // Verificar si el empleado existe
        if (sueldo == null) {
            // Si no se encuentra el empleado, enviar mensaje de error
            request.setAttribute("error", "El empleado con DNI " + dni + " no existe.");
        } else {
            // Si se encuentra, enviar mensaje de éxito con el sueldo
            request.setAttribute("dni", dni);
            request.setAttribute("sueldo", sueldo);
        }

        // Redirigir de nuevo al formulario con el resultado
        request.getRequestDispatcher("/views/salario.jsp").forward(request, response);
    }
}
