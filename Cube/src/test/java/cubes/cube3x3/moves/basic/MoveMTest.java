package cubes.cube3x3.moves.basic;

import cubes.Cube3x3;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveMTest {
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
    public void testMoveM(){
        //given
        char[][] expected = new char[][] {
                {'b','r','r','b','y','o','o','y'},
                {'b','o','o','y','w','g','r','r'},
                {'o','o','g','o','g','w','b','y'},
                {'w','r','g','b','g','b','r','y'},
                {'w','w','r','w','y','o','g','b'},
                {'y','b','g','y','w','r','g','w'}
        };
        //when
        cube3x3.moveUsingString("M");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());
    }

    @Test
    public void testMoveMprim(){
        //given
        char[][] expected = new char[][] {
                {'b','r','r','b','y','o','o','y'},
                {'b','o','o','y','w','g','r','r'},
                {'o','o','g','o','g','w','b','y'},
                {'w','r','g','b','g','b','r','y'},
                {'w','g','r','w','y','o','b','b'},
                {'y','g','g','y','w','r','w','w'}
        };
        //when
        cube3x3.moveUsingString("M'");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());
    }

    @Test
    public void testMoveMdouble(){
        //given
        char[][] expected = new char[][] {
                {'b','g','r','b','y','o','b','y'},
                {'b','g','o','y','w','g','w','r'},
                {'o','o','g','o','g','w','b','y'},
                {'w','r','g','b','g','b','r','y'},
                {'w','r','r','w','y','o','o','b'},
                {'y','o','g','y','w','r','r','w'}
        };
        //when
        cube3x3.moveUsingString("M2");

        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
    @Test
    public void testMoveMMprim(){
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
        cube3x3.moveUsingString("M");
        cube3x3.moveUsingString("M'");

        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
    @Test
    public void testMoveDoubleMdouble(){
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
        cube3x3.moveUsingString("M2");
        cube3x3.moveUsingString("M2");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
}
