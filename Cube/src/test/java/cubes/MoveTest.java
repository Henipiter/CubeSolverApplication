package cubes;

import DTOs.InspectMove;
import DTOs.Move;
import DTOs.MoveEnum;
import DTOs.MoveTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class MoveTest {

    @Test
    public void readMoveSimpleRFieldTest(){
        String direction = "R";
        Move move = new Move(direction);

        Assertions.assertEquals(MoveEnum.R, move.getMoveEnum());
        Assertions.assertEquals(MoveTypeEnum.SIMPLE, move.getMoveTypeEnum());
    }

    @Test
    public void readMoveFieldPrimRTest(){
        String direction = "R'";
        Move move = new Move(direction);

        Assertions.assertEquals(MoveEnum.R, move.getMoveEnum());
        Assertions.assertEquals(MoveTypeEnum.PRIM, move.getMoveTypeEnum());

    }

    @Test
    public void readMoveFieldDoubleRTest(){
        String direction = "R2";
        Move move = new Move(direction);

        Assertions.assertEquals(MoveEnum.R, move.getMoveEnum());
        Assertions.assertEquals(MoveTypeEnum.DOUBLE, move.getMoveTypeEnum());

    }
@Test
    public void readMoveSimpleRwFieldTest(){
        String direction = "Rw";
        Move move = new Move(direction);

        Assertions.assertEquals(MoveEnum.Rw, move.getMoveEnum());
        Assertions.assertEquals(MoveTypeEnum.SIMPLE, move.getMoveTypeEnum());
    }

    @Test
    public void readMoveFieldPrimRwTest(){
        String direction = "Rw'";
        Move move = new Move(direction);

        Assertions.assertEquals(MoveEnum.Rw, move.getMoveEnum());
        Assertions.assertEquals(MoveTypeEnum.PRIM, move.getMoveTypeEnum());

    }

    @Test
    public void readMoveFieldDoubleRwTest(){
        String direction = "Rw2";
        Move move = new Move(direction);

        Assertions.assertEquals(MoveEnum.Rw, move.getMoveEnum());
        Assertions.assertEquals(MoveTypeEnum.DOUBLE, move.getMoveTypeEnum());

    }

    @Test
    public void readMoveInvalid(){
        String direction = "Xx3";
        Move move = new Move(direction);

        Assertions.assertEquals(MoveEnum.INVALID, move.getMoveEnum());
        Assertions.assertEquals(MoveTypeEnum.INVALID, move.getMoveTypeEnum());

    }
    @Test
    public void readMoveInvalid1(){
        String direction = "x'2";
        Move move = new Move(direction);

        Assertions.assertEquals(MoveEnum.INVALID, move.getMoveEnum());
        Assertions.assertEquals(MoveTypeEnum.DOUBLE, move.getMoveTypeEnum());

    }

    @Test
    public void compareEquals_should_return_true(){
        Move move = new Move("x2");
        Move move2 = new Move("x2");
        Assertions.assertEquals(move2, move);
    }
    @Test
    public void compareEquals_should_return_false(){
        Move move = new Move("x");
        Move move2 = new Move("x'");
        Assertions.assertNotEquals(move2, move);
    }

    @Test
    public void call_createAndReturnArrayListFromString_and_should_return_expected_arraylist(){

        ArrayList<Move> expected = new ArrayList<>(Arrays.asList(
                new Move("R"),
                new Move("U"),
                new Move("R"),
                new Move("U")
        ));
        ArrayList<Move> result = InspectMove.stringToMoveList("R U R U");
        Assertions.assertEquals(expected, result);
    }

    @Test
    void call_algToString_and_should_return_RURU(){
        ArrayList<Move> alg = new ArrayList<>(Arrays.asList(
                new Move("R"),
                new Move("U"),
                new Move("R"),
                new Move("U")
        ));
        String result = InspectMove.moveListToString(alg);
        Assertions.assertEquals("R U R U", result);
    }

    @Test
    void call_algToString_and_should_return_empty_string(){
        ArrayList<Move> alg = new ArrayList<>();
        String result = InspectMove.moveListToString(alg);
        Assertions.assertEquals("", result);
    }
}
