package methods.FMs;


import DTOs.Solution;
import cubes.Cube;
import cubes.Cube4x4;

import java.util.ArrayList;

public class FM4X4 implements FM {

    private Cube4x4 cube;

    public FM4X4(Cube cube) {
        this.cube = (Cube4x4) cube;
    }

    @Override
    public ArrayList<Solution> solve() {
        return null;
    }
}
