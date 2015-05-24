/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Tal
 */
public class MySQLUtils {
    
    private final String ADDRESS = "52.28.70.218"; // 52.17.209.130
    private final String DATA_BASE = "mailCenter";

       
    public Connection connectToMySql()
    {
        String driver = "com.mysql.jdbc.Driver";
        Connection conn = null;
        try
        {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://" + ADDRESS+"/"+DATA_BASE,"root","yourpasswordhere");
        }
        catch(Exception e)
        {

        }
        
        return conn;
    }
}
