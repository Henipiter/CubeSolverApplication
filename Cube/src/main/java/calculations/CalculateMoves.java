package calculations;

import DTOs.Move;
import DTOs.MoveEnum;
import DTOs.MoveTypeEnum;

import java.util.ArrayList;

public class CalculateMoves {

    public static Move rotateSideToGetItOnTopAlgorithm(int side) {
        switch (side) {
            case 1:
                return new Move(MoveEnum.x, MoveTypeEnum.DOUBLE);
            case 2:
                return new Move(MoveEnum.z, MoveTypeEnum.SIMPLE);
            case 3:
                return new Move(MoveEnum.z, MoveTypeEnum.PRIM);
            case 4:
                return new Move(MoveEnum.x, MoveTypeEnum.SIMPLE);
            case 5:
                return new Move(MoveEnum.x, MoveTypeEnum.PRIM);
        }
        return new Move(MoveEnum.BLANK, MoveTypeEnum.BLANK);
    }

    public static Move getMoveToSetGivenSideOnFrontExceptBottomAndUpperSide(int side) {
        switch (side) {
            case 2:
                return new Move(MoveEnum.y, MoveTypeEnum.PRIM);
            case 3:
                return new Move(MoveEnum.y, MoveTypeEnum.SIMPLE);
            case 5:
                return new Move(MoveEnum.y, MoveTypeEnum.DOUBLE);
        }
        return new Move(MoveEnum.BLANK, MoveTypeEnum.BLANK);
    }

    public static ArrayList<Move> reduceRepeatingMoves(ArrayList<Move> algorithm){
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
