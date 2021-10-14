package cubes.cube3x3.moves.basic;

import cubes.Cube3x3;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveLTest {
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
    public void testMoveL(){
        //given
        char[][] expected = new char[][] {
                {'r','w','r','y','y','y','g','y'},
                {'o','b','o','w','w','w','g','r'},
                {'w','o','o','b','o','y','g','g'},
                {'w','r','g','b','g','b','r','y'},
                {'b','r','r','b','y','o','o','b'},
                {'b','o','g','y','w','g','r','w'}
        };
        //when
        cube3x3.move("L");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());
    }

    @Test
    public void testMoveLprim(){
        //given
        char[][] expected = new char[][] {
                {'w','w','r','w','y','o','g','y'},
                {'y','b','o','y','w','r','g','r'},
                {'g','g','y','o','b','o','o','w'},
                {'w','r','g','b','g','b','r','y'},
                {'g','r','r','y','y','b','o','b'},
                {'o','o','g','b','w','b','r','w'}
        };
        //when
        cube3x3.move("L'");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());
    }

    @Test
    public void testMoveLdouble(){
        //given
        char[][] expected = new char[][] {
                {'g','w','r','y','y','b','g','y'},
                {'o','b','o','b','w','b','g','r'},
                {'y','b','w','g','o','g','o','o'},
                {'w','r','g','b','g','b','r','y'},
                {'r','r','r','y','y','y','o','b'},
                {'o','o','g','w','w','w','r','w'}
        };
        //when
        cube3x3.move("L2");

        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
    @Test
    public void testMoveLLprim(){
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
        cube3x3.move("L");
        cube3x3.move("L'");

        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
    @Test
    public void testMoveDoubleLdouble(){
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
        cube3x3.move("L2");
        cube3x3.move("L2");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
}
