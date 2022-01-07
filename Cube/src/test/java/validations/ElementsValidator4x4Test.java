package validations;

import DTOs.Algorithm;
import cache.FileElementCache;
import cubes.Cube2x2;
import cubes.Cube3x3;
import cubes.Cube4x4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ElementsValidator4x4Test {

    private static final String ALG1 = "U2 R2 B2 U F2 L2 F2 D' F2 L2 R' B2 U2 R' U L' B' F' D2";

    private ElementsValidator elementsValidator;
    private Cube4x4 cube4x4;

    @BeforeEach
    void init() {
        FileElementCache.loadAll();
        Algorithm.loadPermutations();
        cube4x4 = new Cube4x4();
    }

    @Test
    void testWrongVertexColor1() {
        //given
        cube4x4.makeMoves(ALG1);
        switchTwoFields(4, 0, 2, 3);
        //when
        elementsValidator = new ElementsValidator(new Cube2x2(new Cube3x3(cube4x4)));
        //then
        Assertions.assertTrue(elementsValidator.isWrongVertexColorOrder());
    }

    @Test
    void testWrongVertexColor2() {
        //given
        cube4x4.makeMoves("z");
        switchTwoFields(4, 0, 2, 3);
        //when
        elementsValidator = new ElementsValidator(new Cube2x2(new Cube3x3(cube4x4)));
        //then
        Assertions.assertTrue(elementsValidator.isWrongVertexColorOrder());
    }

    @Test
    void correctCubeTest() {
        //given
        cube4x4.makeMoves("z");
        //when
        elementsValidator = new ElementsValidator(new Cube2x2(new Cube3x3(cube4x4)));
        //then
        Assertions.assertFalse(elementsValidator.isOllParity());
        Assertions.assertFalse(elementsValidator.isPllParity());
        Assertions.assertFalse(elementsValidator.isRollingPop());
        Assertions.assertFalse(elementsValidator.isWrongCenterOrder());
        Assertions.assertFalse(elementsValidator.isWrongVertexColorOrder());
    }

    @Test
    void rollingPop2Times() {
        //given
        rotateVertex();
        cube4x4.makeMoves(ALG1);
        rotateVertex();
        //when
        elementsValidator = new ElementsValidator(new Cube2x2(new Cube3x3(cube4x4)));
        //then
        Assertions.assertTrue(elementsValidator.isRollingPop());
    }

    @Test
    void rollingPopBefore() {
        //given
        rotateVertex();
        cube4x4.makeMoves(ALG1);
        //when
        elementsValidator = new ElementsValidator(new Cube2x2(new Cube3x3(cube4x4)));
        //then
        Assertions.assertTrue(elementsValidator.isRollingPop());
    }

    @Test
    void rollingPopAfter() {
        //given
        cube4x4.makeMoves(ALG1);
        rotateVertex();
        //when
        elementsValidator = new ElementsValidator(new Cube2x2(new Cube3x3(cube4x4)));
        //then
        Assertions.assertTrue(elementsValidator.isRollingPop());
    }

    @Test
    void noRollingPop() {
        //given
        cube4x4.makeMoves(ALG1);
        //when
        elementsValidator = new ElementsValidator(new Cube2x2(new Cube3x3(cube4x4)));
        //then
        Assertions.assertFalse(elementsValidator.isRollingPop());
    }

    private void rotateVertex() {
        char buffer = cube4x4.getCube()[5][0];
        cube4x4.getCube()[5][0] = cube4x4.getCube()[2][0];
        cube4x4.getCube()[2][0] = cube4x4.getCube()[0][0];
        cube4x4.getCube()[0][0] = buffer;
    }

    private void switchTwoFields(int side1, int field1, int side2, int field2) {
        char buffer = cube4x4.getCube()[side1][field1];
        cube4x4.getCube()[side1][field1] = cube4x4.getCube()[side2][field2];
        cube4x4.getCube()[side2][field2] = buffer;
    }
}
