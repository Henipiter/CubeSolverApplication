package methods.BLDs;


import cubes.Cube;
import cubes.Cube2x2;
import cubes.Cube3x3;
import methods.SolvingMethod;

public class BLDFactory implements SolvingMethod {

    private BLD getBLD(Cube cube){

        if(cube instanceof Cube2x2){
            return new BLD2X2(cube);
        }
        if(cube instanceof Cube3x3){
            return new BLD3X3(cube);
        }
        return null;
    }


    public String solve(Cube cube) {

        BLD bld =  getBLD(cube);
        String solution;

        solution = bld.solve();

        return solution;


    }
}
