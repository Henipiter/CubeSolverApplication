package methods.LBLs;

import cubes.Cube;
import methods.SolvingMethod;

public class LBLSolver implements SolvingMethod {

    public String solve(Cube cube) {
        LBLFactory lblFactory = new LBLFactory();
        LBL lbl =  lblFactory.getLBL(cube);


        String solution = lbl.solve('w');

        return solution;
    }
}
