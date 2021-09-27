package methods.LBLs;


import DTOs.*;
import calculations.CalculateEdges3x3;
import calculations.CalculateMoves;
import calculations.CalculateVertices3x3;
import cubes.Cube;
import cubes.Cube3x3;
import interpretations.Interpretation;
import interpretations.Interpretation3x3Centers;
import interpretations.Interpretation3x3Edges;
import interpretations.Interpretation3x3Vertices;

import java.util.ArrayList;
import java.util.Arrays;

public class LBL3X3 implements LBL {

    private Cube cube;
    private final Interpretation3x3Edges interpretationEdges;
    private final Interpretation3x3Vertices interpretation3x3Vertices;
    private final CalculateEdges3x3 calculateEdges;
    private final CalculateVertices3x3 calculateVertices;

    public LBL3X3(Cube cube) {
        this.cube = cube;
        interpretationEdges = new Interpretation3x3Edges();
        interpretation3x3Vertices = new Interpretation3x3Vertices();
        calculateVertices = new CalculateVertices3x3((Cube3x3) cube);
        calculateEdges = new CalculateEdges3x3((Cube3x3) cube);
    }

    @Override
    public String solve(char firstCenterColor) {
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        algorithm.addAll(solveCross(firstCenterColor));
        algorithm.addAll(solveIncorrectCross());
        algorithm.addAll(solveFirstLayer());
        algorithm.addAll(solveSecondLayer());
        algorithm.addAll(solveUpperCross());
        algorithm.addAll(solveIncorrectUpperCross());
        algorithm.addAll(solveNotPermutedVertexes());
        algorithm.addAll(solveNotOrientedVertexes());

        return InspectMove.algorithmToString(algorithm);
    }

    private void updateCubeAndInterpretationAndCalculation(ArrayList<InspectMove> alg) {
        cube.makeMoves(alg);
        interpretationEdges.interpretEdges(cube);
        calculateEdges.refreshCube(cube);
    }

    private char[] removeIrrelevantColors(char[] colors) {
        colors[2] = 'x';
        colors[5] = 'x';
        colors[7] = 'x';
        return colors;
    }

    private InspectMove rotateCubeToSetCrossOnBottom(char firstCenterColor) {
        char oppositeColor = Interpretation.getColorOfOppositeSide(firstCenterColor);
        int sideWithOppositeColor =
                Interpretation3x3Centers.getCenterNumberWithGivenColor(cube, oppositeColor);
        return CalculateMoves.rotateSideToGetItOnTopAlgorithm(sideWithOppositeColor);
    }

    public ArrayList<InspectMove> solveCross(char firstCenterColor) {
        interpretationEdges.interpretEdges(cube);
        calculateEdges.refreshCube(cube);
        ArrayList<InspectMove> tempAlg = new ArrayList<>();
        tempAlg.add(rotateCubeToSetCrossOnBottom(firstCenterColor));
        cube.makeMoves(tempAlg);
        interpretationEdges.interpretEdges(cube);
        calculateEdges.refreshCube(cube);

        int i = 0;
        int side;
        int[] order = interpretationEdges.getOrderSolvingCrossEdges(firstCenterColor);
        while (!interpretationEdges.isSolvedCross()) {
            side = order[i];
            while (interpretationEdges.countFieldsWithGivenColor(firstCenterColor, removeIrrelevantColors(
                    interpretationEdges.getColorFromAllEdgesFromGivenSide(side))) > 0) {
                tempAlg.addAll(recursiveSolveCross(firstCenterColor, side, side));
            }
            if (Arrays.equals(order, interpretationEdges.getOrderSolvingCrossEdges(firstCenterColor))) {
                i = (i + 1) % 4;
            } else {
                order = interpretationEdges.getOrderSolvingCrossEdges(firstCenterColor);
                i = 0;
            }
        }
        return tempAlg;
    }

