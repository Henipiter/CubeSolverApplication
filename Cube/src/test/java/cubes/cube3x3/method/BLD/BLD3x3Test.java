package cubes.cube3x3.method.BLD;

import cubes.Cube;
import cubes.Cube3x3;
import methods.BLDs.BLD3X3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class BLD3x3Test {

    Cube cube;
    BLD3X3 bld3X3;

    @Test
    void solveAllVertices() {
        cube = new Cube3x3();
        cube.makeMoves("U");
        bld3X3 = new BLD3X3(cube);
        bld3X3.solveAllVertices();
    }

    @Test
    void solveAllVertices1() {
        String expectedSolution = "R O U M C B J";
        cube = new Cube3x3();
        cube.makeMoves("B' L D L2 U' R2 B2 D' R2 U' R2 D' B2 L' B' D U R' U2 B");
        bld3X3 = new BLD3X3(cube);
        ArrayList<String> solution = bld3X3.solveAllVertices();
        Assertions.assertEquals(Arrays.asList(expectedSolution.split(" ")), solution);
    }
}
