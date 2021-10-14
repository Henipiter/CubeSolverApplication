package cubes.cube4x4.moves.advanced;

import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class outerMovesTest {

    private Cube4x4 cube;
    @BeforeEach
    public void init(){
        cube = new Cube4x4();
    }

    @Test
    public void alg_RULD_test(){
        cube.makeMoves("R U L D");

        char[][] expected = new char[][] {
                {   'b','w','w','w',    'b','w','w','w',    'b','w','w','w',    'o','g','g','g'},
                {   'b','b','b','b',    'y','y','y','y',    'y','y','y','y',    'g','g','g','r'},
                {   'o','o','o','g',    'o','o','o','g',    'o','o','o','g',    'w','b','b','y'},
                {   'b','b','b','w',    'r','r','r','r',    'r','r','r','r',    'y','g','g','g'},
                {   'w','r','r','r',    'w','g','g','y',    'w','g','g','y',    'o','o','o','y'},
                {   'y','o','o','o',    'y','b','b','w',    'y','b','b','w',    'r','r','r','r'}
        };
        Assert.assertArrayEquals(expected, cube.getCube());
    }

    @Test
    public void alg_Tperm_test(){
        cube.makeMoves("R U R' U' R' F R2 U' R' U' R U R' F'");

        char[][] expected = new char[][] {
                {   'w','w','w','w',    'w','w','w','w',    'w','w','w','w',    'w','w','w','w'},
                {   'y','y','y','y',    'y','y','y','y',    'y','y','y','y',    'y','y','y','y'},
                {   'o','r','r','o',    'o','o','o','o',    'o','o','o','o',    'o','o','o','o'},
                {   'g','o','o','b',    'r','r','r','r',    'r','r','r','r',    'r','r','r','r'},
                {   'g','g','g','r',    'g','g','g','g',    'g','g','g','g',    'g','g','g','g'},
                {   'b','b','b','r',    'b','b','b','b',    'b','b','b','b',    'b','b','b','b'}
        };
        Assert.assertArrayEquals(expected, cube.getCube());
    }

    @Test
    public void alg_Gperm_test(){
        cube.makeMoves("R' U' R U D' R2 U R' U R U' R U' R2 D");

        char[][] expected = new char[][] {
                {   'w','w','w','w',    'w','w','w','w',    'w','w','w','w',    'w','w','w','w'},
                {   'y','y','y','y',    'y','y','y','y',    'y','y','y','y',    'y','y','y','y'},
                {   'o','b','b','o',    'o','o','o','o',    'o','o','o','o',    'o','o','o','o'},
                {   'g','o','o','b',    'r','r','r','r',    'r','r','r','r',    'r','r','r','r'},
                {   'g','r','r','r',    'g','g','g','g',    'g','g','g','g',    'g','g','g','g'},
                {   'b','g','g','r',    'b','b','b','b',    'b','b','b','b',    'b','b','b','b'}
        };
        Assert.assertArrayEquals(expected, cube.getCube());
    }

}
