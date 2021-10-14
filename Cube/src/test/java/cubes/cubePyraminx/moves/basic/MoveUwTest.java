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
        char[] expectedCenterOrder = new char[]{'b','y','g','r'};
        //when
        cubePyraminx.move("Uw");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
        Assert.assertArrayEquals(expectedCenterOrder, cubePyraminx.getCenter());
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
        char[] expectedCenterOrder = new char[]{'r','y','b','g'};
        //when
        cubePyraminx.move("Uw'");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
        Assert.assertArrayEquals(expectedCenterOrder, cubePyraminx.getCenter());
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
        char[] expectedCenterOrder = new char[]{'g','y','r','b'};
        //when
        cubePyraminx.move("Uw");
        cubePyraminx.move("Uw'");

        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
        Assert.assertArrayEquals(expectedCenterOrder, cubePyraminx.getCenter());

    }
}
