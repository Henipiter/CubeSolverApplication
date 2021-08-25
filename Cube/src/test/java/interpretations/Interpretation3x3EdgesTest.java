package interpretations;


import DTOs.Edge;
import cubes.Cube3x3;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

class Interpretation3x3EdgesTest {

    Interpretation3x3Edges interpretation3x3Edges = new Interpretation3x3Edges();
    Cube3x3 cube;

    @BeforeEach
    public void init() {
        cube = new Cube3x3();
    }

    @Test
    public void call_interpretEdges() {
        //given
        cube.makeMovesUsingString("R U L D R");
        interpretation3x3Edges.interpretEdges(cube);
        char[][] expected = new char[][]{
                {'w', 'o'}, {'y', 'r'}, {'g', 'r'}, {'b', 'o'},
                {'o', 'y'}, {'b', 'w'}, {'g', 'y'}, {'g', 'w'},
                {'b', 'r'}, {'w', 'r'}, {'g', 'o'}, {'y', 'b'}
        };
        //when
        ArrayList<Edge> result = interpretation3x3Edges.getEdgeArrayList();
        //then
        for(int i=0;i<12;i++){
            Assert.assertArrayEquals(expected[i], result.get(i).getColor());
        }
    }

    @ParameterizedTest
    @CsvSource({"2,3,0,true","2,3,1,false","2,4,0,false","2,4,1,true",
            "2,7,0,false","2,7,1,true","2,11,0,true","2,11,1,false",
            "3,1,0,true","3,1,1,false","3,5,0,false","3,5,1,true",
            "3,6,0,false","3,6,1,true","3,9,0,true","3,9,1,false",
            "4,2,0,true","4,2,1,false","4,6,0,true","4,6,1,false",
            "4,7,0,true","4,7,1,false","4,10,0,true","4,10,1,false",
            "5,0,0,true","5,0,1,false","5,4,0,true","5,4,1,false",
            "5,5,0,true","5,5,1,false","5,8,0,true","5,8,1,false"})
    public void call_isFieldOnCircumference_should_return_expected_values(
            int side, int edge, int field, boolean expected){
        Assertions.assertAll(()->Assertions.assertEquals(
                expected,interpretation3x3Edges.isFieldOnCircumference(side,edge,field)));

    }


    @ParameterizedTest
    @CsvSource({"2,3,0","2,7,1","2,4,3","2,11,2",
            "3,1,0","3,6,1","3,5,3","3,9,2",
            "4,2,0","4,6,1","4,7,3","4,10,2",
            "5,0,0","5,4,1","5,5,3","5,8,2" })
    public void call_getSideEdgeNumber_should_return_expected_values(
            int side, int edge, int expected){
        Assertions.assertAll(()->Assertions.assertEquals(
                expected,interpretation3x3Edges.getSideEdgeNumber(side,edge)));

    }

    @ParameterizedTest
    @CsvSource({"R, false","R D R' D' R, true" })
    public void call_isSolvedCross_should_return_expected_values(String alg, boolean expected){
        cube.makeMovesUsingString(alg);
        interpretation3x3Edges.interpretEdges(cube);
        Assertions.assertEquals(expected,interpretation3x3Edges.isSolvedCross('y'));
    }

    @ParameterizedTest
    @CsvSource({"R,3, false","R,4, true","R',3,false","R',5,true",
            "L',2, false","L',4, true","L,2,false","L,5,true"})
    public void isCollisionWithDifferentSide(String alg,int side ,boolean expected){
        cube.makeMovesUsingString(alg);
        interpretation3x3Edges.interpretEdges(cube);
        Assertions.assertEquals(expected,interpretation3x3Edges.isCollisionWithDifferentSide(side,'y'));
    }

    @ParameterizedTest
    @CsvSource({"R,4, 3","R',5,3","L',4, 2","L,5,2"})
    public void getSideWhichHaveCollisionWithGivenSide(String alg,int side ,int expected){
        cube.makeMovesUsingString(alg);
        interpretation3x3Edges.interpretEdges(cube);
        Assertions.assertEquals(expected,interpretation3x3Edges.getSideWhichHaveCollisionWithGivenSide(side,'y'));
    }

    @Test
    public  void call_getColorOnCircumferenceFromGivenSide(){
        cube.makeMovesUsingString("F B U D");
        interpretation3x3Edges.interpretEdges(cube);
        char[] expected= new char[]{'r','g','r','b'};
        char[] result = interpretation3x3Edges.getColorOnCircumferenceFromGivenSide(3);
        Assert.assertArrayEquals(expected,result);
    }

    @Test
    public  void getColorOnInnerSideFromGivenSide(){
        cube.makeMovesUsingString("F B U D");
        interpretation3x3Edges.interpretEdges(cube);
        char[] expected= new char[]{'b','w','g','y'};
        char[] result = interpretation3x3Edges.getColorOnInnerSideFromGivenSide(3);
        Assert.assertArrayEquals(expected,result);
    }

    @Test
    public  void getColorFromAllEdgesFromGivenSide(){
        cube.makeMovesUsingString("F B U D");
        interpretation3x3Edges.interpretEdges(cube);
        char[] expected= new char[]{'r','g','r','b','b','w','g','y'};
        char[] result = interpretation3x3Edges.getColorFromAllEdgesFromGivenSide(3);
        Assert.assertArrayEquals(expected,result);
    }

    @Test
    public void countFieldsWithGivenColor(){
        char[] colors= new char[]{'r','g','r','b','b','w','g','y'};
        Assertions.assertEquals(2,interpretation3x3Edges.countFieldsWithGivenColor('r', colors));
    }

    @ParameterizedTest
    @CsvSource({"R, 9","F,10","L,11","B,8"})
    void call_getFreeSlotOnCross(String alg, int expected){
        cube.makeMovesUsingString(alg);
        interpretation3x3Edges.interpretEdges(cube);
        Assertions.assertEquals(expected,interpretation3x3Edges.getFreeSlotOnCross('y'));
    }
}