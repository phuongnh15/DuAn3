/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class SalesChartApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Thống kê số lượng bán theo tháng trong năm 2023");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            // Create and customize chart
            JFreeChart barChart = createChart();
            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new Dimension(800, 600));
            frame.add(chartPanel);

            frame.setVisible(true);
        });
    }

    private static JFreeChart createChart() {
        // Create dataset with example data
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Example data (Total quantity per month in 2023)
        dataset.addValue(1200, "Số lượng", "Tháng 1");
        dataset.addValue(1350, "Số lượng", "Tháng 2");
        dataset.addValue(1450, "Số lượng", "Tháng 3");
        dataset.addValue(1500, "Số lượng", "Tháng 4");
        dataset.addValue(1600, "Số lượng", "Tháng 5");
        dataset.addValue(1700, "Số lượng", "Tháng 6");
        dataset.addValue(1800, "Số lượng", "Tháng 7");
        dataset.addValue(1900, "Số lượng", "Tháng 8");
        dataset.addValue(2000, "Số lượng", "Tháng 9");
        dataset.addValue(2100, "Số lượng", "Tháng 10");
        dataset.addValue(2200, "Số lượng", "Tháng 11");
        dataset.addValue(2300, "Số lượng", "Tháng 12");

        // Create the bar chart with customization
        JFreeChart chart = ChartFactory.createBarChart(
                "Thống kê số lượng bán theo tháng trong năm 2023", // Chart title
                "Tháng", // X axis label
                "Tổng Số Lượng", // Y axis label
                dataset,
                PlotOrientation.VERTICAL, // Bar chart orientation
                true, // Include legend
                true, // Tooltips enabled
                false // URLs disabled
        );

        // Customize the chart
        chart.setBackgroundPaint(Color.white);

        // Get the plot and customize
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(240, 240, 240));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        // Customize the renderer (bars)
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(0, 122, 255)); // Set the color of bars to blue

        // Customize axis label fonts
        plot.getDomainAxis().setLabelFont(new Font("Arial", Font.BOLD, 14));
        plot.getRangeAxis().setLabelFont(new Font("Arial", Font.BOLD, 14));

        // Customize chart title font
        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 16));

        return chart;
    }
}


