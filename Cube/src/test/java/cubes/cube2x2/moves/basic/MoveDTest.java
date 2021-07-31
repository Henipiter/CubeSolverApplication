package cubes.cube2x2.moves.basic;

import cubes.Cube2x2;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveDTest {
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
    public void testMoveD(){
        //given
        char[][] expected = new char[][] {
                {'r','b','g','w'},
                {'g','b','r','o'},
                {'g','o','y','b'},
                {'y','o','r','g'},
                {'y','b','y','w'},
                {'w','o','r','w'}
        };
        //when
        cube2x2.moveUsingString("D");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());
    }

    @Test
    public void testMoveDprim(){
        //given
        char[][] expected = new char[][] {
                {'r','b','g','w'},
                {'o','r','b','g'},
                {'g','o','g','r'},
                {'y','o','b','y'},
                {'y','b','w','r'},
                {'w','o','w','y'}
        };
        //when
        cube2x2.moveUsingString("D'");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());
    }

    @Test
    public void testMoveDdouble(){
        //given
        char[][] expected = new char[][] {
                {'r','b','g','w'},
                {'b','o','g','r'},
                {'g','o','w','r'},
                {'y','o','w','y'},
                {'y','b','y','b'},
                {'w','o','r','g'}
        };
        //when
        cube2x2.moveUsingString("D2");

        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
    @Test
    public void testMoveDDprim(){
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
        cube2x2.moveUsingString("D");
        cube2x2.moveUsingString("D'");

        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
    @Test
    public void testMoveDoubleDdouble(){
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
        cube2x2.moveUsingString("D2");
        cube2x2.moveUsingString("D2");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
}
