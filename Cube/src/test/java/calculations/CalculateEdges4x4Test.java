package calculations;

import DTOs.InspectMove;
import calculations.CalculateEdges4x4;
import cubes.Cube4x4;
import interpretations.Interpretation4x4Edges;
import methods.LBLs.LBL4X4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;

public class CalculateEdges4x4Test {

    Cube4x4 cube;
    Interpretation4x4Edges interpretation4x4Edges;
    CalculateEdges4x4 calculateEdges4x4 ;

    @BeforeEach
    void setUp(){
        cube = new Cube4x4();
        interpretation4x4Edges = new Interpretation4x4Edges();
        calculateEdges4x4 = new CalculateEdges4x4(cube);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/algForTestSetupTo12Edge.csv")
    void call_pairAllEdges_and_check_correctness_of_setup_to_12_edge(String edgeNum, String scramble){
        String initScramble = InspectMove.algorithmToString(CalculateEdges4x4.getAlgorithmToJoinEdges());
        String fullScramble = initScramble+" "+scramble;
        cube.makeMovesUsingString(fullScramble);
        interpretation4x4Edges.interpretEdges(cube);
        ArrayList<InspectMove> alg= calculateEdges4x4.getMovesToPutUnpairedEdgeOn12Index(Integer.parseInt(edgeNum));
        cube.makeMoves(alg);
        cube.makeMovesUsingString(initScramble);
        interpretation4x4Edges.interpretEdges(cube);

        System.out.println(scramble);
        System.out.println(InspectMove.algorithmToString(alg));
        Assertions.assertTrue(interpretation4x4Edges.isAllEdgesArePaired());

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/algForTestSetupTo14Or15Edge.csv")
    void call_pairAllEdges_and_check_correctness_of_setup_to_14_or_15_edge(String edgeNum,String scramble){
        String initScramble = InspectMove.algorithmToString(CalculateEdges4x4.getAlgorithmToJoinEdges());
        String fullScramble = initScramble+" "+scramble;
        cube.makeMovesUsingString(fullScramble);
        interpretation4x4Edges.interpretEdges(cube);
        ArrayList<InspectMove> alg= calculateEdges4x4.getMovesToPutUnpairedEdgeOn14or15Index(Integer.parseInt(edgeNum));
        cube.makeMoves(alg);
        if(edgeNum.equals("10") || edgeNum.equals("11")){
            cube.makeMovesUsingString("R2");
        }
        interpretation4x4Edges.interpretEdges(cube);

        System.out.println(scramble);
        System.out.println(InspectMove.algorithmToString(alg));

        boolean resulttt = interpretation4x4Edges.isGivenEdgeHasItsPairOnGivenEdgePair(14,6) ||
                interpretation4x4Edges.isGivenEdgeHasItsPairOnGivenEdgePair(15,6);
        Assertions.assertTrue(resulttt);

    }

    @Test
    void call_pairAllEdges(){

        cube.makeMovesUsingString("r u l d");
        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<InspectMove> algorithm = lbl.pairAllEdges();
        Assertions.assertTrue(interpretation4x4Edges.isAllEdgesArePaired());
        System.out.println(algorithm.toString());

    }

    @Test
    void call_pairAllEdge2s(){

        cube.makeMovesUsingString("r' d R D2 U' B' r' B' D' d");
        cube.makeMovesUsingString("Lw2 U Lw2 y U' Rw U Rw' y U2 Lw' U' Lw x2 Rw U' Rw' y F Rw U2 Rw' y U Rw U Rw' z x U Rw U' Rw' x Rw U Rw' D Lw2 U2 Lw2 x U2 Rw U2 Rw' U' Lw' U' Lw");
        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<InspectMove> algorithm = lbl.pairAllEdges();
        Assertions.assertTrue(interpretation4x4Edges.isAllEdgesArePaired());
        System.out.println(algorithm.toString());

        String expectedAlg = "R U R' F R' F' R Uw' R U R' F R' F' R Uw M' R U' R' Uw' R U R' F R' F' R Uw D M' R U' R' Uw' R U R' F R' F' R Uw D' M' F R' F' R Uw' R U R' F R' F' R Uw D' M' R U' R' Uw' R U R' F R' F' R Uw M2 F R' F' R Uw' R U R' F R' F' R Uw U R U' R' Uw' R U R' F R' F' R Uw L U' L' R U' R' Uw' R U R' F R' F' R Uw L' D' M' F R' F' R Uw' R U R' F R' F' R Uw R' U R R U' R' Uw' R U R' F R' F' R Uw";
        Assertions.assertEquals(expectedAlg, InspectMove.algorithmToString(algorithm));
    }

    @Test
    void call_pairAllEdge_with_random_scramble1_with_solved_centers(){

        cube.makeMovesUsingString("r u l d");
        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<InspectMove> algorithm = lbl.pairAllEdges();
        Assertions.assertTrue(interpretation4x4Edges.isAllEdgesArePaired());
        System.out.println(algorithm.toString());
    }

}
