/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.polynomialderivativeweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.olynomialderivativeweb.database.DataBase;
import pl.polsl.polynomialderivativeweb.exceptions.NotEnoughDataInArray;
import pl.polsl.polynomialderivativeweb.model.Polynomial;

/**
 *
 * @author matus
 */
@WebServlet(name = "Calculate", urlPatterns = {"/Calculate"})
public class Calculate extends HttpServlet {
    DataBase DB = new DataBase();
    private Polynomial polynomial;

    public Calculate() {
        polynomial = new Polynomial(0);
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
            throws ServletException, IOException, NotEnoughDataInArray {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

             Cookie[] cookies = request.getCookies();
             int degree=0;           
             for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("degree")) {
                        degree = Integer.parseInt(cookie.getValue());
                        break;
                    }
                }
             
            polynomial.setDegree(degree);
            ArrayList<Double> factors = new ArrayList<>();
            boolean allParameters = true;
            for (int i = degree; i >= 0; i--) {
                if (request.getParameter("factor" + i).length() == 0) {
                    allParameters = false;
                }
            }
            if (allParameters) {
                try {
                    for (int i = degree; i >= 0; i--) {
                        factors.add(Double.parseDouble(request.getParameter("factor" + i)));
                    }
                    polynomial.setFactors(factors);
                    Polynomial derivedPolynomial = polynomial.derivative();
                    String origin = polynomial.toString();
                    String result = derivedPolynomial.toString();
               
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title> Result </title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>" + result + "</h1>");
                    out.println("<form action=\"History\" method=\"Post\">");
                    out.println("<input type=submit value=History />");
                    out.println("</form>");
                    out.println("<hr>");
                    out.println("<form action=\"Degree\" method=\"Get\">");
                    out.println("<input type=submit value=\"New polynomial\" />");
                    out.println("</form>");
                    out.println("</body>");
                    out.println("</html>");
                    
                    DB.createTables();
                    int keyID = DataBase.generateUniqueId();
                    DB.insertDataPolynomials(keyID, origin);
                    DB.insertDataDerivatives(keyID, result);
                    
                    Cookie cookieOrigin = new Cookie("origin", origin);
                    response.addCookie(cookieOrigin);
                    Cookie cookieResult = new Cookie("result", result);
                    response.addCookie(cookieResult);
                    Cookie cookieDate = new Cookie("date", new java.util.Date().toString());
                    response.addCookie(cookieDate);
                    
                } catch (NumberFormatException e) {
                    response.sendError(response.SC_BAD_REQUEST, "All parameters should be numbers!");
                }
            } else {
                response.sendError(response.SC_BAD_REQUEST, "You should give all parameters!");
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
        try {
            processRequest(request, response);
        } catch (NotEnoughDataInArray ex) {
            Logger.getLogger(Calculate.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (NotEnoughDataInArray ex) {
            Logger.getLogger(Calculate.class.getName()).log(Level.SEVERE, null, ex);
        }
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
