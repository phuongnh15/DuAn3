/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VoucherDialog extends JDialog {
    
    // Constructor nhận vào maVoucher
    public VoucherDialog(JFrame parent, String maVoucher) {
        super(parent, "Voucher Tốt Nhất", true); // true để cửa sổ là modal
        setLayout(new BorderLayout());
        
        // Tạo thông điệp và label hiển thị thông tin voucher
        JLabel messageLabel = new JLabel("Voucher có mức giảm giá cao nhất: " + maVoucher, JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        // Thêm label vào dialog
        add(messageLabel, BorderLayout.CENTER);

        // Tạo một button để đóng dialog
        JButton closeButton = new JButton("Đóng");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Đóng dialog khi nhấn nút
            }
        });
        
        // Thêm nút vào dialog
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Cài đặt kích thước dialog và hiển thị
        setSize(400, 200);
        setLocationRelativeTo(parent); // Hiển thị ở giữa màn hình chính
    }
}

