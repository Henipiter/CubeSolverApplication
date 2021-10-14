package cubes.cube4x4.moves.basic.inner;

import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveRInTest {
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
    public void testMoveRIn(){
        //given
        char[][] expected = new char[][] {
                {   'r','r','b','b',    'r','r','b','b',    'g','g','r','w',    'g','g','r','w'},
                {   'r','r','o','g',    'r','r','o','g',    'o','o','y','b',    'o','o','y','b'},
                {   'g','g','o','o',    'g','g','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'y','y','o','o',    'y','y','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'y','y','b','b',    'y','y','b','b',    'g','g','g','r',    'g','g','g','r'},
                {   'w','w','w','o',    'w','w','w','o',    'b','b','b','y',    'b','b','b','y'}
        };
        //when
        cube4x4.move("r");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveRInprim(){
        //given
        char[][] expected = new char[][] {
                {   'r','r','y','b',    'r','r','y','b',    'g','g','o','w',    'g','g','o','w'},
                {   'r','r','r','g',    'r','r','r','g',    'o','o','b','b',    'o','o','b','b'},
                {   'g','g','o','o',    'g','g','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'y','y','o','o',    'y','y','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'y','y','b','b',    'y','y','b','b',    'g','g','w','r',    'g','g','w','r'},
                {   'w','w','g','o',    'w','w','g','o',    'b','b','b','y',    'b','b','b','y'}
        };
        //when
        cube4x4.move("r'");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveRIndouble(){
        //given
        char[][] expected = new char[][] {
                {   'r','r','b','b',    'r','r','b','b',    'g','g','g','w',    'g','g','g','w'},
                {   'r','r','w','g',    'r','r','w','g',    'o','o','b','b',    'o','o','b','b'},
                {   'g','g','o','o',    'g','g','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'y','y','o','o',    'y','y','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'y','y','y','b',    'y','y','y','b',    'g','g','o','r',    'g','g','o','r'},
                {   'w','w','r','o',    'w','w','r','o',    'b','b','b','y',    'b','b','b','y'}
        };
        //when
        cube4x4.move("r2");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveRInRInprim(){
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
        cube4x4.move("r");
        cube4x4.move("r'");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveDoubleRIndouble(){
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
        cube4x4.move("r2");
        cube4x4.move("r2");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
}
