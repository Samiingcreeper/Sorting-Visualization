package test;

public class RandomIntegers {

    public static int[] getArray(int size){
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i + 1;
        }
        for (int indexOfElementToSwap = 0; indexOfElementToSwap < size; indexOfElementToSwap++) {
            int indexOfSwapTarget = (int)(Math.random() * size);
            int temp = array[indexOfElementToSwap];
            array[indexOfElementToSwap] = array[indexOfSwapTarget];
            array[indexOfSwapTarget] = temp;
        }
        return array;
    }
}
