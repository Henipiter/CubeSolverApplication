package cubes;


import DTOs.InspectMove;
import DTOs.MoveEnum;
import exceptions.IncorectMoveException;

import java.util.logging.Logger;

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
        char buffer;
        if(clockwise){
            /* vertexes */
            buffer = cube[side][0];
            cube[side][0] = cube[side][2];
            cube[side][2] = cube[side][3];
            cube[side][3] = cube[side][1];
            cube[side][1] = buffer;
        }
        else{
            /* vertexes */
            buffer = cube[side][0];
            cube[side][0] = cube[side][1];
            cube[side][1] = cube[side][3];
            cube[side][3] = cube[side][2];
            cube[side][2] = buffer;
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
            int sideOr = sideOrder[0];
            int field1 = field[1][i];
            char side1 = cube[sideOrder[0]][field[1][i]];
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
        switch (inspectMove.getMoveEnum()){
            case PRIM:
                rotateSide(true,3);
                field =new int[][]{{1,3},{1,3}};
                side = new int[]{4,0,5,1};
                moveElementary( side,field);
                break;
            case DOUBLE:
                inspectMove.setMoveEnum(MoveEnum.SIMPLE);
                moveR(inspectMove);
                moveR(inspectMove);
                break;
            case SIMPLE:
                rotateSide(false, 3);
                field = new int[][]{ {1,3}, {1,3} };
                side = new int[]{ 0,4,1,5 };
                moveElementary(side, field);
                break;
        }
    }

    private void moveL(InspectMove inspectMove){
        int[][] field;
        int[] side;
        switch (inspectMove.getMoveEnum()){
            case PRIM:
                rotateSide(false,2);
                field =new int[][]{{0,2},{0,2}};
                side = new int[]{0,4,1,5};
                moveElementary( side,field);
                break;
            case DOUBLE:
                inspectMove.setMoveEnum(MoveEnum.SIMPLE);
                moveL(inspectMove);
                moveL(inspectMove);
                break;
            case SIMPLE:
                rotateSide(true, 2);
                field = new int[][]{ {0,2}, {0,2} };
                side = new int[]{ 4,0,5,1};
                moveElementary(side, field);
                break;
        }
    }

    private void moveU(InspectMove inspectMove){
        int[][] field;
        int[] side;
        switch (inspectMove.getMoveEnum()){
            case PRIM:
                rotateSide(false,0);
                field =new int[][]{{0,1},{0,1}};
                side = new int[]{4,2,5,3};
                moveElementary( side,field);
                break;
            case DOUBLE:
                inspectMove.setMoveEnum(MoveEnum.SIMPLE);
                moveU(inspectMove);
                moveU(inspectMove);
                break;
            case SIMPLE:
                rotateSide(true, 0);
                field = new int[][]{ {0,1}, {0,1} };
                side = new int[]{ 2,4,3,5};
                moveElementary(side, field);
                break;
        }
    }

    private void moveD(InspectMove inspectMove){
        int[][] field;
        int[] side;
        switch (inspectMove.getMoveEnum()){
            case PRIM:
                rotateSide(true,1);
                field =new int[][]{{2,3},{2,3}};
                side = new int[]{2,4,3,5};
                moveElementary( side,field);
                break;
            case DOUBLE:
                inspectMove.setMoveEnum(MoveEnum.SIMPLE);
                moveD(inspectMove);
                moveD(inspectMove);
                break;
            case SIMPLE:
                rotateSide(false, 1);
                field = new int[][]{ {2,3}, {2,3} };
                side = new int[]{ 4,2,5,3};
                moveElementary(side, field);
                break;
        }
    }

    private void moveF(InspectMove inspectMove){
        int[][] field;
        int[] side;
        switch (inspectMove.getMoveEnum()){
            case PRIM:
                rotateSide(false,4);
                field =new int[][]{{3,2},{1,3}};
                side = new int[]{2,0,3,1};
                moveElementary( side,field);
                break;
            case DOUBLE:
                inspectMove.setMoveEnum(MoveEnum.SIMPLE);
                moveF(inspectMove);
                moveF(inspectMove);
                break;
            case SIMPLE:
                rotateSide(true, 4);
                field = new int[][]{ {1,3}, {3,2} };
                side = new int[]{ 0,2,1,3 };
                moveElementary(side, field);
                break;
        }
    }

    private void moveB(InspectMove inspectMove){
        int[][] field;
        int[] side;
        switch (inspectMove.getMoveEnum()){
            case PRIM:
                rotateSide(true,5);
                field =new int[][]{{2,0},{0,1}};
                side = new int[]{0,2,1,3};
                moveElementary( side,field);
                break;
            case DOUBLE:
                inspectMove.setMoveEnum(MoveEnum.SIMPLE);
                moveB(inspectMove);
                moveB(inspectMove);
                break;
            case SIMPLE:
                rotateSide(false, 5);
                field = new int[][]{ {0,1}, {2,0} };
                side = new int[]{ 2,0,3,1 };
                moveElementary(side, field);
                break;
        }
    }

    private void moveX(InspectMove inspectMove){
        switch (inspectMove.getMoveEnum()){
            case PRIM:
                moveR(new InspectMove("R'"));
                moveL(new InspectMove("L"));
                break;
            case DOUBLE:
                inspectMove.setMoveEnum(MoveEnum.SIMPLE);
                moveX(inspectMove);
                moveX(inspectMove);
                break;
            case SIMPLE:
                moveR(new InspectMove("R"));
                moveL(new InspectMove("L'"));
                break;
        }
    }

    private void moveY(InspectMove inspectMove){
        switch (inspectMove.getMoveEnum()){
            case PRIM:
                moveU(new InspectMove("U'"));
                moveD(new InspectMove("D"));
                break;
            case DOUBLE:
                inspectMove.setMoveEnum(MoveEnum.SIMPLE);
                moveY(inspectMove);
                moveY(inspectMove);
                break;
            case SIMPLE:
                moveU(new InspectMove("U"));
                moveD(new InspectMove("D'"));
                break;
        }
    }

    private void moveZ(InspectMove inspectMove){
        switch (inspectMove.getMoveEnum()){
            case PRIM:
                moveF(new InspectMove("F'"));
                moveB(new InspectMove("B"));
                break;
            case DOUBLE:
                inspectMove.setMoveEnum(MoveEnum.SIMPLE);
                moveZ(inspectMove);
                moveZ(inspectMove);
                break;
            case SIMPLE:
                moveF(new InspectMove("F"));
                moveB(new InspectMove("B'"));
                break;
        }
    }

    @Override
    public void move(String direction){
        InspectMove inspectMove = new InspectMove(direction);
        if(inspectMove.getMoveEnum()== MoveEnum.INVALID)
            logger.info("Cannot do \""+direction+"\" move");
        else{
            switch (inspectMove.getMove()){
                case 'R':
                    moveR(inspectMove);
                    break;
                case 'L':
                    moveL(inspectMove);
                    break;
                case 'U':
                    moveU(inspectMove);
                    break;
                case 'D':
                    moveD(inspectMove);
                    break;
                case 'F':
                    moveF(inspectMove);
                    break;
                case 'B':
                    moveB(inspectMove);
                    break;
                case 'X':
                    moveX(inspectMove);
                    break;
                case 'Y':
                    moveY(inspectMove);
                    break;
                case 'Z':
                    moveZ(inspectMove);
                    break;
                default:
                    logger.info("Cannot do \""+direction+"\" move");
                    break;
            }
        }
    }


    public void setCube(char[][] cube) {
        this.cube = cube;
    }

    public char[][] getCube() {
        return cube;
    }
}
