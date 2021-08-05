package cubes.cube4x4.method.LBL;

import DTOs.InspectMove;
import cubes.Cube;
import cubes.Cube4x4;
import interpretations.Interpretation4x4;
import methods.LBLs.LBL4X4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CentersSolverTest {

    Cube cube = new Cube4x4();
    Interpretation4x4 interpretation = new Interpretation4x4();

    @Test
    public void call_addToAlgorithmAndUpdateCubeStuff_and_check_correctness(){
        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<InspectMove> alg = new ArrayList<>();
        alg.add(new InspectMove("R"));
        alg.add(new InspectMove("U"));
        ArrayList<InspectMove> mainAlg =new ArrayList<>();
        ArrayList<InspectMove> expected =new ArrayList<>();
        expected.add(new InspectMove("R"));
        expected.add(new InspectMove("U"));
        expected.add(new InspectMove("R"));
        expected.add(new InspectMove("U"));
        lbl.addToAlgorithmAndUpdateCubeStuff(alg, mainAlg);
        lbl.addToAlgorithmAndUpdateCubeStuff(alg, mainAlg);

        Assertions.assertEquals(expected, mainAlg);
    }


    @Test
    public void call_solveFirstCenter_with_short_scramble(){
        cube.makeMovesUsingString("r u l d r");

        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<InspectMove> algorithm = lbl.solveFirstCenter();
        interpretation.interpretCenters(cube);
        int countWhiteFields = interpretation.countFieldWithGivenColor(0,'w');
        Assertions.assertEquals(4, countWhiteFields);
    }

    @Test
    public void call_solveFirstCenter_with_random_scramble1(){
        cube.makeMovesUsingString("r' d R D2 U' B' r' B' D' d");

        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<InspectMove> algorithm = lbl.solveFirstCenter();
        interpretation.interpretCenters(cube);
        int countWhiteFields = interpretation.countFieldWithGivenColor(0,'w');
        Assertions.assertEquals(4, countWhiteFields);
    }

    @Test
    public void call_solveFirstCenter__with_random_scramble2(){
        cube.makeMovesUsingString("d B' d l D U' b2 d L2 B2");
        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<InspectMove> algorithm = lbl.solveFirstCenter();
        interpretation.interpretCenters(cube);
        int countWhiteFields = interpretation.countFieldWithGivenColor(0,'w');
        Assertions.assertEquals(4, countWhiteFields);
    }

    @Test
    public void call_solveFirstCenter__with_random_scramble3(){
        cube.makeMovesUsingString("b2 l U2 F2 L2 d' B' b' L B'");

        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<InspectMove> algorithm = lbl.solveFirstCenter();
        interpretation.interpretCenters(cube);
        int countWhiteFields = interpretation.countFieldWithGivenColor(0,'w');
        Assertions.assertEquals(4, countWhiteFields);
    }

    @Test
    public void call_solveSecondCenter_with_short_scramble(){
        cube.makeMovesUsingString("r u l d r");
        cube.makeMovesUsingString("Z' Lw' U' Lw Rw2 U Rw2");

        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<InspectMove> algorithm = lbl.solveSecondCenter();
        interpretation.interpretCenters(cube);
        int countWhiteFields = interpretation.countFieldWithGivenColor(0,'y');
        Assertions.assertEquals(4, countWhiteFields);
    }

    @Test
    public void call_solveThirdCenter_with_short_scramble(){
        cube.makeMovesUsingString("r u l d r");
        cube.makeMovesUsingString("Z' Lw' U' Lw Rw2 U Rw2");
        cube.makeMovesUsingString("X2 U' Lw' U Lw Y2 U2 Rw U Rw'");

        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<InspectMove> algorithm = lbl.solveThirdCenter();
        interpretation.interpretCenters(cube);
        boolean result = interpretation.isWholeCenterInOneColor(0);
        Assertions.assertTrue(result);
    }

    @Test
    public void call_solveFourthCenter_with_short_scramble(){
        cube.makeMovesUsingString("r u l d r");
        cube.makeMovesUsingString("Z' Lw' U' Lw Rw2 U Rw2");
        cube.makeMovesUsingString("X2 U' Lw' U Lw Y2 U2 Rw U Rw'");
        cube.makeMovesUsingString("Z X U' Rw U Rw'");

        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<InspectMove> algorithm = lbl.solveFourthCenter();
        interpretation.interpretCenters(cube);
        boolean result = interpretation.isWholeCenterInOneColor(0);
        Assertions.assertTrue(result);
    }

    @Test
    public void call_solveFifthCenter_with_short_scramble(){
        cube.makeMovesUsingString("r u l d r");
        cube.makeMovesUsingString("Z' Lw' U' Lw Rw2 U Rw2");
        cube.makeMovesUsingString("X2 U' Lw' U Lw Y2 U2 Rw U Rw'");
        cube.makeMovesUsingString("Z X U' Rw U Rw'");
        cube.makeMovesUsingString("X Rw U Rw' D Lw2 U2 Lw2");

        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<InspectMove> algorithm = lbl.solveLastTwoCenters();
        interpretation.interpretCenters(cube);
        boolean result = interpretation.isWholeCenterInOneColor(0);
        Assertions.assertTrue(result);
    }

    @Test
    public void call_solveWholeCenters_with_short_scramble(){

        cube.makeMovesUsingString("r u l d r");
        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<InspectMove> algorithm = lbl.solveCenters();
        interpretation.interpretCenters(cube);
        for(int i=0;i<6;i++){
            Assertions.assertTrue(interpretation.isWholeCenterInOneColor(i));
        }
    }

    @Test
    public void call_solveWholeCenters_with_random3_scramble(){

        cube.makeMovesUsingString("b2 l U2 F2 L2 d' B' b' L B'");
        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<InspectMove> algorithm = lbl.solveCenters();
        interpretation.interpretCenters(cube);
        interpretation.printAlgorithm(algorithm);

        for(int i=0;i<6;i++){
            Assertions.assertTrue(interpretation.isWholeCenterInOneColor(i));
        }
    }

    @Test
    public void call_solveWholeCenters_with_random2_scramble(){

        cube.makeMovesUsingString("d B' d l D U' b2 d L2 B2");
        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<InspectMove> algorithm = lbl.solveCenters();
        interpretation.interpretCenters(cube);
        interpretation.printAlgorithm(algorithm);

        for(int i=0;i<6;i++){
            Assertions.assertTrue(interpretation.isWholeCenterInOneColor(i));
        }
    }

    @Test
    public void call_solveWholeCenters_with_random1_scramble(){

        cube.makeMovesUsingString("r' d R D2 U' B' r' B' D' d");
        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<InspectMove> algorithm = lbl.solveCenters();
        interpretation.interpretCenters(cube);
        interpretation.printAlgorithm(algorithm);

        for(int i=0;i<6;i++){
            Assertions.assertTrue(interpretation.isWholeCenterInOneColor(i));
        }
    }
}
