package controllers;

import eventHandler.EventHandler;
import eventHandler.Listener;

public class Player{


    //region Singleton
    private static Player instance;
    public static Player getInstance(){
        if(instance == null){
            instance = new Player();
            initializePlayer(instance);
        }
        return instance;
    }

    private static void initializePlayer(Object obj) {
        getInstance().onStateLoadedHandler.add(() -> {
            getInstance().isStateLoaded = true;
            synchronized (obj){
                obj.notifyAll();
            }
        });

        new Thread(() ->{
            try {
                getInstance().switchState(new StartState(instance));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        while (!instance.isStateLoaded){
            synchronized (obj){
                try{
                    obj.wait();
                }catch (InterruptedException exc){
                    exc.printStackTrace();
                }
            }
        }
    }
    //endregion



    private int waitingCount;
    private int completedCount;

    private State currState;
    private boolean isStateLoaded;

    private long frameDeltaMillis = 10;

    private EventHandler<Listener> frameUpdateHandler;
    private EventHandler<Listener> onStartEnterHandler;
    private EventHandler<Listener> onPlayingEnterHandler;
    private EventHandler<Listener> onPausedEnterHandler;
    private EventHandler<Listener> onEndEnterHandler;

    private EventHandler<Listener> onStateLoadedHandler;


    private Player(){
        frameUpdateHandler = new EventHandler<>();
        onStartEnterHandler = new EventHandler<>();
        onPausedEnterHandler = new EventHandler<>();
        onPlayingEnterHandler = new EventHandler<>();
        onEndEnterHandler = new EventHandler<>();

        onStateLoadedHandler = new EventHandler<>();
    }




    /*
        ========== Package Interface ==========
     */

    public synchronized void play() throws InterruptedException {
//        pauseThreadUntilStateLoaded();
        currState.onPlay(false);
    }

    public synchronized void playNextFrame() throws InterruptedException {
//        pauseThreadUntilStateLoaded();
        currState.onPlay(true);
    }

    public synchronized void pause() throws InterruptedException {
//        pauseThreadUntilStateLoaded();
        currState.onPause();
    }

    public synchronized void reset() throws InterruptedException{
//        pauseThreadUntilStateLoaded();
        currState.onReset();
    }

    public synchronized void setFrameDeltaMillis(long val){
        System.out.println("->> Set Frame Rate " + val);
        frameDeltaMillis = val;
    }

    public synchronized void addFrameUpdateHandler(Listener listener){
        frameUpdateHandler.add(listener);
    }

    public synchronized void addOnStartEnterHandler(Listener listener){ onStartEnterHandler.add(listener);}

    public synchronized void addOnPlayingEnterHandler(Listener listener){
        onPlayingEnterHandler.add(listener);
    }

    public synchronized void addOnPausedEnterHandler(Listener listener){
        onPausedEnterHandler.add(listener);
    }

    public synchronized void addOnEndEnterHandler(Listener listener) { onEndEnterHandler.add(listener);}










    synchronized void informWaiting(){
        waitingCount++;
        notifyAll();
    }


    synchronized void informCompleted(){
        completedCount++;
        notifyAll();
    }

    synchronized boolean haveAllCompleted(){
        return completedCount == SorterManager.getInstance().getSorterCount();
    }

    synchronized boolean areAllWaiting(){
        return waitingCount == SorterManager.getInstance().getSorterCount() - completedCount;
    }


    synchronized void resetWaitingCount(){
        waitingCount = 0;
    }

    synchronized void resetCompletedCount(){
        completedCount = 0;
    }

    synchronized void invokeFrameUpdateHandler(){
        frameUpdateHandler.invoke();
    }

    synchronized void onStartEnter(){ onStartEnterHandler.invoke();}

    synchronized void onPlayingEnter(){
        onPlayingEnterHandler.invoke();
    }

    synchronized void onPausedEnter(){
        onPausedEnterHandler.invoke();
    }

    synchronized void onEndEnter() { onEndEnterHandler.invoke();}


    synchronized void switchState(State newState) throws InterruptedException {
        if(currState != null){
            currState.exit();
        }
        currState = newState;
        if(!isStateLoaded){
            isStateLoaded = true;
            notifyAll();
            onStateLoadedHandler.invoke();
        }
        if(currState != null){
            currState.enter();
        }
    }

    synchronized long getFrameDeltaMillis(){
        return frameDeltaMillis;
    }

//    private synchronized void pauseThreadUntilStateLoaded() throws InterruptedException {
//        while (!isStateLoaded){
//            wait();
//        }
//    }

}
