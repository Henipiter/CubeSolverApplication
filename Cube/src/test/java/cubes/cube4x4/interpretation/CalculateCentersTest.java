package cubes.cube4x4.interpretation;

import DTOs.InspectMove;
import DTOs.MoveEnum;
import DTOs.MoveTypeEnum;
import calculations.CalculateCenters4x4;
import cubes.Cube4x4;
import interpretations.Interpretation4x4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CalculateCentersTest {

    Interpretation4x4 interpretation4x4 = new Interpretation4x4();
    CalculateCenters4x4 calculateCenters4x4 = new CalculateCenters4x4();
    Cube4x4 cube;

    @BeforeEach
    public void init() {
        cube = new Cube4x4();
    }

    @Test
    public void call_rotateSideToGetItOnTopAlgorithm_for_0side_on_up(){

        cube.move(calculateCenters4x4.rotateSideToGetItOnTopAlgorithm(0));
        Assertions.assertEquals('w', cube.getCube()[0][5]);
    }

    @Test
    public void call_rotateSideToGetItOnTopAlgorithm_for_1side_on_up(){
        cube.makeMovesUsingString("X2");
        cube.move(calculateCenters4x4.rotateSideToGetItOnTopAlgorithm(1));
        Assertions.assertEquals('w', cube.getCube()[0][5]);
    }

    @Test
    public void call_rotateSideToGetItOnTopAlgorithm_for_2side_on_up(){
        cube.makeMovesUsingString("Z'");
        cube.move(calculateCenters4x4.rotateSideToGetItOnTopAlgorithm(2));
        Assertions.assertEquals('w', cube.getCube()[0][5]);
    }

    @Test
    public void call_rotateSideToGetItOnTopAlgorithm_for_3side_on_up(){
        cube.makeMovesUsingString("Z");
        cube.move(calculateCenters4x4.rotateSideToGetItOnTopAlgorithm(3));
        Assertions.assertEquals('w', cube.getCube()[0][5]);
    }

    @Test
    public void call_rotateSideToGetItOnTopAlgorithm_for_4side_on_up(){
        cube.makeMovesUsingString("X'");
        cube.move(calculateCenters4x4.rotateSideToGetItOnTopAlgorithm(4));
        Assertions.assertEquals('w', cube.getCube()[0][5]);
    }

    @Test
    public void call_rotateSideToGetItOnTopAlgorithm_for_5side_on_up(){
        cube.makeMovesUsingString("X");
        cube.move(calculateCenters4x4.rotateSideToGetItOnTopAlgorithm(5));
        Assertions.assertEquals('w', cube.getCube()[0][5]);
    }

    /*****************************************************************/

    @Test
    public void call_getMoveToSetGivenSideOnFrontExceptBottomAndUpperSide_for_2side(){
        cube.makeMovesUsingString("Z'");
        cube.move(calculateCenters4x4.getMoveToSetGivenSideOnFrontExceptBottomAndUpperSide(2));
        Assertions.assertEquals('w', cube.getCube()[4][5]);
    }

    @Test
    public void call_getMoveToSetGivenSideOnFrontExceptBottomAndUpperSide_for_3side(){
        cube.makeMovesUsingString("Z");
        cube.move(calculateCenters4x4.getMoveToSetGivenSideOnFrontExceptBottomAndUpperSide(3));
        Assertions.assertEquals('w', cube.getCube()[4][5]);
    }

    @Test
    public void call_getMoveToSetGivenSideOnFrontExceptBottomAndUpperSide_for_5side(){
        cube.makeMovesUsingString("X");
        cube.move(calculateCenters4x4.getMoveToSetGivenSideOnFrontExceptBottomAndUpperSide(5));
        Assertions.assertEquals('w', cube.getCube()[4][5]);
    }

    /*****************************************************************/

    @Test
    public void call_prepareJoiningIfOnBothSidesAreStripes_for_lengthwise_stripes_for_sides_0_and_4(){
        //given
        cube.makeMovesUsingString("Rw'");
        calculateCenters4x4.refreshCube(cube);
        //when
        ArrayList<InspectMove> alg =calculateCenters4x4.prepareJoiningIfOnBothSidesAreStripes(4,0,'w');
        cube.makeMoves(alg);
        //then
        interpretation4x4.interpretCenters(cube);
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(0,'w'));
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(4,'w'));
    }

    @Test
    public void call_prepareJoiningIfOnBothSidesAreStripes_for_lengthwise_stripes_for_sides_0_and_5(){
        //given
        cube.makeMovesUsingString("Rw");
        calculateCenters4x4.refreshCube(cube);
        //when
        ArrayList<InspectMove> alg =calculateCenters4x4.prepareJoiningIfOnBothSidesAreStripes(5,0,'w');
        cube.makeMoves(alg);
        //then
        interpretation4x4.interpretCenters(cube);
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(0,'w'));
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(5,'w'));
    }

    @Test
    public void call_prepareJoiningIfOnBothSidesAreStripes_for_lengthwise_stripes_for_sides_0_and_1(){
        //given
        cube.makeMovesUsingString("Rw2");
        calculateCenters4x4.refreshCube(cube);
        //when
        ArrayList<InspectMove> alg =calculateCenters4x4.prepareJoiningIfOnBothSidesAreStripes(1,0,'w');
        cube.makeMoves(alg);
        //then
        interpretation4x4.interpretCenters(cube);
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(0,'w'));
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(1,'w'));
    }

    @Test
    public void call_prepareJoiningIfOnBothSidesAreStripes_for_lengthwise_stripes_for_sides_0_and_1_different(){
        //given
        cube.makeMovesUsingString("Rw2 D' U");
        calculateCenters4x4.refreshCube(cube);
        //when
        ArrayList<InspectMove> alg =calculateCenters4x4.prepareJoiningIfOnBothSidesAreStripes(1,0,'w');
        cube.makeMoves(alg);
        //then
        interpretation4x4.interpretCenters(cube);
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(0,'w'));
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(1,'w'));
    }

    @Test
    public void call_prepareJoiningIfOnBothSidesAreStripes_for_notlengthwise_both_stripes_for_sides_0_and_4(){
        //given
        cube.makeMovesUsingString("Rw' F' U");
        calculateCenters4x4.refreshCube(cube);
        //when
        ArrayList<InspectMove> alg =calculateCenters4x4.prepareJoiningIfOnBothSidesAreStripes(4,0,'w');
        cube.makeMoves(alg);
        //then
        interpretation4x4.interpretCenters(cube);
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(0,'w'));
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(4,'w'));
    }

    @Test
    public void call_prepareJoiningIfOnBothSidesAreStripes_for_notlengthwise_both_stripes_for_sides_0_and_5(){
        //given
        cube.makeMovesUsingString("Rw' F' U Y2");
        calculateCenters4x4.refreshCube(cube);
        //when
        ArrayList<InspectMove> alg =calculateCenters4x4.prepareJoiningIfOnBothSidesAreStripes(5,0,'w');
        cube.makeMoves(alg);
        //then
        interpretation4x4.interpretCenters(cube);
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(0,'w'));
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(5,'w'));
    }

    @Test
    public void call_prepareJoiningIfOnBothSidesAreStripes_for_lengthwise_both_stripes_for_sides_0_and_1(){
        //given
        cube.makeMovesUsingString("Rw2 D' U Y2");
        calculateCenters4x4.refreshCube(cube);
        //when
        ArrayList<InspectMove> alg =calculateCenters4x4.prepareJoiningIfOnBothSidesAreStripes(1,0,'w');
        cube.makeMoves(alg);
        //then
        interpretation4x4.interpretCenters(cube);
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(0,'w'));
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(1,'w'));
    }

    @Test
    public void call_prepareJoiningIfOnBothSidesAreStripes_for_across_stripes_for_sides_0_and_4(){
        //given
        cube.makeMovesUsingString("Rw' F'");
        calculateCenters4x4.refreshCube(cube);
        //when
        ArrayList<InspectMove> alg =calculateCenters4x4.prepareJoiningIfOnBothSidesAreStripes(4,0,'w');
        cube.makeMoves(alg);
        //then
        interpretation4x4.interpretCenters(cube);
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(0,'w'));
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(4,'w'));
    }

    @Test
    public void call_prepareJoiningIfOnBothSidesAreStripes_for_across_stripes_for_sides_0_and_4_different(){
        //given
        cube.makeMovesUsingString("Rw' U");
        calculateCenters4x4.refreshCube(cube);
        //when
        ArrayList<InspectMove> alg =calculateCenters4x4.prepareJoiningIfOnBothSidesAreStripes(4,0,'w');
        cube.makeMoves(alg);
        //then
        interpretation4x4.interpretCenters(cube);
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(0,'w'));
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(4,'w'));
    }

    @Test
    public void call_prepareJoiningIfOnBothSidesAreStripes_for_across_stripes_for_sides_0_and_5(){
        //given
        cube.makeMovesUsingString("Rw' F' Y2");
        calculateCenters4x4.refreshCube(cube);
        //when
        ArrayList<InspectMove> alg =calculateCenters4x4.prepareJoiningIfOnBothSidesAreStripes(5,0,'w');
        cube.makeMoves(alg);
        //then
        interpretation4x4.interpretCenters(cube);
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(0,'w'));
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(5,'w'));
    }

    @Test
    public void call_prepareJoiningIfOnBothSidesAreStripes_for_across_stripes_for_sides_0_and_5_different(){
        //given
        cube.makeMovesUsingString("Rw' U Y2");
        calculateCenters4x4.refreshCube(cube);
        //when
        ArrayList<InspectMove> alg =calculateCenters4x4.prepareJoiningIfOnBothSidesAreStripes(5,0,'w');
        cube.makeMoves(alg);
        //then
        interpretation4x4.interpretCenters(cube);
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(0,'w'));
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(5,'w'));
    }

    @Test
    public void call_prepareJoiningIfOnBothSidesAreStripes_for_across_stripes_for_sides_0_and_1(){
        //given
        cube.makeMovesUsingString("Rw2 D");
        calculateCenters4x4.refreshCube(cube);
        //when
        ArrayList<InspectMove> alg =calculateCenters4x4.prepareJoiningIfOnBothSidesAreStripes(1,0,'w');
        cube.makeMoves(alg);
        //then
        interpretation4x4.interpretCenters(cube);
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(0,'w'));
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(1,'w'));
    }

    @Test
    public void call_prepareJoiningIfOnBothSidesAreStripes_for_across_stripes_for_sides_0_and_1_different(){
        //given
        cube.makeMovesUsingString("Rw2 U");
        calculateCenters4x4.refreshCube(cube);
        //when
        ArrayList<InspectMove> alg =calculateCenters4x4.prepareJoiningIfOnBothSidesAreStripes(1,0,'w');
        cube.makeMoves(alg);
        //then
        interpretation4x4.interpretCenters(cube);
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(0,'w'));
        Assertions.assertTrue(interpretation4x4.isTwoFieldsFormLengthwiseStripe(1,'w'));
    }

    /*****************************************************************/

    @Test
    public void call_getMoveEnumToSetup_should_return_L_from_side4_left_bottom_field(){
        //given
        cube.makeMovesUsingString("Rw' F Rw");
        calculateCenters4x4.refreshCube(cube);
        //when
        MoveEnum moveEnum =calculateCenters4x4.getMoveEnumToSetup(4,'w');
        //then
        Assertions.assertEquals(MoveEnum.Lw, moveEnum);
    }

    @Test
    public void call_getMoveEnumToSetup_should_return_L_from_side4_left_upper_field(){
        //given
        cube.makeMovesUsingString("Rw' F' Rw");
        calculateCenters4x4.refreshCube(cube);
        //when
        MoveEnum moveEnum =calculateCenters4x4.getMoveEnumToSetup(4,'w');
        //then
        Assertions.assertEquals(MoveEnum.Lw, moveEnum);
    }

    @Test
    public void call_getMoveEnumToSetup_should_return_R_from_side4_right_bottom_field(){
        //given
        cube.makeMovesUsingString("Lw F' Lw'");
        calculateCenters4x4.refreshCube(cube);
        //when
        MoveEnum moveEnum =calculateCenters4x4.getMoveEnumToSetup(4,'w');
        //then
        Assertions.assertEquals(MoveEnum.Rw, moveEnum);
    }

    @Test
    public void call_getMoveEnumToSetup_should_return_R_from_side4_right_upper_field(){
        //given
        cube.makeMovesUsingString("Lw F Lw'");
        calculateCenters4x4.refreshCube(cube);
        //when
        MoveEnum moveEnum =calculateCenters4x4.getMoveEnumToSetup(4,'w');
        //then
        Assertions.assertEquals(MoveEnum.Rw, moveEnum);
    }

    @Test
    public void call_getMoveEnumToSetup_should_return_L_from_side5_upper_left_field(){
        //given
        cube.makeMovesUsingString("Lw F' Lw' Y2");
        calculateCenters4x4.refreshCube(cube);
        //when
        MoveEnum moveEnum =calculateCenters4x4.getMoveEnumToSetup(5,'w');
        //then
        Assertions.assertEquals(MoveEnum.Lw, moveEnum);
    }

    @Test
    public void call_getMoveEnumToSetup_should_return_L_from_side5_bottom_left_field(){
        //given
        cube.makeMovesUsingString("Lw F Lw' Y2");
        calculateCenters4x4.refreshCube(cube);
        //when
        MoveEnum moveEnum =calculateCenters4x4.getMoveEnumToSetup(5,'w');
        //then
        Assertions.assertEquals(MoveEnum.Lw, moveEnum);
    }

    @Test
    public void call_getMoveEnumToSetup_should_return_R_from_side5_upper_right_field(){
        //given
        cube.makeMovesUsingString("Rw' F' Rw Y2");
        calculateCenters4x4.refreshCube(cube);
        //when
        MoveEnum moveEnum =calculateCenters4x4.getMoveEnumToSetup(5,'w');
        //then
        Assertions.assertEquals(MoveEnum.Rw, moveEnum);
    }

    @Test
    public void call_getMoveEnumToSetup_should_return_R_from_side5_bottom_right_field(){
        //given
        cube.makeMovesUsingString("Rw' F' Rw Y2");
        calculateCenters4x4.refreshCube(cube);
        //when
        MoveEnum moveEnum =calculateCenters4x4.getMoveEnumToSetup(5,'w');
        //then
        Assertions.assertEquals(MoveEnum.Rw, moveEnum);
    }

    @Test
    public void call_getMoveEnumToSetup_should_return_R_from_side1_bottom_right_field(){
        //given
        cube.makeMovesUsingString("Lw2 D' Lw2");
        calculateCenters4x4.refreshCube(cube);
        //when
        MoveEnum moveEnum =calculateCenters4x4.getMoveEnumToSetup(1,'w');
        //then
        Assertions.assertEquals(MoveEnum.Rw, moveEnum);
    }

    @Test
    public void call_getMoveEnumToSetup_should_return_R_from_side1_upper_right_field(){
        //given
        cube.makeMovesUsingString("Lw2 D Lw2");
        calculateCenters4x4.refreshCube(cube);
        //when
        MoveEnum moveEnum =calculateCenters4x4.getMoveEnumToSetup(1,'w');
        //then
        Assertions.assertEquals(MoveEnum.Rw, moveEnum);
    }

    @Test
    public void call_getMoveEnumToSetup_should_return_L_from_side1_bottom_right_field(){
        //given
        cube.makeMovesUsingString("Rw2 D' Rw2");
        calculateCenters4x4.refreshCube(cube);
        //when
        MoveEnum moveEnum =calculateCenters4x4.getMoveEnumToSetup(1,'w');
        //then
        Assertions.assertEquals(MoveEnum.Lw, moveEnum);
    }

    @Test
    public void call_getMoveEnumToSetup_should_return_L_from_side1_upper_right_field(){
        //given
        cube.makeMovesUsingString("Rw2 D Rw2");
        calculateCenters4x4.refreshCube(cube);
        //when
        MoveEnum moveEnum =calculateCenters4x4.getMoveEnumToSetup(1,'w');
        //then
        Assertions.assertEquals(MoveEnum.Lw, moveEnum);
    }

    /*****************************************************************/

    @Test
    public void call_getMoveEnumTypeToSetup_should_return_PRIM_from_side4_left_bottom_field(){
        //given
        cube.makeMovesUsingString("Rw' F Rw");
        calculateCenters4x4.refreshCube(cube);
        //when
        MoveTypeEnum moveTypeEnum =calculateCenters4x4.getMoveEnumTypeToSetup(4,'w');
        //then
        Assertions.assertEquals(MoveTypeEnum.PRIM, moveTypeEnum);
    }

    @Test
    public void call_getMoveEnumTypeToSetup_should_return_PRIM_from_side4_left_upper_field(){
        //given
        cube.makeMovesUsingString("Rw' F' Rw");
        calculateCenters4x4.refreshCube(cube);
        //when
        MoveTypeEnum moveTypeEnum =calculateCenters4x4.getMoveEnumTypeToSetup(4,'w');
        //then
        Assertions.assertEquals(MoveTypeEnum.PRIM, moveTypeEnum);
    }

    @Test
    public void call_getMoveEnumTypeToSetup_should_return_SIMPLE_from_side4_right_bottom_field(){
        //given
        cube.makeMovesUsingString("Lw F' Lw'");
        calculateCenters4x4.refreshCube(cube);
        //when
        MoveTypeEnum moveTypeEnum =calculateCenters4x4.getMoveEnumTypeToSetup(4,'w');
        //then
        Assertions.assertEquals(MoveTypeEnum.SIMPLE, moveTypeEnum);
    }

    @Test
    public void call_getMoveEnumTypeToSetup_should_return_SIMPLE_from_side4_right_upper_field(){
        //given
        cube.makeMovesUsingString("Lw F Lw'");
        calculateCenters4x4.refreshCube(cube);
        //when
        MoveTypeEnum moveTypeEnum =calculateCenters4x4.getMoveEnumTypeToSetup(4,'w');
        //then
        Assertions.assertEquals(MoveTypeEnum.SIMPLE, moveTypeEnum);
    }

    @Test
    public void call_getMoveEnumTypeToSetup_should_return_SIMPLE_from_side5_left_bottom_field(){
        //given
        cube.makeMovesUsingString("Rw' F Rw Y2");
        calculateCenters4x4.refreshCube(cube);
        //when
        MoveTypeEnum moveTypeEnum =calculateCenters4x4.getMoveEnumTypeToSetup(5,'w');
        //then
        Assertions.assertEquals(MoveTypeEnum.PRIM, moveTypeEnum);
    }

    @Test
    public void call_getMoveEnumTypeToSetup_should_return_PRIM_from_side5_left_upper_field(){
        //given
        cube.makeMovesUsingString("Rw' F' Rw Y2");
        calculateCenters4x4.refreshCube(cube);
        //when
        MoveTypeEnum moveTypeEnum =calculateCenters4x4.getMoveEnumTypeToSetup(5,'w');
        //then
        Assertions.assertEquals(MoveTypeEnum.PRIM, moveTypeEnum);
    }

    @Test
    public void call_getMoveEnumTypeToSetup_should_return_SIMPLE_from_side5_right_bottom_field(){
        //given
        cube.makeMovesUsingString("Lw F' Lw' Y2");
        calculateCenters4x4.refreshCube(cube);
        //when
        MoveTypeEnum moveTypeEnum =calculateCenters4x4.getMoveEnumTypeToSetup(5,'w');
        //then
        Assertions.assertEquals(MoveTypeEnum.SIMPLE, moveTypeEnum);
    }

    @Test
    public void call_getMoveEnumTypeToSetup_should_return_SIMPLE_from_side5_right_upper_field(){
        //given
        cube.makeMovesUsingString("Lw F Lw' Y2");
        calculateCenters4x4.refreshCube(cube);
        //when
        MoveTypeEnum moveTypeEnum =calculateCenters4x4.getMoveEnumTypeToSetup(5,'w');
        //then
        Assertions.assertEquals(MoveTypeEnum.SIMPLE, moveTypeEnum);
    }


    @Test
    public void call_getMoveEnumTypeToSetup_should_return_DOUBLE_from_side1_right_upper_field(){
        //given
        cube.makeMovesUsingString("Lw2 D Lw2");
        calculateCenters4x4.refreshCube(cube);
        //when
        MoveTypeEnum moveTypeEnum =calculateCenters4x4.getMoveEnumTypeToSetup(1,'w');
        //then
        Assertions.assertEquals(MoveTypeEnum.DOUBLE, moveTypeEnum);
    }

    /*****************************************************************/

    @Test
    public void call_getReverseSetupMoveToJoin_should_return_r(){
        //given
        InspectMove inspectMove=new InspectMove("r'");
        InspectMove expected = new InspectMove("r");
        //when
        InspectMove result =calculateCenters4x4.getReverseSetupMoveToJoin(inspectMove);
        //then
        Assertions.assertEquals(expected, result);
    }

    /*****************************************************************/

    @Test
    public void call_getMoveToJoin_from_side4_should_return_Uprim(){
        cube.makeMovesUsingString("Rw' F Rw");
        calculateCenters4x4.refreshCube(cube);
        InspectMove result = calculateCenters4x4.getMoveToJoin(4,'w');
        InspectMove expected = new InspectMove("U'");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void call_getMoveToJoin_from_side4_should_return_U(){
        cube.makeMovesUsingString("Rw' F' Rw");
        calculateCenters4x4.refreshCube(cube);
        InspectMove result = calculateCenters4x4.getMoveToJoin(4,'w');
        InspectMove expected = new InspectMove("U");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void call_getMoveToJoin_from_side4_should_return_Udouble(){
        cube.makeMovesUsingString("Rw' F2 Rw");
        calculateCenters4x4.refreshCube(cube);
        InspectMove result = calculateCenters4x4.getMoveToJoin(4,'w');
        InspectMove expected = new InspectMove("U2");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void call_getMoveToJoin_from_side5_should_return_Uprim(){
        cube.makeMovesUsingString("Rw' F Rw Y2");
        calculateCenters4x4.refreshCube(cube);
        InspectMove result = calculateCenters4x4.getMoveToJoin(5,'w');
        InspectMove expected = new InspectMove("U'");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void call_getMoveToJoin_from_side5_should_return_U(){
        cube.makeMovesUsingString("Rw' F' Rw Y2");
        calculateCenters4x4.refreshCube(cube);
        InspectMove result = calculateCenters4x4.getMoveToJoin(5,'w');
        InspectMove expected = new InspectMove("U");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void call_getMoveToJoin_from_side5_should_return_Udouble(){
        cube.makeMovesUsingString("Rw' F2 Rw Y2");
        calculateCenters4x4.refreshCube(cube);
        InspectMove result = calculateCenters4x4.getMoveToJoin(5,'w');
        InspectMove expected = new InspectMove("U2");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void call_getMoveToJoin_from_side1_should_return_Uprim(){
        cube.makeMovesUsingString("Rw2 D Rw2 Y2");
        calculateCenters4x4.refreshCube(cube);
        InspectMove result = calculateCenters4x4.getMoveToJoin(1,'w');
        InspectMove expected = new InspectMove("U'");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void call_getMoveToJoin_from_side1_should_return_U(){
        cube.makeMovesUsingString("Rw2 D' Rw2 Y2");
        calculateCenters4x4.refreshCube(cube);
        InspectMove result = calculateCenters4x4.getMoveToJoin(1,'w');
        InspectMove expected = new InspectMove("U");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void call_getMoveToJoin_from_side1_should_return_Udouble(){
        cube.makeMovesUsingString("Rw2 D2 Rw2 Y2");
        calculateCenters4x4.refreshCube(cube);
        InspectMove result = calculateCenters4x4.getMoveToJoin(1,'w');
        InspectMove expected = new InspectMove("U2");
        Assertions.assertEquals(expected,result);
    }

    /*****************************************************************/

    @Test
    public void call_calculateMovesToJoinFromSourceSideToDestinationSide_join_0_side_with_1(){
        cube.makeMovesUsingString("Rw2 D2 Rw2");
        calculateCenters4x4.refreshCube(cube);
        //when
        ArrayList<InspectMove> result = calculateCenters4x4.calculateMovesToJoinFromSourceSideToDestinationSide(1,0,'w');
        //then
        cube.makeMoves(result);
        interpretation4x4.interpretCenters(cube);
        int countWhiteFields = interpretation4x4.countFieldWithGivenColor(0,'w');

        Assertions.assertEquals(4,countWhiteFields);
    }

    @Test
    public void call2_calculateMovesToJoinFromSourceSideToDestinationSide_join_0_side_with_1(){
        cube.makeMovesUsingString("Rw2 D2 Rw2 D2");
        calculateCenters4x4.refreshCube(cube);
        //when
        ArrayList<InspectMove> result = calculateCenters4x4.calculateMovesToJoinFromSourceSideToDestinationSide(1,0,'w');
        //then
        cube.makeMoves(result);
        interpretation4x4.interpretCenters(cube);
        int countWhiteFields = interpretation4x4.countFieldWithGivenColor(0,'w');

        Assertions.assertEquals(4,countWhiteFields);
    }

    @Test
    public void call3_calculateMovesToJoinFromSourceSideToDestinationSide_join_0_side_with_1(){
        cube.makeMovesUsingString("Rw2 D2 Rw2 U2");
        calculateCenters4x4.refreshCube(cube);
        //when
        ArrayList<InspectMove> result = calculateCenters4x4.calculateMovesToJoinFromSourceSideToDestinationSide(1,0,'w');
        //then
        cube.makeMoves(result);
        interpretation4x4.interpretCenters(cube);
        int countWhiteFields = interpretation4x4.countFieldWithGivenColor(0,'w');

        Assertions.assertEquals(4,countWhiteFields);
    }

    @Test
    public void call4_calculateMovesToJoinFromSourceSideToDestinationSide_join_0_side_with_1(){
        cube.makeMovesUsingString("Rw' d Rw' U");
        calculateCenters4x4.refreshCube(cube);
        //when
        ArrayList<InspectMove> result = calculateCenters4x4.calculateMovesToJoinFromSourceSideToDestinationSide(1,0,'w');
        //then
        cube.makeMoves(result);
        interpretation4x4.interpretCenters(cube);
        int countWhiteFields = interpretation4x4.countFieldWithGivenColor(0,'w');

        Assertions.assertEquals(3,countWhiteFields);
    }

    @Test
    public void call4_calculateMovesToJoinFromSourceSideToDestinationSide_join_0_side_with_4(){
        cube.makeMovesUsingString("Rw'");
        calculateCenters4x4.refreshCube(cube);
        //when
        ArrayList<InspectMove> result = calculateCenters4x4.calculateMovesToJoinFromSourceSideToDestinationSide(4,0,'w');
        //then
        cube.makeMoves(result);
        interpretation4x4.interpretCenters(cube);
        int countWhiteFields = interpretation4x4.countFieldWithGivenColor(0,'w');

        Assertions.assertEquals(4,countWhiteFields);
    }

}
