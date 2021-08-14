package cubes.cube4x4.moves.basic.twin;

import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveBTwinTest {
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
    public void testMoveBTwin(){
        //given
        char[][] expected = new char[][] {
                {   'y','y','r','r',    'y','y','r','r',    'g','g','w','w',    'g','g','w','w'},
                {   'g','g','y','y',    'g','g','y','y',    'o','o','b','b',    'o','o','b','b'},
                {   'b','b','o','o',    'b','b','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'g','g','o','o',    'g','g','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'y','y','b','b',    'y','y','b','b',    'g','g','r','r',    'g','g','r','r'},
                {   'o','o','y','y',    'o','o','y','y',    'w','w','b','b',    'w','w','b','b'}
        };
        //when
        cube4x4.moveUsingString("Bw");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveBTwinprim(){
        //given
        char[][] expected = new char[][] {
                {   'y','y','g','g',    'y','y','g','g',    'g','g','w','w',    'g','g','w','w'},
                {   'r','r','y','y',    'r','r','y','y',    'o','o','b','b',    'o','o','b','b'},
                {   'r','r','o','o',    'r','r','o','o',    'g','g','w','w',    'g','g','w','w'},
                {   'r','r','o','o',    'r','r','o','o',    'b','b','w','w',    'b','b','w','w'},
                {   'y','y','b','b',    'y','y','b','b',    'g','g','r','r',    'g','g','r','r'},
                {   'b','b','w','w',    'b','b','w','w',    'y','y','o','o',    'y','y','o','o'}
        };
        //when
        cube4x4.moveUsingString("Bw'");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveBTwindouble(){
        //given
        char[][] expected = new char[][] {
                {   'g','g','r','r',    'g','g','r','r',    'g','g','w','w',    'g','g','w','w'},
                {   'b','b','r','r',    'b','b','r','r',    'o','o','b','b',    'o','o','b','b'},
                {   'r','r','o','o',    'r','r','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'y','y','o','o',    'y','y','o','o',    'g','g','w','w',    'g','g','w','w'},
                {   'y','y','b','b',    'y','y','b','b',    'g','g','r','r',    'g','g','r','r'},
                {   'y','y','b','b',    'y','y','b','b',    'o','o','w','w',    'o','o','w','w'}
        };
        //when
        cube4x4.moveUsingString("Bw2");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveBTwinBTwinprim(){
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
        cube4x4.moveUsingString("Bw");
        cube4x4.moveUsingString("Bw'");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveDoubleBTwindouble(){
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
        cube4x4.moveUsingString("Bw2");
        cube4x4.moveUsingString("Bw2");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
}
