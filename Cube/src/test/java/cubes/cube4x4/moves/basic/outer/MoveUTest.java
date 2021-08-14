package cubes.cube4x4.moves.basic.outer;

import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveUTest {
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
    public void testMoveU(){
        //given
        char[][] expected = new char[][] {
                {   'g','g','r','r',    'g','g','r','r',    'w','w','b','b',    'w','w','b','b'},
                {   'r','r','g','g',    'r','r','g','g',    'o','o','b','b',    'o','o','b','b'},
                {   'y','y','b','b',    'g','g','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'w','w','o','o',    'y','y','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'o','o','y','y',    'y','y','b','b',    'g','g','r','r',    'g','g','r','r'},
                {   'o','o','g','g',    'w','w','o','o',    'b','b','y','y',    'b','b','y','y'}
        };
        //when
        cube4x4.moveUsingString("U");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveUprim(){
        //given
        char[][] expected = new char[][] {
                {   'b','b','w','w',    'b','b','w','w',    'r','r','g','g',    'r','r','g','g'},
                {   'r','r','g','g',    'r','r','g','g',    'o','o','b','b',    'o','o','b','b'},
                {   'o','o','w','w',    'g','g','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'b','b','y','y',    'y','y','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'g','g','o','o',    'y','y','b','b',    'g','g','r','r',    'g','g','r','r'},
                {   'y','y','o','o',    'w','w','o','o',    'b','b','y','y',    'b','b','y','y'}
        };
        //when
        cube4x4.moveUsingString("U'");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveUdouble(){
        //given
        char[][] expected = new char[][] {
                {   'w','w','g','g',    'w','w','g','g',    'b','b','r','r',    'b','b','r','r'},
                {   'r','r','g','g',    'r','r','g','g',    'o','o','b','b',    'o','o','b','b'},
                {   'o','o','y','y',    'g','g','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'o','o','g','g',    'y','y','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'o','o','w','w',    'y','y','b','b',    'g','g','r','r',    'g','g','r','r'},
                {   'b','b','y','y',    'w','w','o','o',    'b','b','y','y',    'b','b','y','y'}
        };
        //when
        cube4x4.moveUsingString("U2");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveUUprim(){
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
        cube4x4.moveUsingString("U");
        cube4x4.moveUsingString("U'");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveDoubleUdouble(){
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
        cube4x4.moveUsingString("U2");
        cube4x4.moveUsingString("U2");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
}
