package test;

import Repository.reponsitory_getImei;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class ImeiDialog extends JDialog {
    private JList<String> imeiJList;
    private JButton btnSelect;
    public  String selectedImei;

    public String getSelectedImei() {
        return selectedImei;
    }

    public void setSelectedImei(String selectedImei) {
        this.selectedImei = selectedImei;
    }

    public ImeiDialog(JFrame parent, String productId) {
        super(parent, "Danh sách IMEI", true);
        setLayout(new BorderLayout());

        // Lấy danh sách IMEI dựa trên mã sản phẩm
        List<String> imeiList = getImeisByProductId(productId);

        // Hiển thị danh sách IMEI
        imeiJList = new JList<>(imeiList.toArray(new String[0]));
        imeiJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(imeiJList), BorderLayout.CENTER);

        // Nút chọn IMEI
        btnSelect = new JButton("Chọn");
        btnSelect.addActionListener(e -> {
             selectedImei = imeiJList.getSelectedValue();
            if (selectedImei != null) {
                JOptionPane.showMessageDialog(ImeiDialog.this,
                        "Bạn đã chọn IMEI: " + selectedImei,
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Đóng dialog
            } else {
                JOptionPane.showMessageDialog(ImeiDialog.this,
                        "Vui lòng chọn một IMEI!",
                        "Lỗi", JOptionPane.WARNING_MESSAGE);
            }
        });
        add(btnSelect, BorderLayout.SOUTH);

        setSize(300, 400);
        setLocationRelativeTo(parent); // Hiển thị ở giữa form cha
    }

    private List<String> getImeisByProductId(String productId) {
        reponsitory_getImei rp = new reponsitory_getImei();
        // Trả về danh sách IMEI dựa trên mã sản phẩm
        return rp.getAllImei(productId);
    }
}
