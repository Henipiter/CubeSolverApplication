package methods.LBLs;


import DTOs.InspectMove;
import calculations.CalculateCenters4x4;
import calculations.CalculateEdges4x4;
import calculations.CalculateMoves;
import cubes.Cube;
import cubes.Cube3x3;
import cubes.Cube4x4;
import interpretations.*;
import parsers.Parse3x3To4x4;

import java.util.ArrayList;
import java.util.Arrays;

public class LBL4X4 implements LBL {

    private Cube cube;
    private Cube4x4 beginStateCube;
    private final Interpretation4x4Centers interpretation4x4Centers;
    private final Interpretation4x4Edges interpretation4x4Edges;
    private final CalculateCenters4x4 calculateCenters4x4;
    private final CalculateEdges4x4 calculateEdges4x4;
    private Cube3x3 cube3x3;

    public LBL4X4(Cube cube) {
        interpretation4x4Centers = new Interpretation4x4Centers();
        interpretation4x4Edges = new Interpretation4x4Edges();
        calculateCenters4x4 = new CalculateCenters4x4();
        calculateEdges4x4 = new CalculateEdges4x4((Cube4x4) cube);
        this.cube = cube;// new Cube4x4(cube.getCube());
        this.beginStateCube = new Cube4x4(Arrays.stream(cube.getCube())
                .map(a -> Arrays.copyOf(a, a.length))
                .toArray(char[][]::new));
    }

    private Cube4x4 getCopyOfBeginState() {
        return new Cube4x4(Arrays.stream(beginStateCube.getCube())
                .map(a -> Arrays.copyOf(a, a.length))
                .toArray(char[][]::new));
    }

    /**
     * Here is implementation of LBL for 4x4
     *
     * @return series of moves to solve cube
     */
    @Override
    public String solve(char firstCenterColor) {
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        algorithm.addAll(solveCenters(firstCenterColor));

//        cube = getCopyOfBeginState();
//        cube = getCubeAfterMoves(algorithm);

        System.out.println(InspectMove.algorithmToString(algorithm) + "\n\n");

        algorithm.addAll(pairAllEdges());
        System.out.println(InspectMove.algorithmToString(algorithm) + "\n\n");
//        cube = getCopyOfBeginState();
//        cube = getCubeAfterMoves(algorithm);

        algorithm.addAll(phase3x3(firstCenterColor));
        System.out.println(InspectMove.algorithmToString(algorithm) + "\n\n");

        //TODO
        return InspectMove.algorithmToString(algorithm);
    }

    public ArrayList<InspectMove> solveCenters(char firstCenterColor) {
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        algorithm.addAll(solveFirstCenter(firstCenterColor));
        algorithm.addAll(solveSecondCenter());
        algorithm.addAll(solveThirdCenter());
        algorithm.addAll(solveFourthCenter());
        algorithm.addAll(solveLastTwoCenters());
        return algorithm;
    }

    private ArrayList<InspectMove> rotateCubeToGetMaxNumOfColoredFieldOnCenter(char color) {
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        interpretation4x4Centers.interpretCenters(cube);
        //find side to rotate cube in right position
        int sideOnTop = interpretation4x4Centers.getSideWithTheMostFieldsWithGivenColor(color);
        //find alg to rotate cube
        algorithm.add(CalculateMoves.rotateSideToGetItOnTopAlgorithm(sideOnTop));
        return algorithm;
    }

    private ArrayList<InspectMove> setupFieldAndJoinToDestSide(int source, int dest, char color) {
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        ArrayList<InspectMove> partial_alg = new ArrayList<>();
        interpretation4x4Centers.interpretCenters(cube);
        //calculate alg to rotate to prepare to join field into upper side
        partial_alg.add(CalculateMoves.getMoveToSetGivenSideOnFrontExceptBottomAndUpperSide(source));
        addToAlgorithmAndUpdateCubeStuff(partial_alg, algorithm);
        source = interpretation4x4Centers.inWhichSideIsGivenColorFieldsExceptUpperSide(color);
        //calculate alg to prepare both sides to join
        partial_alg = calculateCenters4x4.calculateMovesToPrepareJoining(source, dest, color);
        addToAlgorithmAndUpdateCubeStuff(partial_alg, algorithm);
        //join
        partial_alg = calculateCenters4x4.calculateMovesToJoinFromSourceSideToDestinationSide(source, dest, color);
        addToAlgorithmAndUpdateCubeStuff(partial_alg, algorithm);
        return algorithm;
    }

    public void updateCubeAndInterpretationAndCalculation(ArrayList<InspectMove> alg) {
        cube.makeMoves(alg);
        interpretation4x4Centers.interpretCenters(cube);
        interpretation4x4Edges.interpretEdges(cube);
        calculateCenters4x4.refreshCube(cube);
    }

