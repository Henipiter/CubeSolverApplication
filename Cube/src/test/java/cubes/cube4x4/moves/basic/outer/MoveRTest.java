package cubes.cube4x4.moves.basic.outer;

import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveRTest {
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
    public void testMoveR(){
        //given
        char[][] expected = new char[][] {
                {   'r','r','b','b',    'r','r','b','b',    'g','g','w','r',    'g','g','w','r'},
                {   'r','r','g','o',    'r','r','g','o',    'o','o','b','y',    'o','o','b','y'},
                {   'g','g','o','o',    'g','g','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'o','o','w','w',    'o','o','w','w',    'y','y','r','r',    'y','y','r','r'},
                {   'y','y','b','b',    'y','y','b','b',    'g','g','r','g',    'g','g','r','g'},
                {   'w','w','o','w',    'w','w','o','w',    'b','b','y','b',    'b','b','y','b'}
        };
        //when
        cube4x4.moveUsingString("R");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveRprim(){
        //given
        char[][] expected = new char[][] {
                {   'r','r','b','y',    'r','r','b','y',    'g','g','w','o',    'g','g','w','o'},
                {   'r','r','g','r',    'r','r','g','r',    'o','o','b','b',    'o','o','b','b'},
                {   'g','g','o','o',    'g','g','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'r','r','y','y',    'r','r','y','y',    'w','w','o','o',    'w','w','o','o'},
                {   'y','y','b','b',    'y','y','b','b',    'g','g','r','w',    'g','g','r','w'},
                {   'w','w','o','g',    'w','w','o','g',    'b','b','y','b',    'b','b','y','b'}
        };
        //when
        cube4x4.moveUsingString("R'");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveRdouble(){
        //given
        char[][] expected = new char[][] {
                {   'r','r','b','b',    'r','r','b','b',    'g','g','w','g',    'g','g','w','g'},
                {   'r','r','g','w',    'r','r','g','w',    'o','o','b','b',    'o','o','b','b'},
                {   'g','g','o','o',    'g','g','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'w','w','r','r',    'w','w','r','r',    'o','o','y','y',    'o','o','y','y'},
                {   'y','y','b','y',    'y','y','b','y',    'g','g','r','o',    'g','g','r','o'},
                {   'w','w','o','r',    'w','w','o','r',    'b','b','y','b',    'b','b','y','b'}
        };
        //when
        cube4x4.moveUsingString("R2");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveRRprim(){
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
        cube4x4.moveUsingString("R");
        cube4x4.moveUsingString("R'");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveDoubleRdouble(){
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
        cube4x4.moveUsingString("R2");
        cube4x4.moveUsingString("R2");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
}
