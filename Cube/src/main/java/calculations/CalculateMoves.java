package calculations;

import DTOs.InspectMove;
import DTOs.MoveEnum;
import DTOs.MoveTypeEnum;

import java.util.ArrayList;

public class CalculateMoves {

    public static InspectMove rotateSideToGetItOnTopAlgorithm(int side) {
        switch (side) {
            case 1:
                return new InspectMove(MoveEnum.x, MoveTypeEnum.DOUBLE);
            case 2:
                return new InspectMove(MoveEnum.z, MoveTypeEnum.SIMPLE);
            case 3:
                return new InspectMove(MoveEnum.z, MoveTypeEnum.PRIM);
            case 4:
                return new InspectMove(MoveEnum.x, MoveTypeEnum.SIMPLE);
            case 5:
                return new InspectMove(MoveEnum.x, MoveTypeEnum.PRIM);
        }
        return new InspectMove(MoveEnum.BLANK, MoveTypeEnum.BLANK);
    }

    public static InspectMove getMoveToSetGivenSideOnFrontExceptBottomAndUpperSide(int side) {
        switch (side) {
            case 2:
                return new InspectMove(MoveEnum.y, MoveTypeEnum.PRIM);
            case 3:
                return new InspectMove(MoveEnum.y, MoveTypeEnum.SIMPLE);
            case 5:
                return new InspectMove(MoveEnum.y, MoveTypeEnum.DOUBLE);
        }
        return new InspectMove(MoveEnum.BLANK, MoveTypeEnum.BLANK);
    }

    public static ArrayList<InspectMove> reduceRepeatingMoves(ArrayList<InspectMove> algorithm){
        int i=0;
        while(i<algorithm.size()-1){
            if(algorithm.get(i).getMoveEnum() == algorithm.get(i+1).getMoveEnum()){
                MoveTypeEnum newMoveType = MoveTypeEnum.simplify(
                        algorithm.get(i).getMoveTypeEnum(), algorithm.get(i+1).getMoveTypeEnum());
                algorithm.get(i).setMoveTypeEnum(newMoveType);
                algorithm.remove(i+1);
            }
            else if(algorithm.get(i+1).getMoveEnum()==MoveEnum.BLANK || algorithm.get(i+1).getMoveTypeEnum()==MoveTypeEnum.BLANK){
                algorithm.remove(i+1);
            }
            else {
                i++;
            }
        }
        return algorithm;
    }

}
