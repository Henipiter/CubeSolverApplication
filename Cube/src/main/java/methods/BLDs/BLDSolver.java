package methods.BLDs;

import DTOs.Solution;
import cache.CubeOrientationCache;
import cubes.Cube;
import methods.SolvingMethod;

import java.util.ArrayList;

public class BLDSolver implements SolvingMethod {
    public ArrayList<Solution> solve(Cube cube) {
        BLDFactory bldFactory = new BLDFactory();
        BLD bld = bldFactory.getBLD(cube);

        return bld.solve(CubeOrientationCache.upperColorBLD, CubeOrientationCache.frontColorBLD);
    }
}
