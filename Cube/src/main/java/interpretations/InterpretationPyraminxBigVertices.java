package interpretations;

import DTOs.Vertex;
import cubes.Cube;

import java.util.ArrayList;

public class InterpretationPyraminxBigVertices {

    private ArrayList<Vertex> vertexArrayList;
    private char[] centerArray = new char[6];

    public InterpretationPyraminxBigVertices() {
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
        vertexArrayList.add(addSingleVertex(new int[]{0, 2, 3}, new int[]{2, 2, 2}));
        vertexArrayList.add(addSingleVertex(new int[]{1, 2, 3}, new int[]{2, 5, 7}));
        vertexArrayList.add(addSingleVertex(new int[]{0, 1, 3}, new int[]{7, 5, 5}));
        vertexArrayList.add(addSingleVertex(new int[]{0, 1, 2}, new int[]{5, 7, 7}));
    }

    private Vertex addSingleVertex(int[] sides, int[] fields) {

        return Vertex.builder().wall(sides).field(fields).build();

    }

}
