/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Com.example.controller;

import Com.example.common.DBConnection;
import Com.example.common.TrackUser;
import Com.example.model.Login;
import Com.example.model.Product;
import java.sql.*;

/**
 *
 * @author Sushil Shrestha
 */
public class LoginDAO {
    public int validateUser(Login ob){
        int x=0;
        try{
            Connection con=DBConnection.getConnection();
            String sql="select * from login where username=? and password=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,ob.getUsername());
            pst.setString(2,ob.getPassword());
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                x++;
                TrackUser.uid=rs.getInt("id");
                TrackUser.uname=rs.getString("username");
            }
            con.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
        return x;
    }
    
       
}
