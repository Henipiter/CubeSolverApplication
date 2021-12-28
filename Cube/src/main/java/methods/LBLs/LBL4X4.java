package methods.LBLs;

import DTOs.Algorithm;
import DTOs.InfoType;
import DTOs.Move;
import DTOs.Solution;
import calculations.CalculateCenters4x4;
import calculations.CalculateEdges3x3;
import calculations.CalculateEdges4x4;
import calculations.CalculateMoves;
import cubes.Cube;
import cubes.Cube3x3;
import cubes.Cube4x4;
import interpretations.Interpretation;
import interpretations.Interpretation3x3Vertices;
import interpretations.Interpretation4x4Centers;
import interpretations.Interpretation4x4Edges;

import java.util.ArrayList;
import java.util.Arrays;

public class LBL4X4 implements LBL {

    private final Cube4x4 cube;
    private final Interpretation4x4Centers interpretation4x4Centers;
    private final Interpretation4x4Edges interpretation4x4Edges;
    private final CalculateCenters4x4 calculateCenters4x4;
    private final CalculateEdges4x4 calculateEdges4x4;
    private Cube3x3 cube3x3;
    private final Interpretation interpretation;

    public LBL4X4(Cube cube) {
        interpretation4x4Centers = new Interpretation4x4Centers();
        interpretation4x4Edges = new Interpretation4x4Edges();
        calculateCenters4x4 = new CalculateCenters4x4();
        calculateEdges4x4 = new CalculateEdges4x4((Cube4x4) cube);
        this.cube = (Cube4x4) cube;
        setCenters();
        interpretation = new Interpretation(cube.getCenter());
    }

    @Override
    public ArrayList<Solution> solve(char firstCenterColor) {
        ArrayList<Solution> algorithm = new ArrayList<>();
        algorithm.addAll(solveCenters(firstCenterColor));
        algorithm.addAll(pairAllEdges());
        algorithm.addAll(phase3x3(firstCenterColor));
        return algorithm;
    }

    private void setCenters() {
        Cube3x3 cube3x3 = new Cube3x3(cube);
        Interpretation3x3Vertices interpretation3x3Vertices = new Interpretation3x3Vertices();
        interpretation3x3Vertices.interpretVertices(cube3x3);
        cube.setCenter(interpretation3x3Vertices.analyzeColorOrder());
    }

    public ArrayList<Solution> solveCenters(char firstCenterColor) {
        ArrayList<Solution> algorithm = new ArrayList<>();
        algorithm.add(solveFirstCenter(firstCenterColor));
        algorithm.add(solveSecondCenter());
        algorithm.add(solveThirdCenter());
        algorithm.add(solveFourthCenter());
        algorithm.add(solveLastTwoCenters());
        cube.getLogger().info("Centers solved!");
        return algorithm;
    }

    private ArrayList<Move> rotateCubeToGetMaxNumOfColoredFieldOnCenter(char color) {
        ArrayList<Move> algorithm = new ArrayList<>();
        interpretation4x4Centers.interpretCenters(cube);
        int sideOnTop = interpretation4x4Centers.getSideWithTheMostFieldsWithGivenColor(color);
        algorithm.add(CalculateMoves.rotateSideToGetItOnTopAlgorithm(sideOnTop));
        return algorithm;
    }

