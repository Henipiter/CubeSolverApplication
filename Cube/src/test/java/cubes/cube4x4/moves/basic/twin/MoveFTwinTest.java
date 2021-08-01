package cubes.cube4x4.moves.basic.twin;

import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveFTwinTest {
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
    public void testMoveFTwin(){
        //given
        char[][] expected = new char[][] {
                {   'r','r','b','b',    'r','r','b','b',    'w','w','o','o',    'w','w','o','o'},
                {   'r','r','g','g',    'r','r','g','g',    'w','w','o','o',    'w','w','o','o'},
                {   'g','g','o','o',    'g','g','o','o',    'y','y','b','b',    'y','y','b','b'},
                {   'y','y','g','g',    'y','y','g','g',    'r','r','w','w',    'r','r','w','w'},
                {   'g','g','y','y',    'g','g','y','y',    'r','r','b','b',    'r','r','b','b'},
                {   'w','w','o','o',    'w','w','o','o',    'b','b','y','y',    'b','b','y','y'}
        };
        //when
        cube4x4.moveUsingString("Fw");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveFTwinprim(){
        //given
        char[][] expected = new char[][] {
                {   'r','r','b','b',    'r','r','b','b',    'o','o','w','w',    'o','o','w','w'},
                {   'r','r','g','g',    'r','r','g','g',    'o','o','w','w',    'o','o','w','w'},
                {   'g','g','w','w',    'g','g','w','w',    'y','y','g','g',    'y','y','g','g'},
                {   'y','y','b','b',    'y','y','b','b',    'r','r','o','o',    'r','r','o','o'},
                {   'b','b','r','r',    'b','b','r','r',    'y','y','g','g',    'y','y','g','g'},
                {   'w','w','o','o',    'w','w','o','o',    'b','b','y','y',    'b','b','y','y'}
        };
        //when
        cube4x4.moveUsingString("Fw'");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveFTwindouble(){
        //given
        char[][] expected = new char[][] {
                {   'r','r','b','b',    'r','r','b','b',    'b','b','o','o',    'b','b','o','o'},
                {   'r','r','g','g',    'r','r','g','g',    'w','w','g','g',    'w','w','g','g'},
                {   'g','g','w','w',    'g','g','w','w',    'y','y','o','o',    'y','y','o','o'},
                {   'y','y','w','w',    'y','y','w','w',    'r','r','o','o',    'r','r','o','o'},
                {   'r','r','g','g',    'r','r','g','g',    'b','b','y','y',    'b','b','y','y'},
                {   'w','w','o','o',    'w','w','o','o',    'b','b','y','y',    'b','b','y','y'}
        };
        //when
        cube4x4.moveUsingString("Fw2");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveFTwinFTwinprim(){
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
        cube4x4.moveUsingString("Fw");
        cube4x4.moveUsingString("Fw'");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveDoubleFTwindouble(){
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
        cube4x4.moveUsingString("Fw2");
        cube4x4.moveUsingString("Fw2");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
}
