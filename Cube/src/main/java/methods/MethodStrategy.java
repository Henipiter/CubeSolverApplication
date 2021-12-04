package methods;

import DTOs.Solution;
import cubes.Cube;

import java.util.ArrayList;

public class MethodStrategy {

    private SolvingMethod solvingMethod;

    public MethodStrategy(SolvingMethod solvingMethod) {
        this.solvingMethod = solvingMethod;
    }

    public ArrayList<Solution> solve(Cube cube) {
        return solvingMethod.solve(cube);
    }
}
