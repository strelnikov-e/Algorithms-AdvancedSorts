import java.util.Random;

public class MergeSort {
    private static final int CUTOFF = 7;

    private static void merge(Comparable[] a,Comparable[] aux,int lo,int mid,int hi) {
        assert isSorted(a, lo,mid);
        assert  isSorted(a, mid+1, hi);

        //copy array
        if (hi + 1 >= 0) System.arraycopy(a, 0, aux, 0, hi + 1);

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)                            a[k] = aux[j++];
            else if (j > hi)                        a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0)  a[k] = aux[j++];
            else                                    a[k] = aux[i++];
        }
        assert isSorted(a,lo,hi);
    }

    private static void mergeSort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi-lo)/2;
        mergeSort(a,aux,lo,mid);
        mergeSort(a,aux,mid+1,hi);
        merge(a,aux,lo,mid,hi);
    }

    //Use insertion sort for small subarrays;
    private static void mergeSortCutOff(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo + CUTOFF - 1) {
            ElementarySorts.insertionSort(a, lo, hi);
            return;
        }
        int mid = lo + (hi-lo)/2;
        mergeSort(a,aux,lo,mid);
        mergeSort(a,aux,mid+1,hi);
        merge(a,aux,lo,mid,hi);
    }

    public static void mergeSort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        mergeSort(a,aux,0,a.length-1);
        assert isSorted(a);
    }

    public static void mergeSortCutOff(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        mergeSortCutOff(a,aux,0,a.length-1);
        assert isSorted(a);
    }

    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo+1; i <= hi; i++) {
            if (a[i].compareTo(a[i-1]) < 0) return false;
        }
        return true;
    }

    public static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0 , a.length-1);
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
        mergeSort(a);
        long stopTime = System.nanoTime();
        System.out.println("Merge sort time: \t\t\t\t" + (stopTime - startTime) + "ns");

        startTime = System.nanoTime();
        mergeSortCutOff(b);
        stopTime = System.nanoTime();
        System.out.println("Merge sort (CutOff) time: \t\t" + (stopTime - startTime) + "ns");

    }
}
