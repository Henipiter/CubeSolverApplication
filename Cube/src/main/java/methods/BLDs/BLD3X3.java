package methods.BLDs;


import DTOs.*;
import calculations.CalculateMoves;
import cubes.Cube;
import cubes.Cube3x3;
import interpretations.Interpretation3x3EdgesExt;
import interpretations.Interpretation3x3VerticesExt;

import java.util.ArrayList;
import java.util.Arrays;

public class BLD3X3 implements BLD {

    private final boolean[] vertexCorrect = new boolean[8];
    private final boolean[] edgeCorrect = new boolean[12];
    private boolean parity;

    private final Interpretation3x3VerticesExt interpretationCubeVertex = new Interpretation3x3VerticesExt();
    private final Interpretation3x3VerticesExt interpretationPatternCubeVertex = new Interpretation3x3VerticesExt();
    private final Interpretation3x3EdgesExt interpretationCubeEdge = new Interpretation3x3EdgesExt();
    private final Interpretation3x3EdgesExt interpretationPatternCubeEdge = new Interpretation3x3EdgesExt();

    private final Cube cube;
    private final Cube patternCube;

    public BLD3X3(Cube cube) {
        this.cube = cube;
        patternCube = new Cube3x3();
        rotatePatternCube();
        interpretationPatternCubeVertex.interpretVertices(patternCube);
        interpretationPatternCubeEdge.interpretEdges(patternCube);
    }

    @Override
    public ArrayList solve() {
        ArrayList<SolutionBLD> solutionBLDs = new ArrayList<>();
        //TODO orient cube
        solutionBLDs.addAll(solveAllVertices());
        if (parity) {
            solutionBLDs.add(solveParity());
        }
        solutionBLDs.addAll(solveAllEdges());

        return solutionBLDs;
    }

    public SolutionBLD solveParity() {
        SolutionBLD solutionBLD = new SolutionBLD();
        if (parity) {
            Algorithm algorithm = new Algorithm();
            solutionBLD.setMarks("<PARITY>");
            solutionBLD.setAlgorithm(InspectMove.stringToMoveList(algorithm.getPerm("R")));
        }
        return solutionBLD;
    }

    public ArrayList<SolutionBLD> solveAllVertices() {
        ArrayList<SolutionBLD> solution = new ArrayList<>();
        interpretationCubeVertex.interpretVertices(cube);
        parity = false;
        noteUnsolvedVertices();
        while (countSolvedVertices() < 8) {
            int vertexIndex = getUnresolvedVertex();
            if (vertexIndex != -1) {
                solution.addAll(solveSingleVertex(vertexIndex, 0, true));
            }
        }
        return solution;
    }

    public ArrayList<SolutionBLD> solveAllEdges() {
        ArrayList<SolutionBLD> solution = new ArrayList<>();
        interpretationCubeEdge.interpretEdges(cube);
        noteUnsolvedEdges();
        while (countSolvedEdges() < 12) {
            int edgeIndex = getUnresolvedEdge();
            if (edgeIndex != -1) {
                solution.addAll(solveSingleEdge(edgeIndex, 0, true));
            }
        }
        return solution;
    }

    private ArrayList<SolutionBLD> solveSingleVertex(int vertexIndex, int fieldIndex, boolean start) {
        ArrayList<SolutionBLD> solution = new ArrayList<>();
        VertexExt cubeVertex = interpretationCubeVertex.getVertexExtArrayList().get(vertexIndex);
        int destinationVertexIndex = searchRightPlaceForVertex(cubeVertex);
        int destinationFieldIndex = getRightVertexField(destinationVertexIndex, cubeVertex.getColor()[fieldIndex]);
        if (!start) {
            vertexCorrect[vertexIndex] = true;
        }
        if (vertexIndex != 0) {
            solution.add(addToSolutionVertex(vertexIndex, fieldIndex));
        }
        parity = !parity;
        if (!vertexCorrect[destinationVertexIndex]) {
            solution.addAll(solveSingleVertex(destinationVertexIndex, destinationFieldIndex, false));
        }
        return solution;
    }

