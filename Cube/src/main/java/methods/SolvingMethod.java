package methods;


import DTOs.Solution;
import cubes.Cube;

import java.util.ArrayList;

public interface SolvingMethod {
    ArrayList<Solution> solve(Cube cube);
}
