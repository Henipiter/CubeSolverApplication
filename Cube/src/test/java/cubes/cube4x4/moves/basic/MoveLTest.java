package cubes.cube4x4.moves.basic;

import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveLTest {
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
    public void testMoveL(){
        //given
        char[][] expected = new char[][] {
                {   'b','r','b','b',    'b','r','b','b',    'w','g','w','w',    'w','g','w','w'},
                {   'g','r','g','g',    'g','r','g','g',    'y','o','b','b',    'y','o','b','b'},
                {   'y','y','g','g',    'y','y','g','g',    'w','w','o','o',    'w','w','o','o'},
                {   'y','y','o','o',    'y','y','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'r','y','b','b',    'r','y','b','b',    'g','g','r','r',    'g','g','r','r'},
                {   'r','w','o','o',    'r','w','o','o',    'o','b','y','y',    'o','b','y','y'}
        };
        //when
        cube4x4.moveUsingString("L");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveLprim(){
        //given
        char[][] expected = new char[][] {
                {   'y','r','b','b',    'y','r','b','b',    'g','g','w','w',    'g','g','w','w'},
                {   'w','r','g','g',    'w','r','g','g',    'b','o','b','b',    'b','o','b','b'},
                {   'o','o','w','w',    'o','o','w','w',    'g','g','y','y',    'g','g','y','y',},
                {   'y','y','o','o',    'y','y','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'o','y','b','b',    'o','y','b','b',    'r','g','r','r',    'r','g','r','r'},
                {   'g','w','o','o',    'g','w','o','o',    'r','b','y','y',    'r','b','y','y'}
        };
        //when
        cube4x4.moveUsingString("L'");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveLdouble(){
        //given
        char[][] expected = new char[][] {
                {   'o','r','b','b',    'o','r','b','b',    'r','g','w','w',    'r','g','w','w'},
                {   'g','r','g','g',    'g','r','g','g',    'r','o','b','b',    'r','o','b','b'},
                {   'w','w','y','y',    'w','w','y','y',    'o','o','g','g',    'o','o','g','g'},
                {   'y','y','o','o',    'y','y','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'b','y','b','b',    'b','y','b','b',    'w','g','r','r',    'w','g','r','r'},
                {   'g','w','o','o',    'g','w','o','o',    'y','b','y','y',    'y','b','y','y'}
        };
        //when
        cube4x4.moveUsingString("L2");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveLLprim(){
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
        cube4x4.moveUsingString("L");
        cube4x4.moveUsingString("L'");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveDoubleLdouble(){
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
        cube4x4.moveUsingString("L2");
        cube4x4.moveUsingString("L2");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
}
