/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Com.example.controller;

import Com.example.common.DBConnection;
import Com.example.model.Sales;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author Sushil Shrestha
 */
public class SalesDAO {
    public void insertData(ArrayList<Sales>list){
        try{
            Connection con=DBConnection.getConnection();
            for(int i=0;i<list.size();i++){
                String sql="insert into sales(pid,sid,price,qty,dos) values(?,?,?,?,?)";
                PreparedStatement pst=con.prepareStatement(sql);
                pst.setInt(1, list.get(i).getPid());
                pst.setInt(2, list.get(i).getSid());
                pst.setDouble(3, list.get(i).getPrice());
                pst.setInt(4, list.get(i).getQty());
                pst.setDate(5, new java.sql.Date(list.get(i).getDos().getTime()));
                pst.executeUpdate();
                //get qty from product table
                ProductDAO productDAO=new ProductDAO();
                int qty=productDAO.getQty(list.get(i).getPid(),con);
                
                //update qty in product table
                int newqty=qty-list.get(i).getQty();
                productDAO.updateQty(newqty,list.get(i).getPid(), con);
                
                
                
            }
            con.close();
        }catch(Exception ex){
            System.out.println(ex);
            
        }
        
    }
    
}
