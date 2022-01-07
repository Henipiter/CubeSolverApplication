package validations;

import DTOs.Algorithm;
import cache.FileElementCache;
import cubes.Cube2x2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ElementsValidator2x2Test {

    private static final String ALG1 = "U2 R2 B2 U F2 L2 F2 D' F2 L2 R' B2 U2 R' U L' B' F' D2";

    private ElementsValidator elementsValidator;
    private Cube2x2 cube2x2;

    @BeforeEach
    void init() {
        FileElementCache.loadAll();
        Algorithm.loadPermutations();
        cube2x2 = new Cube2x2();
    }

    @Test
    void testWrongVertexColor1() {
        //given
        cube2x2.makeMoves(ALG1);
        switchTwoFields(4,0,2,1);
        //when
        elementsValidator = new ElementsValidator(cube2x2);
        //then
        Assertions.assertTrue(elementsValidator.isWrongVertexColorOrder());
    }

    @Test
    void testWrongVertexColor2() {
        //given
        cube2x2.makeMoves("z");
        switchTwoFields(4,0,2,1);
        //when
        elementsValidator = new ElementsValidator(cube2x2);
        //then
        Assertions.assertTrue(elementsValidator.isWrongVertexColorOrder());
    }

    @Test
    void correctCubeTest() {
        //given
        cube2x2.makeMoves("z");
        //when
        elementsValidator = new ElementsValidator(cube2x2);
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
        cube2x2.makeMoves(ALG1);
        rotateVertex();
        //when
        elementsValidator = new ElementsValidator(cube2x2);
        //then
        Assertions.assertTrue(elementsValidator.isRollingPop());
    }

    @Test
    void rollingPopBefore() {
        //given
        rotateVertex();
        cube2x2.makeMoves(ALG1);
        //when
        elementsValidator = new ElementsValidator(cube2x2);
        //then
        Assertions.assertTrue(elementsValidator.isRollingPop());
    }

    @Test
    void rollingPopAfter() {
        //given
        cube2x2.makeMoves(ALG1);
        rotateVertex();
        //when
        elementsValidator = new ElementsValidator(cube2x2);
        //then
        Assertions.assertTrue(elementsValidator.isRollingPop());
    }

    @Test
    void noRollingPop() {
        //given
        cube2x2.makeMoves(ALG1);
        //when
        elementsValidator = new ElementsValidator(cube2x2);
        //then
        Assertions.assertFalse(elementsValidator.isRollingPop());
    }

    private void rotateVertex() {
        char buffer = cube2x2.getCube()[5][0];
        cube2x2.getCube()[5][0] = cube2x2.getCube()[2][0];
        cube2x2.getCube()[2][0] = cube2x2.getCube()[0][0];
        cube2x2.getCube()[0][0] = buffer;
    }

    private void switchTwoFields(int side1, int field1, int side2, int field2) {
        char buffer = cube2x2.getCube()[side1][field1];
        cube2x2.getCube()[side1][field1] = cube2x2.getCube()[side2][field2];
        cube2x2.getCube()[side2][field2] = buffer;
    }
}
