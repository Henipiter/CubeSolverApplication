package calculations;

import DTOs.*;
import cubes.Cube;
import cubes.Cube3x3;
import interpretations.Interpretation3x3Edges;

import java.util.ArrayList;

public class CalculateEdges3x3 extends CalculateMoves {

    private Interpretation3x3Edges interpretation3x3Edges = new Interpretation3x3Edges();
    private Cube3x3 cube3x3;

    public CalculateEdges3x3(Cube3x3 cube3x3) {
        this.cube3x3 = cube3x3;
    }

    public void refreshCube(Cube cube) {
        interpretation3x3Edges.interpretEdges(cube);
    }

    public Move getMovesToMoveInnerEdgeOnConflictEdge(int side, char crossColor) {
        Edge edge = interpretation3x3Edges.getEdgeArrayList().get(interpretation3x3Edges.getRightCrossEdge(side));
        int sideEdgeIndex = interpretation3x3Edges.getEdgeIndexFromSideWithGivenColorOnSide(side, crossColor);
        if (sideEdgeIndex % 2 == 0) {
            MoveEnum moveEnum = getMoveEnumToJoinField(side);
            if (!interpretation3x3Edges.isGivenSideEdgeIndexHasGivenColor(edge, crossColor)) {
                if (sideEdgeIndex == 0) {
                    return new Move(moveEnum, MoveTypeEnum.SIMPLE);
                }
                return new Move(moveEnum, MoveTypeEnum.PRIM);
            }
            if (sideEdgeIndex == 0) {
                return new Move(moveEnum, MoveTypeEnum.PRIM);
            }
            return new Move(moveEnum, MoveTypeEnum.SIMPLE);
        }
        return new Move(MoveEnum.BLANK, MoveTypeEnum.BLANK);
    }

    public ArrayList<Move> getMovesToJoinEdgeToCross1(int side, int sideEdgeNumber, int edgeField, char crossColor) {
        ArrayList<Move> alg = new ArrayList<>();
        switch (sideEdgeNumber) {
            case 2:
                if (edgeField == 0) {
                    alg.add(getMoveToGetFreeSlotOnGivenSide(4, crossColor));
                    alg.addAll(InspectMove.stringToMoveList("F2"));
                } else {
                    alg.add(getMoveToGetFreeSlotOnGivenSide(4, crossColor));
                    alg.addAll(InspectMove.stringToMoveList("F' D' L"));
                }
                break;
            case 7:
                if (edgeField == 0) {
                    alg.add(getMoveToGetFreeSlotOnGivenSide(4, crossColor));
                    alg.addAll(InspectMove.stringToMoveList("F'"));
                } else {
                    alg.add(getMoveToGetFreeSlotOnGivenSide(2, crossColor));
                    alg.addAll(InspectMove.stringToMoveList("L"));
                }
                break;
            case 6:
                if (edgeField == 0) {
                    alg.add(getMoveToGetFreeSlotOnGivenSide(4, crossColor));
                    alg.addAll(InspectMove.stringToMoveList("F"));
                } else {
                    alg.add(getMoveToGetFreeSlotOnGivenSide(3, crossColor));
                    alg.addAll(InspectMove.stringToMoveList("R'"));
                }

                break;
            case 10:
                if (edgeField == 0) {
                    alg.add(getMoveToGetFreeSlotOnGivenSide(4, crossColor));
                } else {
                    alg.add(getMoveToGetFreeSlotOnGivenSide(4, crossColor));
                    alg.addAll(InspectMove.stringToMoveList("F D' L"));
                }
                break;
        }
        return alg;
    }

    public ArrayList<Move> getMovesToJoinEdgeToCross(int side, int sideEdgeNumber, char crossColor) {
        ArrayList<Move> tempAlg = new ArrayList<>();
        int edgeIndex = interpretation3x3Edges.getIndexesOfEdgesOnGivenSide(side)[sideEdgeNumber];
        int edgeFieldIndex = interpretation3x3Edges.getIndexFieldOfEdgeWithGivenColor(edgeIndex, crossColor);
        if (interpretation3x3Edges.isFieldOnCircumference(side, sideEdgeNumber, edgeFieldIndex)) {
            tempAlg.add(getMoveToGetFreeSlotOnGivenSide(side, crossColor));
            tempAlg.add(getMoveToJoinCircumferenceField(side, sideEdgeNumber));
        } else {
            tempAlg.add(getMovesToMoveInnerEdgeOnConflictEdge(side, crossColor));
        }
        return tempAlg;
    }

