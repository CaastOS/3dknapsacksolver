package algorithms.greedy;
import algorithms.Search;
import data.ParcelDatabase;
import data.PentominoesDatabase;
import gui.ArrayVisualization;
import javafx.application.Platform;

/**
 * This class includes the methods to perform a Greedy search to solve the bin packing / knapsack problem.
 */

public class GreedySearch extends Search implements Runnable {

    static ArrayVisualization visualization;

    public static void setVisualizer(ArrayVisualization arrayVisualization) {
        visualization = arrayVisualization;
    }


    /**
    * Perform a greedy search to find a solution to the 3D bin packing problem,
    * and calculate the value found to find a possible (yet not optimal) solution to the 3D knaspack problem.
    * @param field field to search the solution in
    */

    public void search(int[][][] field) {

        int value1 = Integer.parseInt(ArrayVisualization.value1);
        int value2 = Integer.parseInt(ArrayVisualization.value2);
        int value3 = Integer.parseInt(ArrayVisualization.value3);
        int quantity1 = 0, quantity2 = 0, quantity3 = 0;

        if(!ArrayVisualization.unlimited) {
            quantity1 = Integer.parseInt(ArrayVisualization.quantity1);
            quantity2 = Integer.parseInt(ArrayVisualization.quantity2);
            quantity3 = Integer.parseInt(ArrayVisualization.quantity3);
            
        }

        int[] quantities = {quantity1,quantity2, quantity3 };
        boolean[][][][][] database = ParcelDatabase.getDatabase();

        int[] values = {value1,value2, value3 };
        

              


        if(ArrayVisualization.type.equals("Parcels")) {
            database = ParcelDatabase.getDatabase();
        } else {
            database = PentominoesDatabase.getDatabase();
        }
            
         for (int i = 0; i < 3; i++) { // for every parcel

            int largest = 0;

            for (int k = 0; k < values.length; k++) {
                if (values[k] > values[largest]) largest = k;
            }
              

            for (int j = 0; j < database[largest].length; j++) { // for every rotation
                boolean[][][] pieceToPlace = database[largest][j];

                if(ArrayVisualization.unlimited){
                    boolean isRunning = true;
                    while(isRunning) {
                        isRunning = addBottomBackLeftForGreedy(field, pieceToPlace, largest + 1);
                    }
                    addBottomBackLeftForGreedy(field, pieceToPlace, largest + 1);
                } else {
                    boolean isRunning = true;
                    while(isRunning && quantities[largest] != 0) {
                        isRunning = addBottomBackLeftForGreedy(field, pieceToPlace, largest + 1);
                        quantities[largest]--;
                    }
                }
            }

            values[largest]=0;
        }

        Platform.runLater(new Runnable() { // display solution
            @Override
            public void run() {
                visualization.updateState(field);
                ArrayVisualization.setScore(value);
            }
        });
    }
    
    @Override
    public void run() { // function ran when the thread is called
        value = 0;
        search(new int[33][8][5]);
        visualization.stopButton.fire();
    }

}