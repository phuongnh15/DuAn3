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
                Integer hinhThuc = rs.getInt(5);
                String ngayBD = rs.getString(6);
                String ngayKT = rs.getString(7);
                String dieuKien = rs.getString(8);
                Model_Vourcher model = new Model_Vourcher(ma, moTa, giamGia, giamToiDa, ngayBD, ngayKT);
                list.add(model);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
//    
//    public ArrayList<Model.Model_HoaDon> loc(boolean trangthai){
//        ArrayList<Model.Model_HoaDon> list_HD = new ArrayList<>();
//        sql = "select mahoadon, kh.makhachhang, kh.ten, kh.sodienthoai, id_nhanvien, ngaythanhtoan, tongtienBanDau, tongkhuyenmai, maVoucher, tongtienSauKM, trangthai from HoaDon hd\n" +
//"join KhachHang kh on kh.makhachhang = hd.makhachhang where trangthai = ?";
//        try {
//            con = DBConnect.DBConnect_Phuong.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setObject(1, trangthai);
//            rs = ps.executeQuery();
//            while(rs.next()) {
//                String maHD = rs.getString(1);
//                String maKH = rs.getString(2);
//                String tenKH = rs.getString(3);
//                String sdt = rs.getString(4);
//                String id_NV = rs.getString(5);
//                String ngaythanhtoan = rs.getString(6);
//                double tongtienBD = rs.getDouble(7);
//                double tongKM = rs.getDouble(8);
//                String maVoucher = rs.getString(9);
//                double tongtiensauKM = rs.getDouble(10);
//                boolean tt = rs.getBoolean(11);
//                Model.Model_HoaDon hd = new Model_HoaDon(maHD, maKH, tenKH, sdt, id_NV, ngaythanhtoan, maVoucher, tongtienBD, tongKM, tongtiensauKM, tt);
//                list_HD.add(hd);
//            }
//            return list_HD;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    
//    public int xoa(String ma_xoa, boolean tt) {
//        try {
//            sql = "delete from HoaDonChiTiet where mahoadon = ?\n" +
//"delete from HoaDon where mahoadon = ?";
//            con = DBConnect.DBConnect_Phuong.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setObject(1, ma_xoa);
//            ps.setObject(2, ma_xoa);
//            return ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//}

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
