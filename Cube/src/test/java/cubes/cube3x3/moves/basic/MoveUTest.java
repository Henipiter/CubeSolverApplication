package cubes.cube3x3.moves.basic;

import cubes.Cube3x3;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveUTest {
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
    public void testMoveU(){
        //given
        char[][] expected = new char[][] {
                {'o','b','b','g','w','y','y','r'},
                {'b','b','o','y','w','g','g','r'},
                {'w','r','r','o','g','w','b','y'},
                {'y','o','g','b','g','b','r','y'},
                {'g','r','w','w','y','o','o','b'},
                {'g','o','o','y','w','r','r','w'}
        };
        //when
        cube3x3.move("U");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());
    }

    @Test
    public void testMoveUprim(){
        //given
        char[][] expected = new char[][] {
                {'r','y','y','w','g','b','b','o'},
                {'b','b','o','y','w','g','g','r'},
                {'g','o','y','o','g','w','b','y'},
                {'r','r','w','b','g','b','r','y'},
                {'o','o','g','w','y','o','o','b'},
                {'w','r','g','y','w','r','r','w'}
        };
        //when
        cube3x3.move("U'");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());
    }

    @Test
    public void testMoveUdouble(){
        //given
        char[][] expected = new char[][] {
                {'y','g','o','y','b','r','w','b'},
                {'b','b','o','y','w','g','g','r'},
                {'g','r','w','o','g','w','b','y'},
                {'g','o','o','b','g','b','r','y'},
                {'g','o','y','w','y','o','o','b'},
                {'r','r','w','y','w','r','r','w'}
        };
        //when
        cube3x3.move("U2");

        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
    @Test
    public void testMoveUUprim(){
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
        cube3x3.move("U");
        cube3x3.move("U'");

        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
    @Test
    public void testMoveDoubleUdouble(){
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
        cube3x3.move("U2");
        cube3x3.move("U2");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
}
