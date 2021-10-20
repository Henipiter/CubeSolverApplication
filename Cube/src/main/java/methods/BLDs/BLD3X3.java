package methods.BLDs;


import DTOs.Move;
import cubes.Cube;
import interpretations.Interpretation3x3VerticesExt;

import java.util.ArrayList;

public class BLD3X3 implements BLD {


    private static final String VERTEX_MARKS_FILE = "src/main/resources/vertexMarks.txt";
    private static final String VERTEX_SETUP_FILE = "src/main/resources/vertexSetup.txt";


    private Cube cube;
    private final Interpretation3x3VerticesExt interpretation3x3Vertices;


    public BLD3X3(Cube cube) {
        this.cube = cube;
        interpretation3x3Vertices = new Interpretation3x3VerticesExt();
    }

    @Override
    public String solve() {

        return null;
    }

    public ArrayList<Move> solveVertices() {
        int vert;

        while (!interpretation3x3Vertices.isAllVerticesCorrect()) {
            vert = interpretation3x3Vertices.searchIncorrectVertex();
            if (vert > 0);
                ///solveVertex(vert, 0, true);
        }


        return null;
    }


}
