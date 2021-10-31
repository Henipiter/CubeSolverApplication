package validations;

import cubes.Cube3x3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ElementsValidatorTest {

    private static final String ALG1 = "U2 R2 B2 U F2 L2 F2 D' F2 L2 R' B2 U2 R' U L' B' F' D2";

    private ElementsValidator elementsValidator;
    private Cube3x3 cube3x3;

    @BeforeEach
    void init() {
        cube3x3 = new Cube3x3();
    }

    @Test
    void rollingPop2Times() {
        //given
        rotateVertex();
        cube3x3.makeMoves(ALG1);
        rotateVertex();
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertTrue(elementsValidator.isRollingPop());
    }

    @Test
    void rollingPopBefore() {
        //given
        rotateVertex();
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
        rotateVertex();
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
        rotateEdge();
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
        rotateEdge();
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertTrue(elementsValidator.isOllParity());
    }

    @Test
    void isPllParityByEdgesBefore() {
        //given
        switchTwoEdges();
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
        switchTwoEdges();
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertTrue(elementsValidator.isPllParity());
    }

    @Test
    void isPllParityByVerticesBefore() {
        //given
        switchTwoVertices();
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
        switchTwoVertices();
        //when
        elementsValidator = new ElementsValidator(cube3x3);
        //then
        Assertions.assertTrue(elementsValidator.isPllParity());
    }

    @Test
    void noPllParityByVerticesAndEdges() {
        //given
        switchTwoEdges();
        cube3x3.makeMoves(ALG1);
        switchTwoVertices();
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

    private void rotateVertex() {
        char buffer = cube3x3.getCube()[5][0];
        cube3x3.getCube()[5][0] = cube3x3.getCube()[2][0];
        cube3x3.getCube()[2][0] = cube3x3.getCube()[0][0];
        cube3x3.getCube()[0][0] = buffer;
    }

    private void rotateEdge() {
        switchTwoFields(0, 1, 5, 1);
    }

    private void switchTwoVertices() {
        switchTwoFields(0, 0, 0, 2);
        switchTwoFields(2, 0, 5, 2);
        switchTwoFields(5, 0, 3, 0);

    }

    private void switchTwoEdges() {
        switchTwoFields(0, 1, 0, 6);
        switchTwoFields(5, 1, 4, 1);

    }

    private void switchTwoFields(int side1, int field1, int side2, int field2) {
        char buffer = cube3x3.getCube()[side1][field1];
        cube3x3.getCube()[side1][field1] = cube3x3.getCube()[side2][field2];
        cube3x3.getCube()[side2][field2] = buffer;
    }
}
