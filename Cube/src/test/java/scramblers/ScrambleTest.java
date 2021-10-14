package scramblers;

import DTOs.InspectMove;
import DTOs.Move;
import cubes.Cube2x2;
import cubes.Cube4x4;
import cubes.CubePyraminx;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

public class ScrambleTest {

    @Test
    void scramble2x2() {
        //given
        ScramblerFactory scramblerFactory = new ScramblerFactory();
        //when
        ArrayList<Move> scrambleAlg = scramblerFactory.getScramble(new Cube2x2());
        //then
        System.out.println(InspectMove.moveListToString(scrambleAlg));
    }

    @Test
    void scramble4x4() {
        //given
        ScramblerFactory scramblerFactory = new ScramblerFactory();
        //when
        ArrayList<Move> scrambleAlg = scramblerFactory.getScramble(new Cube4x4());
        //then
        System.out.println(InspectMove.moveListToString(scrambleAlg));
    }

    @Test
    void scramblePyraminx() {
        //given
        ScramblerFactory scramblerFactory = new ScramblerFactory();
        //when
        ArrayList<Move> scrambleAlg = scramblerFactory.getScramble(new CubePyraminx());
        //then
        System.out.println(InspectMove.moveListToString(scrambleAlg));
    }

    @ParameterizedTest
    @CsvSource({"true, 0,1", "true,0,1", "false,0,2", "true, 3,3", "true, 0,6"})
    void isMovesOpposite(boolean expected, int value1, int value2) {
        //given
        Scrambler2x2 scrambler2x2 = new Scrambler2x2();
        //when
        boolean result = scrambler2x2.isMovesOpposite(value1, value2);
        //then
        Assertions.assertEquals(expected, result);
    }

}
