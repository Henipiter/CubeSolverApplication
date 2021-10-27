package methods.FMs;

import DTOs.Solution;
import cubes.Cube;
import cubes.Cube2x2;

import java.util.ArrayList;

public class FM2X2 implements FM {

    private Cube2x2 cube;

    public FM2X2(Cube cube) {
        this.cube = (Cube2x2) cube;
    }

    @Override
    public ArrayList<Solution> solve() {
        return null;
    }
}
