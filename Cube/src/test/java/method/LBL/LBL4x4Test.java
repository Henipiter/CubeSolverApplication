package method.LBL;

import DTOs.InspectMove;
import DTOs.Move;
import DTOs.Solution;
import DTOs.SolutionLBL;
import cubes.Cube;
import cubes.Cube4x4;
import interpretations.Interpretation4x4Centers;
import methods.LBLs.LBL4X4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

public class LBL4x4Test {

    Cube cube = new Cube4x4();
    Interpretation4x4Centers interpretation = new Interpretation4x4Centers();

    @Test
    public void call_addToAlgorithmAndUpdateCubeStuff_and_check_correctness() {
        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<Move> alg = new ArrayList<>();
        alg.add(new Move("R"));
        alg.add(new Move("U"));
        ArrayList<Move> mainAlg = new ArrayList<>();
        ArrayList<Move> expected = new ArrayList<>();
        expected.add(new Move("R"));
        expected.add(new Move("U"));
        expected.add(new Move("R"));
        expected.add(new Move("U"));
        lbl.addToAlgorithmAndUpdateCubeStuff(alg, mainAlg);
        lbl.addToAlgorithmAndUpdateCubeStuff(alg, mainAlg);
        Assertions.assertEquals(expected, mainAlg);
    }

    @ParameterizedTest
    @CsvSource({"w, z' Lw' U' Lw Rw2 U Rw2",
            "y, z Lw2 U' Lw2 y2 Lw' U Lw",
            "g, x' y' Rw U' Rw' y' U2 Rw U' Rw'",
            "b, x y Rw U Rw' y U2 Rw U Rw'",
            "r, x2 Lw' U Lw Rw2 U' Rw2",
            "o, Lw' U' Lw Rw2 U Rw2"})
    public void call_solveFirstCenter_with_short_scramble(char firstCenterColor, String expectedAlg) {
        cube.makeMoves("r u l d r");

        LBL4X4 lbl = new LBL4X4(cube);
        SolutionLBL algorithm = lbl.solveFirstCenter(firstCenterColor);
        interpretation.interpretCenters(cube);
        System.out.println("Solution: " + InspectMove.moveListToString(algorithm.getAlgorithm()));
        Assertions.assertAll(
                () -> Assertions.assertTrue(interpretation.isWholeCenterInOneColor(0)),
                () -> Assertions.assertEquals(expectedAlg, InspectMove.moveListToString(algorithm.getAlgorithm()))
        );

    }

    @ParameterizedTest
    @CsvSource({"w, Lw2 U Lw2 y U' Rw U Rw' y U2 Lw' U' Lw",
            "y, z Lw' U Lw y U Rw U Rw'",
            "g, x2 y U2 Rw U Rw' y U' Rw U Rw'",
            "b, Rw U2 Rw' U' Rw U Rw'",
            "r, x' y Lw' U Lw ",
            "o, z U2 Lw2 U Lw2 y' U' Rw U Rw'"})
    public void call_solveFirstCenter_with_random_scramble1(char firstCenterColor, String expectedAlg) {
        cube.makeMoves("r' d R D2 U' B' r' B' D' d");

        LBL4X4 lbl = new LBL4X4(cube);
        SolutionLBL algorithm = lbl.solveFirstCenter(firstCenterColor);
        System.out.println("Solution: " + InspectMove.moveListToString(algorithm.getAlgorithm()));
        interpretation.interpretCenters(cube);
        Assertions.assertAll(
                () -> Assertions.assertTrue(interpretation.isWholeCenterInOneColor(0)),
                () -> Assertions.assertEquals(expectedAlg, InspectMove.moveListToString(algorithm.getAlgorithm()))
        );
    }

