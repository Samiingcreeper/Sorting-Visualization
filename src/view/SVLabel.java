package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SVLabel extends Label {

    private static double BUTTON_SIZE = 20;
    private static Color COLOR = Color.DARKGRAY.darker().darker();


    SVLabel(String content){
        setBackground(null);
        setMaxHeight(BUTTON_SIZE);

        setText(content);
        setFont(Font.font("Helvetica", FontWeight.EXTRA_LIGHT, 20));
        setTextFill(COLOR);
        setPadding(new Insets(6));
        setBorder(new Border(new BorderStroke(COLOR, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1.5))));
    }
}
