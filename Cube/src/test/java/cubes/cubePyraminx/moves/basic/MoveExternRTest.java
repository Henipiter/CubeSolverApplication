package cubes.cubePyraminx.moves.basic;

import cubes.CubePyraminx;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveExternRTest {
// R U L R
    //up Y, f O
    private CubePyraminx cubePyraminx;

    @BeforeEach
    public void init(){
        cubePyraminx = new CubePyraminx();
    }

    @Test
    public void testMover(){
        //given
        char[][] expected = new char[][] {
                {'g',   'g','g','g',    'g','g','g','g','y'},
                {'y',   'y','y','y',    'b','y','y','y','y'},
                {'r',   'r','r','r',    'r','r','r','r','r'},
                {'b',   'b','b','b',    'g','b','b','b','b'}
        };
        //when
        cubePyraminx.moveUsingString("r");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
    }

    @Test
    public void testMoverprim(){
        //given
        char[][] expected = new char[][] {
                {'g',   'g','g','g',    'g','g','g','g','b'},
                {'y',   'y','y','y',    'g','y','y','y','y'},
                {'r',   'r','r','r',    'r','r','r','r','r'},
                {'b',   'b','b','b',    'y','b','b','b','b'}
        };
        //when
        cubePyraminx.moveUsingString("r'");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
    }

    @Test
    public void testMoverrprim(){
        //given
        char[][] expected = new char[][] {
                {'g',   'g','g','g',    'g','g','g','g','g'},
                {'y',   'y','y','y',    'y','y','y','y','y'},
                {'r',   'r','r','r',    'r','r','r','r','r'},
                {'b',   'b','b','b',    'b','b','b','b','b'}
        };
        //when
        cubePyraminx.moveUsingString("r");
        cubePyraminx.moveUsingString("r'");

        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());

    }
}
