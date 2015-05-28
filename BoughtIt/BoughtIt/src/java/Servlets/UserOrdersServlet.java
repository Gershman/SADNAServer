/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Logic.ItemData;
import Logic.OrderData;
import Utils.MySQLUtils;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tal
 */
@WebServlet(name = "UserOrdersServlet", urlPatterns = {"/UserOrders"})
public class UserOrdersServlet extends HttpServlet {

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
        
        String password = request.getParameter("password");
        String userName = request.getParameter("email");
        
        userName="mail.center.test@gmail.com";
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try
            {
                boolean found= false;
                Gson gson = new Gson();
                MySQLUtils mySQL = new MySQLUtils();
                Connection conn = mySQL.connectToMySql();
                Statement stmtOrders =  conn.createStatement();
                ResultSet rsOrders = stmtOrders.executeQuery("SELECT * FROM " + MySQLUtils.ordersDataTableName + " where userName='"+userName+"'");
                OrderData newOrder = null;
                while(rsOrders.next())
                {
                    found = true;
                    newOrder = new OrderData();
                    insertDataToOrderFomSQL(newOrder,rsOrders);
                    String orderID = rsOrders.getString("orderID");
                    Statement stmtItems =  conn.createStatement();
                    ResultSet rsItems = stmtItems.executeQuery("SELECT * FROM " + MySQLUtils.itemsDataTableName + " where orderID='"+orderID+"'");
                    while(rsItems.next())
                    {
                        ItemData newItem = new ItemData();
                        insertDataToItemFromSQL(newItem,rsItems);
                        newOrder.getItems().add(newItem);
                    }
                }
                if(found == false)
                {
                    gson.toJson(null, out);
                }
                else
                {
                    gson.toJson(newOrder, out);
                }
            }
            catch (Exception e)
            {
                 out.println("server is Down");
            }
            
            out.flush();
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

    private void insertDataToItemFromSQL(ItemData newItem, ResultSet rsItems) throws SQLException {
        
        Date date = rsItems.getDate("ETA");
        String imageURL = rsItems.getString("imageURL");
        String ItemIDWebSite = rsItems.getString("ItemIDWebSite");
        double itemPrice = rsItems.getDouble("itemPrice");
        String name = rsItems.getString("name");
        int quantity = rsItems.getInt("quantity");
        double shippingCost = rsItems.getDouble("shippingCost");
        double totalPrice = rsItems.getDouble("totalPrice");
        
        newItem.setETA(date);
        newItem.setImageURL(imageURL);
        newItem.setItemIDWebSite(ItemIDWebSite);
        newItem.setItemPrice(itemPrice);
        newItem.setName(name);
        newItem.setQuantity(quantity);
        newItem.setShippingCost(shippingCost);
        newItem.setTotalPrice(totalPrice);
    }

    private void insertDataToOrderFomSQL(OrderData newOrder, ResultSet rsOrders) throws SQLException {
        
        String address = rsOrders.getString("address");
        String currency = rsOrders.getString("currency");
        Date dateOrderRecivedToParse= rsOrders.getDate("dateOrderRecivedToParse");
        Date orderDate = rsOrders.getDate("orderDate");
        double shippingCost = rsOrders.getDouble("shippingCost");
        String shoppingWebSite = rsOrders.getString("shoppingWebSite");
        double totalPrice = rsOrders.getDouble("totalPrice");
        String userName = rsOrders.getString("userName");
        
        newOrder.setAddress(address);
        newOrder.setCurrency(currency);
        newOrder.setDateOrderReceivedToParse(dateOrderRecivedToParse);
        newOrder.setOrderDate(orderDate);
        newOrder.setShippingCost(shippingCost);
        newOrder.setShoppingWebSite(shoppingWebSite);
        newOrder.setTotalPrice(totalPrice);
        newOrder.setUserName(userName);
    }

}
