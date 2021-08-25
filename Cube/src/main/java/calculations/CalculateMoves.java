package calculations;

import DTOs.InspectMove;
import DTOs.MoveEnum;
import DTOs.MoveTypeEnum;

public class CalculateMoves {

    public InspectMove rotateSideToGetItOnTopAlgorithm(int side) {
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

    public InspectMove getMoveToSetGivenSideOnFrontExceptBottomAndUpperSide(int side) {
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

}
