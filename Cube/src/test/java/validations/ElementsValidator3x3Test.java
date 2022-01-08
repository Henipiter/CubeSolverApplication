package validations;

import DTOs.Algorithm;
import cache.FileElementCache;
import cubes.Cube3x3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ElementsValidator3x3Test {

    private static final String ALG1 = "U2 R2 B2 U F2 L2 F2 D' F2 L2 R' B2 U2 R' U L' B' F' D2";

    private ElementsValidator elementsValidator;
    private Cube3x3 cube3x3;
    private ValidationTestHelper validationTestHelper;

    @BeforeEach
    void init() {
        FileElementCache.loadAll();
        Algorithm.loadPermutations();
        cube3x3 = new Cube3x3();
        validationTestHelper = new ValidationTestHelper(cube3x3.getCube());
    }

    @Test
    void testWrongVertexColor1() {
        //given
        cube3x3.makeMoves(ALG1);
        validationTestHelper.switchTwoFields(4, 0, 2, 2);
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertTrue(elementsValidator.isWrongVertexColorOrder());
    }

    @Test
    void testWrongVertexColor2() {
        //given
        cube3x3.makeMoves("z");
        validationTestHelper.switchTwoFields(4, 0, 2, 2);
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertTrue(elementsValidator.isWrongVertexColorOrder());
    }

    @Test
    void correctCubeTest() {
        //given
        cube3x3.makeMoves("z");
        //when
        elementsValidator = new ElementsValidator(cube3x3);
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
        validationTestHelper.rotateVertex();
        cube3x3.makeMoves(ALG1);
        validationTestHelper.rotateVertex();
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertTrue(elementsValidator.isRollingPop());
    }

    @Test
    void rollingPopBefore() {
        //given
        validationTestHelper.rotateVertex();
        cube3x3.makeMoves(ALG1);
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertTrue(elementsValidator.isRollingPop());
    }

    @Test
    void rollingPopAfter() {
        //given
        cube3x3.makeMoves(ALG1);
        validationTestHelper.rotateVertex();
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertTrue(elementsValidator.isRollingPop());
    }

    @Test
    void noRollingPop() {
        //given
        cube3x3.makeMoves(ALG1);
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertFalse(elementsValidator.isRollingPop());
    }

    @Test
    void noOllParity() {
        //given
        cube3x3.makeMoves(ALG1);
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertFalse(elementsValidator.isOllParity());
    }

    @Test
    void isOllParityBefore() {
        //given
        validationTestHelper.rotateEdge3x3();
        cube3x3.makeMoves(ALG1);
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertTrue(elementsValidator.isOllParity());
    }

    @Test
    void isOllParityAfter() {
        //given
        cube3x3.makeMoves(ALG1);
        validationTestHelper.rotateEdge3x3();
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertTrue(elementsValidator.isOllParity());
    }

    @Test
    void isPllParityByEdgesBefore() {
        //given
        validationTestHelper.switchTwoEdges3x3();
        cube3x3.makeMoves(ALG1);
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertTrue(elementsValidator.isPllParity());
    }

    @Test
    void isPllParityByEdgesAfter() {
        //given
        cube3x3.makeMoves(ALG1);
        validationTestHelper.switchTwoEdges3x3();
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertTrue(elementsValidator.isPllParity());
    }

    @Test
    void isPllParityByVerticesBefore() {
        //given
        validationTestHelper.switchTwoVertices3x3();
        cube3x3.makeMoves(ALG1);
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertTrue(elementsValidator.isPllParity());
    }

    @Test
    void isPllParityByVerticesAfter() {
        //given
        cube3x3.makeMoves(ALG1);
        validationTestHelper.switchTwoVertices3x3();
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertTrue(elementsValidator.isPllParity());
    }

    @Test
    void noPllParityByVerticesAndEdges() {
        //given
        validationTestHelper.switchTwoEdges3x3();
        cube3x3.makeMoves(ALG1);
        validationTestHelper.switchTwoVertices3x3();
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertFalse(elementsValidator.isPllParity());
    }

    @Test
    void noPllParity() {
        //given
        cube3x3.makeMoves(ALG1);
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertFalse(elementsValidator.isPllParity());
    }

    @ParameterizedTest
    @CsvSource({"R u x", "R u z", "R u y"})
    void correctCenters() {
        //given
        cube3x3.makeMoves(ALG1);
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertFalse(elementsValidator.isWrongCenterOrder());
    }

    @ParameterizedTest
    @CsvSource({"R u x", "R u z", "R u y"})
    void incorrectTwoCenters() {
        //given
        cube3x3.makeMoves(ALG1);
        validationTestHelper.switchCenters(cube3x3.getCenter(), 0, 1);
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertTrue(elementsValidator.isWrongCenterOrder());
    }

    @ParameterizedTest
    @CsvSource({"R u x", "R u z", "R u y"})
    void incorrectTwoCenters1() {
        //given
        cube3x3.makeMoves(ALG1);
        validationTestHelper.switchCenters(cube3x3.getCenter(),0, 4);
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertTrue(elementsValidator.isWrongCenterOrder());
    }

    @ParameterizedTest
    @CsvSource({"R R'", "R u x", "R u z", "R u y"})
    void incorrectFourCenters() {
        //given
        cube3x3.makeMoves(ALG1);
        validationTestHelper.switchCenters(cube3x3.getCenter(),0, 1);
        validationTestHelper.switchCenters(cube3x3.getCenter(),2, 3);
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertFalse(elementsValidator.isWrongCenterOrder());
    }

    @ParameterizedTest
    @CsvSource({"R R'", "R u x", "R u z", "R u y"})
    void incorrectThreeCenters() {
        //given
        cube3x3.makeMoves(ALG1);
        validationTestHelper.switchCenters(cube3x3.getCenter(),0, 4);
        validationTestHelper.switchCenters(cube3x3.getCenter(),4, 2);
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertTrue(elementsValidator.isWrongCenterOrder());
    }
}