    public void updateCubeAndSaveAlgorithm(ArrayList<InspectMove> temp_alg, ArrayList<InspectMove> final_alg) {
        updateCubeAndInterpretationAndCalculation(temp_alg);
        final_alg.addAll(temp_alg);
        temp_alg.clear();
    }

    public ArrayList<InspectMove> solveFirstCenter(char baseColor) {
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        interpretation4x4Centers.interpretCenters(cube);
        algorithm.addAll(rotateCubeToGetMaxNumOfColoredFieldOnCenter(baseColor));
        updateCubeAndInterpretationAndCalculation(algorithm);
        getAlgorithmToSolveWholeCenter(algorithm, baseColor, new int[]{4, 1, 2, 3, 5});
        System.out.println("To solve first center: ");
        interpretation4x4Centers.printAlgorithm(algorithm);
        return algorithm;
    }

    public ArrayList<InspectMove> solveSecondCenter() {
        interpretation4x4Centers.interpretCenters(cube);
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        char secondCenterColor = Interpretation.getColorOfOppositeSide(interpretation4x4Centers.getColorOfCenter(0));
        algorithm.add(new InspectMove("x2"));
        updateCubeAndInterpretationAndCalculation(algorithm);

        getAlgorithmToSolveWholeCenter(algorithm, secondCenterColor, new int[]{4, 2, 3, 5});

        System.out.println("To solve second center: ");
        interpretation4x4Centers.printAlgorithm(algorithm);
        return algorithm;
    }

    public void addToAlgorithmAndUpdateCubeStuff(ArrayList<InspectMove> algToSet, ArrayList<InspectMove> finalAlg) {
        updateCubeAndInterpretationAndCalculation(algToSet);
        finalAlg.addAll(algToSet);
    }

    public ArrayList<InspectMove> solveThirdCenter() {

        ArrayList<InspectMove> temp_alg = new ArrayList<>();
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        interpretation4x4Centers.interpretCenters(cube);
        temp_alg.add(new InspectMove("z"));
        addToAlgorithmAndUpdateCubeStuff(temp_alg, algorithm);
        temp_alg.clear();
        int numOfDestSide = interpretation4x4Centers.inWhichSideIsTheGreatestAmountOfCentersWithSameColor(new int[]{0, 4, 1, 5});
        char colorOfDestSide = interpretation4x4Centers.whichColorIsMostCommonInGivenSide(numOfDestSide);
        temp_alg.add(CalculateMoves.rotateSideToGetItOnTopAlgorithm(numOfDestSide));
        addToAlgorithmAndUpdateCubeStuff(temp_alg, algorithm);

        getAlgorithmToSolveWholeCenter(algorithm, colorOfDestSide, new int[]{4, 1, 5});

        System.out.println("To solve third center: ");
        interpretation4x4Centers.printAlgorithm(algorithm);
        return algorithm;

    }

    public ArrayList<InspectMove> solveFourthCenter() {

        ArrayList<InspectMove> temp_alg = new ArrayList<>();
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        interpretation4x4Centers.interpretCenters(cube);

        int numOfDestSide = 4;
        char colorOnLeft = interpretation4x4Centers.getCenterArrayList().get(2).getColor()[0];
        char colorOnUp = interpretation4x4Centers.getCenterArrayList().get(0).getColor()[0];
        char colorOfDestSide = Interpretation.whichColorIsNextInOrder(numOfDestSide, colorOnLeft, colorOnUp);
        temp_alg.add(CalculateMoves.rotateSideToGetItOnTopAlgorithm(numOfDestSide));
        addToAlgorithmAndUpdateCubeStuff(temp_alg, algorithm);

        getAlgorithmToSolveWholeCenter(algorithm, colorOfDestSide, new int[]{4, 1});

        System.out.println("To solve fourth center: ");
        interpretation4x4Centers.printAlgorithm(algorithm);
        return algorithm;

    }

    public ArrayList<InspectMove> solveLastTwoCenters() {

        ArrayList<InspectMove> temp_alg = new ArrayList<>();
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        interpretation4x4Centers.interpretCenters(cube);

        int numOfDestSide = 4;
        char colorOnLeft = interpretation4x4Centers.getCenterArrayList().get(2).getColor()[0];
        char colorOnUp = interpretation4x4Centers.getCenterArrayList().get(0).getColor()[0];
        char colorOfDestSide = Interpretation.whichColorIsNextInOrder(numOfDestSide, colorOnLeft, colorOnUp);
        temp_alg.add(CalculateMoves.rotateSideToGetItOnTopAlgorithm(numOfDestSide));
        addToAlgorithmAndUpdateCubeStuff(temp_alg, algorithm);

        getAlgorithmToSolveWholeCenter(algorithm, colorOfDestSide, new int[]{5});

        System.out.println("To solve fifth center: ");
        interpretation4x4Centers.printAlgorithm(algorithm);
        return algorithm;
    }

