package controllers;

public class ResetState extends State{
    ResetState(Player player) {
        super(player);
    }

    @Override
    void enter() throws InterruptedException {
        SorterManager.getInstance().resetAll();
        getPlayer().switchState(new StartState(getPlayer()));
    }

    @Override
    void exit() throws InterruptedException {

    }

    // Test comment for learning git
    @Override
    void onPlay(boolean isPlayOnlyOnce) throws InterruptedException {

    }

    @Override
    void onPause() throws InterruptedException {

    }

    @Override
    void onReset() throws InterruptedException {

    }
}
