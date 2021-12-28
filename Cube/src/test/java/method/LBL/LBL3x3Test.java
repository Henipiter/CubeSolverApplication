package method.LBL;

import DTOs.InspectMove;
import DTOs.Solution;
import cubes.Cube;
import cubes.Cube3x3;
import interpretations.Interpretation3x3Edges;
import interpretations.Interpretation3x3Vertices;
import methods.LBLs.LBL3X3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

public class LBL3x3Test {

    Cube cube;
    LBL3X3 lbl3X3;
    Interpretation3x3Edges interpretation3x3Edges = new Interpretation3x3Edges();
    Interpretation3x3Vertices interpretation3x3Vertices = new Interpretation3x3Vertices();

    @ParameterizedTest
    @CsvSource({
            "w, R D2 F' U2 L D2 L D2 U2 R F2 L' F2 R2 B R F2 L U R F",
            "y,D R2 D2 B2 U' L2 R2 U' R2 F2 R D2 F D' B' U' L2 R F2 D2",
            "y,L B R2 D2 F2 U2 B2 R F2 R2 B2 R' F2 R' U F L' D R2 U F'",
            "w,L B R2 D2 F2 U2 B2 R F2 R2 B2 R' F2 R' U F L' D R2 U F'",
            "w, x2 F B' D2 L D R M2 U2 M2 U2 y U R U' R' U y' L'",
            "y, R D2 F' U2 L D2 L D2 U2 R F2 L' F2 R2 B R F2 L U R F"
    })
    void solve(char color, String scramble) {
        cube = new Cube3x3();
        cube.makeMoves(scramble);
        lbl3X3 = new LBL3X3(cube);
        //when
        ArrayList<Solution> alg = lbl3X3.solve(color);
        //then
        System.out.println("Solution: " + InspectMove.moveListToString(Solution.getWholeAlg(alg)));
        Assertions.assertTrue(Cube.isSolved(cube));
    }

    @Test
    void solveSingle() {
        cube = new Cube3x3();
        cube.makeMoves("U2 R' U L F L2 D' R2 B' R' D2 F2 U2 F L' D L' D2 R' U F'"); //B2 L2 F D2 B' L' F2 R D L2 B D2 L B U2 B' U L2 U2 R2 D'
        lbl3X3 = new LBL3X3(cube);
        //when
        ArrayList<Solution> alg = lbl3X3.solve('w');
        //then
        System.out.println("Solution: " + InspectMove.moveListToString(Solution.getWholeAlg(alg)));
        Assertions.assertTrue(Cube.isSolved(cube));
    }

    @ParameterizedTest
    @CsvSource({

            "F R B' R F2 L U F' U2 F U2 F' D2 F L2 F D2 B R' D, F R' D R' D2 B R D' F",
            "D R2 D2 B2 U' L2 R2 U' R2 F2 R D2 F D' B' U' L2 R F2 D2  ,   L' F' D' F' R B' R'",

            "L B R2 D2 F2 U2 B2 R F2 R2 B2 R' F2 R' U F L' D R2 U F',R' B' D R'",
            "B' U B2 R2 F2 U2 F2 D2 R' F2 R D2 R D B' R F' L2 R D2 F2,F' L B R' D' F D' R",
            "R U L D R, R' D R' L'",

            "D2 L B R' U R L B U F2 L2 B2 U' R2 F2 D' L2 D2 F2 B, B' R' D R' F' D' L"
    })
    void solveCross(String scramble, String expected) {
        cube = new Cube3x3();
        cube.makeMoves(scramble);
        lbl3X3 = new LBL3X3(cube);
        lbl3X3.solveCross('y');
        interpretation3x3Edges.interpretEdges(cube);

    }

