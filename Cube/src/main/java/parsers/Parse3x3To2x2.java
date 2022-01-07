package parsers;


import cubes.Cube3x3;

public class Parse3x3To2x2 {

    public static char[][] copyFieldsColors(Cube3x3 cube3x3) {
        char[][] cubeArray = new char[6][4];
        int[] vertexOrder3x3 = new int[]{0, 2, 5, 7};
        int[] vertexOrder2x2 = new int[]{0, 1, 2, 3};
        for (int side = 0; side < 6; side++) {
            for (int i = 0; i < 4; i++) {
                cubeArray[side][vertexOrder2x2[i]] = cube3x3.getCube()[side][vertexOrder3x3[i]];
            }
        }
        return cubeArray;
    }

    public static char[] copyCentersColors(Cube3x3 cube3x3) {
        char[] centerArray = new char[6];
        for (int i = 0; i < 6; i++) {
            centerArray[i] = cube3x3.getCenter()[i];
        }
        return centerArray;
    }
}
