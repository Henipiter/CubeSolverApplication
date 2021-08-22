package interpretations;


import DTOs.Edge;
import cubes.Cube3x3;
import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class Interpretation3x3EdgesTest {

    Interpretation3x3Edges interpretation3x3Edges = new Interpretation3x3Edges();
    Cube3x3 cube;

    @BeforeEach
    public void init() {
        cube = new Cube3x3();
    }

    @Test
    public void call_interpretEdges() {
        //given
        cube.makeMovesUsingString("R U L D R");
        interpretation3x3Edges.interpretEdges(cube);
        char[][] expected = new char[][]{
                {'w', 'o'}, {'y', 'r'}, {'g', 'r'}, {'b', 'o'},
                {'o', 'y'}, {'b', 'w'}, {'g', 'y'}, {'g', 'w'},
                {'b', 'r'}, {'w', 'r'}, {'g', 'o'}, {'y', 'b'}
        };
        //when
        ArrayList<Edge> result = interpretation3x3Edges.getEdgeArrayList();
        //then
        for(int i=0;i<12;i++){
            Assert.assertArrayEquals(expected[i], result.get(i).getColor());
        }

    }


}