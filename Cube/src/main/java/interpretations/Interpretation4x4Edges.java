package interpretations;

import DTOs.Edge;
import cubes.Cube;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
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



    public boolean isChosenEdgeIsPaired(int edgeIndex){
            return Arrays.equals( edgeArrayList.get(edgeIndex).getColor(), edgeArrayList.get(edgeIndex+1).getColor());
    }

    public boolean isAllEdgesArePaired(){
        for (int i = 0; i < 24; i+=2) {
            if(!isChosenEdgeIsPaired(i)){
                return false;
            }
        }
        return true;
    }

    public int getUnpairedPairEdgeIndex() {
        int[] order = new int[]{3, 4, 11, 0, 1, 2, 10, 9, 8, 5, 6, 7};
        for (int i : order) {
            if (!isChosenEdgeIsPaired(i * 2)) {
                return i * 2;
            }
        }
        return 0;
    }

    private boolean isTheSameContent(char[] colors1, char[] colors2) {
        return (colors1[0] == colors2[0] && colors1[1] == colors2[1]) ||
                (colors1[0] == colors2[1] && colors1[1] == colors2[0]);
    }

    public boolean isGivenEdgesHaveTheSameContent(int edge1, int edge2){
        char[] colorsOnEdge1 = edgeArrayList.get(edge1).getColor();
        char[] colorsOnEdge2 = edgeArrayList.get(edge2).getColor();
        return isTheSameContent(colorsOnEdge1, colorsOnEdge2);
    }

    public boolean isGivenEdgeHasItsPairOnGivenEdgePair(int edge, int pairEdge){
        return isGivenEdgesHaveTheSameContent(edge, pairEdge*2) ||
                isGivenEdgesHaveTheSameContent(edge, pairEdge*2+1);
    }

    public int getEdgeIndexWithTheSameColorsLikeInGivenEdge(int sourceEdge){
        int[] order = new int[]{6, 2, 3, 4, 11, 0, 1, 10, 9, 8, 5, 7};
        for (int i : order) {
            for (int checkingEdge = 2 * i; checkingEdge <= 2 * i + 1; checkingEdge++) {
                if( isGivenEdgesHaveTheSameContent(sourceEdge, checkingEdge)){
                    return checkingEdge;
                }

            }
        }
        return 0;
        //TODO exception - cannot find
    }
}
