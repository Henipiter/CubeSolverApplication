package cubes;

import DTOs.InspectMove;
import lombok.Getter;

import java.util.ArrayList;
import java.util.logging.Logger;

import static DTOs.MoveEnum.*;
import static DTOs.MoveTypeEnum.*;
import static DTOs.MoveTypeEnum.INVALID;
import static java.util.Arrays.deepEquals;

@Getter
public class Cube3x3 extends Cube{

    char[][] cube = new char[6][8];
    char[] center = new char[]{ 'w','y','o','r','g','b' };
    private Logger logger = Logger.getLogger("Cube3x3");

    public Cube3x3(){
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 8; j++)
                cube[i][j] = center[i];
    }

    public Cube3x3(char[][] cube){
        this.cube = cube;
    }

    public void rotate(boolean clockwise, int side){
        if(clockwise) {
            /* vertexes */
            changeFourFields(cube, side, new int[]{0,5,7,2});
            /* edges */
            changeFourFields(cube, side, new int[]{1,3,6,4});
        }
        else {
            /* vertexes */
            changeFourFields(cube, side, new int[]{0,2,7,5});
            /* edges */
            changeFourFields(cube, side, new int[]{1,4,6,3});
        }
    }

    public void rotateCenter(int[] order){
        char buffer;
        buffer = center[order[0]];
        for (int i = 0; i < 3; i++)
            center[order[i]] = center[order[i + 1]];
        center[order[3]] = buffer;
    }

    public void moveElementary_2(int[] sideOrder,int[][] field){
        char[] buffer = new char[3];
        for (int i = 0; i < 2; i++)
            buffer[i] = cube[sideOrder[0]][field[0][i]];
        for (int i = 0; i < 2; i++)
            cube[sideOrder[0]][field[0][i]] = cube[sideOrder[1]][field[1][i]];
        for (int i = 0; i < 2; i++)
            cube[sideOrder[1]][field[1][i]] = cube[sideOrder[2]][field[0][1 - i]];
        for (int i = 0; i < 2; i++)
            cube[sideOrder[2]][field[0][i]] = cube[sideOrder[3]][field[1][i]];
        for (int i = 0; i < 2; i++)
            cube[sideOrder[3]][field[1][1 - i]] = buffer[i];
    }
    public void moveElementary_3(int[] sideOrder,int[][] field){
        char[] buffer = new char[3];
        for (int i = 0; i < 3; i++)
            buffer[i] = cube[sideOrder[0]][field[1][i]];
        for (int i = 0; i < 3; i++)
            cube[sideOrder[0]][field[1][i]] = cube[sideOrder[1]][field[0][i]];
        for (int i = 0; i < 3; i++)
            cube[sideOrder[1]][field[0][i]] = cube[sideOrder[2]][field[1][2 - i]];
        for (int i = 0; i < 3; i++)
            cube[sideOrder[2]][field[1][i]] = cube[sideOrder[3]][field[0][i]];
        for (int i = 0; i < 3; i++)
            cube[sideOrder[3]][field[0][2 - i]] = buffer[i];
    }

    private void moveM(InspectMove inspectMove){
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                rotateCenter(new int[]{0,4,1,5});
                moveElementary_2(new int[]{ 0,4,1,5 }, new int[][]{ {1,6}, {1,6} });
                break;
            case DOUBLE:
                moveM(new InspectMove(M, SIMPLE));
                moveM(new InspectMove(M, SIMPLE));
                break;
            case SIMPLE:
                rotateCenter(new int[]{ 0,5,1,4 });
                moveElementary_2(new int[]{ 4,0,5,1 }, new int[][]{ {1,6}, {1,6} });
                break;
        }
    }

    private void moveS(InspectMove inspectMove){
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                rotateCenter(new int[]{ 0,3,1,2 });
                moveElementary_2(new int[]{ 0,3,1,2 }, new int[][]{ {3,4},{6,1} });
                break;
            case DOUBLE:
                moveS(new InspectMove(S, SIMPLE));
                moveS(new InspectMove(S, SIMPLE));
                break;
            case SIMPLE:
                rotateCenter( new int[]{ 3,0,2,1 });
                moveElementary_2(new int[]{ 3,0,2,1 }, new int[][]{ {1,6},{3,4} });
                break;
        }
    }

    private void moveE(InspectMove inspectMove){
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                rotateCenter(new int[]{ 2,4,3,5 });
                moveElementary_2(new int[]{ 2,4,3,5 }, new int[][]{ { 3,4 }, { 3,4 } });
                break;
            case DOUBLE:
                moveE(new InspectMove(E, SIMPLE));
                moveE(new InspectMove(E, SIMPLE));
                break;
            case SIMPLE:
                rotateCenter(new int[]{ 4,2,5,3 });
                moveElementary_2(new int[]{ 4,2,5,3 }, new int[][]{ { 3,4 }, { 3,4 } });
                break;
        }
    }

    private void moveU(InspectMove inspectMove){
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                rotate(false, 0);
                moveElementary_3(new int[]{ 4,2,5,3 }, new int[][]{ {0,1,2}, {0,1,2} });
            break;
            case DOUBLE:
                moveU(new InspectMove(U, SIMPLE));
                moveU(new InspectMove(U, SIMPLE));
                break;
            case SIMPLE:
                rotate(true, 0);
                moveElementary_3(new int[]{ 2,4,3,5 }, new int[][]{ {0,1,2}, {0,1,2} });
                break;
        }
    }

    private void moveD(InspectMove inspectMove){
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                rotate(true, 1);
                moveElementary_3(new int[]{ 2,4,3,5 }, new int[][]{ {5,6,7}, {5,6,7} });
            break;
            case DOUBLE:
                moveD(new InspectMove(D, SIMPLE));
                moveD(new InspectMove(D, SIMPLE));
                break;
            case SIMPLE:
                rotate(false, 1);
                moveElementary_3(new int[]{ 4,2,5,3 }, new int[][]{ {5,6,7}, {5,6,7} });
            break;
        }
    }

    private void moveF(InspectMove inspectMove){
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                rotate(false, 4);
                moveElementary_3(new int[]{ 2,0,3,1 }, new int[][]{ {7,6,5}, {2,4,7} });
            break;
            case DOUBLE:
                moveF(new InspectMove(F, SIMPLE));
                moveF(new InspectMove(F, SIMPLE));
                break;
            case SIMPLE:
                rotate(true, 4);
                moveElementary_3(new int[]{ 0,2,1,3 }, new int[][]{ {2,4,7},{7,6,5} });
            break;
        }
    }

    private void moveB(InspectMove inspectMove){
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                rotate(true, 5);
                moveElementary_3(new int[]{ 0,2,1,3 }, new int[][]{ {5,3,0} ,{0,1,2} });
            break;
            case DOUBLE:
                moveB(new InspectMove(B, SIMPLE));
                moveB(new InspectMove(B, SIMPLE));
                break;
            case SIMPLE:
                rotate(false, 5);
                moveElementary_3(new int[]{ 2,0,3,1 }, new int[][] { {0,1,2}, {5,3,0} });
            break;
        }
    }

    private void moveR(InspectMove inspectMove){
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                rotate(true, 3);
                moveElementary_3(new int[]{ 4,0,5,1 }, new int[][]{ {2,4,7}, {2,4,7} });
            break;
            case DOUBLE:
                moveR(new InspectMove(R, SIMPLE));
                moveR(new InspectMove(R, SIMPLE));
                break;
            case SIMPLE:
                rotate(false, 3);
                moveElementary_3(new int[]{ 0,4,1,5 }, new int[][]{ {2,4,7}, {2,4,7} });
            break;
        }
    }

    private void moveL(InspectMove inspectMove){
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                rotate(false, 2);
                moveElementary_3(new int[]{ 0,4,1,5 }, new int[][]{ {0,3,5}, {0,3,5} });
            break;
            case DOUBLE:
                moveL(new InspectMove(L, SIMPLE));
                moveL(new InspectMove(L, SIMPLE));
                break;
            case SIMPLE:
                rotate(true, 2);
                moveElementary_3(new int[]{ 4,0,5,1 }, new int[][]{ {0,3,5}, {0,3,5} });
            break;
        }
    }

    private void moveX(InspectMove inspectMove){
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                moveR(new InspectMove(R, PRIM));
                moveM(new InspectMove(M, SIMPLE));
                moveL(new InspectMove(L, SIMPLE));
                break;
            case DOUBLE:
                moveX(new InspectMove(x, SIMPLE));
                moveX(new InspectMove(x, SIMPLE));
                break;
            case SIMPLE:
                moveR(new InspectMove(R, SIMPLE));
                moveM(new InspectMove(M, PRIM));
                moveL(new InspectMove(L, PRIM));
                break;
        }
    }

    private void moveY(InspectMove inspectMove){
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                moveU(new InspectMove(R, PRIM));
                moveE(new InspectMove(M, SIMPLE));
                moveD(new InspectMove(L, SIMPLE));
                break;
            case DOUBLE:
                moveY(new InspectMove(y, SIMPLE));
                moveY(new InspectMove(y, SIMPLE));
                break;
            case SIMPLE:
                moveU(new InspectMove(R, SIMPLE));
                moveE(new InspectMove(M, PRIM));
                moveD(new InspectMove(L, PRIM));
                break;
        }
    }

    private void moveZ(InspectMove inspectMove){
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                moveF(new InspectMove(F, PRIM));
                moveS(new InspectMove(S, PRIM));
                moveB(new InspectMove(B, SIMPLE));
                break;
            case DOUBLE:
                moveZ(new InspectMove(z, SIMPLE));
                moveZ(new InspectMove(z, SIMPLE));
                break;
            case SIMPLE:
                moveF(new InspectMove(F, SIMPLE));
                moveS(new InspectMove(S, SIMPLE));
                moveB(new InspectMove(B, PRIM));
                break;
        }
    }

    private void mover(InspectMove inspectMove){
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                moveR(new InspectMove(R, PRIM));
                moveM(new InspectMove(M, SIMPLE));
                break;
            case DOUBLE:
                moveR(new InspectMove(R, DOUBLE));
                moveM(new InspectMove(M, DOUBLE));
                break;
            case SIMPLE:
                moveR(new InspectMove(R, SIMPLE));
                moveM(new InspectMove(M, PRIM));
                break;
        }
    }

    private void movel(InspectMove inspectMove){
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                moveL(new InspectMove(L, PRIM));
                moveM(new InspectMove(M, PRIM));
                break;
            case DOUBLE:
                moveL(new InspectMove(L, DOUBLE));
                moveM(new InspectMove(M, DOUBLE));
                break;
            case SIMPLE:
                moveL(new InspectMove(R, SIMPLE));
                moveM(new InspectMove(M, SIMPLE));
                break;
        }
    }

    private void movef(InspectMove inspectMove){
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                moveF(new InspectMove(F, PRIM));
                moveS(new InspectMove(S, PRIM));
                break;
            case DOUBLE:
                moveF(new InspectMove(F, DOUBLE));
                moveS(new InspectMove(S, DOUBLE));
                break;
            case SIMPLE:
                moveF(new InspectMove(F, SIMPLE));
                moveS(new InspectMove(S, SIMPLE));
                break;
        }
    }

    private void moveb(InspectMove inspectMove){
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                moveB(new InspectMove(B, PRIM));
                moveS(new InspectMove(S, SIMPLE));
                break;
            case DOUBLE:
                moveB(new InspectMove(B, DOUBLE));
                moveS(new InspectMove(S, DOUBLE));
                break;
            case SIMPLE:
                moveB(new InspectMove(R, SIMPLE));
                moveS(new InspectMove(M, PRIM));
                break;
        }
    }

    private void moveu(InspectMove inspectMove){
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                moveU(new InspectMove(U, PRIM));
                moveE(new InspectMove(E, SIMPLE));
                break;
            case DOUBLE:
                moveU(new InspectMove(U, DOUBLE));
                moveE(new InspectMove(E, DOUBLE));
                break;
            case SIMPLE:
                moveU(new InspectMove(U, SIMPLE));
                moveE(new InspectMove(E, PRIM));
                break;
        }
    }

    private void moved(InspectMove inspectMove){
        switch (inspectMove.getMoveTypeEnum()) {
            case PRIM:
                moveD(new InspectMove(D, PRIM));
                moveE(new InspectMove(E, PRIM));
                break;
            case DOUBLE:
                moveD(new InspectMove(D, DOUBLE));
                moveE(new InspectMove(E, DOUBLE));
                break;
            case SIMPLE:
                moveD(new InspectMove(D, SIMPLE));
                moveE(new InspectMove(E, SIMPLE));
                break;
        }
    }


    @Override
    public void moveUsingString(String direction){
        InspectMove inspectMove = new InspectMove(direction);
        if(inspectMove.getMoveTypeEnum()== INVALID)
            logger.info("Cannot do \""+direction+"\" move");
        else {
            move(inspectMove);
        }

    }

    @Override
    public void move(InspectMove inspectMove){
        // logger.info("Do " + inspectMove.getMove().toString() + inspectMove.getMoveTypeEnum().toString() + " move");
        switch (inspectMove.getMoveEnum()) {
            case M: moveM(inspectMove); break;
            case S: moveS(inspectMove); break;
            case E: moveE(inspectMove); break;
            case U: moveU(inspectMove); break;
            case D: moveD(inspectMove); break;
            case F: moveF(inspectMove); break;
            case B: moveB(inspectMove); break;
            case R: moveR(inspectMove); break;
            case L: moveL(inspectMove); break;
            case x: moveX(inspectMove); break;
            case y: moveY(inspectMove); break;
            case z: moveZ(inspectMove); break;
            case r: mover(inspectMove); break;
            case l: movel(inspectMove); break;
            case f: movef(inspectMove); break;
            case b: moveb(inspectMove); break;
            case u: moveu(inspectMove); break;
            case d: moved(inspectMove); break;
            default:
                logger.info("Cannot do \"" + inspectMove.getMoveEnum().toString() + "\" move");
                break;
        }

    }
    public void makeMoves(ArrayList<InspectMove> algorithm){
        for( InspectMove move : algorithm ){
            move(move);
        }
    }

    public void makeMovesUsingString(String algorithm){
        String[] splitAlg = algorithm.split(" ");
        for( String move : splitAlg){
            moveUsingString(move);
        }
    }

    public char[][] getCube() {
        return cube;
    }

    public void setCube(char[][] cube) {
        this.cube = cube;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Cube3x3)) {
            return false;
        }
        Cube3x3 c = (Cube3x3) o;
        return deepEquals(c.cube,this.cube);
    }
}
