package controllers;

class PausedState extends State {

    private boolean isContinue;
    private boolean isReset;
    private boolean isPlayOnlyOnce;

    PausedState(Player player) {
        super(player);
    }

    @Override
    void enter() throws InterruptedException {

        synchronized (getPlayer()){
            getPlayer().onPausedEnter();

            while (!isContinue && !isReset){
                getPlayer().wait();
            }

            if(isContinue){
                getPlayer().switchState(new PlayingState(getPlayer(), isPlayOnlyOnce));
            }else if(isReset){
                getPlayer().switchState(new ResetState(getPlayer()));
            }
        }
    }

    @Override
    void exit() {

    }

    @Override
    void onPlay(boolean isPlayOnlyOnce) {
        isContinue = true;
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
        isReset = true;
        synchronized (getPlayer()){
            getPlayer().notifyAll();
        }
    }
}
