package cubes.cube4x4.moves.basic.twin;

import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveLTwinTest {
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
    public void testMoveLTwin(){
        //given
        char[][] expected = new char[][] {
                {   'b','b','b','b',    'b','b','b','b',    'w','w','w','w',    'w','w','w','w'},
                {   'g','g','g','g',    'g','g','g','g',    'y','y','b','b',    'y','y','b','b'},
                {   'y','y','g','g',    'y','y','g','g',    'w','w','o','o',    'w','w','o','o'},
                {   'y','y','o','o',    'y','y','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'r','r','b','b',    'r','r','b','b',    'g','g','r','r',    'g','g','r','r'},
                {   'r','r','o','o',    'r','r','o','o',    'o','o','y','y',    'o','o','y','y'}
        };
        //when
        cube4x4.moveUsingString("Lw");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveLTwinprim(){
        //given
        char[][] expected = new char[][] {
                {   'y','y','b','b',    'y','y','b','b',    'g','g','w','w',    'g','g','w','w'},
                {   'w','w','g','g',    'w','w','g','g',    'b','b','b','b',    'b','b','b','b'},
                {   'o','o','w','w',    'o','o','w','w',    'g','g','y','y',    'g','g','y','y',},
                {   'y','y','o','o',    'y','y','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'o','o','b','b',    'o','o','b','b',    'r','r','r','r',    'r','r','r','r'},
                {   'g','g','o','o',    'g','g','o','o',    'r','r','y','y',    'r','r','y','y'}
        };
        //when
        cube4x4.moveUsingString("Lw'");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());
    }

    @Test
    public void testMoveLTwindouble(){
        //given
        char[][] expected = new char[][] {
                {   'o','o','b','b',    'o','o','b','b',    'r','r','w','w',    'r','r','w','w'},
                {   'g','g','g','g',    'g','g','g','g',    'r','r','b','b',    'r','r','b','b'},
                {   'w','w','y','y',    'w','w','y','y',    'o','o','g','g',    'o','o','g','g'},
                {   'y','y','o','o',    'y','y','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'b','b','b','b',    'b','b','b','b',    'w','w','r','r',    'w','w','r','r'},
                {   'g','g','o','o',    'g','g','o','o',    'y','y','y','y',    'y','y','y','y'}
        };
        //when
        cube4x4.moveUsingString("Lw2");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveLTwinLTwinprim(){
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
        cube4x4.moveUsingString("Lw");
        cube4x4.moveUsingString("Lw'");

        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
    @Test
    public void testMoveDoubleLTwindouble(){
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
        cube4x4.moveUsingString("Lw2");
        cube4x4.moveUsingString("Lw2");
        //then
        Assert.assertArrayEquals(expected, cube4x4.getCube());


    }
}
