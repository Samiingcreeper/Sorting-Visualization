package test;

class InsertionSorter extends Sorter{

    @Override
    public void sort(int[] array){
        for (int pElementToInsert = 0; pElementToInsert < array.length; pElementToInsert++){

            /*
                ARRAY ACCESS
             */
            int elementToInsert = array[pElementToInsert];

            int pInsertionProbe = pElementToInsert - 1;
            int pLocationToInsert = pElementToInsert;
            boolean isInsertionLocationFound = false;
            while (pInsertionProbe >= 0 && !isInsertionLocationFound){
                /*
                    ARRAY ACCESS
                */
                isInsertionLocationFound = array[pInsertionProbe] <= elementToInsert;
                if(!isInsertionLocationFound){
                    // "move" the element pointed by the insertion probe to the right by 1

                    /*
                        ARRAY ACCESS x2
                    */
                    array[pInsertionProbe + 1] = array[pInsertionProbe];
                    pLocationToInsert = pInsertionProbe;
                }else {
                    break;
                }
                pInsertionProbe--;
            }

            /*
                ARRAY ACCESS
             */
            array[pLocationToInsert] = elementToInsert;
        }
    }
}
