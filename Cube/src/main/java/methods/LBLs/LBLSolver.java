package methods.LBLs;

import cubes.Cube;
import methods.FMs.FM;
import methods.FMs.FMFactory;
import methods.SolvingMethod;

public class LBLSolver implements SolvingMethod {

    public String solve(Cube cube) {
        LBLFactory lblFactory = new LBLFactory();
        LBL lbl =  lblFactory.getLBL(cube);

        String solution=null;
        solution = lbl.solve();

        return solution;
    }
}
