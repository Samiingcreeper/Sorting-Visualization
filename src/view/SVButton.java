package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


class SVButton extends Button {

    private static double BUTTON_SIZE = 20;
    private static Color COLOR = Color.DARKGRAY.darker().darker();

    SVButton(String content){
        this(content, false);
    }

    SVButton(String content, boolean isSquare){
        setBackground(Background.EMPTY);
        setMaxHeight(BUTTON_SIZE);
        if(isSquare){
            setMaxWidth(BUTTON_SIZE);
        }
        setText(content);
        setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 20));
        setTextFill(COLOR);
        setBorder(new Border(new BorderStroke(COLOR, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
    }

    void setOnClick(EventHandler<ActionEvent> eventHandler){
        setOnAction(eventHandler);
    }
}
