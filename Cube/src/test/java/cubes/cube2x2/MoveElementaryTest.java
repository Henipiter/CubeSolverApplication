package cubes.cube2x2;

import cubes.Cube2x2;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MoveElementaryTest {


    @Test
    public void testMoveElementary(){


        //given
        char[][] input = new char[][] {
                {'w','r','g','y'},
                {'y','o','y','w'},
                {'g','y','o','o'},
                {'w','r','b','r'},
                {'r','b','g','b'},
                {'o','g','b','w'}
        };
        char[][] expected = new char[][] {
                {'w','r','g','y'},
                {'y','o','y','w'},
                {'g','y','o','o'},
                {'w','r','b','r'},
                {'r','b','g','b'},
                {'o','g','b','w'}
        };
        Cube2x2 cube2x2 = new Cube2x2(input);
        int[][] field = new int[][]{{0,1},{1,0}};
        int[] sideOrder = new int[] {2,4,3,5};
        //when

        cube2x2.moveElementary(sideOrder, field);

        //then
        Assert.assertArrayEquals(input, cube2x2.getCube());


    }
}
