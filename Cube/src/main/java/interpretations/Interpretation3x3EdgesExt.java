package interpretations;

import DTOs.Edge;
import DTOs.EdgeExt;
import cache.FileElementCache;
import cubes.Cube;
import lombok.Getter;
import parsers.ParseElementToElementExt;
import parsers.ParseFileElementToElement;

import java.util.ArrayList;
import java.util.Collections;

@Getter
public class Interpretation3x3EdgesExt extends Interpretation3x3Edges {

    private final boolean[] edgeCorrect = new boolean[12];
    private ArrayList<EdgeExt> edgeExtArrayList = new ArrayList<>(Collections.nCopies(12, null));

    public Interpretation3x3EdgesExt() {
        super();
        edgeExtArrayList = ParseElementToElementExt.convertToEdgeExtList(edgeExtArrayList, super.getEdgeArrayList());
        initNamesAndSetups();
        saveEdgeMarks();
        saveEdgeSetups();
        saveEdgeAlgorithm();
    }

    private void initNamesAndSetups() {
        for (EdgeExt edgeExt : edgeExtArrayList) {
            edgeExt.setSetup(new ArrayList<>(Collections.nCopies(2, null)));
            edgeExt.setName(new ArrayList<>(Collections.nCopies(2, null)));
            edgeExt.setAlgorithm(new ArrayList<>(Collections.nCopies(2, null)));
        }
    }

    @Override
    public void interpretEdges(Cube cube) {
        super.interpretEdges(cube);
        edgeExtArrayList = ParseElementToElementExt.convertToEdgeExtList(edgeExtArrayList, super.getEdgeArrayList());
        setEdgeCorrect();
    }

    public void setEdgeCorrect() {
        for (int edgeIndex = 0; edgeIndex < 8; edgeIndex++) {
            edgeCorrect[edgeIndex] = checkIfEdgeInIncorrectPlace(edgeIndex) == -1;
        }
    }

    public EdgeExt rotateEdgeColor(Edge edge) {
        char[] colors = edge.getColor();
        return EdgeExt.builder().color(new char[]{colors[1], colors[0]}).build();
    }

    private void saveEdgeMarks() {
        ParseFileElementToElement.getEdgeName(edgeExtArrayList, FileElementCache.getEdgeMarkList());
    }

    private void saveEdgeAlgorithm() {
        ParseFileElementToElement.getEdgeAlgorithm(edgeExtArrayList, FileElementCache.getEdgeAlgorithmList());
    }

    private void saveEdgeSetups() {
        ParseFileElementToElement.getEdgeSetup(edgeExtArrayList, FileElementCache.getEdgeSetupList());
    }

    private int checkIfEdgeInIncorrectPlace(int edgeIndex) {
        if (edgeIndex >= 4 && edgeIndex < 8) {
            return getIncorrectEdgeInSecondLayer();
        }
        return isEdgeOnBottomOrUpperSideIsInCorrectPlace(edgeIndex);
    }

    private int isEdgeOnBottomOrUpperSideIsInCorrectPlace(int edgeIndex) {
        int[] sideOrder = new int[]{5, 3, 4, 2};
        if (edgeExtArrayList.get(edgeIndex).getColor()[0] == getCenterArray()[edgeIndex / 8] &&
                edgeExtArrayList.get(edgeIndex).getColor()[1] == getCenterArray()[sideOrder[edgeIndex]]) {
            return -1;
        } else {
            return edgeIndex;
        }
    }
}
