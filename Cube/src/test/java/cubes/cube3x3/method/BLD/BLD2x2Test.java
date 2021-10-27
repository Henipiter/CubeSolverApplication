package cubes.cube3x3.method.BLD;

import DTOs.SolutionBLD;
import cubes.Cube2x2;
import methods.BLDs.BLD2X2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BLD2x2Test {

    Cube2x2 cube2x2;
    BLD2X2 bld2X2;

    @BeforeEach
    void init() {
        cube2x2 = new Cube2x2();
    }

    @Test
    void solve(){

        String expectedSolution = "B C A";
        cube2x2.makeMoves("U");
        bld2X2 = new BLD2X2(cube2x2);
        ArrayList<SolutionBLD> solution = bld2X2.solve();
        Assertions.assertEquals(expectedSolution, SolutionBLD.getWholeMarks(solution));

    }
    @Test
    void solve1(){

        String expectedSolution = "N M Z U J B D";
        cube2x2.makeMoves("U2 R U2 R F' R2 U2 F2 D2");
        bld2X2 = new BLD2X2(cube2x2);
        ArrayList<SolutionBLD> solution = bld2X2.solve();
        Assertions.assertEquals(expectedSolution, SolutionBLD.getWholeMarks(solution));

    }
}
