package parsers;

import cubes.Cube;
import cubes.Cube2x2;
import cubes.Cube3x3;

public class Parse2x2To3x3 {

    private Cube2x2 cube2x2;

    public Parse2x2To3x3(Cube cube2x2) {
        this.cube2x2 = (Cube2x2) cube2x2;
    }

    public Cube3x3 parseTo3x3(){
        Cube3x3 cube3x3 = new Cube3x3(copyFieldsColors(cube2x2));
        cube3x3.setCenter(copyCentersColors(cube2x2));
        return cube3x3;
    }

    public static char[][] copyFieldsColors(Cube2x2 cube2x2) {
        char[][] cubeArray = new char[6][8];
        int[] vertexOrder3x3 = new int[]{0, 2, 5, 7};
        int[] vertexOrder2x2 = new int[]{0, 1, 2, 3};
        int[] edgeOrder3x3 = new int[]{1, 3, 4, 6};
        for (int side = 0; side < 6; side++) {
            for (int i = 0; i < 4; i++) {
                cubeArray[side][vertexOrder3x3[i]] = cube2x2.getCube()[side][vertexOrder2x2[i]];
                cubeArray[side][edgeOrder3x3[i]] = 'x';
            }
        }
        return cubeArray;
    }

    public static char[] copyCentersColors(Cube2x2 cube2x2) {
        char[] centerArray = new char[6];
        System.arraycopy(cube2x2.getCenter(), 0, centerArray, 0, 6);
        return centerArray;
    }

}
