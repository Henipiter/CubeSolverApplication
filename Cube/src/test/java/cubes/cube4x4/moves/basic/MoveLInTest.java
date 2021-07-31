package cubes.cube4x4.moves.basic;

import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveLInTest {
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
    public void testMoveLIn(){
        //given
        char[][] expected = new char[][] {
                {   'r','b','b','b',    'r','b','b','b',    'g','w','w','w',    'g','w','w','w'},
                {   'r','g','g','g',    'r','g','g','g',    'o','y','b','b',    'o','y','b','b'},
                {   'g','g','o','o',    'g','g','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'y','y','o','o',    'y','y','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'y','r','b','b',    'y','r','b','b',    'g','g','r','r',    'g','g','r','r'},
                {   'w','r','o','o',    'w','r','o','o',    'b','o','y','y',    'b','o','y','y'}
        };
        //when
        cube4x4.moveUsingString("l");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveLInprim(){
        //given
        char[][] expected = new char[][] {
                {   'r','y','b','b',    'r','y','b','b',    'g','g','w','w',    'g','g','w','w'},
                {   'r','w','g','g',    'r','w','g','g',    'o','b','b','b',    'o','b','b','b'},
                {   'g','g','o','o',    'g','g','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'y','y','o','o',    'y','y','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'y','o','b','b',    'y','o','b','b',    'g','r','r','r',    'g','r','r','r'},
                {   'w','g','o','o',    'w','g','o','o',    'b','r','y','y',    'b','r','y','y'}
        };
        //when
        cube4x4.moveUsingString("l'");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveLIndouble(){
        //given
        char[][] expected = new char[][] {
                {   'r','o','b','b',    'r','o','b','b',    'g','r','w','w',    'g','r','w','w'},
                {   'r','g','g','g',    'r','g','g','g',    'o','r','b','b',    'o','r','b','b'},
                {   'g','g','o','o',    'g','g','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'y','y','o','o',    'y','y','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'y','b','b','b',    'y','b','b','b',    'g','w','r','r',    'g','w','r','r'},
                {   'w','g','o','o',    'w','g','o','o',    'b','y','y','y',    'b','y','y','y'}
        };
        //when
        cube4x4.moveUsingString("l2");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveLInLInprim(){
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
        cube4x4.moveUsingString("l");
        cube4x4.moveUsingString("l'");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveDoubleLIndouble(){
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
        cube4x4.moveUsingString("l2");
        cube4x4.moveUsingString("l2");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
}
