package cubes;

import DTOs.InspectMove;
import DTOs.MoveEnum;
import DTOs.MoveTypeEnum;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class makeMovesTest {

    private Cube4x4 cube;
    private Cube4x4 cubeExpected;
    @BeforeEach
    public void init(){
        cube = new Cube4x4();
        cubeExpected = new Cube4x4();
    }

    @Test
    public void alg_RULD_string_test(){
        cube.makeMovesUsingString("R U L D");

        cubeExpected.moveUsingString("R");
        cubeExpected.moveUsingString("U");
        cubeExpected.moveUsingString("L");
        cubeExpected.moveUsingString("D");

        Assert.assertArrayEquals(cube.getCube(), cubeExpected.getCube());
    }

    @Test
    public void alg_RprimUdoubleLD_string_test(){
        cube.makeMovesUsingString("R' U2 L D");

        cubeExpected.moveUsingString("R'");
        cubeExpected.moveUsingString("U2");
        cubeExpected.moveUsingString("L");
        cubeExpected.moveUsingString("D");

        Assert.assertArrayEquals(cube.getCube(), cubeExpected.getCube());
    }

    @Test
    public void alg_RULD_test(){
        ArrayList<InspectMove> alg = new ArrayList<>();
        alg.add(new InspectMove(MoveEnum.R, MoveTypeEnum.SIMPLE));
        alg.add(new InspectMove(MoveEnum.U, MoveTypeEnum.SIMPLE));
        alg.add(new InspectMove(MoveEnum.L, MoveTypeEnum.SIMPLE));
        alg.add(new InspectMove(MoveEnum.D, MoveTypeEnum.SIMPLE));

        cube.makeMoves(alg);

        cubeExpected.move(new InspectMove(MoveEnum.R, MoveTypeEnum.SIMPLE));
        cubeExpected.move(new InspectMove(MoveEnum.U, MoveTypeEnum.SIMPLE));
        cubeExpected.move(new InspectMove(MoveEnum.L, MoveTypeEnum.SIMPLE));
        cubeExpected.move(new InspectMove(MoveEnum.D, MoveTypeEnum.SIMPLE));

        Assert.assertArrayEquals(cube.getCube(), cubeExpected.getCube());
    }

    @Test
    public void alg_RprimUdoubleLD_test(){
        ArrayList<InspectMove> alg = new ArrayList<>();
        alg.add(new InspectMove(MoveEnum.R, MoveTypeEnum.PRIM));
        alg.add(new InspectMove(MoveEnum.U, MoveTypeEnum.DOUBLE));
        alg.add(new InspectMove(MoveEnum.L, MoveTypeEnum.SIMPLE));
        alg.add(new InspectMove(MoveEnum.D, MoveTypeEnum.SIMPLE));
        cube.makeMoves(alg);

        cubeExpected.move(new InspectMove(MoveEnum.R, MoveTypeEnum.PRIM));
        cubeExpected.move(new InspectMove(MoveEnum.U, MoveTypeEnum.DOUBLE));
        cubeExpected.move(new InspectMove(MoveEnum.L, MoveTypeEnum.SIMPLE));
        cubeExpected.move(new InspectMove(MoveEnum.D, MoveTypeEnum.SIMPLE));

        Assert.assertArrayEquals(cube.getCube(), cubeExpected.getCube());
    }

}
