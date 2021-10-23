package methods.BLDs;


import DTOs.VertexExt;
import calculations.CalculateMoves;
import cubes.Cube;
import cubes.Cube3x3;
import interpretations.Interpretation3x3VerticesExt;

import java.util.ArrayList;
import java.util.Arrays;

public class BLD3X3 implements BLD {

    private boolean[] vertexCorrect = new boolean[8];
    private boolean parity;

    private Interpretation3x3VerticesExt interpretationCube = new Interpretation3x3VerticesExt();
    private Interpretation3x3VerticesExt interpretationPatternCube = new Interpretation3x3VerticesExt();

    private Cube cube;
    private Cube patternCube;

    public BLD3X3(Cube cube) {
        this.cube = cube;
        patternCube = new Cube3x3();
        rotatePatternCube();
        interpretationPatternCube.interpretVertices(patternCube);
    }

    @Override
    public String solve() {
        solveAllVertices();
        return null;
    }

    public ArrayList<String> solveAllVertices() {
        ArrayList<String> solution = new ArrayList<>();
        interpretationCube.interpretVertices(cube);
        parity = false;
        noteUnsolvedVertex();
        //TODO orient cube
        while (countSolvedVertex() < 8) {
            int vertexIndex = getUnresolvedVertex();
            if (vertexIndex != -1) {
                solution.addAll(solveSingleVertex(vertexIndex, 0, true));
            }
        }
        return solution;
    }

    private ArrayList<String> solveSingleVertex(int vertexIndex, int fieldIndex, boolean start) {
        ArrayList<String> solution = new ArrayList<>();
        VertexExt cubeVertex = interpretationCube.getVertexExtArrayList().get(vertexIndex);
        int destinationVertexIndex = searchRightPlaceForVertex(cubeVertex);
        int destinationFieldIndex = getRightField(destinationVertexIndex, cubeVertex.getColor()[fieldIndex]);
        if (!start) {
            vertexCorrect[vertexIndex] = true;
        }
        if (vertexIndex != 0) {
            solution.add(interpretationCube.getVertexExtArrayList().get(vertexIndex).getName().get(fieldIndex));
        }
        parity = !parity;
        if (!vertexCorrect[destinationVertexIndex]) {
            solution.addAll(solveSingleVertex(destinationVertexIndex, destinationFieldIndex, false));
        }
        return solution;
    }

    public int searchRightPlaceForVertex(VertexExt cubeVertex) {
        for (int i = 0; i < 8; i++) {
            if (checkRotatedVertex(cubeVertex, i)) {
                return i;
            }
        }
        return -1;
    }

    public int getRightField(int vertexIndex, char fieldColor) {
        for (int i = 0; i < 3; i++) {
            if (fieldColor == interpretationPatternCube.getVertexArrayList()
                    .get(vertexIndex).getColor()[i]) {
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

    private int countSolvedVertex() {
        int counter = 0;
        for (int i = 0; i < 8; i++) {
            if (vertexCorrect[i]) {
                counter++;
            }
        }
        return counter;
    }

    private void noteUnsolvedVertex() {
        for (int i = 0; i < 8; i++) {
            vertexCorrect[i] = Arrays.equals(interpretationCube.getVertexArrayList().get(i).getColor(),
                    interpretationPatternCube.getVertexArrayList().get(i).getColor());
        }
    }

    public boolean checkRotatedVertex(VertexExt cubeVertex, int patternVertexIndex) {
        VertexExt rotatedVertex = interpretationCube.rotateVertexColor(cubeVertex);
        VertexExt twiceRotatedVertex = interpretationCube.rotateVertexColor(rotatedVertex);
        VertexExt patternVertex = interpretationPatternCube.getVertexExtArrayList().get(patternVertexIndex);
        return isVertexInRightPlace(patternVertex, cubeVertex) ||
                isVertexInRightPlace(patternVertex, rotatedVertex) ||
                isVertexInRightPlace(patternVertex, twiceRotatedVertex);

    }

    private boolean isVertexInRightPlace(VertexExt cubeVertex, VertexExt patternCubeVertex) {
        char[] colors = patternCubeVertex.getColor();
        return charArrayContainsChar(colors, cubeVertex.getColor()[0]) &&
                charArrayContainsChar(colors, cubeVertex.getColor()[1]) &&
                charArrayContainsChar(colors, cubeVertex.getColor()[2]);
    }

    private boolean charArrayContainsChar(char[] array, char character) {
        return new String(array).indexOf(character) != -1;
    }
}
