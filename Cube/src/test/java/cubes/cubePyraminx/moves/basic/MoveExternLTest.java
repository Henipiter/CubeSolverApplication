package cubes.cubePyraminx.moves.basic;

import cubes.CubePyraminx;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveExternLTest {
// R U L R
    //up Y, f O
    private CubePyraminx cubePyraminx;

    @BeforeEach
    public void init(){
        cubePyraminx = new CubePyraminx();
    }

    @Test
    public void testMovel(){
        //given
        char[][] expected = new char[][] {
                {'g',   'g','g','g',    'r','g','g','g','g'},
                {'y',   'y','y','y',    'y','y','y','y','g'},
                {'r',   'r','r','r',    'r','r','r','r','y'},
                {'b',   'b','b','b',    'b','b','b','b','b'}
        };
        //when
        cubePyraminx.moveUsingString("l");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
    }

    @Test
    public void testMovelprim(){
        //given
        char[][] expected = new char[][] {
                {'g',   'g','g','g',    'y','g','g','g','g'},
                {'y',   'y','y','y',    'y','y','y','y','r'},
                {'r',   'r','r','r',    'r','r','r','r','g'},
                {'b',   'b','b','b',    'b','b','b','b','b'}
        };
        //when
        cubePyraminx.moveUsingString("l'");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
    }

    @Test
    public void testMovellprim(){
        //given
        char[][] expected = new char[][] {
                {'g',   'g','g','g',    'g','g','g','g','g'},
                {'y',   'y','y','y',    'y','y','y','y','y'},
                {'r',   'r','r','r',    'r','r','r','r','r'},
                {'b',   'b','b','b',    'b','b','b','b','b'}
        };
        //when
        cubePyraminx.moveUsingString("l");
        cubePyraminx.moveUsingString("l'");

        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());

    }
}
