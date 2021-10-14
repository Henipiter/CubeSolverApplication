package cubes.cube3x3.moves.basic;

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
    public void testMoveR(){
        //given
        char[][] expected = new char[][] {
                {'b','w','r','b','y','o','g','b'},
                {'b','b','g','y','w','g','g','w'},
                {'o','o','g','o','g','w','b','y'},
                {'g','g','y','r','r','w','b','b'},
                {'w','r','r','w','w','o','o','o'},
                {'y','o','y','y','y','r','r','r'}
        };
        //when
        cube3x3.move("R");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());
    }

    @Test
    public void testMoveRprim(){
        //given
        char[][] expected = new char[][] {
                {'b','w','w','b','w','o','g','g'},
                {'b','b','b','y','y','g','g','r'},
                {'o','o','g','o','g','w','b','y'},
                {'b','b','w','r','r','y','g','g'},
                {'w','r','r','w','y','o','o','y'},
                {'y','o','o','y','w','r','r','r'}
        };
        //when
        cube3x3.move("R'");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());
    }

    @Test
    public void testMoveRdouble(){
        //given
        char[][] expected = new char[][] {
                {'b','w','r','b','w','o','g','o'},
                {'b','b','y','y','y','g','g','r'},
                {'o','o','g','o','g','w','b','y'},
                {'y','r','b','g','b','g','r','w'},
                {'w','r','w','w','w','o','o','g'},
                {'y','o','b','y','y','r','r','r'}
        };
        //when
        cube3x3.move("R2");

        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
    @Test
    public void testMoveRRprim(){
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
        cube3x3.move("R");
        cube3x3.move("R'");

        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
    @Test
    public void testMoveDoubleRdouble(){
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
        cube3x3.move("R2");
        cube3x3.move("R2");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
}
