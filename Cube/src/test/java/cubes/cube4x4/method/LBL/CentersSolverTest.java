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
}
