package cubes.cube3x3.method.LBL;

import DTOs.InspectMove;
import cubes.Cube;
import cubes.Cube3x3;
import interpretations.Interpretation3x3Edges;
import methods.LBLs.LBL3X3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

public class CrossSolverTest {

    Cube cube;
    LBL3X3 lbl3X3;
    Interpretation3x3Edges interpretation3x3Edges = new Interpretation3x3Edges();

    @ParameterizedTest
    @CsvSource({ "D R2 D2 B2 U' L2 R2 U' R2 F2 R D2 F D' B' U' L2 R F2 D2  ,   L' F' D' F' R B' R'",

            "L B R2 D2 F2 U2 B2 R F2 R2 B2 R' F2 R' U F L' D R2 U F',R' B' D R'",
            "B' U B2 R2 F2 U2 F2 D2 R' F2 R D2 R D B' R F' L2 R D2 F2,F' L B R' D' F D' R",
            "R U L D R, R' D R' L'"
    })
    void solveCross(String scramble, String expected){
        cube = new Cube3x3();
        cube.makeMovesUsingString(scramble);
        lbl3X3 = new LBL3X3(cube);
        ArrayList<InspectMove> alg = lbl3X3.solveCross('y');
        interpretation3x3Edges.interpretEdges(cube);
        System.out.println(InspectMove.algorithmToString(alg));
        Assertions.assertAll(
                ()->Assertions.assertTrue(interpretation3x3Edges.isSolvedCross('y')),
                ()->Assertions.assertEquals(expected, InspectMove.algorithmToString(alg))

        );


    }
}