    private ArrayList<InspectMove> recursiveSolveCross(char crossColor, int beginSide, int actualSide) {
        ArrayList<InspectMove> recursiveAlg = new ArrayList<>();
        if (interpretationEdges.isCollisionWithDifferentSide(actualSide, crossColor)) {
            int nextCollisionSide = interpretationEdges.getSideWhichHaveCollisionWithGivenSide(actualSide, crossColor);
            if (nextCollisionSide != beginSide) {
                recursiveAlg.addAll(recursiveSolveCross(crossColor, beginSide, nextCollisionSide));
            }
        }
        if(!isAlgorithmContainsMoveEnum(MoveEnum.D, recursiveAlg))
        //calculate
        {
            int sideEdgeNumber = interpretationEdges.getEdgeIndexFromSideWithGivenColorOnCircumference(actualSide, crossColor);
            if (sideEdgeNumber < 0 || sideEdgeNumber == 2) {
                sideEdgeNumber = interpretationEdges.getEdgeIndexFromSideWithGivenColorOnInnerSide(actualSide, crossColor);
            }
            ArrayList<InspectMove> tempAlg = calculateEdges.getMovesToJoinEdgeToCross(actualSide, sideEdgeNumber, crossColor);
            updateCubeAndInterpretationAndCalculation(tempAlg);
            recursiveAlg.addAll(tempAlg);
        }
        return recursiveAlg;
    }

    private boolean isAlgorithmContainsMoveEnum(MoveEnum moveEnum, ArrayList<InspectMove> alg){
        for(InspectMove move : alg){
            if( move.getMoveEnum() == moveEnum){
                return true;
            }
        }
        return false;
    }

    public ArrayList<InspectMove> solveIncorrectCross() {
        interpretationEdges.interpretEdges(cube);
        calculateEdges.refreshCube(cube);
        return calculateEdges.getMoveToSolveIncorrectOrderCross();
    }

    private void addInspectMoveAndRefreshCube(ArrayList<InspectMove> tempAlg, InspectMove movement) {
        tempAlg.add(movement);
        cube.move(movement);
        calculateEdges.refreshCube(cube);
        calculateVertices.refreshCube(cube);
    }

    private int refreshVertexIndexAfterMovement(int vertexIndex, InspectMove movement) {
        return (vertexIndex + movement.getMoveTypeEnum().getValue()) % 4;
    }

    private int refreshEdgeIndexAfterMovement(int edgeIndex, InspectMove movement) {
        return (edgeIndex + movement.getMoveTypeEnum().getValue()) % 4;
    }

    private ArrayList<InspectMove> putVertexFromUpperSideToBottomLayer(char crossColor) {
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        InspectMove moveUpperSide;
        InspectMove rotateCube;
        int vertexIndex = interpretation3x3Vertices.getVertexWithGivenColorOnUpperSide(crossColor);
        Vertex vertex = interpretation3x3Vertices.getVertexArrayList().get(vertexIndex);
        moveUpperSide = calculateVertices.getMoveToMoveVertexAboveRightDestination(vertexIndex, vertex);
        vertexIndex = refreshVertexIndexAfterMovement(vertexIndex, moveUpperSide);
        addInspectMoveAndRefreshCube(algorithm, moveUpperSide);

        rotateCube = calculateVertices.getMoveToRotateCubeToGetVertexOnFrontSide(vertexIndex);
        vertexIndex = refreshVertexIndexAfterMovement(vertexIndex, rotateCube);
        addInspectMoveAndRefreshCube(algorithm, rotateCube);
        ArrayList<InspectMove> joinAlg = calculateVertices.getMoveToJoinVertexIntoFirstLayer(vertexIndex, crossColor);
        algorithm.addAll(joinAlg);
        cube.makeMoves(joinAlg);
        return algorithm;
    }

