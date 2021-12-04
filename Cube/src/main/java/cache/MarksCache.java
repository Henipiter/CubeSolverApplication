package cache;

public class MarksCache {

    private static char[][] edgeMarks;
    private static char[][] vertexMarks;

    public static void load() {
        edgeMarks = new char[][]{
                {'A', 'F'}, {'x', 'x'}, {'C', 'D'}, {'B', 'E'},
                {'J', 'K'}, {'M', 'L'}, {'N', 'G'}, {'I', 'H'},
                {'T', 'R'}, {'Z', 'S'}, {'W', 'O'}, {'U', 'P'}};

        vertexMarks = new char[][]{
                {'x', 'x', 'x'}, {'A', 'O', 'N'}, {'C', 'P', 'D'}, {'B', 'I', 'H'},
                {'U', 'K', 'L'}, {'T', 'S', 'M'}, {'Z', 'R', 'F'}, {'W', 'J', 'G'},
        };

    }

    public static char[][] getEdgeMarks() {
        return edgeMarks;
    }

    public static void setEdgeMarks(char[][] edgeMarks) {
        MarksCache.edgeMarks = edgeMarks;
    }

    public static char[][] getVertexMarks() {
        return vertexMarks;
    }

    public static void setVertexMarks(char[][] vertexMarks) {
        MarksCache.vertexMarks = vertexMarks;
    }
}