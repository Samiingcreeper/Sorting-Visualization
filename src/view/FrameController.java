package view;

import controllers.Player;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

class FrameController extends HBox {

    private ScrollBar sbRate;

    FrameController(){
        sbRate = new ScrollBar();
        sbRate.setMax(100);
        sbRate.setMin(1);
        sbRate.setBlockIncrement(1);
        sbRate.setValue(10);
        sbRate.setPrefWidth(300);
        sbRate.setOnMouseReleased(dragEvent -> {
            Player.getInstance().setFrameDeltaMillis((long) Math.max(1, (0.5 * sbRate.getValue())));
        });
        setSpacing(20);

        setAlignment(Pos.BASELINE_CENTER);
        getChildren().addAll( new SVLabel("Frame Rate"), sbRate);
    }
}
