package cache;

public class ReplacerCache {

    public static String[][] getEdgeReplace() {
        return edgeReplace;
    }

    public static void setEdgeReplace(String[][] edgeReplace) {
        ReplacerCache.edgeReplace = edgeReplace;
    }

    private static String[][] edgeReplace;

    public static void load(){
        edgeReplace = new String[][]{
                {"7","J"},{"x","x"},
                {"J","7"},{"T","J"},
                {"T","T"},{"T","T"},
                {"T","T"},{"T","T"},
                {"J","7"},{"T","J"},
                {"7","J"},{"T","J"}
        };
    }
}
