package com.educacionit.sistemaeducativo.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet para manejar el login del sistema.
 * Por ahora usa validación simple, luego se puede integrar con DAO de usuarios.
 * 
 * @author Ludmila Martos
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        
        // Validación simple (TODO: integrar con DAO de usuarios)
        if (validarCredenciales(usuario, password)) {
            // Crear sesión
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            session.setAttribute("autenticado", true);
            
            // Redirigir al dashboard
            response.sendRedirect("dashboard");
        } else {
            // Volver al login con error
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }
    
    private boolean validarCredenciales(String usuario, String password) {
        // Validación simple para demostración
        // TODO: Implementar con DAO y base de datos
        return ("admin".equals(usuario) && "admin123".equals(password)) ||
               ("ludmila".equals(usuario) && "ludmila123".equals(password));
    }
}


