package algorithms;

import eventHandler.EventHandler;
import eventHandler.Listener;
import javafx.scene.paint.Color;

import java.util.function.Supplier;

public abstract class Sorter extends Thread{

    private SortArray sortArray;
    private EventHandler<Listener> onCompleteHandler;

    public Sorter(){
        sortArray = new SortArray();
        onCompleteHandler = new EventHandler<>();
    }


    @Override
    public void run(){
        setName(getClass().getName());
        try {
            sort();
            onCompleteHandler.invoke();

        } catch(TerminateException exc){
            System.out.println("->> terminated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    abstract void sort() throws InterruptedException;





    /*
        ========== Package Interface ==========
     */

    public void forward(){
        sortArray.forward();
        synchronized (sortArray){
            sortArray.notifyAll();
        }
    }

    public void terminate(){
        sortArray.terminate();
    }

    public SortArray.Content getArrayContent(){
        return sortArray.new Content();
    }

    public void setSortArray(int[] elements){
        sortArray.setArray(elements == null ? null : elements.clone());
    }

    public void addOnWaitListener(Listener listener){
        sortArray.addOnWaitListener(listener);
    }

    public void addOnCompleteListener(Listener listener){
        onCompleteHandler.add(listener);
    }










    /*
     *   ========== Other Methods ==========
     */

    SortArray sortArray(){
        return sortArray;
    }

    Pointer createPointer(String name){
        return sortArray.createPointer(name);
    }


}
