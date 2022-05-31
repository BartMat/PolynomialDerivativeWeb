/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.polynomialderivativeweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author matus
 */
@WebServlet(name = "GetFactors", urlPatterns = {"/GetFactors"})
public class GetFactors extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            String degreeString = request.getParameter("degree");
            if (degreeString.length() == 0) {
                response.sendError(response.SC_BAD_REQUEST, "You should give an positive integer!");
            } else {
                try {
                    int degree = Integer.parseInt(degreeString);
                    if (degree < 0) {
                        response.sendError(response.SC_BAD_REQUEST, "You should give an positive integer!");
                    }
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Factors input</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<form action=\"Calculate\" method=\"POST\">");
                    for (int i = degree; i >= 0; i--) {
                        out.println("<p>x^" + i + ":<input type=text name=factor" + i + "></p>");
                    }
                    out.println("<input type=submit value=OK />");
                    out.println("</form>");
                    out.println("</form>");
                    out.println("</body>");
                    out.println("</html>");
  
                    Cookie cookie = new Cookie("degree", request.getParameter("degree"));
                    response.addCookie(cookie);
                } catch (NumberFormatException e) {
                    response.sendError(response.SC_BAD_REQUEST, "You should give an positive integer!");
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
