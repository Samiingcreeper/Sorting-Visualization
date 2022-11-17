package algorithms;

public class MergeSorter extends Sorter{

    private Pointer pStarts;
    private Pointer pEnds;
    private Pointer pLeft;
    private Pointer pRight;
    private Pointer pMid;
    private Pointer pReplace;

    public MergeSorter(){
        super();
        pStarts = createPointer("#Aux");
        pEnds = createPointer("#Aux");
        pLeft = createPointer("Left");
        pRight = createPointer("Right");
        pMid = createPointer("#Aux");
        pReplace = createPointer("Replace");
        pReplace.set(-1);
    }

    @Override
    void sort() throws InterruptedException {
        sort(0, sortArray().size() - 1);
    }

    private void sort(int indexStarts, int indexEnds) throws InterruptedException{
        if(indexStarts >= indexEnds){
            return;
        }
        int indexMid = (indexStarts + indexEnds) / 2;
        sort(indexStarts, indexMid);
        sort(indexMid + 1, indexEnds);

        pStarts.set(indexStarts);
        pEnds.set(indexEnds);
        pMid.set(indexMid);

        int[] temp = new int[indexEnds - indexStarts + 1];
        int i = 0;
        pLeft.set(pStarts.value());
        pRight.set(indexMid + 1);

        while (!isLeftEmpty() || !isRightEmpty()){

            boolean isPopFromLeft = !isLeftEmpty() &&
                    ( isRightEmpty() || sortArray().getAt(pLeft) < sortArray().getAt(pRight) );
            if(isPopFromLeft){
                popFromLeft(temp, i);
            }else {
                popFromRight(temp, i);
            }
            i++;
        }

        pLeft.set(-1);
        pRight.set(-1);
        for(int j = 0; j < temp.length; j++){
            pReplace.set(pStarts.value() + j);
            sortArray().setAt(pReplace, temp[j]);
        }
        pReplace.set(-1);
    }

    private void popFromRight(int[] temp, int i) throws InterruptedException {
        temp[i] = sortArray().getAt(pRight);
        pRight.increment();
    }

    private void popFromLeft(int[] temp, int i) throws InterruptedException {
        temp[i] = sortArray().getAt(pLeft);
        pLeft.increment();
    }

    private boolean isLeftEmpty(){
        return pLeft.value() > pMid.value();
    }

    private boolean isRightEmpty(){
        return pRight.value() > pEnds.value();
    }
}
