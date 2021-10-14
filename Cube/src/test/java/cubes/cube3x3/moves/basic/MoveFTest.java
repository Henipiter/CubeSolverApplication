package cubes.cube3x3.moves.basic;

import cubes.Cube3x3;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveFTest {
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
    public void testMoveF(){
        //given
        char[][] expected = new char[][] {
                {'b','w','r','b','y','y','g','g'},
                {'b','b','o','y','w','y','g','g'},
                {'o','o','g','o','g','w','b','r'},
                {'w','r','o','b','g','b','r','y'},
                {'o','w','w','o','r','b','y','r'},
                {'y','o','g','y','w','r','r','w'}
        };
        //when
        cube3x3.move("F");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());
    }

    @Test
    public void testMoveFprim(){
        //given
        char[][] expected = new char[][] {
                {'b','w','r','b','y','g','g','y'},
                {'b','b','o','y','w','g','g','y'},
                {'o','o','y','o','g','w','b','o'},
                {'w','r','r','b','g','b','r','g'},
                {'r','y','b','r','o','w','w','o'},
                {'y','o','g','y','w','r','r','w'}
        };
        //when
        cube3x3.move("F'");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());
    }

    @Test
    public void testMoveFdouble(){
        //given
        char[][] expected = new char[][] {
                {'b','w','r','b','y','r','g','g'},
                {'b','b','o','y','w','y','g','o'},
                {'o','o','y','o','g','w','b','g'},
                {'w','r','y','b','g','b','r','g'},
                {'b','o','o','y','w','r','r','w'},
                {'y','o','g','y','w','r','r','w'}
        };
        //when
        cube3x3.move("F2");

        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
    @Test
    public void testMoveFFprim(){
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
        cube3x3.move("F");
        cube3x3.move("F'");

        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
    @Test
    public void testMoveDoubleFdouble(){
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
        cube3x3.move("F2");
        cube3x3.move("F2");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());


    }
}
