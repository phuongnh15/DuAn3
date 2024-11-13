/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.Model_DoanhThu;
import Model.Model_Imei_Sp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class repository_DoanhThu {

    private Connection con = null;
    private PreparedStatement ps = null;
    private PreparedStatement pr = null;
    private ResultSet rs = null;
    private String sql = null;

    public ArrayList<Model_DoanhThu> getAll(String nam) {
        ArrayList<Model_DoanhThu> ds = new ArrayList<>();
        sql = "SELECT \n"
                + "    FORMAT(ngayThanhToan, 'MM') AS Thang, \n"
                + "    SUM(soLuong) AS TongSoLuong\n"
                + "FROM \n"
                + "    HoaDonChiTiet \n"
                + "JOIN \n"
                + "    HoaDon ON HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon\n"
                + "WHERE \n"
                + "    YEAR(ngayThanhToan) = ?\n"
                + "GROUP BY \n"
                + "    FORMAT(ngayThanhToan, 'MM'), YEAR(ngayThanhToan), MONTH(ngayThanhToan)\n"
                + "ORDER BY \n"
                + "    YEAR(ngayThanhToan), MONTH(ngayThanhToan); ";
        try {
            con = DBConnect.DBConnect_Cong.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, nam);
            rs = ps.executeQuery();
            while (rs.next()) {
                String thang = rs.getString(1);
                int soLuong = rs.getInt(2);
                Model_DoanhThu dt = new Model_DoanhThu(thang, soLuong);
                ds.add(dt);
            }
            return ds;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public ArrayList<Model_DoanhThu> getDataByDay(int year, int month) {
        ArrayList<Model_DoanhThu> ds = new ArrayList<>();
        String sql = "SELECT FORMAT(ngayThanhToan, 'dd') AS Ngay, SUM(soLuong) AS TongSoLuong "
                + "FROM HoaDonChiTiet "
                + "JOIN HoaDon ON HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon "
                + "WHERE YEAR(ngayThanhToan) = ? AND MONTH(ngayThanhToan) = ? "
                + "GROUP BY FORMAT(ngayThanhToan, 'dd') "
                + "ORDER BY FORMAT(ngayThanhToan, 'dd');";

        try (Connection con = DBConnect.DBConnect_Cong.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, year);
            ps.setInt(2, month);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ngay = rs.getString("Ngay");
                int soLuong = rs.getInt("TongSoLuong");
                ds.add(new Model_DoanhThu(ngay, soLuong));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return ds;
    }

    // Phương thức lấy dữ liệu tổng số lượng bán trong cả năm
    public ArrayList<Model_DoanhThu> getSOLuongNam(int year, String tang_Giam) {
        ArrayList<Model_DoanhThu> dataList = new ArrayList<>();
        String sql = "SELECT masanpham, tensp, SUM(soLuong) AS TongSoLuong "
                + "FROM HoaDonChiTiet "
                + "JOIN HoaDon ON HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon "
                + "JOIN sanpham ON sanpham.masp = HoaDonChiTiet.masanpham "
                + "WHERE YEAR(ngayThanhToan) = ? "
                + "GROUP BY masanpham, tensp "
                + "ORDER BY TongSoLuong " + (tang_Giam.equals("Tang") ? "ASC" : "DESC");  // Sắp xếp theo tổng số lượng giảm dần

        try (Connection con = DBConnect.DBConnect_Cong.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, year);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maSanPham = rs.getString("masanpham");
                String tenSanPham = rs.getString("tensp");
                int tongSoLuong = rs.getInt("TongSoLuong");

                Model_DoanhThu data = new Model_DoanhThu(tongSoLuong, maSanPham, tenSanPham);
                dataList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // In lỗi nếu có
        }
        return dataList;
    }

    public ArrayList<Model_DoanhThu> getSOLuongThang(int year, int month,String loc) {
        ArrayList<Model_DoanhThu> dataList = new ArrayList<>();
        String sql = "SELECT masanpham, tensp, SUM(soLuong) AS TongSoLuong "
                + "FROM HoaDonChiTiet "
                + "JOIN HoaDon ON HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon "
                + "JOIN sanpham ON sanpham.masp = HoaDonChiTiet.masanpham "
                + "WHERE YEAR(ngayThanhToan) = ? AND MONTH(ngayThanhToan) = ? "
                + "GROUP BY masanpham, tensp "
                + "ORDER BY TongSoLuong "+(loc.equals("Tang") ? "ASC" : "DESC");  // Sắp xếp giảm dần theo số lượng

        try (Connection con = DBConnect.DBConnect_Cong.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, year);  // Truyền vào năm
            ps.setInt(2, month); // Truyền vào tháng
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maSanPham = rs.getString("masanpham");
                String tenSanPham = rs.getString("tensp");
                int tongSoLuong = rs.getInt("TongSoLuong");

                Model_DoanhThu data = new Model_DoanhThu(tongSoLuong, maSanPham, tenSanPham);
                dataList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // In lỗi nếu có
        }
        return dataList;
    }

}
