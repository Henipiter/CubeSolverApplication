package DTOs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

class InspectMoveTest {

    @ParameterizedTest
    @CsvSource({
            "R F', F R'",
            "R' F', F R",
            "R F, F' R'",
            "R' F, F' R",

    })
    void getReverseAlgorithm(String given, String expected) {
        //when
        ArrayList<Move> result = InspectMove.getReverseAlgorithm(InspectMove.stringToMoveList(given));
        //then
        Assertions.assertEquals(expected, InspectMove.moveListToString(result));
    }

}