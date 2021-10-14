package cubes.cube4x4.moves.basic.inner;

import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveUInTest {
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
    public void testMoveUIn(){
        //given
        char[][] expected = new char[][] {
                {   'r','r','b','b',    'r','r','b','b',    'g','g','w','w',    'g','g','w','w'},
                {   'r','r','g','g',    'r','r','g','g',    'o','o','b','b',    'o','o','b','b'},
                {   'g','g','o','o',    'y','y','b','b',    'y','y','w','w',    'y','y','w','w'},
                {   'y','y','o','o',    'w','w','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'y','y','b','b',    'o','o','y','y',    'g','g','r','r',    'g','g','r','r'},
                {   'w','w','o','o',    'o','o','g','g',    'b','b','y','y',    'b','b','y','y'}
        };
        //when
        cube4x4.move("u");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveUInprim(){
        //given
        char[][] expected = new char[][] {
                {   'r','r','b','b',    'r','r','b','b',    'g','g','w','w',    'g','g','w','w'},
                {   'r','r','g','g',    'r','r','g','g',    'o','o','b','b',    'o','o','b','b'},
                {   'g','g','o','o',    'o','o','w','w',    'y','y','w','w',    'y','y','w','w'},
                {   'y','y','o','o',    'b','b','y','y',    'r','r','w','w',    'r','r','w','w'},
                {   'y','y','b','b',    'g','g','o','o',    'g','g','r','r',    'g','g','r','r'},
                {   'w','w','o','o',    'y','y','o','o',    'b','b','y','y',    'b','b','y','y'}
        };
        //when
        cube4x4.move("u'");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveUIndouble(){
        //given
        char[][] expected = new char[][] {
                {   'r','r','b','b',    'r','r','b','b',    'g','g','w','w',    'g','g','w','w'},
                {   'r','r','g','g',    'r','r','g','g',    'o','o','b','b',    'o','o','b','b'},
                {   'g','g','o','o',    'o','o','y','y',    'y','y','w','w',    'y','y','w','w'},
                {   'y','y','o','o',    'o','o','g','g',    'r','r','w','w',    'r','r','w','w'},
                {   'y','y','b','b',    'o','o','w','w',    'g','g','r','r',    'g','g','r','r'},
                {   'w','w','o','o',    'b','b','y','y',    'b','b','y','y',    'b','b','y','y'}
        };
        //when
        cube4x4.move("u2");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveUInUInprim(){
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
        cube4x4.move("u");
        cube4x4.move("u'");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveDoubleUIndouble(){
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
        cube4x4.move("u2");
        cube4x4.move("u2");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
}
