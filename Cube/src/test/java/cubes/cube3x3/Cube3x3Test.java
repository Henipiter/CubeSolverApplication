package cubes.cube3x3;

import cubes.Cube3x3;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class Cube3x3Test {

    @Test
    void testEmptyMethod(){
        //given
        char[][] expected = new char[][] {
                {'x','x','x','x','x','x','x','x'},
                {'x','x','x','x','x','x','x','x'},
                {'x','x','x','x','x','x','x','x'},
                {'x','x','x','x','x','x','x','x'},
                {'x','x','x','x','x','x','x','x'},
                {'x','x','x','x','x','x','x','x'},
        };
        //when
        Cube3x3 cube3x3 = Cube3x3.empty();
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());





    }

}
