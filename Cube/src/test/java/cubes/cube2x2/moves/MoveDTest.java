package cubes.cube2x2.moves;

import cubes.Cube2x2;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveDTest {
// R U L D R
    //up Y, f O
    private char[][] input = new char[][] {
        {'r','b','g','w'},
        {'r','g','o','b'},
        {'g','o','y','w'},
        {'y','o','r','w'},
        {'y','b','g','r'},
        {'w','o','b','y'}
};
    private Cube2x2 cube2x2;

    @BeforeEach
    public void init(){


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
        cube2x2.move("D");
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
        cube2x2.move("D'");
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
        cube2x2.move("D2");

        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());


    }
    @Test
    public void testMoveDDprim(){

        //when
        cube2x2.move("D");
        cube2x2.move("D'");

        //then
        Assert.assertArrayEquals(input, cube2x2.getCube());


    }
    @Test
    public void testMoveDoubleDdouble(){
        //given

        //when
        cube2x2.move("2D");
        cube2x2.move("2D");
        //then
        Assert.assertArrayEquals(input, cube2x2.getCube());


    }
}
