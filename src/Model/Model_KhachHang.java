/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Model_KhachHang {
    private String maKH;
    private String tenKH;
    private String SDT;
    private String email;
    private String diaChi;
    private boolean gioiTinh;

    public Model_KhachHang() {
    }

    public Model_KhachHang(String maKH, String tenKH, String SDT, String email, String diaChi, boolean gioiTinh) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.SDT = SDT;
        this.email = email;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
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

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
}
