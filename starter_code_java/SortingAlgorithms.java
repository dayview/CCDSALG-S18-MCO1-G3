/* This file contains implementations of sorting algorithms.
 * You are NOT allowed to modify any of the given method headers.
 */

public class SortingAlgorithms {

    /*
     * You may define additional helper functions here if you need to.
     * Make sure the helper functions have the private access modifier, as
     * they will only be used in this class.
     */

    // used in Main.java
    public long comparisonCount = 0; 

    // helper function used in selectionSort
    private void swap(Record[] arr, int i, int j) { 
        Record temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void insertionSort(Record[] arr, int n) {
        // TODO: Implement this sorting algorithm here.
        for (int i = 1; i < n; i++) {
            Record key = arr[i];
            int j = i - 1;
            boolean placeFound = false; 
            
            while (j >= 0 && !placeFound) {
                comparisonCount++; 

                if (arr[j].getIdNumber() > key.getIdNumber()) {
                    arr[j + 1] = arr[j];
                    j = j - 1;
                } else {
                    placeFound = true;
                }
            }
            arr[j + 1] = key;
        }
    }

    public void selectionSort(Record[] arr, int n) {
        // TODO: Implement this sorting algorithm here.
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                comparisonCount++; 
                if (arr[j].getIdNumber() < arr[minIdx].getIdNumber()) {
                    minIdx = j;
                }
            }
            swap(arr, minIdx, i);
        }
    }

    public void mergeSort(Record[] arr, int p, int r) {
        // TODO: Implement this sorting algorithm here.

    }

    /*
     * Define AT LEAST ONE more sorting algorithm here, apart from the
     * ones given above. Make sure that the method accepts an array of
     * records
     */


}
