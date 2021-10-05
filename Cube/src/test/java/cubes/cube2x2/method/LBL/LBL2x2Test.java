package cubes.cube2x2.method.LBL;

import DTOs.InspectMove;
import DTOs.Move;
import cubes.Cube;
import cubes.Cube2x2;
import methods.LBLs.LBL2X2;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

public class LBL2x2Test {

    private LBL2X2 lbl2x2;
    private Cube2x2 cube2x2;

    @BeforeEach
    void init(){
       cube2x2 = new Cube2x2();
    }

    @ParameterizedTest
    @CsvSource({"U F' R' U' F' R2 U R F2,7", "U2 F U' F' R U2 F2 R' U' D,6", "U,3"})
    void getVertexOfBegin(String scramble, int expected){
        //given
        cube2x2.makeMovesUsingString(scramble);
        lbl2x2 = new LBL2X2(cube2x2);
        //when
        int result = lbl2x2.getVertexOfBegin('w');
        //then
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "R U R' U' R' F R2 U' R' U' R U R' F' U",
            "R U R' U' R' F R2 U' R' U' R U R' F' U2",
            "R U R' U' R' F R2 U' R' U' R U R' F' U'",
            "R U R' U' R' F R2 U' R' U' R U R' F'",
            "R' U' R U' L R U2 R' U' R U2 L' U R2 U R",
            "R' U' R U' L R U2 R' U' R U2 L' U R2 U R U",
            "R' U' R U' L R U2 R' U' R U2 L' U R2 U R U2",
            "R' U' R U' L R U2 R' U' R U2 L' U R2 U R U'",

    })
    void solvePll(String scramble){
        //given
        cube2x2.makeMovesUsingString(scramble);
        lbl2x2 = new LBL2X2(cube2x2);
        //when
        ArrayList<Move> result = lbl2x2.solvePll();
        //then
        System.out.println(InspectMove.algorithmToString(result));
        Assertions.assertTrue(Cube.isSolved(cube2x2));
    }

    @ParameterizedTest
    @CsvSource({"BLANK,x2", "x,x", "x',x'", "z,z","z,z" })
    void rotateCubeToGetColorOnBottomSide(String scramble, Move expected){
        //given
        cube2x2.makeMovesUsingString(scramble);
        lbl2x2 = new LBL2X2(cube2x2);
        //when
        Move result = lbl2x2.rotateCubeToGetColorOnBottomSide('w');
        //then
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"4","5","6","7"})
    void setCentersByVertex(int vertex){
        //given
        cube2x2.makeMovesUsingString("x2");
        lbl2x2 = new LBL2X2(cube2x2);
        char[] expected = new char[]{'y','w','o','r','b','g'};
        //when
        lbl2x2.setCentersByVertex(vertex);
        //then
        Assert.assertArrayEquals(expected, cube2x2.getCenter());

    }


    @ParameterizedTest
    @CsvSource({"U2 F U' F' R U2 F2 R' U' D","U F' R' U' F' R2 U R F2",  "U"})
    void solve(String scramble){
        cube2x2.makeMovesUsingString(scramble);
        lbl2x2 = new LBL2X2(cube2x2);
        String alg = lbl2x2.solve('w');
        System.out.println(alg);
        Assertions.assertTrue(Cube.isSolved(cube2x2));
    }

}
