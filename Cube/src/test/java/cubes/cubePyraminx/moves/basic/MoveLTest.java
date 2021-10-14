package cubes.cubePyraminx.moves.basic;

import cubes.CubePyraminx;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveLTest {
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
    public void testMoveL(){
        //given
        char[][] expected = new char[][] {
                {'b',   'b','b','g',    'y','y','y','b','b'},
                {'y',   'r','y','b',    'g','g','r','r','r'},
                {'g',   'g','g','y',    'r','r','g','g','g'},
                {'r',   'y','r','r',    'y','y','b','b','b'}
        };
        //when
        cubePyraminx.move("L");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
    }

    @Test
    public void testMoveLprim(){
        //given
        char[][] expected = new char[][] {
                {'b',   'g','b','g',    'g','g','y','b','b'},
                {'y',   'r','y','y',    'g','g','b','y','y'},
                {'g',   'g','g','b',    'r','r','r','r','r'},
                {'r',   'y','r','r',    'y','y','b','b','b'}
        };
        //when
        cubePyraminx.move("L'");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
    }

    @Test
    public void testMoveLLprim(){
        //given
        char[][] expected = new char[][] {
                {'b',   'r','b','g',    'r','r','b','b','b'},
                {'y',   'r','y','y',    'g','g','g','g','g'},
                {'g',   'g','g','y',    'r','r','b','y','y'},
                {'r',   'y','r','r',    'y','y','b','b','b'}
        };
        //when
        cubePyraminx.move("L");
        cubePyraminx.move("L'");

        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());

    }
}
