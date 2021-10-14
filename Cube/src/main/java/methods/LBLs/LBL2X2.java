package methods.LBLs;


import DTOs.InspectMove;
import DTOs.Move;
import DTOs.Vertex;
import calculations.CalculateMoves;
import calculations.CalculateVertices2x2;
import cubes.Cube;
import cubes.Cube2x2;
import cubes.Cube3x3;
import interpretations.Interpretation;
import interpretations.Interpretation1x1;
import interpretations.Interpretation2x2Vertices;
import interpretations.Interpretation3x3Centers;
import parsers.Parse2x2To3x3;

import java.util.ArrayList;

public class LBL2X2 implements LBL {

    private Cube cube;
    private char[] center;

    private Interpretation2x2Vertices interpretation2x2Vertices;
    private CalculateVertices2x2 calculateVertices2x2;

    public LBL2X2(Cube cube) {
        this.cube = cube;
        interpretation2x2Vertices = new Interpretation2x2Vertices();
        calculateVertices2x2 = new CalculateVertices2x2((Cube2x2) cube);
    }

    private void addAlgAndMoveCube( ArrayList<Move> finalAlg,ArrayList<Move> alg){
        cube.makeMoves(alg);
        finalAlg.addAll(alg);
    }


    @Override
    public String solve(char firstCenterColor) {
        ArrayList<Move> algorithm = new ArrayList<>();
        interpretation2x2Vertices.interpretVertices(cube);
        algorithm.add(rotateCubeToGetColorOnBottomSide(firstCenterColor));
        int vertOfBegin = interpretation2x2Vertices.getIndexVertexFromBottomHasColor(firstCenterColor);
        setCentersByVertex(vertOfBegin);

        Parse2x2To3x3 parser = new Parse2x2To3x3(cube);
        Cube3x3 cube3x3 = parser.parseTo3x3();
        LBL3X3 lbl3X3 = new LBL3X3(cube3x3);

        addAlgAndMoveCube(algorithm,lbl3X3.solveFirstLayer());
        addAlgAndMoveCube(algorithm,lbl3X3.solveNotOrientedVertexes());
        System.out.println(InspectMove.moveListToString(algorithm));
        algorithm.addAll(solvePll());


        return InspectMove.moveListToString(algorithm);
    }

    public ArrayList<Move> solvePll() {
        calculateVertices2x2.refreshCube(cube);
        interpretation2x2Vertices.interpretVertices(cube);
        ArrayList<Move> alg = new ArrayList<>();
        alg.add(calculateVertices2x2.moveUpperSideToGetRightPlacedVertex());
        calculateVertices2x2.refreshCube(cube);
        alg.add(calculateVertices2x2.moveBottomSideToGetRightPlacedVertexInCorrectPosition());
        alg.addAll(calculateVertices2x2.permuteVertexAlgorithm());
        cube.makeMoves(calculateVertices2x2.permuteVertexAlgorithm());
        return alg;
    }

    public void setCentersByVertex(int indexVertex){
        char[] centerArray = setBottomLeftBackCenters(indexVertex);
        setUpperRightFrontCenters(centerArray);
        cube.setCenter(centerArray);
    }

    private char[] setUpperRightFrontCenters(char[] centerArray){
        interpretation2x2Vertices.interpretVertices(cube);
        centerArray[0] = Interpretation.getColorOfOppositeSide(centerArray[1]);
        centerArray[3] = Interpretation.getColorOfOppositeSide(centerArray[2]);
        centerArray[4] = Interpretation.getColorOfOppositeSide(centerArray[5]);
        return centerArray;
    }

    private char getLeftCenterColor(int indexVertex, char[] vertexColors){
        switch (indexVertex){
            case 4:
            case 7:
                return vertexColors[1];
            case 5:
            case 6:
                return Interpretation.getColorOfOppositeSide(vertexColors[1]);
        }
        return 'x';
    }

    private char getBackCenterColor(int indexVertex, char[] vertexColors){
        switch (indexVertex){
            case 6:
            case 7:
                return Interpretation.getColorOfOppositeSide(vertexColors[2]);
            case 4:
            case 5:
                return vertexColors[2];
        }
        return 'x';
    }

    private char[] setBottomLeftBackCenters(int indexVertex){
        interpretation2x2Vertices.interpretVertices(cube);
        char[] centerArray = new char[6];
        char[] vertexColors = interpretation2x2Vertices.getVertexArrayList().get(indexVertex).getColor();

        centerArray[1] = interpretation2x2Vertices.getVertexArrayList().get(indexVertex).getColor()[0];
        centerArray[2] = getLeftCenterColor(indexVertex,vertexColors);
        centerArray[5] = getBackCenterColor(indexVertex,vertexColors);
        return centerArray;
    }

    private int getIndexOfChar(char c, char[] order) {
        for (int i = 0; i < order.length; i++) {
            if (order[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public void setCenterColorOrder(Vertex beginVertex, char bottomColor) {
        Interpretation1x1 interpretation1x1 = new Interpretation1x1();
        int fieldWithCenterColor = interpretation2x2Vertices.getFieldWithColor(beginVertex, bottomColor);
        char up = Interpretation.getColorOfOppositeSide(bottomColor);
        char back = beginVertex.getColor()[(fieldWithCenterColor + 1) % 3];
        char[] order = interpretation1x1.getColorOrder(up);
        int startIndex = getIndexOfChar(back, order);
        char right = order[startIndex];
        char left = Interpretation.getColorOfOppositeSide(right);
        char front = Interpretation.getColorOfOppositeSide(back);
        cube.setCenter(new char[]{up, bottomColor, left, right, front, back});
    }

    public int getVertexOfBegin(char color) {
        interpretation2x2Vertices.interpretVertices(cube);
        Vertex candidateVertex;
        for (int i = 7; i >= 0; i--) {
            candidateVertex = interpretation2x2Vertices.getVertexArrayList().get(i);
            if (interpretation2x2Vertices.isVertexHasGivenColor(candidateVertex, color)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Move> rotateCubeToSetOneVertexSolved(char backColor, char bottomColor) {
        ArrayList<Move> alg = new ArrayList<>();
        char frontColor = Interpretation.getColorOfOppositeSide(backColor);
        char upperColor = Interpretation.getColorOfOppositeSide(bottomColor);
        int sideWithFrontColor =
                Interpretation3x3Centers.getCenterNumberWithGivenColor(cube, frontColor);
        int sideWithUpperColor =
                Interpretation3x3Centers.getCenterNumberWithGivenColor(cube, upperColor);
        alg.add(CalculateMoves.rotateSideToGetItOnTopAlgorithm(sideWithUpperColor));
        alg.add(CalculateMoves.getMoveToSetGivenSideOnFrontExceptBottomAndUpperSide(sideWithFrontColor));
        return alg;
    }

    public Move rotateCubeToGetColorOnBottomSide(char bottomColor) {
        interpretation2x2Vertices.interpretVertices(cube);
        ArrayList<Move> moves = InspectMove.stringToMoveList("BLANK x x' z z' x2");
        int indexMove = 1;
        while (!interpretation2x2Vertices.isBottomSideHasColor(bottomColor)) {
            cube.move(InspectMove.getReverseMove(moves.get(indexMove-1)));
            cube.move(moves.get(indexMove));
            interpretation2x2Vertices.interpretVertices(cube);
            indexMove++;
        }
        return moves.get(indexMove-1);
    }

}
