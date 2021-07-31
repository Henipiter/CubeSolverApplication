package cubes;

import DTOs.InspectMove;
import DTOs.MoveTypeEnum;
import static DTOs.MoveTypeEnum.*;
import static DTOs.MoveEnum.*;

import java.util.logging.Logger;

public class Cube4x4 extends Cube{

    char[][] cube = new char[6][16];
    char[] center = new char[]{ 'w','y','o','r','g','b' };
    private Logger logger = Logger.getLogger("Cube4x4");


    public Cube4x4(){
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 16; j++)
                cube[i][j] = center[i];
    }

    public Cube4x4(char[][] cube){
        this.cube = cube;
    }


    private void rotateSide(boolean clockwise, int side){
        char buffer;
        char[] fieldsOrder;
        if(clockwise){
            /* vertexes */
            changeFourFields(cube, side, new char[]{0,12,15,3});
            /* edges */
            changeFourFields(cube, side, new char[]{1,8,14,7});
            changeFourFields(cube, side, new char[]{2,4,13,11});
            /* centers */
            changeFourFields(cube, side, new char[]{5,9,10,6});
        }
        else{
            /* vertexes */
            changeFourFields(cube, side, new char[]{0,3,15,12});
            /* edges */
            changeFourFields(cube, side, new char[]{1,7,14,8});
            changeFourFields(cube, side, new char[]{2,11,13,4});
            /* centers */
            changeFourFields(cube, side, new char[]{5,6,10,9});
        }
    }


    private void moveElementary(int[] sideOrder, int[][] field){
        char[] buffer =new char[4];
        for (int i = 0; i < 4; i++)
            buffer[i] = cube[sideOrder[0]][field[1][i]];
        for (int i = 0; i < 4; i++)
            cube[sideOrder[0]][field[1][i]] = cube[sideOrder[1]][field[0][i]];
        for (int i = 0; i < 4; i++)
            cube[sideOrder[1]][field[0][i]] = cube[sideOrder[2]][field[1][3 - i]];
        for (int i = 0; i < 4; i++)
            cube[sideOrder[2]][field[1][i]] = cube[sideOrder[3]][field[0][i]];
        for (int i = 0; i < 4; i++)
            cube[sideOrder[3]][field[0][3 - i]] = buffer[i];
    }

    private void moveR(InspectMove inspectMove){
        switch (inspectMove.getMoveType()) {
            case PRIM:
                rotateSide(true,3);
                moveElementary( new int[]{4,0,5,1},new int[][]{{3,7,11,15},{3,7,11,15}});
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveR(inspectMove);
                moveR(inspectMove);
                break;
            case SIMPLE:
                rotateSide(false, 3);
                moveElementary(new int[]{ 0,4,1,5 }, new int[][]{ {3,7,11,15}, {3,7,11,15} });
                break;
        }
    }

    private void moveRIn(InspectMove inspectMove){
        switch (inspectMove.getMoveType()) {
            case PRIM:
                moveElementary( new int[]{4,0,5,1},new int[][]{{2,6,10,14},{2,6,10,14}});
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveRIn(inspectMove);
                moveRIn(inspectMove);
                break;
            case SIMPLE:
                moveElementary(new int[]{ 0,4,1,5 }, new int[][]{ {2,6,10,14}, {2,6,10,14} });
                break;
        }
    }

    private void moveL(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                rotateSide(false,2);
                moveElementary( new int[]{0,4,1,5},new int[][]{{0,4,8,12},{0,4,8,12}});
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveL(inspectMove);
                moveL(inspectMove);
                break;
            case SIMPLE:
                rotateSide(true, 2);
                moveElementary(new int[]{ 4,0,5,1}, new int[][]{ {0,4,8,12}, {0,4,8,12} });
                break;
        }
    }

    private void moveLIn(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                moveElementary( new int[]{0,4,1,5},new int[][]{{1,5,9,13},{1,5,9,13}});
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveLIn(inspectMove);
                moveLIn(inspectMove);
                break;
            case SIMPLE:
                moveElementary(new int[]{ 4,0,5,1}, new int[][]{ {1,5,9,13}, {1,5,9,13} });
                break;
        }
    }

    private void moveU(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                rotateSide(false,0);
                moveElementary( new int[]{4,2,5,3},new int[][]{{0,1,2,3},{0,1,2,3}});
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveU(inspectMove);
                moveU(inspectMove);
                break;
            case SIMPLE:
                rotateSide(true, 0);
                moveElementary(new int[]{ 2,4,3,5}, new int[][]{ {0,1,2,3}, {0,1,2,3} });
                break;
        }
    }

    private void moveUIn(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                moveElementary( new int[]{4,2,5,3},new int[][]{{4,5,6,7},{4,5,6,7}});
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveUIn(inspectMove);
                moveUIn(inspectMove);
                break;
            case SIMPLE:
                moveElementary(new int[]{ 2,4,3,5}, new int[][]{ {4,5,6,7}, {4,5,6,7} });
                break;
        }
    }

    private void moveD(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                rotateSide(true,1);
                moveElementary( new int[]{2,4,3,5},new int[][]{{12,13,14,15},{12,13,14,15}});
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveD(inspectMove);
                moveD(inspectMove);
                break;
            case SIMPLE:
                rotateSide(false, 1);
                moveElementary(new int[]{ 4,2,5,3}, new int[][]{ {12,13,14,15}, {12,13,14,15} });
                break;
        }
    }

    private void moveDIn(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                moveElementary( new int[]{2,4,3,5},new int[][]{{8,9,10,11},{8,9,10,11}});
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveDIn(inspectMove);
                moveDIn(inspectMove);
                break;
            case SIMPLE:
                moveElementary(new int[]{ 4,2,5,3}, new int[][]{ {8,9,10,11}, {8,9,10,11} });
                break;
        }
    }

    private void moveF(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                rotateSide(false,4);
                moveElementary( new int[]{2,0,3,1},new int[][]{{15,14,13,12},{3,7,11,15}});
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveF(inspectMove);
                moveF(inspectMove);
                break;
            case SIMPLE:
                rotateSide(true, 4);
                moveElementary(new int[]{ 0,2,1,3 }, new int[][]{ {3,7,11,15}, {15,14,13,12} });
                break;
        }
    }

    private void moveFIn(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                moveElementary( new int[]{2,0,3,1},new int[][]{{11,10,9,8},{2,6,10,14}});
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveFIn(inspectMove);
                moveFIn(inspectMove);
                break;
            case SIMPLE:
                moveElementary(new int[]{ 0,2,1,3 }, new int[][]{ {2,6,10,14}, {11,10,9,8} });
                break;
        }
    }
    private void moveB(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                rotateSide(true,5);
                moveElementary( new int[]{0,2,1,3},new int[][]{{12,8,4,0},{0,1,2,3}});
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveB(inspectMove);
                moveB(inspectMove);
                break;
            case SIMPLE:
                rotateSide(false, 5);
                moveElementary(new int[]{ 2,0,3,1 }, new int[][]{ {0,1,2,3}, {12,8,4,0} });
                break;
        }
    }

    private void moveBIn(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                moveElementary( new int[]{0,2,1,3},new int[][]{{13,9,5,1},{4,5,6,7}});
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveBIn(inspectMove);
                moveBIn(inspectMove);
                break;
            case SIMPLE:
                moveElementary(new int[]{ 2,0,3,1 }, new int[][]{ {4,5,6,7}, {13,9,5,1} });
                break;
        }
    }
    private void moveX(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                moveR(new InspectMove(R,PRIM));
                moveRIn(new InspectMove(r, PRIM));
                moveLIn(new InspectMove(l,SIMPLE));
                moveL(new InspectMove(L,SIMPLE));
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveX(inspectMove);
                moveX(inspectMove);
                break;
            case SIMPLE:
                moveR(new InspectMove(R,SIMPLE));
                moveRIn(new InspectMove(r, SIMPLE));
                moveLIn(new InspectMove(l, PRIM));
                moveL(new InspectMove(L, PRIM));
                break;
        }
    }

    private void moveY(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                moveU(new InspectMove(U, PRIM));
                moveUIn(new InspectMove(u, PRIM));
                moveDIn(new InspectMove(d, SIMPLE));
                moveD(new InspectMove(D,SIMPLE));
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveY(inspectMove);
                moveY(inspectMove);
                break;
            case SIMPLE:
                moveU(new InspectMove(U, SIMPLE));
                moveUIn(new InspectMove(u, SIMPLE));
                moveDIn(new InspectMove(d, PRIM));
                moveD(new InspectMove(D, PRIM));
                break;
        }
    }

    private void moveZ(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                moveFTwin(new InspectMove(Fw,PRIM));
                moveBTwin(new InspectMove(Bw, SIMPLE));
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveZ(inspectMove);
                moveZ(inspectMove);
                break;
            case SIMPLE:
                moveFTwin(new InspectMove(Fw,SIMPLE));
                moveBTwin(new InspectMove(Bw, PRIM));
                break;
        }
    }

    private void moveRTwin(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                moveR(new InspectMove(R, PRIM));
                moveRIn(new InspectMove(r, PRIM));
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveRTwin(inspectMove);
                moveRTwin(inspectMove);
                break;
            case SIMPLE:
                moveR(new InspectMove(R, SIMPLE));
                moveRIn(new InspectMove(r, SIMPLE));
                break;
        }
    }

    private void moveLTwin(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                moveL(new InspectMove(L, PRIM));
                moveLIn(new InspectMove(l, PRIM));
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveLTwin(inspectMove);
                moveLTwin(inspectMove);
                break;
            case SIMPLE:
                moveL(new InspectMove(L, SIMPLE));
                moveLIn(new InspectMove(l, SIMPLE));
                break;
        }
    }

    private void moveUTwin(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                moveU(new InspectMove(U, PRIM));
                moveUIn(new InspectMove(u, PRIM));
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveUTwin(inspectMove);
                moveUTwin(inspectMove);
                break;
            case SIMPLE:
                moveU(new InspectMove(U, SIMPLE));
                moveUIn(new InspectMove(u, SIMPLE));
                break;
        }
    }

    private void moveDTwin(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                moveD(new InspectMove(D,PRIM));
                moveDIn(new InspectMove(d, PRIM));
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveDTwin(inspectMove);
                moveDTwin(inspectMove);
                break;
            case SIMPLE:
                moveD(new InspectMove(D, SIMPLE));
                moveDIn(new InspectMove(d, SIMPLE));
                break;
        }
    }

    private void moveFTwin(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                moveF(new InspectMove(F, PRIM));
                moveFIn(new InspectMove(f, PRIM));
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveFTwin(inspectMove);
                moveFTwin(inspectMove);
                break;
            case SIMPLE:
                moveF(new InspectMove(F, SIMPLE));
                moveFIn(new InspectMove(f, SIMPLE));
                break;
        }
    }

    private void moveBTwin(InspectMove inspectMove){
        switch (inspectMove.getMoveType()){
            case PRIM:
                moveB(new InspectMove(B, PRIM));
                moveBIn(new InspectMove(b, PRIM));
                break;
            case DOUBLE:
                inspectMove.setMoveType(MoveTypeEnum.SIMPLE);
                moveBTwin(inspectMove);
                moveBTwin(inspectMove);
                break;
            case SIMPLE:
                moveB(new InspectMove(B, SIMPLE));
                moveBIn(new InspectMove(b, SIMPLE));
                break;
        }
    }

    @Override
    public void moveUsingString(String direction){
        InspectMove inspectMove = new InspectMove(direction);
        if(inspectMove.getMoveType()== MoveTypeEnum.INVALID)
            logger.info("Cannot do \""+direction+"\" move");
        else {
            move(inspectMove);
        }

    }

    @Override
    public void move(InspectMove inspectMove){
        switch (inspectMove.getMove()) {
            case R: moveR(inspectMove); break;
            case L: moveL(inspectMove); break;
            case U: moveU(inspectMove); break;
            case D: moveD(inspectMove); break;
            case F: moveF(inspectMove); break;
            case B: moveB(inspectMove); break;
            case r: moveRIn(inspectMove); break;
            case l: moveLIn(inspectMove); break;
            case u: moveUIn(inspectMove); break;
            case d: moveDIn(inspectMove); break;
            case f: moveFIn(inspectMove); break;
            case b: moveBIn(inspectMove); break;
            case Rw: moveRTwin(inspectMove); break;
            case Lw: moveLTwin(inspectMove); break;
            case Uw: moveUTwin(inspectMove); break;
            case Dw: moveDTwin(inspectMove); break;
            case Fw: moveFTwin(inspectMove); break;
            case Bw: moveBTwin(inspectMove); break;
            case X: moveX(inspectMove); break;
            case Y: moveY(inspectMove); break;
            case Z: moveZ(inspectMove); break;
//                case M: moveM(inspectMove); break;
//                case S: moveS(inspectMove); break;
//                case E: moveE(inspectMove); break;
            case BLANK: /*nothing to do*/ break;
            default:
                logger.info("Cannot do \"" + inspectMove.getMove().toString() + "\" move");
                break;
        }

    }

    public char[][] getCube() {
        return cube;
    }

    public void setCube(char[][] cube) {
        this.cube = cube;
    }
}