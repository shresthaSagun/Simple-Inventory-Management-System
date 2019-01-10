/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.example.controller;

import Com.example.common.DBConnection;
import Com.example.model.Product;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author aayushsharma
 */
public class ProductDAO {
    public void insertData (Product ob){
        try{
            Connection con=DBConnection.getConnection();
            String sql="insert into product(name,price,qty)values(?,?,?)";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,ob.getName());
            pst.setDouble(2,ob.getPrice());
            pst.setInt(3,ob.getQty());
            pst.executeUpdate();
            con.close();
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
         public ArrayList<Product> fetchData() {
         ArrayList<Product> list =new ArrayList();
        try{
            Connection con=DBConnection.getConnection();
            String sql="select * from product";
            Statement st=con.createStatement();
            if(con!=null)
            {
                System.out.println("Connection Sucessful");
            }
            else
                System.out.println("Connection failed");
            ResultSet rs=st.executeQuery(sql);
            while (rs.next()) {
                Product ob=new Product();
                ob.setId(rs.getInt("id"));
                ob.setQty(rs.getInt("qty"));
                ob.setName(rs.getString("name"));
                ob.setPrice(rs.getDouble("price"));
                ob.setQty(rs.getInt("qty"));
                list.add(ob);
            }
            con.close();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;

         }
         public int getQty(int id,Connection con){
             int qty=0;
             try{
                 String sql="select qty from product where id=?";
                 PreparedStatement pst=con.prepareStatement(sql);
                 pst.setInt(1,id);
                 ResultSet rs=pst.executeQuery();
                 if(rs.next()){
                        qty=rs.getInt("qty");
                 }
                 
             }
                 catch(Exception ex){
                         System.out.println(ex);
                 }
                return qty;    
         }
         
         public void updateQty(int newqty,int id,Connection con){
             try{
                 String sql="update product set qty=? where id=?";
                 PreparedStatement pst=con.prepareCall(sql);
                 pst.setInt(1,newqty);
                 pst.setInt(2, id);
                 pst.executeUpdate();
             }catch(Exception ex){
                 System.out.println(ex);
             }
         }
         
         
         
         public void UpdateData (Product ob){
        try{
            Connection con=DBConnection.getConnection();
            String sql="update product set name=?,price=?,qty=? where id=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,ob.getName());
            pst.setDouble(2,ob.getPrice());
            pst.setInt(3,ob.getQty());
            pst.setInt(4,ob.getId());
            pst.executeUpdate();
            con.close();
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
         public void DeleteData (Product ob){
        try{
            Connection con=DBConnection.getConnection();
            //String sql="delete from product where id="+ob.getId()+"";
            String sql="delete from product where id=?";
            PreparedStatement pst=con.prepareStatement(sql);
            
            /*pst.setString(1,ob.getName());
            pst.setDouble(2,ob.getPrice());
            pst.setInt(3,ob.getQty());*/
            pst.setInt(1,ob.getId());
            pst.executeUpdate();
            con.close();
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
public ArrayList<Product> fetchDataWithId(int id) {
         ArrayList<Product> list =new ArrayList();
        try{
            Connection con=DBConnection.getConnection();
            String sql="select * from product where id=? and qty>0";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1,id);
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {
                Product ob=new Product();
                ob.setId(rs.getInt("id"));
                ob.setQty(rs.getInt("qty"));
                ob.setName(rs.getString("name"));
                ob.setPrice(rs.getDouble("price"));
                ob.setQty(rs.getInt("qty"));
                list.add(ob);
            }
            con.close();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
}
}
