package test;

public class MergerSorter extends Sorter{

    @Override
    void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private void sort(int[] array, int pStarts, int pEnds){
        if(pStarts >= pEnds){
            return;
        }
        int pMid = (pStarts + pEnds) / 2;
        sort(array, pStarts, pMid);
        sort(array, pMid + 1, pEnds);

        int[] temp = new int[pEnds - pStarts + 1];
        int i = 0;
        int pLeft = pStarts;
        int pRight = pMid + 1;
        while (!isLeftEmpty(pLeft, pMid) || !isRightEmpty(pRight, pEnds)){

            if(isRightEmpty(pRight, pEnds)){
                /*
                    ARRAY ACCESS
                 */
                temp[i] = array[pLeft++];
            }
            else if(isLeftEmpty(pLeft, pMid)){
                /*
                    ARRAY ACCESS
                */
                temp[i] = array[pRight++];
            }
            else {
                /*
                    ARRAY ACCESS x2
                */
                boolean isPutFromLeft = array[pLeft] < array[pRight];
                if(isPutFromLeft){
                    /*
                        ARRAY ACCESS
                    */
                    temp[i] = array[pLeft++];
                }else {
                    /*
                        ARRAY ACCESS
                    */
                    temp[i] = array[pRight++];
                }
            }
            i++;
        }

        for (int j = 0; j < temp.length; j++){
            /*
                ARRAY ACCESS
             */
            array[pStarts + j] = temp[j];
        }
    }

    private boolean isLeftEmpty(int pLeft, int pMid){
        return pLeft > pMid;
    }

    private boolean isRightEmpty(int pRight, int pEnds){
        return pRight > pEnds;
    }
}
