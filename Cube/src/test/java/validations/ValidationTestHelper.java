package validations;

public class ValidationTestHelper {

    private final char[][] cube;

    public ValidationTestHelper(char[][] cube) {
        this.cube = cube;
    }

    public void rotateEdge3x3() {
        switchTwoFields(0, 1, 5, 1);
    }

    public void rotateEdge4x4() {
        switchTwoFields(0, 1, 5, 1);
        switchTwoFields(0, 2, 5, 2);
    }

    public void switchTwoVertices3x3() {
        switchTwoFields(0, 0, 0, 2);
        switchTwoFields(2, 0, 5, 2);
        switchTwoFields(5, 0, 3, 0);
    }

    public void switchTwoVertices4x4() {
        switchTwoFields(0, 0, 0, 3);
        switchTwoFields(2, 0, 5, 3);
        switchTwoFields(5, 0, 3, 0);
    }

    public void switchTwoEdges3x3() {
        switchTwoFields(0, 1, 0, 6);
        switchTwoFields(5, 1, 4, 1);
    }

    public void switchTwoFields(int side1, int field1, int side2, int field2) {
        char buffer = cube[side1][field1];
        cube[side1][field1] = cube[side2][field2];
        cube[side2][field2] = buffer;
    }

    public void switchCenters(char[] center, int side1, int side2) {
        char centerColor = center[side1];
        center[side1] = center[side2];
        center[side2] = centerColor;
    }

    public void rotateVertex() {
        char buffer = cube[5][0];
        cube[5][0] = cube[2][0];
        cube[2][0] = cube[0][0];
        cube[0][0] = buffer;
    }
}
