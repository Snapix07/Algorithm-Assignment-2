package main.java.visualization;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PerformancePlotter {
    public static void main(String[] args) throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Структура для хранения данных
        Map<String, Map<Integer, Double>> algorithmAverages = new HashMap<>();
        Map<String, Map<Integer, Integer>> algorithmCounts = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("results.csv"))) {
            String line;
            br.readLine(); // пропускаем заголовок

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) continue;

                String algorithm = parts[0].trim();
                int size = Integer.parseInt(parts[1].trim());
                String dataType = parts[2].trim();

                // Парсим время, заменяя запятые на точки если нужно
                String timeStr = parts[3].trim().replace(',', '.');
                double time = Double.parseDouble(timeStr);

                // Фильтруем только random данные
                if (!"random".equals(dataType)) {
                    continue;
                }

                // Инициализируем структуры данных
                algorithmAverages.putIfAbsent(algorithm, new HashMap<>());
                algorithmCounts.putIfAbsent(algorithm, new HashMap<>());

                // Обновляем среднее значение
                Map<Integer, Double> avgMap = algorithmAverages.get(algorithm);
                Map<Integer, Integer> countMap = algorithmCounts.get(algorithm);

                double currentAvg = avgMap.getOrDefault(size, 0.0);
                int currentCount = countMap.getOrDefault(size, 0);

                double newAvg = (currentAvg * currentCount + time) / (currentCount + 1);

                avgMap.put(size, newAvg);
                countMap.put(size, currentCount + 1);
            }
        }

        // Определяем порядок размеров для оси X
        Integer[] sizes = {100, 500, 1000, 2000, 5000, 10000};

        // Добавляем данные в dataset в правильном порядке
        for (int size : sizes) {
            for (String algorithm : Arrays.asList("InsertionSort", "SelectionSort")) {
                if (algorithmAverages.containsKey(algorithm) &&
                        algorithmAverages.get(algorithm).containsKey(size)) {

                    double avgTime = algorithmAverages.get(algorithm).get(size);
                    dataset.addValue(avgTime, algorithm, String.valueOf(size));

                    System.out.printf("Algorithm: %s, Size: %d, Average Time: %.3f ms%n",
                            algorithm, size, avgTime);
                }
            }
        }

        // Создаем график
        JFreeChart chart = ChartFactory.createLineChart(
                "Algorithm Performance Comparison (Random Data)",
                "Input Size",
                "Time (ms)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Настраиваем внешний вид графика
        CategoryPlot plot = chart.getCategoryPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // Настраиваем отображение
        JFrame frame = new JFrame("Sorting Algorithms Performance");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}