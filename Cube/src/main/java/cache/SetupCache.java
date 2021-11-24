package cache;

public class SetupCache {

    private static String[][] edgeSetup;
    private static String[][] vertexSetup;

    public static void load() {
        edgeSetup = new String[][]{
                {"BLANK", "M"}, {"BLANK", "BLANK"},
                {"BLANK", "M'"}, {"BLANK", "L2 D M'"},
                {"E L'", "ML"}, {"E L", "E2 L'"},
                {"E' L'", "E2 L"}, {"E' L", "L'"},
                {"M2", "M"}, {"D2 L2", "D' M'"},
                {"M2", "M'"}, {"L2", "D M'"}
        };

        vertexSetup = new String[][]{
                {"BLANK", "BLANK", "BLANK"}, {"R D'", "R2", "R' F"},
                {"F", "R'", "F2 D"}, {"F R'", "F2", "F' D"},
                {"D F'", "D2", "D' R"}, {"D2 F'", "R", "D'"},
                {"D' F'", "BLANK", "F D"}, {"F'", "D2 R", "D"},
        };
    }


    public static String[][] getEdgeSetup() {
        return edgeSetup;
    }

    public static void setEdgeSetup(String[][] edgeSetup) {
        SetupCache.edgeSetup = edgeSetup;
    }

    public static String[][] getVertexSetup() {
        return vertexSetup;
    }

    public static void setVertexSetup(String[][] vertexSetup) {
        SetupCache.vertexSetup = vertexSetup;
    }

}