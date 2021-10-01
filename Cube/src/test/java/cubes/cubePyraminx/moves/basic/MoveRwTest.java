package cubes.cubePyraminx.moves.basic;

import cubes.CubePyraminx;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveRwTest {
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
    public void testMoveRw(){
        //given
        char[][] expected = new char[][] {
                {'g',   'y','g','g',    'y','y','r','g','g'},
                {'r',   'y','r','r',    'y','y','b','b','b'},
                {'y',   'y','y','b',    'g','g','g','r','r'},
                {'r',   'b','r','r',    'b','b','g','b','b'}
        };
        //when
        cubePyraminx.moveUsingString("Rw");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
    }

    @Test
    public void testMoveRwprim(){
        //given
        char[][] expected = new char[][] {
                {'b',   'r','b','b',    'r','r','y','y','y'},
                {'r',   'b','r','r',    'b','b','g','b','b'},
                {'r',   'b','r','g',    'y','y','y','g','g'},
                {'y',   'r','y','y',    'g','g','g','g','g'}
        };
        //when
        cubePyraminx.moveUsingString("Rw'");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
    }

    @Test
    public void testMoveRwRwprim(){
        //given
        char[][] expected = new char[][] {
                {'b',   'r','b','g',    'r','r','b','b','b'},
                {'y',   'r','y','y',    'g','g','g','g','g'},
                {'g',   'g','g','y',    'r','r','b','y','y'},
                {'r',   'y','r','r',    'y','y','b','b','b'}
        };
        //when
        cubePyraminx.moveUsingString("Rw");
        cubePyraminx.moveUsingString("Rw'");

        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());

    }
}
