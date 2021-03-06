package cubes.cube4x4.moves.basic.twin;

import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveDTwinTest {
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
    public void testMoveDTwin(){
        //given
        char[][] expected = new char[][] {
                {   'r','r','b','b',    'r','r','b','b',    'g','g','w','w',    'g','g','w','w'},
                {   'g','g','b','b',    'g','g','b','b',    'r','r','o','o',    'r','r','o','o'},
                {   'g','g','o','o',    'g','g','o','o',    'y','y','b','b',    'y','y','b','b'},
                {   'y','y','o','o',    'y','y','o','o',    'r','r','g','g',    'r','r','g','g'},
                {   'y','y','b','b',    'y','y','b','b',    'y','y','w','w',    'y','y','w','w'},
                {   'w','w','o','o',    'w','w','o','o',    'r','r','w','w',    'r','r','w','w'}
        };
        //when
        cube4x4.move("Dw");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveDTwinprim(){
        //given
        char[][] expected = new char[][] {
                {   'r','r','b','b',    'r','r','b','b',    'g','g','w','w',    'g','g','w','w'},
                {   'b','b','o','o',    'b','b','o','o',    'g','g','r','r',    'g','g','r','r'},
                {   'g','g','o','o',    'g','g','o','o',    'w','w','r','r',    'w','w','r','r'},
                {   'y','y','o','o',    'y','y','o','o',    'w','w','y','y',    'w','w','y','y'},
                {   'y','y','b','b',    'y','y','b','b',    'y','y','b','b',    'y','y','b','b'},
                {   'w','w','o','o',    'w','w','o','o',    'r','r','g','g',    'r','r','g','g'}
        };
        //when
        cube4x4.move("Dw2");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveDTwindouble(){
        //given
        char[][] expected = new char[][] {
                {   'r','r','b','b',    'r','r','b','b',    'g','g','w','w',    'g','g','w','w'},
                {   'b','b','o','o',    'b','b','o','o',    'g','g','r','r',    'g','g','r','r'},
                {   'g','g','o','o',    'g','g','o','o',    'w','w','r','r',    'w','w','r','r'},
                {   'y','y','o','o',    'y','y','o','o',    'w','w','y','y',    'w','w','y','y'},
                {   'y','y','b','b',    'y','y','b','b',    'y','y','b','b',    'y','y','b','b'},
                {   'w','w','o','o',    'w','w','o','o',    'r','r','g','g',    'r','r','g','g'}
        };
        //when
        cube4x4.move("Dw2");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveDTwinDTwinprim(){
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
        cube4x4.move("Dw");
        cube4x4.move("Dw'");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveDoubleDTwindouble(){
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
        cube4x4.move("Dw2");
        cube4x4.move("Dw2");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
}
