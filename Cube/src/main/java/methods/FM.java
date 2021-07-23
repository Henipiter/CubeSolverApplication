package methods;


import cubes.Cube;

public class FM  implements SolvingMethod {
    public String solve(Cube cube) {
        return cube.message();
    }
}