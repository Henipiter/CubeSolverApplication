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
        cube.makeMoves("R U L D R");
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
    @CsvSource({"2,0,0,true","2,0,1,false","2,3,0,false","2,3,1,true",
            "2,1,0,false","2,1,1,true","2,2,0,true","2,2,1,false",
            "3,0,0,true","3,0,1,false","3,3,0,false","3,3,1,true",
            "3,1,0,false","3,1,1,true","3,2,0,true","3,2,1,false",
            "4,0,0,true","4,0,1,false","4,3,0,true","4,3,1,false",
            "4,1,0,true","4,1,1,false","4,2,0,true","4,2,1,false",
            "5,0,0,true","5,0,1,false","5,3,0,true","5,3,1,false",
            "5,1,0,true","5,1,1,false","5,2,0,true","5,2,1,false"})
    public void call_isFieldOnCircumference_should_return_expected_values(
            int side, int edge, int field, boolean expected){
        Assertions.assertAll(()->Assertions.assertEquals(
                expected,interpretation3x3Edges.isFieldOnCircumference(side,edge,field)));

    }


    @ParameterizedTest
    @CsvSource({"2,3,0","2,7,1","2,4,3","2,11,2",
            "3,1,0","3,5,1","3,6,3","3,9,2",
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
        cube.makeMoves(alg);
        interpretation3x3Edges.interpretEdges(cube);
        Assertions.assertEquals(expected,interpretation3x3Edges.isSolvedCross());
    }

    @ParameterizedTest
    @CsvSource({"R,3, false","R,4, true","R',3,false","R',5,true",
            "L',2, false","L',4, true","L,2,false","L,5,true"})
    public void isCollisionWithDifferentSide(String alg,int side ,boolean expected){
        cube.makeMoves(alg);
        interpretation3x3Edges.interpretEdges(cube);
        Assertions.assertEquals(expected,interpretation3x3Edges.isCollisionWithDifferentSide(side,'y'));
    }

    @ParameterizedTest
    @CsvSource({"R,4, 3","R',5,3","L',4, 2","L,5,2"})
    public void getSideWhichHaveCollisionWithGivenSide(String alg,int side ,int expected){
        cube.makeMoves(alg);
        interpretation3x3Edges.interpretEdges(cube);
        Assertions.assertEquals(expected,interpretation3x3Edges.getSideWhichHaveCollisionWithGivenSide(side,'y'));
    }

    @Test
    public  void call_getColorOnCircumferenceFromGivenSide(){
        cube.makeMoves("F B U D");
        interpretation3x3Edges.interpretEdges(cube);
        char[] expected= new char[]{'r','b','r','g'};
        char[] result = interpretation3x3Edges.getColorOnCircumferenceFromGivenSide(3);
        Assert.assertArrayEquals(expected,result);
    }

    @Test
    public  void getColorOnInnerSideFromGivenSide(){
        cube.makeMoves("F B U D");
        interpretation3x3Edges.interpretEdges(cube);
        char[] expected= new char[]{'b','y','g','w'};
        char[] result = interpretation3x3Edges.getColorOnInnerSideFromGivenSide(3);
        Assert.assertArrayEquals(expected,result);
    }

    @Test
    public  void getColorFromAllEdgesFromGivenSide(){
        cube.makeMoves("F B U D");
        interpretation3x3Edges.interpretEdges(cube);
        char[] expected= new char[]{'r','b','r','g','b','y','g','w'};
        char[] result = interpretation3x3Edges.getColorFromAllEdgesFromGivenSide(3);
        Assert.assertArrayEquals(expected,result);
    }

    @Test
    public void countFieldsWithGivenColor(){
        char[] colors= new char[]{'r','g','r','b','b','w','g','y'};
        Assertions.assertEquals(2,interpretation3x3Edges.countFieldsWithGivenColor('r', colors));
    }



    @ParameterizedTest
    @CsvSource({"D,true", "R D R' D' R, false", "M2 U2 M2, false" })
    void call_isCrossInCorrectOrder(String alg, boolean expected){
        cube.makeMoves(alg);
        interpretation3x3Edges.interpretEdges(cube);
        boolean result = interpretation3x3Edges.isCrossInCorrectOrder('y');
        Assertions.assertEquals(expected,result);
    }

    @ParameterizedTest
    @CsvSource({"D,0", "D2 D2,4", "R D R' D' R, 2", "R D R' D' R D, 1","M2 U2 M2, 2" })
    void call_countEdgesPairedWithCenters(String alg, int expected){
        cube.makeMoves(alg);
        interpretation3x3Edges.interpretEdges(cube);
        int result = interpretation3x3Edges.countEdgesPairedWithCenters();
        Assertions.assertEquals(expected,result);
    }

    @ParameterizedTest
    @CsvSource({"R R', true","R U R' F R' F' R, false" })
    void call_isSecondLayerComplete(String alg, boolean expected){
        cube.makeMoves(alg);
        interpretation3x3Edges.interpretEdges(cube);
        boolean result = interpretation3x3Edges.isSecondLayerComplete();
        Assertions.assertEquals(expected,result);
    }
}