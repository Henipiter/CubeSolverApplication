package cache;

import DTOs.FileElement;
import parsers.ParseCodeToElement;

import java.util.List;

public class FileElementCache {

    private static final String EDGE_MARKS_FILE = "src/main/resources/edgeMarks.txt";
    private static final String EDGE_SETUP_FILE = "src/main/resources/edgeSetup.txt";
    private static final String EDGE_ALGORITHM_FILE = "src/main/resources/edgeReplaceAlg.txt";
    private static final String VERTEX_MARKS_FILE = "src/main/resources/vertexMarks.txt";
    private static final String VERTEX_SETUP_FILE = "src/main/resources/vertexSetup.txt";

    private static List<FileElement> edgeMarkList;
    private static List<FileElement> edgeSetupList;
    private static List<FileElement> edgeAlgorithmList;
    private static List<FileElement> vertexMarkList;
    private static List<FileElement> vertexSetupList;

    public static void loadAll() {
        loadConstant();
        loadVariable();
    }

    public static void loadVariable() {
        loadEdgeMarkList();
        loadVertexMarkList();
    }

    private static void loadConstant() {
        loadEdgeSetupList();
        loadEdgeAlgorithmList();
        loadVertexSetupList();
    }

    private static void loadEdgeMarkList() {
        edgeMarkList = ParseCodeToElement.getElementsFromFile(EDGE_MARKS_FILE);
    }

    private static void loadEdgeSetupList() {
        edgeSetupList = ParseCodeToElement.getElementsFromFile(EDGE_SETUP_FILE);
    }

    private static void loadEdgeAlgorithmList() {
        edgeAlgorithmList = ParseCodeToElement.getElementsFromFile(EDGE_ALGORITHM_FILE);
    }

    private static void loadVertexMarkList() {
        vertexMarkList = ParseCodeToElement.getElementsFromFile(VERTEX_MARKS_FILE);
    }

    private static void loadVertexSetupList() {
        vertexSetupList = ParseCodeToElement.getElementsFromFile(VERTEX_SETUP_FILE);
    }

    public static List<FileElement> getEdgeMarkList() {
        return edgeMarkList;
    }

    public static List<FileElement> getEdgeSetupList() {
        return edgeSetupList;
    }

    public static List<FileElement> getEdgeAlgorithmList() {
        return edgeAlgorithmList;
    }

    public static List<FileElement> getVertexMarkList() {
        return vertexMarkList;
    }

    public static List<FileElement> getVertexSetupList() {
        return vertexSetupList;
    }
}
