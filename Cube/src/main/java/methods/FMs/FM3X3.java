package methods.FMs;


import DTOs.Solution;
import cubes.Cube;
import cubes.Cube3x3;

import java.util.ArrayList;

public class FM3X3 implements FM {

    private Cube3x3 cube;

    public FM3X3(Cube cube) {
        this.cube = (Cube3x3) cube;
    }

    @Override
    public ArrayList<Solution> solve() {
        return null;
    }
}
