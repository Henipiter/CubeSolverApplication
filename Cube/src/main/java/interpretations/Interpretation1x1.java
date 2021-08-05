package interpretations;

import DTOs.InspectMove;
import calculations.CalculateMoves;
import cubes.Cube;
import cubes.Cube1x1;

public class Interpretation1x1 {

    private Cube1x1 cube;

    public Interpretation1x1(){
    }

    public void refreshCube(Cube1x1 cube){
        this.cube = cube;
    }

    public int getSideWithGivenColor(char color){
        int result='w';
        for (int side = 0; side < 6; side++) {
            if (cube.getCube()[side][0] == color) {
                result = side;
                break;
            }
        }
        return result;
    }

    public char whichColorIsNextInOrder(int choosenSide, char leftSideColor, char upperSideColor) {
        CalculateMoves calculate = new CalculateMoves();
        int leftSide = getSideWithGivenColor(leftSideColor);

        cube.move(calculate.rotateSideToGetItOnTopAlgorithm(leftSide));


        cube.moveUsingString("Z'");
        int upperSide = getSideWithGivenColor(upperSideColor);
        cube.move(calculate.rotateSideToGetItOnTopAlgorithm(upperSide));
        return cube.getCube()[choosenSide][0];
    }



}
