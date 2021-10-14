package cubes.cube4x4.moves.basic.rotate;

import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveZTest {
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
    public void testMoveZ(){
        //given
        char[][] expected = new char[][] {
                {   'y','y','g','g',    'y','y','g','g',    'w','w','o','o',    'w','w','o','o'},
                {   'r','r','y','y',    'r','r','y','y',    'w','w','o','o',    'w','w','o','o'},
                {   'r','r','o','o',    'r','r','o','o',    'g','g','b','b',    'g','g','b','b'},
                {   'r','r','g','g',    'r','r','g','g',    'b','b','w','w',    'b','b','w','w'},
                {   'g','g','y','y',    'g','g','y','y',    'r','r','b','b',    'r','r','b','b'},
                {   'b','b','w','w',    'b','b','w','w',    'y','y','o','o',    'y','y','o','o'}
        };
        //when
        cube4x4.move("z");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveZprim(){
        //given
        char[][] expected = new char[][] {
                {   'y','y','r','r',    'y','y','r','r',    'o','o','w','w',    'o','o','w','w'},
                {   'g','g','y','y',    'g','g','y','y',    'o','o','w','w',    'o','o','w','w'},
                {   'b','b','w','w',    'b','b','w','w',    'r','r','g','g',    'r','r','g','g'},
                {   'g','g','b','b',    'g','g','b','b',    'r','r','o','o',    'r','r','o','o'},
                {   'b','b','r','r',    'b','b','r','r',    'y','y','g','g',    'y','y','g','g'},
                {   'o','o','y','y',    'o','o','y','y',    'w','w','b','b',    'w','w','b','b'}
        };
        //when
        cube4x4.move("z'");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveZdouble(){
        //given
        char[][] expected = new char[][] {
                {   'g','g','r','r',    'g','g','r','r',    'b','b','o','o',    'b','b','o','o'},
                {   'b','b','r','r',    'b','b','r','r',    'w','w','g','g',    'w','w','g','g'},
                {   'r','r','w','w',    'r','r','w','w',    'y','y','o','o',    'y','y','o','o'},
                {   'y','y','w','w',    'y','y','w','w',    'g','g','o','o',    'g','g','o','o'},
                {   'r','r','g','g',    'r','r','g','g',    'b','b','y','y',    'b','b','y','y'},
                {   'y','y','b','b',    'y','y','b','b',    'o','o','w','w',    'o','o','w','w'}
        };
        //when
        cube4x4.move("z2");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveZZprim(){
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
        cube4x4.move("z");
        cube4x4.move("z'");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveDoubleZdouble(){
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
        cube4x4.move("z2");
        cube4x4.move("z2");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
}
