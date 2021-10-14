package cubes.cube3x3.moves.advanced;

import cubes.Cube3x3;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RandomScrambles {

    private Cube3x3 cube3x3;

    @BeforeEach
    public void init(){
        cube3x3 = new Cube3x3();
    }


    @Test
    public void testRandom1(){
        //given
        char[][] expected = new char[][] {
                {'o','y','y','y','r','b','r','r'},
                {'y','b','o','g','y','w','w','w'},
                {'w','o','w','b','o','g','o','r'},
                {'r','y','b','r','o','y','b','b'},
                {'r','g','y','w','b','g','g','o'},
                {'g','g','g','r','w','o','w','b'}
        };
        //when
        cube3x3.makeMoves("D F' L' B' L' F' D' R' U' R2 D' R2 D2 F2 B2 R2 F2 U' R2 F'");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());
    }

    @Test
    public void testRandom2(){
        //given
        char[][] expected = new char[][] {
                {'g','w','b','g','y','g','g','w'},
                {'o','w','g','w','b','r','g','o'},
                {'o','y','r','o','b','b','o','b'},
                {'w','r','o','g','b','r','r','y'},
                {'w','w','b','o','y','y','o','g'},
                {'w','b','r','y','r','y','r','y'}
        };
        //when
        cube3x3.makeMoves("L2 F B2 R' F2 L D2 U2 L F2 L2 R' B2 R' D L2 U' L U' R D'");
        //then
        Assert.assertArrayEquals(expected, cube3x3.getCube());
    }
}
