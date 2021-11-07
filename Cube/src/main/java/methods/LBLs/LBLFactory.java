package methods.LBLs;

import cubes.*;

public class LBLFactory {

    public LBL getLBL(Cube cube) {
        if (cube instanceof Cube2x2) {
            return new LBL2X2(cube);
        }
        if (cube instanceof Cube3x3) {
            return new LBL3X3(cube);
        }
        if (cube instanceof Cube4x4) {
            return new LBL4X4(cube);
        }
        if (cube instanceof CubePyraminx) {
            return new LBLPyraminx(cube);
        }
        return null;
    }
}
