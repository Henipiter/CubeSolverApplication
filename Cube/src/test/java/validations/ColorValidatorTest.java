package validations;

import cubes.Cube3x3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ColorValidatorTest {

    private Cube3x3 cube3x3;
    private ColorValidation3x3 colorValidator;

    @BeforeEach
    void init() {
        cube3x3 = new Cube3x3();
    }

    @Test
    void isVerticesHaveRepeatingColorsOrHasColorFromOppositeSide() {
        //given
        colorValidator = new ColorValidation3x3(cube3x3);
        //when
        boolean result = colorValidator.isVertexColorCorrectness();
        //then
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({"r", "o", "b", "g", "y"})
    void isVerticesHaveRepeatingColorsOrHasColorFromOppositeSide1(char color) {
        //given
        cube3x3.getCube()[0][0] = color;
        colorValidator = new ColorValidation3x3(cube3x3);
        //when
        boolean result = colorValidator.isVertexColorCorrectness();
        //then
        Assertions.assertFalse(result);
    }
}
