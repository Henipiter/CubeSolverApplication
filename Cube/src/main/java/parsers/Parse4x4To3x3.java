package parsers;

import cubes.Cube4x4;

public class Parse4x4To3x3 {

    public static char[][] copyFieldsColors(Cube4x4 cube4x4) {
        char[][] cubeArray = new char[6][8];
        setVertices(cube4x4, cubeArray);
        setEdges(cube4x4, cubeArray);
        return cubeArray;
    }

    private static void setVertices(Cube4x4 cube4x4, char[][] cubeArray) {
        int[] vertexOrder3x3 = new int[]{0, 2, 5, 7};
        int[] vertexOrder4x4 = new int[]{0, 3, 12, 15};
        for (int side = 0; side < 6; side++) {
            for (int i = 0; i < 4; i++) {
                cubeArray[side][vertexOrder3x3[i]] = cube4x4.getCube()[side][vertexOrder4x4[i]];
            }
        }
    }

    private static void setEdges(Cube4x4 cube4x4, char[][] cubeArray) {
        int[] edgeOrder3x3 = new int[]{1, 3, 4, 6};
        int[] edgeOrder4x4 = new int[]{1, 4, 7, 13};
        boolean isPairedEdges = is4x4HasPairedEdges(cube4x4);
        for (int side = 0; side < 6; side++) {
            for (int i = 0; i < 4; i++) {
                if (isPairedEdges) {
                    cubeArray[side][edgeOrder3x3[i]] = cube4x4.getCube()[side][edgeOrder4x4[i]];
                } else {
                    cubeArray[side][edgeOrder3x3[i]] = 'x';
                }
            }
        }
    }

    public static char[] copyCentersColors(Cube4x4 cube4x4) {
        char[] centerArray = new char[6];
        boolean isPairedCenters = is4x4HasPairedCenters(cube4x4);
        for (int side = 0; side < 6; side++) {
            if (isPairedCenters) {
                centerArray[side] = cube4x4.getCube()[side][5];
            } else {
                centerArray[side] = 'x';
            }
        }
        return centerArray;
    }

    private static boolean is4x4HasPairedEdges(Cube4x4 cube4x4) {
        int[][] edgeOrder4x4 = new int[][]{{1, 2}, {4, 8}, {7, 11}, {13, 14}};
        for (int side = 0; side < 6; side++) {
            for (int[] edgeField : edgeOrder4x4) {
                if (cube4x4.getCube()[side][edgeField[0]] != cube4x4.getCube()[side][edgeField[1]]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean is4x4HasPairedCenters(Cube4x4 cube4x4) {
        int[] centerOrder4x4 = new int[]{5, 6, 9, 10};
        for (int side = 0; side < 6; side++) {
            for (int i = 1; i < 4; i++) {
                if (cube4x4.getCube()[side][centerOrder4x4[0]] != cube4x4.getCube()[side][centerOrder4x4[i]]) {
                    return false;
                }
            }
        }
        return true;
    }
}
