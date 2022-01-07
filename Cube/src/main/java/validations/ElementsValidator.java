package validations;

import DTOs.Move;
import DTOs.Solution;
import calculations.CalculateMoves;
import cubes.Cube2x2;
import cubes.Cube3x3;
import interpretations.Interpretation;
import lombok.Getter;
import methods.BLDs.BLD2X2;
import methods.BLDs.BLD3X3;

import java.util.ArrayList;

@Getter
public class ElementsValidator {

    private boolean rollingPop;
    private boolean ollParity;
    private boolean pllParity;
    private boolean wrongCenterOrder;
    private boolean wrongVertexColorOrder;

    private Cube3x3 cube3x3;
    private Cube2x2 cube2x2;

    public ElementsValidator(Cube3x3 cube3x3) {

        this.cube3x3 = cube3x3;

        boolean wrongOrder1;
        boolean wrongOrder2;
        validateCenters(cube3x3);
        if (!wrongCenterOrder) {
            BLD3X3 bld3X3 = new BLD3X3(cube3x3);
            ArrayList<Solution> solution = bld3X3.solve('w', 'g');

            wrongOrder1 = bld3X3.isBadColorOrder();
            cube3x3.makeMoves(Solution.getWholeAlg(solution));
            bld3X3.solve('w', 'g');

            wrongOrder2 = bld3X3.isBadColorOrder();
            wrongVertexColorOrder = wrongOrder1 || wrongOrder2;
            validateRollingPop(cube3x3.getCube());
            validateOllParity();
            validatePllParity();
        }


    }


    public ElementsValidator(Cube2x2 cube2x2) {

        this.cube2x2 = cube2x2;
        boolean wrongOrder1;
        boolean wrongOrder2;

        BLD2X2 bld2X2 = new BLD2X2(cube2x2);
        ArrayList<Solution> solution = bld2X2.solve('w', 'g');
        wrongOrder1 = bld2X2.isBadColorOrder();
        cube2x2.makeMoves(Solution.getWholeAlg(solution));
        bld2X2.solve('w', 'g');

        wrongOrder2 = bld2X2.isBadColorOrder();
        wrongVertexColorOrder = wrongOrder1 || wrongOrder2;
        validateRollingPop(cube2x2.getCube());

    }

    public void validateCenters(Cube3x3 a) {
        Cube3x3 ori = new Cube3x3(a.getCube(), a.getCenter());


        char[] center = Cube3x3.getCentersFromVertices(ori);
        Cube3x3 cube3x3 = new Cube3x3(ori.getCube(), center);

        Move onTop = CalculateMoves.rotateSideToGetItOnTopAlgorithm(Interpretation.getSideWithColor(
                ori.getCenter()[0], cube3x3.getCenter()));
        cube3x3.move(onTop);
        Move onFront = CalculateMoves.getMoveToSetGivenSideOnFrontExceptBottomAndUpperSide(
                Interpretation.getSideWithColor(ori.getCenter()[4], cube3x3.getCenter()));
        cube3x3.move(onFront);

        wrongCenterOrder = false;
        for (int i = 0; i < 6; i++) {
            if (ori.getCenter()[i] != cube3x3.getCenter()[i]) {
                wrongCenterOrder = true;
                break;
            }
        }
    }

    public void validateRollingPop(char[][] cube) {
        rollingPop = cube[0][0] != cube[0][2];
    }

    public void validateOllParity() {
        ollParity = cube3x3.getCube()[0][4] != cube3x3.getCube()[0][0];
    }

    public void validatePllParity() {
        BLD3X3 bld3X3 = new BLD3X3(cube3x3);
        int vertexSolutionSize = bld3X3.solveAllVertices().size();
        int edgeSolutionSize = bld3X3.solveAllEdges().size();
        pllParity = vertexSolutionSize != edgeSolutionSize;
    }
}
