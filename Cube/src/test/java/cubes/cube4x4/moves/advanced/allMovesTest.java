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

    @Test
    public void alg_random(){
        cube.makeMovesUsingString("D R' F' L2 D B' R2 D2 R U2 F R2 B' L2 D2 B' L2 B2 U2 F' x2 L' F B' R' D' F y R D R' D' R U L' U' L U y' L' U' L y U R U' R' y R U R' U' R U R' U2 y' U' L' U L F' L F L' U' y' U' L' U L F' L F L' y2 U' L' U L F' L F L' U' F R U R' U' R U R' U' F' U y R U R' U R U2 R' U L' U R U' L U R' U' y2 L' U R U' L U R' U' L' U R U' L U R' U' U R' D R D' R' D R D' R' D R D' R' D R D' U R' D R D' R' D R D' U2");
        char[][] expected = new char[][] {
                {   'y','y','y','y','y','y','y','y','y','y','y','y','y','y','y','y'},
                {   'w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w'},
                {   'b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b'},
                {   'g','g','g','g','g','g','g','g','g','g','g','g','g','g','g','g'},
                {   'r','r','r','r','r','r','r','r','r','r','r','r','r','r','r','r'},
                {   'o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o'}
        };
        Assert.assertArrayEquals(expected, cube.getCube());
    }

    @Test
    public void alg_oll_parity(){
        cube.makeMovesUsingString("z2 Rw2 B2 U2 Lw U2 Rw' U2 Rw U2 F2 Rw F2 Lw' B2 Rw2");
        char[][] expected = new char[][] {
                {   'y','y','y','y','y','y','y','y','y','y','y','y','g','g','g','g'},
                {   'w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w'},
                {   'r','o','o','o','r','r','r','r','r','r','r','r','r','r','r','r'},
                {   'o','r','r','r','o','o','o','o','o','o','o','o','o','o','o','o'},
                {   'y','y','y','y','g','g','g','g','g','g','g','g','g','g','g','g'},
                {   'b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b'}
        };
        Assert.assertArrayEquals(expected, cube.getCube());
    }

    @Test
    public void alg_pll_parity(){
        cube.makeMovesUsingString("r2 U2 r2 Uw2 r2 u2");
        char[][] expected = new char[][] {
                {   'w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w'},
                {   'y','y','y','y','y','y','y','y','y','y','y','y','y','y','y','y'},
                {   'o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o'},
                {   'r','r','r','r','r','r','r','r','r','r','r','r','r','r','r','r'},
                {   'g','b','b','g','g','g','g','g','g','g','g','g','g','g','g','g'},
                {   'b','g','g','b','b','b','b','b','b','b','b','b','b','b','b','b'}
        };
        Assert.assertArrayEquals(expected, cube.getCube());
    }
}
