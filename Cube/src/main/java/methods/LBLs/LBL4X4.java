package methods.LBLs;


import DTOs.InspectMove;
import calculations.CalculateCenters4x4;
import cubes.Cube;
import cubes.Cube4x4;
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
        this.cube = new Cube4x4(cube.getCube());
        this.oriStateCube = new Cube4x4(cube.getCube());
    }

    /** Here is implementation of LBL for 4x4
     *
     * @return series of moves to solve cube
     */
    @Override
    public String solve(){
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        algorithm.addAll(solveCenters());
        //...
        return null;
    }

    public ArrayList<InspectMove> solveCenters(){
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        algorithm.addAll(solveFirstCenter());
        algorithm.addAll(solveSecondCenter());
        algorithm.addAll(solveThirdCenter());
        algorithm.addAll(solveFourthCenter());
        algorithm.addAll(solveLastTwoCenters());
        return algorithm;
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
        ArrayList<InspectMove> partial_alg = new ArrayList<>();
        interpretation.interpretCenters(cube);
        //calculate alg to rotate to prepare to join field into upper side
        partial_alg.add(calculation.getMoveToSetGivenSideOnFrontExceptBottomAndUpperSide(source));
        addToAlgorithmAndUpdateCubeStuff(partial_alg, algorithm);
        source = interpretation.inWhichSideIsGivenColorFieldsExceptUpperSide(color);
        //calculate alg to prepare both sides to join
        partial_alg = calculation.calculateMovesToPrepareJoining(source, dest,color);
        addToAlgorithmAndUpdateCubeStuff(partial_alg, algorithm);
        //join
        partial_alg=calculation.calculateMovesToJoinFromSourceSideToDestinationSide(source,dest,color);
        addToAlgorithmAndUpdateCubeStuff(partial_alg, algorithm);
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
        }
        System.out.println("To solve first center: " );
        interpretation.printAlgorithm(algorithm);
        return algorithm;
    }

    public ArrayList<InspectMove> solveSecondCenter(){
        interpretation.interpretCenters(cube);
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        ArrayList<InspectMove> alg_solve_center = new ArrayList<>();
       // char secondCenterColor = interpretation.col
        algorithm.add(new InspectMove("X2"));
        updateCubeAndInterpretationAndCalculation(algorithm);
        while(interpretation.countFieldWithGivenColor(0,'y')<4){
            alg_solve_center.clear();
            int sideWithWhiteField = interpretation.inWhichSideIsGivenColorFieldsExceptUpperSide('y');
            alg_solve_center = setupFieldAndJoinToDestSide(sideWithWhiteField,0,'y');
            algorithm.addAll(alg_solve_center);
        }
        System.out.println("To solve second center: " );
        interpretation.printAlgorithm(algorithm);
        return algorithm;
    }

    public void addToAlgorithmAndUpdateCubeStuff(ArrayList<InspectMove> algToSet, ArrayList<InspectMove> finalAlg){
        updateCubeAndInterpretationAndCalculation(algToSet);
        finalAlg.addAll(algToSet);
    }

    public ArrayList<InspectMove> solveThirdCenter(){

        ArrayList<InspectMove> temp_alg = new ArrayList<>();
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        interpretation.interpretCenters(cube);
        temp_alg.add(new InspectMove("Z"));
        addToAlgorithmAndUpdateCubeStuff(temp_alg,algorithm);
        temp_alg.clear();
        int numOfDestSide = interpretation.inWhichSideIsTheGreatestAmountOfCentersWithSameColor(new int[]{0, 4, 1, 5});
        char colorOfDestSide = interpretation.whichColorIsMostCommonInGivenSide(numOfDestSide);
        temp_alg.add(calculation.rotateSideToGetItOnTopAlgorithm(numOfDestSide));
        addToAlgorithmAndUpdateCubeStuff(temp_alg,algorithm);

        while(!interpretation.isWholeCenterInOneColor(0)){

            int sideWithWhiteField = interpretation.inWhichSideIsGivenColorFieldsExceptUpperSide(colorOfDestSide);
            temp_alg = setupFieldAndJoinToDestSide(sideWithWhiteField,0,colorOfDestSide);
            algorithm.addAll(temp_alg);
            //updateCubeAndInterpretationAndCalculation(alg_solve_center);
        }
        System.out.println("To solve third center: " );
        interpretation.printAlgorithm(algorithm);
        return algorithm;

    }

    public ArrayList<InspectMove> solveFourthCenter(){

        ArrayList<InspectMove> temp_alg = new ArrayList<>();
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        interpretation.interpretCenters(cube);

        int numOfDestSide = 4;
        char colorOfDestSide = interpretation.whichColorIsNextInOrder(numOfDestSide);
        temp_alg.add(calculation.rotateSideToGetItOnTopAlgorithm(numOfDestSide));
        addToAlgorithmAndUpdateCubeStuff(temp_alg,algorithm);

        while(!interpretation.isWholeCenterInOneColor(0)){

            int sideWithWhiteField = interpretation.inWhichSideIsGivenColorFieldsExceptUpperSide(colorOfDestSide);
            temp_alg = setupFieldAndJoinToDestSide(sideWithWhiteField,0,colorOfDestSide);
            algorithm.addAll(temp_alg);
            //updateCubeAndInterpretationAndCalculation(alg_solve_center);
        }
        System.out.println("To solve fourth center: " );
        interpretation.printAlgorithm(algorithm);
        return algorithm;

    }

    public ArrayList<InspectMove> solveLastTwoCenters(){

        ArrayList<InspectMove> temp_alg = new ArrayList<>();
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        interpretation.interpretCenters(cube);

        int numOfDestSide = 4;
        char colorOfDestSide = interpretation.whichColorIsNextInOrder(numOfDestSide);
        temp_alg.add(calculation.rotateSideToGetItOnTopAlgorithm(numOfDestSide));
        addToAlgorithmAndUpdateCubeStuff(temp_alg,algorithm);

        while(!interpretation.isWholeCenterInOneColor(0)){

            int sideWithWhiteField = interpretation.inWhichSideIsGivenColorFieldsExceptUpperSide(colorOfDestSide);
            temp_alg = setupFieldAndJoinToDestSide(sideWithWhiteField,0,colorOfDestSide);
            algorithm.addAll(temp_alg);
            //updateCubeAndInterpretationAndCalculation(alg_solve_center);
        }
        System.out.println("To solve fifth center: " );
        interpretation.printAlgorithm(algorithm);
        return algorithm;

    }

}
