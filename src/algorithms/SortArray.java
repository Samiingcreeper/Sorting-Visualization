package algorithms;

import eventHandler.EventHandler;
import eventHandler.Listener;
import javafx.scene.paint.Color;

import java.util.Hashtable;

public class SortArray {

    private int[] array;
    private Hashtable<String, Pointer> pointers;
    private boolean isWait;
    private boolean isTerminate;
    private EventHandler<Listener> onWaitHandler;

    SortArray(){
        pointers = new Hashtable<>();
        onWaitHandler = new EventHandler<>();
    }

    synchronized void forward(){
        isWait = false;
    }

    synchronized void terminate() {
        isTerminate = true;
        synchronized (this){
            notifyAll();
        }
        System.out.println("->> isTerminate set");
    }

    void addOnWaitListener(Listener listener){
        onWaitHandler.add(listener);
    }


    void setArray(int[] array){
        this.array = array;
    }

    Pointer createPointer(String name){
        Pointer pointer = new Pointer(this, name);
        pointers.put(pointer.getName(), pointer);
        return pointer;
    }

    synchronized int getAt(Pointer pointer) throws InterruptedException {
        int value;
        value = array[pointer.value()];

        isWait = true;
        onWaitHandler.invoke();
        while (isWait){
            wait();
            if(isTerminate){
                throw new TerminateException();
            }
        }

        return value;
    }

    synchronized void setAt(Pointer pointer, int newVal) throws InterruptedException {
        array[pointer.value()] = newVal;

        isWait = true;
        onWaitHandler.invoke();
        while (isWait){
            wait();
            if(isTerminate){
                throw new TerminateException();
            }
        }
    }

    void swap(Pointer p1, Pointer p2) throws InterruptedException{
        int temp = getAt(p1);
        setAt(p1, getAt(p2));
        setAt(p2, temp);
    }

    int size(){
        return array.length;
    }





    public class Content{
        public int[] getArray(){
            return array.clone();
        }

        public Pointer[] getPointers(){
            return pointers.values().toArray(new Pointer[0]);
        }
    }
}
