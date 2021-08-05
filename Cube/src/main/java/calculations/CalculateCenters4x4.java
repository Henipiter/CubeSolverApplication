package calculations;

import DTOs.*;
import cubes.Cube;
import interpretations.Interpretation4x4;

import java.util.ArrayList;

public class CalculateCenters4x4 extends CalculateMoves {

    private Interpretation4x4 interpretation4x4;

    public CalculateCenters4x4() {
        interpretation4x4 = new Interpretation4x4();
    }

    public void refreshCube(Cube cube) {
        interpretation4x4.interpretCenters(cube);
    }

    public ArrayList<InspectMove> calculateMovesToPrepareJoining(int sourceSide, int destinationSide, char color) {
        if (interpretation4x4.isStripesOnGivenSides(sourceSide, destinationSide, color)) {
            return prepareJoiningIfOnBothSidesAreStripes(sourceSide, destinationSide, color);
        } else {
            ArrayList<InspectMove> alg = new ArrayList<>();
            alg.add(getMoveToRotateDestinationSideToAdaptForSourceSide(sourceSide, destinationSide, color));
            return alg;
        }
    }

    //diagonally field on destination side must have different color than field on source side
    private InspectMove getMoveToRotateDestinationSideToAdaptForSourceSide(int sideSource, int sideDestination, char color) {
        int sourceField = interpretation4x4.getNumOfFieldsOnGivenSideWithGivenColor(sideSource, color);
        int diagonallyField = (sourceField + 2) % 4;
        if(!interpretation4x4.isFieldInGivenColor(sideDestination,diagonallyField,color)){
            return new InspectMove(MoveEnum.BLANK, MoveTypeEnum.SIMPLE); //everything is OK
        }
        if (!interpretation4x4.isFieldInGivenColor(sideDestination, sourceField, color)) {
            return new InspectMove(MoveEnum.U, MoveTypeEnum.DOUBLE);
        }
        if (!interpretation4x4.isFieldInGivenColor(sideDestination, (diagonallyField + 1) % 4, color)) {
            return new InspectMove(MoveEnum.U, MoveTypeEnum.PRIM);
        }
        if (!interpretation4x4.isFieldInGivenColor(sideDestination, (diagonallyField + 3) % 4, color)) {
            return new InspectMove(MoveEnum.U, MoveTypeEnum.SIMPLE);
        }
        return new InspectMove(MoveEnum.BLANK, MoveTypeEnum.SIMPLE); //everything is OK
    }

    public ArrayList<InspectMove> prepareJoiningIfOnBothSidesAreStripes(int sourceSide, int destinationSide, char color) {
        ArrayList<InspectMove> alg = new ArrayList<>();
        boolean isDestSideStripeLengthwise = interpretation4x4.isTwoFieldsFormLengthwiseBlankStripe(destinationSide, color);
        boolean isSrcSideStripeLengthwise = interpretation4x4.isTwoFieldsFormLengthwiseStripe(sourceSide, color);
        if (!isDestSideStripeLengthwise) {
            alg.add(new InspectMove(MoveEnum.U, MoveTypeEnum.SIMPLE));
        }
        if (!isSrcSideStripeLengthwise) {
            switch (sourceSide) {
                case 1:
                    alg.add(new InspectMove(MoveEnum.D, MoveTypeEnum.SIMPLE));
                    break;
                case 4:
                    alg.add(new InspectMove(MoveEnum.F, MoveTypeEnum.SIMPLE));
                    break;
                case 5:
                    alg.add(new InspectMove(MoveEnum.B, MoveTypeEnum.SIMPLE));
                    break;
            }
        }
        if (isDestSideStripeLengthwise && isSrcSideStripeLengthwise) {
            alg.add(new InspectMove(MoveEnum.BLANK, MoveTypeEnum.SIMPLE));
        }
        return alg;
    }

    public MoveEnum getMoveEnumToSetup(int sideSource, char color) {
        int field = interpretation4x4.getNumOfFieldsOnGivenSideWithGivenColor(sideSource, color);
        if (field ==0 || field ==3) {
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

    public InspectMove getSetupMoveToJoin(int sideSource, char color) {
        return new InspectMove(getMoveEnumToSetup(sideSource, color), getMoveEnumTypeToSetup(sideSource, color));
    }

    public InspectMove getReverseSetupMoveToJoin(InspectMove setup) {
        if (setup.getMoveType() == MoveTypeEnum.PRIM)
            return new InspectMove(setup.getMove(), MoveTypeEnum.SIMPLE);
        else if (setup.getMoveType() == MoveTypeEnum.SIMPLE)
            return new InspectMove(setup.getMove(), MoveTypeEnum.PRIM);
        return new InspectMove(setup);
    }

    public InspectMove getMoveToJoin(int sideSource, char color) {
        if (interpretation4x4.isStripe(sideSource, color)) {
            return new InspectMove(MoveEnum.U, MoveTypeEnum.DOUBLE);
        }
        if (interpretation4x4.isFieldInGivenColor(sideSource, 0, color)
                || interpretation4x4.isFieldInGivenColor(sideSource, 2, color)) {
            return new InspectMove(MoveEnum.U, MoveTypeEnum.SIMPLE);
        }
        return new InspectMove(MoveEnum.U, MoveTypeEnum.PRIM);
    }

    public ArrayList<InspectMove> calculateMovesToJoinFromSourceSideToDestinationSide(int sourceSide, int destinationSide, char color) {
        ArrayList<InspectMove> alg = new ArrayList<>();
        if (interpretation4x4.isStripesOnGivenSides(sourceSide,destinationSide,color) &&
                interpretation4x4.isStripesAreInOneLine(sourceSide, destinationSide, color)) {
            alg.add(new InspectMove(MoveEnum.U, MoveTypeEnum.DOUBLE));
        }
        InspectMove setup = getSetupMoveToJoin(sourceSide, color);
        alg.add(setup); //setup
        alg.add(getMoveToJoin(sourceSide, color)); //join
        alg.add(getReverseSetupMoveToJoin(setup)); //undo setup
        return alg;

    }
}
