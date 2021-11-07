package methods.BLDs;


import cubes.Cube;
import cubes.Cube2x2;
import cubes.Cube3x3;

public class BLDFactory {

    public BLD getBLD(Cube cube) {
        if (cube instanceof Cube2x2) {
            return new BLD2X2(cube);
        }
        if (cube instanceof Cube3x3) {
            return new BLD3X3(cube);
        }
        return null;
    }
}
