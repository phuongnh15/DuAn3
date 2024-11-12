/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import Model.Model_Imei_Sp;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelImporter {

    public static List<Model_Imei_Sp> importExcel(String filePath) {
        List<Model_Imei_Sp> imeiList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Bỏ qua dòng đầu (header)
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String imei = row.getCell(0).getStringCellValue(); // Cột 1: IMEI
                String maSP = row.getCell(1).getStringCellValue(); // Cột 2: Mã sản phẩm

                imeiList.add(new Model_Imei_Sp(imei, maSP));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imeiList;
    }
}
