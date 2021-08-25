package methods.LBLs;


import DTOs.InspectMove;
import calculations.CalculateEdges3x3;
import cubes.Cube;
import cubes.Cube3x3;
import interpretations.Interpretation3x3Edges;

import java.util.ArrayList;

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

    public ArrayList<InspectMove> solveCross(char firstCenterColor){
        ArrayList<InspectMove> tempAlg = new ArrayList<>();
        int i=0;
        int side;
        while(!interpretationEdges.isSolvedCross(firstCenterColor)) {
            side = i + 2;
            char[] colors = interpretationEdges.getColorFromAllEdgesFromGivenSide(side);
            if (interpretationEdges.countFieldsWithGivenColor(firstCenterColor, colors) > 0) {
                tempAlg.addAll(recursiveSolveCross(firstCenterColor, side, side));
            }
            i = (i+1)% 4;
        }
        return null;
    }

    public ArrayList<InspectMove> recursiveSolveCross(char crossColor,int beginSide, int actualSide){
        ArrayList<InspectMove> tempAlg = new ArrayList<>();
        if(interpretationEdges.isCollisionWithDifferentSide(actualSide,crossColor)){
            int nextCollisionSide = interpretationEdges.getSideWhichHaveCollisionWithGivenSide(actualSide, crossColor);
            if(nextCollisionSide!=beginSide){
                tempAlg.addAll(recursiveSolveCross(crossColor, beginSide,nextCollisionSide));
            }
        }
        //calculate
        int sideEdgeNumber = interpretationEdges.getEdgeIndexFromSideWithGivenColorOnCircumference(actualSide,crossColor);
        if(sideEdgeNumber<0){
            sideEdgeNumber = interpretationEdges.getEdgeIndexFromSideWithGivenColorOnInnerSide(actualSide,crossColor);
        }
        int edge = interpretationEdges.getIndexesOfEdgesOnGivenSide(actualSide)[sideEdgeNumber];
       calculateEdges.getMovesToJoinEdgeToCross(actualSide,sideEdgeNumber, crossColor);

        return null;

    }

}
