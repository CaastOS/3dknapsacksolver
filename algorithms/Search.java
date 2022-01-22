package algorithms;

import data.ParcelDatabase;

public class Search {

    public static int value = 0;

    /**
    * Place the field in the most deep bottom-left empty spot, used for greedy implementation
    * @param piece the 3D piece to place in the field
    * @param field the field to place the piece in
    * @param id id of the piece (determines the color)
    * @return true if piece is placed, false if piece is not placed
    */

    static public boolean addBottomBackLeftForGreedy(int [][][] field, boolean[][][] piece, int id) {
        for (int iField = field.length - 1; iField >= 0; iField--) { // for every z
            for (int jField = 0; jField < field[iField].length; jField++) { // for every y
                for (int kField = field[iField][jField].length - 1; kField >= 0; kField--) { // for every x

                    int[] coords = {
                        iField,
                        jField,
                        kField
                    };
                        if(isPlaceable(field, piece, coords)) {
                            addPiece(field, piece, coords, id);
                            value += ParcelDatabase.getValue(id);
                            return true;
                        }
                        
                }
            }
        }
        return false;
    }

    /**
    * Place the field in the most deep bottom-left empty spot
    * @param piece the 3D piece to place in the field
    * @param field the field to place the piece in
    * @param id id of the piece (determines the color)
    */

    static public void addFromBottomBackLeft(int [][][] field, boolean[][][] piece, int id) {
        for (int iField = field.length - 1; iField >= 0; iField--) { // for every z
            for (int jField = 0; jField < field[iField].length; jField++) { // for every y
                for (int kField = field[iField][jField].length - 1; kField >= 0; kField--) { // for every x

                    int[] coords = {
                        iField,
                        jField,
                        kField
                    };
                        if(isPlaceable(field, piece, coords)) {
                            addPiece(field, piece, coords, id);
                            value += ParcelDatabase.getValue(id);
                            return;
                        }
                }
            }
        }
    }    

    /**
    * If the piece is placeable, add the piece to the field
    * @param piece the 3D piece to place in the field
    * @param field the field to place the piece in
    * @param coord the array containing the x,y,z where we want to place the piece
    * @param id id of the piece (determines the color)
    */

    static public void addPiece(int[][][] field, boolean[][][] piece, int[] coord, int id) {

        for (int i = 0; i < piece.length; i++) { // for every z
            for (int j = 0; j < piece[i].length; j++) { // for every y
                for (int k = 0; k < piece[i][j].length; k++) { // for every x
                if(piece[i][j][k]) {

                    field[coord[0] + i][coord[1] + j][coord[2] + k] = id; // add the piece to the field
                }
                }
            }
        }
    }

    /**
    * check whether a 3D piece is placable in the field at given coordinates
    * @param piece the 3D piece to place in the field
    * @param coord the array containing the x,y,z where we want to place the piece
    * @param field the field to place the piece in
    * @return true if placeable, false if not placeable
    */

    static public boolean isPlaceable(int[][][] field, boolean[][][] piece, int[] coord) {

        for (int i = 0; i < piece.length; i++) { // for every z
            for (int j = 0; j < piece[i].length; j++) { // for every y
                for (int k = 0; k < piece[i][j].length; k++) { // for every x
                    if(!piece[i][j][k]) {
                        continue;
                    }
                    if (coord[0] + i >= field.length) return false; // if out of bounds
                    if (coord[1] + j >= field[i].length) return false; // if out of bounds
                    if (coord[2] + k >= field[i][j].length) return false; // if out of bounds

                    if (field[coord[0] + i][coord[1] + j][coord[2] + k] != 0) { // if already full
                        return false;
                    }
                }
            }
        }

        return true; // if is placeable
    }

    
    /**
    * generate a field starting from a GA chromosome
    * @param chromosomes chromosome from the GA
    * @param database chosen database
    * @return field
    */

    static public int[][][] generateFieldFromChromosomes(int[][] chromosomes, boolean[][][][][] database) {
        value = 0;
        int[][][] field = new int[33][8][5];
        for(int i = 0; i < chromosomes[0].length; i++) {
            addFromBottomBackLeft(field, database[chromosomes[0][i]][chromosomes[1][i]], chromosomes[0][i] + 1);
        }
        return field;
    }

    /**
    * get piece from database
    * @param i piece index
    * @param r piece rotation
    * @return piece [i][r]
    */

    public boolean[][][] getPiece(int i, int r) {
        return ParcelDatabase.getDatabase()[i][r];
    }

    /**
    * accessor method for value
    * @return value
    */

    public static int getValue() {
        return value;
    }
}