    public Move getMoveToJoinCircumferenceField(int side, int sideEdgeNumber) {
        MoveTypeEnum moveTypeEnum = getMoveTypeEnumToJoinCircumferenceField(side, sideEdgeNumber);
        return new Move(getMoveEnumToJoinField(side), moveTypeEnum);
    }

    private Move getMoveToGetFreeSlotOn4thSide(char crossColor) {
        int edgeIndex = interpretation3x3Edges.getFreeSlotOnCross(crossColor);
        switch (edgeIndex) {
            case 9:
                return new Move(MoveEnum.D, MoveTypeEnum.PRIM);
            case 11:
                return new Move(MoveEnum.D, MoveTypeEnum.SIMPLE);
            case 8:
                return new Move(MoveEnum.D, MoveTypeEnum.DOUBLE);
        }
        return new Move(MoveEnum.BLANK, MoveTypeEnum.BLANK);
    }

    private Move getMoveToGetFreeSlotFrom4thSideToGivenSide(int side) {
        switch (side) {
            case 2:
                return new Move(MoveEnum.D, MoveTypeEnum.PRIM);
            case 3:
                return new Move(MoveEnum.D, MoveTypeEnum.SIMPLE);
            case 5:
                return new Move(MoveEnum.D, MoveTypeEnum.DOUBLE);
        }
        return new Move(MoveEnum.BLANK, MoveTypeEnum.BLANK);
    }

    public Move getMoveToGetFreeSlotOnGivenSide(int side, char crossColor) {
        int sideEdgeIndex = interpretation3x3Edges.getIndexesOfEdgesOnGivenSide(side)[2];
        if (interpretation3x3Edges.getEdgeArrayList().get(sideEdgeIndex).getColor()[0] != crossColor) {
            return new Move(MoveEnum.BLANK, MoveTypeEnum.BLANK);
        }
        Move to4thSide = getMoveToGetFreeSlotOn4thSide(crossColor);
        Move toGivenSide = getMoveToGetFreeSlotFrom4thSideToGivenSide(side);
        MoveTypeEnum moveTypeEnum = MoveTypeEnum.simplify(to4thSide.getMoveTypeEnum(), toGivenSide.getMoveTypeEnum());
        return new Move(MoveEnum.D, moveTypeEnum);
    }

    private MoveTypeEnum getMoveTypeEnumToJoinCircumferenceField(int side, int sideEdgeNumber) {
        MoveTypeEnum moveTypeEnum = MoveTypeEnum.BLANK;
        switch (sideEdgeNumber) {
            case 0:
                moveTypeEnum = MoveTypeEnum.DOUBLE;
                break;
            case 1:
                moveTypeEnum = MoveTypeEnum.SIMPLE;
                break;
            case 3:
                moveTypeEnum = MoveTypeEnum.PRIM;
                break;
        }
        return moveTypeEnum;
    }

    private MoveEnum getMoveEnumToJoinField(int side) {
        MoveEnum moveEnum = MoveEnum.BLANK;
        switch (side) {
            case 2:
                moveEnum = MoveEnum.L;
                break;
            case 3:
                moveEnum = MoveEnum.R;
                break;
            case 4:
                moveEnum = MoveEnum.F;
                break;
            case 5:
                moveEnum = MoveEnum.B;
                break;
        }
        return moveEnum;
    }

    public Move getMoveToPairCrossEdgesToCenters() {
        int movesCounter = 0;
        int pairedEdges = interpretation3x3Edges.countEdgesPairedWithCenters();
        while (pairedEdges != 2 && pairedEdges != 4) {
            movesCounter++;
            cube3x3.move("D");
            refreshCube(cube3x3);
            pairedEdges = interpretation3x3Edges.countEdgesPairedWithCenters();
        }
        return new Move(MoveEnum.D, MoveTypeEnum.returnEnumByInt(movesCounter));
    }

