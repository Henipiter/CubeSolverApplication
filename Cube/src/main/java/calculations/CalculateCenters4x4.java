package calculations;

import DTOs.InspectMove;
import DTOs.Move;
import DTOs.MoveEnum;
import DTOs.MoveTypeEnum;
import cubes.Cube;
import interpretations.Interpretation4x4Centers;

import java.util.ArrayList;

public class CalculateCenters4x4 extends CalculateMoves {

    private Interpretation4x4Centers interpretation4X4Centers;

    public CalculateCenters4x4() {
        interpretation4X4Centers = new Interpretation4x4Centers();
    }

    public void refreshCube(Cube cube) {
        interpretation4X4Centers.interpretCenters(cube);
    }

    public ArrayList<Move> calculateMovesToPrepareJoining(int sourceSide, int destinationSide, char color) {
        if (interpretation4X4Centers.isStripesOnGivenSides(sourceSide, destinationSide, color)) {
            return prepareJoiningIfOnBothSidesAreStripes(sourceSide, destinationSide, color);
        } else {
            ArrayList<Move> alg = new ArrayList<>();
            alg.add(getMoveToRotateDestinationSideToAdaptForSourceSide(sourceSide, destinationSide, color));
            return alg;
        }
    }

    private Move getMoveToRotateDestinationSideToAdaptForSourceSide(int sideSource, int sideDestination, char color) {
        int sourceField = interpretation4X4Centers.getNumOfFieldsOnGivenSideWithGivenColor(sideSource, color);
        int diagonallyField = (sourceField + 2) % 4;
        if (!interpretation4X4Centers.isFieldInGivenColor(sideDestination, diagonallyField, color)) {
            return new Move(MoveEnum.BLANK, MoveTypeEnum.BLANK); //everything is OK
        }
        if (!interpretation4X4Centers.isFieldInGivenColor(sideDestination, sourceField, color)) {
            return new Move(MoveEnum.U, MoveTypeEnum.DOUBLE);
        }
        if (!interpretation4X4Centers.isFieldInGivenColor(sideDestination, (diagonallyField + 1) % 4, color)) {
            return new Move(MoveEnum.U, MoveTypeEnum.PRIM);
        }
        if (!interpretation4X4Centers.isFieldInGivenColor(sideDestination, (diagonallyField + 3) % 4, color)) {
            return new Move(MoveEnum.U, MoveTypeEnum.SIMPLE);
        }
        return new Move(MoveEnum.BLANK, MoveTypeEnum.BLANK); //everything is OK
    }

    public ArrayList<Move> prepareJoiningIfOnBothSidesAreStripes(int sourceSide, int destinationSide, char color) {
        ArrayList<Move> alg = new ArrayList<>();
        boolean isDestSideStripeLengthwise = interpretation4X4Centers.isTwoFieldsFormLengthwiseBlankStripe(destinationSide, color);
        boolean isSrcSideStripeLengthwise = interpretation4X4Centers.isTwoFieldsFormLengthwiseStripe(sourceSide, color);
        if (!isDestSideStripeLengthwise) {
            alg.add(new Move(MoveEnum.U, MoveTypeEnum.SIMPLE));
        }
        if (!isSrcSideStripeLengthwise) {
            switch (sourceSide) {
                case 1:
                    alg.add(new Move(MoveEnum.D, MoveTypeEnum.SIMPLE));
                    break;
                case 4:
                    alg.add(new Move(MoveEnum.F, MoveTypeEnum.SIMPLE));
                    break;
                case 5:
                    alg.add(new Move(MoveEnum.B, MoveTypeEnum.SIMPLE));
                    break;
            }
        }
        if (isDestSideStripeLengthwise && isSrcSideStripeLengthwise) {
            alg.add(new Move(MoveEnum.BLANK, MoveTypeEnum.BLANK));
        }
        return alg;
    }

    public MoveEnum getMoveEnumToSetup(int sideSource, char color) {
        int field = interpretation4X4Centers.getNumOfFieldsOnGivenSideWithGivenColor(sideSource, color);

        if (interpretation4X4Centers.isStripe(sideSource, color) && interpretation4X4Centers.isTwoFieldsFormBlankStripe(0, color)) {
            if (interpretation4X4Centers.isFieldInGivenColor(sideSource, 0, color)
                    && interpretation4X4Centers.isFieldInGivenColor(sideSource, 3, color)) {
                return MoveEnum.Lw;
            } else return MoveEnum.Rw;
        }
        if (field == 0 || field == 3) {
            return MoveEnum.Lw;
        }
        return MoveEnum.Rw;
    }

    public MoveTypeEnum getMoveEnumTypeToSetup(int sideSource, char color) {
        switch (sideSource) {
            case 1:
                return MoveTypeEnum.DOUBLE;
            case 4:
                if (getMoveEnumToSetup(sideSource, color) == MoveEnum.Rw)
                    return MoveTypeEnum.SIMPLE;
                return MoveTypeEnum.PRIM;
            case 5:
                if (getMoveEnumToSetup(sideSource, color) == MoveEnum.Rw)
                    return MoveTypeEnum.PRIM;
                return MoveTypeEnum.SIMPLE;
        }
        return MoveTypeEnum.INVALID;
    }

    public Move getSetupMoveToJoinSingleField(int sideSource, char color) {
        return new Move(getMoveEnumToSetup(sideSource, color), getMoveEnumTypeToSetup(sideSource, color));
    }

    public Move getMoveToJoin(int sideSource, char color) {
        if (interpretation4X4Centers.isStripe(sideSource, color)) {
            return new Move(MoveEnum.U, MoveTypeEnum.DOUBLE);
        }
        if (interpretation4X4Centers.isFieldInGivenColor(sideSource, 0, color)
                || interpretation4X4Centers.isFieldInGivenColor(sideSource, 2, color)) {
            return new Move(MoveEnum.U, MoveTypeEnum.SIMPLE);
        }
        return new Move(MoveEnum.U, MoveTypeEnum.PRIM);
    }

    public ArrayList<Move> calculateMovesToJoinFromSourceSideToDestinationSide(int sourceSide, int destinationSide, char color) {
        ArrayList<Move> alg = new ArrayList<>();
        if (interpretation4X4Centers.isStripesOnGivenSides(sourceSide, destinationSide, color) &&
                !interpretation4X4Centers.isStripesAreNotInOneLine(sourceSide, destinationSide, color) &&
                interpretation4X4Centers.isStripesAreInOneLine(sourceSide, destinationSide, color)) {
            alg.add(new Move(MoveEnum.U, MoveTypeEnum.DOUBLE));
        }
        Move setup = getSetupMoveToJoinSingleField(sourceSide, color);
        alg.add(setup);
        alg.add(getMoveToJoin(sourceSide, color));
        alg.add(InspectMove.getReverseMove(setup));
        return alg;
    }
}
