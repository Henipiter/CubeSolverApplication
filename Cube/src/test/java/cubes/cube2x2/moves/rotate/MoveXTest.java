package cubes.cube2x2.moves.rotate;

import cubes.Cube2x2;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveXTest {
// R U L D R
    //up Y, f O
    private char[][] input;
    private Cube2x2 cube2x2;

    @BeforeEach
    public void init(){
        input = new char[][] {
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
    public void testMoveX(){
        //given
        char[][] expected = new char[][] {
                {'y','b','g','r'},
                {'w','o','b','y'},
                {'o','w','g','y'},
                {'o','w','y','r'},
                {'o','b','r','g'},
                {'g','w','r','b'}
        };
        //when
        cube2x2.moveUsingString("x");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());
    }

    @Test
    public void testMoveXprim(){
        //given
        char[][] expected = new char[][] {
                {'b','y','w','o'},
                {'g','r','y','b'},
                {'y','g','w','o'},
                {'r','y','w','o'},
                {'r','b','g','w'},
                {'r','g','o','b'}
        };
        //when
        cube2x2.moveUsingString("x'");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());
    }

    @Test
    public void testMoveXdouble(){
        //given
        char[][] expected = new char[][] {
                {'o','b','r','g'},
                {'g','w','r','b'},
                {'w','y','o','g'},
                {'w','r','o','y'},
                {'b','y','w','o'},
                {'g','r','y','b'}
        };
        //when
        cube2x2.moveUsingString("x2");

        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
    @Test
    public void testMoveXXprim(){
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
        cube2x2.moveUsingString("x");
        cube2x2.moveUsingString("x'");

        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
    @Test
    public void testMoveDoubleXdouble(){
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
        cube2x2.moveUsingString("x2");
        cube2x2.moveUsingString("x2");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
}