    public ArrayList<Move> getMoveToSolveIncorrectOrderCross() {
        ArrayList<Move> alg = new ArrayList<>();
        ArrayList<Move> alg2 = new ArrayList<>();
        alg.add(getMoveToPairCrossEdgesToCenters());
        if (interpretation3x3Edges.countEdgesPairedWithCenters() == 2) {
            int notPairedEdgeIndex = interpretation3x3Edges.getIndexOfNotPairedEdgeWithCenter();
            int sideWithNotPairedEdge = interpretation3x3Edges.getSideIndexWithGivenEdgeIndex(notPairedEdgeIndex);
            alg2.add(CalculateMoves.getMoveToSetGivenSideOnFrontExceptBottomAndUpperSide(sideWithNotPairedEdge));
            boolean adjacentEdges = interpretation3x3Edges.isOppositeEdgeIsPairedWithCenter(notPairedEdgeIndex);
            alg2.addAll(getAlgorithmToMakeCorrectOrderCross(adjacentEdges));
            cube3x3.makeMoves(alg2);
            alg.addAll(alg2);
        }
        return alg;
    }

    public ArrayList<Move> getAlgorithmToMakeCorrectOrderCross(boolean adjacentEdges) {
        if (adjacentEdges) {
            return InspectMove.stringToMoveList("R D R' D' R");
        }
        return InspectMove.stringToMoveList("M2 U2 M2");
    }

    public Move getMoveToRotateCubeToGetEdgeOnFrontSide(int edgeIndex) {
        switch (edgeIndex) {
            case 3:
            case 4:
                return new Move("y'");
            case 1:
            case 5:
                return new Move("y");
            case 0:
                return new Move("y2");
        }
        return new Move(MoveEnum.BLANK, MoveTypeEnum.BLANK);
    }

    public ArrayList<Move> getMoveToJoinEdgeIntoSecondLayer(int edgeIndex, char secondCenterColor) {
        if (secondCenterColor == interpretation3x3Edges.getCenterArray()[3]) {
            return InspectMove.stringToMoveList("U R U' R' F R' F' R");
        }
        return InspectMove.stringToMoveList("U' L' U L F' L F L'");
    }

    public ArrayList<Move> getMoveToMoveOutEdgeFromSecondLayer(int edgeIndex) {
        String alg = "";
        switch (edgeIndex) {
            case 2:
                alg = "U R U' R' F R' F' R";
                break;
            case 3:
                alg = "U' L' U L F' L F L'";
                break;
        }
        return InspectMove.stringToMoveList(alg);
    }

    public Move getMoveToMoveEdgeAboveRightCenter(int edgeIndex, Edge edge) {
        int movesCounter = 0;
        while (!interpretation3x3Edges.isEdgeAboveRightCenters((edgeIndex + movesCounter) % 4, edge)) {
            movesCounter++;
        }
        return new Move(MoveEnum.U, MoveTypeEnum.returnEnumByInt(movesCounter));
    }

    public Move rotateUpperCrossToRightPosition() {
        int movesCounter = 0;
        while (!interpretation3x3Edges.isUpperCrossPositionCorrect()) {
            movesCounter++;
            cube3x3.move("U");
            refreshCube(cube3x3);
        }
        return new Move(MoveEnum.U, MoveTypeEnum.returnEnumByInt(movesCounter));
    }

    public Move moveUpperIncorrectCrossToRightPosition() {
        int movesCounter = 0;
        while (interpretation3x3Edges.getNumOfCorrectEdgesInUpperCross() < 2) {
            movesCounter++;
            cube3x3.move("U");
            refreshCube(cube3x3);
        }
        return new Move(MoveEnum.U, MoveTypeEnum.returnEnumByInt(movesCounter));
    }

    public Move moveUpperCrossToRightPositionBeforeOllParity() {
        int movesCounter = 0;
        while (!interpretation3x3Edges.isCorrectPositionBeforeOllParity()) {
            movesCounter++;
            cube3x3.move("U");
            refreshCube(cube3x3);
        }
        return new Move(MoveEnum.U, MoveTypeEnum.returnEnumByInt(movesCounter));
    }

    public Move rotateUpperIncorrectCrossToRightPosition() {
        int movesCounter = 0;
        while (!interpretation3x3Edges.isUpperIncorrectCrossPositionCorrect()) {
            movesCounter++;
            cube3x3.move("y");
            refreshCube(cube3x3);
        }
        return new Move(MoveEnum.y, MoveTypeEnum.returnEnumByInt(movesCounter));
    }

    public ArrayList<Move> upperCrossSolveAlgorithm() {
        return InspectMove.stringToMoveList("F R U R' U' F'");
    }

    public ArrayList<Move> incorrectUpperCrossSolveAlgorithm() {
        return InspectMove.stringToMoveList("R U R' U R U2 R' U");
    }
}