    private ArrayList<Move> setupFieldAndJoinToDestSide(int source, int dest, char color) {
        ArrayList<Move> algorithm = new ArrayList<>();
        ArrayList<Move> partial_alg = new ArrayList<>();
        interpretation4x4Centers.interpretCenters(cube);

        partial_alg.add(CalculateMoves.getMoveToSetGivenSideOnFrontExceptBottomAndUpperSide(source));
        addToAlgorithmAndUpdateCubeStuff(partial_alg, algorithm);
        source = interpretation4x4Centers.inWhichSideIsGivenColorFieldsExceptUpperSide(color);

        partial_alg = calculateCenters4x4.calculateMovesToPrepareJoining(source, dest, color);
        addToAlgorithmAndUpdateCubeStuff(partial_alg, algorithm);

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

    public Solution solveFirstCenter(char baseColor) {
        interpretation4x4Centers.interpretCenters(cube);
        ArrayList<Move> algorithm = rotateCubeToGetMaxNumOfColoredFieldOnCenter(baseColor);
        updateCubeAndInterpretationAndCalculation(algorithm);
        getAlgorithmToSolveWholeCenter(algorithm, baseColor, new int[]{4, 1, 2, 3, 5});

        return Solution.center4x4(algorithm, baseColor);
    }

    public Solution solveSecondCenter() {
        interpretation4x4Centers.interpretCenters(cube);
        ArrayList<Move> algorithm = new ArrayList<>();
        char secondCenterColor = interpretation.getColorOfOppositeSide(interpretation4x4Centers.getColorOfCenter(0));
        algorithm.add(new Move("x2"));
        updateCubeAndInterpretationAndCalculation(algorithm);
        getAlgorithmToSolveWholeCenter(algorithm, secondCenterColor, new int[]{4, 2, 3, 5});

        return Solution.center4x4(algorithm, secondCenterColor);
    }

    public void addToAlgorithmAndUpdateCubeStuff(ArrayList<Move> algToSet, ArrayList<Move> finalAlg) {
        updateCubeAndInterpretationAndCalculation(algToSet);
        finalAlg.addAll(algToSet);
    }

    public Solution solveThirdCenter() {
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

        return Solution.center4x4(algorithm, colorOfDestSide);
    }

    public Solution solveFourthCenter() {
        ArrayList<Move> temp_alg = new ArrayList<>();
        ArrayList<Move> algorithm = new ArrayList<>();
        interpretation4x4Centers.interpretCenters(cube);

        int numOfDestSide = 4;
        char colorOnLeft = interpretation4x4Centers.getCenterArrayList().get(2).getColor()[0];
        char colorOnUp = interpretation4x4Centers.getCenterArrayList().get(0).getColor()[0];
        char colorOfDestSide = interpretation.whichColorIsNextInOrder(numOfDestSide, colorOnLeft, colorOnUp);
        temp_alg.add(CalculateMoves.rotateSideToGetItOnTopAlgorithm(numOfDestSide));
        addToAlgorithmAndUpdateCubeStuff(temp_alg, algorithm);
        getAlgorithmToSolveWholeCenter(algorithm, colorOfDestSide, new int[]{4, 1});

        return Solution.center4x4(algorithm, colorOfDestSide);
    }

    public Solution solveLastTwoCenters() {
        ArrayList<Move> temp_alg = new ArrayList<>();
        ArrayList<Move> algorithm = new ArrayList<>();
        interpretation4x4Centers.interpretCenters(cube);

        int numOfDestSide = 4;
        char colorOnLeft = interpretation4x4Centers.getCenterArrayList().get(2).getColor()[0];
        char colorOnUp = interpretation4x4Centers.getCenterArrayList().get(0).getColor()[0];
        char colorOfDestSide = interpretation.whichColorIsNextInOrder(numOfDestSide, colorOnLeft, colorOnUp);
        temp_alg.add(CalculateMoves.rotateSideToGetItOnTopAlgorithm(numOfDestSide));
        addToAlgorithmAndUpdateCubeStuff(temp_alg, algorithm);
        getAlgorithmToSolveWholeCenter(algorithm, colorOfDestSide, new int[]{5});

        return Solution.center4x4(algorithm, colorOfDestSide);
    }

    public void getAlgorithmToSolveWholeCenter(ArrayList<Move> algorithm, char colorOfDestSide, int[] sideWhereSearchFields) {
        ArrayList<Move> temp_alg;
        while (interpretation4x4Centers.getCenterArrayList().get(0).getColor()[0] != colorOfDestSide ||
                !interpretation4x4Centers.isWholeCenterInOneColor(0)) {
            int sideWithWhiteField = interpretation4x4Centers.getSideWithTheMostFieldsWithGivenColorFromGivenSides(colorOfDestSide, sideWhereSearchFields);
            temp_alg = setupFieldAndJoinToDestSide(sideWithWhiteField, 0, colorOfDestSide);
            algorithm.addAll(temp_alg);
        }
    }

    public ArrayList<Solution> pairAllEdges() {
        ArrayList<Solution> solution = new ArrayList<>();
        ArrayList<Move> temp_alg;
        int firstEdgePairIndex;
        int secondEdgePairIndex;
        interpretation4x4Edges.interpretEdges(cube);
        Solution solutionLBL;
        while (!interpretation4x4Edges.isAllEdgesArePaired()) {
            solutionLBL = new Solution(InfoType.INDEX);
            firstEdgePairIndex = 14;
            if (interpretation4x4Edges.isChosenEdgeIsPaired(14)) {
                firstEdgePairIndex = interpretation4x4Edges.getUnpairedPairEdgeIndex();
                temp_alg = calculateEdges4x4.getMovesToPutUnpairedEdgeOn14or15Index(firstEdgePairIndex);
                updateCubeAndSaveAlgorithm(temp_alg, solutionLBL.getAlgorithm());
            }
            secondEdgePairIndex = interpretation4x4Edges.
                    getEdgeIndexWithTheSameColorsLikeInGivenEdge(14);
            temp_alg = calculateEdges4x4.getMovesToPutUnpairedEdgeOn12Index(secondEdgePairIndex);
            temp_alg.addAll(CalculateEdges4x4.getAlgorithmToJoinEdges());
            updateCubeAndSaveAlgorithm(temp_alg, solutionLBL.getAlgorithm());
            solutionLBL.setBeginIndex(new ArrayList<>(Arrays.asList(firstEdgePairIndex, secondEdgePairIndex)));
            solution.add(solutionLBL);
        }
        cube.getLogger().info("Edges paired!");
        return solution;
    }

    private void makeOllParityOn3x3() {
        char buffer;
        buffer = cube3x3.getCube()[4][1];
        cube3x3.getCube()[4][1] = cube3x3.getCube()[0][6];
        cube3x3.getCube()[0][6] = buffer;
    }

    private void makePllParityOn3x3() {
        char buffer;
        buffer = cube3x3.getCube()[4][1];
        cube3x3.getCube()[4][1] = cube3x3.getCube()[5][1];
        cube3x3.getCube()[5][1] = buffer;
    }

    private Solution resolveOLLParity() {
        ArrayList<Move> alg = new ArrayList<>();
        alg.add(prepareBeforeOllParity());
        alg.addAll(Algorithm.getPermAlg("OLL"));
        cube.getLogger().info("OLL Parity");
        makeOllParityOn3x3();
        cube3x3.makeMoves("R2 B2 U2 L U2 R' U2 R U2 F2 R F2 L' B2 R2");
        return Solution.parity(alg, "OLL Parity",
                new ArrayList<>(Arrays.asList(4, 5)),
                new ArrayList<>(Arrays.asList(4, 5)));
    }

    private Move prepareBeforeOllParity() {
        CalculateEdges3x3 calculateEdges3x3 = new CalculateEdges3x3(cube3x3);
        calculateEdges3x3.refreshCube(cube3x3);
        return calculateEdges3x3.moveUpperCrossToRightPositionBeforeOllParity();
    }

    private ArrayList<Solution> resolvePllParity(LBL3X3 lbl3X3) {
        ArrayList<Solution> algorithm = new ArrayList<>();
        cube.getLogger().info("PLL Parity");
        ArrayList<Move> alg = Algorithm.getPermAlg("PLL");
        algorithm.add(Solution.parity(alg, "PLL Parity",
                new ArrayList<>(Arrays.asList(0, 1, 4, 5)),
                new ArrayList<>(Arrays.asList(0, 1, 4, 5))));
        makePllParityOn3x3();
        algorithm.add(lbl3X3.solveIncorrectUpperCross());
        algorithm.add(lbl3X3.solveNotPermutedVertexes());
        return algorithm;
    }

    public ArrayList<Solution> phase3x3(char firstCenterColor) {
        ArrayList<Solution> algorithm = new ArrayList<>();
        cube3x3 = new Cube3x3(cube);
        LBL3X3 lbl3X3 = new LBL3X3(cube3x3);
        algorithm.addAll(lbl3X3.solveF2L_LBL(firstCenterColor));
        try {
            lbl3X3.checkOllParity();
        } catch (Exception exception) {
            cube.getLogger().info("OLL Parity");
            algorithm.add(resolveOLLParity());
        }
        algorithm.add(lbl3X3.solveUpperCross());
        algorithm.add(lbl3X3.solveIncorrectUpperCross());
        algorithm.add(lbl3X3.solveNotPermutedVertexes());
        try {
            lbl3X3.checkPllParity();
        } catch (Exception exception) {
            cube.getLogger().info("PLL Parity");
            algorithm.addAll(resolvePllParity(lbl3X3));
        }
        algorithm.add(lbl3X3.solveNotOrientedVertexes());
        return algorithm;
    }
}
