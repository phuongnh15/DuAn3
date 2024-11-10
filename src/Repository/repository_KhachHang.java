/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DBConnect.DBConnect_Cong;
import Model.Model_KhachHang;
import java.sql.*;
import java.util.ArrayList;

/**
 * /**
 *
 * @author Admin
 */
public class repository_KhachHang {

    private Connection conn = null;
    PreparedStatement pr = null;
    ResultSet rs = null;
    String query = null;

    public ArrayList<Model_KhachHang> getData() {
        ArrayList<Model_KhachHang> lst = new ArrayList<>();

        try {
            conn = DBConnect_Cong.getConnection();
             query = "select makhachhang, ten, sodienthoai, email, diachi, gioiTinh   from KhachHang";
            pr = conn.prepareStatement(query);
            rs = pr.executeQuery();
            while (rs.next()) {
                Model_KhachHang kh = new Model_KhachHang();
                kh.setMaKH(rs.getString(1));
                kh.setTenKH(rs.getString(2));
                kh.setSDT(rs.getString(3));
                kh.setEmail(rs.getString(4));
                kh.setDiaChi(rs.getString(5));
                kh.setGioiTinh(rs.getBoolean(6));

                lst.add(kh);
            }
            return lst;
        }
         catch (Exception e){
             return null;
        }
    }
    
}
   

    
    

    
