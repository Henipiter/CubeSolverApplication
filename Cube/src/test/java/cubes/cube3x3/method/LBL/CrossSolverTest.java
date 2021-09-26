package cubes.cube3x3.method.LBL;

import DTOs.InspectMove;
import cubes.Cube;
import cubes.Cube3x3;
import interpretations.Interpretation3x3Edges;
import interpretations.Interpretation3x3Vertices;
import methods.LBLs.LBL3X3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

public class CrossSolverTest {

    Cube cube;
    LBL3X3 lbl3X3;
    Interpretation3x3Edges interpretation3x3Edges = new Interpretation3x3Edges();
    Interpretation3x3Vertices interpretation3x3Vertices = new Interpretation3x3Vertices();

    @ParameterizedTest
    @CsvSource({"D R2 D2 B2 U' L2 R2 U' R2 F2 R D2 F D' B' U' L2 R F2 D2  ,   L' F' D' F' R B' R'",

            "L B R2 D2 F2 U2 B2 R F2 R2 B2 R' F2 R' U F L' D R2 U F',R' B' D R'",
            "B' U B2 R2 F2 U2 F2 D2 R' F2 R D2 R D B' R F' L2 R D2 F2,F' L B R' D' F D' R",
            "R U L D R, R' D R' L'",
            "D2 L B R' U R L B U F2 L2 B2 U' R2 F2 D' L2 D2 F2 B, B' R' D R' F' D' L"
    })
    void solveCross(String scramble, String expected) {
        cube = new Cube3x3();
        cube.makeMovesUsingString(scramble);
        lbl3X3 = new LBL3X3(cube);
        ArrayList<InspectMove> alg = lbl3X3.solveCross('y');
        interpretation3x3Edges.interpretEdges(cube);
        System.out.println(InspectMove.algorithmToString(alg));
        Assertions.assertAll(
                () -> Assertions.assertTrue(interpretation3x3Edges.isSolvedCross()),
                () -> Assertions.assertEquals(expected, InspectMove.algorithmToString(alg))

        );
    }

    @ParameterizedTest
    @CsvSource({
            "M2 D2 M2",
            "R D R' D' R",
            "U",
            "D",
            "D2 L B R' U R L B U F2 L2 B2 U' R2 F2 D' L2 D2 F2 B B' R' D R' F' D' L"
    })
    void solveIncorrectCross(String scramble) {
        cube = new Cube3x3();
        cube.makeMovesUsingString(scramble);
        lbl3X3 = new LBL3X3(cube);
        ArrayList<InspectMove> alg = lbl3X3.solveIncorrectCross();
        interpretation3x3Edges.interpretEdges(cube);
        System.out.println(InspectMove.algorithmToString(alg));
        Assertions.assertAll(
                () -> Assertions.assertTrue(interpretation3x3Edges.isCorrectCross())
        );
    }

    @ParameterizedTest
    @CsvSource({
            "D2 L B R' U R L B U F2 L2 B2 U' R2 F2 D' L2 D2 F2 B B' R' D R' F' D' L y' R D R' D' R, U' R U2 R' U' R U R' U' y U R U' R' U y R U R' y R U R' U' R U2 R' U' R U R'",
            "R U R' L' U' L R U R', L' U' L R U R' L' U' L",
    })
    void solveFirstLayer(String scramble, String expected) {
        cube = new Cube3x3();
        cube.makeMovesUsingString(scramble);
        lbl3X3 = new LBL3X3(cube);
        //when
        ArrayList<InspectMove> alg = lbl3X3.solveFirstLayer();
        //then
        interpretation3x3Vertices.interpretVertices(cube);
        System.out.println(InspectMove.algorithmToString(alg));
        Assertions.assertAll(
                () -> Assertions.assertTrue(interpretation3x3Vertices.isFirstLayerComplete()),
                () -> Assertions.assertEquals(expected, InspectMove.algorithmToString(alg))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "R2 U2 R2 U2 R2, U R U' R' F R' F' R U y U R U' R' F R' F' R U' y' U R U' R' F R' F' R",
            "D2 L B R' U R L B U F2 L2 B2 U' R2 F2 D' L2 D2 F2 B B' R' D R' F' D' L y' R D R' D' R U' R U2 R' U' R U R' U' y U R U' R' U y R U R' y R U R' U' R U2 R' U' R U R', U2 y' U R U' R' F R' F' R U' y' U' L' U L F' L F L' U2 U R U' R' F R' F' R",

    })
    void solveSecondLayer(String scramble, String expected) {
        cube = new Cube3x3();
        cube.makeMovesUsingString(scramble);
        lbl3X3 = new LBL3X3(cube);
        //when
        ArrayList<InspectMove> alg = lbl3X3.solveSecondLayer();
        //then
        interpretation3x3Edges.interpretEdges(cube);
        System.out.println(InspectMove.algorithmToString(alg));
        Assertions.assertAll(
                () -> Assertions.assertTrue(interpretation3x3Edges.isSecondLayerComplete()),
                () -> Assertions.assertEquals(expected, InspectMove.algorithmToString(alg))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "D2 L B R' U R L B U F2 L2 B2 U' R2 F2 D' L2 D2 F2 B B' R' D R' F' D' L y' R D R' D' R U' R U2 R' U' R U R' U' y U R U' R' U y R U R' y R U R' U' R U2 R' U' R U R' U2 y' U R U' R' F R' F' R U' y' U' L' U L F' L F L' U2 U R U' R' F R' F' R, F R U R' U' F' U2 F R U R' U' R U R' U' F'",
            "R2 U2 R2 U2 R2 U R U' R' F R' F' R U y U R U' R' F R' F' R U' y' U R U' R' F R' F' R U, U F R U R' U' F'",
    })
    void solveUpperCross(String scramble, String expected) {
        cube = new Cube3x3();
        cube.makeMovesUsingString(scramble);
        lbl3X3 = new LBL3X3(cube);
        //when
        ArrayList<InspectMove> alg = lbl3X3.solveUpperCross();
        //then
        interpretation3x3Edges.interpretEdges(cube);
        System.out.println(InspectMove.algorithmToString(alg));
        Assertions.assertAll(
                () -> Assertions.assertTrue(interpretation3x3Edges.isCrossOnUpperSideIsComplete()),
                () -> Assertions.assertEquals(expected, InspectMove.algorithmToString(alg))
        );
    }

}
