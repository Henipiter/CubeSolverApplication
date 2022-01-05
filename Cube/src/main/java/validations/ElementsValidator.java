package validations;

import DTOs.Move;
import DTOs.Solution;
import calculations.CalculateMoves;
import cubes.Cube3x3;
import interpretations.Interpretation;
import lombok.Getter;
import methods.BLDs.BLD3X3;

import java.util.ArrayList;

@Getter
public class ElementsValidator {

    private boolean rollingPop;
    private boolean ollParity;
    private boolean pllParity;
    private boolean wrongCenterOrder;
    private boolean wrongVertexColorOrder;

    private final Cube3x3 cube3x3;

    public ElementsValidator(Cube3x3 cube3x3) {

        this.cube3x3 = cube3x3;
        try {
            validateCenters(cube3x3);
            if(!wrongCenterOrder) {
                BLD3X3 bld3X3 = new BLD3X3(cube3x3);
                ArrayList<Solution> solution = bld3X3.solve('w', 'g');
                cube3x3.makeMoves(Solution.getWholeAlg(solution));
                validateRollingPop();
                validateOllParity();
                validatePllParity();
            }
        }
        catch (IndexOutOfBoundsException e){
            wrongVertexColorOrder = true;
        }

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

    public void validateRollingPop() {
        rollingPop = cube3x3.getCube()[0][0] != cube3x3.getCube()[0][2];
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
