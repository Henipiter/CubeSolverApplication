package cubes.cube2x2;



import cubes.Cube2x2;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConstructorTest {

    @Test
    public void testCube2x2EmptyConstructor(){

        //given
        Cube2x2 cube2x2 = new Cube2x2();
        char[][] expected = new char[][] {
                {'w','w','w','w'},
                {'y','y','y','y'},
                {'o','o','o','o'},
                {'r','r','r','r'},
                {'g','g','g','g'},
                {'b','b','b','b'}
        };

        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());

    }

    @Test
    public void testCube2x2FillConstructor(){

        //given
        char[][] example = new char[][] {
                {'g','y','g','b'},
                {'r','y','y','b'},
                {'o','r','b','b'},
                {'g','w','g','r'},
                {'w','o','o','y'},
                {'w','o','w','r'}
        };

        Cube2x2 cube2x2 = new Cube2x2(example);

        //then
        Assert.assertArrayEquals(example, cube2x2.getCube());

    }



}
