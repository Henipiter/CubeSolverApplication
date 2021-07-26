package methods.FMs;

import cubes.Cube;
import methods.BLDs.BLD;
import methods.BLDs.BLDFactory;
import methods.SolvingMethod;

public class FMSolver implements SolvingMethod {

    public String solve(Cube cube) {
        FMFactory fmFactory = new FMFactory();
        FM fm =  fmFactory.getFM(cube);

        String solution=null;
        solution = fm.solve();
        return solution;

    }
}
