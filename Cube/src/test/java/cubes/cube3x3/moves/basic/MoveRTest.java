package cubes.cube3x3.moves.basic;

import cubes.Cube2x2;
import cubes.Cube3x3;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveRTest {
// R U L D R
    //up Y, f O
    private char[][] input;
    private Cube3x3 cube3x3;

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

        cube3x3 = new Cube3x3(input);
    }


    @Test
    public void testMoveR(){
        //given
        char[][] expected = new char[][] {
                {'r','b','g','r'},
                {'r','o','o','y'},
                {'g','o','y','w'},
                {'o','w','y','r'},
                {'y','b','g','g'},
                {'w','w','b','b'}
        };
        //when
        cube3x3.moveUsingString("R");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());
    }

    @Test
    public void testMoveRprim(){
        //given
        char[][] expected = new char[][] {
                {'r','y','g','o'},
                {'r','r','o','b'},
                {'g','o','y','w'},
                {'r','y','w','o'},
                {'y','b','g','w'},
                {'w','g','b','b'}
        };
        //when
        cube3x3.moveUsingString("R'");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());
    }

    @Test
    public void testMoveRdouble(){
        //given
        char[][] expected = new char[][] {
                {'r','b','g','g'},
                {'r','w','o','b'},
                {'g','o','y','w'},
                {'w','r','o','y'},
                {'y','y','g','o'},
                {'w','r','b','b'}
        };
        //when
        cube3x3.moveUsingString("R2");

        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
    @Test
    public void testMoveRRprim(){
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
        cube3x3.moveUsingString("R");
        cube3x3.moveUsingString("R'");

        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
    @Test
    public void testMoveDoubleRdouble(){
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
        cube3x3.moveUsingString("R2");
        cube3x3.moveUsingString("R2");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
}
