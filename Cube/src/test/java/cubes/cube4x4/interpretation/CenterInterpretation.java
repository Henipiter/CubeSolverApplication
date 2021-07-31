package cubes.cube4x4.interpretation;

import cubes.Cube4x4;
import interpretations.Interpretation4x4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CenterInterpretation {



    Interpretation4x4 interpretation4x4 = new Interpretation4x4();
    Cube4x4 cube;

    @BeforeEach
    public void init() {
        cube = new Cube4x4();
        interpretation4x4.interpretCenters(cube);
    }
    @Test
    public void call_interpretCenters_and_check_correctness(){
        interpretation4x4.interpretCenters(cube);
        int[] order = new int[]{5,6,9,10};
        int[] order2 = new int[]{9,10,6,5};
        for(int i=0;i<6;i++){
            if(i>=3)
                order = order2;
            for(int j=0;j<4;j++){
                char cubeField = cube.getCube()[i][order[j]];
                char interpretedField = interpretation4x4.getCenterArrayList().get(i).getColor()[j];
                Assertions.assertEquals(cubeField, interpretedField);
            }
        }
    }

    @Test
    public void call_inWhichSideIsTheMostWhiteFields_and_should_return_0(){
        interpretation4x4.interpretCenters(cube);
        Assertions.assertEquals(0, interpretation4x4.inWhichSideIsTheMostWhiteFields('w'));
    }

    @Test
    public void call_inWhichSideIsTheMostWhiteFields_and_should_return_5(){
        cube.makeMovesUsingString("X");
        interpretation4x4.interpretCenters(cube);
        Assertions.assertEquals(5, interpretation4x4.inWhichSideIsTheMostWhiteFields('w'));
    }

    @Test
    public void call_inWhichSideIsTheMostWhiteFields_and_should_return_4(){
        cube.makeMovesUsingString("r u X'");
        interpretation4x4.interpretCenters(cube);
        Assertions.assertEquals(4, interpretation4x4.inWhichSideIsTheMostWhiteFields('w'));
    }

    @Test
    public void call_inWhichSideIsGivenColorFieldsExceptUpperSide_and_should_return_0(){
        interpretation4x4.interpretCenters(cube);
        Assertions.assertEquals(0, interpretation4x4.inWhichSideIsGivenColorFieldsExceptUpperSide('w'));
    }

    @Test
    public void call_inWhichSideIsGivenColorFieldsExceptUpperSide_and_should_return_3(){
        cube.makeMovesUsingString("r u");
        interpretation4x4.interpretCenters(cube);
        Assertions.assertEquals(3, interpretation4x4.inWhichSideIsGivenColorFieldsExceptUpperSide('w'));
    }

    @Test
    public void call_countFieldWithGivenColor_and_verify_correctness(){
        cube.makeMovesUsingString("r u");
        interpretation4x4.interpretCenters(cube);
        Assertions.assertEquals(2, interpretation4x4.countFieldWithGivenColor(0,'w'));
        Assertions.assertEquals(0, interpretation4x4.countFieldWithGivenColor(0,'y'));
        Assertions.assertEquals(0, interpretation4x4.countFieldWithGivenColor(0,'o'));
        Assertions.assertEquals(0, interpretation4x4.countFieldWithGivenColor(0,'r'));
        Assertions.assertEquals(2, interpretation4x4.countFieldWithGivenColor(0,'g'));
        Assertions.assertEquals(0, interpretation4x4.countFieldWithGivenColor(0,'b'));

        Assertions.assertEquals(0, interpretation4x4.countFieldWithGivenColor(1,'w'));
        Assertions.assertEquals(2, interpretation4x4.countFieldWithGivenColor(1,'y'));
        Assertions.assertEquals(0, interpretation4x4.countFieldWithGivenColor(1,'o'));
        Assertions.assertEquals(0, interpretation4x4.countFieldWithGivenColor(1,'r'));
        Assertions.assertEquals(0, interpretation4x4.countFieldWithGivenColor(1,'g'));
        Assertions.assertEquals(2, interpretation4x4.countFieldWithGivenColor(1,'b'));

        Assertions.assertEquals(0, interpretation4x4.countFieldWithGivenColor(4,'w'));
        Assertions.assertEquals(1, interpretation4x4.countFieldWithGivenColor(4,'y'));
        Assertions.assertEquals(0, interpretation4x4.countFieldWithGivenColor(4,'o'));
        Assertions.assertEquals(2, interpretation4x4.countFieldWithGivenColor(4,'r'));
        Assertions.assertEquals(1, interpretation4x4.countFieldWithGivenColor(4,'g'));
        Assertions.assertEquals(0, interpretation4x4.countFieldWithGivenColor(4,'b'));
    }

    @Test
    public void call_isStripesOnGivenSides_with_parallel_stripes_and_should_return_true(){
        cube.makeMovesUsingString("r");
        interpretation4x4.interpretCenters(cube);
        Assertions.assertTrue(interpretation4x4.isStripesOnGivenSides(0,5,'w'));
    }

    @Test
    public void call_isStripesOnGivenSides_with_across_stripes_and_should_return_true(){
        cube.makeMovesUsingString("u F");
        interpretation4x4.interpretCenters(cube);
        Assertions.assertTrue(interpretation4x4.isStripesOnGivenSides(4,3,'r'));
    }

    @Test
    public void call_isStripesOnGivenSides_with_no_stripe_on_one_side_and_should_return_false(){
        cube.makeMovesUsingString("u F u");
        interpretation4x4.interpretCenters(cube);
        Assertions.assertFalse(interpretation4x4.isStripesOnGivenSides(4,3,'r'));
    }

    @Test
    public void call_isStripesOnGivenSides_with_no_stripes_on_both_side_and_should_return_false(){
        cube.makeMovesUsingString("u l f");
        interpretation4x4.interpretCenters(cube);
        Assertions.assertFalse(interpretation4x4.isStripesOnGivenSides(2,0,'o'));
    }

    @Test
    public void call_isTwoFieldsFormStripe_with_lengthwise_stripe_and_should_return_true(){
        cube.makeMovesUsingString("u");
        interpretation4x4.interpretCenters(cube);
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormStripe(0,'w'));
    }

    @Test
    public void call_isTwoFieldsFormStripe_with_not_lengthwise_stripe_and_should_return_false(){
        cube.makeMovesUsingString("u r");
        interpretation4x4.interpretCenters(cube);
        Assertions.assertFalse(interpretation4x4.isTwoFieldsFormLengthwiseStripe(2,'g'));
    }

    @Test
    public void call_isTwoFieldsFormStripe_with_two_field_on_side_but_no_stripe_and_should_return_false(){
        cube.makeMovesUsingString("u r U' r'");
        interpretation4x4.interpretCenters(cube);
        Assertions.assertFalse(interpretation4x4.isTwoFieldsFormLengthwiseStripe(4,'g'));
    }


    @Test
    public void call_isStripesAreInOneLine_with_stripes_in_one_line_and_should_return_true(){
        cube.makeMovesUsingString("r U2");
        interpretation4x4.interpretCenters(cube);
        Assertions.assertFalse(interpretation4x4.isStripesAreInOneLine(0,4,'r'));
    }

    @Test
    public void call_isStripesAreInOneLine_with_no_stripes_in_one_line_and_should_return_false(){
        cube.makeMovesUsingString("r");
        interpretation4x4.interpretCenters(cube);
        Assertions.assertFalse(interpretation4x4.isStripesAreInOneLine(0,4,'r'));
    }

    @Test
    public void call_isStripesAreInOneLine_with_stripes_across_and_should_return_false(){
        cube.makeMovesUsingString("r U");
        interpretation4x4.interpretCenters(cube);
        Assertions.assertFalse(interpretation4x4.isStripesAreInOneLine(0,4,'g'));
    }

    @Test
    public void call_getNumOfFieldsOnGivenSideWithGivenColor_and_should_return_2(){
        cube.makeMovesUsingString("r U");
        interpretation4x4.interpretCenters(cube);
        Assertions.assertEquals(2,interpretation4x4.getNumOfFieldsOnGivenSideWithGivenColor(0,'g'));
    }

    @Test
    public void call_getNumOfFieldsOnGivenSideWithGivenColor_and_should_return_minus1(){
        cube.makeMovesUsingString("r U");
        interpretation4x4.interpretCenters(cube);
        Assertions.assertEquals(-1,interpretation4x4.getNumOfFieldsOnGivenSideWithGivenColor(0,'r'));
    }
    @Test
    public void call_isFieldInGivenColor_and_should_return_true(){
        interpretation4x4.interpretCenters(cube);
        Assertions.assertTrue(interpretation4x4.isFieldInGivenColor(0,3,'w'));
    }


    @Test
    public void call_isFieldInGivenColor_and_should_return_false(){
        interpretation4x4.interpretCenters(cube);
        Assertions.assertFalse(interpretation4x4.isFieldInGivenColor(0,3,'g'));
    }
}
