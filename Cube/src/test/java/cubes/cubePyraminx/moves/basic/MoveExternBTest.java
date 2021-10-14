package cubes.cubePyraminx.moves.basic;

import cubes.CubePyraminx;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveExternBTest {
// R U L R
    //up Y, f O
    private CubePyraminx cubePyraminx;

    @BeforeEach
    public void init(){
        cubePyraminx = new CubePyraminx();
    }

    @Test
    public void testMoveb(){
        //given
        char[][] expected = new char[][] {
                {'g',   'g','g','g',    'g','g','g','g','g'},
                {'r',   'y','y','y',    'y','y','y','y','y'},
                {'r',   'r','r','r',    'b','r','r','r','r'},
                {'b',   'b','b','b',    'b','b','b','b','y'}
        };
        //when
        cubePyraminx.move("b");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
    }

    @Test
    public void testMovebprim(){
        //given
        char[][] expected = new char[][] {
                {'g',   'g','g','g',    'g','g','g','g','g'},
                {'b',   'y','y','y',    'y','y','y','y','y'},
                {'r',   'r','r','r',    'y','r','r','r','r'},
                {'b',   'b','b','b',    'b','b','b','b','r'}
        };
        //when
        cubePyraminx.move("b'");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
    }

    @Test
    public void testMovebbprim(){
        //given
        char[][] expected = new char[][] {
                {'g',   'g','g','g',    'g','g','g','g','g'},
                {'y',   'y','y','y',    'y','y','y','y','y'},
                {'r',   'r','r','r',    'r','r','r','r','r'},
                {'b',   'b','b','b',    'b','b','b','b','b'}
        };
        //when
        cubePyraminx.move("b");
        cubePyraminx.move("b'");

        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());

    }
}
