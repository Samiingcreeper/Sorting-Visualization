package view;

import controllers.Player;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

class PlayerController extends HBox {

    PlayerController(){
        setSpacing(10);
        SVButton btnPlay = new SVButton(">");
        btnPlay.setOnAction(actionEvent -> {
            try {
                Player.getInstance().play();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        SVButton btnNextFrame = new SVButton(">|");
        btnNextFrame.setOnAction(actionEvent -> {
            try{
                Player.getInstance().playNextFrame();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        SVButton btnPause = new SVButton("||");
        btnPause.setOnAction(actionEvent -> {
            try {
                Player.getInstance().pause();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        SVButton btnReset = new SVButton("<<");
        btnReset.setOnAction(actionEvent -> {
            try{
                Player.getInstance().reset();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        getChildren().addAll(btnReset, btnPlay, btnPause, btnNextFrame);
    }

}
