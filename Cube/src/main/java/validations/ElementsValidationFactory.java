package validations;


import cubes.Cube;
import cubes.Cube2x2;
import cubes.Cube3x3;
import cubes.Cube4x4;

public class ElementsValidationFactory {

    public static ElementsValidator getValidator(Cube cube) {

        if (cube instanceof Cube2x2) {
            return new ElementsValidator((Cube2x2) cube);
        }
        if (cube instanceof Cube3x3) {
            return new ElementsValidator((Cube3x3) cube);
        }
        if (cube instanceof Cube4x4) {
            return new ElementsValidator(new Cube2x2(new Cube3x3((Cube4x4) cube)));
        }
        return null;
    }

}
