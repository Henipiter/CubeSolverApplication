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
    public void call_solveFirstCenter(){
        cube.makeMovesUsingString("r u l d r");

        LBL4X4 lbl = new LBL4X4(cube);
        ArrayList<InspectMove> algorithm = lbl.solveFirstCenter();
        cube.makeMoves(algorithm);
        int countWhiteFields = interpretation.countFieldWithGivenColor(0,'w');
        Assertions.assertEquals(4, countWhiteFields);



    }

}
