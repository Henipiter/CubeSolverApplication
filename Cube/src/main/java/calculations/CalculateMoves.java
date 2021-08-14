package calculations;

import DTOs.InspectMove;
import DTOs.MoveEnum;
import DTOs.MoveTypeEnum;

public class CalculateMoves {

    public InspectMove rotateSideToGetItOnTopAlgorithm(int side) {
        switch (side) {
            case 1:
                return new InspectMove(MoveEnum.X, MoveTypeEnum.DOUBLE);
            case 2:
                return new InspectMove(MoveEnum.Z, MoveTypeEnum.SIMPLE);
            case 3:
                return new InspectMove(MoveEnum.Z, MoveTypeEnum.PRIM);
            case 4:
                return new InspectMove(MoveEnum.X, MoveTypeEnum.SIMPLE);
            case 5:
                return new InspectMove(MoveEnum.X, MoveTypeEnum.PRIM);
        }
        return new InspectMove(MoveEnum.BLANK, MoveTypeEnum.SIMPLE);
    }

    public InspectMove getMoveToSetGivenSideOnFrontExceptBottomAndUpperSide(int side) {
        switch (side) {
            case 2:
                return new InspectMove(MoveEnum.Y, MoveTypeEnum.PRIM);
            case 3:
                return new InspectMove(MoveEnum.Y, MoveTypeEnum.SIMPLE);
            case 5:
                return new InspectMove(MoveEnum.Y, MoveTypeEnum.DOUBLE);
        }
        return new InspectMove(MoveEnum.BLANK, MoveTypeEnum.SIMPLE);
    }

}
