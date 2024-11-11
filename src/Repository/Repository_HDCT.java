/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.Model_HDCT;
import Model.Model_HoaDon;
import Model.Model_SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class Repository_HDCT {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public void ThemGioHang(Model_SanPham sp) {
        try {
            sql = "INSERT INTO GioHangTamThoi(soLuong,dongia,mahoadon,imei,masanpham)\n"
                    + "VALUES (?,?,?,?,?)";

            con = DBConnect.DBConnect_Cong.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, sp.getSoLuongTonKho());
            ps.setObject(2, sp.getGia());
            ps.setObject(4, sp.getTenSP());
            ps.setObject(3, sp.getCpu());
            ps.setObject(5, sp.getMaSP());
            ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public ArrayList<Model_SanPham> getAllGioHangTamThoi(String maHD) {
        ArrayList<Model_SanPham> list_HD = new ArrayList<>();
        sql = "select magioHangTamthoi, masanpham, dongia, soluong, imei from GioHangTamThoi where mahoadon = ?";

        try {
            double thanhTien=0;
            con = DBConnect.DBConnect_Phuong.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, maHD); // Đảm bảo truyền tham số vào câu lệnh SQL
            rs = ps.executeQuery();

            while (rs.next()) {
                // Lấy các giá trị từ kết quả truy vấn
                String id = rs.getString(1);
                String maSanPham = rs.getString(2);
                double donGia = rs.getDouble(3);
                int soLuong = rs.getInt(4);
                String cpu = rs.getString(5);
                thanhTien+=donGia;

                Model_SanPham sp = new Model_SanPham(maSanPham, soLuong, donGia, cpu, soLuong);

                // Thêm đối tượng hoaDon vào danh sách
                list_HD.add(sp);
            }

            // Trả về danh sách các hóa đơn
            return list_HD;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Model.Model_HDCT> getAll(){
        ArrayList<Model.Model_HDCT> list_HDCT = new ArrayList<>();
        sql = "select mahoadonct, mahoadon, masanpham, imei, dongia, soluong from HoaDonChiTiet";
        try {
            con = DBConnect.DBConnect_Phuong.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                int maHDCT = rs.getInt(1);
                String maHD = rs.getString(2);
                String maSP = rs.getString(3);
                String imei = rs.getString(4);
                double dongia = rs.getDouble(5);
                int solg = rs.getInt(6);
                Model.Model_HDCT hdct = new Model_HDCT(maHDCT, maHD, maSP, imei, solg, dongia);
                list_HDCT.add(hdct);
            }
            return list_HDCT;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Model.Model_HDCT> timkiemHDCT(String maHDcantim){
        ArrayList<Model.Model_HDCT> list_HDCT = new ArrayList<>();
        sql = "select mahoadonct, mahoadon, masanpham, imei, dongia, soluong from HoaDonChiTiet where mahoadon = ?";
        try {
            con = DBConnect.DBConnect_Phuong.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maHDcantim);
            rs = ps.executeQuery();
            while(rs.next()) {
                int maHDCT = rs.getInt(1);
                String maHD = rs.getString(2);
                String maSP = rs.getString(3);
                String imei = rs.getString(4);
                double dongia = rs.getDouble(5);
                int solg = rs.getInt(6);
                Model.Model_HDCT hdct = new Model_HDCT(maHDCT, maHD, maSP, imei, solg, dongia);
                list_HDCT.add(hdct);
            }
            return list_HDCT;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
