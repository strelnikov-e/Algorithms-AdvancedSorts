import java.util.Random;

public class QuickSort {

    public static void quickSort(Comparable[] a) {

    }

    public static void quickSort(Comparable[] a, int lo, int hi) {

    }


    public static void main(String[] args) {
        int n = 10000;
        Random random = new Random();
        Comparable[] a = new Comparable[n];
        Comparable[] b = new Comparable[n];
        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(n*10);
            b[i] = a[i];
        }
        long startTime = System.nanoTime();
        quickSort(a);
        long stopTime = System.nanoTime();
        System.out.println("Merge sort time: \t\t\t\t" + (stopTime - startTime) + "ns");

        startTime = System.nanoTime();
        quickSort(b);
        stopTime = System.nanoTime();
        System.out.println("Merge sort (CutOff) time: \t\t" + (stopTime - startTime) + "ns");
    }

}
