package interpretations;

import DTOs.Vertex;
import cubes.Cube;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Interpretation3x3Vertices {

    private final ArrayList<Vertex> vertexArrayList = new ArrayList<>();
    private char[] centerArray = new char[6];

    public Interpretation3x3Vertices() {
        saveVertexPositionOnWallsAndFields();
    }

    public void interpretVertices(Cube cube) {
        for (Vertex vertex : vertexArrayList) {
            char[] vertexColor = new char[3];
            for (int i = 0; i < 3; i++) {
                vertexColor[i] = cube.getCube()[vertex.getWall()[i]][vertex.getField()[i]];
            }
            vertex.setColor(vertexColor);
        }
        centerArray = Interpretation.getCenterArray(cube);
    }

    private void saveVertexPositionOnWallsAndFields() {
        vertexArrayList.add(addSingleVertex(new int[]{0, 2, 5}, new int[]{0, 0, 0}));
        vertexArrayList.add(addSingleVertex(new int[]{0, 3, 5}, new int[]{2, 0, 2}));
        vertexArrayList.add(addSingleVertex(new int[]{0, 3, 4}, new int[]{7, 2, 2}));
        vertexArrayList.add(addSingleVertex(new int[]{0, 2, 4}, new int[]{5, 2, 0}));

        vertexArrayList.add(addSingleVertex(new int[]{1, 2, 5}, new int[]{0, 5, 5}));
        vertexArrayList.add(addSingleVertex(new int[]{1, 3, 5}, new int[]{2, 5, 7}));
        vertexArrayList.add(addSingleVertex(new int[]{1, 3, 4}, new int[]{7, 7, 7}));
        vertexArrayList.add(addSingleVertex(new int[]{1, 2, 4}, new int[]{5, 7, 5}));
    }

    private Vertex addSingleVertex(int[] sides, int[] fields) {
        return Vertex.builder().wall(sides).field(fields).build();
    }

    public boolean isVertexHasGivenColor(Vertex vertex, char color) {
        return getFieldWithColor(vertex, color) != -1;
    }

    public int getFieldWithColor(Vertex vertex, char color) {
        for (int i = 0; i < 3; i++) {
            if (vertex.getColor()[i] == color) {
                return i;
            }
        }
        return -1;
    }

    public boolean isVertexBetweenItsCenters(int vertexIndex, Vertex vertex) {
        char firstColor = centerArray[2 + (vertexIndex + 3) % 4];
        char secondColor = centerArray[2 + vertexIndex % 4];
        return isVertexHasGivenColor(vertex, firstColor) &&
                isVertexHasGivenColor(vertex, secondColor);
    }

    public boolean isVertexWithGivenColorOnUpperSide(char color) {
        return getVertexWithGivenColorOnUpperSide(color) != -1;
    }

    public int getVertexWithGivenColorOnUpperSide(char color) {
        for (int i = 3; i >= 0; i--) {
            if (isVertexHasGivenColor(getVertexArrayList().get(i), color)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isFirstLayerComplete() {
        return !isVertexWithGivenColorOnUpperSide(getCenterArray()[1]) &&
                getIncorrectVertexInFirstLayer() == -1;
    }

    private boolean isVertexNotInRightPlaceOrHasIncorrectOrientation(int vertexIndex) {
        Vertex vertex = getVertexArrayList().get(vertexIndex);
        return !isVertexBetweenItsCenters(vertexIndex, vertex) ||
                vertexArrayList.get(vertexIndex).getColor()[0] != centerArray[vertexIndex/4];
    }

    public int getIncorrectVertexInFirstLayer() {
        for (int i = 7; i >= 4; i--) {
            if (isVertexNotInRightPlaceOrHasIncorrectOrientation(i)) {
                return i;
            }
        }
        return -1;
    }

    public int getNumOfVerticesInRightPlace() {
        int counter = 0;
        for (int i = 0; i < 4; i++) {
            if (isVertexBetweenItsCenters(i, vertexArrayList.get(i))) {
                counter++;
            }
        }
        return counter;
    }

    public boolean isAllVerticesInRightPlace() {
        return getNumOfVerticesInRightPlace() % 2 == 0 && getNumOfVerticesInRightPlace() > 0;
    }

    public boolean isVerticesInRightPosition() {
        return getNumOfVerticesInRightPlace() == 0 ||
                (getNumOfVerticesInRightPlace() == 1 && isVertexBetweenItsCenters(2, vertexArrayList.get(2)));
    }

    public boolean isVerticesInNotRightOrientation() {
        return vertexArrayList.get(2).getColor()[0] != centerArray[0];
    }

    public int getVertexInNotRightOrientation() {
        for (int i = 0; i < 4; i++) {
            if (vertexArrayList.get(i).getColor()[0] != centerArray[0]) {
                return i;
            }
        }
        return -1;
    }

    public boolean isAllVertexesInRightOrientation() {
        return getVertexInNotRightOrientation() == -1;
    }
}
