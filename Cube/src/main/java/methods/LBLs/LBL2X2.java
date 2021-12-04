package methods.LBLs;


import DTOs.*;
import calculations.CalculateVertices2x2;
import cubes.Cube;
import cubes.Cube2x2;
import cubes.Cube3x3;
import interpretations.Interpretation;
import interpretations.Interpretation2x2Vertices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LBL2X2 implements LBL {

    Solution solution;
    private Cube2x2 cube;

    private Interpretation2x2Vertices interpretation2x2Vertices;
    private CalculateVertices2x2 calculateVertices2x2;

    public LBL2X2(Cube cube) {
        this.cube = (Cube2x2) cube;
        interpretation2x2Vertices = new Interpretation2x2Vertices();
        calculateVertices2x2 = new CalculateVertices2x2((Cube2x2) cube);
    }

    @Override
    public ArrayList<Solution> solve(char firstCenterColor) {
        ArrayList<Solution> algorithm = new ArrayList<>();
        interpretation2x2Vertices.interpretVertices(cube);
        algorithm.add(new Solution(new ArrayList<>(Collections.singletonList(
                rotateCubeToGetColorOnBottomSide(firstCenterColor))), "Rotate"));
        int vertOfBegin = interpretation2x2Vertices.getIndexVertexFromBottomHasColor(firstCenterColor);
        setCentersByVertex(vertOfBegin);

        Cube3x3 cube3x3 = new Cube3x3(cube);
        LBL3X3 lbl3X3 = new LBL3X3(cube3x3);
        ArrayList<Solution> tempAlg = lbl3X3.solveFirstLayer();
        cube.makeMoves(solution.getWholeAlg(tempAlg));
        algorithm.addAll(tempAlg);
        Solution tempSolution = lbl3X3.solveNotOrientedVertexes();
        cube.makeMoves(tempSolution.getAlgorithm());
        algorithm.add(tempSolution);
        algorithm.add(solvePll());
        return algorithm;
    }

    public Solution solvePll() {
        calculateVertices2x2.refreshCube(cube);
        interpretation2x2Vertices.interpretVertices(cube);
        ArrayList<Move> alg = new ArrayList<>();
        alg.add(calculateVertices2x2.moveUpperSideToGetRightPlacedVertex());
        calculateVertices2x2.refreshCube(cube);
        alg.add(calculateVertices2x2.moveBottomSideToGetRightPlacedVertexInCorrectPosition());
        alg.addAll(calculateVertices2x2.permuteVertexAlgorithm());
        cube.makeMoves(calculateVertices2x2.permuteVertexAlgorithm());
        return new Solution(alg, "Permute",
                new ArrayList<>(Arrays.asList(1, 2, 3, 4)), ElementType.VERTEX, ProgressInfo.NONE);
    }

    public void setCentersByVertex(int indexVertex) {
        char[] centerArray = setBottomLeftBackCenters(indexVertex);
        setUpperRightFrontCenters(centerArray);
        cube.setCenter(centerArray);
    }

    private char[] setUpperRightFrontCenters(char[] centerArray) {
        interpretation2x2Vertices.interpretVertices(cube);
        centerArray[0] = Interpretation.getColorOfOppositeSide(centerArray[1]);
        centerArray[3] = Interpretation.getColorOfOppositeSide(centerArray[2]);
        centerArray[4] = Interpretation.getColorOfOppositeSide(centerArray[5]);
        return centerArray;
    }

    private char getLeftCenterColor(int indexVertex, char[] vertexColors) {
        switch (indexVertex) {
            case 4:
            case 7:
                return vertexColors[1];
            case 5:
            case 6:
                return Interpretation.getColorOfOppositeSide(vertexColors[1]);
        }
        return 'x';
    }

    private char getBackCenterColor(int indexVertex, char[] vertexColors) {
        switch (indexVertex) {
            case 6:
            case 7:
                return Interpretation.getColorOfOppositeSide(vertexColors[2]);
            case 4:
            case 5:
                return vertexColors[2];
        }
        return 'x';
    }

    private char[] setBottomLeftBackCenters(int indexVertex) {
        interpretation2x2Vertices.interpretVertices(cube);
        char[] centerArray = new char[6];
        char[] vertexColors = interpretation2x2Vertices.getVertexArrayList().get(indexVertex).getColor();
        centerArray[1] = interpretation2x2Vertices.getVertexArrayList().get(indexVertex).getColor()[0];
        centerArray[2] = getLeftCenterColor(indexVertex, vertexColors);
        centerArray[5] = getBackCenterColor(indexVertex, vertexColors);
        return centerArray;
    }

    private int getIndexOfChar(char c, char[] order) {
        for (int i = 0; i < order.length; i++) {
            if (order[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public int getVertexOfBegin(char color) {
        interpretation2x2Vertices.interpretVertices(cube);
        Vertex candidateVertex;
        for (int i = 7; i >= 0; i--) {
            candidateVertex = interpretation2x2Vertices.getVertexArrayList().get(i);
            if (interpretation2x2Vertices.isVertexHasGivenColor(candidateVertex, color)) {
                return i;
            }
        }
        return -1;
    }

    public Move rotateCubeToGetColorOnBottomSide(char bottomColor) {
        interpretation2x2Vertices.interpretVertices(cube);
        ArrayList<Move> moves = InspectMove.stringToMoveList("BLANK x x' z z' x2");
        int indexMove = 1;
        while (!interpretation2x2Vertices.isBottomSideHasColor(bottomColor)) {
            cube.move(InspectMove.getReverseMove(moves.get(indexMove - 1)));
            cube.move(moves.get(indexMove));
            interpretation2x2Vertices.interpretVertices(cube);
            indexMove++;
        }
        return moves.get(indexMove - 1);
    }
}
