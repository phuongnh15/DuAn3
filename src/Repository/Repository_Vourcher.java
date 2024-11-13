/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.sql.*;
import DBConnect.DBConnect_Cong;
import java.util.ArrayList;
import Model.Model_Vourcher;
import javax.swing.JOptionPane;

/**
 *
 * @author OS
 */
public class Repository_Vourcher {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public ArrayList<Model_Vourcher> getAll() {
        ArrayList<Model_Vourcher> list = new ArrayList<>();
        sql = "select * from Voucher";
        try {
            con = DBConnect_Cong.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString(1);
                String moTa = rs.getString(2);
                Double giamGia = rs.getDouble(3);
                Double giamToiDa = rs.getDouble(4);
                int mucgiamgia = rs.getInt(8);
                String ngayBD = rs.getString(5);
                String ngayKT = rs.getString(6);
                String dieuKien = rs.getString(7);
//                Model_Vourcher model = new Model_Vourcher(ma, moTa, giamGia, giamToiDa, ngayBD, ngayKT);
                Model_Vourcher model = new Model_Vourcher(ma, moTa, giamGia, giamToiDa, ngayBD, ngayKT, mucgiamgia);
                list.add(model);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int them(Model_Vourcher model) {
        try {
            sql = "insert into Voucher(maVoucher,moTa,giamGiaToiDa,ngayBD,ngayKT,giamgia,phantramgiamgia) values(?,?,?,?,?,?,?)";
            con = DBConnect_Cong.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, model.getMa());
            ps.setObject(2, model.getMoTa());
            ps.setObject(3, model.getGiamToiDa());
            ps.setObject(4, model.getNgayBD());
            ps.setObject(5, model.getNgayKT());
            ps.setObject(6, model.getGiamGia());
            ps.setObject(7, model.getMucGiamGia());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int sua(Model_Vourcher model) {
        try {
            sql = "update Voucher set moTa= ?,giamGiaToiDa= ?,ngayBD= ?,ngayKT= ?,giamGia= ?,phantramgiamgia=? where maVoucher=?";
            con = DBConnect_Cong.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, model.getMoTa());
            ps.setObject(2, model.getGiamToiDa());
            ps.setObject(3, model.getNgayBD());
            ps.setObject(4, model.getNgayKT());
            ps.setObject(5, model.getGiamGia());
            ps.setObject(7, model.getMa());
            ps.setObject(6, model.getMucGiamGia());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList<String> cboMaVoucher_FormBH(double tongTien) {
        ArrayList<String> list = new ArrayList<>();
        sql = "select *  from Voucher where giamGia < ? ";
        try {
            con = DBConnect_Cong.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, tongTien);

            rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString(1);
                list.add(ma);
            }
            return list;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public String voucher_ToTNhat(double tongTien) {
        String voucherTotNhat = null;
        double giamLonNhat = 0;
        sql = "select maVoucher,phantramgiamgia,giamGiaToiDa  from Voucher where giamGia < ? ";

        try {
            con = DBConnect_Cong.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, tongTien);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maVoucher = rs.getString("maVoucher");
                double phanTramGiamGia = rs.getDouble("phantramgiamgia");
                double giamGiaToiDa = rs.getDouble("giamGiaToiDa");

                // Tính mức giảm giá từ phần trăm
                double giamGia = tongTien * phanTramGiamGia / 100;

                // Kiểm tra xem mức giảm giá có vượt quá mức tối đa không, nếu có thì gán lại giá trị tối đa
                if (giamGia > giamGiaToiDa) {
                    giamGia = giamGiaToiDa;
                }

                // Cập nhật voucher có mức giảm giá cao nhất
                if (giamGia > giamLonNhat) {
                    giamLonNhat = giamGia;
                    voucherTotNhat = maVoucher;
                }
            }
            return voucherTotNhat;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public double tinhTienGiamGia(double tongTien, String maVoucher) {
        double giamGiaTotNhat = 0;
        sql = "SELECT phantramgiamgia, giamGiaToiDa FROM Voucher WHERE maVoucher = ?";

        try {
            con = DBConnect_Cong.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, maVoucher);
            rs = ps.executeQuery();

            if (rs.next()) {
                double phanTramGiamGia = rs.getDouble("phantramgiamgia");
                double giamGiaToiDa = rs.getDouble("giamGiaToiDa");

                double giamGia = tongTien * phanTramGiamGia / 100;

                // Kiểm tra nếu mức giảm giá vượt quá mức tối đa
                if (giamGia > giamGiaToiDa) {
                    giamGia = giamGiaToiDa; // Giảm giá không được vượt quá mức tối đa
                }

                // Cập nhật giá trị giảm giá cao nhất
                giamGiaTotNhat = giamGia;
            }

            return giamGiaTotNhat;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return 0;
        }
    }

}
