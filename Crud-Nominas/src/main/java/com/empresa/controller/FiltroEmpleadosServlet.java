package com.empresa.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empresa.dao.EmpleadoDAO;
import com.empresa.model.Empleado;

@WebServlet("/buscarEmpleados")
public class FiltroEmpleadosServlet extends HttpServlet {

    /**
     * Maneja las solicitudes GET para buscar empleados.
     * Obtiene los parámetros de búsqueda del formulario y
     * llama al método correspondiente en el DAO para obtener
     * la lista de empleados que cumplen con los criterios.
     *
     * @param request  la solicitud HTTP que contiene los parámetros de búsqueda.
     * @param response la respuesta HTTP que se enviará al cliente.
     * @throws ServletException si ocurre un error durante el manejo de la solicitud.
     * @throws IOException      si ocurre un error de entrada/salida.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener parámetros del formulario
        String nombre = request.getParameter("nombre");
        String dni = request.getParameter("dni");
        String sexo = request.getParameter("sexo");
        String categoriaStr = request.getParameter("categoria");
        String anosTrabajadosStr = request.getParameter("anosTrabajados");

        // Convertir los parámetros numéricos
        Integer categoria = (categoriaStr != null && !categoriaStr.isEmpty()) ? Integer.parseInt(categoriaStr) : null;
        Integer anosTrabajados = (anosTrabajadosStr != null && !anosTrabajadosStr.isEmpty())
                ? Integer.parseInt(anosTrabajadosStr)
                : null;

        // Llamar al método de búsqueda de empleados
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        List<Empleado> empleados = empleadoDAO.buscarEmpleados(nombre, dni, sexo, categoria, anosTrabajados);

        // Pasar la lista de empleados al JSP
        request.setAttribute("empleados", empleados);

        // Redirigir al JSP
        request.getRequestDispatcher("views/buscarEmpleados.jsp").forward(request, response);
    }
}
