package view;

import controllers.*;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

class SortingPane extends BorderPane {

    SortingPane(SorterType type){
        Text title = getTitle(type);
        setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        setCenter(new SortingChart(type));
    }

    private Text getTitle(SorterType type){
        String string = "";
        switch (type){
            case INSERTION_SORT: string = "Insertion Sort"; break;
            case QUICK_SORT: string = "Quick Sort"; break;
            case MERGE_SORT: string = "Merge Sort"; break;
        }
        Text title = new Text(string);
        title.setFont(Font.font("Helvetica", FontWeight.MEDIUM, 20));
        title.setFill(Color.DARKGRAY.darker().darker());
        title.setUnderline(true);
        return title;
    }
}
