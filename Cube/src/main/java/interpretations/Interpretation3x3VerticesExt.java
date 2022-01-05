package interpretations;

import DTOs.Vertex;
import DTOs.VertexExt;
import cache.FileElementCache;
import cubes.Cube;
import lombok.Getter;
import parsers.ParseElementToElementExt;
import parsers.ParseFileElementToElement;

import java.util.ArrayList;
import java.util.Collections;

@Getter
public class Interpretation3x3VerticesExt extends Interpretation3x3Vertices {

    private boolean[] vertexCorrect = new boolean[8];
    private ArrayList<VertexExt> vertexExtArrayList = new ArrayList<>(Collections.nCopies(8, null));

    public Interpretation3x3VerticesExt() {
        super();
        vertexExtArrayList = ParseElementToElementExt.convertToVertexExtList(vertexExtArrayList, super.getVertexArrayList());
        initNamesAndSetups();
        saveVertexMarks();
        saveVertexSetups();
    }

    public VertexExt rotateVertexColor(VertexExt vertex) {
        char[] colors = vertex.getColor();
        return VertexExt.builder().color(new char[]{colors[1], colors[2], colors[0]}).build();
    }

    @Override
    public void interpretVertices(Cube cube) {
        super.interpretVertices(cube);
        ParseElementToElementExt.convertToVertexExtList(vertexExtArrayList, super.getVertexArrayList());
        setVertexCorrect();
    }

    public void setVertexCorrect() {
        for (int vertexIndex = 0; vertexIndex < 8; vertexIndex++) {
            vertexCorrect[vertexIndex] = !isVertexNotInRightPlaceOrHasIncorrectOrientation(vertexIndex);
        }
    }

    private void initNamesAndSetups() {
        for (VertexExt vertexExt : vertexExtArrayList) {
            vertexExt.setSetup(new ArrayList<>(Collections.nCopies(3, null)));
            vertexExt.setName(new ArrayList<>(Collections.nCopies(3, null)));
        }
    }

    private void saveVertexMarks() {
        ParseFileElementToElement.getVertexName(vertexExtArrayList, FileElementCache.getVertexMarkList());
    }

    private void saveVertexSetups() {
        ParseFileElementToElement.getVertexSetup(vertexExtArrayList, FileElementCache.getVertexSetupList());
    }

    private boolean isVertexNotInRightPlaceOrHasIncorrectOrientation(int vertexIndex) {
        Vertex vertex = getVertexArrayList().get(vertexIndex);
        return !isVertexBetweenItsCenters(vertexIndex, vertex) ||
                vertexExtArrayList.get(vertexIndex).getColor()[0] != super.getCenterArray()[vertexIndex / 4];
    }
}
