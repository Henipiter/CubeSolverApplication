package cubes.cube2x2.moves.scramble;

import cubes.Cube2x2;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScrambleTest {
// R U L D R
    //up Y, f O
    private char[][] input;
    private Cube2x2 cube2x2;

    @BeforeEach
    public void init(){

        cube2x2 = new Cube2x2();
    }


    @Test
    public void X2_Yprim_R_U_L_D_R(){
        //given
        char[][] expected = new char[][] {
                {'r','b','g','w'},
                {'r','g','o','b'},
                {'g','o','y','w'},
                {'y','o','r','w'},
                {'y','b','g','r'},
                {'w','o','b','y'}
        };
        //when
        cube2x2.move("x2");
        cube2x2.move("y'");
        cube2x2.move("R");
        cube2x2.move("U");
        cube2x2.move("L");
        cube2x2.move("D");
        cube2x2.move("R");
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCube());
    }




}
