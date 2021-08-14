package cubes.cube4x4.moves.basic.rotate;

import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveXTest {
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
    public void testMoveX(){
        //given
        char[][] expected = new char[][] {
                {   'y','y','b','b',    'y','y','b','b',    'g','g','r','r',    'g','g','r','r'},
                {   'w','w','o','o',    'w','w','o','o',    'b','b','y','y',    'b','b','y','y'},
                {   'o','o','w','w',    'o','o','w','w',    'g','g','y','y',    'g','g','y','y',},
                {   'o','o','w','w',    'o','o','w','w',    'y','y','r','r',    'y','y','r','r'},
                {   'o','o','b','b',    'o','o','b','b',    'r','r','g','g',    'r','r','g','g'},
                {   'g','g','w','w',    'g','g','w','w',    'r','r','b','b',    'r','r','b','b'}
        };
        //when
        cube4x4.moveUsingString("X");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveXprim(){
        //given
        char[][] expected = new char[][] {
                {   'b','b','y','y',    'b','b','y','y',    'w','w','o','o',    'w','w','o','o'},
                {   'g','g','r','r',    'g','g','r','r',    'y','y','b','b',    'y','y','b','b'},
                {   'y','y','g','g',    'y','y','g','g',    'w','w','o','o',    'w','w','o','o'},
                {   'r','r','y','y',    'r','r','y','y',    'w','w','o','o',    'w','w','o','o'},
                {   'r','r','b','b',    'r','r','b','b',    'g','g','w','w',    'g','g','w','w'},
                {   'r','r','g','g',    'r','r','g','g',    'o','o','b','b',    'o','o','b','b'}
        };
        //when
        cube4x4.moveUsingString("X'");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveXdouble(){
        //given
        char[][] expected = new char[][] {
                {   'o','o','b','b',    'o','o','b','b',    'r','r','g','g',    'r','r','g','g'},
                {   'g','g','w','w',    'g','g','w','w',    'r','r','b','b',    'r','r','b','b'},
                {   'w','w','y','y',    'w','w','y','y',    'o','o','g','g',    'o','o','g','g'},
                {   'w','w','r','r',    'w','w','r','r',    'o','o','y','y',    'o','o','y','y'},
                {   'b','b','y','y',    'b','b','y','y',    'w','w','o','o',    'w','w','o','o'},
                {   'g','g','r','r',    'g','g','r','r',    'y','y','b','b',    'y','y','b','b'}
        };
        //when
        cube4x4.moveUsingString("X2");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveXXprim(){
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
        cube4x4.moveUsingString("X");
        cube4x4.moveUsingString("X'");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveDoubleXdouble(){
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
        cube4x4.moveUsingString("X2");
        cube4x4.moveUsingString("X2");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
}
