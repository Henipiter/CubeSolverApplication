package calculations;

import DTOs.InspectMove;
import DTOs.Move;
import DTOs.MoveEnum;
import DTOs.MoveTypeEnum;
import cubes.Cube;
import cubes.Cube2x2;
import interpretations.Interpretation2x2Vertices;

import java.util.ArrayList;

public class CalculateVertices2x2 {

    private Interpretation2x2Vertices interpretation2x2Vertices = new Interpretation2x2Vertices();
    private Cube2x2 cube2x2;

    public CalculateVertices2x2(Cube2x2 cube2x2) {
        this.cube2x2 = cube2x2;
    }

    public void refreshCube(Cube cube) {
        interpretation2x2Vertices.interpretVertices(cube);
    }

    public Move moveBottomSideToGetRightPlacedVertexInCorrectPosition() {
        int movesCounter = 0;
        while (!interpretation2x2Vertices.isVerticesInRightPosition()) {
            movesCounter++;
            cube2x2.move("D");
            refreshCube(cube2x2);
        }
        return new Move(MoveEnum.D, MoveTypeEnum.returnEnumByInt(movesCounter));
    }

    private Move moveUpperSideToGetRightPlaceVertexInline() {
        int movesCounter = 0;
        while (!interpretation2x2Vertices.isVerticesPairedInRightPosition()) {
            movesCounter++;
            cube2x2.move("U");
            refreshCube(cube2x2);
        }
        return new Move(MoveEnum.U, MoveTypeEnum.returnEnumByInt(movesCounter));
    }

    private Move moveUpperSideToGetRightPlaceVertexAcross() {
        int movesCounter = 0;
        while (!interpretation2x2Vertices.isVerticesAcrossInRightPosition()) {
            movesCounter++;
            cube2x2.move("U");
            refreshCube(cube2x2);
        }
        return new Move(MoveEnum.U, MoveTypeEnum.returnEnumByInt(movesCounter));
    }

    public Move moveUpperSideToGetRightPlacedVertex() {
        interpretation2x2Vertices.interpretVertices(cube2x2);
        if (interpretation2x2Vertices.isVerticesPairedInLine()) {
            return moveUpperSideToGetRightPlaceVertexInline();
        }
        return moveUpperSideToGetRightPlaceVertexAcross();
    }

    public ArrayList<Move> permuteVertexAlgorithm() {
        if(!interpretation2x2Vertices.isVerticesPermuted()) {

            if (interpretation2x2Vertices.isVerticesNotPairedInLine()) {
                return InspectMove.stringToMoveList("R' F R' F2 R U' R' F2 R2 U'");
            }
            return InspectMove.stringToMoveList("R' U' R U' L R U2 R' U' R U2 L' U R2 U R");
        }
        return InspectMove.stringToMoveList("BLANK");
    }
}
