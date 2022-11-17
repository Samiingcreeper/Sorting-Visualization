package algorithms;

import javafx.scene.paint.Color;

public class Pointer {

    private String name;

    private SortArray sortArray;
    private int value;

    Pointer(SortArray sortArray, String name){
        this.sortArray = sortArray;
        this.name = name;
    }

    public int value(){
        return value;
    }

    public String getName(){
        return name;
    }

    public boolean isOutOfBound(){
        return value < 0 || value >= sortArray.size();
    }

    public boolean isAuxiliary(){
        return name.equals("#Aux");
    }

    Pointer increment(){
        value++;
        return this;
    }

    Pointer decrement(){
        value--;
        return this;
    }

    Pointer set(int newVal){
        value = newVal;
        return this;
    }

}
