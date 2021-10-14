package cubes.cube2x2.moves.basic;

import cubes.Cube2x2;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveLTest {
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
    public void testMoveL(){
        //given
        char[][] expected = new char[][] {
                {'b','b','w','w'},
                {'g','g','y','b'},
                {'y','g','w','o'},
                {'y','o','r','w'},
                {'r','b','g','r'},
                {'r','o','o','y'}
        };
        //when
        cube2x2.move("L");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());
    }

    @Test
    public void testMoveLprim(){
        //given
        char[][] expected = new char[][] {
                {'y','b','g','w'},
                {'w','g','b','b'},
                {'o','w','g','y'},
                {'y','o','r','w'},
                {'o','b','r','r'},
                {'g','o','r','y'}
        };
        //when
        cube2x2.move("L'");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());
    }

    @Test
    public void testMoveLdouble(){
        //given
        char[][] expected = new char[][] {
                {'o','b','r','w'},
                {'g','g','r','b'},
                {'w','y','o','g'},
                {'y','o','r','w'},
                {'b','b','w','r'},
                {'g','o','y','y'}
        };
        //when
        cube2x2.move("L2");

        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
    @Test
    public void testMoveLLprim(){
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
        cube2x2.move("L");
        cube2x2.move("L'");

        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
    @Test
    public void testMoveDoubleLdouble(){
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
        cube2x2.move("L2");
        cube2x2.move("L2");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
}
