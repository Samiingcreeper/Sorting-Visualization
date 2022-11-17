package view;

import controllers.Player;
import controllers.SorterManager;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import test.RandomIntegers;

class ElementSetter extends HBox {

    private static Color COLOR = Color.DARKGRAY.darker();

    private TextField tfAmount;

    ElementSetter(){

        tfAmount = new TextField();
        tfAmount.setPrefHeight(40);
        tfAmount.setBackground(Background.EMPTY);
        tfAmount.setFont(Font.font("Helvetica", FontWeight.EXTRA_LIGHT, 20));
        tfAmount.setPrefColumnCount(5);
        tfAmount.setBorder(new Border(new BorderStroke(COLOR, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 2, 0))));

        SVButton btnReset = new SVButton("Reset");
        btnReset.setOnAction( actionEvent -> {

            int count = 10;
            try{
                count = Integer.parseInt(tfAmount.getText());
            }catch (NumberFormatException exc){
                System.out.println("exc");
            }

            count = Math.max(3, Math.min(500, count));

            try {
                Player.getInstance().reset();
                SorterManager.getInstance().setElementsToSort(RandomIntegers.getArray(count));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        setSpacing(10);

        getChildren().addAll(new SVLabel("Number Of Elements"), tfAmount, btnReset);
    }


}
