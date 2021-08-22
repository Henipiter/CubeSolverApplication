package cubes.cube2x2.moves.rotate;

import cubes.Cube2x2;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveZTest {
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
    public void testMoveZ(){
        //given
        char[][] expected = new char[][] {
                {'y','g','w','o'},
                {'r','y','w','o'},
                {'r','o','g','b'},
                {'r','g','b','w'},
                {'g','y','r','b'},
                {'b','w','y','o'}
        };
        //when
        cube2x2.moveUsingString("z");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());
    }

    @Test
    public void testMoveZprim(){
        //given
        char[][] expected = new char[][] {
                {'y','r','o','w'},
                {'g','y','o','w'},
                {'b','w','r','g'},
                {'g','b','r','o'},
                {'b','r','y','g'},
                {'o','y','w','b'}
        };
        //when
        cube2x2.moveUsingString("z'");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());
    }

    @Test
    public void testMoveZdouble(){
        //given
        char[][] expected = new char[][] {
                {'g','r','b','o'},
                {'b','r','w','g'},
                {'r','w','y','o'},
                {'y','w','g','o'},
                {'r','g','b','y'},
                {'y','b','o','w'}
        };
        //when
        cube2x2.moveUsingString("z2");

        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
    @Test
    public void testMoveZZprim(){

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
        cube2x2.moveUsingString("z");
        cube2x2.moveUsingString("z'");

        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
    @Test
    public void testMoveDoubleZdouble(){
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
        cube2x2.moveUsingString("z2");
        cube2x2.moveUsingString("z2");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
}
