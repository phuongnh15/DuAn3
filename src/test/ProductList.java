/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductList extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public ProductList() {
        setTitle("Danh sách sản phẩm");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo dữ liệu mẫu cho bảng sản phẩm
        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Giá"};
        Object[][] data = {
            {"SP001", "Laptop Dell Inspiron", 15000000},
            {"SP002", "Laptop Dell XPS", 30000000},
            {"SP003", "Laptop Dell Vostro", 20000000}
        };

        // Tạo bảng
        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Gắn sự kiện nhấp chuột vào bảng
        table.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        int selectedRow = table.getSelectedRow(); // Lấy dòng được chọn
        System.out.println("Dòng được chọn: " + selectedRow); // Debug dòng được chọn

       
            if (selectedRow != -1) {
                String productId = table.getValueAt(selectedRow, 0).toString();
                System.out.println("Mã sản phẩm được chọn: " + productId); // Debug mã sản phẩm
                openImeiDialog(productId);
            }
        
    }
});
    }

    private void openImeiDialog(String productId) {
        System.out.println("Đang mở IMEI dialog cho sản phẩm: " + productId);
        ImeiDialog imeiDialog = new ImeiDialog(this, productId);
        imeiDialog.setVisible(true); // Hiển thị dialog
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductList().setVisible(true));
    }
}
