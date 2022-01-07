package methods.BLDs;

import DTOs.Solution;
import cubes.Cube;
import cubes.Cube2x2;
import cubes.Cube3x3;

import java.util.ArrayList;

public class BLD2X2 implements BLD {

    private Cube2x2 cube;
    private boolean badColorOrder;

    public BLD2X2(Cube cube) {
        this.cube = (Cube2x2) cube;
    }

    @Override
    public ArrayList<Solution> solve(char upperColor, char frontColor) {
        Cube3x3 cube3x3 = new Cube3x3(cube);
        BLD3X3 bld3X3 = new BLD3X3(cube3x3);
        ArrayList<Solution> solution = new ArrayList<>();

        solution.add(bld3X3.solveOrientation(upperColor, frontColor));
        solution.addAll(bld3X3.solveAllVertices());

        badColorOrder = bld3X3.isBadColorOrder();
        return solution;
    }

    public boolean isBadColorOrder() {
        return badColorOrder;
    }
}
