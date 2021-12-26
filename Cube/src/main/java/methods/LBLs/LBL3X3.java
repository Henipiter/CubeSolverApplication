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
import java.util.Collections;

public class LBL3X3 implements LBL {

    private final Cube3x3 cube;
    private final Interpretation3x3Edges interpretationEdges;
    private final Interpretation3x3Vertices interpretation3x3Vertices;
    private final CalculateEdges3x3 calculateEdges;
    private final CalculateVertices3x3 calculateVertices;

    public LBL3X3(Cube cube) {
        this.cube = (Cube3x3) cube;
        interpretationEdges = new Interpretation3x3Edges();
        interpretation3x3Vertices = new Interpretation3x3Vertices();
        calculateVertices = new CalculateVertices3x3((Cube3x3) cube);
        calculateEdges = new CalculateEdges3x3((Cube3x3) cube);
    }

    public ArrayList<Solution> solveF2L_LBL(char firstCenterColor) {
        ArrayList<Solution> algorithm = new ArrayList<>();
        algorithm.addAll(solveCross(firstCenterColor));
        algorithm.add(solveIncorrectCross());
        algorithm.addAll(solveFirstLayer());
        algorithm.addAll(solveSecondLayer());
        return algorithm;
    }

    @Override
    public ArrayList<Solution> solve(char firstCenterColor) {
        ArrayList<Solution> algorithm = new ArrayList<>(solveF2L_LBL(firstCenterColor));
        try {
            checkOllParity();
        } catch (Exception exception) {
            cube.getLogger().warning("OLL Parity");
            return null;
        }
        algorithm.add(solveUpperCross());
        algorithm.add(solveIncorrectUpperCross());
        algorithm.add(solveNotPermutedVertexes());
        try {
            checkPllParity();
        } catch (Exception exception) {
            cube.getLogger().warning("PLL Parity");
            return null;
        }
        algorithm.add(solveNotOrientedVertexes());

        return algorithm;
    }


    public ArrayList<Solution> solveCross(char firstCenterColor) {
        interpretationEdges.interpretEdges(cube);
        calculateEdges.refreshCube(cube);
        ArrayList<Solution> solutionLBL = new ArrayList<>();
        ArrayList<Move> tempAlg = new ArrayList<>();
        tempAlg.add(rotateCubeToSetCrossOnBottom(firstCenterColor));

        cube.makeMoves(tempAlg);
        solutionLBL.add(Solution.rotate(new ArrayList<>(tempAlg)));
        tempAlg.clear();
        interpretationEdges.interpretEdges(cube);
        calculateEdges.refreshCube(cube);
        while (!interpretationEdges.isSolvedCross()) {

            int sideEdgeNumber = interpretationEdges.getEdgeIndexFromSideWithGivenColorOnSide(4, firstCenterColor);
            if (sideEdgeNumber == -1) {
                cube.makeMoves("y");
                tempAlg.add(new Move("y"));
                interpretationEdges.interpretEdges(cube);
                calculateEdges.refreshCube(cube);
            } else {
                int rotateMoves = tempAlg.size();
                int fieldEdge = interpretationEdges.getEdgeIndexFieldWithColor(interpretationEdges.getEdgeArrayList().get(sideEdgeNumber), firstCenterColor);

                ArrayList<Move> x = calculateEdges.getMovesToJoinEdgeToCross(sideEdgeNumber, fieldEdge, firstCenterColor);

                int edgeAtEndColored = interpretationEdges.getEdgeIndexAfterJoinToCross(sideEdgeNumber, fieldEdge);
                tempAlg.addAll(x);
                System.out.println(InspectMove.moveListToString(x));
                updateCubeAndInterpretationAndCalculation(x);
                solutionLBL.add(Solution.firstCross(new ArrayList<>(tempAlg),
                        new ArrayList<>(Collections.singletonList(interpretationEdges.getEdgeIndexBeforeRotation(rotateMoves, sideEdgeNumber))),
                        new ArrayList<>(Collections.singletonList(edgeAtEndColored))));
                tempAlg.clear();
            }
        }

        return solutionLBL;
    }

    private void updateCubeAndInterpretationAndCalculation(ArrayList<Move> alg) {
        cube.makeMoves(alg);
        interpretationEdges.interpretEdges(cube);
        calculateEdges.refreshCube(cube);
    }


