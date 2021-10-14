package cubes.cubePyraminx.moves.advanced;

import cubes.CubePyraminx;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveRandomTest {
// R U L R
    //up Y, f O
    private char[][] input;
    private CubePyraminx cubePyraminx;

    @BeforeEach
    public void init(){
        cubePyraminx = new CubePyraminx();
    }


    @Test
    public void testRandomAlg1(){
        //given
        char[][] expected = new char[][] {
                {'r',   'g','g','r',    'r','y','r','g','y'},
                {'y',   'g','b','r',    'b','y','y','r','g'},
                {'b',   'y','r','y',    'r','y','g','g','y'},
                {'g',   'b','b','b',    'g','b','b','r','b'}
        };
        //when
        cubePyraminx.makeMoves("R' B R' B' L B' L R' l' r b u'");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
    }

    @Test
    public void testRandomAlg2(){
        //given
        char[][] expected = new char[][] {
                {'r',   'b','r','g',    'y','y','g','g','g'},
                {'b',   'y','b','y',    'y','y','r','r','r'},
                {'b',   'b','b','r',    'y','y','b','g','g'},
                {'g',   'y','g','g',    'b','b','r','r','r'}
        };
        //when
        cubePyraminx.makeMoves("R L R U R' B' U L' U' R U R' B' L' B' U' R B' U L R' B R' B' L'");
        //then
        Assert.assertArrayEquals(expected, cubePyraminx.getCube());
    }

}
