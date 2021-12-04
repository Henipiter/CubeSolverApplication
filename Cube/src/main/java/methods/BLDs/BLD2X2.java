package methods.BLDs;

import DTOs.SolutionBLD;
import cubes.Cube;
import cubes.Cube2x2;
import cubes.Cube3x3;

import java.util.ArrayList;

public class BLD2X2 implements BLD {

    private Cube2x2 cube;

    public BLD2X2(Cube cube) {
        this.cube = (Cube2x2) cube;
    }

    @Override
    public ArrayList solve(char upperColor, char frontColor) {
        Cube3x3 cube3x3 = new Cube3x3(cube);
        BLD3X3 bld3X3 = new BLD3X3(cube3x3);
        ArrayList<SolutionBLD> solution = new ArrayList<>();

        solution.add(bld3X3.solveOrientation(upperColor, frontColor));
        bld3X3.refreshBeforeSolve(solution.get(0).getAlgorithm());
        solution.addAll(bld3X3.solveAllVertices());

        return solution;
    }
}