    private ArrayList<InspectMove> putVertexFromBottomLayerToUpperSide() {
        InspectMove rotateCube;
        ArrayList<InspectMove> algorithm = new ArrayList<>();

        int vertexIndex = interpretation3x3Vertices.getIncorrectVertexInFirstLayer();
        rotateCube = calculateVertices.getMoveToRotateCubeToGetVertexOnFrontSide(vertexIndex % 4);
        vertexIndex = refreshVertexIndexAfterMovement(vertexIndex, rotateCube) + 4;

        addInspectMoveAndRefreshCube(algorithm, rotateCube);
        ArrayList<InspectMove> joinAlg = calculateVertices.getMoveToMoveOutVertexFromFirstLayer(vertexIndex % 4);
        algorithm.addAll(joinAlg);

        cube.makeMoves(joinAlg);
        return algorithm;
    }

    public ArrayList<InspectMove> solveFirstLayer() {
        interpretation3x3Vertices.interpretVertices(cube);
        calculateVertices.refreshCube(cube);
        ArrayList<InspectMove> tempAlg = new ArrayList<>();
        char crossColor = interpretation3x3Vertices.getCenterArray()[1];
        while (!interpretation3x3Vertices.isFirstLayerComplete()) {
            if (interpretation3x3Vertices.isVertexWithGivenColorOnUpperSide(crossColor)) {
                tempAlg.addAll(putVertexFromUpperSideToBottomLayer(crossColor));
            } else {
                tempAlg.addAll(putVertexFromBottomLayerToUpperSide());
            }
            interpretation3x3Vertices.interpretVertices(cube);
            calculateVertices.refreshCube(cube);
        }
        return tempAlg;
    }


    public ArrayList<InspectMove> putEdgeFromUpperSideToSecondLayer() {
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        int edgeIndex = interpretationEdges.getSecondLayerEdgeIndexOnUpperSide();
        Edge edge = interpretationEdges.getEdgeArrayList().get(edgeIndex);
        InspectMove moveUpperSide = calculateEdges.getMoveToMoveEdgeAboveRightCenter(edgeIndex, edge);
        edgeIndex = refreshEdgeIndexAfterMovement(edgeIndex, moveUpperSide);
        addInspectMoveAndRefreshCube(algorithm, moveUpperSide);
        InspectMove rotateCube = calculateEdges.getMoveToRotateCubeToGetEdgeOnFrontSide(edgeIndex);
        edgeIndex = refreshEdgeIndexAfterMovement(edgeIndex, rotateCube);
        addInspectMoveAndRefreshCube(algorithm, rotateCube);
        ArrayList<InspectMove> joinAlg = calculateEdges.getMoveToJoinEdgeIntoSecondLayer(edgeIndex, edge.getColor()[0]);
        algorithm.addAll(joinAlg);
        cube.makeMoves(joinAlg);
        return algorithm;
    }

    private ArrayList<InspectMove> putEdgeFromSecondLayerToUpperSide() {
        InspectMove rotateCube;
        ArrayList<InspectMove> algorithm = new ArrayList<>();

        int edgeIndex = interpretationEdges.getIncorrectEdgeInSecondLayer();
        rotateCube = calculateEdges.getMoveToRotateCubeToGetEdgeOnFrontSide(edgeIndex);
        edgeIndex = refreshEdgeIndexAfterMovement(edgeIndex, rotateCube);
        addInspectMoveAndRefreshCube(algorithm, rotateCube);


        ArrayList<InspectMove> joinAlg = calculateEdges.getMoveToMoveOutEdgeFromSecondLayer(edgeIndex % 4);
        algorithm.addAll(joinAlg);

        cube.makeMoves(joinAlg);
        return algorithm;
    }

    public ArrayList<InspectMove> solveSecondLayer() {
        interpretationEdges.interpretEdges(cube);
        calculateEdges.refreshCube(cube);
        ArrayList<InspectMove> tempAlg = new ArrayList<>();
        while (!interpretationEdges.isSecondLayerComplete()) {
            if (interpretationEdges.isSecondLayerEdgeOnUpperSide()) {
                tempAlg.addAll(putEdgeFromUpperSideToSecondLayer());
            } else {
                tempAlg.addAll(putEdgeFromSecondLayerToUpperSide());
            }
            interpretationEdges.interpretEdges(cube);
            calculateEdges.refreshCube(cube);
        }
        return tempAlg;
    }

