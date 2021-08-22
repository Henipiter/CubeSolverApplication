package interpretations;

import cubes.Cube4x4;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Interpretation4x4CentersTest {

    Interpretation4x4Centers interpretation4X4Centers = new Interpretation4x4Centers();
    Cube4x4 cube;

    @BeforeEach
    public void init() {
        cube = new Cube4x4();
    }

    @ParameterizedTest
    @CsvSource({"w,y","y,w","g,b","b,g","o,r","r,o"})
    public void call_getColorOfOppositeSide_and_check_correctness(char input, char expected) {
        Assertions.assertEquals(expected, interpretation4X4Centers.getColorOfOppositeSide(input));
    }

    @Test
    public void call_interpretCenters_and_check_correctness() {
        cube.makeMovesUsingString("r u l d r");
        interpretation4X4Centers.interpretCenters(cube);
        char[][] expectedCenter = new char[][]{
                {'b', 'r', 'o', 'o'},
                {'r', 'r', 'o', 'g'},
                {'g', 'y', 'y', 'w'},
                {'w', 'b', 'y', 'w'},
                {'w', 'b', 'b', 'o'},
                {'r', 'g', 'g', 'y'}
        };
        for (int i = 0; i < 6; i++) {
            Assert.assertArrayEquals(expectedCenter[i], interpretation4X4Centers.getCenterArrayList().get(i).getColor());
        }
    }
    /*****************************************************************/


    @Test
    public void call_inWhichSideIsTheMostWhiteFields_and_should_return_0(){
        interpretation4X4Centers.interpretCenters(cube);
        Assertions.assertEquals(0, interpretation4X4Centers.getSideWithTheMostFieldsWithGivenColor('w'));
    }

    @Test
    public void call_inWhichSideIsTheMostWhiteFields_and_should_return_5(){
        cube.makeMovesUsingString("x");
        interpretation4X4Centers.interpretCenters(cube);
        Assertions.assertEquals(5, interpretation4X4Centers.getSideWithTheMostFieldsWithGivenColor('w'));
    }

    @Test
    public void call_inWhichSideIsTheMostWhiteFields_and_should_return_4(){
        cube.makeMovesUsingString("r u x'");
        interpretation4X4Centers.interpretCenters(cube);
        Assertions.assertEquals(4, interpretation4X4Centers.getSideWithTheMostFieldsWithGivenColor('w'));
    }

    /*****************************************************************/


    @Test
    public void call_inWhichSideIsGivenColorFieldsExceptUpperSide_and_should_return_0(){
        interpretation4X4Centers.interpretCenters(cube);
        Assertions.assertEquals(0, interpretation4X4Centers.inWhichSideIsGivenColorFieldsExceptUpperSide('w'));
    }

    @Test
    public void call_inWhichSideIsGivenColorFieldsExceptUpperSide_and_should_return_3(){
        cube.makeMovesUsingString("r u");
        interpretation4X4Centers.interpretCenters(cube);
        Assertions.assertEquals(3, interpretation4X4Centers.inWhichSideIsGivenColorFieldsExceptUpperSide('w'));
    }

    /*****************************************************************/

    @Test
    public void call_countFieldWithGivenColor_and_verify_correctness(){
        cube.makeMovesUsingString("r u");
        interpretation4X4Centers.interpretCenters(cube);
        Assertions.assertEquals(2, interpretation4X4Centers.countFieldWithGivenColor(0,'w'));
        Assertions.assertEquals(0, interpretation4X4Centers.countFieldWithGivenColor(0,'y'));
        Assertions.assertEquals(0, interpretation4X4Centers.countFieldWithGivenColor(0,'o'));
        Assertions.assertEquals(0, interpretation4X4Centers.countFieldWithGivenColor(0,'r'));
        Assertions.assertEquals(2, interpretation4X4Centers.countFieldWithGivenColor(0,'g'));
        Assertions.assertEquals(0, interpretation4X4Centers.countFieldWithGivenColor(0,'b'));

        Assertions.assertEquals(0, interpretation4X4Centers.countFieldWithGivenColor(1,'w'));
        Assertions.assertEquals(2, interpretation4X4Centers.countFieldWithGivenColor(1,'y'));
        Assertions.assertEquals(0, interpretation4X4Centers.countFieldWithGivenColor(1,'o'));
        Assertions.assertEquals(0, interpretation4X4Centers.countFieldWithGivenColor(1,'r'));
        Assertions.assertEquals(0, interpretation4X4Centers.countFieldWithGivenColor(1,'g'));
        Assertions.assertEquals(2, interpretation4X4Centers.countFieldWithGivenColor(1,'b'));

        Assertions.assertEquals(0, interpretation4X4Centers.countFieldWithGivenColor(4,'w'));
        Assertions.assertEquals(1, interpretation4X4Centers.countFieldWithGivenColor(4,'y'));
        Assertions.assertEquals(0, interpretation4X4Centers.countFieldWithGivenColor(4,'o'));
        Assertions.assertEquals(2, interpretation4X4Centers.countFieldWithGivenColor(4,'r'));
        Assertions.assertEquals(1, interpretation4X4Centers.countFieldWithGivenColor(4,'g'));
        Assertions.assertEquals(0, interpretation4X4Centers.countFieldWithGivenColor(4,'b'));
    }

    /*****************************************************************/

    @Test
    public void call_isStripesOnGivenSides_with_parallel_stripes_and_should_return_true(){
        cube.makeMovesUsingString("r");
        interpretation4X4Centers.interpretCenters(cube);
        Assertions.assertTrue(interpretation4X4Centers.isStripesOnGivenSides(0,5,'w'));
    }

    @Test
    public void call_isStripesOnGivenSides_with_across_stripes_and_should_return_true(){
        cube.makeMovesUsingString("u F");
        interpretation4X4Centers.interpretCenters(cube);
        Assertions.assertTrue(interpretation4X4Centers.isStripesOnGivenSides(4,3,'r'));
    }

    @Test
    public void call_isStripesOnGivenSides_with_no_stripe_on_one_side_and_should_return_false(){
        cube.makeMovesUsingString("u F u");
        interpretation4X4Centers.interpretCenters(cube);
        Assertions.assertFalse(interpretation4X4Centers.isStripesOnGivenSides(4,3,'r'));
    }

    @Test
    public void call_isStripesOnGivenSides_with_no_stripes_on_both_side_and_should_return_false(){
        cube.makeMovesUsingString("u l f");
        interpretation4X4Centers.interpretCenters(cube);
        Assertions.assertFalse(interpretation4X4Centers.isStripesOnGivenSides(2,0,'o'));
    }

    /*****************************************************************/

    @Test
    public void call_isTwoFieldsFormStripe_with_lengthwise_stripe_and_should_return_true(){
        cube.makeMovesUsingString("u");
        interpretation4X4Centers.interpretCenters(cube);
        Assertions.assertTrue(interpretation4X4Centers.isTwoFieldsFormStripe(0,'w'));
    }

    @Test
    public void call_isTwoFieldsFormStripe_with_not_lengthwise_stripe_and_should_return_false(){
        cube.makeMovesUsingString("u r");
        interpretation4X4Centers.interpretCenters(cube);
        Assertions.assertFalse(interpretation4X4Centers.isTwoFieldsFormLengthwiseStripe(2,'g'));
    }

    @Test
    public void call_isTwoFieldsFormStripe_with_two_field_on_side_but_no_stripe_and_should_return_false(){
        cube.makeMovesUsingString("u r U' r'");
        interpretation4X4Centers.interpretCenters(cube);
        Assertions.assertFalse(interpretation4X4Centers.isTwoFieldsFormLengthwiseStripe(4,'g'));
    }

    /*****************************************************************/

    @Test
    public void call_isStripesAreInOneLine_with_stripes_in_one_line_and_should_return_true(){
        cube.makeMovesUsingString("r U2");
        interpretation4X4Centers.interpretCenters(cube);
        Assertions.assertFalse(interpretation4X4Centers.isStripesAreInOneLine(0,4,'r'));
    }

    @Test
    public void call_isStripesAreInOneLine_with_no_stripes_in_one_line_and_should_return_false(){
        cube.makeMovesUsingString("r");
        interpretation4X4Centers.interpretCenters(cube);
        Assertions.assertFalse(interpretation4X4Centers.isStripesAreInOneLine(0,4,'r'));
    }

    @Test
    public void call_isStripesAreInOneLine_with_stripes_across_and_should_return_false(){
        cube.makeMovesUsingString("r U");
        interpretation4X4Centers.interpretCenters(cube);
        Assertions.assertFalse(interpretation4X4Centers.isStripesAreInOneLine(0,4,'g'));
    }

    /*****************************************************************/

    @Test
    public void call_getNumOfFieldsOnGivenSideWithGivenColor_and_should_return_2(){
        cube.makeMovesUsingString("r U");
        interpretation4X4Centers.interpretCenters(cube);
        Assertions.assertEquals(2, interpretation4X4Centers.getNumOfFieldsOnGivenSideWithGivenColor(0,'g'));
    }

    @Test
    public void call_getNumOfFieldsOnGivenSideWithGivenColor_and_should_return_minus1(){
        cube.makeMovesUsingString("r U");
        interpretation4X4Centers.interpretCenters(cube);
        Assertions.assertEquals(-1, interpretation4X4Centers.getNumOfFieldsOnGivenSideWithGivenColor(0,'r'));
    }

    /*****************************************************************/

    @Test
    public void call_isFieldInGivenColor_and_should_return_true(){
        interpretation4X4Centers.interpretCenters(cube);
        Assertions.assertTrue(interpretation4X4Centers.isFieldInGivenColor(0,3,'w'));
    }


    @Test
    public void call_isFieldInGivenColor_and_should_return_false(){
        interpretation4X4Centers.interpretCenters(cube);
        Assertions.assertFalse(interpretation4X4Centers.isFieldInGivenColor(0,3,'g'));
    }

}
