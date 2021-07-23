package methods;

import cubes.Cube;

public class LBL  implements SolvingMethod {

    public String solve(Cube cube) {
        return cube.message();
    }
}