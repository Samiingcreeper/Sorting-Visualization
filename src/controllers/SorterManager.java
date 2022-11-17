package controllers;

import algorithms.*;
import eventHandler.EventHandler;
import eventHandler.Listener;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.function.Supplier;

public class SorterManager {

    /*
        ========== Singleton ==========
     */
    private static SorterManager instance;
    public static SorterManager getInstance(){
        if(instance == null){
            instance = new SorterManager();
        }
        return instance;
    }

    static {
        Player.getInstance();
    }


    private HashMap<SorterType, Sorter> sorters;
    private int[] elementsToSort;

    private EventHandler<Listener> onElementsToSortUpdated;


    private SorterManager(){
        sorters = new HashMap<>();
//        sorters.put(SorterType.INSERTION_SORT, null);
//        sorters.put(SorterType.QUICK_SORT, null);
//        sorters.put(SorterType.MERGE_SORT, null);

        sorters.put(SorterType.INSERTION_SORT, createSorter(SorterType.INSERTION_SORT));
        sorters.put(SorterType.QUICK_SORT, createSorter(SorterType.QUICK_SORT));
        sorters.put(SorterType.MERGE_SORT, createSorter(SorterType.MERGE_SORT));

        onElementsToSortUpdated = new EventHandler<>();
    }






    /*
        ========== Package Interface ==========
     */

    public synchronized void setElementsToSort(int[] elementsToSort){
        this.elementsToSort = elementsToSort;
        for(Sorter sorter : sorters.values()){
            if(sorter != null)
                sorter.setSortArray(elementsToSort);
        }
        onElementsToSortUpdated.invoke();
    }

    public SortArray.Content getSorterContent(SorterType sorterType){
        return sorters.get(sorterType).getArrayContent();
    }

    public void addOnElementsToSortUpdated(Listener listener){
        onElementsToSortUpdated.add(listener);
    }







    synchronized void forwardAll(){
        for(Sorter sorter : sorters.values()){
            sorter.forward();
        }
    }

    synchronized void startAll(){
        for(Sorter sorter : sorters.values()){
            sorter.start();
        }
    }

    synchronized void resetAll(){
        for(Sorter sorter : sorters.values()){
            if(sorter != null)
                sorter.terminate();
        }
        for(SorterType type : sorters.keySet()){
            Sorter newSorter = createSorter(type);
            newSorter.setSortArray(elementsToSort);
            sorters.put(type, newSorter);
        }
    }

    int getSorterCount(){
        return sorters.size();
    }



    private Sorter createSorter(SorterType sorterType){
        Sorter sorter = null;
        switch (sorterType){
            case INSERTION_SORT: sorter = new InsertionSorter(); break;
            case QUICK_SORT: sorter = new QuickSorter(); break;
            case MERGE_SORT: sorter = new MergeSorter(); break;
        }

        sorter.addOnWaitListener(Player.getInstance()::informWaiting);
        sorter.addOnCompleteListener(Player.getInstance()::informCompleted);
        return sorter;
    }
}
