package calculations;

import DTOs.InspectMove;
import DTOs.MoveEnum;
import DTOs.MoveTypeEnum;
import DTOs.Vertex;
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

    public InspectMove getMoveToMoveVertexAboveRightDestination(int vertexIndex, Vertex vertex) {
        int movesCounter = 0;

        while (!interpretation3x3Vertices.isVertexBetweenItsCenters((vertexIndex+movesCounter)%4,vertex)) {
            movesCounter++;
        }
        return new InspectMove(MoveEnum.U, MoveTypeEnum.returnEnumByInt(movesCounter));
    }

    public InspectMove getMoveToRotateCubeToGetVertexOnFrontSide(int vertexIndex) {
        switch (vertexIndex) {
            case 1:
                return new InspectMove("y");
            case 0:
                return new InspectMove("y'");
        }
        return new InspectMove(MoveEnum.BLANK, MoveTypeEnum.BLANK);
    }

    public ArrayList<InspectMove> getMoveToMoveOutVertexFromFirstLayer(int vertexIndex) {
        String alg = "";
        switch (vertexIndex) {
            case 2:
                alg = "R U R'";
                break;
            case 3:
                alg = "L' U' L";
                break;
        }
        return InspectMove.createAndReturnArrayListFromString(alg);
    }

    public ArrayList<InspectMove> getMoveToJoinVertexIntoFirstLayer(int vertexIndex, char color) {
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

    private ArrayList<InspectMove> getMoveToSolveVertexIntoFirstLayerOnRightHand(int field) {
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
        return InspectMove.createAndReturnArrayListFromString(algorithm);
    }

    private ArrayList<InspectMove> getMoveToSolveVertexIntoFirstLayerOnLeftHand(int field) {
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
        return InspectMove.createAndReturnArrayListFromString(algorithm);
    }

}
