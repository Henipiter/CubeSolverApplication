package method.BLD;

import DTOs.Algorithm;
import DTOs.InspectMove;
import DTOs.Solution;
import cubes.Cube;
import cubes.Cube3x3;
import interpretations.Interpretation3x3Edges;
import interpretations.Interpretation3x3Vertices;
import methods.BLDs.BLD3X3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BLD3x3Test {

    Cube cube;
    BLD3X3 bld3X3;

    @BeforeAll
    static void init(){
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
        ArrayList<Solution> solution = bld3X3.solve('y','g');
        cube.makeMoves(Solution.getWholeAlg(solution));
        System.out.println(InspectMove.moveListToString(Solution.getWholeAlg(solution)));
        cube = new Cube3x3();
        cube.makeMoves("x2 F R' F x2 y2");
        cube.makeMoves(Solution.getWholeAlg(solution));
        Assertions.assertTrue(Cube.isSolved(cube));
    }

    @Test
    void solveAllC555ube() {

        char[][] input = new char[][] {
                {'b','y','y','b','y','r','r','w'},
                {'g','w','w','g','w','y','y','y'},
                {'w','w','g','r','o','r','r','o'},
                {'o','o','b','o','r','o','o','r'},
                {'w','w','o','b','b','b','b','b'},
                {'r','g','g','y','g','y','g','g'}
        };

        cube = new Cube3x3(input);
        cube.setCenter(new char[]{'y','w','r','o','b','g'});

        bld3X3 = new BLD3X3(cube);
        ArrayList<Solution> solution = bld3X3.solve('w','g');
        cube.makeMoves(Solution.getWholeAlg(solution));
        System.out.println(InspectMove.moveListToString(Solution.getWholeAlg(solution)));

        cube = new Cube3x3(input);
        cube.setCenter(new char[]{'y','w','r','o','b','g'});
        cube.makeMoves(Solution.getWholeAlg(solution));

        Interpretation3x3Edges interpretation3x3Edges = new Interpretation3x3Edges();
        Interpretation3x3Vertices interpretation3x3Vertices  = new Interpretation3x3Vertices();
        interpretation3x3Edges.interpretEdges(this.cube);
        interpretation3x3Vertices.interpretVertices(this.cube);


        Assertions.assertTrue(Cube.isSolved(cube));
    }
}
