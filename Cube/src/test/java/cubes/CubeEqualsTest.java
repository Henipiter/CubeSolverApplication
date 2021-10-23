package cubes;

import DTOs.Vertex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CubeEqualsTest {

    @Test
    public void cube2x2_is_equal_should_return_true(){
        Cube2x2 cube2x2 = new Cube2x2();
        Cube2x2 cube2x2_2 = new Cube2x2();
        Assertions.assertEquals(cube2x2_2, cube2x2);
    }

    @Test
    public void cube2x2_is_equal_should_return_false(){
        Cube2x2 cube2x2 = new Cube2x2();
        Cube2x2 cube2x2_2 = new Cube2x2();
        cube2x2.move("R");
        Assertions.assertNotEquals(cube2x2_2, cube2x2);
    }

    @Test
    public void cube4x4_is_equal_should_return_true(){
        Cube4x4 cube4x4 = new Cube4x4();
        Cube4x4 cube4x4_2 = new Cube4x4();
        Assertions.assertEquals(cube4x4_2, cube4x4);
    }

    @Test
    public void cube4x4_is_equal_should_return_false(){
        Cube4x4 cube4x4 = new Cube4x4();
        Cube4x4 cube4x4_2 = new Cube4x4();
        cube4x4.move("R");
        Assertions.assertNotEquals(cube4x4_2, cube4x4);
    }

    @Test
    public void vertexEqual(){
        Vertex vertexExt1 = Vertex.builder().color(new char[]{'o','w','b'}).build();
        Vertex vertexExt2 = Vertex.builder().color(new char[]{'o','w','b'}).build();
        Assertions.assertEquals(vertexExt1,vertexExt2);
    }

    @Test
    public void vertexEqual2(){
        Vertex vertexExt1 = Vertex.builder().color(new char[]{'w','o','b'}).build();
        Vertex vertexExt2 = Vertex.builder().color(new char[]{'o','w','b'}).build();
        Assertions.assertNotEquals(vertexExt1,vertexExt2);
    }
}
