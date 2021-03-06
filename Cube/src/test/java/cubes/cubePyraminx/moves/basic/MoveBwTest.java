package cubes.cubePyraminx.moves.basic;

import cubes.CubePyraminx;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveBwTest {
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
    public void testMoveBw(){
        //given
        char[][] expected = new char[][] {
                {'b',   'g','b','b',    'b','b','r','r','r'},
                {'r',   'b','r','g',    'y','y','y','g','g'},
                {'y',   'b','y','y',    'b','b','r','r','r'},
                {'g',   'g','g','r',    'g','g','y','y','y'}
        };
        char[] expectedCenterOrder = new char[]{'g','r','b','y'};
        //when
        cubePyraminx.move("Bw");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
        Assert.assertArrayEquals(expectedCenterOrder, cubePyraminx.getCenter());
    }

    @Test
    public void testMoveBwprim(){
        //given
        char[][] expected = new char[][] {
                {'r',   'b','r','r',    'b','b','g','b','b'},
                {'b',   'r','b','b',    'r','r','y','y','y'},
                {'g',   'y','g','g',    'y','y','r','g','g'},
                {'y',   'y','y','b',    'g','g','g','r','r'}
        };
        char[] expectedCenterOrder = new char[]{'g','b','y','r'};
        //when
        cubePyraminx.move("Bw'");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
        Assert.assertArrayEquals(expectedCenterOrder, cubePyraminx.getCenter());
    }

    @Test
    public void testMoveBwBwprim(){
        //given
        char[][] expected = new char[][] {
                {'b',   'r','b','g',    'r','r','b','b','b'},
                {'y',   'r','y','y',    'g','g','g','g','g'},
                {'g',   'g','g','y',    'r','r','b','y','y'},
                {'r',   'y','r','r',    'y','y','b','b','b'}
        };
        char[] expectedCenterOrder = new char[]{'g','y','r','b'};
        //when
        cubePyraminx.move("Bw");
        cubePyraminx.move("Bw'");

        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
        Assert.assertArrayEquals(expectedCenterOrder, cubePyraminx.getCenter());

    }
}
