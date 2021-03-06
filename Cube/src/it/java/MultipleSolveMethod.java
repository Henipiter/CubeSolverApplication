import DTOs.Algorithm;
import DTOs.InspectMove;
import DTOs.Move;
import DTOs.Solution;
import cache.CubeOrientationCache;
import cache.FileElementCache;
import cubes.Cube;
import cubes.Cube2x2;
import cubes.Cube3x3;
import cubes.Cube4x4;
import methods.BLDs.BLD2X2;
import methods.BLDs.BLD3X3;
import methods.LBLs.LBL2X2;
import methods.LBLs.LBL3X3;
import methods.LBLs.LBL4X4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scramblers.ScramblerFactory;

import java.util.ArrayList;

public class MultipleSolveMethod {

    private static final int TIMES = 100;

    ScramblerFactory scramblerFactory = new ScramblerFactory();


    @BeforeEach
    void init(){
        FileElementCache.loadAll();
        Algorithm.loadPermutations();
    }

    @Test
    void loop2x2LBL() {
        for (int i = 0; i < TIMES; i++) {
            System.out.println("No: " + i);
            multiple2x2LBL();
        }
    }

    @Test
    void loop3x3LBL() {
        for (int i = 0; i < TIMES; i++) {
            System.out.println("No: " + i);
            multiple3x3LBL();
        }
    }

    @Test
    void loop4x4LBL() {
        for (int i = 0; i < TIMES; i++) {
            System.out.println("No: " + i);
            multiple4x4LBL();
        }
    }

    @Test
    void loop2x2BLD() {
        for (int i = 0; i < TIMES; i++) {
            System.out.println("No: " + i);
            multiple2x2BLD();
        }
    }

    @Test
    void loop3x3BLD() {
        for (int i = 0; i < TIMES; i++) {
            System.out.println("No: " + i);
            multiple3x3BLD();
        }
    }

    private void multiple2x2LBL() {
        Cube2x2 cube2x2 = new Cube2x2();
        ArrayList<Move> scrambleAlg = scramblerFactory.getScramble(cube2x2);
        System.out.println("---\nScramble:");
        System.out.println(InspectMove.moveListToString(scrambleAlg));
        cube2x2.makeMoves(scrambleAlg);
        LBL2X2 lbl2X2 = new LBL2X2(cube2x2);
        ArrayList<Solution> x = lbl2X2.solve('w');
        System.out.println("---");
        System.out.println(InspectMove.moveListToString(Solution.getWholeAlg(x)));

        cube2x2 = new Cube2x2();
        cube2x2.makeMoves(scrambleAlg);
        cube2x2.makeMoves(Solution.getWholeAlg(x));
        Assertions.assertTrue(Cube.isSolved(cube2x2));
    }

    private void multiple3x3LBL() {
        Cube3x3 cube3x3 = new Cube3x3();
        ArrayList<Move> scrambleAlg = scramblerFactory.getScramble(cube3x3);
        System.out.println("---\nScramble:");
        System.out.println(InspectMove.moveListToString(scrambleAlg));
        cube3x3.makeMoves(scrambleAlg);
        LBL3X3 lbl3X3 = new LBL3X3(cube3x3);
        ArrayList<Solution> x = lbl3X3.solve('w');
        System.out.println("---");
        System.out.println(InspectMove.moveListToString(Solution.getWholeAlg(x)));

        cube3x3 = new Cube3x3();
        cube3x3.makeMoves(scrambleAlg);
        cube3x3.makeMoves(Solution.getWholeAlg(x));
        Assertions.assertTrue(Cube.isSolved(cube3x3));
    }

    private void multiple4x4LBL() {
        Cube4x4 cube4x4 = new Cube4x4();
        ArrayList<Move> scrambleAlg = scramblerFactory.getScramble(cube4x4);
        System.out.println("---\nScramble:");
        System.out.println(InspectMove.moveListToString(scrambleAlg));
        cube4x4.makeMoves(scrambleAlg);
        LBL4X4 lbl4X4 = new LBL4X4(cube4x4);
        ArrayList<Solution> x = lbl4X4.solve('w');
        System.out.println("---");
        System.out.println(InspectMove.moveListToString(Solution.getWholeAlg(x)));

        cube4x4 = new Cube4x4();
        cube4x4.makeMoves(scrambleAlg);
        cube4x4.makeMoves(Solution.getWholeAlg(x));
        Assertions.assertTrue(Cube.isSolved(cube4x4));
    }

    private void multiple2x2BLD() {
        Cube2x2 cube2x2 = new Cube2x2();
        ArrayList<Move> scrambleAlg = scramblerFactory.getScramble(cube2x2);
        System.out.println("---\nScramble:");
        System.out.println(InspectMove.moveListToString(scrambleAlg));
        cube2x2.makeMoves(scrambleAlg);
        BLD2X2 bld2X2 = new BLD2X2(cube2x2);
        ArrayList<Solution> x = bld2X2.solve(CubeOrientationCache.upperColorBLD, CubeOrientationCache.frontColorBLD);
        System.out.println("---");
        System.out.println(InspectMove.moveListToString(Solution.getWholeAlg(x)));

        cube2x2 = new Cube2x2();
        cube2x2.makeMoves(scrambleAlg);
        cube2x2.makeMoves(Solution.getWholeAlg(x));
        Assertions.assertTrue(Cube.isSolved(cube2x2));
    }

    private void multiple3x3BLD() {
        Cube3x3 cube3x3 = new Cube3x3();
        ArrayList<Move> scrambleAlg = scramblerFactory.getScramble(cube3x3);
        System.out.println("---\nScramble:");
        System.out.println(InspectMove.moveListToString(scrambleAlg));
        cube3x3.makeMoves(scrambleAlg);
        BLD3X3 bld3X3 = new BLD3X3(cube3x3);
        ArrayList<Solution> x = bld3X3.solve(CubeOrientationCache.upperColorBLD, CubeOrientationCache.frontColorBLD);
        System.out.println("---");
        System.out.println(InspectMove.moveListToString(Solution.getWholeAlg(x)));

        cube3x3 = new Cube3x3();
        cube3x3.makeMoves(scrambleAlg);
        cube3x3.makeMoves(Solution.getWholeAlg(x));
        Assertions.assertTrue(Cube.isSolved(cube3x3));
    }
}
