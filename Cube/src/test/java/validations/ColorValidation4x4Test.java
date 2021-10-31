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

    @BeforeEach
    void init() {
        cube4x4 = new Cube4x4();
    }
    @Test
    void isEdgesHaveRepeatingColorsOrHasColorFromOppositeSide(){
        //given
        colorValidator = new ColorValidation4x4(cube4x4);
        //when
        boolean result = colorValidator.isEdgeColorCorrectness();
        //then
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({"r", "o", "b", "g", "y"})
    void isEdgesHaveRepeatingColorsOrHasColorFromOppositeSide(char color){
        //given
        cube4x4.getCube()[0][1] = color;
        colorValidator = new ColorValidation4x4(cube4x4);
        //when
        boolean result = colorValidator.isEdgeColorCorrectness();
        //then
        Assertions.assertFalse(result);
    }
}