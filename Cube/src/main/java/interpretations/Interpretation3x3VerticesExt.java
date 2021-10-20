package interpretations;

import DTOs.FileElement;
import DTOs.Vertex;
import DTOs.VertexExt;
import cubes.Cube;
import parsers.ParseCodeToElement;
import parsers.ParseFileElementToElement;
import parsers.ParseVertexToVertexExt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Interpretation3x3VerticesExt extends Interpretation3x3Vertices {


    private static final String VERTEX_MARKS_FILE = "src/main/resources/vertexMarks.txt";
    private static final String VERTEX_SETUP_FILE = "src/main/resources/vertexSetup.txt";

    private boolean[] vertexCorrect = new boolean[8];
    private ArrayList<VertexExt> vertexArrayList = new ArrayList<>(Collections.nCopies(8, null));


    public Interpretation3x3VerticesExt() {
        super();
        vertexArrayList = ParseVertexToVertexExt.convertToVertexExtList(vertexArrayList, super.getVertexArrayList());
        initNamesAndSetups();

        saveVertexMarks();
        saveVertexSetups();
    }

    @Override
    public void interpretVertices(Cube cube) {
        super.interpretVertices(cube);
        ParseVertexToVertexExt.convertToVertexExtList(vertexArrayList, super.getVertexArrayList());
        setVertexCorrect();
    }

    public void setVertexCorrect() {
        for (int vertexIndex = 0; vertexIndex < 8; vertexIndex++) {
            vertexCorrect[vertexIndex] = !isVertexNotInRightPlaceOrHasIncorrectOrientation(vertexIndex);
        }
    }

    public boolean isAllVerticesCorrect() {
        return searchIncorrectVertex() == -1;
    }

    private void initNamesAndSetups() {
        for (VertexExt vertexExt : vertexArrayList) {
            vertexExt.setSetup(new ArrayList<>(Collections.nCopies(3, null)));
            vertexExt.setName(new ArrayList<>(Collections.nCopies(3, null)));
        }
    }

    public int searchIncorrectVertex() {
        for (int i = 0; i < 8; i++) {
            if (!vertexCorrect[i]) {
                return i;
            }
        }
        return -1;
    }

    private void saveVertexMarks() {
        List<FileElement> elementList = ParseCodeToElement.getElementsFromFile(VERTEX_MARKS_FILE);
        ParseFileElementToElement.getVertexName(vertexArrayList, elementList);
    }

    private void saveVertexSetups() {
        List<FileElement> elementList = ParseCodeToElement.getElementsFromFile(VERTEX_SETUP_FILE);
        ParseFileElementToElement.getVertexSetup(vertexArrayList, elementList);
    }

    private boolean isVertexNotInRightPlaceOrHasIncorrectOrientation(int vertexIndex) {
        Vertex vertex = getVertexArrayList().get(vertexIndex);
        return !isVertexBetweenItsCenters(vertexIndex, vertex) ||
                vertexArrayList.get(vertexIndex).getColor()[0] != super.getCenterArray()[vertexIndex / 4];
    }


}
