package validations;

import DTOs.Solution;
import DTOs.SolutionBLD;
import cubes.Cube3x3;
import lombok.Getter;
import methods.BLDs.BLD3X3;

import java.util.ArrayList;

@Getter
public class ElementsValidator {

    private boolean rollingPop;
    private boolean ollParity;
    private boolean pllParity;

    private Cube3x3 cube3x3;
    Solution solutionBLD = new SolutionBLD();

    public ElementsValidator(Cube3x3 cube3x3) {
        this.cube3x3 = cube3x3;
        BLD3X3 bld3X3 = new BLD3X3(cube3x3);
        ArrayList<SolutionBLD> solution = bld3X3.solve('w', 'g');
        cube3x3.makeMoves(solutionBLD.getWholeAlg(solution));
        validateRollingPop();
        validateOllParity();
        validatePllParity();
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

    public void throwExceptions() throws Exception {
        if (rollingPop) {
            throw new Exception("RollingPop");
        }
        if (ollParity) {
            throw new Exception("OllParity");
        }
        if (pllParity) {
            throw new Exception("PllParity");
        }
    }
}