    public void getAlgorithmToSolveWholeCenter(ArrayList<InspectMove> algorithm, char colorOfDestSide, int[] sideWhereSearchFields) {
        ArrayList<InspectMove> temp_alg;
        while (!interpretation4x4Centers.isWholeCenterInOneColor(0)) {
            int sideWithWhiteField = interpretation4x4Centers.getSideWithTheMostFieldsWithGivenColorFromGivenSides(colorOfDestSide, sideWhereSearchFields);
            temp_alg = setupFieldAndJoinToDestSide(sideWithWhiteField, 0, colorOfDestSide);
            algorithm.addAll(temp_alg);
        }
    }

    //////////////////////////////////////////////////////

    public ArrayList<InspectMove> pairAllEdges() {
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        ArrayList<InspectMove> temp_alg;

        interpretation4x4Edges.interpretEdges(cube);

        while (!interpretation4x4Edges.isAllEdgesArePaired()) {
            if (interpretation4x4Edges.isChosenEdgeIsPaired(14)) {
                //get moves to put unpaired edge in 14 or 15 index
                int edgePairIndex = interpretation4x4Edges.getUnpairedPairEdgeIndex();
                temp_alg = calculateEdges4x4.getMovesToPutUnpairedEdgeOn14or15Index(edgePairIndex);
                updateCubeAndSaveAlgorithm(temp_alg, algorithm);
            }
            int edgePairIndex = interpretation4x4Edges.getEdgeIndexWithTheSameColorsLikeInGivenEdge(14);
            temp_alg = calculateEdges4x4.getMovesToPutUnpairedEdgeOn12Index(edgePairIndex);
            temp_alg.addAll(CalculateEdges4x4.getAlgorithmToJoinEdges());
            updateCubeAndSaveAlgorithm(temp_alg, algorithm);

        }
        System.out.println(InspectMove.algorithmToString(algorithm));
        return algorithm;
    }

    private Cube3x3 parseTo3x3(Cube4x4 cube4x4) {
        Parse3x3To4x4 parser = new Parse3x3To4x4(cube4x4);
        try {
            return parser.parseTo3x3();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private Cube4x4 getCubeAfterMoves(ArrayList<InspectMove> alg) {
        Cube4x4 cube4x4 = getCopyOfBeginState();
        cube4x4.makeMoves(alg);
        return cube4x4;
    }

    private Cube3x3 refreshCube3x3(ArrayList<InspectMove> alg) {
        return parseTo3x3(getCubeAfterMoves(alg));
    }

    private void makeOllParityOn3x3() {
        char buffer;
        buffer = cube3x3.getCube()[4][1];
        cube3x3.getCube()[4][1] = cube3x3.getCube()[0][6];
        cube3x3.getCube()[0][6] = buffer;
    }

    private void makePllParityOn3x3(){
        char buffer;
        buffer = cube3x3.getCube()[4][1];
        cube3x3.getCube()[4][1] = cube3x3.getCube()[5][1];
        cube3x3.getCube()[5][1] = buffer;
    }

    public ArrayList<InspectMove> phase3x3(char firstCenterColor) {
        ArrayList<InspectMove> algorithm = new ArrayList<>();
        cube3x3 = parseTo3x3((Cube4x4) cube);
        LBL3X3 lbl3X3 = new LBL3X3(cube3x3);
        algorithm.addAll(lbl3X3.solveCross(firstCenterColor));
        algorithm.addAll(lbl3X3.solveIncorrectCross());
        algorithm.addAll(lbl3X3.solveFirstLayer());
        algorithm.addAll(lbl3X3.solveSecondLayer());

        try {
            lbl3X3.checkOllParity();
        } catch (Exception exception) {
            System.out.println("OLL Parity");
            algorithm.addAll(CalculateEdges4x4.getParityOLLAlgorithm());
            makeOllParityOn3x3();
            cube3x3.makeMovesUsingString("R2 B2 U2 L U2 R' U2 R U2 F2 R F2 L' B2 R2");
            System.out.println(InspectMove.algorithmToString(CalculateEdges4x4.getParityOLLAlgorithm()));
        }
        algorithm.addAll(lbl3X3.solveUpperCross());
        algorithm.addAll(lbl3X3.solveIncorrectUpperCross());
        algorithm.addAll(lbl3X3.solveNotPermutedVertexes());

        try {
            lbl3X3.checkPllParity();

        } catch (Exception exception) {
            System.out.println("PLL Parity");
            algorithm.addAll(CalculateEdges4x4.getParityPLLAlgorithm());
            makePllParityOn3x3();
            algorithm.addAll(lbl3X3.solveIncorrectUpperCross());
            algorithm.addAll(lbl3X3.solveNotPermutedVertexes());
        }

        algorithm.addAll(lbl3X3.solveNotOrientedVertexes());
        return algorithm;
    }



}
