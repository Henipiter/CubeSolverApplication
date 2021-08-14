package cubes;

import DTOs.InspectMove;
import DTOs.MoveEnum;
import DTOs.MoveTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InspectMoveTest {

    @Test
    public void readMoveSimpleRFieldTest(){
        String direction = "R";
        InspectMove inspectMove = new InspectMove(direction);

        Assertions.assertEquals(MoveEnum.R, inspectMove.getMove());
        Assertions.assertEquals(MoveTypeEnum.SIMPLE, inspectMove.getMoveType());
    }

    @Test
    public void readMoveFieldPrimRTest(){
        String direction = "R'";
        InspectMove inspectMove = new InspectMove(direction);

        Assertions.assertEquals(MoveEnum.R, inspectMove.getMove());
        Assertions.assertEquals(MoveTypeEnum.PRIM, inspectMove.getMoveType());

    }

    @Test
    public void readMoveFieldDoubleRTest(){
        String direction = "R2";
        InspectMove inspectMove = new InspectMove(direction);

        Assertions.assertEquals(MoveEnum.R, inspectMove.getMove());
        Assertions.assertEquals(MoveTypeEnum.DOUBLE, inspectMove.getMoveType());

    }
@Test
    public void readMoveSimpleRwFieldTest(){
        String direction = "Rw";
        InspectMove inspectMove = new InspectMove(direction);

        Assertions.assertEquals(MoveEnum.Rw, inspectMove.getMove());
        Assertions.assertEquals(MoveTypeEnum.SIMPLE, inspectMove.getMoveType());
    }

    @Test
    public void readMoveFieldPrimRwTest(){
        String direction = "Rw'";
        InspectMove inspectMove = new InspectMove(direction);

        Assertions.assertEquals(MoveEnum.Rw, inspectMove.getMove());
        Assertions.assertEquals(MoveTypeEnum.PRIM, inspectMove.getMoveType());

    }

    @Test
    public void readMoveFieldDoubleRwTest(){
        String direction = "Rw2";
        InspectMove inspectMove = new InspectMove(direction);

        Assertions.assertEquals(MoveEnum.Rw, inspectMove.getMove());
        Assertions.assertEquals(MoveTypeEnum.DOUBLE, inspectMove.getMoveType());

    }

    @Test
    public void readMoveInvalid(){
        String direction = "X3";
        InspectMove inspectMove = new InspectMove(direction);

        Assertions.assertEquals(MoveEnum.INVALID, inspectMove.getMove());
        Assertions.assertEquals(MoveTypeEnum.INVALID, inspectMove.getMoveType());

    }
    @Test
    public void readMoveInvalid1(){
        String direction = "X'2";
        InspectMove inspectMove = new InspectMove(direction);

        Assertions.assertEquals(MoveEnum.INVALID, inspectMove.getMove());
        Assertions.assertEquals(MoveTypeEnum.DOUBLE, inspectMove.getMoveType());

    }

    @Test
    public void compareEquals_should_return_true(){
        InspectMove inspectMove = new InspectMove("X2");
        InspectMove inspectMove2 = new InspectMove("X2");
        Assertions.assertTrue(inspectMove.equals(inspectMove2));
    }
    @Test
    public void compareEquals_should_return_false(){
        InspectMove inspectMove = new InspectMove("X");
        InspectMove inspectMove2 = new InspectMove("X'");
        Assertions.assertFalse(inspectMove.equals(inspectMove2));
    }
}
