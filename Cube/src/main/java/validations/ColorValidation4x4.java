package validations;

import DTOs.Edge;
import cubes.Cube3x3;
import cubes.Cube4x4;
import interpretations.Interpretation4x4Edges;

import java.util.ArrayList;

public class ColorValidation4x4 extends ColorValidator {

    private static final int EDGE_NUMBER = 24;
    private ArrayList<Edge> edgeArrayList;

    public ColorValidation4x4(Cube4x4 cube4x4) {
        super(new Cube3x3(cube4x4));
        Interpretation4x4Edges interpretation4x4Edges = new Interpretation4x4Edges();
        interpretation4x4Edges.interpretEdges(cube4x4);
        edgeArrayList = interpretation4x4Edges.getEdgeArrayList();

        vertexColorCorrectness = !super.isVerticesHaveRepeatingColorsOrHasColorFromOppositeSide();
        edgeColorCorrectness = !isEdgesHaveRepeatingColorsOrHasColorFromOppositeSide();
    }

    private boolean isEdgesHaveRepeatingColorsOrHasColorFromOppositeSide() {
        return super.isEdgesHaveRepeatingColors(EDGE_NUMBER, edgeArrayList) ||
                super.isEdgesHaveColorFromOppositeSide(EDGE_NUMBER, edgeArrayList);
    }
}
