package method.BLD;

import DTOs.Algorithm;
import DTOs.InspectMove;
import DTOs.Solution;
import cache.FileElementCache;
import cubes.Cube;
import cubes.Cube3x3;
import methods.BLDs.BLD3X3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

public class BLD3x3Test {

    Cube cube;
    BLD3X3 bld3X3;

    @BeforeAll
    static void init() {
        FileElementCache.loadAll();
        Algorithm.loadPermutations();
    }

    @Test
    void solveAllVertices() {
        String expectedSolution = "B C A";
        cube = new Cube3x3();
        cube.makeMoves("U");
        bld3X3 = new BLD3X3(cube);
        ArrayList<Solution> solution = bld3X3.solveAllVertices();
        Assertions.assertEquals(expectedSolution, Solution.getWholeMarks(solution));
    }

    @Test
    void solveAllVertices1() {
        String expectedSolution = "R O U M C B J";
        cube = new Cube3x3();
        cube.makeMoves("B' L D L2 U' R2 B2 D' R2 U' R2 D' B2 L' B' D U R' U2 B");
        bld3X3 = new BLD3X3(cube);
        ArrayList<Solution> solution = bld3X3.solveAllVertices();
        Assertions.assertEquals(expectedSolution, Solution.getWholeMarks(solution));
    }

    @Test
    void solveAllEdges() {
        String expectedSolution = "A B C";
        cube = new Cube3x3();
        cube.makeMoves("U");
        bld3X3 = new BLD3X3(cube);
        bld3X3.solveAllEdges();
        ArrayList<Solution> solution = bld3X3.solveAllEdges();
        Assertions.assertEquals(expectedSolution, Solution.getWholeMarks(solution));
    }

    @Test
    void solveAllEdges1() {
        String expectedSolution = "J W B I D P G S A L T";
        cube = new Cube3x3();
        cube.makeMoves("B' L D L2 U' R2 B2 D' R2 U' R2 D' B2 L' B' D U R' U2 B");
        bld3X3 = new BLD3X3(cube);
        ArrayList<Solution> solution = bld3X3.solveAllEdges();
        Assertions.assertEquals(expectedSolution, Solution.getWholeMarks(solution));
    }

    @Test
    void solveAllCube() {
        cube = new Cube3x3();
        cube.makeMoves("x2 F R' F x2 y2");
        bld3X3 = new BLD3X3(cube);
        ArrayList<Solution> solution = bld3X3.solve('y', 'g');
        cube.makeMoves(Solution.getWholeAlg(solution));
        System.out.println(InspectMove.moveListToString(Solution.getWholeAlg(solution)));
        cube = new Cube3x3();
        cube.makeMoves("x2 F R' F x2 y2");
        cube.makeMoves(Solution.getWholeAlg(solution));
        Assertions.assertTrue(Cube.isSolved(cube));
    }

    @Test
    void solveAllCubeWhenAllEdgesAreRotated() {
        String scramble = "M U M U M U M U M' U M' U M' U M' U x2 M U M U M U M U M'" +
                " U M' U M' U M' U x U M U M U M U2 M' U M' U M' U x2 U M U M U M U2 M' U M' U M' U";
        cube = new Cube3x3();
        cube.makeMoves(scramble);
        bld3X3 = new BLD3X3(cube);
        ArrayList<Solution> solution = bld3X3.solve('y', 'g');
        cube.makeMoves(Solution.getWholeAlg(solution));
        System.out.println(InspectMove.moveListToString(Solution.getWholeAlg(solution)));
        cube = new Cube3x3();
        cube.makeMoves(scramble);
        cube.makeMoves(Solution.getWholeAlg(solution));
        Assertions.assertTrue(Cube.isSolved(cube));
        Assertions.assertEquals("  A F C D B E J K M L N G I H T R Z S W O U P", Solution.getWholeMarks(solution));
    }

    @ParameterizedTest
    @CsvSource({
            "y R U' R' U R U' R' U D  R U' R' U R U' R' U D  R U' R' U R U' R' U D2 x2 D  R U' R' U R U' R' U D R U' R' U R U' R' U D R U' R' U R U' R' U D x' D' R U' R' U R U' R' U D  R U' R' U R U' R' U  R U' R' U R U' R' U x' y', ANCPBHULTSZFWJ",
            "y R U R' U' R U R' U' D  R U R' U' R U R' U' D  R U R' U' R U R' U' D2 x2 D  R U R' U' R U R' U' D R U R' U' R U R' U' D R U R' U' R U R' U' D x' D' R U R' U' R U R' U' D  R U R' U' R U R' U'  R U R' U' R U R' U' x' y', AOCDBIUKTMZRWG"
    })
    void solveAllCubeWhenAllVerticesAreRotatedOnce(String scramble, String expectedMarks) {
        cube = new Cube3x3();
        cube.makeMoves(scramble);
        bld3X3 = new BLD3X3(cube);
        ArrayList<Solution> solution = bld3X3.solve('y', 'r');
        cube.makeMoves(Solution.getWholeAlg(solution));
        System.out.println(InspectMove.moveListToString(Solution.getWholeAlg(solution)));
        cube = new Cube3x3();
        cube.makeMoves(scramble);
        cube.makeMoves(Solution.getWholeAlg(solution));
        Assertions.assertTrue(Cube.isSolved(cube));
        Assertions.assertEquals(expectedMarks, Solution.getWholeMarks(solution).replace(" ",""));
    }

    @Test
    void solveAllCube2222() {
        char[][] input2 = new char[][]{
                {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
                {'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r'},
                {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
                {'y', 'y', 'y', 'y', 'y', 'y', 'y', 'y'},
                {'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g'},
                {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'},
        };
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

        cube.makeMoves("x2 F R' F x2 y2");
        bld3X3 = new BLD3X3(cube);
        ArrayList<Solution> solution = bld3X3.solve('y', 'g');
        cube.makeMoves(Solution.getWholeAlg(solution));
        System.out.println(InspectMove.moveListToString(Solution.getWholeAlg(solution)));
        cube = new Cube3x3(input2, center);
        Assertions.assertTrue(Cube.isSolved(cube));
        cube.makeMoves("x2 F R' F x2 y2");
        cube.makeMoves(Solution.getWholeAlg(solution));
        Assertions.assertTrue(Cube.isSolved(cube));
    }

    @Test
    void solveTwoTimes(){

            cube = new Cube3x3();
            cube.makeMoves("x2 F R' F x2 y2");
            bld3X3 = new BLD3X3(cube);
            ArrayList<Solution> solution = bld3X3.solve('y', 'g');
            cube.makeMoves(Solution.getWholeAlg(solution));
            System.out.println(InspectMove.moveListToString(Solution.getWholeAlg(solution)));
            cube = new Cube3x3();
            cube.makeMoves("x2 F R' F x2 y2");
            cube.makeMoves(Solution.getWholeAlg(solution));

            bld3X3 = new BLD3X3(cube);
            solution = bld3X3.solve('y', 'g');
        System.out.println(InspectMove.moveListToString(Solution.getWholeAlg(solution)));

        //  cube.makeMoves(Solution.getWholeAlg(solution));
            Assertions.assertTrue(Cube.isSolved(cube));

    }

}
