package DTOs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class AlgorithmTest {

    @Test
    void shouldThrowNullPointerExceptionIfAlgNotLoaded() {
        //given
        Algorithm.loadPermutations();
        //expected
        Assertions.assertThrows(NullPointerException.class,
                () -> Algorithm.getPermAlg("H"));
    }

    @Test
    void shouldReturnMoveListWhenCacheLoaded() {
        //given
        ArrayList<Move> expected = InspectMove.stringToMoveList("R U2 R' U' R U2 L' U R' U' L");
        Algorithm.loadPermutations();
        //when
        ArrayList<Move> alg = Algorithm.getPermAlg("J");
        //then
        Assertions.assertEquals(expected, alg);
    }

    @Test
    void shouldReturnAlgorithmWhenCacheLoaded() {
        //given
        String expected = "R U2 R' U' R U2 L' U R' U' L";
        Algorithm.loadPermutations();
        //expected
        Assertions.assertEquals(expected, Algorithm.getPerm("J"));
    }
}