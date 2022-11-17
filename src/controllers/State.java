package controllers;

public abstract class State {

    private Player player;

    State(Player player){
        this.player = player;
    }

    abstract void enter() throws InterruptedException;

    abstract void exit() throws InterruptedException;

    abstract void onPlay(boolean isPlayOnlyOnce) throws InterruptedException;

    abstract void onPause() throws InterruptedException;

    abstract void onReset() throws InterruptedException;

    Player getPlayer(){
        return player;
    }


//    synchronized void informWaiting(){
//        getPlayer().incrementWaitingCount();
//        notifyAll();
//    }
//
//    synchronized void informCompleted(){
//        getPlayer().incrementCompletedCount();
//        notifyAll();
//    }
}
