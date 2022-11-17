package controllers;

public class EndState extends State {

    private boolean isReset;

    EndState(Player player) {
        super(player);
    }

    @Override
    void enter() throws InterruptedException {
        getPlayer().onEndEnter();

        while (!isReset){
            synchronized (getPlayer()){
                getPlayer().wait();
            }
        }

        if(isReset){
            getPlayer().switchState(new ResetState(getPlayer()));
        }
    }

    @Override
    void exit() {

    }

    @Override
    void onPlay(boolean isPlayOnlyOnce) throws InterruptedException {

    }

    @Override
    void onPause() {

    }

    @Override
    void onReset() throws InterruptedException {
        isReset = true;
        synchronized (getPlayer()){
            getPlayer().notifyAll();
        }
    }
}
