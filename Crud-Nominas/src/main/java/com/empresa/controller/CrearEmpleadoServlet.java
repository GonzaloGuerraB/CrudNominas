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

@WebServlet("/crear")
public class CrearEmpleadoServlet extends HttpServlet {
    private EmpleadoDAO empleadoDAO;

    /**
     * Inicializa el servlet y crea una instancia de EmpleadoDAO.
     */
    @Override
    public void init() {
        empleadoDAO = new EmpleadoDAO();
    }

    /**
     * Maneja las solicitudes GET para mostrar el formulario de creación de empleados.
     *
     * @param request  la solicitud HTTP que se procesa.
     * @param response la respuesta HTTP que se enviará al cliente.
     * @throws ServletException si ocurre un error durante el manejo de la solicitud.
     * @throws IOException      si ocurre un error de entrada/salida.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Muestra el formulario para crear empleado
        request.getRequestDispatcher("views/crear.jsp").forward(request, response);
    }

    /**
     * Maneja las solicitudes POST para crear un nuevo empleado.
     * Recupera los parámetros del formulario, crea una instancia de Empleado,
     * y llama al DAO para almacenar el empleado en la base de datos.
     * Luego, carga la lista de empleados y redirige a la vista de empleados.
     *
     * @param request  la solicitud HTTP que se procesa.
     * @param response la respuesta HTTP que se enviará al cliente.
     * @throws ServletException si ocurre un error durante el manejo de la solicitud.
     * @throws IOException      si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String dni = request.getParameter("dni");
        String sexo = request.getParameter("sexo");
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        int anosTrabajados = Integer.parseInt(request.getParameter("anosTrabajados"));

        Empleado empleado = new Empleado(nombre, dni, sexo, categoria, anosTrabajados);
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        boolean creado = empleadoDAO.crearEmpleado(empleado);

        if (creado) {
            // Establece un mensaje de éxito
            request.setAttribute("success", "Empleado creado exitosamente.");
        } else {
            // Establece un mensaje de error
            request.setAttribute("error", "Error al crear el empleado. Inténtalo de nuevo.");
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
