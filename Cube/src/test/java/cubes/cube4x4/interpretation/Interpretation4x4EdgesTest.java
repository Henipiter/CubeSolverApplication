package cubes.cube4x4.interpretation;

import DTOs.Edge;
import cubes.Cube4x4;
import interpretations.Interpretation4x4Centers;
import interpretations.Interpretation4x4Edges;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

class Interpretation4x4EdgesTest {

    Interpretation4x4Edges interpretation4x4Edges = new Interpretation4x4Edges();
    Cube4x4 cube;

    @BeforeEach
    public void init() {
        cube = new Cube4x4();
    }

    @Test
    public void call_interpretCenters_and_check_correctness() {

        cube.makeMovesUsingString("Rw Uw Lw Dw Rw");
        interpretation4x4Edges.interpretEdges(cube);
        ArrayList<Edge> result = interpretation4x4Edges.getEdgeArrayList();
        char[][] expected = new char[][]{
                {'b', 'y'}, {'r', 'g'},
                {'r', 'w'}, {'y', 'g'},
                {'y', 'r'}, {'o', 'w'},
                {'o', 'g'}, {'b', 'o'},

                {'o', 'y'}, {'w', 'r'},
                {'w', 'g'}, {'b', 'w'},
                {'g', 'r'}, {'y', 'b'},
                {'g', 'w'}, {'y', 'o'},

                {'b', 'r'}, {'o', 'w'},
                {'o', 'b'}, {'r', 'y'},
                {'r', 'b'}, {'g', 'o'},
                {'g', 'y'}, {'b', 'w'},
        };

        for(int i=0;i<24;i++){
            Assert.assertArrayEquals(expected[i], result.get(i).getColor());
        }
    }

}