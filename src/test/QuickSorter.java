package test;

class QuickSorter extends Sorter{

    @Override
    void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private void sort(int[] array, int pStarts, int pEnds){
        if(pStarts > pEnds){
            return;
        }
        int pPivot = partition(array, pStarts, pEnds);
        sort(array, pStarts, pPivot - 1);
        sort(array, pPivot + 1, pEnds);
    }

    /**
     *  returns the index of the pivot, -1 means no need to partition anymore
     */
    private int partition(int[] array, int pStarts, int pEnds){
        // the pivot is the right most element

        /*
           ARRAY ACCESS
         */
        int pivot = array[pEnds];
        int pLeft = pStarts;
        int pRight = pEnds - 1;

        // element x is LEFT if x < pivot, element x is RIGHT if x >= pivot
        // the pivot is put in the "center" after the partition ends
        while (!isPartitionEnds(pLeft, pRight)){

            // move pLeft to the right until it meets a RIGHT element

            /*
                ARRAY ACCESS
            */
            while (array[pLeft] < pivot && !isPartitionEnds(pLeft, pRight)){
                pLeft++;
            }
            // move pRight to the left until it meets a LEFT element

            /*
                ARRAY ACCESS
            */
            while (array[pRight] >= pivot && !isPartitionEnds(pLeft, pRight)){
                pRight--;
            }

            if(!isPartitionEnds(pLeft, pRight)){

                /*
                    ARRAY ACCESS x2
                */
                swap(array, pLeft, pRight);
            }
        }

        int pPivot = pLeft;

        /*
            ARRAY ACCESS
        */
        if(array[pLeft] < pivot){
            pPivot++;
        }

        /*
            ARRAY ACCESS x2
        */
        swap(array, pPivot, pEnds);

        return pPivot;
    }

    private boolean isPartitionEnds(int pLeft, int pRight){
        return pLeft >= pRight;
    }
}
