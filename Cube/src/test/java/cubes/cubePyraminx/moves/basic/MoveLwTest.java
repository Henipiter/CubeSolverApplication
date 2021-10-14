package cubes.cubePyraminx.moves.basic;

import cubes.CubePyraminx;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveLwTest {
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
    public void testMoveLw(){
        //given
        char[][] expected = new char[][] {
                {'r',   'b','r','g',    'y','y','y','g','g'},
                {'b',   'g','b','b',    'b','b','r','r','r'},
                {'y',   'r','y','y',    'g','g','g','g','g'},
                {'b',   'r','b','b',    'r','r','y','y','y'}
        };
        char[] expectedCenterOrder = new char[]{'r','g','y','b'};
        //when
        cubePyraminx.move("Lw");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
        Assert.assertArrayEquals(expectedCenterOrder, cubePyraminx.getCenter());
    }

    @Test
    public void testMoveLwprim(){
        //given
        char[][] expected = new char[][] {
                {'g',   'g','g','r',    'g','g','y','y','y'},
                {'g',   'g','g','y',    'r','r','b','y','y'},
                {'b',   'g','b','b',    'b','b','r','r','r'},
                {'y',   'b','y','y',    'b','b','r','r','r'}
        };
        char[] expectedCenterOrder = new char[]{'y','r','g','b'};
        //when
        cubePyraminx.move("Lw'");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
        Assert.assertArrayEquals(expectedCenterOrder, cubePyraminx.getCenter());
    }

    @Test
    public void testMoveLwLwprim(){
        //given
        char[][] expected = new char[][] {
                {'b',   'r','b','g',    'r','r','b','b','b'},
                {'y',   'r','y','y',    'g','g','g','g','g'},
                {'g',   'g','g','y',    'r','r','b','y','y'},
                {'r',   'y','r','r',    'y','y','b','b','b'}
        };
        char[] expectedCenterOrder = new char[]{'g','y','r','b'};
        //when
        cubePyraminx.move("Lw");
        cubePyraminx.move("Lw'");

        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
        Assert.assertArrayEquals(expectedCenterOrder, cubePyraminx.getCenter());

    }
}
