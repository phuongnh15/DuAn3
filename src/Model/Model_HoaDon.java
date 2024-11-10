/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Model_HoaDon {

    private String maHD, maKH, tenKH, sdt, id_NV, ngaythanhtoan, maVoucher;
    private double tongtienBD, tongKM, tongtiensauKM;
    private boolean trangthai;
    private int id;

    public Model_HoaDon() {
    }

    public Model_HoaDon(String maHD, String maKH, String tenKH, String sdt, String id_NV, String ngaythanhtoan, String maVoucher, double tongtienBD, double tongKM, double tongtiensauKM, boolean trangthai) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.id_NV = id_NV;
        this.ngaythanhtoan = ngaythanhtoan;
        this.maVoucher = maVoucher;
        this.tongtienBD = tongtienBD;
        this.tongKM = tongKM;
        this.tongtiensauKM = tongtiensauKM;
        this.trangthai = trangthai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Model_HoaDon(String maHD, String id_NV, String ngaythanhtoan, boolean trangthai, int id) {
        this.maHD = maHD;
        this.id_NV = id_NV;
        this.ngaythanhtoan = ngaythanhtoan;
        this.trangthai = trangthai;
        this.id = id;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getId_NV() {
        return id_NV;
    }

    public void setId_NV(String id_NV) {
        this.id_NV = id_NV;
    }

    public String getNgaythanhtoan() {
        return ngaythanhtoan;
    }

    public void setNgaythanhtoan(String ngaythanhtoan) {
        this.ngaythanhtoan = ngaythanhtoan;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public double getTongtienBD() {
        return tongtienBD;
    }

    public void setTongtienBD(double tongtienBD) {
        this.tongtienBD = tongtienBD;
    }

    public double getTongKM() {
        return tongKM;
    }

    public void setTongKM(double tongKM) {
        this.tongKM = tongKM;
    }

    public double getTongtiensauKM() {
        return tongtiensauKM;
    }

    public void setTongtiensauKM(double tongtiensauKM) {
        this.tongtiensauKM = tongtiensauKM;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }

    public Object[] ToDataRow() {
        return new Object[]{this.maHD, this.maKH, this.tenKH, this.sdt, this.id_NV, this.ngaythanhtoan, this.tongtienBD, this.tongKM, this.maVoucher, this.tongtiensauKM, this.trangthai ? "Đã thanh toán" : "Chưa thanh toán"};
    }

    public Object[] toDataHoaDonCho() {
        return new Object[]{id,maHD,ngaythanhtoan,id_NV,trangthai?"Đã Thanh Toán":"Chờ Thanh Toan"};
    }

}
