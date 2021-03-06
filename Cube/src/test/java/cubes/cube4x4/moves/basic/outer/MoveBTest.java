package cubes.cube4x4.moves.basic.outer;

import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveBTest {
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
    public void testMoveB(){
        //given
        char[][] expected = new char[][] {
                {   'y','y','r','r',    'r','r','b','b',    'g','g','w','w',    'g','g','w','w'},
                {   'g','g','y','y',    'r','r','g','g',    'o','o','b','b',    'o','o','b','b'},
                {   'b','g','o','o',    'b','g','o','o',    'r','y','w','w',    'r','y','w','w'},
                {   'g','y','o','o',    'g','y','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'y','y','b','b',    'y','y','b','b',    'g','g','r','r',    'g','g','r','r'},
                {   'o','o','y','y',    'o','o','y','y',    'w','w','b','b',    'w','w','b','b'}
        };
        //when
        cube4x4.move("B");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveBprim(){
        //given
        char[][] expected = new char[][] {
                {   'y','y','g','g',    'r','r','b','b',    'g','g','w','w',    'g','g','w','w'},
                {   'r','r','y','y',    'r','r','g','g',    'o','o','b','b',    'o','o','b','b'},
                {   'r','g','o','o',    'r','g','o','o',    'g','y','w','w',    'g','y','w','w'},
                {   'r','y','o','o',    'r','y','o','o',    'b','r','w','w',    'b','r','w','w'},
                {   'y','y','b','b',    'y','y','b','b',    'g','g','r','r',    'g','g','r','r'},
                {   'b','b','w','w',    'b','b','w','w',    'y','y','o','o',    'y','y','o','o'}
        };
        //when
        cube4x4.move("B'");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveBdouble(){
        //given
        char[][] expected = new char[][] {
                {   'g','g','r','r',    'r','r','b','b',    'g','g','w','w',    'g','g','w','w'},
                {   'b','b','r','r',    'r','r','g','g',    'o','o','b','b',    'o','o','b','b'},
                {   'r','g','o','o',    'r','g','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'y','y','o','o',    'y','y','o','o',    'g','r','w','w',    'g','r','w','w'},
                {   'y','y','b','b',    'y','y','b','b',    'g','g','r','r',    'g','g','r','r'},
                {   'y','y','b','b',    'y','y','b','b',    'o','o','w','w',    'o','o','w','w'}
        };
        //when
        cube4x4.move("B2");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveBBprim(){
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
        cube4x4.move("B");
        cube4x4.move("B'");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveDoubleBdouble(){
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
        cube4x4.move("B2");
        cube4x4.move("B2");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
}
