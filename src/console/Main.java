package console;

import controllers.*;
import test.RandomIntegers;

import java.util.Arrays;
import java.util.Scanner;

class Main extends Thread{

    private static int i;

    public static void main(String[] args) throws InterruptedException {

        int[] elementsToSort = RandomIntegers.getArray(5);
//        int[] elementsToSort = {0, 3, 4, 1, 2};

        SorterManager.getInstance().setElementsToSort(elementsToSort);

//        Player.getInstance().play();

        Player.getInstance().addFrameUpdateHandler(() -> {
            System.out.println("========== " + i + "th Array Access ==========");
            System.out.println("Insertion ->> " + Arrays.toString(SorterManager.getInstance().getSorterContent(SorterType.INSERTION_SORT).getArray()));
            System.out.println("Quick ->> " + Arrays.toString(SorterManager.getInstance().getSorterContent(SorterType.QUICK_SORT).getArray()));
            System.out.println("Merge ->> " + Arrays.toString(SorterManager.getInstance().getSorterContent(SorterType.MERGE_SORT).getArray()));
            System.out.println("\n\n");
            i++;
            try{
                Thread.sleep(500);
            }catch (InterruptedException exc){
                exc.printStackTrace();
            }
        });

        Player.getInstance().addOnStartEnterHandler(() -> {
            System.out.println("========== | Start | ==========");
            System.out.println("Insertion ->> " + Arrays.toString(SorterManager.getInstance().getSorterContent(SorterType.INSERTION_SORT).getArray()));
            System.out.println("Quick ->> " + Arrays.toString(SorterManager.getInstance().getSorterContent(SorterType.QUICK_SORT).getArray()));
            System.out.println("Merge ->> " + Arrays.toString(SorterManager.getInstance().getSorterContent(SorterType.MERGE_SORT).getArray()));
            System.out.println("\n\n");
        });

        Player.getInstance().addOnPlayingEnterHandler(() -> {
            System.out.println("->> Playing State Entered");
        });

        Player.getInstance().addOnPausedEnterHandler(() -> {
            System.out.println("->> Paused State Entered");
        });

        Player.getInstance().addOnEndEnterHandler(() -> {
            System.out.println("->> End State Entered");
        });


        //boolean isPaused = false;
        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine();
            switch (input){
                case "t": Player.getInstance().reset(); i = 0; break;
                case "r": Player.getInstance().play(); break;
                case "p": Player.getInstance().pause(); break;
            }
        }


    }
}
