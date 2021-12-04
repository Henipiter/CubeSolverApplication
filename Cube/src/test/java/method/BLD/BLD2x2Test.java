package method.BLD;

import DTOs.Algorithm;
import DTOs.InspectMove;
import DTOs.Solution;
import DTOs.SolutionBLD;
import cubes.Cube;
import cubes.Cube2x2;
import methods.BLDs.BLD2X2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BLD2x2Test {

    Cube2x2 cube2x2;
    BLD2X2 bld2X2;

    @BeforeAll
    static void initAll(){
        Algorithm.loadPermutations();
    }

    @Test
    void solveAllCube() {
        String scramble = "U2 R U2 R F' R2 U2 F2 D2";
        cube2x2 = new Cube2x2();
        Solution solutionBLD = new SolutionBLD();
        cube2x2.makeMoves(scramble);
        bld2X2 = new BLD2X2(cube2x2);
        ArrayList<SolutionBLD> solution = bld2X2.solve('y','r');
        cube2x2.makeMoves(solutionBLD.getWholeAlg(solution));
        System.out.println(InspectMove.moveListToString(solutionBLD.getWholeAlg(solution)));
        cube2x2 = new Cube2x2();
        cube2x2.makeMoves(scramble);
        cube2x2.makeMoves(solutionBLD.getWholeAlg(solution));
        Assertions.assertTrue(Cube.isSolved(cube2x2));
    }
}
