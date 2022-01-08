package validations;

import cubes.Cube4x4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class ColorValidation4x4Test {

    private Cube4x4 cube4x4;
    private ColorValidation4x4 colorValidator;
    private ValidationTestHelper validationTestHelper;

    @BeforeEach
    void init() {
        cube4x4 = new Cube4x4();
        validationTestHelper = new ValidationTestHelper(cube4x4.getCube());
    }

    @Test
    void isEdgesHaveRepeatingColorsOrHasColorFromOppositeSide() {
        //given
        colorValidator = new ColorValidation4x4(cube4x4);
        //when
        boolean result = colorValidator.isNonUniqueEdges();
        //then
        Assertions.assertFalse(result);
    }

    @ParameterizedTest
    @CsvSource({"r", "o", "b", "g", "y", "x"})
    void shouldReturnTrueIfThereIsDifferentSumsOfFieldsColors(char color) {
        //given
        cube4x4.getCube()[0][1] = color;
        //when
        colorValidator = new ColorValidation4x4(cube4x4);
        //then
        Assertions.assertTrue(colorValidator.isDifferentSumsOfColors());
    }

    @ParameterizedTest
    @CsvSource({"r, true", "o, true", "x, false", "g, false", "y, true", "b, false"})
    void testUniqueEdgeValidation1(char color, boolean expected) {
        //given
        cube4x4.getCube()[0][1] = color;
        cube4x4.getCube()[0][2] = color;
        //when
        colorValidator = new ColorValidation4x4(cube4x4);
        //then
        Assertions.assertEquals(expected, colorValidator.isNonUniqueEdges());
    }

    @ParameterizedTest
    @CsvSource({"r", "x", "g", "y", "b"})
    void testUniqueEdgeValidation2(char color) {
        //given
        cube4x4.getCube()[0][1] = color;
        //when
        colorValidator = new ColorValidation4x4(cube4x4);
        //then
        Assertions.assertTrue(colorValidator.isNonUniqueEdges());
    }

    @ParameterizedTest
    @CsvSource({"r, false", "o, false", "x, false", "g, false", "y, true", "b, false"})
    void testUniqueVertexValidation(char color, boolean expected) {
        //given
        cube4x4.getCube()[0][0] = color;
        //when
        colorValidator = new ColorValidation4x4(cube4x4);
        //then
        Assertions.assertEquals(expected, colorValidator.isNonUniqueVertices());
    }

    @Test
    void shouldNotDetectRollingPop() {
        //given
        validationTestHelper.rotateVertex();
        //when
        colorValidator = new ColorValidation4x4(cube4x4);
        //expected
        Assertions.assertFalse(colorValidator.isDifferentSumsOfColors());
        Assertions.assertFalse(colorValidator.isNonUniqueVertices());
        Assertions.assertFalse(colorValidator.isNonUniqueEdges());
    }

    @Test
    void shouldNotDetectOllAndPllParity() {
        //given
        validationTestHelper.switchTwoVertices4x4();
        validationTestHelper.rotateEdge4x4();
        //when
        colorValidator = new ColorValidation4x4(cube4x4);
        //expected
        Assertions.assertFalse(colorValidator.isDifferentSumsOfColors());
        Assertions.assertFalse(colorValidator.isNonUniqueVertices());
        Assertions.assertFalse(colorValidator.isNonUniqueEdges());
    }

    @Test
    void shouldNotBadVertexColorOrder() {
        //given
        validationTestHelper.switchTwoFields(4, 0, 2, 3);
        //when
        colorValidator = new ColorValidation4x4(cube4x4);
        //expected
        Assertions.assertFalse(colorValidator.isDifferentSumsOfColors());
        Assertions.assertFalse(colorValidator.isNonUniqueVertices());
        Assertions.assertFalse(colorValidator.isNonUniqueEdges());
    }
}