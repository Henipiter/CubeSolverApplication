package methods.FMs;


import cubes.*;
import methods.BLDs.BLD;
import methods.BLDs.BLD2X2;
import methods.BLDs.BLD3X3;
import methods.SolvingMethod;

public class FMFactory{

    public FM getFM(Cube cube){

        if(cube instanceof Cube2x2){
            return new FM2X2(cube);
        }
        if(cube instanceof Cube3x3){
            return new FM3X3(cube);
        }
        if(cube instanceof Cube4x4){
            return new FM3X3(cube);
        }
        if(cube instanceof CubePyraminx){
            return new FM3X3(cube);
        }
        return null;
    }

}