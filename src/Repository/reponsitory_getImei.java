/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class reponsitory_getImei {
    private Connection con = null;
    private PreparedStatement pr = null;
    private ResultSet rs = null;
    private String sql = null;
    
    public ArrayList<String> getAllImei(String maSP){
        ArrayList<String> ds =new ArrayList<>();
        try {
            
            sql="select imei from Imei where masp = ?";
            con=DBConnect.DBConnect_Cong.getConnection();
            pr=con.prepareStatement(sql);
            pr.setObject(1, maSP);
            rs=pr.executeQuery();
            while (rs.next()) {                
                String maImei=rs.getString(1);
                ds.add(maImei);
            }
            return ds;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
}
