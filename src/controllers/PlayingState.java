package controllers;

public class PlayingState extends State {

    private boolean isPause;
    private boolean isReset;

    private boolean isPlayOnlyOnce;

    PlayingState(Player player) {
        super(player);
    }

    PlayingState(Player player, boolean isPlayOnlyOnce){
        this(player);
        this.isPlayOnlyOnce = isPlayOnlyOnce;
    }

    @Override
    void enter() throws InterruptedException {

        synchronized (getPlayer()){
            getPlayer().onPlayingEnter();

            stop:
            while (!getPlayer().haveAllCompleted()){
                while (!getPlayer().areAllWaiting()){
                    getPlayer().wait();
                    if(isPause || isReset) break stop;
                }
                getPlayer().invokeFrameUpdateHandler();
                getPlayer().resetWaitingCount();
                SorterManager.getInstance().forwardAll();

                Thread.sleep(getPlayer().getFrameDeltaMillis());

                if(isPlayOnlyOnce){
                    isPause = true;
                    break;
                }
            }

            if(isPause){
                getPlayer().switchState(new PausedState(getPlayer()));
            } else if(isReset){
                getPlayer().switchState(new ResetState(getPlayer()));
            } else {
                getPlayer().switchState(new EndState(getPlayer()));
            }
        }
    }

    @Override
    void exit() {

    }

    @Override
    void onPlay(boolean isPlayOnlyOnce) {

    }

    @Override
    void onPause() {
        isPause = true;
        synchronized (getPlayer()){
            getPlayer().notifyAll();
        }
    }

    @Override
    void onReset() throws InterruptedException {
        isReset = true;
        synchronized (getPlayer()){
            getPlayer().notifyAll();
        }
    }
}
