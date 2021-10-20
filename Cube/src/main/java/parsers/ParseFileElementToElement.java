package parsers;

import DTOs.EdgeExt;
import DTOs.FileElement;
import DTOs.InspectMove;
import DTOs.VertexExt;

import java.util.List;

public class ParseFileElementToElement {


    public static List<VertexExt> getVertexName(List<VertexExt> vertexList, List<FileElement> fileElements) {
        for (FileElement element : fileElements) {
            vertexList.get(element.getElement()).getName().set(element.getField(), element.getData());
        }
        return vertexList;
    }

    public static List<VertexExt> getVertexSetup(List<VertexExt> vertexList, List<FileElement> fileElements) {
        for (FileElement element : fileElements) {
            vertexList.get(element.getElement()).getSetup().set(element.getField(), InspectMove.stringToMoveList(element.getData()));
        }
        return vertexList;
    }

    public static List<EdgeExt> getEdgeName(List<EdgeExt> edgeList, List<FileElement> fileElements) {
        for (FileElement element : fileElements) {
            edgeList.get(element.getElement()).getName().set(element.getField(), element.getData());
        }
        return edgeList;
    }

    public static List<EdgeExt> getEdgeAlgorithm(List<EdgeExt> edgeList, List<FileElement> fileElements) {
        for (FileElement element : fileElements) {
            edgeList.get(element.getElement()).getAlgorithm().set(element.getField(), element.getData());
        }
        return edgeList;
    }

    public static List<EdgeExt> getEdgeSetup(List<EdgeExt> edgeList, List<FileElement> fileElements) {
        for (FileElement element : fileElements) {
            edgeList.get(element.getElement()).getSetup().set(element.getField(), InspectMove.stringToMoveList(element.getData()));
        }
        return edgeList;
    }

}
