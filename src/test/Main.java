package test;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] array = RandomIntegers.getArray(10);

        System.out.println("Array to sort ->> " + Arrays.toString(array));

        Sorter sorter = new MergerSorter();
        sorter.sort(array);


        System.out.println("Array sorted ->> " + Arrays.toString(array));
    }
}
