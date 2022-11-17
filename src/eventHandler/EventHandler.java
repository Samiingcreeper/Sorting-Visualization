package eventHandler;

import java.util.HashSet;

public class EventHandler<T extends Listener> {

    private HashSet<T> listeners;

    public EventHandler(){
        listeners = new HashSet<>();
    }

    public void invoke(){
        for(Listener listener : listeners){
            listener.invoke();
        }
    }

    public void add(T listener){
        listeners.add(listener);
    }

    public void remove(T listener){
        listeners.remove(listener);
    }
}
