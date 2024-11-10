/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.Model_SanPham;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class reponsitory_SanPham {

    private Connection con = null;
    private PreparedStatement pr = null;
    private ResultSet rs = null;
    private String sql = null;

    public ArrayList<Model_SanPham> gettAll_Sp() {
        
        ArrayList<Model_SanPham> ds = new ArrayList<Model_SanPham>();
        sql = "	select masp,tenSP  from SanPham";
        try {
            con = DBConnect.DBConnect_Cong.getConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            int id = 1;
            while (rs.next()) {
                Model_SanPham sp = new Model_SanPham(rs.getString(1), rs.getString(2), id);
                id++;
                ds.add(sp);
            }
            return ds;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public ArrayList<Model_SanPham> gettAll_SpChiTiet() {
        ArrayList<Model_SanPham> ds = new ArrayList<>();
        sql = "SELECT masp, tenSP, soluongtonkho, gia, cpu, gpu, ram, mausac, dungLuong FROM SanPham";

        try {
            con = DBConnect.DBConnect_Cong.getConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();

            while (rs.next()) {

                Model_SanPham sp = new Model_SanPham(
                        rs.getString("masp"),
                        rs.getString("tenSP"),
                        rs.getInt("soluongtonkho"),
                        rs.getDouble("gia"),
                        rs.getString("cpu"),
                        rs.getString("gpu"),
                        rs.getString("ram"),
                        rs.getString("mausac"),
                        rs.getString("dungLuong")
                );
                ds.add(sp);
            }
            return ds;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pr != null) {
                    pr.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
}