    @ParameterizedTest
    @CsvSource({"w, x2 y U2 Rw U' Rw' y Rw U Rw'",
            "y, y' Lw' U Lw y' Lw' U' Lw",
            "g, U2 Rw U' Rw' Rw2 U' Rw2 y' U2 Rw U' Rw'",
            "b, z' y U2 Lw' U' Lw y U' Lw' U' Lw",
            "r, x y U2 Rw U' Rw' y U2 Lw' U Lw ",
            "o, z y2 U2 Lw' U2 Lw"})
    public void call_solveFirstCenter__with_random_scramble2(char firstCenterColor, String expectedAlg) {
        cube.makeMoves("d B' d l D U' b2 d L2 B2");
        LBL4X4 lbl = new LBL4X4(cube);
        SolutionLBL algorithm = lbl.solveFirstCenter(firstCenterColor);
        System.out.println("Solution: " + InspectMove.moveListToString(algorithm.getAlgorithm()));
        interpretation.interpretCenters(cube);
        Assertions.assertAll(
                () -> Assertions.assertTrue(interpretation.isWholeCenterInOneColor(0)),
                () -> Assertions.assertEquals(expectedAlg, InspectMove.moveListToString(algorithm.getAlgorithm()))
        );
    }

    @ParameterizedTest
    @CsvSource({"w, z Rw U Rw' Rw2 U Rw2 y2 Rw U Rw'",
            "y, x2 U' Lw2 U' Lw2 y' U Rw U' Rw'",
            "g, Lw' U Lw Lw2 U Lw2 y' U Rw U' Rw'",
            "b, z' y' Rw U' Rw' y' Lw' U' Lw ",
            "r, z Lw' U Lw U' Rw2 U' Rw2 y2 U2 Lw' U' Lw",
            "o, Rw U Rw' Rw2 U Rw2 y2 U' Rw U' Rw'"})
    public void call_solveFirstCenter__with_random_scramble3(char firstCenterColor, String expectedAlg) {
        cube.makeMoves("b2 l U2 F2 L2 d' B' b' L B'");

        LBL4X4 lbl = new LBL4X4(cube);
        SolutionLBL algorithm = lbl.solveFirstCenter(firstCenterColor);
        System.out.println("Solution: " + InspectMove.moveListToString(algorithm.getAlgorithm()));
        interpretation.interpretCenters(cube);
        Assertions.assertAll(
                () -> Assertions.assertTrue(interpretation.isWholeCenterInOneColor(0)),
                () -> Assertions.assertEquals(expectedAlg, InspectMove.moveListToString(algorithm.getAlgorithm()))
        );
    }

    @Test
    public void call_solveSecondCenter_with_short_scramble() {
        cube.makeMoves("r u l d r");
        cube.makeMoves("z' Lw' U' Lw Rw2 U Rw2");

        LBL4X4 lbl = new LBL4X4(cube);
        SolutionLBL algorithm = lbl.solveSecondCenter();
        System.out.println("Solution: " + InspectMove.moveListToString(algorithm.getAlgorithm()));
        interpretation.interpretCenters(cube);
        int countWhiteFields = interpretation.countFieldWithGivenColor(0, interpretation.getColorOfOppositeSide(1));
        Assertions.assertEquals(4, countWhiteFields);
    }

    @Test
    public void call_solveThirdCenter_with_short_scramble() {
        cube.makeMoves("r u l d r");
        cube.makeMoves("z' Lw' U' Lw Rw2 U Rw2");
        cube.makeMoves("x2 U' Lw' U Lw y2 U2 Rw U Rw'");

        LBL4X4 lbl = new LBL4X4(cube);
        SolutionLBL algorithm = lbl.solveThirdCenter();
        System.out.println("Solution: " + InspectMove.moveListToString(algorithm.getAlgorithm()));
        interpretation.interpretCenters(cube);
        boolean result = interpretation.isWholeCenterInOneColor(0);
        Assertions.assertTrue(result);
    }

    @Test
    public void call_solveFourthCenter_with_short_scramble() {
        cube.makeMoves("r u l d r");
        cube.makeMoves("z' Lw' U' Lw Rw2 U Rw2");
        cube.makeMoves("x2 U' Lw' U Lw y2 U2 Rw U Rw'");
        cube.makeMoves("z x U' Rw U Rw'");

        LBL4X4 lbl = new LBL4X4(cube);
        SolutionLBL algorithm = lbl.solveFourthCenter();
        System.out.println("Solution: " + InspectMove.moveListToString(algorithm.getAlgorithm()));
        interpretation.interpretCenters(cube);
        boolean result = interpretation.isWholeCenterInOneColor(0);
        Assertions.assertTrue(result);
    }

