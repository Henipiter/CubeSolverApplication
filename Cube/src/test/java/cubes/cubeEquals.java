package cubes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class cubeEquals {

    @Test
    public void cube2x2_is_equal_should_return_true(){
        Cube2x2 cube2x2 = new Cube2x2();
        Cube2x2 cube2x2_2 = new Cube2x2();
        Assertions.assertTrue(cube2x2.equals(cube2x2_2));
    }

    @Test
    public void cube2x2_is_equal_should_return_false(){
        Cube2x2 cube2x2 = new Cube2x2();
        Cube2x2 cube2x2_2 = new Cube2x2();
        cube2x2.moveUsingString("R");
        Assertions.assertFalse(cube2x2.equals(cube2x2_2));
    }

    @Test
    public void cube4x4_is_equal_should_return_true(){
        Cube4x4 cube4x4 = new Cube4x4();
        Cube4x4 cube4x4_2 = new Cube4x4();
        Assertions.assertTrue(cube4x4.equals(cube4x4_2));
    }

    @Test
    public void cube4x4_is_equal_should_return_false(){
        Cube4x4 cube4x4 = new Cube4x4();
        Cube4x4 cube4x4_2 = new Cube4x4();
        cube4x4.moveUsingString("R");
        Assertions.assertFalse(cube4x4.equals(cube4x4_2));
    }
}
