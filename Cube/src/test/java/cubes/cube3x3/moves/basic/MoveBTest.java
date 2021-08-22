package cubes.cube3x3.moves.basic;

import cubes.Cube3x3;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveBTest {
// R U L D R
    //up Y, f O
    private char[][] input;
    private Cube3x3 cube3x3;

    @BeforeEach
    public void init(){
        input = new char[][] {
                {'b','w','r','b','y','o','g','y'},
                {'b','b','o','y','w','g','g','r'},
                {'o','o','g','o','g','w','b','y'},
                {'w','r','g','b','g','b','r','y'},
                {'w','r','r','w','y','o','o','b'},
                {'y','o','g','y','w','r','r','w'}
        };

        cube3x3 = new Cube3x3(input);
    }


    @Test
    public void testMoveB(){
        //given
        char[][] expected = new char[][] {
                {'w','b','b','b','y','o','g','y'},
                {'o','o','w','y','w','g','g','r'},
                {'r','o','g','w','g','b','b','y'},
                {'o','r','g','b','g','b','r','y'},
                {'w','r','r','w','y','o','o','b'},
                {'g','w','w','o','r','y','y','r'}
        };
        //when
        cube3x3.moveUsingString("B");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());
    }

    @Test
    public void testMoveBprim(){
        //given
        char[][] expected = new char[][] {
                {'w','o','o','b','y','o','g','y'},
                {'b','b','w','y','w','g','g','r'},
                {'b','o','g','b','g','o','b','y'},
                {'b','r','g','w','g','r','r','y'},
                {'w','r','r','w','y','o','o','b'},
                {'r','y','y','r','o','w','w','g'}
        };
        //when
        cube3x3.moveUsingString("B'");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());
    }

    @Test
    public void testMoveBdouble(){
        //given
        char[][] expected = new char[][] {
                {'o','b','b','b','y','o','g','y'},
                {'r','w','b','y','w','g','g','r'},
                {'b','o','g','b','g','w','b','y'},
                {'w','r','g','o','g','o','r','y'},
                {'w','r','r','w','y','o','o','b'},
                {'w','r','r','w','y','g','o','y'}
        };
        //when
        cube3x3.moveUsingString("B2");

        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
    @Test
    public void testMoveBBprim(){
        //given
        char[][] expected = new char[][] {
                {'b','w','r','b','y','o','g','y'},
                {'b','b','o','y','w','g','g','r'},
                {'o','o','g','o','g','w','b','y'},
                {'w','r','g','b','g','b','r','y'},
                {'w','r','r','w','y','o','o','b'},
                {'y','o','g','y','w','r','r','w'}
        };
        //when
        cube3x3.moveUsingString("B");
        cube3x3.moveUsingString("B'");

        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
    @Test
    public void testMoveDoubleBdouble(){
        //given
        char[][] expected = new char[][] {
                {'b','w','r','b','y','o','g','y'},
                {'b','b','o','y','w','g','g','r'},
                {'o','o','g','o','g','w','b','y'},
                {'w','r','g','b','g','b','r','y'},
                {'w','r','r','w','y','o','o','b'},
                {'y','o','g','y','w','r','r','w'}
        };
        //when
        cube3x3.moveUsingString("B2");
        cube3x3.moveUsingString("B2");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
}
