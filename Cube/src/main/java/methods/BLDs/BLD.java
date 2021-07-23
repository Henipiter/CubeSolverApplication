package methods.BLDs;


import cubes.Cube;
import cubes.Cube2x2;
import cubes.Cube3x3;
import methods.SolvingMethod;

public class BLD implements SolvingMethod {


    public String solve(Cube cube) {




        if(cube instanceof Cube2x2){
            BLD2X2 method = new BLD2X2();
            return method.solve(cube);
        }
        if(cube instanceof Cube3x3){
            BLD3X3 method = new BLD3X3();
            return method.solve(cube);
        }

        return "dziedzicza";




    }
}
