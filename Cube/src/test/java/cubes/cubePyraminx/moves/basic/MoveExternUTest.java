package cubes.cubePyraminx.moves.basic;

import cubes.CubePyraminx;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveExternUTest {
// R U L R
    //up Y, f O
    private CubePyraminx cubePyraminx;

    @BeforeEach
    public void init(){
        cubePyraminx = new CubePyraminx();
    }

    @Test
    public void testMoveu(){
        //given
        char[][] expected = new char[][] {
                {'b',   'g','g','g',    'g','g','g','g','g'},
                {'y',   'y','y','y',    'y','y','y','y','y'},
                {'g',   'r','r','r',    'r','r','r','r','r'},
                {'r',   'b','b','b',    'b','b','b','b','b'}
        };
        //when
        cubePyraminx.move("u");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
    }

    @Test
    public void testMoveuprim(){
        //given
        char[][] expected = new char[][] {
                {'r',   'g','g','g',    'g','g','g','g','g'},
                {'y',   'y','y','y',    'y','y','y','y','y'},
                {'b',   'r','r','r',    'r','r','r','r','r'},
                {'g',   'b','b','b',    'b','b','b','b','b'}
        };
        //when
        cubePyraminx.move("u'");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
    }

    @Test
    public void testMoveuuprim(){
        //given
        char[][] expected = new char[][] {
                {'g',   'g','g','g',    'g','g','g','g','g'},
                {'y',   'y','y','y',    'y','y','y','y','y'},
                {'r',   'r','r','r',    'r','r','r','r','r'},
                {'b',   'b','b','b',    'b','b','b','b','b'}
        };
        //when
        cubePyraminx.move("u");
        cubePyraminx.move("u'");

        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());

    }
}
