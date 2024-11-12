/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.Model_Imei_Sp;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Repository_IMei {

    private Connection con = null;
    private PreparedStatement ps = null;
    private PreparedStatement pr = null;
    private ResultSet rs = null;
    private String sql = null;

    public ArrayList<Model.Model_Imei_Sp> getAll() {
        ArrayList<Model_Imei_Sp> list_HD = new ArrayList<>();
        sql = "select * from Imei";
        try {
            con = DBConnect.DBConnect_Cong.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maIMei = rs.getString(1);
                boolean maKH = rs.getBoolean(2);
                String maSP = rs.getString(1);
                Model_Imei_Sp sp = new Model_Imei_Sp(maSP, maSP, maKH);
            }
            return list_HD;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void ThemImei_SP(Model_Imei_Sp sp) {
        String sql = "INSERT INTO Imei (imei, trangthai, masp) VALUES (?, ?, ?)";

        try {
            con = DBConnect.DBConnect_Cong.getConnection();
            pr = con.prepareStatement(sql);

            pr.setString(1, sp.getIemi());
            pr.setInt(2, 1);
            pr.setString(3, sp.getMaSP());

            // Thực thi câu lệnh INSERT
            pr.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            // Đảm bảo đóng kết nối
            try {
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

    public void insertImeiList_Excel(List<Model_Imei_Sp> imeiList) {
        String sql = "INSERT INTO Imei (imei,trangthai, masp) VALUES (?,?, ?)";
        try (Connection con = DBConnect.DBConnect_Cong.getConnection(); PreparedStatement pr = con.prepareStatement(sql)) {

            for (Model_Imei_Sp item : imeiList) {
                pr.setString(1, item.getIemi());
                pr.setObject(2, 1);
                pr.setString(3, item.getMaSP());
                pr.addBatch(); // Thêm batch để giảm thời gian thực thi
            }
            pr.executeBatch(); // Thực thi tất cả lệnh một lần
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
