package interpretations;

import DTOs.Edge;
import cubes.Cube4x4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import java.util.ArrayList;

class Interpretation4x4EdgesTest {

    Interpretation4x4Edges interpretation4x4Edges = new Interpretation4x4Edges();
    Cube4x4 cube;

    @BeforeEach
    public void init() {
        cube = new Cube4x4();
    }

    @Test
    public void call_interpretEdges() {

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

    @Test
    public void call_isChosenEdgeIsPaired(){
        cube.makeMovesUsingString("Rw");
        interpretation4x4Edges.interpretEdges(cube);
        for(int i=0;i<24;i++){
            if(i/2==0 || i/2==2 || i/2==8 || i/2==10){
                Assertions.assertFalse(interpretation4x4Edges.isChosenEdgeIsPaired(i));
            }
            else {
                Assertions.assertTrue(interpretation4x4Edges.isChosenEdgeIsPaired(i));
            }
        }
    }

    @Test
    public void call_isChosenEdgeIsPairedw(){
        cube.makeMovesUsingString("Rw Uw Rw");
        interpretation4x4Edges.interpretEdges(cube);
        for(int i=0;i<24;i++){
            if(i/2==6 || i/2==11){
                Assertions.assertTrue(interpretation4x4Edges.isChosenEdgeIsPaired(i));
            }
            else {
                Assertions.assertFalse(interpretation4x4Edges.isChosenEdgeIsPaired(i));
            }
        }
    }

}