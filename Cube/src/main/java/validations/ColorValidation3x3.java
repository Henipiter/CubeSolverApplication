package validations;

import DTOs.Edge;
import cubes.Cube3x3;
import interpretations.Interpretation3x3Edges;

import java.util.ArrayList;

public class ColorValidation3x3 extends ColorValidator {

    private static final int EDGE_NUMBER = 12;
    private ArrayList<Edge> edgeArrayList;

    public ColorValidation3x3(Cube3x3 cube3x3) {
        super(cube3x3);
        countColors(cube3x3);
        Interpretation3x3Edges interpretation3x3Edges = new Interpretation3x3Edges();
        interpretation3x3Edges.interpretEdges(cube3x3);
        edgeArrayList = interpretation3x3Edges.getEdgeArrayList();

        nonUniqueVertices = super.areVerticesUnique();
        nonUniqueEdges = isEdgesHaveRepeatingColorsOrHasColorFromOppositeSide();
    }

    private void countColors(Cube3x3 cube3x3) {
        super.countColors(cube3x3.getCube(), 8);
        super.countColors(cube3x3.getCenter(), 6);
        differentSumsOfColors = !checkCountsOfUniqueElements(9);
    }

    private boolean isEdgesHaveRepeatingColorsOrHasColorFromOppositeSide() {
        return super.areEdgesUnique(EDGE_NUMBER, edgeArrayList);
    }
}
