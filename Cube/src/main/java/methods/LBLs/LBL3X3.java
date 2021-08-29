package methods.LBLs;


import DTOs.InspectMove;
import calculations.CalculateEdges3x3;
import cubes.Cube;
import cubes.Cube3x3;
import interpretations.Interpretation3x3Edges;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class LBL3X3 implements LBL {

    private Cube cube;
    private final Interpretation3x3Edges interpretationEdges;
    private final CalculateEdges3x3 calculateEdges;

    public LBL3X3(Cube cube){
        this.cube = cube;
        interpretationEdges = new Interpretation3x3Edges();
        calculateEdges = new CalculateEdges3x3((Cube3x3)cube);
    }


    @Override
    public String solve(char firstCenterColor){
        firstCenterColor='w';


        return null;
    }

    public void updateCubeAndInterpretationAndCalculation( ArrayList<InspectMove> alg){
        cube.makeMoves(alg);
        interpretationEdges.interpretEdges(cube);
        calculateEdges.refreshCube(cube);
    }

    private char[] removeIrrelevantColors(char[] colors){
        colors[2]='x';
        colors[5]='x';
        colors[7]='x';
        return colors;
    }

    public boolean isOldIterator(int[] order,char color){
        return Arrays.equals(order, interpretationEdges.getOrderSolvingCrossEdges(color));
    }

    public ArrayList<InspectMove> solveCross(char firstCenterColor){
        interpretationEdges.interpretEdges(cube);
        calculateEdges.refreshCube(cube);
        ArrayList<InspectMove> tempAlg = new ArrayList<>();
        int i=0;
        int side;
        int[] order = interpretationEdges.getOrderSolvingCrossEdges(firstCenterColor);

        while(!interpretationEdges.isSolvedCross(firstCenterColor)) {
            side = order[i];

            while (interpretationEdges.countFieldsWithGivenColor(firstCenterColor, removeIrrelevantColors(
                            interpretationEdges.getColorFromAllEdgesFromGivenSide(side))) > 0) {
                tempAlg.addAll(recursiveSolveCross(firstCenterColor, side, side));
            }

            if(isOldIterator(order,firstCenterColor)){
                i= (i+1)%4;
            }
            else{
                order =interpretationEdges.getOrderSolvingCrossEdges(firstCenterColor);
                i=0;
            }
        }
        return tempAlg;
    }

    private ArrayList<InspectMove> recursiveSolveCross(char crossColor,int beginSide, int actualSide){
        ArrayList<InspectMove> recursiveAlg = new ArrayList<>();
        if(interpretationEdges.isCollisionWithDifferentSide(actualSide,crossColor)){
            int nextCollisionSide = interpretationEdges.getSideWhichHaveCollisionWithGivenSide(actualSide, crossColor);
            if(nextCollisionSide!=beginSide){
                recursiveAlg.addAll(recursiveSolveCross(crossColor, beginSide,nextCollisionSide));
            }
        }
        //calculate
        int sideEdgeNumber = interpretationEdges.getEdgeIndexFromSideWithGivenColorOnCircumference(actualSide,crossColor);
        if(sideEdgeNumber<0 || sideEdgeNumber==2){
            sideEdgeNumber = interpretationEdges.getEdgeIndexFromSideWithGivenColorOnInnerSide(actualSide,crossColor);
        }
        ArrayList<InspectMove> tempAlg = calculateEdges.getMovesToJoinEdgeToCross(actualSide,sideEdgeNumber, crossColor);
        updateCubeAndInterpretationAndCalculation(tempAlg);
        recursiveAlg.addAll( tempAlg);
        return recursiveAlg;

    }

}
