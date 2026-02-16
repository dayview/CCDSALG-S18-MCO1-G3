import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // TODO: Use this method to run your experiments.

        // CHANGE THIS TO YOUR FILE PATH
        String folderPath = "C:\\Users\\marti\\Documents\\CCDSALG\\CCDSALG-S18-MCO1-G3\\starter_code_java\\data\\";

        String[] files = {
                folderPath + "random100.txt",
                folderPath + "random25000.txt",
                folderPath + "random50000.txt",
                folderPath + "random75000.txt",
                folderPath + "random100000.txt",
                folderPath + "almostsorted.txt",
                folderPath + "totallyreversed.txt"
        };

        FileReader fileReader = new FileReader();
        SortingAlgorithms sorter = new SortingAlgorithms();

        System.out.printf("%-25s %-15s %-15s %-15s\n", "Dataset", "Algorithm", "Avg Time (ms)", "Comparisons");

        for (String filePath : files) {
            Record[] originalData = fileReader.readFile(filePath);

            if (originalData == null) {
                System.out.println("Could not read file: " + filePath);
                continue;
            }

            runExperiment(sorter, originalData, "Insertion Sort", filePath);
            runExperiment(sorter, originalData, "Selection Sort", filePath);
            runExperiment(sorter, originalData, "Merge Sort", filePath);
            runExperiment(sorter, originalData, "Radix Sort", filePath);

            System.out.println("-----------------------------------------------------------------------");
        }
    }
    private static void saveSortedData(Record[] data, String algo, String originalFileName) {
        String outputFileName = "sorted_" + algo.replace(" ", "") + "_" + originalFileName;
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFileName))) {
            for (Record r : data) {
                writer.println(r.getIdNumber() + " " + r.getName()); 
            }
        } catch (IOException e) {
            System.err.println("Could not save sorted file: " + e.getMessage());
        }
    }

    private static void runExperiment(SortingAlgorithms sorter, Record[] originalData, String algoName, String filePath) {
        int n = originalData.length;
        long totalTime = 0;
        long comparisons = 0;
        int runs = 5;

        // measure execution time (average of 5 runs)
        for (int i = 0; i < runs; i++) {
            Record[] dataCopy = Arrays.copyOf(originalData, n);

            long startTime = System.currentTimeMillis();

            if (algoName.equals("Insertion Sort")) {
                sorter.insertionSort(dataCopy, n);
            } else if (algoName.equals("Selection Sort")) {
                sorter.selectionSort(dataCopy, n);
            }else if (algoName.equals("Merge Sort")) {
                sorter.mergeSort(dataCopy, 0, n - 1); 
            } else if (algoName.equals("Radix Sort")) {
                sorter.radixSort(dataCopy); 
            }

            long endTime = System.currentTimeMillis();
            totalTime += (endTime - startTime);
        }

        double avgTime = totalTime / (double) runs;

        // measure frequency count
        Record[] dataCopyForCount = Arrays.copyOf(originalData, n);

        // reset the counter manually
        sorter.comparisonCount = 0; //comparisonCount from SortingAlgorithms class

        if (algoName.equals("Insertion Sort")) {
            sorter.insertionSort(dataCopyForCount, n);
        } else if (algoName.equals("Selection Sort")) {
            sorter.selectionSort(dataCopyForCount, n);
        } else if (algoName.equals("Merge Sort")) {
            sorter.mergeSort(dataCopyForCount, 0, n - 1);
        } else if (algoName.equals("Radix Sort")) {
            sorter.radixSort(dataCopyForCount);
        }

        // retrieve the count manually
        comparisons = sorter.comparisonCount;

        // format filename for cleaner output
        String fileName = new java.io.File(filePath).getName();

        saveSortedData(dataCopyForCount, algoName, fileName); 

        System.out.printf("%-25s %-15s %-15.2f %-15d\n", fileName, algoName, avgTime, comparisons);
    }

}
