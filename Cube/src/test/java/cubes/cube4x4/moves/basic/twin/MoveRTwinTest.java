package cubes.cube4x4.moves.basic.twin;

import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveRTwinTest {
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
    public void testMoveRTwin(){
        //given
        char[][] expected = new char[][] {
                {   'r','r','b','b',    'r','r','b','b',    'g','g','r','r',    'g','g','r','r'},
                {   'r','r','o','o',    'r','r','o','o',    'o','o','y','y',    'o','o','y','y'},
                {   'g','g','o','o',    'g','g','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'o','o','w','w',    'o','o','w','w',    'y','y','r','r',    'y','y','r','r'},
                {   'y','y','b','b',    'y','y','b','b',    'g','g','g','g',    'g','g','g','g'},
                {   'w','w','w','w',    'w','w','w','w',    'b','b','b','b',    'b','b','b','b'}
        };
        //when
        cube4x4.moveUsingString("Rw");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveRTwinprim(){
        //given
        char[][] expected = new char[][] {
                {   'r','r','y','y',    'r','r','y','y',    'g','g','o','o',    'g','g','o','o'},
                {   'r','r','r','r',    'r','r','r','r',    'o','o','b','b',    'o','o','b','b'},
                {   'g','g','o','o',    'g','g','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'r','r','y','y',    'r','r','y','y',    'w','w','o','o',    'w','w','o','o'},
                {   'y','y','b','b',    'y','y','b','b',    'g','g','w','w',    'g','g','w','w'},
                {   'w','w','g','g',    'w','w','g','g',    'b','b','b','b',    'b','b','b','b'}
        };
        //when
        cube4x4.moveUsingString("Rw'");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveRTwindouble(){
        //given
        char[][] expected = new char[][] {
                {   'r','r','b','b',    'r','r','b','b',    'g','g','g','g',    'g','g','g','g'},
                {   'r','r','w','w',    'r','r','w','w',    'o','o','b','b',    'o','o','b','b'},
                {   'g','g','o','o',    'g','g','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'w','w','r','r',    'w','w','r','r',    'o','o','y','y',    'o','o','y','y'},
                {   'y','y','y','y',    'y','y','y','y',    'g','g','o','o',    'g','g','o','o'},
                {   'w','w','r','r',    'w','w','r','r',    'b','b','b','b',    'b','b','b','b'}
        };
        //when
        cube4x4.moveUsingString("Rw2");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveRTwinRTwinprim(){
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
        cube4x4.moveUsingString("Rw");
        cube4x4.moveUsingString("Rw'");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveDoubleRTwindouble(){
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
        cube4x4.moveUsingString("Rw2");
        cube4x4.moveUsingString("Rw2");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
}
