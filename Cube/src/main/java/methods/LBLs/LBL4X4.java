package methods.LBLs;


import DTOs.InspectMove;
import calculations.CalculateCenters4x4;
import cubes.Cube;
import interpretations.Interpretation4x4;

import javax.print.DocFlavor;
import java.util.ArrayList;

public class LBL4X4 implements LBL {

    private Cube cube;
    private Cube oriStateCube;
    private Interpretation4x4 interpretation;
    private CalculateCenters4x4 calculation;

    public LBL4X4(Cube cube){
        interpretation = new Interpretation4x4();
        this.cube = cube;
        this.oriStateCube = cube;
    }

    /** Here is implementation of LBL for 4x4
     *
     * @return series of moves to solve cube
     */
    @Override
    public String solve(){
        solveFirstCenter();
        return null;
    }

    private ArrayList<InspectMove> rotateCubeToGetMaxNumOfColoredFieldOnCenter(char color){
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        //interpret cube
        interpretation.interpretCenters(cube);
        //find side to rotate cube in right position
        int sideOnTop = interpretation.inWhichSideIsTheMostWhiteFields(color);
        //find alg to rotate cube
        algorithm.add(calculation.rotateSideToGetItOnTopAlgorithm(sideOnTop));
        return algorithm;
    }

    private ArrayList<InspectMove> setupFieldAndJoinToDestSide(int source, int dest, char color){

        ArrayList<InspectMove> algorithm = new ArrayList<>();
        interpretation.interpretCenters(cube);
        //calculate alg to rotate to prepare to join field into upper side
        algorithm.add(calculation.getMoveToSetGivenSideOnFront(source));
        //calculate alg to prepare both sides to join
        algorithm.addAll(calculation.calculateMovesToPrepareJoining(source, dest,color));
        //join
        algorithm.addAll(calculation.calculateMovesToJoinFromSourceSideToDestinationSide(source,dest,color));
        return algorithm;
    }

    private ArrayList<InspectMove> solveFirstCenter(){
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        algorithm.addAll(rotateCubeToGetMaxNumOfColoredFieldOnCenter('w'));
        cube.makeMoves(algorithm);
        interpretation.interpretCenters(cube);
        while(interpretation.countFieldWithGivenColor(0,'w')<4){
            int sideWithWhiteField = interpretation.inWhichSideIsGivenColorFieldsExceptUpperSide('w');
            setupFieldAndJoinToDestSide(sideWithWhiteField,0,'w');
        }
        return algorithm;
    }


}
