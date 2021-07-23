package methods.BLDs;


import cubes.Cube;
import cubes.Cube2x2;

public class BLD2X2 extends BLD {

    private Cube2x2 cube;



    @Override
    public String solve(Cube cube){
        this.cube = (Cube2x2)cube;
        this.cube.x();
        return "a";
    }


}
