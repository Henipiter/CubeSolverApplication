package cubes;

import DTOs.Move;
import DTOs.MoveEnum;
import DTOs.MoveTypeEnum;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MakeMovesTest {

    private Cube4x4 cube;
    private Cube4x4 cubeExpected;
    @BeforeEach
    public void init(){
        cube = new Cube4x4();
        cubeExpected = new Cube4x4();
    }

    @Test
    public void alg_RULD_string_test(){
        cube.makeMoves("R U L D");

        cubeExpected.move("R");
        cubeExpected.move("U");
        cubeExpected.move("L");
        cubeExpected.move("D");

        Assert.assertArrayEquals(cube.getCube(), cubeExpected.getCube());
    }

    @Test
    public void alg_RprimUdoubleLD_string_test(){
        cube.makeMoves("R' U2 L D");

        cubeExpected.move("R'");
        cubeExpected.move("U2");
        cubeExpected.move("L");
        cubeExpected.move("D");

        Assert.assertArrayEquals(cube.getCube(), cubeExpected.getCube());
    }

    @Test
    public void alg_RULD_test(){
        ArrayList<Move> alg = new ArrayList<>();
        alg.add(new Move(MoveEnum.R, MoveTypeEnum.SIMPLE));
        alg.add(new Move(MoveEnum.U, MoveTypeEnum.SIMPLE));
        alg.add(new Move(MoveEnum.L, MoveTypeEnum.SIMPLE));
        alg.add(new Move(MoveEnum.D, MoveTypeEnum.SIMPLE));

        cube.makeMoves(alg);

        cubeExpected.move(new Move(MoveEnum.R, MoveTypeEnum.SIMPLE));
        cubeExpected.move(new Move(MoveEnum.U, MoveTypeEnum.SIMPLE));
        cubeExpected.move(new Move(MoveEnum.L, MoveTypeEnum.SIMPLE));
        cubeExpected.move(new Move(MoveEnum.D, MoveTypeEnum.SIMPLE));

        Assert.assertArrayEquals(cube.getCube(), cubeExpected.getCube());
    }

    @Test
    public void alg_RprimUdoubleLD_test(){
        ArrayList<Move> alg = new ArrayList<>();
        alg.add(new Move(MoveEnum.R, MoveTypeEnum.PRIM));
        alg.add(new Move(MoveEnum.U, MoveTypeEnum.DOUBLE));
        alg.add(new Move(MoveEnum.L, MoveTypeEnum.SIMPLE));
        alg.add(new Move(MoveEnum.D, MoveTypeEnum.SIMPLE));
        cube.makeMoves(alg);

        cubeExpected.move(new Move(MoveEnum.R, MoveTypeEnum.PRIM));
        cubeExpected.move(new Move(MoveEnum.U, MoveTypeEnum.DOUBLE));
        cubeExpected.move(new Move(MoveEnum.L, MoveTypeEnum.SIMPLE));
        cubeExpected.move(new Move(MoveEnum.D, MoveTypeEnum.SIMPLE));

        Assert.assertArrayEquals(cube.getCube(), cubeExpected.getCube());
    }

}
