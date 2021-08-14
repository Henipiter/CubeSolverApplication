package interpretations;

import DTOs.Edge;
import cubes.Cube;

import java.util.ArrayList;

public class Interpretation4x4Edges {

    private ArrayList<Edge> edgeArrayList = new ArrayList<>();

    public Interpretation4x4Edges() {
        saveEdgePositionOnWallsAndFields();
    }

    public void interpretEdges(Cube cube) {
        for (Edge edge : edgeArrayList) {
            char colorOfFirstEdge = cube.getCube()[edge.getWall()[0]][edge.getField()[0]];
            char colorOfSecondEdge = cube.getCube()[edge.getWall()[1]][edge.getField()[1]];
            edge.setColor(new char[]{colorOfFirstEdge, colorOfSecondEdge});
        }
    }

    private void saveEdgePositionOnWallsAndFields() {
        edgeArrayList.addAll(addPairOfEdges(new int[]{0, 5}, new int[]{1, 1}, new int[]{2, 2}));
        edgeArrayList.addAll(addPairOfEdges(new int[]{0, 3}, new int[]{7, 1}, new int[]{11, 2}));
        edgeArrayList.addAll(addPairOfEdges(new int[]{0, 4}, new int[]{14, 2}, new int[]{13, 1}));
        edgeArrayList.addAll(addPairOfEdges(new int[]{0, 2}, new int[]{8, 2}, new int[]{4, 1}));

        edgeArrayList.addAll(addPairOfEdges(new int[]{2, 5}, new int[]{4, 4}, new int[]{8, 8}));
        edgeArrayList.addAll(addPairOfEdges(new int[]{3, 5}, new int[]{4, 7}, new int[]{8, 11}));
        edgeArrayList.addAll(addPairOfEdges(new int[]{3, 4}, new int[]{7, 7}, new int[]{11, 11}));
        edgeArrayList.addAll(addPairOfEdges(new int[]{2, 4}, new int[]{7, 4}, new int[]{11, 8}));

        edgeArrayList.addAll(addPairOfEdges(new int[]{1, 5}, new int[]{1, 13}, new int[]{2, 14}));
        edgeArrayList.addAll(addPairOfEdges(new int[]{1, 3}, new int[]{7, 13}, new int[]{11, 14}));
        edgeArrayList.addAll(addPairOfEdges(new int[]{1, 4}, new int[]{14, 14}, new int[]{13, 13}));
        edgeArrayList.addAll(addPairOfEdges(new int[]{1, 2}, new int[]{8, 14}, new int[]{4, 13}));
    }

    private ArrayList<Edge> addPairOfEdges(int[] sides, int[] fieldsFirstEdge, int[] fieldsSecondEdge) {
        ArrayList<Edge> edgeList = new ArrayList<>();
        Edge edge;
        edge = Edge.builder().wall(sides).field(fieldsFirstEdge).build();
        edgeList.add(edge);
        edge = Edge.builder().wall(sides).field(fieldsSecondEdge).build();
        edgeList.add(edge);
        return edgeList;
    }

    public ArrayList<Edge> getEdgeArrayList() {
        return edgeArrayList;
    }
}
