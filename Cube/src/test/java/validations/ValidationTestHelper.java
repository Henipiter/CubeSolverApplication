package validations;

import cubes.Cube3x3;

public class ValidationTestHelper {

    private final Cube3x3 cube3x3;

    public ValidationTestHelper(Cube3x3 cube3x3) {
        this.cube3x3 = cube3x3;
    }

    public void rotateEdge() {
        switchTwoFields(0, 1, 5, 1);
    }

    public void switchTwoVertices() {
        switchTwoFields(0, 0, 0, 2);
        switchTwoFields(2, 0, 5, 2);
        switchTwoFields(5, 0, 3, 0);

    }

    public void switchTwoEdges() {
        switchTwoFields(0, 1, 0, 6);
        switchTwoFields(5, 1, 4, 1);

    }

    public void switchTwoFields(int side1, int field1, int side2, int field2) {
        char buffer = cube3x3.getCube()[side1][field1];
        cube3x3.getCube()[side1][field1] = cube3x3.getCube()[side2][field2];
        cube3x3.getCube()[side2][field2] = buffer;
    }

    public void switchCenters(int side1, int side2) {
        char centerColor = cube3x3.getCenter()[side1];
        cube3x3.getCenter()[side1] = cube3x3.getCenter()[side2];
        cube3x3.getCenter()[side2] = centerColor;
    }

    public void rotateVertex() {
        char buffer = cube3x3.getCube()[5][0];
        cube3x3.getCube()[5][0] = cube3x3.getCube()[2][0];
        cube3x3.getCube()[2][0] = cube3x3.getCube()[0][0];
        cube3x3.getCube()[0][0] = buffer;
    }

    public Cube3x3 getCube3x3() {
        return cube3x3;
    }

}
