package calculations;

import DTOs.InspectMove;
import DTOs.MoveEnum;
import DTOs.MoveTypeEnum;
import DTOs.Vertex;
import cubes.Cube3x3;
import interpretations.Interpretation3x3Edges;
import interpretations.Interpretation3x3Vertices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

public class CalculateVertices3x3Test {

    Cube3x3 cube;
    Interpretation3x3Vertices interpretation3x3Vertices;
    CalculateVertices3x3 calculateVertices3x3 ;

    @BeforeEach
    void setUp(){
        cube = new Cube3x3();
        interpretation3x3Vertices = new Interpretation3x3Vertices();
        calculateVertices3x3 = new CalculateVertices3x3(cube);
    }

    @ParameterizedTest
    @CsvSource({
            "U, U'", "U2,U2", "U',U"
    })
    void getMoveToMoveVertexAboveRightDestination(String scramble, String expect){

        cube.makeMovesUsingString(scramble);
        calculateVertices3x3.refreshCube(cube);
        interpretation3x3Vertices.interpretVertices(cube);
        Vertex vertex = interpretation3x3Vertices.getVertexArrayList().get(2);

        InspectMove result = calculateVertices3x3.getMoveToMoveVertexAboveRightDestination(2,vertex);
        Assertions.assertEquals(expect, result.toString());
    }







}
