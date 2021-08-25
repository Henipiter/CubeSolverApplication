package calculations;

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


    public ArrayList<InspectMove> getMovesToJoinEdgeToCross(int side, int sideEdgeNumber, char crossColor){
        ArrayList<InspectMove> tempAlg = new ArrayList<>();
        int edgeFieldIndex =  interpretation3x3Edges.getIndexFieldOfEdgeWithGivenColor(sideEdgeNumber, crossColor);
        tempAlg.add( getMoveToGetFreeSlotOnGivenSide(side,crossColor));
        if(interpretation3x3Edges.isFieldOnCircumference(side, sideEdgeNumber, edgeFieldIndex)){
            tempAlg.add(getInspectMoveToJoinCircumferenceField(side, sideEdgeNumber));
        }
        else{
            tempAlg.add(new InspectMove(getMoveEnumToJoinField(side), MoveTypeEnum.SIMPLE));
        }
        return tempAlg;
    }

    public InspectMove getInspectMoveToJoinCircumferenceField(int side, int sideEdgeNumber){
        MoveTypeEnum moveTypeEnum= getMoveTypeEnumToJoinCircumferenceField(sideEdgeNumber);
        return new InspectMove(getMoveEnumToJoinField(side), moveTypeEnum);
    }

    private InspectMove getMoveToGetFreeSlotOn4thSide(char crossColor){
        int edgeIndex = interpretation3x3Edges.getFreeSlotOnCross(crossColor);
        switch (edgeIndex){
            case 9:
                return new InspectMove(MoveEnum.D,MoveTypeEnum.PRIM);
            case 11:
                return new InspectMove(MoveEnum.D,MoveTypeEnum.SIMPLE);
            case 8:
                return new InspectMove(MoveEnum.D,MoveTypeEnum.DOUBLE);
        }
        return new InspectMove(MoveEnum.BLANK,MoveTypeEnum.BLANK);
    }

    private InspectMove getMoveToGetFreeSlotFrom4thSideToGivenSide(int side){

        switch (side){
            case 2:
                return new InspectMove(MoveEnum.D,MoveTypeEnum.PRIM);
            case 3:
                return new InspectMove(MoveEnum.D,MoveTypeEnum.SIMPLE);
            case 5:
                return new InspectMove(MoveEnum.D,MoveTypeEnum.DOUBLE);
        }
        return new InspectMove(MoveEnum.BLANK,MoveTypeEnum.BLANK);
    }

    public InspectMove getMoveToGetFreeSlotOnGivenSide(int side, char crossColor){
        InspectMove to4thSide = getMoveToGetFreeSlotOn4thSide(crossColor);
        InspectMove toGivenSide = getMoveToGetFreeSlotFrom4thSideToGivenSide(side);
        MoveTypeEnum moveTypeEnum = MoveTypeEnum.simplify(to4thSide.getMoveTypeEnum(),toGivenSide.getMoveTypeEnum());
        return new InspectMove(MoveEnum.D,moveTypeEnum);
    }

    private MoveTypeEnum getMoveTypeEnumToJoinCircumferenceField(int sideEdgeNumber){
        MoveTypeEnum moveTypeEnum = MoveTypeEnum.BLANK;
        switch (sideEdgeNumber){
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

    private MoveEnum getMoveEnumToJoinField(int side){
        MoveEnum moveEnum = MoveEnum.BLANK;
        switch (side){
            case 2:
                moveEnum =MoveEnum.L;
                break;
            case 3:
                moveEnum =MoveEnum.R;
                break;
            case 4:
                moveEnum =MoveEnum.F;
                break;
            case 5:
                moveEnum =MoveEnum.B;
                break;
        }
        return  moveEnum;

    }


}



