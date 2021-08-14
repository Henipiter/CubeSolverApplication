package cubes.cube4x4.moves.advanced;

import cubes.Cube2x2;
import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class innerMovesTest {


    private Cube4x4 cube;
    @BeforeEach
    public void init(){
        cube = new Cube4x4();
    }

    @Test
    public void alg_ruld_test(){
        cube.makeMovesUsingString("r u l d");

        char[][] expected = new char[][] {
                {   'w','b','g','w',    'w','b','g','w',    'w','o','g','w',    'w','b','g','w'},
                {   'y','g','b','y',    'y','g','b','y',    'y','r','b','y',    'y','g','b','y'},
                {   'o','o','o','o',    'g','g','y','g',    'b','w','y','b',    'o','o','o','o'},
                {   'r','r','r','r',    'b','b','w','b',    'g','y','w','g',    'r','r','r','r'},
                {   'g','w','y','g',    'r','w','r','r',    'o','o','o','o',    'g','w','y','g'},
                {   'b','y','w','b',    'o','y','o','o',    'r','r','r','r',    'b','y','w','b'}
        };
        Assert.assertArrayEquals(expected, cube.getCube());
    }
}
