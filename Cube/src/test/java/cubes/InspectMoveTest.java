package cubes;

import DTOs.InspectMove;
import DTOs.MoveEnum;
import DTOs.MoveTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class InspectMoveTest {

    @Test
    public void readMoveSimpleRFieldTest(){
        String direction = "R";
        InspectMove inspectMove = new InspectMove(direction);

        Assertions.assertEquals(MoveEnum.R, inspectMove.getMoveEnum());
        Assertions.assertEquals(MoveTypeEnum.SIMPLE, inspectMove.getMoveTypeEnum());
    }

    @Test
    public void readMoveFieldPrimRTest(){
        String direction = "R'";
        InspectMove inspectMove = new InspectMove(direction);

        Assertions.assertEquals(MoveEnum.R, inspectMove.getMoveEnum());
        Assertions.assertEquals(MoveTypeEnum.PRIM, inspectMove.getMoveTypeEnum());

    }

    @Test
    public void readMoveFieldDoubleRTest(){
        String direction = "R2";
        InspectMove inspectMove = new InspectMove(direction);

        Assertions.assertEquals(MoveEnum.R, inspectMove.getMoveEnum());
        Assertions.assertEquals(MoveTypeEnum.DOUBLE, inspectMove.getMoveTypeEnum());

    }
@Test
    public void readMoveSimpleRwFieldTest(){
        String direction = "Rw";
        InspectMove inspectMove = new InspectMove(direction);

        Assertions.assertEquals(MoveEnum.Rw, inspectMove.getMoveEnum());
        Assertions.assertEquals(MoveTypeEnum.SIMPLE, inspectMove.getMoveTypeEnum());
    }

    @Test
    public void readMoveFieldPrimRwTest(){
        String direction = "Rw'";
        InspectMove inspectMove = new InspectMove(direction);

        Assertions.assertEquals(MoveEnum.Rw, inspectMove.getMoveEnum());
        Assertions.assertEquals(MoveTypeEnum.PRIM, inspectMove.getMoveTypeEnum());

    }

    @Test
    public void readMoveFieldDoubleRwTest(){
        String direction = "Rw2";
        InspectMove inspectMove = new InspectMove(direction);

        Assertions.assertEquals(MoveEnum.Rw, inspectMove.getMoveEnum());
        Assertions.assertEquals(MoveTypeEnum.DOUBLE, inspectMove.getMoveTypeEnum());

    }

    @Test
    public void readMoveInvalid(){
        String direction = "Xx3";
        InspectMove inspectMove = new InspectMove(direction);

        Assertions.assertEquals(MoveEnum.INVALID, inspectMove.getMoveEnum());
        Assertions.assertEquals(MoveTypeEnum.INVALID, inspectMove.getMoveTypeEnum());

    }
    @Test
    public void readMoveInvalid1(){
        String direction = "x'2";
        InspectMove inspectMove = new InspectMove(direction);

        Assertions.assertEquals(MoveEnum.INVALID, inspectMove.getMoveEnum());
        Assertions.assertEquals(MoveTypeEnum.DOUBLE, inspectMove.getMoveTypeEnum());

    }

    @Test
    public void compareEquals_should_return_true(){
        InspectMove inspectMove = new InspectMove("x2");
        InspectMove inspectMove2 = new InspectMove("x2");
        Assertions.assertEquals(inspectMove2, inspectMove);
    }
    @Test
    public void compareEquals_should_return_false(){
        InspectMove inspectMove = new InspectMove("x");
        InspectMove inspectMove2 = new InspectMove("x'");
        Assertions.assertNotEquals(inspectMove2, inspectMove);
    }

    @Test
    public void call_createAndReturnArrayListFromString_and_should_return_expected_arraylist(){

        ArrayList<InspectMove> expected = new ArrayList<>(Arrays.asList(
                new InspectMove("R"),
                new InspectMove("U"),
                new InspectMove("R"),
                new InspectMove("U")
        ));
        ArrayList<InspectMove> result = InspectMove.createAndReturnArrayListFromString("R U R U");
        Assertions.assertEquals(expected, result);
    }

    @Test
    void call_algToString_and_should_return_RURU(){
        ArrayList<InspectMove> alg = new ArrayList<>(Arrays.asList(
                new InspectMove("R"),
                new InspectMove("U"),
                new InspectMove("R"),
                new InspectMove("U")
        ));
        String result = InspectMove.algorithmToString(alg);
        Assertions.assertEquals("R U R U", result);
    }

    @Test
    void call_algToString_and_should_return_empty_string(){
        ArrayList<InspectMove> alg = new ArrayList<>();
        String result = InspectMove.algorithmToString(alg);
        Assertions.assertEquals("", result);
    }
}
