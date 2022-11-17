package view;

import algorithms.Pointer;
import controllers.*;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class SortingChart extends Pane {

    private static double CHART_WIDTH = 500;
    private static double CHART_HEIGHT = 500;
    private static double SPACE_RATION = 0.05;
    private static double HEIGHT_UNIT = 1;

    private static Color DEFAULT_COLOR = Color.LIGHTBLUE;
    private static Color[] POINTER_COLORS = {Color.CRIMSON, Color.CORAL, Color.BURLYWOOD, Color.DARKOLIVEGREEN, Color.DARKKHAKI, Color.DARKMAGENTA, Color.DARKSLATEBLUE};


    private SorterType target;
    private Rectangle[] bars;

    private double barWidth;
    private double barGap;


    SortingChart(SorterType target){
        this.target = target;
        Player.getInstance().addFrameUpdateHandler(() -> Platform.runLater(() -> {
            update();
        }));
        Player.getInstance().addOnStartEnterHandler(() -> Platform.runLater(() -> {
            update();
        }));
        SorterManager.getInstance().addOnElementsToSortUpdated(() -> Platform.runLater(() -> {
            update();
        }));
        setPrefWidth(CHART_WIDTH);
        setPrefHeight(CHART_HEIGHT);
        update();
    }


    void update(){
        getChildren().clear();

        int[] targetArray = SorterManager.getInstance().getSorterContent(target).getArray();
        barWidth = ( CHART_WIDTH * (1 - SPACE_RATION) ) / targetArray.length;
        barGap = ( CHART_WIDTH * SPACE_RATION ) / (targetArray.length - 1);

        bars = new Rectangle[targetArray.length];
        for(int i = 0; i < targetArray.length; i++){
            bars[i] = createBar(targetArray[i]);
            bars[i].setX(i * (barWidth + barGap));
            bars[i].setY(CHART_HEIGHT - bars[i].getHeight());
            getChildren().add(bars[i]);
        }

        Pointer[] pointers = SorterManager.getInstance().getSorterContent(target).getPointers();
        for(int indexOfPointer = 0; indexOfPointer < pointers.length; indexOfPointer++){
            Pointer pointer = pointers[indexOfPointer];
            if(pointer.isAuxiliary())
                continue;
            int pElement = pointer.value();
            if(!pointer.isOutOfBound()){
                bars[pElement].setFill(POINTER_COLORS[indexOfPointer % POINTER_COLORS.length]);
            }
        }
    }

    private Rectangle createBar(int value){
        Rectangle bar = new Rectangle(barWidth, value * HEIGHT_UNIT);
        bar.setFill(DEFAULT_COLOR);
        return bar;
    }
}