    private ArrayList<InspectMove> getMovesToRotateUpperSideToCorrectPositionAndSolveUpperCross() {
        ArrayList<InspectMove> tempAlg = new ArrayList<>();
        tempAlg.add(calculateEdges.rotateUpperCrossToRightPosition());
        tempAlg.addAll(calculateEdges.upperCrossSolveAlgorithm());
        cube.makeMoves(calculateEdges.upperCrossSolveAlgorithm());
        interpretationEdges.interpretEdges(cube);
        calculateEdges.refreshCube(cube);
        return tempAlg;
    }


    public ArrayList<InspectMove> solveUpperCross() {
        interpretationEdges.interpretEdges(cube);
        calculateEdges.refreshCube(cube);
        ArrayList<InspectMove> tempAlg = new ArrayList<>();
        while (!interpretationEdges.isCrossOnUpperSideIsComplete()) {
            tempAlg.addAll(getMovesToRotateUpperSideToCorrectPositionAndSolveUpperCross());
        }
        return CalculateMoves.reduceRepeatingMoves(tempAlg);
    }

    public ArrayList<InspectMove> solveIncorrectUpperCross() {

        ArrayList<InspectMove> tempAlg = new ArrayList<>();
        interpretationEdges.interpretEdges(cube);
        calculateEdges.refreshCube(cube);
        while (!interpretationEdges.isUpperCrossIsCorrect()) {
            tempAlg.add(calculateEdges.moveUpperIncorrectCrossToRightPosition());
            tempAlg.add(calculateEdges.rotateUpperIncorrectCrossToRightPosition());
            tempAlg.addAll(calculateEdges.incorrectUpperCrossSolveAlgorithm());
            cube.makeMoves(calculateEdges.incorrectUpperCrossSolveAlgorithm());
            interpretationEdges.interpretEdges(cube);
            calculateEdges.refreshCube(cube);
        }
        return tempAlg;
    }

    public ArrayList<InspectMove> solveNotPermutedVertexes() {
        ArrayList<InspectMove> tempAlg = new ArrayList<>();
        interpretation3x3Vertices.interpretVertices(cube);
        calculateVertices.refreshCube(cube);
        while (!interpretation3x3Vertices.isAllVerticesInRightPlace()) {
            tempAlg.add(calculateVertices.rotateCubeToGetRightPlacedVertexInCorrectPosition());
            tempAlg.addAll(calculateVertices.permuteVertexAlgorithm());
            cube.makeMoves(calculateVertices.permuteVertexAlgorithm());
            interpretation3x3Vertices.interpretVertices(cube);
            calculateVertices.refreshCube(cube);
        }
        return tempAlg;
    }

    public ArrayList<InspectMove> solveNotOrientedVertexes() {
        ArrayList<InspectMove> tempAlg = new ArrayList<>();
        int uMoveCounter = 0;
        interpretation3x3Vertices.interpretVertices(cube);
        calculateVertices.refreshCube(cube);
        while (!interpretation3x3Vertices.isAllVertexesInRightOrientation()) {
            InspectMove uMove = calculateVertices.getMoveToMoveVertexToOrientationPlace();
            uMoveCounter = uMoveCounter + uMove.getMoveTypeEnum().getValue();
            tempAlg.add(uMove);
            tempAlg.addAll(calculateVertices.orientVertexAlgorithm());
            cube.makeMoves(calculateVertices.orientVertexAlgorithm());
            interpretation3x3Vertices.interpretVertices(cube);
            calculateVertices.refreshCube(cube);
        }
        uMoveCounter = (4 - uMoveCounter%4)%4;
        InspectMove lastMove = new InspectMove(MoveEnum.U, MoveTypeEnum.returnEnumByInt(uMoveCounter));
        tempAlg.add(lastMove);
        cube.move(lastMove);


        return tempAlg;
    }

}
