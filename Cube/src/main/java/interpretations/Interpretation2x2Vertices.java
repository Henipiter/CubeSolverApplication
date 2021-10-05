package interpretations;

import DTOs.Vertex;
import cubes.Cube;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Interpretation2x2Vertices {

    private final ArrayList<Vertex> vertexArrayList = new ArrayList<>();
    private char[] centerArray = new char[6];

    public Interpretation2x2Vertices() {
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
    }

    private void saveVertexPositionOnWallsAndFields() {
        vertexArrayList.add(addSingleVertex(new int[]{0, 2, 5}, new int[]{0, 0, 0}));
        vertexArrayList.add(addSingleVertex(new int[]{0, 3, 5}, new int[]{1, 0, 1}));
        vertexArrayList.add(addSingleVertex(new int[]{0, 3, 4}, new int[]{3, 1, 1}));
        vertexArrayList.add(addSingleVertex(new int[]{0, 2, 4}, new int[]{2, 1, 0}));

        vertexArrayList.add(addSingleVertex(new int[]{1, 2, 5}, new int[]{0, 2, 2}));
        vertexArrayList.add(addSingleVertex(new int[]{1, 3, 5}, new int[]{1, 2, 3}));
        vertexArrayList.add(addSingleVertex(new int[]{1, 3, 4}, new int[]{3, 3, 3}));
        vertexArrayList.add(addSingleVertex(new int[]{1, 2, 4}, new int[]{2, 3, 2}));
    }

    private Vertex addSingleVertex(int[] sides, int[] fields) {
        return Vertex.builder().wall(sides).field(fields).build();
    }

    public int getIndexVertexFromBottomHasColor(char color) {
        for (int i = 4; i < 8; i++) {
            if (vertexArrayList.get(i).getColor()[0] == color) {
                return i;
            }
        }
        return -1;
    }


    public boolean isBottomSideHasColor(char color) {
        return getIndexVertexFromBottomHasColor(color) != -1;
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

    public boolean isVerticesNotPairedAreAcross() {
        return vertexArrayList.get(0).getColor()[2] == Interpretation.getColorOfOppositeSide(vertexArrayList.get(1).getColor()[2]);
    }

    public boolean isVerticesNotPairedInLine() {
        return vertexArrayList.get(0).getColor()[2] == vertexArrayList.get(1).getColor()[2];
    }

    public boolean isVerticesPermuted(){
        return vertexArrayList.get(0).getColor()[2] == vertexArrayList.get(1).getColor()[2] &&
                vertexArrayList.get(2).getColor()[2] == vertexArrayList.get(3).getColor()[2];
    }

    public boolean isVerticesPairedInLine() {
        return vertexArrayList.get(0).getColor()[2] == vertexArrayList.get(1).getColor()[2] ||
                vertexArrayList.get(2).getColor()[2] == vertexArrayList.get(3).getColor()[2] ||
                vertexArrayList.get(0).getColor()[1] == vertexArrayList.get(3).getColor()[1] ||
                vertexArrayList.get(1).getColor()[1] == vertexArrayList.get(2).getColor()[1];
    }

    public boolean isVerticesPairedInRightPosition() {
        return isVerticesNotPairedInLine();
    }

    public boolean isVerticesAcrossInRightPosition() {
        return isVerticesNotPairedAreAcross();
    }

    public boolean isUpperLayerFitToBottomLayer() {
        return vertexArrayList.get(1).getColor()[2] == vertexArrayList.get(4).getColor()[2];
    }


    public boolean isVerticesInRightPosition() {
        return isUpperLayerFitToBottomLayer();
    }
}