    @ParameterizedTest
    @CsvSource({
            "x2 F B' D2 L D R M2 U2 M2 U2 y U R U' R' U y' L' R' B D' F R",
            "M2 D2 M2",
            "R D R' D' R",
            "U",
            "D",
            "D2 L B R' U R L B U F2 L2 B2 U' R2 F2 D' L2 D2 F2 B B' R' D R' F' D' L"
    })
    void solveIncorrectCross(String scramble) {
        cube = new Cube3x3();
        cube.makeMoves(scramble);
        lbl3X3 = new LBL3X3(cube);
        Solution alg = lbl3X3.solveIncorrectCross();
        interpretation3x3Edges.interpretEdges(cube);
        System.out.println("Solution: " + InspectMove.moveListToString(alg.getAlgorithm()));

        Assertions.assertAll(
                () -> Assertions.assertTrue(interpretation3x3Edges.isCorrectCross())
        );
    }

    @ParameterizedTest
    @CsvSource({
            "D2 L B R' U R L B U F2 L2 B2 U' R2 F2 D' L2 D2 F2 B B' R' D R' F' D' L y' R D R' D' R, U' R U2 R' U' R U R' U' y U R U' R' U y R U R' y R U R' U' R U2 R' U' R U R'",
            "R U R' L' U' L R U R', L' U' L U U' R U R' L' U' L",
    })
    void solveFirstLayer(String scramble, String expected) {
        cube = new Cube3x3();
        cube.makeMoves(scramble);
        lbl3X3 = new LBL3X3(cube);
        //when
        ArrayList<Solution> alg = lbl3X3.solveFirstLayer();
        //then
        interpretation3x3Vertices.interpretVertices(cube);
        System.out.println("Solution: " + InspectMove.moveListToString(Solution.getWholeAlg(alg)));

        Assertions.assertAll(
                () -> Assertions.assertTrue(interpretation3x3Vertices.isFirstLayerComplete()),
                () -> Assertions.assertEquals(expected, InspectMove.moveListToString(Solution.getWholeAlg(alg)))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "R2 U2 R2 U2 R2, U R U' R' F R' F' R U y U R U' R' F R' F' R U' y' U R U' R' F R' F' R",
            "D2 L B R' U R L B U F2 L2 B2 U' R2 F2 D' L2 D2 F2 B B' R' D R' F' D' L y' R D R' D' R U' R U2 R' U' R U R' U' y U R U' R' U y R U R' y R U R' U' R U2 R' U' R U R', U2 y' U R U' R' F R' F' R U' y' U' L' U L F' L F L' U2 U R U' R' F R' F' R",

    })
    void solveSecondLayer(String scramble, String expected) {
        cube = new Cube3x3();
        cube.makeMoves(scramble);
        lbl3X3 = new LBL3X3(cube);
        //when
        ArrayList<Solution> alg = lbl3X3.solveSecondLayer();
        //then
        interpretation3x3Edges.interpretEdges(cube);
        System.out.println("Solution: " + InspectMove.moveListToString(Solution.getWholeAlg(alg)));

        Assertions.assertAll(
                () -> Assertions.assertTrue(interpretation3x3Edges.isSecondLayerComplete()),
                () -> Assertions.assertEquals(expected, InspectMove.moveListToString(Solution.getWholeAlg(alg)))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "D2 L B R' U R L B U F2 L2 B2 U' R2 F2 D' L2 D2 F2 B B' R' D R' F' D' L y' R D R' D' R U' R U2 R' U' R U R' U' y U R U' R' U y R U R' y R U R' U' R U2 R' U' R U R' U2 y' U R U' R' F R' F' R U' y' U' L' U L F' L F L' U2 U R U' R' F R' F' R, F R U R' U' F' U2 F R U R' U' R U R' U' F'",
            "R2 U2 R2 U2 R2 U R U' R' F R' F' R U y U R U' R' F R' F' R U' y' U R U' R' F R' F' R U, U F R U R' U' F'",
    })
    void solveUpperCross(String scramble, String expected) {
        cube = new Cube3x3();
        cube.makeMoves(scramble);
        lbl3X3 = new LBL3X3(cube);
        //when
        Solution alg = lbl3X3.solveUpperCross();
        //then
        interpretation3x3Edges.interpretEdges(cube);
        System.out.println("Solution: " + InspectMove.moveListToString(alg.getAlgorithm()));
        Assertions.assertAll(
                () -> Assertions.assertTrue(interpretation3x3Edges.isCrossOnUpperSideIsComplete()),
                () -> Assertions.assertEquals(expected, InspectMove.moveListToString(alg.getAlgorithm()))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "R U R' U' R' F R2 U' R' U' R U R' F', R U R' U R U2 R' U U' y' R U R' U R U2 R' U",
            "R U2 R' U' R U2 L' U R' U' L, y R U R' U R U2 R' U",
    })
    void solveIncorrectUpperCross(String scramble, String expected) {
        cube = new Cube3x3();
        cube.makeMoves(scramble);
        lbl3X3 = new LBL3X3(cube);
        //when
        Solution alg = lbl3X3.solveIncorrectUpperCross();
        //then
        interpretation3x3Edges.interpretEdges(cube);
        System.out.println("Solution: " + InspectMove.moveListToString(alg.getAlgorithm()));
        Assertions.assertAll(
                () -> Assertions.assertTrue(interpretation3x3Edges.isUpperCrossIsCorrect()),
                () -> Assertions.assertEquals(expected, InspectMove.moveListToString(alg.getAlgorithm()))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "x R' U R' D2 R U' R' D2 R2 x', y' L' U R U' L U R' U' L' U R U' L U R' U'",
            "x' R U' R' D R U R' D' R U R' D R U' R' D' x, L' U R U' L U R' U' y2 L' U R U' L U R' U' L' U R U' L U R' U'",

    })
    void solveNotPermutedVertexes(String scramble, String expected) {
        cube = new Cube3x3();
        cube.makeMoves(scramble);
        lbl3X3 = new LBL3X3(cube);
        //when
        Solution alg = lbl3X3.solveNotPermutedVertexes();
        //then
        interpretation3x3Vertices.interpretVertices(cube);
        System.out.println("Solution: " + InspectMove.moveListToString(alg.getAlgorithm()));
        Assertions.assertAll(
                () -> Assertions.assertTrue(interpretation3x3Vertices.isAllVerticesInRightPlace()),
                () -> Assertions.assertEquals(expected, InspectMove.moveListToString(alg.getAlgorithm()))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "R U R' U R U2 R' U d, U R' D R D' R' D R D' R' D R D' R' D R D' U R' D R D' R' D R D' R' D R D' R' D R D' U R' D R D' R' D R D' R' D R D' R' D R D' U",

    })
    void solveNotOrientedVertexes(String scramble, String expected) {
        cube = new Cube3x3();
        cube.makeMoves(scramble);
        lbl3X3 = new LBL3X3(cube);
        //when
        Solution alg = lbl3X3.solveNotOrientedVertexes();
        //then
        interpretation3x3Vertices.interpretVertices(cube);
        System.out.println("Solution: " + InspectMove.moveListToString(alg.getAlgorithm()));
        Assertions.assertAll(
                () -> Assertions.assertTrue(interpretation3x3Vertices.isAllVertexesInRightOrientation()),
                () -> Assertions.assertEquals(expected, InspectMove.moveListToString(alg.getAlgorithm()))
        );
    }

    @Test
    void aasolveCross() {
        char[][] input = new char[][]{
                {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
                {'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r'},
                {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'y', 'y', 'y', 'y', 'y', 'y', 'y', 'y'},
                {'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g'},
                {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'},
        };
        char[] center = new char[]{'w', 'r', 'o', 'y', 'g', 'b'};

        cube = new Cube3x3(input, center);
        cube.makeMoves("M2 S2 E2");
        lbl3X3 = new LBL3X3(cube);
        //when
        ArrayList<Solution> alg = lbl3X3.solve('w');
        //then
        cube.makeMoves(Solution.getWholeAlg(alg));
        System.out.println("Solution: " + InspectMove.moveListToString(Solution.getWholeAlg(alg)));


    }
}
