/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import test.ImeiDialog;

public class Model_Imei_Sp {
      private String Iemi;
      private String maSP;
      private boolean trangThai;

    public Model_Imei_Sp() {
    }

    public Model_Imei_Sp(String Iemi, String maSP, boolean trangThai) {
        this.Iemi = Iemi;
        this.maSP = maSP;
        this.trangThai = trangThai;
    }

    public String getIemi() {
        return Iemi;
    }

    public void setIemi(String Iemi) {
        this.Iemi = Iemi;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
     
    public Object toDataRow(){
        return new Object[]{
          maSP,Iemi,trangThai?"Chưa bán":"Đã Bán "  
        };
    }
}
