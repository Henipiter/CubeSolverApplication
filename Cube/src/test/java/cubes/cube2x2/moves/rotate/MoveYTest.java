package cubes.cube2x2.moves.rotate;

import cubes.Cube2x2;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveYTest {
// R U L D R
    //up Y, f O
    private char[][] input;

    private Cube2x2 cube2x2;

    @BeforeEach
    public void init(){
        input = new char[][]{
            {'r','b','g','w'},
            {'r','g','o','b'},
            {'g','o','y','w'},
            {'y','o','r','w'},
            {'y','b','g','r'},
            {'w','o','b','y'}
        };

        cube2x2 = new Cube2x2(input);
    }


    @Test
    public void testMoveY(){
        //given
        char[][] expected = new char[][] {
                {'g','r','w','b'},
                {'o','r','b','g'},
                {'y','b','g','r'},
                {'w','o','b','y'},
                {'o','y','w','r'},
                {'o','g','w','y'}
        };
        //when
        cube2x2.moveUsingString("Y");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());
    }

    @Test
    public void testMoveYprim(){
        //given
        char[][] expected = new char[][] {
                {'b','w','r','g'},
                {'g','b','r','o'},
                {'o','w','y','b'},
                {'b','y','r','g'},
                {'g','o','y','w'},
                {'y','o','r','w'}
        };
        //when
        cube2x2.moveUsingString("Y'");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());
    }

    @Test
    public void testMoveYdouble(){
        //given
        char[][] expected = new char[][] {
                {'w','g','b','r'},
                {'b','o','g','r'},
                {'o','y','w','r'},
                {'o','g','w','y'},
                {'o','w','y','b'},
                {'b','y','r','g'}
        };
        //when
        cube2x2.moveUsingString("Y2");

        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
    @Test
    public void testMoveYYprim(){

        //given
        char[][] expected = new char[][] {
                {'r','b','g','w'},
                {'r','g','o','b'},
                {'g','o','y','w'},
                {'y','o','r','w'},
                {'y','b','g','r'},
                {'w','o','b','y'}
        };
        //when
        cube2x2.moveUsingString("Y");
        cube2x2.moveUsingString("Y'");

        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
    @Test
    public void testMoveDoubleYdouble(){
        //given
        char[][] expected = new char[][] {
                {'r','b','g','w'},
                {'r','g','o','b'},
                {'g','o','y','w'},
                {'y','o','r','w'},
                {'y','b','g','r'},
                {'w','o','b','y'}
        };
        //when
        cube2x2.moveUsingString("Y2");
        cube2x2.moveUsingString("Y2");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
}
