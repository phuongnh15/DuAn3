/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DBConnect.DBConnect_Cong;
import Model.Model_KhachHang;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public int save(Model_KhachHang kh) throws SQLException {
        query = "insert KhachHang values(?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, kh.getMaKH());
        ps.setString(2, kh.getTenKH());
        ps.setString(3, kh.getSDT());
        ps.setString(4, kh.getEmail());
        ps.setString(5, kh.getDiaChi());
        ps.setInt(6, kh.isGioiTinh() ? 1 : 0);

        return ps.executeUpdate();
    }

    public boolean update(Model_KhachHang kh, String ma) {
        int check = 0;
        query = "update KhachHang set ten = ?, sodienthoai = ?, email = ?, diachi = ?, gioiTinh = ? where makhachhang = ?";
        try {
            pr = conn.prepareStatement(query);
            pr.setString(1, kh.getTenKH());
            pr.setString(2, kh.getSDT());
            pr.setString(3, kh.getEmail());
            pr.setString(4, kh.getDiaChi());
            pr.setInt(5, kh.isGioiTinh() ? 1 : 0);
            pr.setString(6, kh.getMaKH());
            check = pr.executeUpdate();
            if (check > 0) {
                JOptionPane.showMessageDialog(null, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
//            Logger.getLogger(repository_KhachHang.class.getName()).log(Level.SEVERE, null, ex);
            if (check <= 0) {
                JOptionPane.showMessageDialog(null, "Sửa không thành công: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        return check > 0;

    }

    public ArrayList<Model_KhachHang> tim(String sdt) throws SQLException {
        ArrayList<Model_KhachHang> lst_ad = new ArrayList<>();
        String sql = "select makhachhang, ten, sodienthoai, email, diachi, gioiTinh\n"
                + "from KhachHang where sodienthoai = ?;";
        conn = DBConnect_Cong.getConnection();
        pr = conn.prepareStatement(sql);
        pr.setObject(1, sdt);
        rs = pr.executeQuery();
        while (rs.next()) {
            String maKH = rs.getString(1);
            String ten = rs.getString(2);
            String sdtc = rs.getString(3);

            String email = rs.getString(4);
            String diaChi = rs.getString(5);
            boolean gt = rs.getBoolean(6);

            Model_KhachHang kh = new Model_KhachHang(maKH, ten, sdtc, email, diaChi, gt);
            lst_ad.add(kh);
        }
        return lst_ad;
    }

    public double tong(String maKH) throws SQLException {
        String sql = "select SUM(hd.tongtienSauKM)\n"
                + "from KhachHang kh join HoaDon hd on kh.makhachhang = hd.makhachhang\n"
                + "where kh.makhachhang = ?\n"
                + "group by  kh.makhachhang";

        conn = DBConnect_Cong.getConnection();
        pr = conn.prepareStatement(sql);
        pr.setString(1, maKH);
        rs = pr.executeQuery();
        double tt = 0.0;
        try {
            if (rs.next()) {
                tt = rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(repository_KhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tt;
    }

    //   public boolean delete(String ma) {
//        int check = 0;
//        String sql = "Delete from KhachHang where makhachhang =?";
//        try (Connection con = DBConnect.DBConnect_Cong.getConnection()) {
//            PreparedStatement ps = con.prepareCall(sql);
//            ps.setObject(1, ma);
//            check = ps.executeUpdate();
//            if (check > 0) {
//                JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//            }
//        } catch (Exception e) {
//            if(check<=0) {
//                JOptionPane.showMessageDialog(null, "Xóa không thành công: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//        return check > 0;
//
//    }
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
                if (rs != null) {
                    rs.close();
                }
                if (pr != null) {
                    pr.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

        return thongTinKH; // Trả về mảng kết quả
    }

    public String[] getMaKh_TenKh(String maHoaDon) {
        String[] thongTinKH = null;
        query = "SELECT HoaDon.makhachhang, ten "
                + "FROM HoaDon "
                + "JOIN KhachHang ON HoaDon.makhachhang = KhachHang.makhachhang "
                + "WHERE mahoadon = ?";
        conn = DBConnect_Cong.getConnection();

        try {
            pr = conn.prepareStatement(query);
            pr.setObject(1, maHoaDon);

            rs = pr.executeQuery();
            if (rs.next()) {
                thongTinKH = new String[2];
                thongTinKH[0] = rs.getString("makhachhang");
                thongTinKH[1] = rs.getString("ten");
            }
        } catch (SQLException e) {
            thongTinKH = null;
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            // Đóng kết nối sau khi truy vấn xong
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pr != null) {
                    pr.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

        return thongTinKH;
    }

}
