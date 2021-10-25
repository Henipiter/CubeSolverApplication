package methods.FMs;

import DTOs.Solution;
import cubes.Cube;
import methods.SolvingMethod;

import java.util.ArrayList;

public class FMSolver implements SolvingMethod {

    public ArrayList<Solution> solve(Cube cube) {
        FMFactory fmFactory = new FMFactory();
        FM fm = fmFactory.getFM(cube);

        ArrayList<Solution> solution = fm.solve();
        return solution;
    }
}
