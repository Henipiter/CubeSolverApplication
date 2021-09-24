package interpretations;

import cubes.Cube1x1;

import static calculations.CalculateMoves.rotateSideToGetItOnTopAlgorithm;

public class Interpretation1x1 {

    private Cube1x1 cube;

    public Interpretation1x1() {
    }

    public void refreshCube(Cube1x1 cube) {
        this.cube = cube;
    }

    public int getSideWithGivenColor(char color) {
        int result = 'w';
        for (int side = 0; side < 6; side++) {
            if (cube.getCube()[side][0] == color) {
                result = side;
                break;
            }
        }
        return result;
    }

    public char whichColorIsNextInOrder(int chosenSide, char leftSideColor, char upperSideColor) {
        int leftSide = getSideWithGivenColor(leftSideColor);
        cube.move(rotateSideToGetItOnTopAlgorithm(leftSide));
        cube.moveUsingString("z'");
        int upperSide = getSideWithGivenColor(upperSideColor);
        cube.move(rotateSideToGetItOnTopAlgorithm(upperSide));
        return cube.getCube()[chosenSide][0];
    }

    public char[] getColorOrder(char upperSideColor) {
        int upperSide = getSideWithGivenColor(upperSideColor);
        cube.move(rotateSideToGetItOnTopAlgorithm(upperSide));
        char[] colorOrder = new char[4];
        int[] readingOrder = new int[]{2, 5, 3, 4};
        for (int i = 0; i < 4; i++) {
            colorOrder[i] = cube.getCube()[readingOrder[i]][0];
        }
        return colorOrder;
    }


}
