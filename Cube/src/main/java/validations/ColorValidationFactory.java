package validations;

import cubes.Cube;
import cubes.Cube2x2;
import cubes.Cube3x3;
import cubes.Cube4x4;

public class ColorValidationFactory {

    public static ColorValidator getValidator(Cube cube) {

        if (cube instanceof Cube2x2) {
            return new ColorValidation2x2((Cube2x2) cube);
        }
        if (cube instanceof Cube3x3) {
            return new ColorValidation3x3((Cube3x3) cube);
        }
        if (cube instanceof Cube4x4) {
            return new ColorValidation4x4((Cube4x4) cube);
        }
        return null;
    }

}
