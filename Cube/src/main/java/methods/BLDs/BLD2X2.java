package methods.BLDs;

import DTOs.SolutionBLD;
import cubes.Cube;
import cubes.Cube3x3;
import parsers.Parse2x2To3x3;

import java.util.ArrayList;

public class BLD2X2 implements BLD {

    private Cube cube;

    public BLD2X2(Cube cube) {
        this.cube = cube;
    }

    @Override
    public ArrayList solve() {
        Parse2x2To3x3 parser = new Parse2x2To3x3(cube);
        Cube3x3 cube3x3 = parser.parseTo3x3();
        BLD3X3 bld3X3 = new BLD3X3(cube3x3);
        ArrayList<SolutionBLD> solution = bld3X3.solveAllVertices();

        return solution;
    }
}
