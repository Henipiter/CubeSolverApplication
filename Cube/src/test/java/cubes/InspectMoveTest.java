package cubes;

import DTOs.InspectMove;
import DTOs.MoveEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InspectMoveTest {

    @Test
    public void readMoveSimpleRFieldTest(){
        String direction = "R";
        InspectMove inspectMove = new InspectMove(direction);

        Assertions.assertEquals('R', inspectMove.getMove());
        Assertions.assertEquals(MoveEnum.SIMPLE, inspectMove.getMoveEnum());
    }

    @Test
    public void readMoveFieldPrimRTest(){
        String direction = "R'";
        InspectMove inspectMove = new InspectMove(direction);

        Assertions.assertEquals('R', inspectMove.getMove());
        Assertions.assertEquals(MoveEnum.PRIM, inspectMove.getMoveEnum());

    }

    @Test
    public void readMoveFieldDoubleRTest(){
        String direction = "R2";
        InspectMove inspectMove = new InspectMove(direction);

        Assertions.assertEquals('R', inspectMove.getMove());
        Assertions.assertEquals(MoveEnum.DOUBLE, inspectMove.getMoveEnum());

    }

}
