package interpretations;

import DTOs.Edge;
import DTOs.Vertex;
import cubes.Cube;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Interpretation3x3Vertices {

    private ArrayList<Vertex> vertexArrayList = new ArrayList<>();

    public Interpretation3x3Vertices() {
        saveEdgePositionOnWallsAndFields();
    }

    public void interpretEdges(Cube cube) {
        for (Vertex vertex : vertexArrayList) {
            char[] vertexColor = new char[3];
            for (int i = 0; i < 3; i++) {
                vertexColor[i] = cube.getCube()[vertex.getWall()[i]][vertex.getField()[i]];
            }
            vertex.setColor(vertexColor);
        }
    }

    private void saveEdgePositionOnWallsAndFields() {
        vertexArrayList.addAll(addSingleVertex(new int[]{0,2,5}, new int[]{0,0,0}));
        vertexArrayList.addAll(addSingleVertex(new int[]{0,3,5}, new int[]{2,0,2}));
        vertexArrayList.addAll(addSingleVertex(new int[]{0,3,4}, new int[]{7,2,2}));
        vertexArrayList.addAll(addSingleVertex(new int[]{0,2,4}, new int[]{5,2,0}));

        vertexArrayList.addAll(addSingleVertex(new int[]{1,2,5}, new int[]{0,5,5}));
        vertexArrayList.addAll(addSingleVertex(new int[]{1,3,5}, new int[]{2,5,7}));
        vertexArrayList.addAll(addSingleVertex(new int[]{1,3,4}, new int[]{7,7,7}));
        vertexArrayList.addAll(addSingleVertex(new int[]{1,2,4}, new int[]{5,7,5}));
    }

    private ArrayList<Vertex> addSingleVertex(int[] sides, int[] fields) {
        ArrayList<Vertex> vertexList = new ArrayList<>();
        Vertex vertex;
        vertex = Vertex.builder().wall(sides).field(fields).build();
        vertexList.add(vertex);
        return vertexList;
    }
}
