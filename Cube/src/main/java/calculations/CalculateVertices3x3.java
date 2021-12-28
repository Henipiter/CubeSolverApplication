package calculations;

import DTOs.*;
import cubes.Cube;
import cubes.Cube3x3;
import interpretations.Interpretation3x3Vertices;

import java.util.ArrayList;

public class CalculateVertices3x3 {

    private Interpretation3x3Vertices interpretation3x3Vertices = new Interpretation3x3Vertices();
    private Cube3x3 cube3x3;

    public CalculateVertices3x3(Cube3x3 cube3x3) {
        this.cube3x3 = cube3x3;
    }

    public void refreshCube(Cube cube) {
        interpretation3x3Vertices.interpretVertices(cube);
    }

    public Move getMoveToMoveVertexAboveRightDestination(int vertexIndex, Vertex vertex) {
        int movesCounter = 0;

        while (!interpretation3x3Vertices.isVertexBetweenItsCenters((vertexIndex + movesCounter) % 4, vertex)) {
            movesCounter++;
        }
        return new Move(MoveEnum.U, MoveTypeEnum.returnEnumByInt(movesCounter));
    }

    public Move getMoveToRotateCubeToGetVertexOnFrontSide(int vertexIndex) {
        switch (vertexIndex) {
            case 1:
                return new Move("y");
            case 0:
                return new Move("y'");
        }
        return new Move(MoveEnum.BLANK, MoveTypeEnum.BLANK);
    }

    public ArrayList<Move> getMoveToMoveOutVertexFromFirstLayer(int vertexIndex) {
        String alg = "";
        switch (vertexIndex) {
            case 2:
                alg = "R U R' U'";
                break;
            case 3:
                alg = "L' U' L U";
                break;
        }
        return InspectMove.stringToMoveList(alg);
    }

    public ArrayList<Move> getMoveToJoinVertexIntoFirstLayer(int vertexIndex, char color) {
        Vertex vertex = interpretation3x3Vertices.getVertexArrayList().get(vertexIndex);
        int field = interpretation3x3Vertices.getFieldWithColor(vertex, color);
        switch (vertexIndex) {
            case 2:
                return getMoveToSolveVertexIntoFirstLayerOnRightHand(field);
            case 3:
                return getMoveToSolveVertexIntoFirstLayerOnLeftHand(field);
        }
        return new ArrayList<>();
    }

    private ArrayList<Move> getMoveToSolveVertexIntoFirstLayerOnRightHand(int field) {
        String algorithm = "";
        switch (field) {
            case 0:
                algorithm = "R U2 R' U' R U R'";
                break;
            case 1:
                algorithm = "R U R'";
                break;
            case 2:
                algorithm = "U R U' R'";
                break;
        }
        return InspectMove.stringToMoveList(algorithm);
    }

    private ArrayList<Move> getMoveToSolveVertexIntoFirstLayerOnLeftHand(int field) {
        String algorithm = "";
        switch (field) {
            case 0:
                algorithm = "L' U2 L U L' U' L";
                break;
            case 1:
                algorithm = "L' U' L";
                break;
            case 2:
                algorithm = "U' L' U L";
                break;
        }
        return InspectMove.stringToMoveList(algorithm);
    }

    public Move rotateCubeToGetRightPlacedVertexInCorrectPosition() {
        int movesCounter = 0;
        while (!interpretation3x3Vertices.isVerticesInRightPosition()) {
            movesCounter++;
            cube3x3.move("y");
            refreshCube(cube3x3);
        }
        return new Move(MoveEnum.y, MoveTypeEnum.returnEnumByInt(movesCounter));
    }

    public ArrayList<Move> permuteVertexAlgorithm() {
        return InspectMove.stringToMoveList("L' U R U' L U R' U'");
    }

    public Move getMoveToMoveVertexToOrientationPlace() {
        int movesCounter = 0;
        while (!interpretation3x3Vertices.isVerticesInNotRightOrientation()) {
            movesCounter++;
            cube3x3.move("U");
            refreshCube(cube3x3);
        }
        return new Move(MoveEnum.U, MoveTypeEnum.returnEnumByInt(movesCounter));
    }

    public ArrayList<Move> orientVertexAlgorithm() {
        return InspectMove.stringToMoveList("R' D R D' R' D R D'");
    }
}
