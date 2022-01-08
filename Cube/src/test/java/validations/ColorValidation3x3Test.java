package validations;

import cubes.Cube3x3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class ColorValidation3x3Test {

    private Cube3x3 cube3x3;
    private ColorValidation3x3 colorValidator;
    private ValidationTestHelper validationTestHelper;

    @BeforeEach
    void init() {
        cube3x3 = new Cube3x3();
        validationTestHelper = new ValidationTestHelper(cube3x3.getCube());
    }

    @ParameterizedTest
    @CsvSource({"r", "o", "b", "g", "y", "x"})
    void shouldReturnTrueIfThereIsDifferentSumsOfFieldsColors(char color) {
        //given
        cube3x3.getCube()[0][1] = color;
        //when
        colorValidator = new ColorValidation3x3(cube3x3);
        //then
        Assertions.assertTrue(colorValidator.isDifferentSumsOfColors());
    }

    @ParameterizedTest
    @CsvSource({"r, true", "o, true", "x, false", "g, false", "y, true", "b, false"})
    void testUniqueEdgeValidation(char color, boolean expected) {
        //given
        cube3x3.getCube()[0][1] = color;
        //when
        colorValidator = new ColorValidation3x3(cube3x3);
        //then
        Assertions.assertEquals(expected, colorValidator.isNonUniqueEdges());
    }

    @ParameterizedTest
    @CsvSource({"r, false", "o, false", "x, false", "g, false", "y, true", "b, false"})
    void testUniqueVertexValidation(char color, boolean expected) {
        //given
        cube3x3.getCube()[0][0] = color;
        //when
        colorValidator = new ColorValidation3x3(cube3x3);
        //then
        Assertions.assertEquals(expected, colorValidator.isNonUniqueVertices());
    }

    @Test
    void shouldNotDetectRollingPop() {
        //given
        validationTestHelper.rotateVertex();
        //when
        colorValidator = new ColorValidation3x3(cube3x3);
        //expected
        Assertions.assertFalse(colorValidator.isDifferentSumsOfColors());
        Assertions.assertFalse(colorValidator.isNonUniqueVertices());
        Assertions.assertFalse(colorValidator.isNonUniqueEdges());
    }

    @Test
    void shouldNotDetectOllAndPllParity() {
        //given
        validationTestHelper.rotateEdge3x3();
        validationTestHelper.switchTwoEdges3x3();
        //when
        colorValidator = new ColorValidation3x3(cube3x3);
        //expected
        Assertions.assertFalse(colorValidator.isDifferentSumsOfColors());
        Assertions.assertFalse(colorValidator.isNonUniqueVertices());
        Assertions.assertFalse(colorValidator.isNonUniqueEdges());
    }

    @Test
    void shouldNotBadVertexColorOrder() {
        //given
        validationTestHelper.switchTwoFields(4, 0, 2, 2);
        //when
        colorValidator = new ColorValidation3x3(cube3x3);
        //expected
        Assertions.assertFalse(colorValidator.isDifferentSumsOfColors());
        Assertions.assertFalse(colorValidator.isNonUniqueVertices());
        Assertions.assertFalse(colorValidator.isNonUniqueEdges());
    }
}
