/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.Model_Imei_Sp;
import Model.Model_SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class reponsitory_getImei {

    private Connection con = null;
    private PreparedStatement pr = null;
    private ResultSet rs = null;
    private String sql = null;

    public ArrayList<String> getAllImei(String maSP) {
        ArrayList<String> ds = new ArrayList<>();
        try {

            sql = "SELECT imei \n"
                    + "FROM Imei i\n"
                    + "WHERE i.masp = ? \n"
                    + "AND NOT EXISTS (\n"
                    + "    SELECT 1 \n"
                    + "    FROM ImeiDaBan idb \n"
                    + "    WHERE idb.maimei = i.imei\n"
                    + ");";
            con = DBConnect.DBConnect_Cong.getConnection();
            pr = con.prepareStatement(sql);
            pr.setObject(1, maSP);
            rs = pr.executeQuery();
            while (rs.next()) {
                String maImei = rs.getString(1);
                ds.add(maImei);
            }
            return ds;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
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

}
