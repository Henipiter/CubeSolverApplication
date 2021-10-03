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

    public ArrayList<Move> solveF2L_LBL(char firstCenterColor){
        ArrayList<Move> algorithm = new ArrayList<>();
        algorithm.addAll(solveCross(firstCenterColor));
        algorithm.addAll(solveIncorrectCross());
        algorithm.addAll(solveFirstLayer());
        algorithm.addAll(solveSecondLayer());
        return algorithm;
    }

    @Override
    public String solve(char firstCenterColor) {
        ArrayList<Move> algorithm = new ArrayList<>();
        algorithm.addAll(solveF2L_LBL(firstCenterColor));
        try {
            checkOllParity();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        algorithm.addAll(solveUpperCross());
        algorithm.addAll(solveIncorrectUpperCross());
        algorithm.addAll(solveNotPermutedVertexes());
        try {
            checkPllParity();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        algorithm.addAll(solveNotOrientedVertexes());

        return InspectMove.algorithmToString(algorithm);
    }

    private void updateCubeAndInterpretationAndCalculation(ArrayList<Move> alg) {
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

    private Move rotateCubeToSetCrossOnBottom(char firstCenterColor) {
        char oppositeColor = Interpretation.getColorOfOppositeSide(firstCenterColor);
        int sideWithOppositeColor =
                Interpretation3x3Centers.getCenterNumberWithGivenColor(cube, oppositeColor);
        return CalculateMoves.rotateSideToGetItOnTopAlgorithm(sideWithOppositeColor);
    }

    public ArrayList<Move> solveCross(char firstCenterColor) {
        interpretationEdges.interpretEdges(cube);
        calculateEdges.refreshCube(cube);
        ArrayList<Move> tempAlg = new ArrayList<>();
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
        System.out.println(InspectMove.algorithmToString(tempAlg));
        return tempAlg;
    }

    private ArrayList<Move> recursiveSolveCross(char crossColor, int beginSide, int actualSide) {
        ArrayList<Move> recursiveAlg = new ArrayList<>();
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
            ArrayList<Move> tempAlg = calculateEdges.getMovesToJoinEdgeToCross(actualSide, sideEdgeNumber, crossColor);
            updateCubeAndInterpretationAndCalculation(tempAlg);
            recursiveAlg.addAll(tempAlg);
        }
        return recursiveAlg;
    }

    private boolean isAlgorithmContainsMoveEnum(MoveEnum moveEnum, ArrayList<Move> alg){
        for(Move move : alg){
            if( move.getMoveEnum() == moveEnum){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Move> solveIncorrectCross() {
        interpretationEdges.interpretEdges(cube);
        calculateEdges.refreshCube(cube);
        ArrayList<Move> tempAlg = calculateEdges.getMoveToSolveIncorrectOrderCross();
        System.out.println(InspectMove.algorithmToString(tempAlg));

        return tempAlg;
    }

    private void addInspectMoveAndRefreshCube(ArrayList<Move> tempAlg, Move movement) {
        tempAlg.add(movement);
        cube.move(movement);
        calculateEdges.refreshCube(cube);
        calculateVertices.refreshCube(cube);
    }

    private int refreshVertexIndexAfterMovement(int vertexIndex, Move movement) {
        return (vertexIndex + movement.getMoveTypeEnum().getValue()) % 4;
    }

    private int refreshEdgeIndexAfterMovement(int edgeIndex, Move movement) {
        return (edgeIndex + movement.getMoveTypeEnum().getValue()) % 4;
    }

    private ArrayList<Move> putVertexFromUpperSideToBottomLayer(char crossColor) {
        ArrayList<Move> algorithm = new ArrayList<>();
        Move moveUpperSide;
        Move rotateCube;
        int vertexIndex = interpretation3x3Vertices.getVertexWithGivenColorOnUpperSide(crossColor);
        Vertex vertex = interpretation3x3Vertices.getVertexArrayList().get(vertexIndex);
        moveUpperSide = calculateVertices.getMoveToMoveVertexAboveRightDestination(vertexIndex, vertex);
        vertexIndex = refreshVertexIndexAfterMovement(vertexIndex, moveUpperSide);
        addInspectMoveAndRefreshCube(algorithm, moveUpperSide);

        rotateCube = calculateVertices.getMoveToRotateCubeToGetVertexOnFrontSide(vertexIndex);
        vertexIndex = refreshVertexIndexAfterMovement(vertexIndex, rotateCube);
        addInspectMoveAndRefreshCube(algorithm, rotateCube);
        ArrayList<Move> joinAlg = calculateVertices.getMoveToJoinVertexIntoFirstLayer(vertexIndex, crossColor);
        algorithm.addAll(joinAlg);
        cube.makeMoves(joinAlg);
        return algorithm;
    }

    private ArrayList<Move> putVertexFromBottomLayerToUpperSide() {
        Move rotateCube;
        ArrayList<Move> algorithm = new ArrayList<>();

        int vertexIndex = interpretation3x3Vertices.getIncorrectVertexInFirstLayer();
        rotateCube = calculateVertices.getMoveToRotateCubeToGetVertexOnFrontSide(vertexIndex % 4);
        vertexIndex = refreshVertexIndexAfterMovement(vertexIndex, rotateCube) + 4;

        addInspectMoveAndRefreshCube(algorithm, rotateCube);
        ArrayList<Move> joinAlg = calculateVertices.getMoveToMoveOutVertexFromFirstLayer(vertexIndex % 4);
        algorithm.addAll(joinAlg);

        cube.makeMoves(joinAlg);
        return algorithm;
    }

    public ArrayList<Move> solveFirstLayer() {
        interpretation3x3Vertices.interpretVertices(cube);
        calculateVertices.refreshCube(cube);
        ArrayList<Move> tempAlg = new ArrayList<>();
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
        System.out.println(InspectMove.algorithmToString(tempAlg));

        return tempAlg;
    }


    private ArrayList<Move> putEdgeFromUpperSideToSecondLayer() {
        ArrayList<Move> algorithm = new ArrayList<>();
        int edgeIndex = interpretationEdges.getSecondLayerEdgeIndexOnUpperSide();
        Edge edge = interpretationEdges.getEdgeArrayList().get(edgeIndex);
        Move moveUpperSide = calculateEdges.getMoveToMoveEdgeAboveRightCenter(edgeIndex, edge);
        edgeIndex = refreshEdgeIndexAfterMovement(edgeIndex, moveUpperSide);
        addInspectMoveAndRefreshCube(algorithm, moveUpperSide);
        Move rotateCube = calculateEdges.getMoveToRotateCubeToGetEdgeOnFrontSide(edgeIndex);
        edgeIndex = refreshEdgeIndexAfterMovement(edgeIndex, rotateCube);
        addInspectMoveAndRefreshCube(algorithm, rotateCube);
        ArrayList<Move> joinAlg = calculateEdges.getMoveToJoinEdgeIntoSecondLayer(edgeIndex, edge.getColor()[0]);
        algorithm.addAll(joinAlg);
        cube.makeMoves(joinAlg);
        return algorithm;
    }

    private ArrayList<Move> putEdgeFromSecondLayerToUpperSide() {
        Move rotateCube;
        ArrayList<Move> algorithm = new ArrayList<>();

        int edgeIndex = interpretationEdges.getIncorrectEdgeInSecondLayer();
        rotateCube = calculateEdges.getMoveToRotateCubeToGetEdgeOnFrontSide(edgeIndex);
        edgeIndex = refreshEdgeIndexAfterMovement(edgeIndex, rotateCube);
        addInspectMoveAndRefreshCube(algorithm, rotateCube);


        ArrayList<Move> joinAlg = calculateEdges.getMoveToMoveOutEdgeFromSecondLayer(edgeIndex % 4);
        algorithm.addAll(joinAlg);

        cube.makeMoves(joinAlg);
        return algorithm;
    }

    public ArrayList<Move> solveSecondLayer() {
        interpretationEdges.interpretEdges(cube);
        calculateEdges.refreshCube(cube);
        ArrayList<Move> tempAlg = new ArrayList<>();
        while (!interpretationEdges.isSecondLayerComplete()) {
            if (interpretationEdges.isSecondLayerEdgeOnUpperSide()) {
                tempAlg.addAll(putEdgeFromUpperSideToSecondLayer());
            } else {
                tempAlg.addAll(putEdgeFromSecondLayerToUpperSide());
            }
            interpretationEdges.interpretEdges(cube);
            calculateEdges.refreshCube(cube);
        }
        System.out.println(InspectMove.algorithmToString(tempAlg));

        return tempAlg;
    }

    private ArrayList<Move> getMovesToRotateUpperSideToCorrectPositionAndSolveUpperCross() {
        ArrayList<Move> tempAlg = new ArrayList<>();
        tempAlg.add(calculateEdges.rotateUpperCrossToRightPosition());
        tempAlg.addAll(calculateEdges.upperCrossSolveAlgorithm());
        cube.makeMoves(calculateEdges.upperCrossSolveAlgorithm());
        interpretationEdges.interpretEdges(cube);
        calculateEdges.refreshCube(cube);
        return tempAlg;
    }


    public ArrayList<Move> solveUpperCross() {
        interpretationEdges.interpretEdges(cube);
        calculateEdges.refreshCube(cube);
        ArrayList<Move> tempAlg = new ArrayList<>();
        while (!interpretationEdges.isCrossOnUpperSideIsComplete()) {
            tempAlg.addAll(getMovesToRotateUpperSideToCorrectPositionAndSolveUpperCross());
        }
        System.out.println(InspectMove.algorithmToString(tempAlg));

        return CalculateMoves.reduceRepeatingMoves(tempAlg);
    }

    public void checkOllParity() throws Exception {
        if( interpretationEdges.getNumOfCrossEdges()%2==1){
            throw new Exception("ParityOLL");
        }
    }

    public void checkPllParity() throws Exception {
        if(interpretation3x3Vertices.getNumOfVerticesInRightPlace()==2){
            throw new Exception("PLL Parity");
        }
    }

    public ArrayList<Move> solveIncorrectUpperCross() {

        ArrayList<Move> tempAlg = new ArrayList<>();
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
        System.out.println(InspectMove.algorithmToString(tempAlg));

        return tempAlg;
    }

    public ArrayList<Move> solveNotPermutedVertexes() {
        ArrayList<Move> tempAlg = new ArrayList<>();
        interpretation3x3Vertices.interpretVertices(cube);
        calculateVertices.refreshCube(cube);
        while (!interpretation3x3Vertices.isAllVerticesInRightPlace()) {
            tempAlg.add(calculateVertices.rotateCubeToGetRightPlacedVertexInCorrectPosition());
            tempAlg.addAll(calculateVertices.permuteVertexAlgorithm());
            cube.makeMoves(calculateVertices.permuteVertexAlgorithm());
            interpretation3x3Vertices.interpretVertices(cube);
            calculateVertices.refreshCube(cube);
        }
        System.out.println(InspectMove.algorithmToString(tempAlg));

        return tempAlg;
    }

    public ArrayList<Move> solveNotOrientedVertexes() {
        ArrayList<Move> tempAlg = new ArrayList<>();
        int uMoveCounter = 0;
        interpretation3x3Vertices.interpretVertices(cube);
        calculateVertices.refreshCube(cube);
        while (!interpretation3x3Vertices.isAllVertexesInRightOrientation()) {
            Move uMove = calculateVertices.getMoveToMoveVertexToOrientationPlace();
            uMoveCounter = uMoveCounter + uMove.getMoveTypeEnum().getValue();
            tempAlg.add(uMove);
            tempAlg.addAll(calculateVertices.orientVertexAlgorithm());
            cube.makeMoves(calculateVertices.orientVertexAlgorithm());
            interpretation3x3Vertices.interpretVertices(cube);
            calculateVertices.refreshCube(cube);
        }
        uMoveCounter = (4 - uMoveCounter%4)%4;
        Move lastMove = new Move(MoveEnum.U, MoveTypeEnum.returnEnumByInt(uMoveCounter));
        tempAlg.add(lastMove);
        cube.move(lastMove);
        System.out.println(InspectMove.algorithmToString(tempAlg));


        return tempAlg;
    }

    private void rotateVertexClockwise(){

    }

}
