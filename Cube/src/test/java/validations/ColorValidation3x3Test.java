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

    @BeforeEach
    void init() {
        cube3x3 = new Cube3x3();
    }

    @Test
    void isEdgesHaveRepeatingColorsOrHasColorFromOppositeSide() {
        //given
        colorValidator = new ColorValidation3x3(cube3x3);
        //when
        boolean result = colorValidator.isEdgeColorCorrectness();
        //then
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({"r", "o", "b", "g", "y"})
    void isEdgesHaveRepeatingColorsOrHasColorFromOppositeSide1(char color) {
        //given
        cube3x3.getCube()[0][1] = color;
        colorValidator = new ColorValidation3x3(cube3x3);
        //when
        boolean result = colorValidator.isEdgeColorCorrectness();
        //then
        Assertions.assertFalse(result);
    }
}
