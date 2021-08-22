package cubes.cube4x4.moves.advanced;

import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class rotateMovesTest {


    private Cube4x4 cube;
    @BeforeEach
    public void init(){
        cube = new Cube4x4();
    }

    @Test
    public void alg_Z2Y_test(){
        cube.makeMovesUsingString("z2 y");

        char[][] expected = new char[][] {
                {   'y','y','y','y',    'y','y','y','y',    'y','y','y','y',    'y','y','y','y'},
                {   'w','w','w','w',    'w','w','w','w',    'w','w','w','w',    'w','w','w','w'},
                {   'g','g','g','g',    'g','g','g','g',    'g','g','g','g',    'g','g','g','g'},
                {   'b','b','b','b',    'b','b','b','b',    'b','b','b','b',    'b','b','b','b'},
                {   'o','o','o','o',    'o','o','o','o',    'o','o','o','o',    'o','o','o','o'},
                {   'r','r','r','r',    'r','r','r','r',    'r','r','r','r',    'r','r','r','r'}
                };
        Assert.assertArrayEquals(expected, cube.getCube());
    }
}
