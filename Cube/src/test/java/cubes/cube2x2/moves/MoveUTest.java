package cubes.cube2x2.moves;

import cubes.Cube2x2;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveUTest {
// R U L D R
    //up Y, f O
    private char[][] input = new char[][] {
        {'r','b','g','w'},
        {'r','g','o','b'},
        {'g','o','y','w'},
        {'y','o','r','w'},
        {'y','b','g','r'},
        {'w','o','b','y'}
};
    private Cube2x2 cube2x2;

    @BeforeEach
    public void init(){


        cube2x2 = new Cube2x2(input);
    }


    @Test
    public void testMoveU(){
        //given
        char[][] expected = new char[][] {
                {'g','r','w','b'},
                {'r','g','o','b'},
                {'y','b','y','w'},
                {'w','o','r','w'},
                {'o','y','g','r'},
                {'o','g','b','y'}
        };
        //when
        cube2x2.move("U");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());
    }

    @Test
    public void testMoveUprim(){
        //given
        char[][] expected = new char[][] {
                {'b','w','r','g'},
                {'r','g','o','b'},
                {'o','w','y','w'},
                {'b','y','r','w'},
                {'g','o','g','r'},
                {'y','o','b','y'}
        };
        //when
        cube2x2.move("U'");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());
    }

    @Test
    public void testMoveUdouble(){
        //given
        char[][] expected = new char[][] {
                {'w','g','b','r'},
                {'r','g','o','b'},
                {'o','y','y','w'},
                {'o','g','r','w'},
                {'o','w','g','r'},
                {'b','y','b','y'}
        };
        //when
        cube2x2.move("U2");

        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
    @Test
    public void testMoveUUprim(){

        //when
        cube2x2.move("U");
        cube2x2.move("U'");

        //then
        Assert.assertArrayEquals(input, cube2x2.getCube());


    }
    @Test
    public void testMoveDoubleUdouble(){
        //given

        //when
        cube2x2.move("2U");
        cube2x2.move("2U");
        //then
        Assert.assertArrayEquals(input, cube2x2.getCube());


    }
}