    private ArrayList<SolutionBLD> solveSingleEdge(int edgeIndex, int fieldIndex, boolean start) {
        ArrayList<SolutionBLD> solution = new ArrayList<>();
        EdgeExt cubeEdge = interpretationCubeEdge.getEdgeExtArrayList().get(edgeIndex);
        int destinationEdgeIndex = searchRightPlaceForEdge(cubeEdge);
        int destinationFieldIndex = getRightEdgeField(destinationEdgeIndex, cubeEdge.getColor()[fieldIndex]);
        if (!start) {
            edgeCorrect[edgeIndex] = true;
        }
        if (edgeIndex != 1) {
            solution.add(addToSolutionEdge(edgeIndex, fieldIndex));
        }
        if (!edgeCorrect[destinationEdgeIndex]) {
            solution.addAll(solveSingleEdge(destinationEdgeIndex, destinationFieldIndex, false));
        }
        return solution;
    }

    private SolutionBLD addToSolutionVertex(int vertexIndex, int fieldIndex) {
        SolutionBLD solution = new SolutionBLD();
        VertexExt vertexExt = interpretationCubeVertex.getVertexExtArrayList().get(vertexIndex);
        solution.setAlgorithm(
                getSetupAndAlgorithmAndReverseSetup("Y", vertexExt.getSetup().get(fieldIndex)));
        solution.setElementIndexes(new ArrayList<>(Arrays.asList(0, vertexIndex)));
        solution.setMarks(vertexExt.getName().get(fieldIndex));
        return solution;
    }

    private SolutionBLD addToSolutionEdge(int edgeIndex, int fieldIndex) {
        SolutionBLD solution = new SolutionBLD();
        EdgeExt edgeExt = interpretationCubeEdge.getEdgeExtArrayList().get(edgeIndex);
        solution.setAlgorithm(getSetupAndAlgorithmAndReverseSetup(
                edgeExt.getAlgorithm().get(fieldIndex), edgeExt.getSetup().get(fieldIndex)));
        solution.setElementIndexes(new ArrayList<>(Arrays.asList(1, edgeIndex)));
        solution.setMarks(edgeExt.getName().get(fieldIndex));
        return solution;
    }

    private ArrayList<Move> getSetupAndAlgorithmAndReverseSetup(String permutation, String setup) {
        final Algorithm algorithm = new Algorithm();
        ArrayList<Move> alg = new ArrayList<>();
        alg.addAll(InspectMove.stringToMoveList(setup));
        alg.addAll(InspectMove.stringToMoveList(algorithm.getPerm(permutation)));
        alg.addAll(InspectMove.getReverseAlgorithm(InspectMove.stringToMoveList(permutation)));
        return alg;
    }

    public int searchRightPlaceForVertex(VertexExt cubeVertex) {
        for (int i = 0; i < 8; i++) {
            if (checkRotatedVertex(cubeVertex, i)) {
                return i;
            }
        }
        return -1;
    }

    public int searchRightPlaceForEdge(EdgeExt cubeEdge) {
        for (int i = 0; i < 12; i++) {
            if (checkRotatedEdge(cubeEdge, i)) {
                return i;
            }
        }
        return -1;
    }

    public int getRightVertexField(int vertexIndex, char fieldColor) {
        for (int i = 0; i < 3; i++) {
            if (fieldColor == interpretationPatternCubeVertex.getVertexArrayList()
                    .get(vertexIndex).getColor()[i]) {
                return i;
            }
        }
        return -1;
    }

    public int getRightEdgeField(int edgeIndex, char fieldColor) {
        for (int i = 0; i < 2; i++) {
            if (fieldColor == interpretationPatternCubeEdge.getEdgeArrayList()
                    .get(edgeIndex).getColor()[i]) {
                return i;
            }
        }
        return -1;
    }

