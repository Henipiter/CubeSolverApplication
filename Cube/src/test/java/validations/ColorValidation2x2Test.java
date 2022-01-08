package validations;

import cubes.Cube2x2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class ColorValidation2x2Test {

    private Cube2x2 cube2x2;
    private ColorValidation2x2 colorValidator;
    private ValidationTestHelper validationTestHelper;

    @BeforeEach
    void init() {
        cube2x2 = new Cube2x2();
        validationTestHelper = new ValidationTestHelper(cube2x2.getCube());
    }

    @ParameterizedTest
    @CsvSource({"r", "o", "b", "g", "y", "x"})
    void shouldReturnTrueIfThereIsDifferentSumsOfFieldsColors(char color) {
        //given
        cube2x2.getCube()[0][1] = color;
        //when
        colorValidator = new ColorValidation2x2(cube2x2);
        //then
        Assertions.assertTrue(colorValidator.isDifferentSumsOfColors());
    }

    @ParameterizedTest
    @CsvSource({"r, false", "o, false", "x, false", "g, false", "y, true", "b, false"})
    void testUniqueVertexValidation(char color, boolean expected) {
        //given
        cube2x2.getCube()[0][0] = color;
        //when
        colorValidator = new ColorValidation2x2(cube2x2);
        //then
        Assertions.assertEquals(expected, colorValidator.isNonUniqueVertices());
    }

    @Test
    void shouldNotDetectRollingPop() {
        //given
        validationTestHelper.rotateVertex();
        //when
        colorValidator = new ColorValidation2x2(cube2x2);
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
        colorValidator = new ColorValidation2x2(cube2x2);
        //expected
        Assertions.assertFalse(colorValidator.isDifferentSumsOfColors());
        Assertions.assertFalse(colorValidator.isNonUniqueVertices());
        Assertions.assertFalse(colorValidator.isNonUniqueEdges());
    }
}