    @Test
    public void call_solveFifthCenter_with_short_scramble() {
        cube.makeMoves("r u l d r");
        cube.makeMoves("z' Lw' U' Lw Rw2 U Rw2");
        cube.makeMoves("x2 U' Lw' U Lw y2 U2 Rw U Rw'");
        cube.makeMoves("z x U' Rw U Rw'");
        cube.makeMoves("x Rw U Rw' D Lw2 U2 Lw2");

        LBL4X4 lbl = new LBL4X4(cube);
        SolutionLBL algorithm = lbl.solveLastTwoCenters();
        System.out.println("Solution: " + InspectMove.moveListToString(algorithm.getAlgorithm()));
        interpretation.interpretCenters(cube);
        boolean result = interpretation.isWholeCenterInOneColor(0);
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({"w, z' Lw' U' Lw Rw2 U Rw2 x2 U' Lw' U Lw y2 U2 Rw U Rw' z x U' Rw U Rw' x Rw U Rw' D Lw2 U2 Lw2 x U' Rw U Rw'",
            "y, z Lw2 U' Lw2 y2 Lw' U Lw x2 U' Lw' U' Lw y2 U2 Rw U' Rw' z Lw' U Lw Rw2 U' Rw2 x Lw' U Lw Lw2 U Lw2 U Rw2 U Rw2 x U F Rw U2 Rw'",
            "g, x' y' Rw U' Rw' y' U2 Rw U' Rw' x2 U2 Rw U Rw' y' U Rw U Rw' z U' Lw2 U Lw2 y2 U' Lw' U' Lw x Lw' U Lw Rw2 U' Rw2 U2 Lw2 U' Lw2 x U2 Lw' U' Lw",
            "b, x y Rw U Rw' y U2 Rw U Rw' x2 U2 Rw U' Rw' y U' Rw U' Rw' z Lw' U' Lw U Rw2 U Rw2 x Lw' U Lw Rw2 U' Rw2 U2 Lw2 U' Lw2 x U' Lw' U2 Lw U Lw' U' Lw",
            "r, x2 Lw' U Lw Rw2 U' Rw2 x2 U' Lw' U' Lw y2 U2 Rw U' Rw' z Lw2 U Lw2 y2 U' Rw U Rw' x Lw' U' Lw U D Lw2 U2 Lw2 x U2 Rw U' Rw'",
            "o, Lw' U' Lw Rw2 U Rw2 x2 U' Lw' U Lw y2 U2 Rw U Rw' z x y2 U' Rw U Rw' x Lw' U' Lw U D Lw2 U2 Lw2 x Rw U Rw'"})
    public void call_solveWholeCenters_with_short_scramble(char firstCenterColor, String expectedAlg) {

        cube.makeMoves("r u l d r");
        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<SolutionLBL> algorithm = lbl.solveCenters(firstCenterColor);
        interpretation.interpretCenters(cube);
        System.out.println("Solution: " + InspectMove.moveListToString(Solution.getWholeAlg(algorithm)));
        for (int i = 0; i < 6; i++) {
            Assertions.assertTrue(interpretation.isWholeCenterInOneColor(i));
        }
        Assertions.assertEquals(expectedAlg, InspectMove.moveListToString(Solution.getWholeAlg(algorithm)));
    }


    @ParameterizedTest
    @CsvSource({"w, Lw2 U Lw2 y U' Rw U Rw' y U2 Lw' U' Lw x2 y F Rw U2 Rw' y' U2 Rw U' Rw' y2 Rw U Rw' z U2 Rw2 U Rw2 x Lw' U Lw U2 Lw2 U2 Lw2 U' Rw2 U' Rw2 x U2 Lw' U2 Lw U Rw U Rw'",
            "y,z Lw' U Lw y U Rw U Rw' x2 Lw' U' Lw y U2 Lw' U Lw y Rw U Rw' z y2 U' Lw' U' Lw x Rw U Rw' Lw2 U Lw2 x U F U2 Lw' U2 Lw",
            "g, x2 y U2 Rw U Rw' y U' Rw U Rw' x2 y2 Rw U2 Rw' U' Rw U Rw' z x' y2 Lw' U Lw x U' Rw U2 Rw' U' Rw U Rw' x U2 Lw' U2 Lw",
            "b, Rw U2 Rw' U' Rw U Rw' x2 y U2 Rw U Rw' y U' Rw U Rw' z x' Lw' U Lw x U' Rw U2 Rw' U' Rw U Rw' x U2 Lw' U2 Lw",
            "r,x' y Lw' U Lw x2 Lw' U2 Lw y U2 Rw U' Rw' y U' Lw' U Lw z x' y2 U2 Rw U Rw' x U' Rw2 U' Rw2 x F Lw' U2 Lw",
            "o, z U2 Lw2 U Lw2 y' U' Rw U Rw' x2 y' Rw U2 Rw' Lw' U' Lw z U2 Rw U' Rw' y2 Rw U Rw' x Rw U' Rw' U2 Rw2 U2 Rw2 U Lw2 U Lw2 x U2 Lw' U' Lw"})
    public void call_solveWholeCenters_with_random1_scramble(char firstCenterColor, String expectedAlg) {
        cube.makeMoves("r' d R D2 U' B' r' B' D' d");
        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<SolutionLBL> algorithm = lbl.solveCenters(firstCenterColor);
        interpretation.interpretCenters(cube);
        System.out.println("Solution: " + InspectMove.moveListToString(Solution.getWholeAlg(algorithm)));

        for (int i = 0; i < 6; i++) {
            Assertions.assertTrue(interpretation.isWholeCenterInOneColor(i));
        }
        Assertions.assertEquals(expectedAlg, InspectMove.moveListToString(Solution.getWholeAlg(algorithm)));
    }

    @ParameterizedTest
    @CsvSource({"w,x2 y U2 Rw U' Rw' y Rw U Rw' x2 Lw' U' Lw y U Lw' U Lw z x' U Lw' U' Lw x Lw' U Lw Rw2 U' Rw2 U' Lw2 U' Lw2 x U2 Lw' U2 Lw Rw U' Rw'",
            "y,y' Lw' U Lw y' Lw' U' Lw x2 U2 Rw U Rw' y' U' Rw U' Rw' z y2 Rw U' Rw' Lw' U' Lw x U2 Lw' U Lw U D U2 Lw2 U2 Lw2 x U Rw U' Rw'",
            "g,U2 Rw U' Rw' Rw2 U' Rw2 y' U2 Rw U' Rw' x2 U2 Lw' U2 Lw y' U' Lw' U Lw z Rw U Rw' y2 U Lw' U' Lw x U U2 Rw U2 Rw' x U' Rw U Rw'",
            "b,z' y U2 Lw' U' Lw y U' Lw' U' Lw x2 Rw U Rw' y' Rw U' Rw' y2 U Lw' U Lw z x U' Rw U Rw' x Lw2 U2 Lw2 U2 Rw2 U' Rw2 U' Lw2 U' Lw2 x U Rw U Rw'",
            "r,x y U2 Rw U' Rw' y U2 Lw' U Lw x2 y U2 Lw' U2 Lw z y2 U2 Rw U' Rw' U2 Lw' U' Lw x Lw' U Lw U2 Rw U Rw' U2 Rw2 U' Rw2 x U' Lw' U2 Lw U Lw' U' Lw",
            "o,z y2 U2 Lw' U2 Lw x2 U2 Rw U2 Rw' y' U2 Rw U' Rw' z F Rw U2 Rw' x U' Rw U Rw' Lw2 U Lw2 x Lw' U Lw U2 Rw U Rw'"})
    public void call_solveWholeCenters_with_random2_scramble(char firstCenterColor, String expectedAlg) {

        cube.makeMoves("d B' d l D U' b2 d L2 B2");
        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<SolutionLBL> algorithm = lbl.solveCenters(firstCenterColor);
        interpretation.interpretCenters(cube);
        System.out.println("Solution: " + InspectMove.moveListToString(Solution.getWholeAlg(algorithm)));


        for (int i = 0; i < 6; i++) {
            Assertions.assertTrue(interpretation.isWholeCenterInOneColor(i));
        }
        Assertions.assertEquals(expectedAlg, InspectMove.moveListToString(Solution.getWholeAlg(algorithm)));
    }

    @ParameterizedTest
    @CsvSource({"w,z Rw U Rw' Rw2 U Rw2 y2 Rw U Rw' x2 y Lw' U Lw Rw U Rw' y' Lw' U' Lw y' U2 Rw U Rw' z x U2 Lw2 U Lw2 y2 Lw' U' Lw x U2 Rw U Rw' U Lw2 U Lw2 x Rw U Rw'",
            "y,x2 U' Lw2 U' Lw2 y' U Rw U' Rw' x2 Rw U' Rw' y' U2 Rw U' Rw' y' Lw' U' Lw y' U Lw' U' Lw z Lw' U' Lw x Rw U' Rw' U' Rw2 U2 Rw2 Rw2 U' Rw2 U' Lw2 U' Lw2 x U Rw U2 Rw'",
            "g,Lw' U Lw Lw2 U Lw2 y' U Rw U' Rw' x2 F Rw U2 Rw' y' U2 Lw' U Lw y2 U2 Rw U' Rw' z U2 Rw U' Rw' y2 U Lw' U' Lw x U2 Lw' U2 Lw Rw2 U' Rw2 x U' Rw U2 Rw' Rw U' Rw' U' Lw' U' Lw",
            "b,z' y' Rw U' Rw' y' Lw' U' Lw x2 Rw U' Rw' y' U' Rw U' Rw' y2 Rw U' Rw' z Lw2 U2 Lw2 U Lw2 U' Lw2 x F U2 Lw' U2 Lw U Lw2 U' Lw2 x U2 Lw' U2 Lw",
            "r,z Lw' U Lw U' Rw2 U' Rw2 y2 U2 Lw' U' Lw x2 Rw U Rw' y' U2 Lw' U Lw y' U2 Rw U' Rw' y' U Rw U' Rw' z x2 U' Rw2 U' Rw2 x U' Rw U Rw' U' Lw2 U' Lw2 x Rw U2 Rw' U Lw' U Lw",
            "o,Rw U Rw' Rw2 U Rw2 y2 U' Rw U' Rw' x2 Rw U' Rw' y' Rw U Rw' y' Rw U Rw' y' U2 Lw' U Lw z Rw U' Rw' U' Rw2 U Rw2 x U Rw2 U Rw2 x U' Rw U2 Rw' Rw U' Rw' U' Lw' U' Lw"})
    public void call_solveWholeCenters_with_random3_scramble(char firstCenterColor, String expectedAlg) {

        cube.makeMoves("b2 l U2 F2 L2 d' B' b' L B'");
        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<SolutionLBL> algorithm = lbl.solveCenters(firstCenterColor);
        interpretation.interpretCenters(cube);

        System.out.println("Solution: " + InspectMove.moveListToString(Solution.getWholeAlg(algorithm)));
        for (int i = 0; i < 6; i++) {
            Assertions.assertTrue(interpretation.isWholeCenterInOneColor(i));
        }
        Assertions.assertEquals(expectedAlg, InspectMove.moveListToString(Solution.getWholeAlg(algorithm)));
    }


    @ParameterizedTest
    @CsvSource({"y", "b", "g", "r", "o", "w"})
    public void call_solve(char firstCenterColor) {
        //given
        cube.makeMoves("b2 l U2 F2 L2 d' B' b' L B'");
        LBL4X4 lbl = new LBL4X4(cube);
        //when
        ArrayList<SolutionLBL> algorithm = lbl.solve(firstCenterColor);
        //then
        System.out.println("Solution: " + algorithm);
        cube = new Cube4x4();
        cube.makeMoves("b2 l U2 F2 L2 d' B' b' L B'");
        cube.makeMoves(InspectMove.moveListToString(Solution.getWholeAlg(algorithm)));
        Assertions.assertTrue(Cube.isSolved(cube));
    }

}
