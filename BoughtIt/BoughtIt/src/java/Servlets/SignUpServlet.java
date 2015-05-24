/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Logic.User;
import Utils.MySQLUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Tal
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUp"})
public class SignUpServlet extends HttpServlet {
    

    /**
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
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userName = request.getParameter("email");
        String country = request.getParameter("country");
        String birthDay = request.getParameter("birthDay");
        
        java.sql.Date birthDayDate = convetStringToDate(birthDay);
        User newUser = new User(password,firstName,lastName,userName,country,birthDayDate);
        
//        newUser.setPassword("123");
//        newUser.setFirstName("sdfgsdg");
//        newUser.setLastName("dfgdf");
//        newUser.setUserName("tal.gdfg@gmail.com");
//        newUser.setCountry("dfgdfg");
//        
        final String DATA_TABLE = "UserDetails";
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try
            {
                MySQLUtils mySQL = new MySQLUtils();
                Connection conn = mySQL.connectToMySql();
                Statement stmt =  conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT userName FROM " + DATA_TABLE + " where userName='"+newUser.getUserName()+"'");
                if(!rs.next())
                {
                    insertUserDetailsToTable(conn,newUser);
                    response.sendRedirect("main.html");
                }
                else
                {
                    out.println("username is all ready taken");
                    conn.close();
                }
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
    
    private java.sql.Date convetStringToDate(String str)
    {
        java.sql.Date sqlDate = null;
        try
        {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY);
            Date date1 = format.parse(str);
            sqlDate = new java.sql.Date(date1.getTime());
            
        }
        catch(Exception e)
        {
            
        } 
        return sqlDate;
    }

    private void insertUserDetailsToTable(Connection conn, User newUser) throws SQLException, ParseException {
         
        Statement stmt = null;
        String DATA_TABLE = "UserDetails";
        
        stmt = conn.createStatement();
        String query = "insert into " + DATA_TABLE + "(userName,password,firstName,lastName,country,birthday) VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, newUser.getUserName());
        preparedStmt.setString (2, newUser.getPassword());
        preparedStmt.setString (3, newUser.getFirstName());
        preparedStmt.setString (4, newUser.getLastName());
        preparedStmt.setString (5, newUser.getCountry());
        preparedStmt.setDate   (6, newUser.getBirthDay());
        
        preparedStmt.execute();
        conn.close();
    }

}
