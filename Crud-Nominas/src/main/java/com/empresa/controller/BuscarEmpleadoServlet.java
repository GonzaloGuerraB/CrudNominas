package com.empresa.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empresa.dao.EmpleadoDAO;
import com.empresa.model.Empleado;
import com.empresa.model.Nomina;

@WebServlet("/buscar")
public class BuscarEmpleadoServlet extends HttpServlet {
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    /**
     * Maneja las solicitudes GET y redirige al formulario de búsqueda de empleados.
     *
     * @param request  la solicitud HTTP que se procesa.
     * @param response la respuesta HTTP que se enviará al cliente.
     * @throws ServletException si ocurre un error durante el manejo de la solicitud.
     * @throws IOException      si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Puedes redirigir a la misma página o mostrar un mensaje
        response.sendRedirect("buscar.jsp");
    }
    
    /**
     * Maneja las solicitudes POST para buscar un empleado según el DNI proporcionado.
     * Si el empleado es encontrado, calcula su sueldo utilizando la clase Nomina.
     * En caso contrario, establece un mensaje indicando que no se encontró al empleado.
     *
     * @param request  la solicitud HTTP que se procesa.
     * @param response la respuesta HTTP que se enviará al cliente.
     * @throws ServletException si ocurre un error durante el manejo de la solicitud.
     * @throws IOException      si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String busqueda = request.getParameter("busqueda");
        Empleado empleado = null;
        String mensaje = null;
        double sueldoCalculado = 0.0;

        if (busqueda != null && !busqueda.trim().isEmpty()) {
            empleado = empleadoDAO.obtenerEmpleadoPorDni(busqueda); // Método para buscar empleado

            if (empleado != null) {
                // Crear una instancia de Nomina y calcular el sueldo en base a los datos del empleado
                Nomina nomina = new Nomina();
                sueldoCalculado = nomina.calculaSueldo(empleado.getCategoria(), empleado.getAnosTrabajados());
            } else {
                mensaje = "Empleado no encontrado.";
            }
        }
        
        // Pasar el empleado y la nómina al JSP
        request.setAttribute("empleado", empleado);
        request.setAttribute("nomina", sueldoCalculado); // Pasar el sueldo calculado
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("buscar.jsp").forward(request, response);
    }
}
