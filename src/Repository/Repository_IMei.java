/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;


import Model.Model_Imei_Sp;
import java.sql.*;
import java.util.ArrayList;
public class Repository_IMei {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    
    public ArrayList<Model.Model_Imei_Sp> getAll(){
        ArrayList<Model_Imei_Sp> list_HD = new ArrayList<>();
       sql="select * from Imei";
        try {
            con = DBConnect.DBConnect_Cong.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                String maIMei = rs.getString(1);
                boolean maKH = rs.getBoolean(2);
                String maSP = rs.getString(1);
                Model_Imei_Sp sp =new Model_Imei_Sp(maSP, maSP, maKH);
            }
            return list_HD;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
