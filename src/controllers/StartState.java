package controllers;

public class StartState extends State {

    private boolean isPlay;
    private boolean isPlayOnlyOnce;

    StartState(Player player) {
        super(player);
    }

    @Override
    void enter() throws InterruptedException {

        //SorterManager.getInstance().resetAll();
        getPlayer().resetCompletedCount();
        getPlayer().resetWaitingCount();

        getPlayer().onStartEnter();

        while (!isPlay){
            synchronized (getPlayer()){
                getPlayer().wait();
            }
        }
        getPlayer().switchState(new PlayingState(getPlayer(), isPlayOnlyOnce));
    }

    @Override
    void exit() {
        SorterManager.getInstance().startAll();
    }

    @Override
    synchronized void onPlay(boolean isPlayOnlyOnce) throws InterruptedException {
        isPlay = true;
        this.isPlayOnlyOnce = isPlayOnlyOnce;
        synchronized (getPlayer()){
            getPlayer().notifyAll();
        }
    }

    @Override
    void onPause() {

    }

    @Override
    void onReset() throws InterruptedException {

    }
}
