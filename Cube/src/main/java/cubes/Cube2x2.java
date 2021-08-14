package cubes;


import DTOs.InspectMove;
import DTOs.MoveTypeEnum;

import java.util.logging.Logger;

import static DTOs.MoveEnum.*;
import static DTOs.MoveTypeEnum.*;
import static java.util.Arrays.deepEquals;

public class Cube2x2 extends Cube {

    char[][] cube = new char[6][4];
    char[] center = new char[]{ 'w','y','o','r','g','b' };
    private Logger logger = Logger.getLogger("Cube2x2");

    public Cube2x2(){
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 4; j++)
                cube[i][j] = center[i];
    }

    public Cube2x2(char[][] cube){
        this.cube = cube;
    }

    /** Rotate fields in chosen side of cube
     *
     * @param clockwise true/false
     * @param side - which side will be rotate
     */
    public void rotateSide(boolean clockwise, int side){
        if(clockwise){
            /* vertexes */
            changeFourFields(cube, side, new char[]{0,2,3,1});
        }
        else{
            /* vertexes */
            changeFourFields(cube, side, new char[]{0,1,3,2});
        }
    }

    /** Rotate fields on 4 sides
     *
     * @param sideOrder order, in which fields are rotating
     * @param field number of field on side
     */
    private void moveElementary(int[] sideOrder, int[][] field){
        char[] buffer = new char[2];
        for (int i = 0; i < 2; i++){
            buffer[i] = cube[sideOrder[0]][field[1][i]];
        }
        for (int i = 0; i < 2; i++)
            cube[sideOrder[0]][field[1][i]] = cube[sideOrder[1]][field[0][i]];
        for (int i = 0; i < 2; i++)
            cube[sideOrder[1]][field[0][i]] = cube[sideOrder[2]][field[1][1 - i]];
        for (int i = 0; i < 2; i++)
            cube[sideOrder[2]][field[1][i]] = cube[sideOrder[3]][field[0][i]];
        for (int i = 0; i < 2; i++)
            cube[sideOrder[3]][field[0][1 - i]] = buffer[i];
    }

    private void moveR(InspectMove inspectMove){
        int[][] field;
        int[] side;
        switch (inspectMove.getMoveType()){
            case PRIM:
                rotateSide(true,3);
                moveElementary( new int[]{4,0,5,1},new int[][]{{1,3},{1,3}});
                break;
            case DOUBLE:
                moveR(new InspectMove(R, SIMPLE));
                moveR(new InspectMove(R, SIMPLE));
                break;
            case SIMPLE:
                rotateSide(false, 3);
                moveElementary(new int[]{ 0,4,1,5 }, new int[][]{ {1,3}, {1,3}} );
                break;
        }
    }

    private void moveL(InspectMove inspectMove){
        int[][] field;
        int[] side;
        switch (inspectMove.getMoveType()){
            case PRIM:
                rotateSide(false,2);
                moveElementary( new int[]{0,4,1,5},new int[][]{{0,2},{0,2}});
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveL(new InspectMove(L, SIMPLE));
                moveL(new InspectMove(L, SIMPLE));
                break;
            case SIMPLE:
                rotateSide(true, 2);
                moveElementary(new int[]{ 4,0,5,1}, new int[][]{ {0,2}, {0,2} });
                break;
        }
    }

    private void moveU(InspectMove inspectMove){
        int[][] field;
        int[] side;
        switch (inspectMove.getMoveType()){
            case PRIM:
                rotateSide(false,0);
                moveElementary( new int[]{4,2,5,3},new int[][]{{0,1},{0,1}});
                break;
            case DOUBLE:
                moveU(new InspectMove(U, SIMPLE));
                moveU(new InspectMove(U, SIMPLE));
                break;
            case SIMPLE:
                rotateSide(true, 0);
                moveElementary(new int[]{ 2,4,3,5}, new int[][]{ {0,1}, {0,1} });
                break;
        }
    }

    private void moveD(InspectMove inspectMove){
        int[][] field;
        int[] side;
        switch (inspectMove.getMoveType()){
            case PRIM:
                rotateSide(true,1);
                moveElementary( new int[]{2,4,3,5},new int[][]{{2,3},{2,3}});
                break;
            case DOUBLE:
                moveD(new InspectMove(D, SIMPLE));
                moveD(new InspectMove(D, SIMPLE));
                break;
            case SIMPLE:
                rotateSide(false, 1);
                moveElementary(new int[]{ 4,2,5,3}, new int[][]{ {2,3}, {2,3} });
                break;
        }
    }

    private void moveF(InspectMove inspectMove){
        int[][] field;
        int[] side;
        switch (inspectMove.getMoveType()){
            case PRIM:
                rotateSide(false,4);
                moveElementary( new int[]{2,0,3,1},new int[][]{{3,2},{1,3}});
                break;
            case DOUBLE:
                moveF(new InspectMove(F, SIMPLE));
                moveF(new InspectMove(F, SIMPLE));
                break;
            case SIMPLE:
                rotateSide(true, 4);
                moveElementary(new int[]{ 0,2,1,3 }, new int[][]{ {1,3}, {3,2} });
                break;
        }
    }

    private void moveB(InspectMove inspectMove){
        int[][] field;
        int[] side;
        switch (inspectMove.getMoveType()){
            case PRIM:
                rotateSide(true,5);
                moveElementary( new int[]{0,2,1,3},new int[][]{{2,0},{0,1}});
                break;
            case DOUBLE:
                moveB(new InspectMove(B, SIMPLE));
                moveB(new InspectMove(B, SIMPLE));
                break;
            case SIMPLE:
                rotateSide(false, 5);
                moveElementary(new int[]{ 2,0,3,1 }, new int[][]{ {0,1}, {2,0} });
                break;
        }
    }

    private void moveX(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                moveR(new InspectMove("R'"));
                moveL(new InspectMove("L"));
                break;
            case DOUBLE:
                moveX(new InspectMove(X, SIMPLE));
                moveX(new InspectMove(X, SIMPLE));
                break;
            case SIMPLE:
                moveR(new InspectMove("R"));
                moveL(new InspectMove("L'"));
                break;
        }
    }

    private void moveY(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                moveU(new InspectMove("U'"));
                moveD(new InspectMove("D"));
                break;
            case DOUBLE:
                moveY(new InspectMove(Y, SIMPLE));
                moveY(new InspectMove(Y, SIMPLE));
                break;
            case SIMPLE:
                moveU(new InspectMove("U"));
                moveD(new InspectMove("D'"));
                break;
        }
    }

    private void moveZ(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                moveF(new InspectMove("F'"));
                moveB(new InspectMove("B"));
                break;
            case DOUBLE:
                moveZ(new InspectMove(Z, SIMPLE));
                moveZ(new InspectMove(Z, SIMPLE));
                break;
            case SIMPLE:
                moveF(new InspectMove("F"));
                moveB(new InspectMove("B'"));
                break;
        }
    }

    @Override
    public void moveUsingString(String direction) {
        InspectMove inspectMove = new InspectMove(direction);
        if(inspectMove.getMoveType()== MoveTypeEnum.INVALID)
            logger.info("Cannot do \""+direction+"\" move");
        else{
            move(inspectMove);
        }
    }

    @Override
    public void move(InspectMove inspectMove){
        switch (inspectMove.getMove()){
            case R:
                moveR(inspectMove);
                break;
            case L:
                moveL(inspectMove);
                break;
            case U:
                moveU(inspectMove);
                break;
            case D:
                moveD(inspectMove);
                break;
            case F:
                moveF(inspectMove);
                break;
            case B:
                moveB(inspectMove);
                break;
            case X:
                moveX(inspectMove);
                break;
            case Y:
                moveY(inspectMove);
                break;
            case Z:
                moveZ(inspectMove);
                break;
            default:
                logger.info("Cannot do \""+inspectMove.getMove().toString()+"\" move");
                break;
        }

    }


    public void setCube(char[][] cube) {
        this.cube = cube;
    }

    public char[][] getCube() {
        return cube;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Cube2x2)) {
            return false;
        }
        Cube2x2 c = (Cube2x2) o;
        return deepEquals(c.cube,this.cube);
    }
}
