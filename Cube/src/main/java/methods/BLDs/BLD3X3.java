package methods.BLDs;


import cubes.Cube;
import cubes.Cube3x3;

public class BLD3X3 extends BLD {

    private Cube3x3 cube;



    @Override
    public String solve(Cube cube){
        this.cube = (Cube3x3)cube;
        this.cube.y();
        return "a";
    }


}
