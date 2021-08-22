package interpretations;


import DTOs.Edge;
import DTOs.Vertex;
import cubes.Cube3x3;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class Interpretation3x3VerticesTest {

    Interpretation3x3Vertices interpretation3x3Vertices = new Interpretation3x3Vertices();
    Cube3x3 cube;

    @BeforeEach
    public void init() {
        cube = new Cube3x3();
    }

    @Test
    public void call_interpretEdges() {
        //given
        cube.makeMovesUsingString("R U L D R");
        interpretation3x3Vertices.interpretEdges(cube);
        char[][] expected = new char[][]{
                {'b', 'o','y'}, {'r', 'w','g'}, {'y', 'g','r'}, {'o', 'g','w'},
                {'b', 'w','r'}, {'o', 'b','w'}, {'r', 'y','b'}, {'g', 'y','o'},
        };
        //when
        ArrayList<Vertex> result = interpretation3x3Vertices.getVertexArrayList();
        //then
        for(int i=0;i<8;i++){
            Assert.assertArrayEquals(expected[i], result.get(i).getColor());
        }

    }


}