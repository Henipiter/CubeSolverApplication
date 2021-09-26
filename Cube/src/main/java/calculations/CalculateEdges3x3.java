package calculations;

import DTOs.Edge;
import DTOs.InspectMove;
import DTOs.MoveEnum;
import DTOs.MoveTypeEnum;
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

    public InspectMove getMovesToMoveInnerEdgeOnConflictEdge(int side, char crossColor) {
        Edge edge = interpretation3x3Edges.getEdgeArrayList().get(interpretation3x3Edges.getRightCrossEdge(side));
        int sideEdgeIndex = interpretation3x3Edges.getEdgeIndexFromSideWithGivenColorOnSide(side, crossColor);

        if (sideEdgeIndex % 2 == 0) {
            MoveEnum moveEnum = getMoveEnumToJoinField(side);

            if (!interpretation3x3Edges.isGivenSideEdgeIndexHasGivenColor(edge, crossColor)) {
                if (sideEdgeIndex == 0) {
                    return new InspectMove(moveEnum, MoveTypeEnum.SIMPLE);
                }
                return new InspectMove(moveEnum, MoveTypeEnum.PRIM);
            }
            if (sideEdgeIndex == 0) {
                return new InspectMove(moveEnum, MoveTypeEnum.PRIM);
            }
            return new InspectMove(moveEnum, MoveTypeEnum.SIMPLE);
        }
        return new InspectMove(MoveEnum.BLANK, MoveTypeEnum.BLANK);
    }


    public ArrayList<InspectMove> getMovesToJoinEdgeToCross(int side, int sideEdgeNumber, char crossColor) {
        ArrayList<InspectMove> tempAlg = new ArrayList<>();
        int edgeIndex = interpretation3x3Edges.getIndexesOfEdgesOnGivenSide(side)[sideEdgeNumber];
        int edgeFieldIndex = interpretation3x3Edges.getIndexFieldOfEdgeWithGivenColor(edgeIndex, crossColor);
        if (interpretation3x3Edges.isFieldOnCircumference(side, sideEdgeNumber, edgeFieldIndex)) {
            tempAlg.add(getMoveToGetFreeSlotOnGivenSide(side, crossColor));
            tempAlg.add(getInspectMoveToJoinCircumferenceField(side, sideEdgeNumber));
        } else {
            tempAlg.add(getMovesToMoveInnerEdgeOnConflictEdge(side, crossColor));
        }
        return tempAlg;
    }

    public InspectMove getInspectMoveToJoinCircumferenceField(int side, int sideEdgeNumber) {
        MoveTypeEnum moveTypeEnum = getMoveTypeEnumToJoinCircumferenceField(side, sideEdgeNumber);
        return new InspectMove(getMoveEnumToJoinField(side), moveTypeEnum);
    }

    private InspectMove getMoveToGetFreeSlotOn4thSide(char crossColor) {
        int edgeIndex = interpretation3x3Edges.getFreeSlotOnCross(crossColor);
        switch (edgeIndex) {
            case 9:
                return new InspectMove(MoveEnum.D, MoveTypeEnum.PRIM);
            case 11:
                return new InspectMove(MoveEnum.D, MoveTypeEnum.SIMPLE);
            case 8:
                return new InspectMove(MoveEnum.D, MoveTypeEnum.DOUBLE);
        }
        return new InspectMove(MoveEnum.BLANK, MoveTypeEnum.BLANK);
    }

    private InspectMove getMoveToGetFreeSlotFrom4thSideToGivenSide(int side) {

        switch (side) {
            case 2:
                return new InspectMove(MoveEnum.D, MoveTypeEnum.PRIM);
            case 3:
                return new InspectMove(MoveEnum.D, MoveTypeEnum.SIMPLE);
            case 5:
                return new InspectMove(MoveEnum.D, MoveTypeEnum.DOUBLE);
        }
        return new InspectMove(MoveEnum.BLANK, MoveTypeEnum.BLANK);
    }

    public InspectMove getMoveToGetFreeSlotOnGivenSide(int side, char crossColor) {
        int sideEdgeIndex = interpretation3x3Edges.getIndexesOfEdgesOnGivenSide(side)[2];
        if (interpretation3x3Edges.getEdgeArrayList().get(sideEdgeIndex).getColor()[0] != crossColor) {
            return new InspectMove(MoveEnum.BLANK, MoveTypeEnum.BLANK);
        }

        InspectMove to4thSide = getMoveToGetFreeSlotOn4thSide(crossColor);
        InspectMove toGivenSide = getMoveToGetFreeSlotFrom4thSideToGivenSide(side);
        MoveTypeEnum moveTypeEnum = MoveTypeEnum.simplify(to4thSide.getMoveTypeEnum(), toGivenSide.getMoveTypeEnum());
        return new InspectMove(MoveEnum.D, moveTypeEnum);
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

    public InspectMove getMoveToPairCrossEdgesToCenters() {
        int movesCounter = 0;
        int pairedEdges = interpretation3x3Edges.countEdgesPairedWithCenters();
        while (pairedEdges != 2 && pairedEdges != 4) {
            movesCounter++;
            cube3x3.moveUsingString("D");
            refreshCube(cube3x3);
            pairedEdges = interpretation3x3Edges.countEdgesPairedWithCenters();
        }
        return new InspectMove(MoveEnum.D, MoveTypeEnum.returnEnumByInt(movesCounter));
    }

    public ArrayList<InspectMove> getMoveToSolveIncorrectOrderCross() {
        ArrayList<InspectMove> alg = new ArrayList<>();
        ArrayList<InspectMove> alg2 = new ArrayList<>();
        alg.add(getMoveToPairCrossEdgesToCenters());
        if (interpretation3x3Edges.countEdgesPairedWithCenters()==2){
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

    public ArrayList<InspectMove> getAlgorithmToMakeCorrectOrderCross(boolean adjacentEdges){
        if(adjacentEdges){
            return InspectMove.createAndReturnArrayListFromString("R D R' D' R");
        }
        return InspectMove.createAndReturnArrayListFromString("M2 U2 M2");
    }

    public InspectMove getMoveToMoveEdgeAboveRightCenter(int edgeIndex, Edge edge) {
        int movesCounter = 0;

        while (!interpretation3x3Edges.isEdgeAboveRightCenters((edgeIndex+movesCounter)%4,edge)) {
            movesCounter++;
        }
        return new InspectMove(MoveEnum.U, MoveTypeEnum.returnEnumByInt(movesCounter));
    }

    public InspectMove getMoveToRotateCubeToGetEdgeOnFrontSide(int edgeIndex) {
        switch (edgeIndex) {
            case 3:
                return new InspectMove("y'");
            case 1:
                return new InspectMove("y");
            case 0:
                return new InspectMove("y2");
        }
        return new InspectMove(MoveEnum.BLANK, MoveTypeEnum.BLANK);
    }

    public ArrayList<InspectMove> getMoveToJoinEdgeIntoSecondLayer(int edgeIndex, char secondCenterColor) {

        if(secondCenterColor == interpretation3x3Edges.getCenterArray()[3]){
            return InspectMove.createAndReturnArrayListFromString("U R U' R' F R' F' R");
        }
        return InspectMove.createAndReturnArrayListFromString("U' L' U L F' L F L'");
    }

    public ArrayList<InspectMove> getMoveToMoveOutEdgeFromSecondLayer(int edgeIndex) {
        String alg = "";
        switch (edgeIndex) {
            case 2:
                alg = "U R U' R' F R' F' R";
                break;
            case 3:
                alg = "U' L' U L F' L F L'";
                break;
        }
        return InspectMove.createAndReturnArrayListFromString(alg);
    }

}



