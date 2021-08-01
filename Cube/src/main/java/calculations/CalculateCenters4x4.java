package calculations;

import DTOs.*;
import cubes.Cube;
import interpretations.Interpretation4x4;

import java.util.ArrayList;

public class CalculateCenters4x4 {

    private Interpretation4x4 interpretation4x4;

    public CalculateCenters4x4() {
        interpretation4x4 = new Interpretation4x4();
    }

    public void refreshCube(Cube cube) {
        interpretation4x4.interpretCenters(cube);
    }



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


//    private InspectMove getMoveToSetGivenSideOnXAxisSide(int side) {
//        switch (side) {
//            case 2:
//                return new InspectMove(MoveEnum.Y, MoveTypeEnum.PRIM);
//            case 3:
//                return new InspectMove(MoveEnum.Y, MoveTypeEnum.SIMPLE);
//        }
//        return new InspectMove(MoveEnum.BLANK, MoveTypeEnum.SIMPLE);
//    }

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

//    public ArrayList<InspectMove> getMoveToSetGivenSideOnUp(int side) {
//        ArrayList<InspectMove> alg = new ArrayList<>();
//        alg.add(getMoveToSetGivenSideOnXAxisSide(side));
//        switch (side) {
//            case 0:
//                alg.add(new InspectMove(MoveEnum.BLANK, MoveTypeEnum.SIMPLE));
//                break;
//            case 1:
//                alg.add(new InspectMove(MoveEnum.X, MoveTypeEnum.DOUBLE));
//                break;
//            case 4:
//                alg.add(new InspectMove(MoveEnum.X, MoveTypeEnum.SIMPLE));
//                break;
//            case 5:
//                alg.add(new InspectMove(MoveEnum.X, MoveTypeEnum.PRIM));
//                break;
//        }
//        return alg;
//    }


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
        int diagonallyField = (sourceField + 2) % 2;
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
        boolean isDestSideStripeLengthwise = interpretation4x4.isTwoFieldsFormLengthwiseStripe(destinationSide, color);
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
        if (interpretation4x4.isFieldInGivenColor(sideSource, 0, color)
                || interpretation4x4.isFieldInGivenColor(sideSource, 3, color)) {
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
            setup.setMoveType(MoveTypeEnum.SIMPLE);
        else if (setup.getMoveType() == MoveTypeEnum.SIMPLE)
            setup.setMoveType(MoveTypeEnum.PRIM);
        return setup;
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
                !interpretation4x4.isStripesAreInOneLine(sourceSide, destinationSide, color)) {
            alg.add(new InspectMove(MoveEnum.U, MoveTypeEnum.DOUBLE));
        }
        InspectMove setup = getSetupMoveToJoin(sourceSide, color);
        alg.add(setup); //setup
        alg.add(getMoveToJoin(sourceSide, color)); //join
        alg.add(getReverseSetupMoveToJoin(setup)); //undo setup
        return alg;

    }
}
