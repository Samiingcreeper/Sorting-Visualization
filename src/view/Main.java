package view;

import controllers.Player;
import controllers.SorterManager;
import controllers.SorterType;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import test.RandomIntegers;

import java.util.Arrays;

public class Main extends Application{

    private boolean isUpdate;

    @Override
    public void start(Stage stage) throws Exception {

        SorterManager.getInstance().setElementsToSort(RandomIntegers.getArray(100));

        HBox sorterPanes = new HBox(50);
        sorterPanes.getChildren().addAll(new SortingPane(SorterType.INSERTION_SORT), new SortingPane(SorterType.MERGE_SORT), new SortingPane(SorterType.QUICK_SORT));

        BorderPane main = new BorderPane();
        main.setCenter(sorterPanes);

        PlayerController playerController = new PlayerController();
        playerController.setAlignment(Pos.CENTER);

        HBox bottom = new HBox(175);
        bottom.setAlignment(Pos.CENTER);
        bottom.getChildren().addAll(new ElementSetter(), new PlayerController(), new FrameController());
        bottom.setPadding(new Insets(15, 0, 0, 0));

        main.setBottom(bottom);
        main.setPadding(new Insets(15));

        main.setBackground(Background.EMPTY);

        stage.setScene(new Scene(main));
        stage.setTitle("Sorting Algorithm Visualizer");
        stage.show();

        Player.getInstance().setFrameDeltaMillis(10);
        //Player.getInstance().play();

    }
}
