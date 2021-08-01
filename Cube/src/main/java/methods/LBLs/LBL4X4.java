package methods.LBLs;


import DTOs.InspectMove;
import calculations.CalculateCenters4x4;
import cubes.Cube;
import interpretations.Interpretation4x4;

import java.util.ArrayList;

public class LBL4X4 implements LBL {

    private Cube cube;
    private Cube oriStateCube;
    private Interpretation4x4 interpretation;
    private CalculateCenters4x4 calculation;

    public LBL4X4(Cube cube){
        interpretation = new Interpretation4x4();
        calculation = new CalculateCenters4x4();
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
        restoreCube();
        //...
        return null;
    }

    private void restoreCube(){
        cube = oriStateCube;
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
        algorithm.add(calculation.getMoveToSetGivenSideOnFrontExceptBottomAndUpperSide(source));
        //calculate alg to prepare both sides to join
        algorithm.addAll(calculation.calculateMovesToPrepareJoining(source, dest,color));
        //join
        algorithm.addAll(calculation.calculateMovesToJoinFromSourceSideToDestinationSide(source,dest,color));
        return algorithm;
    }

    public void updateCubeAndInterpretationAndCalculation( ArrayList<InspectMove> alg){
        cube.makeMoves(alg);
        interpretation.interpretCenters(cube);
        calculation.refreshCube(cube);
    }

    public ArrayList<InspectMove> solveFirstCenter(){
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        ArrayList<InspectMove> alg_solve_center = new ArrayList<>();

        algorithm.addAll(rotateCubeToGetMaxNumOfColoredFieldOnCenter('w'));
        updateCubeAndInterpretationAndCalculation(algorithm);
        while(interpretation.countFieldWithGivenColor(0,'w')<4){
            alg_solve_center.clear();
            int sideWithWhiteField = interpretation.inWhichSideIsGivenColorFieldsExceptUpperSide('w');
            alg_solve_center = setupFieldAndJoinToDestSide(sideWithWhiteField,0,'w');
            algorithm.addAll(alg_solve_center);
            updateCubeAndInterpretationAndCalculation(alg_solve_center);
        }
        return algorithm;
    }


}
