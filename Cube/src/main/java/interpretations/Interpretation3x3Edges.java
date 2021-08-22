package interpretations;

import DTOs.Edge;
import cubes.Cube;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Interpretation3x3Edges {

    private ArrayList<Edge> edgeArrayList = new ArrayList<>();

    public Interpretation3x3Edges() {
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
        edgeArrayList.addAll(addSingleEdge(new int[]{0, 5}, new int[]{1, 1}));
        edgeArrayList.addAll(addSingleEdge(new int[]{0, 3}, new int[]{4, 1}));
        edgeArrayList.addAll(addSingleEdge(new int[]{0, 4}, new int[]{6, 1}));
        edgeArrayList.addAll(addSingleEdge(new int[]{0, 2}, new int[]{3, 1}));

        edgeArrayList.addAll(addSingleEdge(new int[]{2, 5}, new int[]{3, 3}));
        edgeArrayList.addAll(addSingleEdge(new int[]{3, 5}, new int[]{3, 4}));
        edgeArrayList.addAll(addSingleEdge(new int[]{3, 4}, new int[]{4, 4}));
        edgeArrayList.addAll(addSingleEdge(new int[]{2, 4}, new int[]{4, 3}));

        edgeArrayList.addAll(addSingleEdge(new int[]{1, 5}, new int[]{0, 6}));
        edgeArrayList.addAll(addSingleEdge(new int[]{1, 3}, new int[]{4, 6}));
        edgeArrayList.addAll(addSingleEdge(new int[]{1, 4}, new int[]{6, 6}));
        edgeArrayList.addAll(addSingleEdge(new int[]{1, 2}, new int[]{3, 6}));
    }

    private ArrayList<Edge> addSingleEdge(int[] sides, int[] fieldsFirstEdge) {
        ArrayList<Edge> edgeList = new ArrayList<>();
        Edge edge;
        edge = Edge.builder().wall(sides).field(fieldsFirstEdge).build();
        edgeList.add(edge);
        return edgeList;
    }
}
