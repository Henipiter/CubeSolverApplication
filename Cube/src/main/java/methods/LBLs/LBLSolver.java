package methods.LBLs;

import DTOs.CubeOrientationCache;
import DTOs.Solution;
import cubes.Cube;
import methods.SolvingMethod;

import java.util.ArrayList;

public class LBLSolver implements SolvingMethod {

    public ArrayList<Solution> solve(Cube cube) {
        LBLFactory lblFactory = new LBLFactory();
        LBL lbl = lblFactory.getLBL(cube);

        return lbl.solve(CubeOrientationCache.crossColorLBL);
    }
}
