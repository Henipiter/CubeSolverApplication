package interpretations;

import DTOs.EdgeExt;
import DTOs.FileElement;
import cubes.Cube;
import lombok.Getter;
import parsers.ParseCodeToElement;
import parsers.ParseElementToElementExt;
import parsers.ParseFileElementToElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Interpretation3x3EdgesExt extends Interpretation3x3Edges {


    private static final String VERTEX_MARKS_FILE = "src/main/resources/vertexMarks.txt";
    private static final String VERTEX_SETUP_FILE = "src/main/resources/vertexSetup.txt";
    private static final String VERTEX_ALGORITHM_FILE = "src/main/resources/edgeReplaceAlg.txt";

    private boolean[] edgeCorrect = new boolean[12];
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
            edgeExt.setSetup(new ArrayList<>(Collections.nCopies(3, null)));
            edgeExt.setName(new ArrayList<>(Collections.nCopies(3, null)));
            edgeExt.setAlgorithm(new ArrayList<>(Collections.nCopies(3, null)));
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
            edgeCorrect[edgeIndex] = checkIfEdgeInIncorrectPlace(edgeIndex)==-1;
        }
    }

    public boolean isAllEdgesInCorrectPlace(){
        return getEdgeInIncorrectPlace()==-1;
    }


    private void saveEdgeMarks() {
        List<FileElement> elementList = ParseCodeToElement.getElementsFromFile(VERTEX_MARKS_FILE);
        ParseFileElementToElement.getEdgeName(edgeExtArrayList, elementList);
    }

    private void saveEdgeAlgorithm() {
        List<FileElement> elementList = ParseCodeToElement.getElementsFromFile(VERTEX_ALGORITHM_FILE);
        ParseFileElementToElement.getEdgeAlgorithm(edgeExtArrayList, elementList);
    }

    private void saveEdgeSetups() {
        List<FileElement> elementList = ParseCodeToElement.getElementsFromFile(VERTEX_SETUP_FILE);
        ParseFileElementToElement.getEdgeSetup(edgeExtArrayList, elementList);
    }

    private int getEdgeInIncorrectPlace() {
        for (int i = 0; i < 12; i++) {
            if(checkIfEdgeInIncorrectPlace(i)!=-1)
                return i;
        }
        return -1;
    }

    private int checkIfEdgeInIncorrectPlace(int edgeIndex) {
        if (edgeIndex >= 4 && edgeIndex <8) {
            return getIncorrectEdgeInSecondLayer();
        }
        return isEdgeOnBottomOrUpperSideIsInCorrectPlace(edgeIndex);
    }

    private int isEdgeOnBottomOrUpperSideIsInCorrectPlace(int edgeIndex){
        int[] sideOrder = new int[]{5, 3, 4, 2};
        if (edgeExtArrayList.get(edgeIndex).getColor()[0] == getCenterArray()[edgeIndex/8] &&
                edgeExtArrayList.get(edgeIndex).getColor()[1] == getCenterArray()[sideOrder[edgeIndex]]) {
            return -1;
        } else return edgeIndex;
    }

}