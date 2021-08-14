package cubes.cube4x4.moves.advanced;

import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class allMovesTest {


    private Cube4x4 cube;
    @BeforeEach
    public void init(){
        cube = new Cube4x4();
    }

    @Test
    public void alg_random_scramble_test(){
        cube.makeMovesUsingString("B' D' F U2 F2 L2 F D2 L2 D2 R2 U2 F' D2 L' D' B L' F L2 U' Fw2 Rw2 F' L B' U2 B Rw2 L Uw2 F2 R' U F' R2 Uw B2 Fw L Uw D L U Rw' B");

        char[][] expected = new char[][] {
                {   'w','r','r','y',    'o','g','r','b',    'o','w','r','g',    'b','g','y','y'},
                {   'y','b','y','o',    'g','w','r','g',    'y','y','g','g',    'r','r','o','g'},
                {   'r','b','y','o',    'w','y','g','g',    'o','w','o','w',    'b','r','b','w'},
                {   'o','r','w','g',    'o','o','b','w',    'o','y','b','g',    'g','r','y','w'},
                {   'w','y','r','r',    'w','w','o','b',    'r','b','y','o',    'b','y','y','o'},
                {   'g','b','w','b',    'o','b','r','g',    'b','g','o','w',    'r','w','b','y'}
        };
        Assert.assertArrayEquals(expected, cube.getCube());
    }
}
