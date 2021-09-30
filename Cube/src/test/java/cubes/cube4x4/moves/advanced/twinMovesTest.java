package cubes.cube4x4.moves.advanced;

import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class twinMovesTest {

    private Cube4x4 cube;
    @BeforeEach
    public void init(){
        cube = new Cube4x4();
    }

    @Test
    public void alg_ruld_test(){
        cube.makeMovesUsingString("z2 y Rw Uw Lw Dw Rw");

        char[][] expected = new char[][] {
                {   'r','r','b','b',    'r','r','b','b',    'g','g','w','w',    'g','g','w','w'},
                {   'r','r','g','g',    'r','r','g','g',    'o','o','b','b',    'o','o','b','b'},
                {   'g','g','o','o',    'g','g','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'y','y','o','o',    'y','y','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'y','y','b','b',    'y','y','b','b',    'g','g','r','r',    'g','g','r','r'},
                {   'w','w','o','o',    'w','w','o','o',    'b','b','y','y',    'b','b','y','y'}
        };
        Assert.assertArrayEquals(expected, cube.getCube());


    }

    @Test
    public void alg_ruldddd_test(){
        cube.makeMovesUsingString("b2 l U2 F2 L2 d' B' b' L B' z Rw U Rw' Rw2 U Rw2 y2 Rw U Rw' x2 y Lw' U Lw Rw U Rw' y' Lw' U' Lw y' U2 Rw U Rw' z x U2 Lw2 U Lw2 y2 Lw' U' Lw x U2 Rw U Rw' U Lw2 U Lw2 x Rw U Rw'");
        char[][] expected = new char[][] {
                {   'r','r','b','b',    'r','r','b','b',    'g','g','w','w',    'g','g','w','w'},
                {   'r','r','g','g',    'r','r','g','g',    'o','o','b','b',    'o','o','b','b'},
                {   'g','g','o','o',    'g','g','o','o',    'y','y','w','w',    'y','y','w','w'},
                {   'y','y','o','o',    'y','y','o','o',    'r','r','w','w',    'r','r','w','w'},
                {   'y','y','b','b',    'y','y','b','b',    'g','g','r','r',    'g','g','r','r'},
                {   'w','w','o','o',    'w','w','o','o',    'b','b','y','y',    'b','b','y','y'}
        };
        Assert.assertArrayEquals(expected, cube.getCube());


    }
}
