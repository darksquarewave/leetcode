package sedgewick;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {

    public void quickSort(int[] array) {
        partition(array, 0, array.length - 1);
    }

    private void partition(int[] array, int lower, int upper) {
        if (lower >= upper) {
            return;
        }

        int pivot = ThreadLocalRandom.current().nextInt(lower, upper);
        int val = array[pivot];

        int i = lower;
        int j = upper;

        while (i <= j) {
            if (array[i] >= val) {
                swap(array, i, j);
                if (i == pivot) {
                    pivot = j;
                } else if (j == pivot) {
                    pivot = i;
                }
                j--;
            } else {
                i++;
            }
        }

        swap(array, pivot, i);

        partition(array, lower, i - 1);
        partition(array, i + 1, upper);
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static int[] randomArray(int n){
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(-1000, 1000);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] a = randomArray(1000000);
        QuickSort qs = new QuickSort();

        long start = System.nanoTime();
        qs.quickSort(a);
        long end = System.nanoTime();
        System.out.println("time: " + (end-start)/1000000);

        a = randomArray(1000000);
        start = System.nanoTime();
        Arrays.sort(a);
        end = System.nanoTime();
        System.out.println("time: " + (end-start)/1000000);
    }
}
