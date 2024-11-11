/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DBConnect.DBConnect_Cong;
import Model.Model_KhachHang;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
        } catch (Exception e) {
            return null;
        }
    }

    public String[] getThongTinKH(String sdt) {
    String[] thongTinKH = null; // Khởi tạo mảng trả về
    query = "SELECT makhachhang, ten FROM KhachHang WHERE sodienthoai = ?";
    conn = DBConnect_Cong.getConnection();

    try {
        pr = conn.prepareStatement(query);
        pr.setObject(1, sdt);

        rs = pr.executeQuery();
        if (rs.next()) { 
            thongTinKH = new String[2]; 
            thongTinKH[0] = rs.getString("makhachhang"); 
            thongTinKH[1] = rs.getString("ten"); 
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    } finally {
        // Đóng kết nối sau khi truy vấn xong
        try {
            if (rs != null) rs.close();
            if (pr != null) pr.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    return thongTinKH; // Trả về mảng kết quả
}


}
