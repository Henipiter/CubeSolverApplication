package methods.BLDs;

import cubes.Cube;
import methods.SolvingMethod;

public class BLDSolver implements SolvingMethod {
    public String solve(Cube cube) {
        BLDFactory bldFactory = new BLDFactory();
        BLD bld =  bldFactory.getBLD(cube);

        String solution=null;
        solution = bld.solve();

        return solution;


    }
}
