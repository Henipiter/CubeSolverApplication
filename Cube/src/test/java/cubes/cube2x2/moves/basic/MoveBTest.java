package cubes.cube2x2.moves.basic;

import cubes.Cube2x2;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveBTest {
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
    public void testMoveB(){
        //given
        char[][] expected = new char[][] {
                {'y','r','g','w'},
                {'g','y','o','b'},
                {'b','o','r','w'},
                {'g','o','r','w'},
                {'y','b','g','r'},
                {'o','y','w','b'}
        };
        //when
        cube2x2.move("B");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());
    }

    @Test
    public void testMoveBprim(){
        //given
        char[][] expected = new char[][] {
                {'y','g','g','w'},
                {'r','y','o','b'},
                {'r','o','g','w'},
                {'r','o','b','w'},
                {'y','b','g','r'},
                {'b','w','y','o'}
        };
        //when
        cube2x2.move("B'");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());
    }

    @Test
    public void testMoveBdouble(){
        //given
        char[][] expected = new char[][] {
                {'g','r','g','w'},
                {'b','r','o','b'},
                {'r','o','y','w'},
                {'y','o','g','w'},
                {'y','b','g','r'},
                {'y','b','o','w'}
        };
        //when
        cube2x2.move("B2");

        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
    @Test
    public void testMoveBBprim(){
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
        cube2x2.move("B");
        cube2x2.move("B'");

        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
    @Test
    public void testMoveDoubleBdouble(){
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
        cube2x2.move("B2");
        cube2x2.move("B2");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
}
