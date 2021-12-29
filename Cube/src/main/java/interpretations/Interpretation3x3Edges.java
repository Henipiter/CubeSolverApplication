package interpretations;

import DTOs.Edge;
import cubes.Cube;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Interpretation3x3Edges {

    private final ArrayList<Edge> edgeArrayList = new ArrayList<>();
    private char[] centerArray = new char[6];
    private final int[] sideOrder = new int[]{0, 5, 3, 4, 2, 1};

    public Interpretation3x3Edges() {
        saveEdgePositionOnWallsAndFields();
    }

    public void interpretEdges(Cube cube) {
        for (Edge edge : edgeArrayList) {
            char colorOfFirstEdge = cube.getCube()[edge.getWall()[0]][edge.getField()[0]];
            char colorOfSecondEdge = cube.getCube()[edge.getWall()[1]][edge.getField()[1]];
            edge.setColor(new char[]{colorOfFirstEdge, colorOfSecondEdge});
        }
        centerArray = Interpretation.getCenterArray(cube);
    }

    private void saveEdgePositionOnWallsAndFields() {
        edgeArrayList.add(addSingleEdge(new int[]{0, 5}, new int[]{1, 1}));
        edgeArrayList.add(addSingleEdge(new int[]{0, 3}, new int[]{4, 1}));
        edgeArrayList.add(addSingleEdge(new int[]{0, 4}, new int[]{6, 1}));
        edgeArrayList.add(addSingleEdge(new int[]{0, 2}, new int[]{3, 1}));

        edgeArrayList.add(addSingleEdge(new int[]{2, 5}, new int[]{3, 3}));
        edgeArrayList.add(addSingleEdge(new int[]{3, 5}, new int[]{3, 4}));
        edgeArrayList.add(addSingleEdge(new int[]{3, 4}, new int[]{4, 4}));
        edgeArrayList.add(addSingleEdge(new int[]{2, 4}, new int[]{4, 3}));

        edgeArrayList.add(addSingleEdge(new int[]{1, 5}, new int[]{1, 6}));
        edgeArrayList.add(addSingleEdge(new int[]{1, 3}, new int[]{4, 6}));
        edgeArrayList.add(addSingleEdge(new int[]{1, 4}, new int[]{6, 6}));
        edgeArrayList.add(addSingleEdge(new int[]{1, 2}, new int[]{3, 6}));
    }

    private Edge addSingleEdge(int[] sides, int[] fieldsFirstEdge) {
        return Edge.builder().wall(sides).field(fieldsFirstEdge).build();
    }

    public int countFieldsWithGivenColor(char color, char[] colors) {
        int counter = 0;
        for (char c : colors) {
            if (c == color)
                counter++;
        }
        return counter;
    }

    public int getEdgeIndexBeforeRotation(int numOfRotations, int edgeIndexAfterRotation) {
        int edgeLayer = edgeIndexAfterRotation / 4;
        int edgeIndexOfLayer = edgeIndexAfterRotation % 4;
        int edgeIndexOfLayerBeforeRotate = (4 - numOfRotations + edgeIndexOfLayer) % 4;
        return 4 * edgeLayer + edgeIndexOfLayerBeforeRotate;
    }

    public int getEdgeIndexAfterJoinEdgeIntoSecondLayer(char secondCenterColor, char rightCenter) {
        if (secondCenterColor == rightCenter) {
            return 6;
        }
        return 7;
    }

    public int getEdgeIndexAfterJoinToCross(int sideEdgeNumber, int edgeField) {
        if (edgeField == 0) {
            return 10;
        }
        if (sideEdgeNumber == 6) {
            return 9;
        }
        return 11;
    }

    public char[] getColorOnCircumferenceFromGivenSide(int side) {
        return getColorsFromEdgesFromSide(side, true);
    }

    public char[] getColorOnInnerSideFromGivenSide(int side) {
        return getColorsFromEdgesFromSide(side, false);
    }

    public char[] getColorFromAllEdgesFromGivenSide(int side) {
        String colors = String.valueOf(getColorOnCircumferenceFromGivenSide(side)) +
                String.valueOf(getColorOnInnerSideFromGivenSide(side));
        return colors.toCharArray();
    }

    private char[] getColorsFromEdgesFromSide(int side, boolean isCircumference) {
        int[] fieldIndex = getIndexesOfFieldsOnEdgesOnGivenSide(side);
        char[] colors = new char[4];
        int addToIndex = 0;
        if (!isCircumference) {
            addToIndex = 1;
        }
        ArrayList<Edge> edges = getEdgesOnGivenSide(side);
        for (int i = 0; i < 4; i++) {
            colors[i] = edges.get(i).getColor()[(fieldIndex[i] + addToIndex) % 2];
        }
        return colors;
    }

    public boolean isCollisionWithDifferentSide(int side, char crossColor) {
        ArrayList<Edge> edges = getEdgesOnGivenSide(side);
        int[] circumferenceFields = getIndexesOfFieldsOnEdgesOnGivenSide(side);
        for (int i = 1; i < 4; i += 2) {
            if (edges.get(i).getColor()[(circumferenceFields[i] + 1) % 2] == crossColor) {
                return true;
            }
        }
        return false;
    }

    public int getSideWhichHaveCollisionWithGivenSide(int side, char crossColor) {
        ArrayList<Edge> edges = getEdgesOnGivenSide(side);
        int[] circumferenceFields = getIndexesOfFieldsOnEdgesOnGivenSide(side);
        for (int i = 1; i < 4; i += 2) {
            int innerFieldIndex = (circumferenceFields[i] + 1) % 2;
            if (edges.get(i).getColor()[innerFieldIndex] == crossColor) {
                return edges.get(i).getWall()[circumferenceFields[i]];
            }
        }
        return -1;
    }

    public int getRightCrossEdge(int side) {
        switch (side) {
            case 2:
                return 10;
            case 3:
                return 8;
            case 4:
                return 9;
            case 5:
                return 11;
        }
        return -1;
    }

    public boolean isGivenSideEdgeIndexHasGivenColor(Edge edge, char color) {
        return edge.getColor()[0] == color || edge.getColor()[1] == color;
    }

    public int getEdgeIndexFieldWithColor(Edge edge, char color) {
        if (edge.getColor()[0] == color) {
            return 0;
        } else if (edge.getColor()[1] == color) {
            return 1;
        }
        return -1;
    }

    public int getFreeSlotOnCross(char crossColor) {
        int[] crossEdges = getIndexesOfEdgesOnGivenSide(1);
        for (int i = 0; i < 4; i++) {
            if (getEdgeArrayList().get(crossEdges[i]).getColor()[0] != crossColor) {
                return crossEdges[i];
            }
        }
        return 0;
    }

    public int getIncorrectEdgeInSecondLayer() {
        if (!(getEdgeArrayList().get(7).getColor()[0] == centerArray[5] &&
                getEdgeArrayList().get(7).getColor()[1] == centerArray[4])) {
            return 7;
        }
        if (!(getEdgeArrayList().get(6).getColor()[0] == centerArray[3] &&
                getEdgeArrayList().get(6).getColor()[1] == centerArray[4])) {
            return 6;
        }
        if (!(getEdgeArrayList().get(5).getColor()[0] == centerArray[3] &&
                getEdgeArrayList().get(5).getColor()[1] == centerArray[2])) {
            return 5;
        }
        if (!(getEdgeArrayList().get(4).getColor()[0] == centerArray[5] &&
                getEdgeArrayList().get(4).getColor()[1] == centerArray[2])) {
            return 4;
        }
        return -1;
    }

    public boolean isSecondLayerComplete() {
        return getIncorrectEdgeInSecondLayer() == -1;
    }

    public boolean isSolvedCross() {
        for (int i = 8; i < 12; i++) {
            if (edgeArrayList.get(i).getColor()[0] != centerArray[1]) {
                return false;
            }
        }
        return true;
    }

    private int getEdgeIndexFromSideWithGivenColorOnSide(int side, char color, boolean circumference) {
        int[] edgeIndexes = getIndexesOfEdgesOnGivenSide(side);
        int[] circumferenceFields = getIndexesOfFieldsOnEdgesOnGivenSide(side);
        int field = 0;
        int[] order = new int[]{1, 3, 0};
        if (!circumference) {
            field = 1;
            order = new int[]{1, 3, 0, 2};
        }
        for (int i : order) {
            int index = (circumferenceFields[i] + field) % 2;
            if (edgeArrayList.get(edgeIndexes[i]).getColor()[index] == color)
                return edgeIndexes[i];
        }
        return -1;
    }

    public int getEdgeIndexFromSideWithGivenColorOnSide(int side, char color) {
        int onCircumference = getEdgeIndexFromSideWithGivenColorOnSide(side, color, true);
        if (onCircumference == -1) {
            return getEdgeIndexFromSideWithGivenColorOnSide(side, color, false);
        }
        return onCircumference;
    }

    public ArrayList<Edge> getEdgesOnGivenSide(int side) {
        ArrayList<Edge> edges = new ArrayList<>();
        int[] edgeIndex = getIndexesOfEdgesOnGivenSide(side);
        for (int i = 0; i < 4; i++) {
            edges.add(edgeArrayList.get(edgeIndex[i]));
        }
        return edges;
    }

    public int[] getIndexesOfEdgesOnGivenSide(int side) {
        switch (side) {
            case 1:
                return new int[]{10, 9, 11, 8};
            case 2:
                return new int[]{3, 7, 11, 4};
            case 3:
                return new int[]{1, 5, 9, 6};
            case 4:
                return new int[]{2, 6, 10, 7};
            case 5:
                return new int[]{0, 4, 8, 5};
        }
        return null;
    }

    public int[] getIndexesOfFieldsOnEdgesOnGivenSide(int side) {
        switch (side) {
            case 2:
            case 3:
                return new int[]{0, 1, 0, 1};
            case 4:
            case 5:
                return new int[]{0, 0, 0, 0};
        }
        return null;
    }

    public int countEdgesPairedWithCenters() {
        int counter = 0;
        for (int i = 0; i < 4; i++) {
            if (centerArray[i + 2] == edgeArrayList.get(i + 8).getColor()[1]) {
                counter++;
            }
        }
        return counter;
    }

    public boolean isCorrectCross() {
        return countEdgesPairedWithCenters() == 4;
    }

    public int getIndexOfNotPairedEdgeWithCenter() {

        for (int i = 3; i >= 0; i--) {
            if (centerArray[i + 2] != edgeArrayList.get(i + 8).getColor()[1] &&
                    centerArray[(i + 1) % 4 + 2] == edgeArrayList.get((i + 1) % 4 + 8).getColor()[1]
            ) {
                return i + 8;
            }
        }
        return -1;
    }

    public boolean isOppositeEdgeIsPairedWithCenter(int edge) {
        int buffer = edge / 8;
        int oppositeEdge = (edge % 8 + 2) % 4;
        return centerArray[oppositeEdge + 2] == edgeArrayList.get(oppositeEdge + buffer * 8).getColor()[1];
    }

    public int getSideIndexWithGivenEdgeIndex(int edgeIndex) {
        switch (edgeIndex % 8) {
            case 0:
                return 5;
            case 1:
                return 3;
            case 2:
                return 4;
            case 3:
                return 2;
        }
        return -1;
    }

    public int getSecondLayerEdgeIndexOnUpperSide() {
        int[] order = new int[]{2, 3, 1, 0};
        for (int i : order) {
            if (!isGivenSideEdgeIndexHasGivenColor(edgeArrayList.get(i), centerArray[0])) {
                return i;
            }
        }
        return -1;
    }

    public boolean isSecondLayerEdgeOnUpperSide() {
        return getSecondLayerEdgeIndexOnUpperSide() != -1;
    }

    public boolean isEdgeAboveRightCenters(int edgeIndex, Edge edge) {
        return edge.getColor()[1] == centerArray[2 + edgeIndex];
    }

    public int getNumOfCrossEdges() {
        int counter = 0;
        for (int i = 0; i < 4; i++) {
            if (edgeArrayList.get(i).getColor()[0] == centerArray[0]) {
                counter++;
            }
        }
        return counter;
    }

    public boolean isCrossOnUpperSideIsComplete() {
        return getNumOfCrossEdges() == 4;
    }

    public boolean isUpperCrossPositionCorrect() {
        return edgeArrayList.get(2).getColor()[0] != centerArray[0] &&
                (edgeArrayList.get(1).getColor()[0] != centerArray[0] ||
                        edgeArrayList.get(0).getColor()[0] != centerArray[0]);
    }

    public boolean isUpperIncorrectCrossPositionCorrect(char[] center) {
        Interpretation interpretation = new Interpretation(center);
        return edgeArrayList.get(0).getColor()[1] ==
                interpretation.getColorOfOppositeSide(edgeArrayList.get(2).getColor()[1]) ||
                (edgeArrayList.get(0).getColor()[1] == centerArray[2] &&
                        edgeArrayList.get(1).getColor()[1] == centerArray[3]);

    }

    public int getNumOfCorrectEdgesInUpperCross() {
        int counter = 0;
        for (int i = 0; i < 4; i++) {
            if (edgeArrayList.get(i).getColor()[1] == centerArray[2 + i]) {
                counter++;
            }
        }
        return counter;
    }

    public boolean isCorrectPositionBeforeOllParity() {
        return edgeArrayList.get(2).getColor()[0] != getCenterArray()[0];
    }

    public boolean isUpperCrossIsCorrect() {
        return getNumOfCorrectEdgesInUpperCross() == 4;
    }
}