    private void rotatePatternCube() {
        patternCube.move(CalculateMoves.rotateSideToGetItOnTopAlgorithm(cube.getCenter()[0]));
        patternCube.move(CalculateMoves.getMoveToSetGivenSideOnFrontExceptBottomAndUpperSide(cube.getCenter()[4]));
    }

    private int getUnresolvedVertex() {
        for (int i = 0; i < 8; i++) {
            if (!vertexCorrect[i]) {
                return i;
            }
        }
        return -1;
    }

    private int getUnresolvedEdge() {
        int[] order = new int[]{1, 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        for (int i : order) {
            if (!edgeCorrect[i]) {
                return i;
            }
        }
        return -1;
    }

    private int countSolvedVertices() {
        int counter = 0;
        for (int i = 0; i < 8; i++) {
            if (vertexCorrect[i]) {
                counter++;
            }
        }
        return counter;
    }

    private int countSolvedEdges() {
        int counter = 0;
        for (int i = 0; i < 12; i++) {
            if (edgeCorrect[i]) {
                counter++;
            }
        }
        return counter;
    }

    private void noteUnsolvedVertices() {
        for (int i = 0; i < 8; i++) {
            vertexCorrect[i] = Arrays.equals(interpretationCubeVertex.getVertexArrayList().get(i).getColor(),
                    interpretationPatternCubeVertex.getVertexArrayList().get(i).getColor());
        }
    }

    private void noteUnsolvedEdges() {
        for (int i = 0; i < 12; i++) {
            edgeCorrect[i] = Arrays.equals(interpretationCubeEdge.getEdgeExtArrayList().get(i).getColor(),
                    interpretationPatternCubeEdge.getEdgeExtArrayList().get(i).getColor());
        }
    }

    public boolean checkRotatedVertex(VertexExt cubeVertex, int patternVertexIndex) {
        VertexExt rotatedVertex = interpretationCubeVertex.rotateVertexColor(cubeVertex);
        VertexExt twiceRotatedVertex = interpretationCubeVertex.rotateVertexColor(rotatedVertex);
        VertexExt patternVertex = interpretationPatternCubeVertex.getVertexExtArrayList().get(patternVertexIndex);
        return isVertexInRightPlace(patternVertex, cubeVertex) ||
                isVertexInRightPlace(patternVertex, rotatedVertex) ||
                isVertexInRightPlace(patternVertex, twiceRotatedVertex);
    }

    public boolean checkRotatedEdge(EdgeExt cubeEdge, int patternEdgeIndex) {
        EdgeExt rotatedEdge = interpretationCubeEdge.rotateEdgeColor(cubeEdge);
        EdgeExt patternEdge = interpretationPatternCubeEdge.getEdgeExtArrayList().get(patternEdgeIndex);
        return isEdgeInRightPlace(patternEdge, cubeEdge) ||
                isEdgeInRightPlace(patternEdge, rotatedEdge);
    }

    private boolean isVertexInRightPlace(VertexExt cubeVertex, VertexExt patternCubeVertex) {
        char[] colors = patternCubeVertex.getColor();
        return charArrayContainsChar(colors, cubeVertex.getColor()[0]) &&
                charArrayContainsChar(colors, cubeVertex.getColor()[1]) &&
                charArrayContainsChar(colors, cubeVertex.getColor()[2]);
    }

    private boolean isEdgeInRightPlace(EdgeExt cubeEdge, EdgeExt patternCubeEdge) {
        char[] colors = patternCubeEdge.getColor();
        return charArrayContainsChar(colors, cubeEdge.getColor()[0]) &&
                charArrayContainsChar(colors, cubeEdge.getColor()[1]);
    }

    private boolean charArrayContainsChar(char[] array, char character) {
        return new String(array).indexOf(character) != -1;
    }
}
