package interpretations;


import DTOs.Vertex;
import cubes.Cube3x3;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

class Interpretation3x3VerticesTest {

    Interpretation3x3Vertices interpretation3x3Vertices = new Interpretation3x3Vertices();
    Cube3x3 cube;

    @BeforeEach
    void init() {
        cube = new Cube3x3();
    }

    @Test
    void call_interpretEdges() {
        //given
        cube.makeMovesUsingString("R U L D R");
        interpretation3x3Vertices.interpretVertices(cube);
        char[][] expected = new char[][]{
                {'b', 'o', 'y'}, {'r', 'w', 'g'}, {'y', 'g', 'r'}, {'o', 'g', 'w'},
                {'b', 'w', 'r'}, {'o', 'b', 'w'}, {'r', 'y', 'b'}, {'g', 'y', 'o'},
        };
        //when
        ArrayList<Vertex> result = interpretation3x3Vertices.getVertexArrayList();
        //then
        for (int i = 0; i < 8; i++) {
            Assert.assertArrayEquals(expected[i], result.get(i).getColor());
        }
    }

    @ParameterizedTest
    @CsvSource({"U U', true", "R U, true", "U' R', true", "R,false"})
    void isVertexHasGivenColor(String scramble, boolean expected) {
        //when
        cube.makeMovesUsingString(scramble);
        interpretation3x3Vertices.interpretVertices(cube);
        Vertex vertex = interpretation3x3Vertices.getVertexArrayList().get(2);
        //when
        boolean result = interpretation3x3Vertices.isVertexHasGivenColor(vertex, 'w');
        //then
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"U U', 0", "R U, 1", "U' R', 2", "R,-1"})
    void getFieldWithColor(String scramble, int expected) {
        //when
        cube.makeMovesUsingString(scramble);
        interpretation3x3Vertices.interpretVertices(cube);
        //when
        int result = interpretation3x3Vertices.getFieldWithColor(interpretation3x3Vertices.getVertexArrayList().get(2), 'w');
        //then
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "U U',2, true", "R U,2, true", "U' R',2, true", "R',2,false",
            "U U',6, true", "R' D',6, true", "D R,6, true", "R,6,false"
    })
    void isVertexBetweenItsCenters(String scramble, int vertexIndex, boolean expected) {
        //when
        cube.makeMovesUsingString(scramble);
        interpretation3x3Vertices.interpretVertices(cube);
        Vertex vertex = interpretation3x3Vertices.getVertexArrayList().get(vertexIndex);
        //when
        boolean result = interpretation3x3Vertices.isVertexBetweenItsCenters(vertexIndex,vertex);
        //then
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "R, true",      "R' D R, true", "F D' F', true",
            "R U, true",    "R' D R U, true", "F D' F' U, true",
            "R U', true",   "R' D R U', true", "F D' F' U', true",
            "R U2, true",   "R' D R U2, true", "F D' F' U2, true",
            "R R', false"
    })
    void isVertexWithGivenColorOnUpperSide(String scramble, boolean expected) {
        //when
        cube.makeMovesUsingString(scramble);
        interpretation3x3Vertices.interpretVertices(cube);
        //when
        boolean result = interpretation3x3Vertices.isVertexWithGivenColorOnUpperSide('y');
        //then
        Assertions.assertEquals(expected,result);
    }

    @ParameterizedTest
    @CsvSource({"R R', -1",
            "R, 2",      "R' D R, 2", "F D' F', 2",
            "R U, 3",    "R' D R U, 3", "F D' F' U, 3",
            "R U', 1",   "R' D R U', 1", "F D' F' U', 1",
            "R U2, 0",   "R' D R U2, 0", "F D' F' U2, 0"

    })
    void getVertexWithGivenColorOnUpperSide(String scramble, int expected) {
        //when
        cube.makeMovesUsingString(scramble);
        interpretation3x3Vertices.interpretVertices(cube);
        //when
        int result = interpretation3x3Vertices.getVertexWithGivenColorOnUpperSide('y');
        //then
        Assertions.assertEquals(expected,result);
    }

    @ParameterizedTest
    @CsvSource({
            "R U R' L' U' L R U R', false",
            "R U R',false",
            "R U' R,false",
            "R, false",
            "R R', true"
    })
    void isFirstLayerComplete(String scramble, boolean expected) {
        //when
        cube.makeMovesUsingString(scramble);
        interpretation3x3Vertices.interpretVertices(cube);
        //when
        boolean result = interpretation3x3Vertices.isFirstLayerComplete();
        //then
        Assertions.assertEquals(expected,result);
    }



    @ParameterizedTest
    @CsvSource({
            "R U R' L' U' L R U R', 7",
            "R U R' U' R U R',6",
            "R U' R,6",
            "R, 6",
            "R R', -1"
    })
    void getIncorrectVertexInFirstLayer(String scramble, int expected) {
        //when
        cube.makeMovesUsingString(scramble);
        interpretation3x3Vertices.interpretVertices(cube);
        //when
        int result = interpretation3x3Vertices.getIncorrectVertexInFirstLayer();
        //then
        Assertions.assertEquals(expected,result);
    }
}