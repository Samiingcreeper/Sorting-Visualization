package algorithms;

import javafx.scene.paint.Color;

import java.util.function.Supplier;

public class InsertionSorter extends Sorter{

    private Pointer pElementToInsert;
    private Pointer pInsertionProbe;
    private Pointer pAuxiliary;

    public InsertionSorter(){
        super();
        pElementToInsert = createPointer("Element to insert");
        pInsertionProbe = createPointer("Insertion location");
        pAuxiliary = createPointer("#Aux");
    }

    @Override
    void sort() throws InterruptedException {
        for(; pElementToInsert.value() < sortArray().size(); pElementToInsert.increment()){
            int elementToInsert = sortArray().getAt(pElementToInsert);

            pInsertionProbe.set(pElementToInsert.value() - 1);
            boolean isInsertionLocationFound = false;
            while (pInsertionProbe.value() >= 0 && !isInsertionLocationFound){
                isInsertionLocationFound = sortArray().getAt(pInsertionProbe) <= elementToInsert;
                if(!isInsertionLocationFound){
                    pAuxiliary.set(pInsertionProbe.value() + 1);
                    sortArray().setAt(pAuxiliary, sortArray().getAt(pInsertionProbe));
                    pInsertionProbe.decrement();
                }
            }

            pInsertionProbe.increment();
            sortArray().setAt(pInsertionProbe, elementToInsert);
        }
    }
}
