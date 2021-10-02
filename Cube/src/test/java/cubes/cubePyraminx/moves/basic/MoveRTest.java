package cubes.cubePyraminx.moves.basic;

import cubes.CubePyraminx;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveRTest {
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
    public void testMoveR(){
        //given
        char[][] expected = new char[][] {
                {'b',   'r','b','g',    'r','r','r','g','g'},
                {'y',   'y','y','y',    'y','y','b','g','g'},
                {'g',   'g','g','y',    'r','r','b','y','y'},
                {'r',   'b','r','r',    'b','b','g','b','b'}
        };
        //when
        cubePyraminx.moveUsingString("R");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
    }

    @Test
    public void testMoveRprim(){
        //given
        char[][] expected = new char[][] {
                {'b',   'r','b','b',    'r','r','y','y','y'},
                {'y',   'b','y','y',    'b','b','g','g','g'},
                {'g',   'g','g','y',    'r','r','b','y','y'},
                {'r',   'r','r','r',    'g','g','g','b','b'}
        };
        //when
        cubePyraminx.moveUsingString("R'");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
    }

    @Test
    public void testMoveRRprim(){
        //given
        char[][] expected = new char[][] {
                {'b',   'r','b','g',    'r','r','b','b','b'},
                {'y',   'r','y','y',    'g','g','g','g','g'},
                {'g',   'g','g','y',    'r','r','b','y','y'},
                {'r',   'y','r','r',    'y','y','b','b','b'}
        };
        //when
        cubePyraminx.moveUsingString("R");
        cubePyraminx.moveUsingString("R'");

        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());

    }
}
