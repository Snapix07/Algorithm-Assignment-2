package main.java.visualization;
import main.java.algoritms.insertionSort;
import main.java.algoritms.SelectionSort;
import main.java.metrics.PerformanceMetrics;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataGeneratorForPlot {

    public static void generateRealisticTestData() throws IOException {
        // Очищаем файл и пишем заголовок
        try (FileWriter writer = new FileWriter("results.csv", false)) {
            writer.write("Algorithm,Size,DataType,Time(ms),Comparisons,Swaps,ArrayAccesses\n");
        }

        int[] sizes = {100, 500, 1000, 2000, 5000, 10000};
        Random random = new Random(42);

        // Делаем 3 прогона для каждого размера для усреднения
        int runs = 3;

        for (int run = 0; run < runs; run++) {
            System.out.println("Run " + (run + 1) + " of " + runs);

            for (int size : sizes) {
                System.out.println("  Testing size: " + size);

                // Генерируем случайный массив
                int[] array = new int[size];
                for (int i = 0; i < size; i++) {
                    array[i] = random.nextInt(size * 10);
                }

                // Тестируем InsertionSort
                int[] insertionArray = array.clone();
                long startTime = System.nanoTime();
                PerformanceMetrics insertionMetrics = insertionSort.sortWithMetrics(insertionArray);
                long insertionTime = System.nanoTime() - startTime;
                double insertionMs = insertionTime / 1_000_000.0;

                // Тестируем SelectionSort
                int[] selectionArray = array.clone();
                startTime = System.nanoTime();
                PerformanceMetrics selectionMetrics = SelectionSort.sortWithMetrics(selectionArray);
                long selectionTime = System.nanoTime() - startTime;
                double selectionMs = selectionTime / 1_000_000.0;

                // Записываем результаты
                try (FileWriter writer = new FileWriter("results.csv", true)) {
                    writer.write(String.format("InsertionSort,%d,random,%.3f,%d,%d,%d%n",
                            size, insertionMs,
                            insertionMetrics.getComparisons(),
                            insertionMetrics.getSwaps(),
                            insertionMetrics.getArrayAccesses()));

                    writer.write(String.format("SelectionSort,%d,random,%.3f,%d,%d,%d%n",
                            size, selectionMs,
                            selectionMetrics.getComparisons(),
                            selectionMetrics.getSwaps(),
                            selectionMetrics.getArrayAccesses()));
                }

                // Небольшая пауза между тестами
                try { Thread.sleep(10); } catch (InterruptedException e) {}
            }
        }

        System.out.println("Test data generation completed!");
    }

    public static void main(String[] args) throws IOException {
        generateRealisticTestData();

        PerformancePlotter.main(args);
    }
}
