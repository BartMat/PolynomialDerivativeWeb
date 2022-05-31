/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.polynomialderivativeweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "History", urlPatterns = {"/History"})
public class History extends HttpServlet {

    private ArrayList<String> origin;
    private ArrayList<String> result;
    private ArrayList<String> date;

    public History() {
        origin = new ArrayList<>();
        result = new ArrayList<>();
        date = new ArrayList<>();
    }

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
            Cookie[] cookies = request.getCookies();
            String originString = "", resultString = "", dateString = "";

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("origin")) {
                        originString = cookie.getValue();
                    }
                    if (cookie.getName().equals("result")) {
                        resultString = cookie.getValue();
                    }
                    if (cookie.getName().equals("date")) {
                        dateString = cookie.getValue();
                    }
                }
            }
            final String dateFinal = dateString;
            if (date.stream().filter(f -> !f.equals(dateFinal)).count() == 0) {

                origin.add(originString);
                result.add(resultString);
                date.add(dateString);
            }
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>History</title>");
            out.println("</head>");
            out.println("<style>");
            out.println("table, th, td {");
            out.println("border:1px solid black;");
            out.println("}");
            out.println("</style>");
            out.println("<body>");
            out.println("<h2>Calculated derivatives</h2>");
            out.println("<table style=\"width:100%\">");
            out.println("<tr>");
            out.println("<th>Date</th>");
            out.println("<th>Polynomial</th>");
            out.println("<th>Derivative</th>");
            out.println("</tr>");
            for (int i = 0; i < date.size(); i++) {
                out.println("<tr>");
                out.println("<td>" + date.get(i) + "</td>");
                out.println("<td>" + origin.get(i) + "</td>");
                out.println("<td>" + result.get(i) + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
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
