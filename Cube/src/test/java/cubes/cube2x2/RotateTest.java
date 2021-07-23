package cubes.cube2x2;

import cubes.Cube2x2;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class RotateTest {

    @Test
    public void testRotateClockwise(){

        //given
        char[][] input = new char[][] {
                {'g','y','g','b'},
                {'r','y','y','b'},
                {'o','r','b','b'},
                {'g','w','g','r'},
                {'w','o','o','y'},
                {'w','o','w','r'}
        };
        char[][] expected = new char[][] {
                {'g','g','b','y'},
                {'r','y','y','b'},
                {'o','r','b','b'},
                {'g','w','g','r'},
                {'w','o','o','y'},
                {'w','o','w','r'}
        };
        Cube2x2 cube2x2 = new Cube2x2(input);
        //when
        cube2x2.rotateSide(true, 0);

        //then
        Assert.assertArrayEquals(input, cube2x2.getCube());

    }

    @Test
    public void testRotateOtherwise(){

        //given
        char[][] input = new char[][] {
                {'g','y','g','b'},
                {'r','y','y','b'},
                {'o','r','b','b'},
                {'g','w','g','r'},
                {'w','o','o','y'},
                {'w','o','w','r'}
        };
        char[][] expected = new char[][] {
                {'b','y','g','g'},
                {'r','y','y','b'},
                {'o','r','b','b'},
                {'g','w','g','r'},
                {'w','o','o','y'},
                {'w','o','w','r'}
        };
        Cube2x2 cube2x2 = new Cube2x2(input);
        //when
        cube2x2.rotateSide(false, 0);

        //then
        Assert.assertArrayEquals(input, cube2x2.getCube());

    }
}
