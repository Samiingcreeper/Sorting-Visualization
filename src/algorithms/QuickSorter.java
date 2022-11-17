package algorithms;

import java.util.function.Supplier;

public class QuickSorter extends Sorter{

    private Pointer pStarts;
    private Pointer pEnds;
    private Pointer pLeft;
    private Pointer pRight;
    private Pointer pPivot;

    public QuickSorter(){
        super();
        pStarts = createPointer("Starts");
        pEnds = createPointer("Ends");
        pLeft = createPointer("Left");
        pRight = createPointer("Right");
        pPivot = createPointer("#Aux");
    }

    @Override
    void sort() throws InterruptedException {
        sort(0, sortArray().size() - 1);
    }

    private void sort(int indexStarts, int indexEnds) throws InterruptedException {
        if(indexStarts > indexEnds){
            return;
        }
        int indexPivot = partition(indexStarts, indexEnds);
        sort(indexStarts, indexPivot - 1);
        sort(indexPivot + 1, indexEnds);
    }

    private int partition(int indexStarts, int indexEnds) throws InterruptedException {
        pStarts.set(indexStarts);
        pEnds.set(indexEnds);

        int pivotElement = sortArray().getAt(pEnds);
        pLeft.set(indexStarts);
        pRight.set(indexEnds - 1);

        while (!isPartitionEnds()){

            while (sortArray().getAt(pLeft) < pivotElement && !isPartitionEnds()){
                pLeft.increment();
            }
            while (sortArray().getAt(pRight) >= pivotElement && !isPartitionEnds()){
                pRight.decrement();
            }

            if(!isPartitionEnds()){
                sortArray().swap(pLeft, pRight);
            }
        }

        pPivot.set(pLeft.value());
        if(sortArray().getAt(pLeft) < pivotElement){
            pPivot.increment();
        }
        sortArray().swap(pPivot, pEnds);

        return pPivot.value();
    }

    private boolean isPartitionEnds(){
        return pLeft.value() >= pRight.value();
    }
}
