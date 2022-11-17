package test;

abstract class Sorter {

    abstract void sort(int[] array);

    void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
