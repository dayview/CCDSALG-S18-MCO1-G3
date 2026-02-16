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

    //Helper method that combines the 2 sorted sub-arrays into a single sorted array
    private void merge(Record[] arr, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        comparisonCount += 2; 

        Record[] L = new Record[n1];
        Record[] R = new Record[n2];
        comparisonCount += 2; 

        for (int i = 0; i < n1; i++) {
            comparisonCount++; 
            L[i] = arr[p + i];
            comparisonCount++;
        }
        for (int j = 0; j < n2; j++) {
            comparisonCount++; 
            R[j] = arr[q + 1 + j];
            comparisonCount++;
        }

        int i = 0, j = 0, k = p;
        comparisonCount += 3; 

        while (i < n1 && j < n2) {
            comparisonCount++;
            if (L[i].getIdNumber() <= R[j].getIdNumber()) {
                comparisonCount++; 
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
            comparisonCount += 2; 
        }

        while (i < n1) {
            comparisonCount += 3; 
            arr[k] = L[i];
            i++; k++;
        }
        while (j < n2) {
            comparisonCount += 3; 
            arr[k] = R[j];
            j++; k++;
        }
    }


    public long mergeSort(Record[] arr, int p, int r) {
        comparisonCount++; 
        if (p < r) {
            int q = (p + r) / 2;
            comparisonCount++; 
            
            mergeSort(arr, p, q); //Recursive call to divide left side of array to single value sub-arrays
            mergeSort(arr, q + 1, r); // Recursive call to divide the left side of the array to single value sub-arrays
            merge(arr, p, q, r);
        }
        return comparisonCount;
    }

    /*
     * Define AT LEAST ONE more sorting algorithm here, apart from the
     * ones given above. Make sure that the method accepts an array of
     * records
     */

    //Helper method for Radix to find amount of place values
    private int getMaximum(Record[] arr) {
        int max = arr[0].getIdNumber();
        comparisonCount++; 
        for (int i = 1; i < arr.length; i++) {
            comparisonCount += 2; 
            if (arr[i].getIdNumber() > max) {
                max = arr[i].getIdNumber();
                comparisonCount++;
            }
        }
        return max;
    }

    //Helper method
    private void countSort(Record[] arr, int exp) {
        int n = arr.length;
        Record[] output = new Record[n];
        int[] count = new int[10];
        comparisonCount += 3;

        for (int i = 0; i < n; i++) {
            comparisonCount += 2; 
            int digit = (arr[i].getIdNumber() / exp) % 10;
            count[digit]++;
        }
        for (int i = 1; i < 10; i++) {
            comparisonCount += 2;
            count[i] += count[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            comparisonCount += 3; 
            int digit = (arr[i].getIdNumber() / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }
        for (int i = 0; i < n; i++) {
            comparisonCount += 2; 
            arr[i] = output[i];
        }
    }

    public void radixSort(Record[] arr) {
        if (arr.length == 0) return;
        
        int max = getMaximum(arr);
        comparisonCount++;

        for (int exp = 1; max / exp > 0; exp *= 10) {
            comparisonCount++; 
            countSort(arr, exp);
        }
    }

}
