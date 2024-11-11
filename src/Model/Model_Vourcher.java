/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author OS
 */
public class Model_Vourcher {
    private String ma;
    private String moTa;
    private Double giamGia;
    private Double giamToiDa ;
    private Integer hinhThuc;
    private String ngayBD;
    private String ngayKT;
    private String dieuKien;

    public Model_Vourcher() {
    }

    public Model_Vourcher(String ma, String moTa, Double giamGia, Double giamToiDa, Integer hinhThuc, String ngayBD, String ngayKT, String dieuKien) {
        this.ma = ma;
        this.moTa = moTa;
        this.giamGia = giamGia;
        this.giamToiDa = giamToiDa;
        this.hinhThuc = hinhThuc;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.dieuKien = dieuKien;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Double getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(Double giamGia) {
        this.giamGia = giamGia;
    }

    public Double getGiamToiDa() {
        return giamToiDa;
    }

    public void setGiamToiDa(Double giamToiDa) {
        this.giamToiDa = giamToiDa;
    }

    public Integer getHinhThuc() {
        return hinhThuc;
    }

    public void setHinhThuc(Integer hinhThuc) {
        this.hinhThuc = hinhThuc;
    }

    public String getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(String ngayBD) {
        this.ngayBD = ngayBD;
    }

    public String getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(String ngayKT) {
        this.ngayKT = ngayKT;
    }

    public String getDieuKien() {
        return dieuKien;
    }

    public void setDieuKien(String dieuKien) {
        this.dieuKien = dieuKien;
    }

    public Model_Vourcher(String ma, String moTa, Double giamGia, Double giamToiDa, String ngayBD, String ngayKT) {
        this.ma = ma;
        this.moTa = moTa;
        this.giamGia = giamGia;
        this.giamToiDa = giamToiDa;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
    }
    
    public Object[] toDatarow(){
        return new Object[]{getMa(),getMoTa(),getGiamToiDa(),getNgayBD(),getNgayKT(),getGiamGia()};
    }
}
