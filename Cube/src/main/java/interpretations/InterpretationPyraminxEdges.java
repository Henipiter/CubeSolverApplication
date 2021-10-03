package interpretations;

import DTOs.Edge;
import cubes.Cube;

import java.util.ArrayList;

public class InterpretationPyraminxEdges {

    private ArrayList<Edge> edgeArrayList;
    private char[] centerArray = new char[6];

    public InterpretationPyraminxEdges() {
        saveEdgePositionOnWallsAndFields();
    }

    public void interpretEdges(Cube cube) {
        for (Edge edge : edgeArrayList) {
            char colorOfFirstEdge = cube.getCube()[edge.getWall()[0]][edge.getField()[0]];
            char colorOfSecondEdge = cube.getCube()[edge.getWall()[1]][edge.getField()[1]];
            edge.setColor(new char[]{colorOfFirstEdge, colorOfSecondEdge});
        }
        centerArray = Interpretation.getCenterArray(cube);
    }

    private void saveEdgePositionOnWallsAndFields() {
        edgeArrayList.add(addSingleEdge(new int[]{2,3}, new int[]{1, 3}));
        edgeArrayList.add(addSingleEdge(new int[]{0,3}, new int[]{1, }));
        edgeArrayList.add(addSingleEdge(new int[]{0,2}, new int[]{6, 1}));
        edgeArrayList.add(addSingleEdge(new int[]{1,3}, new int[]{3, 1}));
        edgeArrayList.add(addSingleEdge(new int[]{1,0}, new int[]{6, 1}));
        edgeArrayList.add(addSingleEdge(new int[]{1, 2}, new int[]{3, 1}));

    }

    private Edge addSingleEdge(int[] sides, int[] fieldsFirstEdge) {
        return Edge.builder().wall(sides).field(fieldsFirstEdge).build();
    }

}
