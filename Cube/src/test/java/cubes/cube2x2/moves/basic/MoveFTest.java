package cubes.cube2x2.moves.basic;

import cubes.Cube2x2;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveFTest {
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
    public void testMoveF(){
        //given
        char[][] expected = new char[][] {
                {'r','b','w','o'},
                {'r','g','w','o'},
                {'g','o','y','b'},
                {'y','g','r','w'},
                {'g','y','r','b'},
                {'w','o','b','y'}
        };
        //when
        cube2x2.move("F");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());
    }

    @Test
    public void testMoveFprim(){
        //given
        char[][] expected = new char[][] {
                {'r','b','o','w'},
                {'r','g','o','w'},
                {'g','w','y','g'},
                {'y','b','r','o'},
                {'b','r','y','g'},
                {'w','o','b','y'}
        };
        //when
        cube2x2.move("F'");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());
    }

    @Test
    public void testMoveFdouble(){
        //given
        char[][] expected = new char[][] {
                {'r','b','b','o'},
                {'r','g','w','g'},
                {'g','w','y','o'},
                {'y','w','r','o'},
                {'r','g','b','y'},
                {'w','o','b','y'}
        };
        //when
        cube2x2.move("F2");

        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
    @Test
    public void testMoveFFprim(){
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
        cube2x2.move("F");
        cube2x2.move("F'");

        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
    @Test
    public void testMoveDoubleFdouble(){
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
        cube2x2.move("F2");
        cube2x2.move("F2");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
}
