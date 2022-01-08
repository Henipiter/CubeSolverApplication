package validations;

import cubes.Cube2x2;
import cubes.Cube3x3;

public class ColorValidation2x2 extends ColorValidator {

    public ColorValidation2x2(Cube2x2 cube2x2) {
        super(new Cube3x3(cube2x2));
        countColors(cube2x2);
        nonUniqueVertices = super.areVerticesUnique();
    }

    private void countColors(Cube2x2 cube2x2) {
        super.countColors(cube2x2.getCube(), 4);
        differentSumsOfColors = !checkCountsOfUniqueElements(4);
    }
}
