package methods.FMs;


import DTOs.Solution;
import cubes.Cube;

import java.util.ArrayList;

public class FMPyraminx implements FM {

    private Cube cube;

    public FMPyraminx(Cube cube) {
        this.cube = cube;
    }

    @Override
    public ArrayList<Solution> solve() {
        return null;
    }
}
