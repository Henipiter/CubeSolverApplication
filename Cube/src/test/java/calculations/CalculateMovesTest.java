package calculations;

import DTOs.InspectMove;
import DTOs.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CalculateMovesTest {

    @Test
    void reduceRepeatingMoves(){
        //given
        ArrayList<Move> input = InspectMove.createAndReturnArrayListFromString("F F2 R R2 R'");
        String expected = "F' R2";
        //when
        ArrayList<Move> result = CalculateMoves.reduceRepeatingMoves(input);
        //then
        Assertions.assertEquals(expected, InspectMove.algorithmToString(result));
    }

}
