package validations;

import DTOs.Edge;
import DTOs.Vertex;
import cubes.Cube3x3;
import interpretations.Interpretation;
import interpretations.Interpretation3x3Vertices;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public class ColorValidator {

    protected boolean vertexColorCorrectness;
    protected boolean edgeColorCorrectness;
    protected char[] center;

    private final Interpretation3x3Vertices interpretation3x3Vertices = new Interpretation3x3Vertices();
    protected Map<String, Integer> sameColorEdgesCounter;

    protected ColorValidator(Cube3x3 cube3x3) {
        interpretation3x3Vertices.interpretVertices(cube3x3);
        sameColorEdgesCounter = new HashMap<>();
        center = cube3x3.getCenter();
    }

    protected String createKey(char[] edgeColors) {
        Arrays.sort(edgeColors);
        return String.valueOf(edgeColors);
    }

    protected boolean checkCountsOfEdgeColors(int rightEdgeOccurrence) {
        for (Integer counter : sameColorEdgesCounter.values()) {
            if (counter != rightEdgeOccurrence) {
                return false;
            }
        }
        return true;
    }

    protected void putOrReplaceValueInMap(String key) {
        if (!sameColorEdgesCounter.containsKey(key)) {
            sameColorEdgesCounter.put(key, 1);
        } else {
            sameColorEdgesCounter.replace(key, sameColorEdgesCounter.get(key) + 1);
        }
    }

    protected boolean isVerticesHaveRepeatingColorsOrHasColorFromOppositeSide() {
        for (int vertexIndex = 0; vertexIndex < 8; vertexIndex++) {
            if (checkWhetherVerticesHasColorsLikeInGivenVertex(vertexIndex) ||
                    isVertexHasColorsFromOppositeCenter(vertexIndex)) {
                return true;
            }
        }
        return false;
    }

    private boolean isVertexHasColorsFromOppositeCenter(int vertexIndex) {
        Interpretation interpretation = new Interpretation(center);
        char[] vertexColor = interpretation3x3Vertices.getVertexArrayList().get(vertexIndex).getColor();
        int[] colorPairIndex = new int[3];
        for (int i = 0; i < 3; i++) {
            colorPairIndex[i] = interpretation.getIndexOfColor(vertexColor[i]) / 2;
        }
        return !(Arrays.stream(colorPairIndex).anyMatch(elem -> elem == 0) &&
                Arrays.stream(colorPairIndex).anyMatch(elem -> elem == 1) &&
                Arrays.stream(colorPairIndex).anyMatch(elem -> elem == 2));
    }

    private boolean checkWhetherVerticesHasColorsLikeInGivenVertex(int vertexIndex) {
        for (int i = 0; i < 8; i++) {
            if (i != vertexIndex && isVertexContainsAllColors(i, vertexIndex)) {
                return true;
            }
        }
        return false;
    }

    private boolean isVertexContainsAllColors(int vertexIndex1, int vertexIndex2) {
        Vertex vertex1 = interpretation3x3Vertices.getVertexArrayList().get(vertexIndex1);
        Vertex vertex2 = interpretation3x3Vertices.getVertexArrayList().get(vertexIndex2);
        return interpretation3x3Vertices.isVertexHasGivenColor(vertex1, vertex2.getColor()[0]) &&
                interpretation3x3Vertices.isVertexHasGivenColor(vertex1, vertex2.getColor()[1]) &&
                interpretation3x3Vertices.isVertexHasGivenColor(vertex1, vertex2.getColor()[2]);
    }


    protected boolean isEdgesHaveColorFromOppositeSide(int numberOfEdges, ArrayList<Edge> edgeArrayList) {
        for (int edgeIndex = 0; edgeIndex < numberOfEdges; edgeIndex++) {
            if (isEdgeHasColorFromOppositeCenters(edgeIndex, edgeArrayList)) {
                return true;
            }
        }
        return false;
    }

    protected boolean isEdgesHaveRepeatingColors(int numberOfEdges, ArrayList<Edge> edgeArrayList) {
        for (int edgeIndex = 0; edgeIndex < numberOfEdges; edgeIndex++) {
            putOrReplaceValueInMap(createKey(edgeArrayList.get(edgeIndex).getColor()));
        }
        return !checkCountsOfEdgeColors(numberOfEdges / 12);
    }

    protected boolean isEdgeHasColorFromOppositeCenters(int edgeIndex, ArrayList<Edge> edgeArrayList) {
        Interpretation interpretation = new Interpretation(center);
        char[] edgeColor = edgeArrayList.get(edgeIndex).getColor();
        int[] colorPairIndex = new int[2];
        for (int i = 0; i < 2; i++) {
            colorPairIndex[i] = interpretation.getIndexOfColor(edgeColor[i]) / 2;
        }
        return colorPairIndex[0] == colorPairIndex[1];
    }
}
