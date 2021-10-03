package methods.LBLs;


import DTOs.InspectMove;
import DTOs.Move;
import calculations.CalculateCenters4x4;
import calculations.CalculateEdges4x4;
import calculations.CalculateMoves;
import cubes.Cube;
import cubes.Cube3x3;
import cubes.Cube4x4;
import interpretations.Interpretation;
import interpretations.Interpretation4x4Centers;
import interpretations.Interpretation4x4Edges;
import parsers.Parse4x4To3x3;

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
        ArrayList<Move> algorithm = new ArrayList<>();
        algorithm.addAll(solveCenters(firstCenterColor));
        algorithm.addAll(pairAllEdges());
        algorithm.addAll(phase3x3(firstCenterColor));
        System.out.println(InspectMove.algorithmToString(algorithm) + "\n\n");
        return InspectMove.algorithmToString(algorithm);
    }

    public ArrayList<Move> solveCenters(char firstCenterColor) {
        ArrayList<Move> algorithm = new ArrayList<>();
        algorithm.addAll(solveFirstCenter(firstCenterColor));
        algorithm.addAll(solveSecondCenter());
        algorithm.addAll(solveThirdCenter());
        algorithm.addAll(solveFourthCenter());
        algorithm.addAll(solveLastTwoCenters());
        return algorithm;
    }

    private ArrayList<Move> rotateCubeToGetMaxNumOfColoredFieldOnCenter(char color) {
        ArrayList<Move> algorithm = new ArrayList<>();
        interpretation4x4Centers.interpretCenters(cube);
        //find side to rotate cube in right position
        int sideOnTop = interpretation4x4Centers.getSideWithTheMostFieldsWithGivenColor(color);
        //find alg to rotate cube
        algorithm.add(CalculateMoves.rotateSideToGetItOnTopAlgorithm(sideOnTop));
        return algorithm;
    }

    private ArrayList<Move> setupFieldAndJoinToDestSide(int source, int dest, char color) {
        ArrayList<Move> algorithm = new ArrayList<>();
        ArrayList<Move> partial_alg = new ArrayList<>();
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

    public void updateCubeAndInterpretationAndCalculation(ArrayList<Move> alg) {
        cube.makeMoves(alg);
        interpretation4x4Centers.interpretCenters(cube);
        interpretation4x4Edges.interpretEdges(cube);
        calculateCenters4x4.refreshCube(cube);
    }

    public void updateCubeAndSaveAlgorithm(ArrayList<Move> temp_alg, ArrayList<Move> final_alg) {
        updateCubeAndInterpretationAndCalculation(temp_alg);
        final_alg.addAll(temp_alg);
        temp_alg.clear();
    }

    public ArrayList<Move> solveFirstCenter(char baseColor) {
        ArrayList<Move> algorithm = new ArrayList<>();
        interpretation4x4Centers.interpretCenters(cube);
        algorithm.addAll(rotateCubeToGetMaxNumOfColoredFieldOnCenter(baseColor));
        updateCubeAndInterpretationAndCalculation(algorithm);
        getAlgorithmToSolveWholeCenter(algorithm, baseColor, new int[]{4, 1, 2, 3, 5});
        System.out.println("To solve first center: ");
        interpretation4x4Centers.printAlgorithm(algorithm);
        return algorithm;
    }

    public ArrayList<Move> solveSecondCenter() {
        interpretation4x4Centers.interpretCenters(cube);
        ArrayList<Move> algorithm = new ArrayList<>();
        char secondCenterColor = Interpretation.getColorOfOppositeSide(interpretation4x4Centers.getColorOfCenter(0));
        algorithm.add(new Move("x2"));
        updateCubeAndInterpretationAndCalculation(algorithm);

        getAlgorithmToSolveWholeCenter(algorithm, secondCenterColor, new int[]{4, 2, 3, 5});

        System.out.println("To solve second center: ");
        interpretation4x4Centers.printAlgorithm(algorithm);
        return algorithm;
    }

    public void addToAlgorithmAndUpdateCubeStuff(ArrayList<Move> algToSet, ArrayList<Move> finalAlg) {
        updateCubeAndInterpretationAndCalculation(algToSet);
        finalAlg.addAll(algToSet);
    }

    public ArrayList<Move> solveThirdCenter() {

        ArrayList<Move> temp_alg = new ArrayList<>();
        ArrayList<Move> algorithm = new ArrayList<>();
        interpretation4x4Centers.interpretCenters(cube);
        temp_alg.add(new Move("z"));
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

    public ArrayList<Move> solveFourthCenter() {

        ArrayList<Move> temp_alg = new ArrayList<>();
        ArrayList<Move> algorithm = new ArrayList<>();
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

    public ArrayList<Move> solveLastTwoCenters() {

        ArrayList<Move> temp_alg = new ArrayList<>();
        ArrayList<Move> algorithm = new ArrayList<>();
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

    public void getAlgorithmToSolveWholeCenter(ArrayList<Move> algorithm, char colorOfDestSide, int[] sideWhereSearchFields) {
        ArrayList<Move> temp_alg;
        while (!interpretation4x4Centers.isWholeCenterInOneColor(0)) {
            int sideWithWhiteField = interpretation4x4Centers.getSideWithTheMostFieldsWithGivenColorFromGivenSides(colorOfDestSide, sideWhereSearchFields);
            temp_alg = setupFieldAndJoinToDestSide(sideWithWhiteField, 0, colorOfDestSide);
            algorithm.addAll(temp_alg);
        }
    }

    //////////////////////////////////////////////////////

    public ArrayList<Move> pairAllEdges() {
        ArrayList<Move> algorithm = new ArrayList<>();
        ArrayList<Move> temp_alg;

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
        Parse4x4To3x3 parser = new Parse4x4To3x3(cube4x4);
        try {
            return parser.parseTo3x3();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
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

    private ArrayList<Move> resolveOLLParity(){
        cube.getLogger().info("OLL Parity");
        makeOllParityOn3x3();
        cube3x3.makeMovesUsingString("R2 B2 U2 L U2 R' U2 R U2 F2 R F2 L' B2 R2");
        return CalculateEdges4x4.getParityOLLAlgorithm();
    }

    private ArrayList<Move> resolvePllParity(LBL3X3 lbl3X3){
        ArrayList<Move> algorithm = new ArrayList<>();
        cube.getLogger().info("PLL Parity");
        algorithm.addAll(CalculateEdges4x4.getParityPLLAlgorithm());
        makePllParityOn3x3();
        algorithm.addAll(lbl3X3.solveIncorrectUpperCross());
        algorithm.addAll(lbl3X3.solveNotPermutedVertexes());
        return algorithm;
    }

    public ArrayList<Move> phase3x3(char firstCenterColor) {
        ArrayList<Move> algorithm = new ArrayList<>();
        cube3x3 = parseTo3x3((Cube4x4) cube);
        cube.setCenter(Parse4x4To3x3.copyCentersColors((Cube4x4)cube));
        LBL3X3 lbl3X3 = new LBL3X3(cube3x3);
        algorithm.addAll(lbl3X3.solveF2L_LBL(firstCenterColor));

        try {
            lbl3X3.checkOllParity();
        } catch (Exception exception) {
            algorithm.addAll(resolveOLLParity());
        }
        algorithm.addAll(lbl3X3.solveUpperCross());
        algorithm.addAll(lbl3X3.solveIncorrectUpperCross());
        algorithm.addAll(lbl3X3.solveNotPermutedVertexes());
        try {
            lbl3X3.checkPllParity();

        } catch (Exception exception) {
            algorithm.addAll(resolvePllParity(lbl3X3));
        }
        algorithm.addAll(lbl3X3.solveNotOrientedVertexes());
        return algorithm;
    }

}
