package interpretations;

import DTOs.Vertex;
import cubes.Cube;

import java.util.ArrayList;

public class InterpretationPyraminxSmallVertices {

    private ArrayList<Vertex> vertexArrayList;
    private char[] centerArray = new char[6];

    public InterpretationPyraminxSmallVertices() {
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
        vertexArrayList.add(addSingleVertex(new int[]{0, 2, 3}, new int[]{0, 0, 0}));
        vertexArrayList.add(addSingleVertex(new int[]{1, 2, 3}, new int[]{0, 4, 8}));
        vertexArrayList.add(addSingleVertex(new int[]{0, 1, 3}, new int[]{8, 4, 4}));
        vertexArrayList.add(addSingleVertex(new int[]{0, 1, 2}, new int[]{4, 8, 8}));
    }

    private Vertex addSingleVertex(int[] sides, int[] fields) {

        return Vertex.builder().wall(sides).field(fields).build();

    }

}
