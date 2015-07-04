/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Utils.MySQLUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nir
 */
@WebServlet(name = "UpdateOrderDetailsServlet", urlPatterns = {"/UpdateOrderDetails"})
public class UpdateOrderDetailsServlet extends HttpServlet {

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
        
        String orderID = request.getParameter("orderID");
        
        String shoppingWebsite = request.getParameter("shoppingWebSite");
        String arrived = request.getParameter("arrived");
        String totalPrice = request.getParameter("totalPrice");

        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
           try
            {
                MySQLUtils mySQL = new MySQLUtils();
                Connection conn = mySQL.connectToMySql();
                Statement stmt =  conn.createStatement();
                ResultSet itemDetails = stmt.executeQuery("SELECT * FROM " + MySQLUtils.ordersDataTableName + " where orderID = '" + orderID + "'");
                while(itemDetails.next())
                {
                    PreparedStatement preparedStmt = null;
                    
                    String arrivedQuery = "update " + MySQLUtils.ordersDataTableName + " SET arrived = ? where orderID = ?";
                    preparedStmt = conn.prepareStatement(arrivedQuery);
                    preparedStmt.setBoolean(1, Boolean.parseBoolean(arrived));
                    preparedStmt.setString(2, orderID);
                    
                    preparedStmt.executeUpdate();
                    
                    String shoppingWebsiteQuery = "update " + MySQLUtils.ordersDataTableName + " SET shoppingWebSite = ? where orderID = ?";
                    preparedStmt = conn.prepareStatement(shoppingWebsiteQuery);
                    preparedStmt.setString(1, shoppingWebsite);
                    preparedStmt.setString(2, orderID);
                    
                    preparedStmt.executeUpdate();
                    
                    String totalPriceQuery = "update " + MySQLUtils.ordersDataTableName + " SET totalPrice = ? where orderID = ?";
                    preparedStmt = conn.prepareStatement(totalPriceQuery);
                    preparedStmt.setDouble(1, Double.parseDouble(totalPrice));
                    preparedStmt.setString(2, orderID);
                    
                    preparedStmt.executeUpdate();
                    preparedStmt.close();
                }
                
                stmt.close();
                conn.close();
                
                out.println("Success");
            }
            catch (Exception e)
            {
                out.println("server is Down");
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
