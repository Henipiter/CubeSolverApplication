package methods.LBLs;

import cubes.*;
import methods.BLDs.BLD;
import methods.BLDs.BLD2X2;
import methods.BLDs.BLD3X3;
import methods.SolvingMethod;

public class LBLFactory implements SolvingMethod {

    private LBL getLBL(Cube cube){




        if(cube instanceof Cube2x2){
            return new LBL2X2(cube);
        }
        if(cube instanceof Cube3x3){
            return new LBL3X3(cube);
        }
        if(cube instanceof Cube4x4){
            return new LBL4X4(cube);
        }
        if(cube instanceof CubePyraminx){
            return new LBLPyraminx(cube);
        }
        return null;
    }



    public String solve(Cube cube) {

        LBL lbl =  getLBL(cube);
        String solution;

        solution = lbl.solve();

        return solution;


    }
}