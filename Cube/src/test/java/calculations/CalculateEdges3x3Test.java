package calculations;

import DTOs.InspectMove;
import DTOs.MoveEnum;
import DTOs.MoveTypeEnum;
import cubes.Cube3x3;
import interpretations.Interpretation3x3Edges;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

public class CalculateEdges3x3Test {

    Cube3x3 cube;
    Interpretation3x3Edges interpretation3x3Edges;
    CalculateEdges3x3 calculateEdges3x3 ;

    @BeforeEach
    void setUp(){
        cube = new Cube3x3();
        interpretation3x3Edges = new Interpretation3x3Edges();
        calculateEdges3x3 = new CalculateEdges3x3(cube);
    }


    @ParameterizedTest
    @CsvSource({"R,3, BLANK, BLANK", "R,4, D, PRIM","R,5,D,SIMPLE","R,2,D,DOUBLE",
            "L,2, BLANK, BLANK", "L,5, D, PRIM","L,4,D,SIMPLE","L,3,D,DOUBLE",
            "F,4, BLANK, BLANK", "F,3, D, SIMPLE","F,2,D,PRIM","F,5,D,DOUBLE",
            "B,5, BLANK, BLANK", "B,2, D, SIMPLE","B,3,D,PRIM","B,4,D,DOUBLE"})
    void call_getMoveToGetFreeSlotOnGivenSide(String alg, int side, MoveEnum moveEnum, MoveTypeEnum moveTypeEnum){
        //given
        cube.makeMovesUsingString(alg);
        interpretation3x3Edges.interpretEdges(cube);
        calculateEdges3x3.refreshCube(cube);
        InspectMove expected = new InspectMove(moveEnum, moveTypeEnum);
        //when
        InspectMove result = calculateEdges3x3.getMoveToGetFreeSlotOnGivenSide(side,'y');
        //then
        Assertions.assertEquals(expected,result);
    }

    @ParameterizedTest
    @CsvSource({"2,0","2,1","2,2","2,3",
            "3,0","2,3","2,3","2,3",
            "4,0","2,4","2,4","2,4",
            "5,0","2,5","2,5","2,5"})
    void call_getMoveTypeEnumToJoinCircumferenceField(int side, int sideEdge){
        //given
        MoveEnum moveEnum=MoveEnum.BLANK;
        MoveTypeEnum moveTypeEnum=MoveTypeEnum.BLANK;
        switch(side){
            case 2:moveEnum=MoveEnum.L;break;
            case 3:moveEnum=MoveEnum.R;break;
            case 4:moveEnum=MoveEnum.F;break;
            case 5:moveEnum=MoveEnum.B;break;
        }
        switch(sideEdge){
            case 0:moveTypeEnum=MoveTypeEnum.DOUBLE;break;
            case 1:
                moveTypeEnum=MoveTypeEnum.SIMPLE;break;
            case 2:moveTypeEnum=MoveTypeEnum.BLANK; break;
            case 3:moveTypeEnum=MoveTypeEnum.PRIM;  break;
        }
        InspectMove expected = new InspectMove(moveEnum, moveTypeEnum);
        //when
        InspectMove result = calculateEdges3x3.getInspectMoveToJoinCircumferenceField(side,sideEdge);
        //then
        Assertions.assertEquals(expected,result);
    }

    @ParameterizedTest
    @CsvSource({"F, F',4,3","F', F,4,1", "F2, F2,4,0"})
    void getInspectMoveToJoinCircumferenceField(String scramble, String expectedAlg, int side, int sideEdge){
        cube.makeMovesUsingString(scramble);
        interpretation3x3Edges.interpretEdges(cube);
        calculateEdges3x3.refreshCube(cube);
        InspectMove expected = new InspectMove(expectedAlg);
        //when
        InspectMove result = calculateEdges3x3.getInspectMoveToJoinCircumferenceField(side,sideEdge);
        //then
        Assertions.assertEquals(expected,result);
    }


    @ParameterizedTest
    @CsvSource({"M' U M U',F'","M' U M U' F2,F", "M' U M U' R2,F","M' U M U' F2 R2,F'"})
    void getMovesToMoveInnerEdgeOnConflictEdge(String scramble, String expected){
        cube.makeMovesUsingString(scramble);
        interpretation3x3Edges.interpretEdges(cube);
        calculateEdges3x3.refreshCube(cube);
        //when
        InspectMove result = calculateEdges3x3.getMovesToMoveInnerEdgeOnConflictEdge(4,'y');
        //then
        Assertions.assertEquals(expected,result.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "M' U M U' F,D R',3,3", "M' U M U' F R,R2,3,0", "M' U M U' F R2 ,R,3,1",
            "M' U M U' F',D' L,2,1"
    })
    void getMovesToJoinEdgeToCross(String scramble, String expected,int side, int sideEdgeNumber){
        cube.makeMovesUsingString(scramble);
        interpretation3x3Edges.interpretEdges(cube);
        calculateEdges3x3.refreshCube(cube);
        //when
        ArrayList<InspectMove> result = calculateEdges3x3.getMovesToJoinEdgeToCross(side,sideEdgeNumber,'y');
        //then
        Assertions.assertEquals(expected,InspectMove.algorithmToString(result));
    }


    @ParameterizedTest
    @CsvSource({
            "D, PRIM", "D2, DOUBLE", "D', SIMPLE", "D2 D2, BLANK"
    })
    void getMoveToPairCrossEdgesToCenters(String scramble, MoveTypeEnum expected){
        cube.makeMovesUsingString(scramble);
        calculateEdges3x3.refreshCube(cube);
        //when
        InspectMove result = calculateEdges3x3.getMoveToPairCrossEdgesToCenters();
        //then
        Assertions.assertEquals(expected,result.getMoveTypeEnum());
    }

    @ParameterizedTest
    @CsvSource({
            "R D R' D' R", "R D R' D' R D",  "R D R' D' R D2",  "R D R' D' R D'",
            "M2 D2 M2", "M2 D2 M2 D", "M2 D2 M2 D2", "M2 D2 M2 D'"
    })
    void isCorrectCross(String scramble){
        //given
        cube.makeMovesUsingString(scramble);
        calculateEdges3x3.refreshCube(cube);
        //when
        calculateEdges3x3.getMoveToSolveIncorrectOrderCross();
        //then
        interpretation3x3Edges.interpretEdges(cube);
        Assertions.assertTrue(interpretation3x3Edges.isCorrectCross());
    }




}