    private Move rotateCubeToSetCrossOnBottom(char firstCenterColor) {
        char oppositeColor = Interpretation.getColorOfOppositeSide(firstCenterColor);
        int sideWithOppositeColor =
                Interpretation3x3Centers.getCenterNumberWithGivenColor(cube, oppositeColor);
        return CalculateMoves.rotateSideToGetItOnTopAlgorithm(sideWithOppositeColor);
    }

    public Solution solveIncorrectCross() {
        interpretationEdges.interpretEdges(cube);
        calculateEdges.refreshCube(cube);
        ArrayList<Move> tempAlg = calculateEdges.getMoveToSolveIncorrectOrderCross();
        cube.getLogger().info("Incorrect cross solved!");
        return Solution.firstIncorrectCross(tempAlg,
                new ArrayList<>(Arrays.asList(8, 9, 10, 11)),
                new ArrayList<>(Arrays.asList(8, 9, 10, 11)));
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

    private Solution putVertexFromUpperSideToBottomLayer(char crossColor) {
        ArrayList<Move> algorithm = new ArrayList<>();
        Move moveUpperSide;
        Move rotateCube;

        int vertexIndex = interpretation3x3Vertices.getVertexWithGivenColorOnUpperSide(crossColor);
        int originalVertexIndex = vertexIndex;
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

        return Solution.firstLayer(algorithm,
                new ArrayList<>(Collections.singletonList(originalVertexIndex)),
                new ArrayList<>(Collections.singletonList(vertexIndex+4)));
    }

    private Solution putVertexFromBottomLayerToUpperSide() {
        Move rotateCube;
        ArrayList<Move> algorithm = new ArrayList<>();

        int vertexIndex = interpretation3x3Vertices.getIncorrectVertexInFirstLayer();

        int originalVertexIndex = vertexIndex;
        rotateCube = calculateVertices.getMoveToRotateCubeToGetVertexOnFrontSide(vertexIndex % 4);
        vertexIndex = refreshVertexIndexAfterMovement(vertexIndex, rotateCube) + 4;

        addInspectMoveAndRefreshCube(algorithm, rotateCube);
        ArrayList<Move> joinAlg = calculateVertices.getMoveToMoveOutVertexFromFirstLayer(vertexIndex % 4);
        algorithm.addAll(joinAlg);

        cube.makeMoves(joinAlg);
        return Solution.firstLayer(algorithm,
                new ArrayList<>(Collections.singletonList(originalVertexIndex)),
                new ArrayList<>(Collections.singletonList(vertexIndex%4)));
    }

    public ArrayList<Solution> solveFirstLayer() {
        interpretation3x3Vertices.interpretVertices(cube);
        calculateVertices.refreshCube(cube);
        ArrayList<Solution> solution = new ArrayList<>();
        char crossColor = interpretation3x3Vertices.getCenterArray()[1];
        while (!interpretation3x3Vertices.isFirstLayerComplete()) {
            if (interpretation3x3Vertices.isVertexWithGivenColorOnUpperSide(crossColor)) {
                solution.add(putVertexFromUpperSideToBottomLayer(crossColor));
            } else {
                solution.add(putVertexFromBottomLayerToUpperSide());
            }
            interpretation3x3Vertices.interpretVertices(cube);
            calculateVertices.refreshCube(cube);
        }
        cube.getLogger().info("First layer solved!");
        return solution;
    }


    private Solution putEdgeFromUpperSideToSecondLayer() {
        ArrayList<Move> algorithm = new ArrayList<>();
        int edgeIndex = interpretationEdges.getSecondLayerEdgeIndexOnUpperSide();
        int originalEdgeIndex = edgeIndex;
        Edge edge = interpretationEdges.getEdgeArrayList().get(edgeIndex);
        Move moveUpperSide = calculateEdges.getMoveToMoveEdgeAboveRightCenter(edgeIndex, edge);
        edgeIndex = refreshEdgeIndexAfterMovement(edgeIndex, moveUpperSide);
        addInspectMoveAndRefreshCube(algorithm, moveUpperSide);
        Move rotateCube = calculateEdges.getMoveToRotateCubeToGetEdgeOnFrontSide(edgeIndex);
        edgeIndex = refreshEdgeIndexAfterMovement(edgeIndex, rotateCube);
        addInspectMoveAndRefreshCube(algorithm, rotateCube);
        ArrayList<Move> joinAlg = calculateEdges.getMoveToJoinEdgeIntoSecondLayer(edge.getColor()[0]);
        algorithm.addAll(joinAlg);
        cube.makeMoves(joinAlg);
        return Solution.secondLayer(algorithm,
                new ArrayList<>(Collections.singletonList(originalEdgeIndex)),
                new ArrayList<>(Collections.singletonList(
                        interpretationEdges.getEdgeIndexAfterJoinEdgeIntoSecondLayer(edge.getColor()[0]))));
    }

    private Solution putEdgeFromSecondLayerToUpperSide() {
        ArrayList<Move> algorithm = new ArrayList<>();

        int edgeIndex = interpretationEdges.getIncorrectEdgeInSecondLayer();
        int originalEdgeIndex = edgeIndex;
        Move rotateCube = calculateEdges.getMoveToRotateCubeToGetEdgeOnFrontSide(edgeIndex);
        edgeIndex = refreshEdgeIndexAfterMovement(edgeIndex, rotateCube);
        addInspectMoveAndRefreshCube(algorithm, rotateCube);

        ArrayList<Move> joinAlg = calculateEdges.getMoveToMoveOutEdgeFromSecondLayer(edgeIndex % 4);
        algorithm.addAll(joinAlg);

        cube.makeMoves(joinAlg);
        return Solution.secondLayer(algorithm,
                new ArrayList<>(Collections.singletonList(originalEdgeIndex)),
                new ArrayList<>(Collections.singletonList(0)));
    }

    public ArrayList<Solution> solveSecondLayer() {
        interpretationEdges.interpretEdges(cube);
        calculateEdges.refreshCube(cube);
        ArrayList<Solution> solution = new ArrayList<>();
        while (!interpretationEdges.isSecondLayerComplete()) {
            if (interpretationEdges.isSecondLayerEdgeOnUpperSide()) {
                solution.add(putEdgeFromUpperSideToSecondLayer());
            } else {
                solution.add(putEdgeFromSecondLayerToUpperSide());
            }
            interpretationEdges.interpretEdges(cube);
            calculateEdges.refreshCube(cube);
        }
        cube.getLogger().info("Second layer solved!");
        return solution;
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

    public Solution solveUpperCross() {
        interpretationEdges.interpretEdges(cube);
        calculateEdges.refreshCube(cube);
        ArrayList<Move> tempAlg = new ArrayList<>();
        while (!interpretationEdges.isCrossOnUpperSideIsComplete()) {
            tempAlg.addAll(getMovesToRotateUpperSideToCorrectPositionAndSolveUpperCross());
        }
        cube.getLogger().info("Upper cross solved!");
        CalculateMoves.reduceRepeatingMoves(tempAlg);
        return Solution.secondCross(tempAlg, interpretationEdges.getCenterArray()[0]);
    }

    public void checkOllParity() throws Exception {
        if (interpretationEdges.getNumOfCrossEdges() % 2 == 1) {
            throw new Exception("ParityOLL");
        }
    }

    public void checkPllParity() throws Exception {
        if (interpretation3x3Vertices.getNumOfVerticesInRightPlace() == 2) {
            throw new Exception("PLL Parity");
        }
    }

    public Solution solveIncorrectUpperCross() {
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
        cube.getLogger().info("Incorrect upper cross solved!");
        return Solution.secondIncorrectCross(tempAlg,
                new ArrayList<>(Arrays.asList(0, 1, 2, 3)),
                new ArrayList<>(Arrays.asList(0, 1, 2, 3)));
    }

    public Solution solveNotPermutedVertexes() {
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
        cube.getLogger().info("Vertex permuted!");
        return Solution.permutation(tempAlg,
                new ArrayList<>(Arrays.asList(0, 1, 2, 3)),
                new ArrayList<>(Arrays.asList(0, 1, 2, 3)));
    }

    public Solution solveNotOrientedVertexes() {
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
        uMoveCounter = (4 - uMoveCounter % 4) % 4;
        Move lastMove = new Move(MoveEnum.U, MoveTypeEnum.returnEnumByInt(uMoveCounter));
        tempAlg.add(lastMove);
        cube.move(lastMove);
        cube.getLogger().info("Vertex oriented!");
        return Solution.orientation(tempAlg, interpretation3x3Vertices.getCenterArray()[0]);
    }
}
