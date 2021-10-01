package cubes.cubePyraminx.moves.basic;

import cubes.CubePyraminx;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveUwTest {
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
    public void testMoveUw(){
        //given
        char[][] expected = new char[][] {
                {'r',   'y','r','r',    'y','y','b','b','b'},
                {'g',   'y','g','g',    'y','y','r','g','g'},
                {'b',   'r','b','g',    'r','r','b','b','b'},
                {'g',   'g','g','y',    'r','r','b','y','y'}
        };
        //when
        cubePyraminx.moveUsingString("Uw");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
    }

    @Test
    public void testMoveUwprim(){
        //given
        char[][] expected = new char[][] {
                {'g',   'g','g','y',    'r','r','b','y','y'},
                {'g',   'g','g','r',    'g','g','y','y','y'},
                {'r',   'y','r','r',    'y','y','b','b','b'},
                {'b',   'r','b','g',    'r','r','b','b','b'}
        };
        //when
        cubePyraminx.moveUsingString("Uw'");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
    }

    @Test
    public void testMoveUwUwprim(){
        //given
        char[][] expected = new char[][] {
                {'b',   'r','b','g',    'r','r','b','b','b'},
                {'y',   'r','y','y',    'g','g','g','g','g'},
                {'g',   'g','g','y',    'r','r','b','y','y'},
                {'r',   'y','r','r',    'y','y','b','b','b'}
        };
        //when
        cubePyraminx.moveUsingString("Uw");
        cubePyraminx.moveUsingString("Uw'");

        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());

    }
}
