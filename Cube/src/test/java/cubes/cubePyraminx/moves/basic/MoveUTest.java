package cubes.cubePyraminx.moves.basic;

import cubes.CubePyraminx;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveUTest {
// R U L R
    //up Y, f O
    private char[][] input;
    private CubePyraminx cubePyraminx;

    @BeforeEach
    public void init(){
        input = new char[][] {
                {'b',   'r','b','g',    'r','r','b','b','b'},
                {'y',   'r','y','y',    'g','g','g','g','g'},
                {'g',   'g','g','y',    'r','r','b','y','y'},
                {'r',   'y','r','r',    'y','y','b','b','b'}
        };

        cubePyraminx = new CubePyraminx(input);
    }


    @Test
    public void testMoveU(){
        //given
        char[][] expected = new char[][] {
                {'r',   'y','r','r',    'r','r','b','b','b'},
                {'y',   'r','y','y',    'g','g','g','g','g'},
                {'b',   'r','b','g',    'r','r','b','y','y'},
                {'g',   'g','g','y',    'y','y','b','b','b'}
        };
        //when
        cubePyraminx.moveUsingString("U");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
    }

    @Test
    public void testMoveUprim(){
        //given
        char[][] expected = new char[][] {
                {'g',   'g','g','y',    'r','r','b','b','b'},
                {'y',   'r','y','y',    'g','g','g','g','g'},
                {'r',   'y','r','r',    'r','r','b','y','y'},
                {'b',   'r','b','g',    'y','y','b','b','b'}
        };
        //when
        cubePyraminx.moveUsingString("U'");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
    }

    @Test
    public void testMoveUUprim(){
        //given
        char[][] expected = new char[][] {
                {'b',   'r','b','g',    'r','r','b','b','b'},
                {'y',   'r','y','y',    'g','g','g','g','g'},
                {'g',   'g','g','y',    'r','r','b','y','y'},
                {'r',   'y','r','r',    'y','y','b','b','b'}
        };
        //when
        cubePyraminx.moveUsingString("U");
        cubePyraminx.moveUsingString("U'");

        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());

    }
}
