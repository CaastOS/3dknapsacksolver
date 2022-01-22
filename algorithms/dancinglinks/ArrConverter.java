package algorithms.dancinglinks;

public class ArrConverter {
    int width = 2;
    int height = 2;
    int depth = 2;

    /**
    * convert the field from 1D to 3D
    * @param a field
    * @param width width of 3D
    * @param height height of 3D
    * @param depth depth of 3D
    * @return 3D field
    */

    public static boolean[][][] OneDto3D (boolean[] a, int width, int height, int depth)
    {

        boolean[][][] array3 = new boolean[depth][height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < depth; z++) {

                    array3[z][y][x] = a[height * depth * x + depth * y + z];
                }
            }
        }
        return array3;
    }

    /**
    * convert the field from 3D to 1D
    * @param array3 3D array
    * @param width width of 3D
    * @param height height of 3D
    * @param depth depth of 3D
    * @return 1D field
    */

    public static boolean[] ThreeDto1D (boolean[][][] array3, int width, int height, int depth) {
        boolean[] array1 = new boolean[width * height * depth];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < depth; z++) {
                    array1[height * depth * x + depth * y + z] = array3[z][y][x];
                }
            }
        }

        return array1;
    }
}