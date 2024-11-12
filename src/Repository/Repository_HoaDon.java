/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.Model_HoaDon;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Repository_HoaDon {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public ArrayList<Model.Model_HoaDon> getAll() {
        ArrayList<Model.Model_HoaDon> list_HD = new ArrayList<>();
        sql = "select mahoadon, kh.makhachhang, kh.ten, kh.sodienthoai, id_nhanvien, ngaythanhtoan, tongtienBanDau, tongkhuyenmai, maVoucher, tongtienSauKM, trangthai from HoaDon hd\n"
                + "full join KhachHang kh on kh.makhachhang = hd.makhachhang";
        try {
            con = DBConnect.DBConnect_Phuong.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maHD = rs.getString(1);
                String maKH = rs.getString(2);
                String tenKH = rs.getString(3);
                String sdt = rs.getString(4);
                String id_NV = rs.getString(5);
                String ngaythanhtoan = rs.getString(6);
                double tongtienBD = rs.getDouble(7);
                double tongKM = rs.getDouble(8);
                String maVoucher = rs.getString(9);
                double tongtiensauKM = rs.getDouble(10);
                boolean tt = rs.getBoolean(11);
                Model.Model_HoaDon hd = new Model_HoaDon(maHD, maKH, tenKH, sdt, id_NV, ngaythanhtoan, maVoucher, tongtienBD, tongKM, tongtiensauKM, tt);
                list_HD.add(hd);
            }
            return list_HD;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Model.Model_HoaDon> loc(boolean trangthai) {
        ArrayList<Model.Model_HoaDon> list_HD = new ArrayList<>();
        sql = "select mahoadon, kh.makhachhang, kh.ten, kh.sodienthoai, id_nhanvien, ngaythanhtoan, tongtienBanDau, tongkhuyenmai, maVoucher, tongtienSauKM, trangthai from HoaDon hd\n"
                + "full join KhachHang kh on kh.makhachhang = hd.makhachhang where trangthai = ?";
        try {
            con = DBConnect.DBConnect_Phuong.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangthai);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maHD = rs.getString(1);
                String maKH = rs.getString(2);
                String tenKH = rs.getString(3);
                String sdt = rs.getString(4);
                String id_NV = rs.getString(5);
                String ngaythanhtoan = rs.getString(6);
                double tongtienBD = rs.getDouble(7);
                double tongKM = rs.getDouble(8);
                String maVoucher = rs.getString(9);
                double tongtiensauKM = rs.getDouble(10);
                boolean tt = rs.getBoolean(11);
                Model.Model_HoaDon hd = new Model_HoaDon(maHD, maKH, tenKH, sdt, id_NV, ngaythanhtoan, maVoucher, tongtienBD, tongKM, tongtiensauKM, tt);
                list_HD.add(hd);
            }
            return list_HD;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int xoa(String ma_xoa, boolean tt) {
        try {
            sql = "delete from HoaDonChiTiet where mahoadon = ?\n"
                    + "delete from HoaDon where mahoadon = ?";
            con = DBConnect.DBConnect_Phuong.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma_xoa);
            ps.setObject(2, ma_xoa);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList<Model.Model_HoaDon> timkiemHD(String macantim) {
        ArrayList<Model.Model_HoaDon> list_HD = new ArrayList<>();
        sql = "select mahoadon, kh.makhachhang, kh.ten, kh.sodienthoai, id_nhanvien, ngaythanhtoan, tongtienBanDau, tongkhuyenmai, maVoucher, tongtienSauKM, trangthai from HoaDon hd\n"
                + "full join KhachHang kh on kh.makhachhang = hd.makhachhang where mahoadon = ?";
        try {
            con = DBConnect.DBConnect_Phuong.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, macantim);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maHD = rs.getString(1);
                String maKH = rs.getString(2);
                String tenKH = rs.getString(3);
                String sdt = rs.getString(4);
                String id_NV = rs.getString(5);
                String ngaythanhtoan = rs.getString(6);
                double tongtienBD = rs.getDouble(7);
                double tongKM = rs.getDouble(8);
                String maVoucher = rs.getString(9);
                double tongtiensauKM = rs.getDouble(10);
                boolean tt = rs.getBoolean(11);
                Model.Model_HoaDon hd = new Model_HoaDon(maHD, maKH, tenKH, sdt, id_NV, ngaythanhtoan, maVoucher, tongtienBD, tongKM, tongtiensauKM, tt);
                list_HD.add(hd);
            }
            return list_HD;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Model_HoaDon> getChoHoaDon(String maHD, String maNV) {
        ArrayList<Model_HoaDon> ds = new ArrayList<>();
        int id = 0;
        String sql = "INSERT INTO HoaDon (mahoadon, id_nhanvien) VALUES (?, ?)";

        try {
//             Thêm hóa đơn mới
            con = DBConnect.DBConnect_Cong.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, maHD);
            ps.setString(2, maNV);
            ps.executeUpdate();

            // Lấy dữ liệu các hóa đơn
            String sql1 = "SELECT mahoadon, ngaythanhtoan, id_nhanvien, trangthai FROM HoaDon where trangthai=0 ";
            ps = con.prepareStatement(sql1);
            rs = ps.executeQuery();

            // Duyệt qua kết quả trả về và thêm vào danh sách
            while (rs.next()) {
                id++;
                String maHD1 = rs.getString("mahoadon");
                String id_NV = rs.getString("id_nhanvien");
                String ngaythanhtoan = rs.getString("ngaythanhtoan");
                boolean trangThai = rs.getBoolean("trangthai");

                // Tạo đối tượng Model_HoaDon và thêm vào danh sách
                Model_HoaDon hd = new Model_HoaDon(maHD1, id_NV, ngaythanhtoan, trangThai, id);
                ds.add(hd);
            }
            return ds;

        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra để debug
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public ArrayList<Model_HoaDon> getAllChoHoaDonTT() {
        ArrayList<Model_HoaDon> ds = new ArrayList<>();
        int id = 0;

        try {
//         
            con = DBConnect.DBConnect_Cong.getConnection();
            // Lấy dữ liệu các hóa đơn
            String sql1 = "SELECT mahoadon, ngaythanhtoan, id_nhanvien, trangthai FROM HoaDon where trangthai=0 ";
            ps = con.prepareStatement(sql1);
            rs = ps.executeQuery();

            // Duyệt qua kết quả trả về và thêm vào danh sách
            while (rs.next()) {
                id++;
                String maHD1 = rs.getString("mahoadon");
                String id_NV = rs.getString("id_nhanvien");
                String ngaythanhtoan = rs.getString("ngaythanhtoan");
                boolean trangThai = rs.getBoolean("trangthai");

                // Tạo đối tượng Model_HoaDon và thêm vào danh sách
                Model_HoaDon hd = new Model_HoaDon(maHD1, id_NV, ngaythanhtoan, trangThai, id);
                ds.add(hd);
            }
            return ds;

        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra để debug
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    //update maKh vao hoa don 
    public void ThemMaKH(String maHD, String maKh) {
        sql = "update HoaDon\n"
                + "set makhachhang =?\n"
                + "where mahoadon=?";
        try {
            con = DBConnect.DBConnect_Cong.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maKh);
            ps.setObject(2, maHD);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void CapNhatHoaDonThanToan(Model_HoaDon hd,String maHD ) {
        sql = "UPDATE HoaDon SET tongtienBanDau = ?, tongkhuyenmai = ?, tongTiensauKM = ?, maVoucher = ? WHERE mahoadon = ?";
        try {
            con = DBConnect.DBConnect_Cong.getConnection();
            ps = con.prepareStatement(sql);

            // Thiết lập các giá trị từ đối tượng Model_HoaDon
            ps.setObject(1, hd.getTongtienBD());
            ps.setObject(2, hd.getTongKM());
            ps.setObject(3, hd.getTongtiensauKM());
            ps.setObject(4, hd.getMaVoucher());
          
            ps.setObject(5, maHD);   
            

            // Thực hiện cập nhật
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Thanh toán thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn cần cập nhật.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
