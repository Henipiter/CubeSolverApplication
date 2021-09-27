package cubes.cube3x3.moves.basic;

import cubes.Cube3x3;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveDTest {
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
    public void testMoveD(){
        //given
        char[][] expected = new char[][] {
                {'b','w','r','b','y','o','g','y'},
                {'o','w','r','b','g','b','y','g'},
                {'o','o','g','o','g','w','r','r'},
                {'w','r','g','b','g','b','o','o'},
                {'w','r','r','w','y','w','b','y'},
                {'y','o','g','y','w','b','r','y'}
        };
        //when
        cube3x3.moveUsingString("D");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());
    }

    @Test
    public void testMoveDprim(){
        //given
        char[][] expected = new char[][] {
                {'b','w','r','b','y','o','g','y'},
                {'g','y','b','g','b','r','w','o'},
                {'o','o','g','o','g','o','o','b'},
                {'w','r','g','b','g','r','r','w'},
                {'w','r','r','w','y','y','r','b'},
                {'y','o','g','y','w','y','b','w'}
        };
        //when
        cube3x3.moveUsingString("D'");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());
    }

    @Test
    public void testMoveDdouble(){
        //given
        char[][] expected = new char[][] {
                {'b','w','r','b','y','o','g','y'},
                {'r','g','g','w','y','o','b','b'},
                {'o','o','g','o','g','y','r','b'},
                {'w','r','g','b','g','y','b','w'},
                {'w','r','r','w','y','w','r','r'},
                {'y','o','g','y','w','b','o','o'}
        };
        //when
        cube3x3.moveUsingString("D2");

        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
    @Test
    public void testMoveDDprim(){
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
        cube3x3.moveUsingString("D");
        cube3x3.moveUsingString("D'");

        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
    @Test
    public void testMoveDoubleDdouble(){
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
        cube3x3.moveUsingString("D2");
        cube3x3.moveUsingString("D2");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
}
