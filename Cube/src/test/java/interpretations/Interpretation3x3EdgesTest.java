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