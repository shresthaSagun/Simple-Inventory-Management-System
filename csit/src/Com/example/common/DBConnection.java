/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.example.common;
import  java.sql.*;

/**
 *
 * @author aayushsharma
 */
public class DBConnection {
    public static Connection getConnection() {
        try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/csit5a","root","");
        return con;
        } catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
    
    
}
