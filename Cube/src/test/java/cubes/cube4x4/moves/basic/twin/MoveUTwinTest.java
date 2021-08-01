package cubes.cube4x4.moves.basic.twin;

import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveUTwinTest {
// R U L D R
    //up Y, f O
    private char[][] input;
    private Cube4x4 cube4x4;

    @BeforeEach
    public void init(){
        input = new char[][] {
                {   'r','r','b','b',    'r','r','b','b',    'g','g','w','w',    'g','g','w','w'},
                {   'r','r','g','g',    'r','r','g','g',    'o','o','b','b',    'o','o','b','b'},
                {   'g','g','o','o',    'g','g','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'y','y','o','o',    'y','y','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'y','y','b','b',    'y','y','b','b',    'g','g','r','r',    'g','g','r','r'},
                {   'w','w','o','o',    'w','w','o','o',    'b','b','y','y',    'b','b','y','y'}
        };

        cube4x4 = new Cube4x4(input);
    }


    @Test
    public void testMoveUTwin(){
        //given
        char[][] expected = new char[][] {
                {   'g','g','r','r',    'g','g','r','r',    'w','w','b','b',    'w','w','b','b'},
                {   'r','r','g','g',    'r','r','g','g',    'o','o','b','b',    'o','o','b','b'},
                {   'y','y','b','b',    'y','y','b','b',    'y','y','w','w',    'y','y','w','w'},
                {   'w','w','o','o',    'w','w','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'o','o','y','y',    'o','o','y','y',    'g','g','r','r',    'g','g','r','r'},
                {   'o','o','g','g',    'o','o','g','g',    'b','b','y','y',    'b','b','y','y'}
        };
        //when
        cube4x4.moveUsingString("Uw");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveUTwinprim(){
        //given
        char[][] expected = new char[][] {
                {   'b','b','w','w',    'b','b','w','w',    'r','r','g','g',    'r','r','g','g'},
                {   'r','r','g','g',    'r','r','g','g',    'o','o','b','b',    'o','o','b','b'},
                {   'o','o','w','w',    'o','o','w','w',    'y','y','w','w',    'y','y','w','w'},
                {   'b','b','y','y',    'b','b','y','y',    'r','r','w','w',    'r','r','w','w'},
                {   'g','g','o','o',    'g','g','o','o',    'g','g','r','r',    'g','g','r','r'},
                {   'y','y','o','o',    'y','y','o','o',    'b','b','y','y',    'b','b','y','y'}
        };
        //when
        cube4x4.moveUsingString("Uw'");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveUTwindouble(){
        //given
        char[][] expected = new char[][] {
                {   'w','w','g','g',    'w','w','g','g',    'b','b','r','r',    'b','b','r','r'},
                {   'r','r','g','g',    'r','r','g','g',    'o','o','b','b',    'o','o','b','b'},
                {   'o','o','y','y',    'o','o','y','y',    'y','y','w','w',    'y','y','w','w'},
                {   'o','o','g','g',    'o','o','g','g',    'r','r','w','w',    'r','r','w','w'},
                {   'o','o','w','w',    'o','o','w','w',    'g','g','r','r',    'g','g','r','r'},
                {   'b','b','y','y',    'b','b','y','y',    'b','b','y','y',    'b','b','y','y'}
        };
        //when
        cube4x4.moveUsingString("Uw2");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveUTwinUTwinprim(){
        //given
        char[][] expected = new char[][] {
                {   'r','r','b','b',    'r','r','b','b',    'g','g','w','w',    'g','g','w','w'},
                {   'r','r','g','g',    'r','r','g','g',    'o','o','b','b',    'o','o','b','b'},
                {   'g','g','o','o',    'g','g','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'y','y','o','o',    'y','y','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'y','y','b','b',    'y','y','b','b',    'g','g','r','r',    'g','g','r','r'},
                {   'w','w','o','o',    'w','w','o','o',    'b','b','y','y',    'b','b','y','y'}
        };
        //when
        cube4x4.moveUsingString("Uw");
        cube4x4.moveUsingString("Uw'");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveDoubleUTwindouble(){
        //given
        char[][] expected = new char[][] {
                {   'r','r','b','b',    'r','r','b','b',    'g','g','w','w',    'g','g','w','w'},
                {   'r','r','g','g',    'r','r','g','g',    'o','o','b','b',    'o','o','b','b'},
                {   'g','g','o','o',    'g','g','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'y','y','o','o',    'y','y','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'y','y','b','b',    'y','y','b','b',    'g','g','r','r',    'g','g','r','r'},
                {   'w','w','o','o',    'w','w','o','o',    'b','b','y','y',    'b','b','y','y'}
        };
        //when
        cube4x4.moveUsingString("Uw2");
        cube4x4.moveUsingString("Uw2");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
}
